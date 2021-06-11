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
    private ContatoRepository contatoRepository;
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @RequestMapping("/contatos/listar")
    public ModelAndView listarContatos() {

        ModelAndView retorno = new ModelAndView("contatos/index");
        List<Contato> contatos = contatoRepository.listar();
        if (contatos != null && !contatos.isEmpty()) {

            retorno.addObject("contatos", contatos);
        } else {
            
            retorno.addObject("msgAlerta", "Não há registros para exibir");
        }
        //----------------------------------------------------------------------
        List<Fornecedor> fornecedores = fornecedorRepository.listar();
        if(fornecedores != null && !fornecedores.isEmpty()) {
            
            retorno.addObject("fornecedores", fornecedores);
        }
        return retorno;
    }
    
    @RequestMapping("/contatos/editar")
    public ModelAndView editarContato(int id) {

        ModelAndView retorno = new ModelAndView("contatos/index");
        Contato contato = contatoRepository.obter(id);
        retorno.addObject("contato", contato);
        
        Fornecedor fornecedor = contato.getFornecedor();
        retorno.addObject("fornecedor", fornecedor);
        
        return retorno;
    }
    
    @RequestMapping("/contatos/salvar")
    public ModelAndView salvarContato(Contato contato, Integer fornId) {

        ModelAndView retorno = new ModelAndView("contatos/index");
       
            Fornecedor forn = fornecedorRepository.obter(fornId);            
            contato.setFornecedor(forn);
        
        if(contato.getId() == null) {
            contatoRepository.inserir(contato);
        } else {
            contatoRepository.atualizar(contato);
        }
        retorno.addObject("contato", null);
        retorno.addObject("contatos", contatoRepository.listar());
        
        List<Fornecedor> fornecedores = fornecedorRepository.listar();
        if(fornecedores != null && !fornecedores.isEmpty()) {
            
            retorno.addObject("fornecedores", fornecedores);
        }
        
        retorno.addObject("msgSucesso", "Contato incluído com sucesso.");
        
        return retorno;
    }
    
    @RequestMapping("/contatos/excluir")
    public ModelAndView excluirContato(int id) {
        
        Contato contato = contatoRepository.obter(id);
        contatoRepository.excluir(contato);
 
        return new ModelAndView("contatos/index"); 
    }

}
