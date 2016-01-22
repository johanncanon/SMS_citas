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
        
        if(c.getIdEmpleado() != null){
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
    
    
    
    public List<SmsAgenda> mostrarDatosReservacion(SmsUsuario u) {
        usuarioID = u;
        vistaReserva = new ArrayList<>();
        IAgendaDao linkDao = new ImpAgendaDao();
        vistaReserva = linkDao.mostrarReservacionHecha(usuarioID);

        return vistaReserva;
    }

}
