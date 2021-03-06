package Modelo;
// Generated 26-nov-2015 15:26:50 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SmsAgenda generated by hbm2java
 */
public class SmsAgenda  implements java.io.Serializable {
    
    
     private Integer idAgenda;
     private SmsVehiculo smsVehiculo;
     private SmsEmpleado smsEmpleado;
     private Date agendaFechaInicio;
     private Date agendaFechaLlegada;
     private Date agendaHoraInicio;
     private Date agendaHoraLlegada;
     private Set<SmsReservacion> smsReservacions = new HashSet<SmsReservacion>(0);

    public SmsAgenda() {
    }
	
    public SmsAgenda(SmsVehiculo smsVehiculo, SmsEmpleado smsEmpleado) {
        this.smsVehiculo = smsVehiculo;
        this.smsEmpleado = smsEmpleado;
    }
    
    public SmsAgenda(SmsVehiculo smsVehiculo, SmsEmpleado smsEmpleado, Date agendaFechaInicio, Date agendaFechaLlegada, Date agendaHoraInicio, Date agendaHoraLlegada, Set<SmsReservacion> smsReservacions) {
       this.smsVehiculo = smsVehiculo;
       this.smsEmpleado = smsEmpleado;
       this.agendaFechaInicio = agendaFechaInicio;
       this.agendaFechaLlegada = agendaFechaLlegada;
       this.agendaHoraInicio = agendaHoraInicio;
       this.agendaHoraLlegada = agendaHoraLlegada;
       this.smsReservacions = smsReservacions;
    }
   
    public Integer getIdAgenda() {
        return this.idAgenda;
    }
    
    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }
    public SmsVehiculo getSmsVehiculo() {
        return this.smsVehiculo;
    }
    
    public void setSmsVehiculo(SmsVehiculo smsVehiculo) {
        this.smsVehiculo = smsVehiculo;
    }

    public SmsEmpleado getSmsEmpleado() {
        return smsEmpleado;
    }

    public void setSmsEmpleado(SmsEmpleado smsEmpleado) {
        this.smsEmpleado = smsEmpleado;
    }
    
    public Date getAgendaFechaInicio() {
        return this.agendaFechaInicio;
    }
    
    public void setAgendaFechaInicio(Date agendaFechaInicio) {
        this.agendaFechaInicio = agendaFechaInicio;
    }
    public Date getAgendaFechaLlegada() {
        return this.agendaFechaLlegada;
    }
    
    public void setAgendaFechaLlegada(Date agendaFechaLlegada) {
        this.agendaFechaLlegada = agendaFechaLlegada;
    }
    public Date getAgendaHoraInicio() {
        return this.agendaHoraInicio;
    }
    
    public void setAgendaHoraInicio(Date agendaHoraInicio) {
        this.agendaHoraInicio = agendaHoraInicio;
    }
    public Date getAgendaHoraLlegada() {
        return this.agendaHoraLlegada;
    }
    
    public void setAgendaHoraLlegada(Date agendaHoraLlegada) {
        this.agendaHoraLlegada = agendaHoraLlegada;
    }
    public Set<SmsReservacion> getSmsReservacions() {
        return this.smsReservacions;
    }
    
    public void setSmsReservacions(Set<SmsReservacion> smsReservacions) {
        this.smsReservacions = smsReservacions;
    }




}


