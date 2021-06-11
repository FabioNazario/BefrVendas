package br.edu.infnet.infra.produtos;

import br.edu.infnet.domain.produtos.Cotacao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Repository
public class CotacaoRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Cotacao> listar() {
        
        return em
                .createNamedQuery("Cotacao.findAll")
                .getResultList();
    }
   
     public Cotacao obter(int id) {
        
        return (Cotacao) em
                .createNamedQuery("Cotacao.findById")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Transactional
    public void inserir(Cotacao cotacao) {
        
        em.persist(cotacao);
    }
     
    @Transactional
    public void atualizar(Cotacao cotacao) {
        em.merge(cotacao);   
    }
    
    @Transactional
    public void excluir(Cotacao cotacao) {
        em.remove(em.getReference(Cotacao.class, cotacao.getId()));  
    }
}
