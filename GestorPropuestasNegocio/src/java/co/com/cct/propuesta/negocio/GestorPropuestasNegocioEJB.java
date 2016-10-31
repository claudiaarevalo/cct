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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.*;

@Stateless
@LocalBean
public class GestorPropuestasNegocioEJB {
    
    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = LogManager.getLogger(GestorPropuestasNegocioEJB.class);
    
    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Collection consultarPropuestasNegocio(String nombreRequerimiento, String razonSocialSocio, String nombreVendedor) {
        logger.trace("Entering consultarPropuestasNegocio");         
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PropuestaNegocio> cq = cb.createQuery(PropuestaNegocio.class);
        Root<PropuestaNegocio> propuestas = cq.from(PropuestaNegocio.class);
        cq.select(propuestas);
                
        List<Predicate> predicate = new ArrayList<Predicate>();
        
        if (nombreRequerimiento != null && !nombreRequerimiento.isEmpty()) {
            Predicate condition = cb.like(propuestas.<Requerimiento>get("requerimiento").<String>get("nombre"),"%"+nombreRequerimiento+"%");
            predicate.add(condition);
        }
        
        if (razonSocialSocio != null && !razonSocialSocio.isEmpty() ) {
            Predicate condition = cb.like(propuestas.<SocioNegocio>get("socioNegocio").<String>get("razonSocial"),"%"+razonSocialSocio+"%");
            predicate.add(condition);
        }
                
        if (nombreVendedor != null && !nombreVendedor.isEmpty()) {
            Predicate condition = cb.like(propuestas.<Requerimiento>get("requerimiento").<Vendedor>get("vendedor").<String>get("nombre"),"%"+nombreVendedor+"%");
            predicate.add(condition);
        }
        
        cq.where(cb.and(predicate.toArray(new Predicate[predicate.size()])));
        Collection result = null;
        
        try{
            Query query = em.createQuery(cq);
            result = query.getResultList();
        }catch(IllegalArgumentException iae){//if the query string is found to be invalid
            logger.error("Exception: ", iae);
        }catch(IllegalStateException ise){//if called for a Java Persistence query language UPDATE or DELETE statement
            logger.error("Exception: ", ise);
        }catch(QueryTimeoutException qte){//if the query execution exceeds the query timeout value set and only the statement is rolled back
            logger.error("Exception: ", qte);
        }catch(TransactionRequiredException tre){//if a lock mode other than NONE has been set and there is no transaction or the persistence context has not been joined to the transaction
            logger.error("Exception: ", tre);
        }catch(PessimisticLockException ple){//if pessimistic locking fails and the transaction is rolled back
            logger.error("Exception: ", ple);
        }catch(LockTimeoutException lte){//if pessimistic locking fails and only the statement is rolled back
            logger.error("Exception: ", lte);
        }catch(PersistenceException pe){//if the query execution exceeds the query timeout value set and the transaction is rolled back
            logger.error("Exception: ", pe);
        }catch(Exception e){
            logger.error("Exception: ", e); 
        }
             
        logger.trace("Exit consultarPropuestasNegocio");
        return result;
    } 
    
}
