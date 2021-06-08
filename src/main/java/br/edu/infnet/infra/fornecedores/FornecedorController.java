package br.edu.infnet.infra.fornecedores;

import br.edu.infnet.domain.fornecedores.Fornecedor;
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
    public ModelAndView listarFornecedores(){
        
        ModelAndView retorno = new ModelAndView("fornecedores/index");
        List<Fornecedor> lista = fr.listar();
        
        if(lista != null && !lista.isEmpty()){
            retorno.addObject("fornecedores", lista);
        }else{
            retorno.addObject("mensagem", "Não existe registros para exibir.");
        }
        
        return retorno;
    }
    
}
