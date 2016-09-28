/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.cct.propuesta.negocio;

import co.com.cct.modelo.PropuestaNegocio;
import co.com.cct.modelo.Requerimiento;
import co.com.cct.modelo.SocioNegocio;
import co.com.cct.modelo.Vendedor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
@LocalBean
public class GestorPropuestasNegocioEJB {
    
    @PersistenceContext
    private EntityManager em;

    public Collection consultarPropuestasNegocio(String nombreRequerimiento, String razonSocialSocio, String nombreVendedor) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PropuestaNegocio> cq = cb.createQuery(PropuestaNegocio.class);
        Root<PropuestaNegocio> propuestas = cq.from(PropuestaNegocio.class);
        cq.select(propuestas);
                
        List<Predicate> predicate = new ArrayList<Predicate>();
        
        if (nombreRequerimiento != null) {
            Predicate condition = cb.like(propuestas.<Requerimiento>get("requerimiento").<String>get("nombre"),"%"+nombreRequerimiento+"%");
            predicate.add(condition);
        }
        
        if (razonSocialSocio != null) {
            Predicate condition = cb.like(propuestas.<SocioNegocio>get("socioNegocio").<String>get("razonSocial"),"%"+razonSocialSocio+"%");
            predicate.add(condition);
        }
                
        if (nombreVendedor != null) {
            Predicate condition = cb.like(propuestas.<Requerimiento>get("requerimiento").<Vendedor>get("vendedor").<String>get("nombre"),"%"+nombreVendedor+"%");
            predicate.add(condition);
        }
        
        cq.where(cb.and(predicate.toArray(new Predicate[predicate.size()])));
        
        Collection result = em.createQuery(cq).getResultList();
        return result;
    } 
    
}
