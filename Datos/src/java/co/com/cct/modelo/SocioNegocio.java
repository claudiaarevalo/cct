/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.cct.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "SOCIO_NEGOCIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocioNegocio.findAll", query = "SELECT s FROM SocioNegocio s"),
    @NamedQuery(name = "SocioNegocio.findById", query = "SELECT s FROM SocioNegocio s WHERE s.id = :id"),
    @NamedQuery(name = "SocioNegocio.findByRazonSocial", query = "SELECT s FROM SocioNegocio s WHERE s.razonSocial = :razonSocial"),
    @NamedQuery(name = "SocioNegocio.findByNit", query = "SELECT s FROM SocioNegocio s WHERE s.nit = :nit")})
public class SocioNegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Size(max = 20)
    @Column(name = "NIT")
    private String nit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socioNegocio")
    private Collection<PropuestaNegocio> propuestaNegocioCollection;

    public SocioNegocio() {
    }

    public SocioNegocio(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @XmlTransient
    public Collection<PropuestaNegocio> getPropuestaNegocioCollection() {
        return propuestaNegocioCollection;
    }

    public void setPropuestaNegocioCollection(Collection<PropuestaNegocio> propuestaNegocioCollection) {
        this.propuestaNegocioCollection = propuestaNegocioCollection;
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
        if (!(object instanceof SocioNegocio)) {
            return false;
        }
        SocioNegocio other = (SocioNegocio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.cct.modelo.SocioNegocio[ id=" + id + " ]";
    }
    
}
