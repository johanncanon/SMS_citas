/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.ICiudadDao;
import DAO.IReservacionDao;
import DAO.ImpAgendaDao;
import DAO.ImpCiudadDao;
import DAO.ImpReservacionDao;
import Modelo.SmsAgenda;
import Modelo.SmsCalificacion;
import Modelo.SmsCiudad;
import Modelo.SmsReservacion;
import Modelo.SmsUsuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Desarrollo_Planit
 */
public class Reservacion {
    
    private SmsReservacion reservacion;
    private List<SmsReservacion> reservaciones;
    
    //Relacion con otras clases
    private SmsAgenda agenda;
    private SmsUsuario cliente;
    private SmsCalificacion calificacion;

    //Sesion  
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;
    
    //Constructor
    public Reservacion() {    
    }
    
    
    
    //Getters & Setter
    public SmsReservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(SmsReservacion reservacion) {
        this.reservacion = reservacion;
    }

    public List<SmsReservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<SmsReservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public SmsAgenda getAgenda() {
        return agenda;
    }

    public void setAgenda(SmsAgenda agenda) {
        this.agenda = agenda;
    }

    public SmsUsuario getCliente() {
        return cliente;
    }

    public void setCliente(SmsUsuario cliente) {
        this.cliente = cliente;
    }

    public SmsCalificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(SmsCalificacion calificacion) {
        this.calificacion = calificacion;
    }
    
    
    //Metodos
    
    public void registrarReservacion(SmsAgenda a, SmsCiudad c, SmsReservacion r){
        
        reservacion = r;
        agenda= a;
        SmsCiudad ciudad=c;        
        
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        
        cliente = (SmsUsuario) httpServletRequest.getSession().getAttribute("Sesion");
        
        //Consulta de objetos
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        
        IAgendaDao agDao = new ImpAgendaDao();
        agenda = agDao.consultarAgenda(agenda).get(0);
        
        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(cliente);
        reservacion.setSmsAgenda(agenda);
        
        //Registro
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.registrarReservacion(reservacion);
        
    }
    
     public void modificacionReservacion(SmsUsuario u, SmsAgenda a, SmsCiudad c, SmsReservacion r){
        
        reservacion = r;
        agenda= a;
        SmsCiudad ciudad=c;
        cliente = u;
        
        //Consulta de objetos
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        
        IAgendaDao agDao = new ImpAgendaDao();
        
        
        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(cliente);
        reservacion.setSmsAgenda(agenda);
        
        //Modificacion
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.modificarReservacion(reservacion);
        
    }
     
      public void eliminarReservacion(SmsReservacion r){
        
        reservacion = r;     
                    
      //Eliminacion
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.eliminarReservacion(reservacion);
        
    }
    
    
    
}
