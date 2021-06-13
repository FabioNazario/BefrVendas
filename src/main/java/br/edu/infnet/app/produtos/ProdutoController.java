package br.edu.infnet.app.produtos;

import br.edu.infnet.domain.produtos.Produto;
import br.edu.infnet.infra.produtos.ProdutoRepository;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    ResourceBundle labels = ResourceBundle.getBundle("br.edu.infnet.bundle");
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping("/produtos/listar")
    public ModelAndView listarProdutos() {

        ModelAndView retorno = new ModelAndView("produtos/index");
        List<Produto> lista = produtoRepository.listar();
        if (lista != null && !lista.isEmpty()) {

            retorno.addObject("produtos", lista);
        } else {

            retorno.addObject("msgAlerta", labels.getString("msg.alerta.semRegistros"));
        }
        return retorno;
    }

    @RequestMapping("/produtos/editar")
    public ModelAndView editarProduto(Integer id) {

        ModelAndView retorno = new ModelAndView("produtos/index");
        try{
            
            Produto entity = produtoRepository.obter(id);
            retorno.addObject("produto", entity);
        } catch (Exception e) {
            
            retorno.addObject("msgErro", labels.getString("msg.erro.inesperado"));
            e.printStackTrace();
        }
        
        return retorno;
    }

    @RequestMapping("/produtos/salvar")
    public ModelAndView salvarProduto(Produto produto) {

        ModelAndView retorno = new ModelAndView("produtos/index");
        
        listarProdutos();
        try {
            if (produto.getId() == null) {
                produtoRepository.inserir(produto);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.inclusaoRegistro"));
            } else {
                produtoRepository.atualizar(produto);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.atualizacaoRegistro"));
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            retorno.addObject("msgErro", labels.getString("msg.erro.campoNuloOuDublicado"));
        }
        
        retorno.addObject("produto", null);
       
        
        return retorno;
    }
    
    
    @RequestMapping("/produtos/excluir")
    public ModelAndView excluirProduto(Integer id) {
        
        ModelAndView retorno = new ModelAndView("produtos/index"); 
        
        try{
            
            Produto produto = produtoRepository.obter(id);
            produtoRepository.excluir(produto);
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
