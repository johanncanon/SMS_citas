package Modelo;
// Generated 26-nov-2015 15:26:50 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsReferencia generated by hbm2java
 */
public class SmsReferencia  implements java.io.Serializable {


     private Integer idReferencia;
     private SmsMarca smsMarca;
     private String referenciaNombre;
     private Set<SmsVehiculo> smsVehiculos = new HashSet<SmsVehiculo>(0);

    public SmsReferencia() {
    }

	
    public SmsReferencia(SmsMarca smsMarca) {
        this.smsMarca = smsMarca;
    }
    public SmsReferencia(SmsMarca smsMarca, String referenciaNombre, Set<SmsVehiculo> smsVehiculos) {
       this.smsMarca = smsMarca;
       this.referenciaNombre = referenciaNombre;
       this.smsVehiculos = smsVehiculos;
    }
   
    public Integer getIdReferencia() {
        return this.idReferencia;
    }
    
    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }
    public SmsMarca getSmsMarca() {
        return this.smsMarca;
    }
    
    public void setSmsMarca(SmsMarca smsMarca) {
        this.smsMarca = smsMarca;
    }
    public String getReferenciaNombre() {
        return this.referenciaNombre;
    }
    
    public void setReferenciaNombre(String referenciaNombre) {
        this.referenciaNombre = referenciaNombre;
    }
    public Set<SmsVehiculo> getSmsVehiculos() {
        return this.smsVehiculos;
    }
    
    public void setSmsVehiculos(Set<SmsVehiculo> smsVehiculos) {
        this.smsVehiculos = smsVehiculos;
    }




}


