package br.edu.infnet.app.fornecedores;

import br.edu.infnet.domain.fornecedores.Contato;
import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.infra.fornecedores.ContatoRepository;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoController {
    
    ResourceBundle labels = ResourceBundle.getBundle("br.edu.infnet.bundle");

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
            
            retorno.addObject("msgAlerta", labels.getString("msg.alerta.semRegistros"));
        }
        
        List<Fornecedor> fornecedores = fornecedorRepository.listar();
        if(fornecedores != null && !fornecedores.isEmpty()) {
            
            retorno.addObject("fornecedores", fornecedores);
        }
        return retorno;
    }
    
    @RequestMapping("/contatos/editar")
    public ModelAndView editarContato(Integer id) {

        ModelAndView retorno = new ModelAndView("contatos/index");
        
        try{
            
            Contato contato = contatoRepository.obter(id);
            retorno.addObject("contato", contato);

            Fornecedor fornecedor = contato.getFornecedor();
            retorno.addObject("fornecedor", fornecedor);
        } catch (Exception e) {

            retorno.addObject("msgErro", labels.getString("msg.erro.inesperado"));
            e.printStackTrace();
        }
        
        return retorno;
    }
    
    @RequestMapping("/contatos/salvar")
    public ModelAndView salvarContato(Contato contato, Integer fornId) {

        ModelAndView retorno = new ModelAndView("contatos/index");
       
        try {
            Fornecedor forn = fornecedorRepository.obter(fornId);
            contato.setFornecedor(forn);
        } catch (Exception e) {
            retorno.addObject("msgErro", labels.getString("msg.erro.fornecedorInvalido"));
            return retorno;
        }   
        
         listarContatos();
        
        try {
            if(contato.getId() == null) {
                contatoRepository.inserir(contato);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.inclusaoRegistro"));
            } else {
                contatoRepository.atualizar(contato);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.atualizacaoRegistro"));
            }
        } catch (PersistenceException e) {
            retorno.addObject("msgErro", labels.getString("msg.erro.campoNuloOuDublicado"));
            e.printStackTrace();
        }
        
        retorno.addObject("contato", null);
        
        return retorno;
    }
    
    @RequestMapping("/contatos/excluir")
    public ModelAndView excluirContato(Integer id) {
        ModelAndView retorno = new ModelAndView("contatos/index");
       
        try{
            
            Contato contato = contatoRepository.obter(id);
            contatoRepository.excluir(contato);
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
