/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.cct.presentacion;

import co.com.cct.propuesta.negocio.GestorPropuestasNegocioEJB;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;


@ManagedBean
public class ConsultarPropuestas {
    
    @EJB
    GestorPropuestasNegocioEJB gp;
    
    private String nombreRequerimiento;
    private String razonSocialSocio;
    private String nombreVendedor;
    
    private Collection propuestas;
    
    public ConsultarPropuestas(){
        
    }
    
    public void consultar(){        
        propuestas = gp.consultarPropuestasNegocio(nombreRequerimiento, razonSocialSocio, nombreVendedor);
    }

    public String getNombreRequerimiento() {
        return nombreRequerimiento;
    }

    public void setNombreRequerimiento(String nombreRequerimiento) {
        this.nombreRequerimiento = nombreRequerimiento;
    }

    public Collection getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(Collection propuestas) {
        this.propuestas = propuestas;
    }

    public String getRazonSocialSocio() {
        return razonSocialSocio;
    }

    public void setRazonSocialSocio(String razonSocialSocio) {
        this.razonSocialSocio = razonSocialSocio;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }
  
}
