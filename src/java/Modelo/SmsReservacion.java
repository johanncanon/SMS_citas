package Modelo;
// Generated 26-nov-2015 15:26:50 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsReservacion generated by hbm2java
 */
public class SmsReservacion  implements java.io.Serializable {

     private Integer idReservacion;
     private SmsAgenda smsAgenda;
     private SmsCiudad smsCiudad;
     private SmsUsuario smsUsuario;
     private String reservacionLugarLlegada;
     private String reservacionLugarDestino;
     private String reservacionNotas;
     private Set<SmsCalificacion> smsCalificacions = new HashSet<SmsCalificacion>(0);

    public SmsReservacion() {
    }

	
    public SmsReservacion(SmsAgenda smsAgenda, SmsCiudad smsCiudad, SmsUsuario smsUsuario) {
        this.smsAgenda = smsAgenda;
        this.smsCiudad = smsCiudad;
        this.smsUsuario = smsUsuario;
    }
    public SmsReservacion(SmsAgenda smsAgenda, SmsCiudad smsCiudad, SmsUsuario smsUsuario, String reservacionLugarLlegada, String reservacionLugarDestino, String reservacionNotas, Set<SmsCalificacion> smsCalificacions) {
       this.smsAgenda = smsAgenda;
       this.smsCiudad = smsCiudad;
       this.smsUsuario = smsUsuario;
       this.reservacionLugarLlegada = reservacionLugarLlegada;
       this.reservacionLugarDestino = reservacionLugarDestino;
       this.reservacionNotas = reservacionNotas;
       this.smsCalificacions = smsCalificacions;
    }
   
    public Integer getIdReservacion() {
        return this.idReservacion;
    }
    
    public void setIdReservacion(Integer idReservacion) {
        this.idReservacion = idReservacion;
    }
    public SmsAgenda getSmsAgenda() {
        return this.smsAgenda;
    }
    
    public void setSmsAgenda(SmsAgenda smsAgenda) {
        this.smsAgenda = smsAgenda;
    }
    public SmsCiudad getSmsCiudad() {
        return this.smsCiudad;
    }
    
    public void setSmsCiudad(SmsCiudad smsCiudad) {
        this.smsCiudad = smsCiudad;
    }
    public SmsUsuario getSmsUsuario() {
        return this.smsUsuario;
    }
    
    public void setSmsUsuario(SmsUsuario smsUsuario) {
        this.smsUsuario = smsUsuario;
    }
    public String getReservacionLugarLlegada() {
        return this.reservacionLugarLlegada;
    }
    
    public void setReservacionLugarLlegada(String reservacionLugarLlegada) {
        this.reservacionLugarLlegada = reservacionLugarLlegada;
    }
    public String getReservacionLugarDestino() {
        return this.reservacionLugarDestino;
    }
    
    public void setReservacionLugarDestino(String reservacionLugarDestino) {
        this.reservacionLugarDestino = reservacionLugarDestino;
    }
    public String getReservacionNotas() {
        return this.reservacionNotas;
    }
    
    public void setReservacionNotas(String reservacionNotas) {
        this.reservacionNotas = reservacionNotas;
    }
    public Set<SmsCalificacion> getSmsCalificacions() {
        return this.smsCalificacions;
    }
    
    public void setSmsCalificacions(Set<SmsCalificacion> smsCalificacions) {
        this.smsCalificacions = smsCalificacions;
    }




}


