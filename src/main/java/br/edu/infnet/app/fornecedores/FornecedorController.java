package br.edu.infnet.app.fornecedores;

import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FornecedorController {

    @Autowired
    private FornecedorRepository fr;

    @RequestMapping("/fornecedores/listar")
    public ModelAndView listarFornecedores() {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        List<Fornecedor> lista = fr.listar();
        if (lista != null && !lista.isEmpty()) {

            retorno.addObject("fornecedores", lista);
        } else {

            retorno.addObject("mensagem", "Não existem registros para exibir");
        }
        return retorno;
    }

    @RequestMapping("/fornecedores/editar")
    public ModelAndView editarFornecedor(int id) {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        Fornecedor forn = fr.obter(id);
        retorno.addObject("fornecedor", forn);
        return retorno;
    }

    @RequestMapping("/fornecedores/salvar")
    public ModelAndView salvarFornecedor(Fornecedor fornecedor) {
        
        System.out.println("##############" + fornecedor);

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        if (fornecedor.getId() == null) {
            fr.inserir(fornecedor);
        } else {
            fr.atualizar(fornecedor);
        }
        retorno.addObject("fornecedor", null);
        retorno.addObject("fornecedores", fr.listar());
        return retorno;
    }
}
