package br.edu.infnet.app.produtos;


import br.edu.infnet.domain.produtos.Cotacao;
import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.domain.produtos.Produto;
import br.edu.infnet.infra.produtos.CotacaoRepository;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import br.edu.infnet.infra.produtos.ProdutoRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CotacaoController {

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
            
            retorno.addObject("msgAlerta", "Não há registros para exibir");
        }
        //----------------------------------------------------------------------
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
        Cotacao cotacao = cotacaoRepository.obter(id);
        retorno.addObject("cotacao", cotacao);
        
        Fornecedor fornecedor = cotacao.getFornecedor();
        retorno.addObject("fornecedor", fornecedor);
        
        Produto produto = cotacao.getProduto();
        retorno.addObject("produto", produto);
        
        return retorno;
    }
    
    @RequestMapping("/cotacoes/salvar")
    public ModelAndView salvarCotacao(Cotacao cotacao, Integer fornId, Integer prodId) {

        
        cotacao.setData(new Date());
        
        ModelAndView retorno = new ModelAndView("cotacoes/index");
       
        Fornecedor forn = fornecedorRepository.obter(fornId);            
        cotacao.setFornecedor(forn);
        
        Produto prod = produtoRepository.obter(prodId);            
        cotacao.setProduto(prod);
        
        if(cotacao.getId() == null) {
            cotacaoRepository.inserir(cotacao);
        } else {
            cotacaoRepository.atualizar(cotacao);
        }
        retorno.addObject("cotacao", null);
        retorno.addObject("cotacoes", cotacaoRepository.listar());
        
        List<Fornecedor> fornecedores = fornecedorRepository.listar();
        if(fornecedores != null && !fornecedores.isEmpty()) {
            
            retorno.addObject("fornecedores", fornecedores);
        }
        
        List<Produto> produtos = produtoRepository.listar();
        if(produtos != null && !produtos.isEmpty()) {
            
            retorno.addObject("produtos", produtos);
        }
        
        retorno.addObject("msgSucesso", "Cotação incluída com sucesso.");
        
        return retorno;
 
    }
    
    @RequestMapping("/cotacoes/excluir")
    public ModelAndView excluirCotacao(int id) {
        
        Cotacao cotacao = cotacaoRepository.obter(id);
        cotacaoRepository.excluir(cotacao);
 
        return new ModelAndView("cotacoes/index"); 
    }

}
