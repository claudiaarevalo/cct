/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.cct.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROPUESTA_NEGOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PropuestaNegocio.findAll", query = "SELECT p FROM PropuestaNegocio p"),
    @NamedQuery(name = "PropuestaNegocio.findById", query = "SELECT p FROM PropuestaNegocio p WHERE p.id = :id"),
    @NamedQuery(name = "PropuestaNegocio.findByCosto", query = "SELECT p FROM PropuestaNegocio p WHERE p.costo = :costo"),
    @NamedQuery(name = "PropuestaNegocio.findByResumen", query = "SELECT p FROM PropuestaNegocio p WHERE p.resumen = :resumen")})
public class PropuestaNegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    private Double costo;
    @Size(max = 1000)
    @Column(name = "RESUMEN")
    private String resumen;
    @JoinColumn(name = "REQUERIMIENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Requerimiento requerimiento;
    @JoinColumn(name = "SOCIO_NEGOCIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SocioNegocio socioNegocio;

    public PropuestaNegocio() {
    }

    public PropuestaNegocio(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    public SocioNegocio getSocioNegocio() {
        return socioNegocio;
    }

    public void setSocioNegocio(SocioNegocio socioNegocio) {
        this.socioNegocio = socioNegocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropuestaNegocio)) {
            return false;
        }
        PropuestaNegocio other = (PropuestaNegocio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.cct.modelo.PropuestaNegocio[ id=" + id + " ]";
    }
    
}
