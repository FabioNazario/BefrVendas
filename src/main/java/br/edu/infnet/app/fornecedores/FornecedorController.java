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
    private FornecedorRepository fornecedorRepository;

    @RequestMapping("/fornecedores/listar")
    public ModelAndView listarFornecedores() {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        List<Fornecedor> lista = fornecedorRepository.listar();
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
        Fornecedor forn = fornecedorRepository.obter(id);
        retorno.addObject("fornecedor", forn);
        return retorno;
    }

    @RequestMapping("/fornecedores/salvar")
    public ModelAndView salvarFornecedor(Fornecedor fornecedor) {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        if (fornecedor.getId() == null) {
            fornecedorRepository.inserir(fornecedor);
        } else {
            fornecedorRepository.atualizar(fornecedor);
        }
        retorno.addObject("fornecedor", null);
        retorno.addObject("fornecedores", fornecedorRepository.listar());
        return retorno;
    }
}
