/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsAgenda;
import Modelo.SmsEmpleado;
import Modelo.SmsUsuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpAgendaDao implements IAgendaDao {

    @Override
    public List<SmsAgenda> mostrarAgenda() {
        Session session = null;
        List<SmsAgenda> agenda = new ArrayList<>();

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsAgenda");
            agenda = (List<SmsAgenda>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return agenda;
    }

    @Override
    public void registrarAgenda(SmsAgenda agenda) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(agenda);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void modificarAgenda(SmsAgenda agenda) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(agenda);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void eliminarAgenda(SmsAgenda agenda) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(agenda);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<SmsAgenda> consultarAgenda(SmsAgenda agenda) {
        Session session = null;
        List<SmsAgenda> agendas = new ArrayList<>();
        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");

        //Se formatean las fechas y horas
        String FechaInicio = formatDate.format(agenda.getAgendaFechaInicio());
        String FechaLlegada = formatDate.format(agenda.getAgendaFechaLlegada());
        String HoraInicio = formatTime.format(agenda.getAgendaHoraInicio());
        String HoraLlegada = formatTime.format(agenda.getAgendaHoraLlegada());

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsAgenda as agenda left join fetch agenda.smsVehiculo as vehiculo left join fetch agenda.smsEmpleado as empleado where vehiculo.idVehiculo = '" + agenda.getSmsVehiculo().getIdVehiculo() + "' and empleado.idEmpleado = '" + agenda.getSmsEmpleado().getIdEmpleado() + "' and "
                    + "agenda.agendaFechaInicio = '" + FechaInicio + "' and agenda.agendaFechaLlegada = '" + FechaLlegada + "' and agenda.agendaHoraInicio = '" + HoraInicio + "' and "
                    + "agenda.agendaHoraLlegada = '" + HoraLlegada + "'");
            agendas = (List<SmsAgenda>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return agendas;
    }

    //QUERY PARA SACAR DATOS DE RESERVA HECHA
    @Override
    public List<SmsAgenda> mostrarAgendaReservacionCliente(SmsUsuario usuarioID) {
        Session session = null;
        List<SmsAgenda> resevacionesHechas = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT a FROM SmsAgenda a left join fetch a.smsVehiculo left join fetch a.smsEmpleado WHERE a.idAgenda = (select  reservacion.smsAgenda.idAgenda from SmsReservacion as reservacion where reservacion.smsUsuario.idUsuario = '" + usuarioID.getIdUsuario() + "' and reservacion.smsAgenda.idAgenda = a.idAgenda)");
            resevacionesHechas = (List<SmsAgenda>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resevacionesHechas;
    }

    @Override
    public List<SmsAgenda> mostrarAgendaReservacionConductores(SmsEmpleado conductor) {
        Session session = null;
        List<SmsAgenda> resevacionesHechas = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT a FROM SmsAgenda a left join fetch a.smsVehiculo left join fetch a.smsEmpleado as empleado WHERE empleado.idEmpleado = '" + conductor.getIdEmpleado() + "'");
            resevacionesHechas = (List<SmsAgenda>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resevacionesHechas;
    }

    @Override
    public List<SmsAgenda> consultarAgendaId(SmsAgenda agenda) {
        Session session = null;
        List<SmsAgenda> agendas = new ArrayList<>();

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsAgenda as agenda left join fetch agenda.smsVehiculo as vehiculo left join fetch agenda.smsEmpleado as empleado where agenda.idAgenda = '" + agenda.getIdAgenda() + "'");
            agendas = (List<SmsAgenda>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return agendas;
    }

    @Override
    public List<SmsAgenda> consultarAgendaSinEmpleado(SmsAgenda agenda) {
        Session session = null;
        List<SmsAgenda> agendas = new ArrayList<>();
        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");

        //Se formatean las fechas y horas
        String FechaInicio = formatDate.format(agenda.getAgendaFechaInicio());
        String FechaLlegada = formatDate.format(agenda.getAgendaFechaLlegada());
        String HoraInicio = formatTime.format(agenda.getAgendaHoraInicio());
        String HoraLlegada = formatTime.format(agenda.getAgendaHoraLlegada());

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsAgenda as agenda left join fetch agenda.smsVehiculo as vehiculo where vehiculo.idVehiculo = '" + agenda.getSmsVehiculo().getIdVehiculo() + "' and "
                    + "agenda.agendaFechaInicio = '" + FechaInicio + "' and agenda.agendaFechaLlegada = '" + FechaLlegada + "' and agenda.agendaHoraInicio = '" + HoraInicio + "' and "
                    + "agenda.agendaHoraLlegada = '" + HoraLlegada + "'");
            agendas = (List<SmsAgenda>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return agendas;
    }

}
