/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.IReservacionDao;
import DAO.ImpAgendaDao;
import DAO.ImpReservacionDao;
import Modelo.SmsAgenda;
import Modelo.SmsEmpleado;
import Modelo.SmsVehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Agenda {

    private SmsAgenda agenda;
    private List<SmsAgenda> agendas;

    public Agenda() {
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
        agenda.setSmsEmpleado(conductor);

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
        agendas = agDao.consultarAgenda(agenda, v, e);
        return agendas;
    }
    

}
