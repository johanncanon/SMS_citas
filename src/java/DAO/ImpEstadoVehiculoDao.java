/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsEstadovehiculo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpEstadoVehiculoDao implements IEstadoVehiculoDao{

    @Override
    public List<SmsEstadovehiculo> mostrarEstadoVehiculo() {
        Session session = null;
        List<SmsEstadovehiculo> EstadoVehiculos = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsCiudad");
            EstadoVehiculos = (List<SmsEstadovehiculo>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return EstadoVehiculos;        
    }

    @Override
    public void registrarEstadoVehiculo(SmsEstadovehiculo estado) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(estado);
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
    public void modificarEstadoVehiculo(SmsEstadovehiculo estado) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(estado);
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
    public void eliminarEstadoVehiculo(SmsEstadovehiculo estado) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(estado);
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
