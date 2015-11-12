/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsReservacion;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpReservacionDao implements IReservacionDao{

    @Override
    public List<SmsReservacion> mostrarReservaciones() {
       Session session = null;
        List<SmsReservacion> reservaciones = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsCiudad");
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
    public void modificarReservacion(SmsReservacion reservacion) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(reservacion);
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
    public void eliminarReservacion(SmsReservacion reservacion) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(reservacion);
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
    
}
