package br.edu.infnet.app.fornecedores;

import br.edu.infnet.domain.fornecedores.Contato;
import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.infra.fornecedores.ContatoRepository;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoController {

    @Autowired
    private ContatoRepository cr;
    
    @Autowired
    private FornecedorRepository fr;

    @RequestMapping("/contatos/listar")
    public ModelAndView listarContatos() {

        ModelAndView retorno = new ModelAndView("contatos/index");
        List<Contato> contatos = cr.listar();
        if (contatos != null && !contatos.isEmpty()) {

            retorno.addObject("contatos", contatos);
        } else {
            
            retorno.addObject("mensagem", "Não há registros para exibir");
        }
        //----------------------------------------------------------------------
        List<Fornecedor> fornecedores = fr.listar();
        if(fornecedores != null && !fornecedores.isEmpty()) {
            
            retorno.addObject("fornecedores", fornecedores);
        }
        return retorno;
    }
    
    @RequestMapping("/contatos/salvar")
    public ModelAndView salvarContato(Contato contato, Integer fornId) {

        ModelAndView retorno = new ModelAndView("contatos/index");
        if(contato.getId() == null) {

            //------------------------------------------------------------------
            //O certo é obterComContatos - use o FETCH
            Fornecedor forn = fr.obter(fornId);            
            ArrayList<Contato> contatos = new ArrayList<>();
            //------------------------------------------------------------------
            contatos.add(contato);
            forn.setContatos(contatos);
            contato.setFornecedor(forn);
            cr.inserir(contato);
        } else {
            
        }
        retorno.addObject("contato", null);
        retorno.addObject("contatos", cr.listar());
        return retorno;
    }
}
