/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.domain.produtos;

import br.edu.infnet.domain.fornecedores.Fornecedor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cotacao", catalog = "cadastro", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cotacao.findAll", query = "SELECT c FROM Cotacao c"),
    @NamedQuery(name = "Cotacao.findById", query = "SELECT c FROM Cotacao c WHERE c.id = :id")
})
public class Cotacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    
    @Column(precision = 14, scale = 2)
    private BigDecimal valor;
    
    @NotNull
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Fornecedor fornecedor;
    
    @NotNull
    @JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Produto produto;

    public Cotacao() {
    }

    public Cotacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cotacao)) {
            return false;
        }
        Cotacao other = (Cotacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.infnet.domain.produtos.Cotacao[ id=" + id + " ]";
    }
    
}
