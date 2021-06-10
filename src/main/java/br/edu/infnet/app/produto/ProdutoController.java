package br.edu.infnet.app.produto;

import br.edu.infnet.domain.produtos.Produto;
import br.edu.infnet.infra.produtos.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping("/produtos/listar")
    public ModelAndView listarProdutos() {

        ModelAndView retorno = new ModelAndView("produtos/index");
        List<Produto> lista = produtoRepository.listar();
        if (lista != null && !lista.isEmpty()) {

            retorno.addObject("produtos", lista);
        } else {

            retorno.addObject("mensagem", "Não existem registros para exibir");
        }
        return retorno;
    }

    @RequestMapping("/produtos/editar")
    public ModelAndView editarProduto(int id) {

        ModelAndView retorno = new ModelAndView("produtos/index");
        Produto entity = produtoRepository.obter(id);
        retorno.addObject("produto", entity);
        return retorno;
    }

    @RequestMapping("/produtos/salvar")
    public ModelAndView salvarProduto(Produto produto) {

        ModelAndView retorno = new ModelAndView("produtos/index");
        if (produto.getId() == null) {
            produtoRepository.inserir(produto);
        } else {
            produtoRepository.atualizar(produto);
        }
        retorno.addObject("produto", null);
        retorno.addObject("produtos", produtoRepository.listar());
        return retorno;
    }
    
    
    @RequestMapping("/produtos/excluir")
    public ModelAndView excluirProduto(int id) {
        
        Produto produto = produtoRepository.obter(id);
        produtoRepository.excluir(produto);
 
        return new ModelAndView("produtos/index"); 
    }
}
