/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsAgenda;
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

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsAgenda as agenda left join fetch agenda.smsVehiculo as vehiculo left join fetch agenda.smsEmpleado as empleado where vehiculo = '" + agenda.getSmsVehiculo().getIdVehiculo() + "' and empleado = '" + agenda.getSmsEmpleado().getIdEmpleado() + "' and "
                    + "agenda.agendaFechaInicio = '"+ agenda.getAgendaFechaInicio() +"' and agenda.agendaFechaLlegada = '"+ agenda.getAgendaFechaLlegada() +"' and agenda.agendaHoraInicio = '"+agenda.getAgendaHoraInicio()+"' and "
                    + "agenda.agendaHoraLlegada = '"+ agenda.getAgendaFechaLlegada() +"'");
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
