package br.edu.infnet.infra.produtos;

import br.edu.infnet.infra.produtos.*;
import br.edu.infnet.domain.produtos.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Repository
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Produto> listar() {

        return em
                .createNamedQuery("Produto.findAll")
                .getResultList();
    }
    
    public Produto obter(int id) {
        
        return (Produto) em
                .createNamedQuery("Produto.findById")
                .setParameter("id", id)
                .getSingleResult();
    }
    
    @Transactional
    public void inserir(Produto produto) {
        em.persist(produto);   
    }
    
    @Transactional
    public void atualizar(Produto produto) {
        em.merge(produto);   
    }
    
    @Transactional
    public void excluir(Produto produto) {
        em.remove(em.getReference(Produto.class, produto.getId()));  
    }
}
