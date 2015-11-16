package Modelo;
// Generated 16-nov-2015 10:24:03 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsCiudad generated by hbm2java
 */
public class SmsCiudad  implements java.io.Serializable {


     private Integer idCiudad;
     private SmsPais smsPais;
     private String ciudadNombre;
     private Set<SmsVehiculo> smsVehiculos = new HashSet<SmsVehiculo>(0);
     private Set<SmsUsuario> smsUsuarios = new HashSet<SmsUsuario>(0);
     private Set<SmsReservacion> smsReservacions = new HashSet<SmsReservacion>(0);

    public SmsCiudad() {
    }

	
    public SmsCiudad(SmsPais smsPais) {
        this.smsPais = smsPais;
    }
    public SmsCiudad(SmsPais smsPais, String ciudadNombre, Set<SmsVehiculo> smsVehiculos, Set<SmsUsuario> smsUsuarios, Set<SmsReservacion> smsReservacions) {
       this.smsPais = smsPais;
       this.ciudadNombre = ciudadNombre;
       this.smsVehiculos = smsVehiculos;
       this.smsUsuarios = smsUsuarios;
       this.smsReservacions = smsReservacions;
    }
   
    public Integer getIdCiudad() {
        return this.idCiudad;
    }
    
    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }
    public SmsPais getSmsPais() {
        return this.smsPais;
    }
    
    public void setSmsPais(SmsPais smsPais) {
        this.smsPais = smsPais;
    }
    public String getCiudadNombre() {
        return this.ciudadNombre;
    }
    
    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }
    public Set<SmsVehiculo> getSmsVehiculos() {
        return this.smsVehiculos;
    }
    
    public void setSmsVehiculos(Set<SmsVehiculo> smsVehiculos) {
        this.smsVehiculos = smsVehiculos;
    }
    public Set<SmsUsuario> getSmsUsuarios() {
        return this.smsUsuarios;
    }
    
    public void setSmsUsuarios(Set<SmsUsuario> smsUsuarios) {
        this.smsUsuarios = smsUsuarios;
    }
    public Set<SmsReservacion> getSmsReservacions() {
        return this.smsReservacions;
    }
    
    public void setSmsReservacions(Set<SmsReservacion> smsReservacions) {
        this.smsReservacions = smsReservacions;
    }




}


