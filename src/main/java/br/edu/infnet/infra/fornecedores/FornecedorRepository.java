package br.edu.infnet.infra.fornecedores;

import br.edu.infnet.domain.fornecedores.Fornecedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class FornecedorRepository {
    @PersistenceContext
    private EntityManager em;
    
    public List<Fornecedor> listar(){
        return em.createNamedQuery("Fornecedor.findAll")
                .getResultList();
    }
}
