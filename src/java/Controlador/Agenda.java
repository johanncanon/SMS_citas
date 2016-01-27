/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.ImpAgendaDao;
import Modelo.SmsAgenda;
import Modelo.SmsEmpleado;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Agenda {

    private SmsAgenda agenda;
    private List<SmsAgenda> agendas;

    //VARIABLES DE RESERVACION
    private SmsUsuario usuarioID;
    private List<SmsAgenda> vistaReserva;

    //VARIABLES PARA MOSTRAR FECHAS DE RESERVACION
    private List<Date> listaFechas_Horas;

    private List<Date> listaFechasInicio;
    private List<Date> listaFechasLlegada;
    private List<Date> listaHorasInicio;
    private List<Date> listaHorasLlegada;

    public Agenda() {
        usuarioID = new SmsUsuario();
        vistaReserva = new ArrayList<>();
    }

    public SmsAgenda getAgenda() {
        return agenda;
    }

    public void setAgenda(SmsAgenda agenda) {
        this.agenda = agenda;
    }

    public List<SmsAgenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<SmsAgenda> agendas) {
        this.agendas = agendas;
    }

    //metodos
    public List<SmsAgenda> cargarAgendas() {
        agendas = new ArrayList();
        IAgendaDao agDao = new ImpAgendaDao();
        agendas = agDao.mostrarAgenda();
        return agendas;
    }

    public void registrarAgenda(SmsEmpleado c, SmsVehiculo v, SmsAgenda a) {
        agenda = a;
        SmsVehiculo vehiculo = v;
        SmsEmpleado conductor = c;

        //Asignacion a la agenda
        agenda.setSmsVehiculo(vehiculo);

        if (c.getIdEmpleado() != null) {
            agenda.setSmsEmpleado(conductor);
        }

//Relacion con el dao y registro de la agenda
        IAgendaDao agDao = new ImpAgendaDao();
        agDao.registrarAgenda(agenda);//Se ejecuta la funcion del dao       
    }

    public void modificarAgenda(SmsEmpleado c, SmsVehiculo v, SmsAgenda a) {
        agenda = a;
        SmsVehiculo vehiculo = v;
        SmsEmpleado conductor = c;

        //Asignacion a la agenda
        agenda.setSmsVehiculo(vehiculo);
        agenda.setSmsEmpleado(conductor);

        //Relacion con el dao y modificacion de la agenda
        IAgendaDao agDao = new ImpAgendaDao();
        agDao.modificarAgenda(agenda);//Se ejecuta la funcion del dao
    }

    public void eliminarAgenda(SmsAgenda a) {
        agenda = a;

        //Relacion con el dao y eliminacion de la agenda
        IAgendaDao agDao = new ImpAgendaDao();
        agDao.eliminarAgenda(agenda);//Se ejecuta la funcion del dao
    }

    public List<SmsAgenda> consultarAgenda(SmsAgenda a, SmsVehiculo v, SmsEmpleado e) {
        agendas = new ArrayList();
        agenda = a;
        IAgendaDao agDao = new ImpAgendaDao();

        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");

        //Se formatean las fechas y horas
        String FechaInicio = formatDate.format(agenda.getAgendaFechaInicio());
        String FechaLlegada = formatDate.format(agenda.getAgendaFechaLlegada());
        String HoraInicio = formatTime.format(agenda.getAgendaHoraInicio());
        String HoraLlegada = formatTime.format(agenda.getAgendaHoraLlegada());

        agendas = agDao.consultarAgenda(FechaInicio, FechaLlegada, HoraInicio, HoraLlegada, v, e);
        return agendas;
    }

    //metodo para sacar consulta de reservacion
    //METODO PARA DEVOLVER LA RESERVACION
    public SmsUsuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(SmsUsuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    public List<SmsAgenda> getVistaReserva() {
        return vistaReserva;
    }

    public void setVistaReserva(List<SmsAgenda> vistaReserva) {
        this.vistaReserva = vistaReserva;
    }

    /**
     * *********************************************************************
     */
    public List<Date> getListaFechasInicio() {
        return listaFechasInicio;
    }

    public void setListaFechasInicio(List<Date> listaFechasInicio) {
        this.listaFechasInicio = listaFechasInicio;
    }

    public List<Date> getListaFechasLlegada() {
        return listaFechasLlegada;
    }

    public void setListaFechasLlegada(List<Date> listaFechasLlegada) {
        this.listaFechasLlegada = listaFechasLlegada;
    }

    public List<Date> getListaHorasInicio() {
        return listaHorasInicio;
    }

    public void setListaHorasInicio(List<Date> listaHorasInicio) {
        this.listaHorasInicio = listaHorasInicio;
    }

    public List<Date> getListaHorasLlegada() {
        return listaHorasLlegada;
    }

    public void setListaHorasLlegada(List<Date> listaHorasLlegada) {
        this.listaHorasLlegada = listaHorasLlegada;
    }

    public List<Date> getListaFechas_Horas() {
        return listaFechas_Horas;
    }

    public void setListaFechas_Horas(List<Date> listaFechas_Horas) {
        this.listaFechas_Horas = listaFechas_Horas;
    }

    //METODO PARA MOSTRAR DATOS DE RESERVACION
    public List<SmsAgenda> mostrarDatosReservacionCliente(SmsUsuario u) {
        usuarioID = u;
        vistaReserva = new ArrayList<>();
        IAgendaDao linkDao = new ImpAgendaDao();
        vistaReserva = linkDao.mostrarAgendaReservacionCliente(usuarioID);

        return vistaReserva;
    }
    
    public List<SmsAgenda> mostrarDatosReservacionConductor(SmsEmpleado e) {
        SmsEmpleado empleado = e;
        vistaReserva = new ArrayList<>();
        IAgendaDao linkDao = new ImpAgendaDao();
        vistaReserva = linkDao.mostrarAgendaReservacionConductores(empleado);

        return vistaReserva;
    }
    
    public List<SmsAgenda> mostrarAgendas(){
        vistaReserva = new ArrayList<>();
        IAgendaDao linkDao = new ImpAgendaDao();
        vistaReserva = linkDao.mostrarAgenda();
        return vistaReserva;
    }

    //METODO PARA MOSTRAR UNICAMENTE LAS FECHAS DE LAS RESERVACIONES
    public List<Date> mostrarFechasInicio(SmsUsuario u) {

        usuarioID = u;
        listaFechas_Horas = new ArrayList<>();

        IAgendaDao linkDao = new ImpAgendaDao();

        for (SmsAgenda var_Date : linkDao.mostrarAgendaReservacionCliente(usuarioID)) {

            if (var_Date.getAgendaFechaInicio() != null) {

                listaFechas_Horas.add(var_Date.getAgendaFechaInicio());
            } else if (var_Date.getAgendaFechaLlegada() != null) {
                listaFechas_Horas.add(var_Date.getAgendaFechaLlegada());
            } else if (var_Date.getAgendaHoraInicio() != null) {
                listaFechas_Horas.add(var_Date.getAgendaHoraInicio());
            } else if (var_Date.getAgendaHoraLlegada() != null) {
                listaFechas_Horas.add(var_Date.getAgendaHoraLlegada());
            }

        }
        return listaFechas_Horas;
    }
    
    
    /*//METODO PARA MOSTRAR UNICAMENTE LAS FECHAS DE INICIO DE LAS RESERVACIONES
     public List<Date> mostrarFechasInicio() {

     IAgendaDao linkDao = new ImpAgendaDao();

     for (SmsAgenda var_Date : linkDao.mostrarReservacionHecha(usuarioID)) {

     if (var_Date.getAgendaFechaInicio() != null) {

     listaFechasInicio.add(var_Date.getAgendaFechaInicio());
     }
     }

     return listaFechasInicio;
     }

     //METODO PARA MOSTRAR UNICAMENTE LAS FECHAS DE LLEGADAS DE LAS RESERVACIONES
     public List<Date> mostrarFechasLlegada() {

     IAgendaDao linkDao = new ImpAgendaDao();

     for (SmsAgenda var_Date : linkDao.mostrarReservacionHecha(usuarioID)) {

     if (var_Date.getAgendaFechaLlegada() != null) {

     listaFechasLlegada.add(var_Date.getAgendaFechaLlegada());
     }
     }
     return listaFechasLlegada;
     }

     //METODO PARA MOSTRAR UNICAMENTE LAS HORAS DE INICIO DE LAS RESERVACIONES
     public List<Date> mostrarHorasInicio() {

     IAgendaDao linkDao = new ImpAgendaDao();

     for (SmsAgenda var_Date : linkDao.mostrarReservacionHecha(usuarioID)) {

     if (var_Date.getAgendaHoraInicio() != null) {
     listaHorasInicio.add(var_Date.getAgendaHoraInicio());
     }
     }
     return listaHorasInicio;
     }

     //METODO PARA MOSTRAR UNICAMENTE LAS FECHAS DE INICIO DE LAS RESERVACIONES
     public List<Date> mostrarHoraLlegada() {

     IAgendaDao linkDao = new ImpAgendaDao();

     for (SmsAgenda var_Date : linkDao.mostrarReservacionHecha(usuarioID)) {

     if (var_Date.getAgendaHoraLlegada() != null) {
     listaHorasLlegada.add(var_Date.getAgendaHoraLlegada());
     }
     }
     return listaHorasLlegada;
     }
     */

}
