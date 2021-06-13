package br.edu.infnet.app.fornecedores;

import br.edu.infnet.domain.fornecedores.Fornecedor;
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
public class FornecedorController {
    
    ResourceBundle labels = ResourceBundle.getBundle("br.edu.infnet.bundle");
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @RequestMapping("/fornecedores/listar")
    public ModelAndView listarFornecedores() {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        List<Fornecedor> lista = fornecedorRepository.listar();
        if (lista != null && !lista.isEmpty()) {

            retorno.addObject("fornecedores", lista);
        } else {

            retorno.addObject("msgAlerta", labels.getString("msg.alerta.semRegistros"));
        }
        return retorno;
    }

    @RequestMapping("/fornecedores/editar")
    public ModelAndView editarFornecedor(Integer id) {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        try {
            
            Fornecedor forn = fornecedorRepository.obter(id);
            retorno.addObject("fornecedor", forn);
        } catch (Exception e) {
            
            retorno.addObject("msgErro", labels.getString("msg.erro.inesperado"));
            e.printStackTrace();
        }
        
        return retorno;
    }

    @RequestMapping("/fornecedores/salvar")
    public ModelAndView salvarFornecedor(Fornecedor fornecedor) {
        
        
        ModelAndView retorno = new ModelAndView("fornecedores/index");
        
        listarFornecedores();
        
         try {
            if(fornecedor.getId() == null) {
                fornecedorRepository.inserir(fornecedor);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.inclusaoRegistro"));
            } else {
                fornecedorRepository.atualizar(fornecedor);
                retorno.addObject("msgSucesso", labels.getString("msg.sucesso.atualizacaoRegistro"));
            } 
        } catch (PersistenceException e) {
            e.printStackTrace();
            retorno.addObject("msgErro", labels.getString("msg.erro.campoNuloOuDublicado"));
        }
         
        retorno.addObject("fornecedor", null);
        
        return retorno;
    }
    
    @RequestMapping("/fornecedores/excluir")
    public ModelAndView excluirFornecedor(Integer id) {
        
        ModelAndView retorno = new ModelAndView("fornecedores/index");
        
         try{
             
            Fornecedor fornecedor = fornecedorRepository.obter(id);
            fornecedorRepository.excluir(fornecedor);
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
