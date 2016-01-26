/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Modelo.SmsReservacion;
import Controlador.Reservacion;
import Modelo.SmsUsuario;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;


/**
 *
 * @author Desarrollo_Planit
 */
public class ReservacionBean {

    private SmsReservacion reservacion;
    private Reservacion reservaControler;
    private SmsUsuario userView;
    private String string;
    private List<SmsReservacion> reservaciones;
    private List<SmsReservacion> vistasReserva;
    
    //CALENDARIO
    
    private ScheduleModel eventoModelo;
    private ScheduleEvent evento;
    


    public ReservacionBean() {
        /*reservacion = new SmsReservacion();
        reservaControler = new Reservacion();
        userView = new SmsUsuario();
        string = new String();
        vistasReserva = new ArrayList<>();*/
        
        evento = new DefaultScheduleEvent();
        eventoModelo = new DefaultScheduleModel();

    }
    

    public Reservacion getReservaControler() {
        return reservaControler;
    }

    public void setReservaControler(Reservacion reservaControler) {
        this.reservaControler = reservaControler;
    }

    public SmsUsuario getUserView() {
        return userView;
    }

    public void setUserView(SmsUsuario userView) {
        this.userView = userView;
    }

    public SmsReservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(SmsReservacion reservacion) {
        this.reservacion = reservacion;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<SmsReservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<SmsReservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public List<SmsReservacion> getVistasReserva() {
        return vistasReserva;
    }

    public void setVistasReserva(List<SmsReservacion> vistasReserva) {
        this.vistasReserva = vistasReserva;
    }

    
    // METODOS DE CALENDARIO

    public ScheduleModel getEventoModelo() {
        return eventoModelo;
    }

    public void setEventoModelo(ScheduleModel eventoModelo) {
        this.eventoModelo = eventoModelo;
    }

    public ScheduleEvent getEvento() {
        return evento;
    }

    public void setEvento(ScheduleEvent evento) {
        this.evento = evento;
    }
    
    //METODO PARA SACAR EL CALENDARIO
    
    public void CalendarioReservas(){
        
        
        
        eventoModelo.addEvent(evento);
        
        
    }
    

}
