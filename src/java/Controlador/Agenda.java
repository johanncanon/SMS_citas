/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.ImpAgendaDao;
import Modelo.SmsAgenda;
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

}
