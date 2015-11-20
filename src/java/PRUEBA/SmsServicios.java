package PRUEBA;
// Generated 17/11/2015 12:16:55 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsServicios generated by hbm2java
 */
public class SmsServicios  implements java.io.Serializable {


     private Integer idServicios;
     private String serviciosNombre;
     private String serviciosDescripcion;
     private Integer serviciosPrecio;
     private Set<SmsCostosServicio> smsCostosServicios = new HashSet<SmsCostosServicio>(0);
     private Set<SmsReservacion> smsReservacions = new HashSet<SmsReservacion>(0);

    public SmsServicios() {
    }

    public SmsServicios(String serviciosNombre, String serviciosDescripcion, Integer serviciosPrecio, Set<SmsCostosServicio> smsCostosServicios, Set<SmsReservacion> smsReservacions) {
       this.serviciosNombre = serviciosNombre;
       this.serviciosDescripcion = serviciosDescripcion;
       this.serviciosPrecio = serviciosPrecio;
       this.smsCostosServicios = smsCostosServicios;
       this.smsReservacions = smsReservacions;
    }
   
    public Integer getIdServicios() {
        return this.idServicios;
    }
    
    public void setIdServicios(Integer idServicios) {
        this.idServicios = idServicios;
    }
    public String getServiciosNombre() {
        return this.serviciosNombre;
    }
    
    public void setServiciosNombre(String serviciosNombre) {
        this.serviciosNombre = serviciosNombre;
    }
    public String getServiciosDescripcion() {
        return this.serviciosDescripcion;
    }
    
    public void setServiciosDescripcion(String serviciosDescripcion) {
        this.serviciosDescripcion = serviciosDescripcion;
    }
    public Integer getServiciosPrecio() {
        return this.serviciosPrecio;
    }
    
    public void setServiciosPrecio(Integer serviciosPrecio) {
        this.serviciosPrecio = serviciosPrecio;
    }
    public Set<SmsCostosServicio> getSmsCostosServicios() {
        return this.smsCostosServicios;
    }
    
    public void setSmsCostosServicios(Set<SmsCostosServicio> smsCostosServicios) {
        this.smsCostosServicios = smsCostosServicios;
    }
    public Set<SmsReservacion> getSmsReservacions() {
        return this.smsReservacions;
    }
    
    public void setSmsReservacions(Set<SmsReservacion> smsReservacions) {
        this.smsReservacions = smsReservacions;
    }




}

