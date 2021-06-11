package br.edu.infnet.infra.fornecedores;

import br.edu.infnet.domain.fornecedores.Fornecedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Repository
public class FornecedorRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Fornecedor> listar() {

        return em
                .createNamedQuery("Fornecedor.findAll")
                .getResultList();
    }
    
    public Fornecedor obter(int id) {
        
        return (Fornecedor) em
                .createNamedQuery("Fornecedor.findById")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Transactional
    public void inserir(Fornecedor fornecedor) {
        em.persist(fornecedor);   
    }
    
    @Transactional
    public void atualizar(Fornecedor fornecedor) {
        em.merge(fornecedor);   
    }
    
    @Transactional
    public void excluir(Fornecedor fornecedor) {
        em.remove(em.getReference(Fornecedor.class, fornecedor.getId()));  
    }
}
