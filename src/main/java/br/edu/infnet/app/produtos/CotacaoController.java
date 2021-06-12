package br.edu.infnet.app.produtos;


import br.edu.infnet.domain.produtos.Cotacao;
import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.domain.produtos.Produto;
import br.edu.infnet.infra.produtos.CotacaoRepository;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import br.edu.infnet.infra.produtos.ProdutoRepository;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CotacaoController {

    ResourceBundle labels = ResourceBundle.getBundle("br.edu.infnet.bundle");
    
    @Autowired
    private CotacaoRepository cotacaoRepository;
    
    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping("/cotacoes/listar")
    public ModelAndView listarCotacoes() {

        ModelAndView retorno = new ModelAndView("cotacoes/index");
        List<Cotacao> cotacoes = cotacaoRepository.listar();
        if (cotacoes != null && !cotacoes.isEmpty()) {

            retorno.addObject("cotacoes", cotacoes);
        } else {
            
            retorno.addObject("msgAlerta", labels.getString("msg.alerta.semRegistros"));
        }
        
        List<Fornecedor> fornecedores = fornecedorRepository.listar();
        if(fornecedores != null && !fornecedores.isEmpty()) {
            
            retorno.addObject("fornecedores", fornecedores);
        }
        
        List<Produto> produtos = produtoRepository.listar();
        if(produtos != null && !produtos.isEmpty()) {
            
            retorno.addObject("produtos", produtos);
        }
        return retorno;
    }
    
    @RequestMapping("/cotacoes/editar")
    public ModelAndView editarCotacao(int id) {

        ModelAndView retorno = new ModelAndView("cotacoes/index");
        
        try {
            Cotacao cotacao = cotacaoRepository.obter(id);
            retorno.addObject("cotacao", cotacao);

            Fornecedor fornecedor = cotacao.getFornecedor();
            retorno.addObject("fornecedor", fornecedor);

            Produto produto = cotacao.getProduto();
            retorno.addObject("produto", produto);  
        } catch (Exception e) {
            
            retorno.addObject("msgErro", labels.getString("msg.erro.inesperado"));
            e.printStackTrace();
        }
        
        
        return retorno;
    }
    
    @RequestMapping("/cotacoes/salvar")
    public ModelAndView salvarCotacao(Cotacao cotacao, Integer fornId, Integer prodId) {

        ModelAndView retorno = new ModelAndView("cotacoes/index"); 
        cotacao.setData(new Date());

        try {
            Fornecedor forn = fornecedorRepository.obter(fornId);
            cotacao.setFornecedor(forn);
        } catch (Exception e) {
            retorno.addObject("msgErro", labels.getString("msg.erro.fornecedorInvalido"));
            return retorno;
        }   
        
        try {
            Produto prod = produtoRepository.obter(prodId);            
            cotacao.setProduto(prod);
        } catch (Exception e) {
            retorno.addObject("msgErro", labels.getString("msg.erro.protutoInvalido"));
            return retorno;
        }  
        
        listarCotacoes();
        
        try {
            if(cotacao.getId() == null) {
                cotacaoRepository.inserir(cotacao);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.inclusaoRegistro"));
            } else {
                cotacaoRepository.atualizar(cotacao);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.atualizacaoRegistro"));
            } 
        } catch (PersistenceException e) {
            retorno.addObject("msgErro", labels.getString("msg.erro.campoNulo"));
        }
            
        
        retorno.addObject("cotacao", null);
  
        return retorno;
 
    }
    
    @RequestMapping("/cotacoes/excluir")
    public ModelAndView excluirCotacao(int id) {
        
        ModelAndView retorno = new ModelAndView("cotacoes/index"); 
        try{
            Cotacao cotacao = cotacaoRepository.obter(id);
            cotacaoRepository.excluir(cotacao);
            retorno.addObject("msgSucesso", labels.getString("msg.sucesso.registroExcluido"));
        }catch (NoResultException e){
            
            retorno.addObject("msgErro", labels.getString("msg.erro.exlcuidoNaoEncontrado"));
            e.printStackTrace();
        } catch (Exception e) {
            
            retorno.addObject("msgErro", labels.getString("msg.erro.inesperado"));
            e.printStackTrace();
        }
        
        return retorno; 
    }

}
