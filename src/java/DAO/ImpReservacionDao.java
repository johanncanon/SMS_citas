/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsReservacion;
import Modelo.SmsUsuario;
import Modelo.SmsVistaReserva;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpReservacionDao implements IReservacionDao {

    private FacesMessage message;

    @Override
    public List<SmsReservacion> mostrarReservaciones() {
        Session session = null;
        List<SmsReservacion> reservaciones = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsReservacion");
            reservaciones = (List<SmsReservacion>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return reservaciones;
    }

    @Override
    public void registrarReservacion(SmsReservacion reservacion) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(reservacion);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservacion registrada", "");
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void modificarReservacion(SmsReservacion reservacion) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(reservacion);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservacion modificada", "");
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void eliminarReservacion(SmsReservacion reservacion) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(reservacion);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservacion eliminada", "");
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    //METODO PARA SACAR LAS CIUDADES RESERVADAS POR CLIENTE CONSULTADO
    @Override
    public List<SmsReservacion> mostrarReservacionHecha(SmsUsuario usuarioID) {
        Session session = null;
        List <SmsReservacion> resevacionesHechas = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT  a FROM SmsAgenda a, SmsReservacion r\n" +
                                                "WHERE a.idAgenda = r.smsAgenda.idAgenda AND ( r.smsUsuario.idUsuario = '" +  usuarioID.getIdUsuario() + "')");
            resevacionesHechas = (List<SmsReservacion>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
           return resevacionesHechas;
    }
    
    

}
