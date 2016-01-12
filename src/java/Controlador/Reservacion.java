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
        int costo = 0;
        SmsAgenda agenda = a;
        SmsVehiculo vehiculo = v;
        SmsServicios servicio = s;
        SmsCostosServicio costos;

        ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
        IServicioDao serDao = new ImpServicioDao();
        IVehiculoDao vehDao = new ImpVehiculoDao();

        vehiculo = vehDao.consultarVehiculo(vehiculo).get(0);
        servicio = serDao.ConsultarServicio(servicio).get(0);
        SmsCategoria categoria = vehiculo.getSmsCategoria();
        // Crear 2 instancias de Calendar
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        Calendar cal4 = Calendar.getInstance();
        // Establecer las fechas
        String enteroString = "";
        int horas = 0;

        switch (servicio.getServiciosNombre()) {

            case "Por horas":
                costos = cosDao.consultarCostoServicio(servicio, categoria).get(0);
                if (agenda.getAgendaFechaInicio().equals(agenda.getAgendaFechaLlegada())) {
                    cal1.setTime(agenda.getAgendaHoraInicio());
                    cal2.setTime(agenda.getAgendaHoraLlegada());
                    // conseguir la representacion de la fecha en milisegundos

                    long milis1 = cal1.getTimeInMillis();
                    long milis2 = cal2.getTimeInMillis();

                    // calcular la diferencia en horas
                    long diff = milis2 - milis1;
                    long diffHours = diff / (60 * 60 * 1000);
                    enteroString = "" + diffHours;
                    horas = Integer.parseInt(enteroString);
                    costo = (horas) * costos.getSmsPrecio();
                } else {
                    Date fecha1 = agenda.getAgendaFechaInicio();
                    Date fecha2 = agenda.getAgendaFechaLlegada();

                    cal1.set(fecha1.getYear(), fecha1.getMonth(), fecha1.getDay(), agenda.getAgendaFechaInicio().getHours(), agenda.getAgendaFechaInicio().getMinutes(), agenda.getAgendaFechaInicio().getSeconds());
                    cal2.set(fecha2.getYear(), fecha2.getMonth(), fecha2.getDay(), agenda.getAgendaFechaLlegada().getHours(), agenda.getAgendaFechaLlegada().getMinutes(), agenda.getAgendaFechaLlegada().getSeconds());

                    long milis1 = cal1.getTimeInMillis();
                    long milis2 = cal2.getTimeInMillis();

                    // calcular la diferencia en horas
                    long diff = milis2 - milis1;
                    long diffHours = diff / (60 * 60 * 1000);

                    enteroString = "" + diffHours;
                    horas = Integer.parseInt(enteroString);

                    costo = (horas) * costos.getSmsPrecio();
                }

                break;

            case "Traslado":
                costos = cosDao.consultarCostoServicio(servicio, categoria).get(0);
                if (agenda.getAgendaFechaInicio().equals(agenda.getAgendaFechaLlegada())) {
                    cal1.setTime(agenda.getAgendaHoraInicio());
                    cal2.setTime(agenda.getAgendaHoraLlegada());
                    // conseguir la representacion de la fecha en milisegundos

                    long milis1 = cal1.getTimeInMillis();
                    long milis2 = cal2.getTimeInMillis();

                    // calcular la diferencia en horas
                    long diff = milis2 - milis1;
                    long diffHours = diff / (60 * 60 * 1000);

                    enteroString = "" + diffHours;
                    horas = Integer.parseInt(enteroString);

                    costo = (horas) * costos.getSmsPrecio();
                } else {
                    Date fecha1 = agenda.getAgendaFechaInicio();
                    Date fecha2 = agenda.getAgendaFechaLlegada();

                    cal1.set(fecha1.getYear(), fecha1.getMonth(), fecha1.getDay(), agenda.getAgendaFechaInicio().getHours(), agenda.getAgendaFechaInicio().getMinutes(), agenda.getAgendaFechaInicio().getSeconds());
                    cal2.set(fecha2.getYear(), fecha2.getMonth(), fecha2.getDay(), agenda.getAgendaFechaLlegada().getHours(), agenda.getAgendaFechaLlegada().getMinutes(), agenda.getAgendaFechaLlegada().getSeconds());

                    long milis1 = cal1.getTimeInMillis();
                    long milis2 = cal2.getTimeInMillis();

                    // calcular la diferencia en horas
                    long diff = milis2 - milis1;
                    long diffHours = diff / (60 * 60 * 1000);

                    enteroString = "" + diffHours;
                    horas = Integer.parseInt(enteroString);

                    costo = (horas) * costos.getSmsPrecio();
                }

                break;

            case "Plan Dia":
                costos = cosDao.consultarCostoServicio(servicio, categoria).get(0);

                Date fecha1 = agenda.getAgendaFechaInicio();
                Date fecha2 = agenda.getAgendaFechaLlegada();

                cal1.set(fecha1.getYear(), fecha1.getMonth(), fecha1.getDay(), agenda.getAgendaFechaInicio().getHours(), agenda.getAgendaFechaInicio().getMinutes(), agenda.getAgendaFechaInicio().getSeconds());
                cal2.set(fecha2.getYear(), fecha2.getMonth(), fecha2.getDay(), agenda.getAgendaFechaLlegada().getHours(), agenda.getAgendaFechaLlegada().getMinutes(), agenda.getAgendaFechaLlegada().getSeconds());

                long milis1 = cal1.getTimeInMillis();
                long milis2 = cal2.getTimeInMillis();

                // calcular la diferencia en horas
                long diff = milis2 - milis1;
                // calcular la diferencia en dias

                long diffDays = diff / (24 * 60 * 60 * 1000);                

                enteroString = "" + diffDays;
                horas = Integer.parseInt(enteroString);

                costo = ((horas)) * costos.getSmsPrecio();

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
