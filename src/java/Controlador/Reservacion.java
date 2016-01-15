/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.ICiudadDao;
import DAO.ICostosServiciosDAO;
import DAO.IReservacionDao;
import DAO.IServicioDao;
import DAO.IUsuarioDao;
import DAO.IVehiculoDao;
import DAO.ImpAgendaDao;
import DAO.ImpCiudadDao;
import DAO.ImpCostosServiciosDAO;
import DAO.ImpReservacionDao;
import DAO.ImpServicioDao;
import DAO.ImpUsuarioDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsAgenda;
import Modelo.SmsCalificacion;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsCostosServicio;
import Modelo.SmsReservacion;
import Modelo.SmsServicios;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.util.Calendar;
import java.util.Date;
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

    //variable para sacar ciudades segun registro del cliente
    private SmsUsuario usuarioID;
    private List<SmsReservacion> ciudadesReservadas;

    //Constructor
    public Reservacion() {
        usuarioID = new SmsUsuario();
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
    public void registrarReservacion(SmsAgenda a, SmsCiudad c, SmsReservacion r, SmsUsuario cliente) {
        reservacion = r;
        agenda = a;
        SmsCiudad ciudad = c;
        IUsuarioDao usuDao = new ImpUsuarioDao();

        this.cliente = usuDao.consultarUsuario(cliente).get(0);

        //Consulta de objetos
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);

        //asignacion
        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(this.cliente);
        reservacion.setSmsAgenda(agenda);

        //Registro
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.registrarReservacion(reservacion);

    }

    public void modificacionReservacion(SmsUsuario u, SmsAgenda a, SmsCiudad c, SmsReservacion r) {

        reservacion = r;
        agenda = a;
        SmsCiudad ciudad = c;
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

    public void eliminarReservacion(SmsReservacion r) {

        reservacion = r;

        //Eliminacion
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.eliminarReservacion(reservacion);

    }

    //Metodos para la reservacion
    public int calcularCostoReservacion(SmsAgenda a, SmsServicios s, SmsVehiculo v) {
        //Instacia de variable propias del metodo
        int costo = 0;
        agenda = a;
        SmsVehiculo vehiculo = v;
        SmsServicios servicio = s;
        SmsCostosServicio costos;

        //instancia de objetos relacionados a los DAO necesarios
        ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
        IServicioDao serDao = new ImpServicioDao();
        IVehiculoDao vehDao = new ImpVehiculoDao();

        //Consultamos el objeto completo que contiene la informacion de vehiculo y servicio
        vehiculo = vehDao.consultarVehiculo(vehiculo).get(0);
        servicio = serDao.ConsultarServicio(servicio).get(0);
        SmsCategoria categoria = vehiculo.getSmsCategoria();//obtenemos la categoria del vehiculo extrayendola del mismo

        // Crear 2 instancias de Calendar
        Calendar calFechaInicio = Calendar.getInstance();
        Calendar calFechaLlegada = Calendar.getInstance();
        

        //Variables necesarias para el calculo del costo de la reservacion       
        Date fechaInicio = new Date();
        Date fechaLlegada = new Date();
        
        long milis1;
        long milis2;
        long diff;
        long diffHours;
        long diffDays;
        long diffWeek;
        long diffMonth;

        costos = cosDao.consultarCostoServicio(servicio, categoria).get(0);//consultamos el costo segun servicio y categoria del vehiculo

       
        fechaInicio.setYear(agenda.getAgendaFechaInicio().getYear());
        fechaInicio.setMonth(agenda.getAgendaFechaInicio().getMonth());
        fechaInicio.setMinutes(agenda.getAgendaFechaInicio().getDay());
        fechaInicio.setHours(agenda.getAgendaHoraInicio().getHours());
        fechaInicio.setMinutes(agenda.getAgendaHoraInicio().getMinutes());
        fechaInicio.setSeconds(agenda.getAgendaHoraInicio().getMinutes());
        
        fechaLlegada.setYear(agenda.getAgendaFechaLlegada().getYear());
        fechaLlegada.setMonth(agenda.getAgendaFechaLlegada().getMonth());
        fechaLlegada.setMinutes(agenda.getAgendaFechaLlegada().getDay());
        fechaLlegada.setHours(agenda.getAgendaHoraLlegada().getHours());
        fechaLlegada.setMinutes(agenda.getAgendaHoraLlegada().getMinutes());
        fechaLlegada.setSeconds(agenda.getAgendaHoraLlegada().getMinutes());
        
        //asignamos a los objetos calendar la fecha de inicio con la hora de inicio y la fecha de llegada
        //con su hora de llegada
        calFechaInicio.setTime(fechaInicio);
        calFechaLlegada.setTime(fechaLlegada);

        // conseguir la representacion de la fecha en milisegundos
        milis1 = calFechaInicio.getTimeInMillis();
        milis2 = calFechaLlegada.getTimeInMillis();

        // calcular la diferencia en horas
        diff = milis2 - milis1;
        diffHours = diff / (60 * 60 * 1000);

        //Calcular la diferencia en dias
        diffDays = diff / (24 * 60 * 60 * 1000);

        //Calcular la diferencia en semanas
        diffWeek = diff / (7 * 24 * 60 * 60 * 1000);

        //Calcular la diferencia en meses
        diffMonth = diff / (30 * 24 * 60 * 60 * 1000);

        switch (servicio.getServiciosNombre()) {
            case "Plan hora":
                costo = ((int) diffHours) * costos.getSmsPrecio();
                break;
            case "Plan dia":
                costo = ((int) diffDays) * costos.getSmsPrecio();
                break;
            case "Plan semana":
                costo = ((int) diffWeek) * costos.getSmsPrecio();
                break;
            case "Plan mes":
                costo = ((int) diffMonth) * costos.getSmsPrecio();
                break;
        }
        return costo;
    }

    //METODO PARA LLAMAR LAS CIUDADES DE LOS CLIENTES QUE HAN RESERVADO
    public List<SmsReservacion> getCiudadesReservadas() {
        return ciudadesReservadas;
    }

    public void setCiudadesReservadas(List<SmsReservacion> ciudadesReservadas) {
        this.ciudadesReservadas = ciudadesReservadas;
    }

    public SmsUsuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(SmsUsuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void listaCiudadesReservacion(SmsUsuario us) {
        usuarioID = us;
        IReservacionDao linkDao = new ImpReservacionDao();
        ciudadesReservadas = linkDao.mostrarCiudadReservacion(usuarioID);
    }

}
