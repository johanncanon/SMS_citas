/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Modelo.SmsReservacion;
import Controlador.Reservacion;
import Modelo.SmsUsuario;
import Modelo.SmsVistaReserva;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
    
    //SESSION
     //Sesion  
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;


    public ReservacionBean() {
        reservacion = new SmsReservacion();
        reservaControler = new Reservacion();
        userView = new SmsUsuario();
        string = new String();
        vistasReserva = new ArrayList<>();

    }
    
    @PostConstruct
    public void init(){
        reservacionClienteAgenda();
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

    

    public void reservacionClienteAgenda() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        userView = (SmsUsuario) httpServletRequest.getSession().getAttribute("Sesion");

        
        vistasReserva = reservaControler.mostrarDatosReservacion(userView);

    }

}
