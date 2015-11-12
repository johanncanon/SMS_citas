/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsProveedor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpProveedorDao implements IProveedorDao{

    @Override
    public List<SmsProveedor> mostrarProveedores() {
        Session session = null;
        List<SmsProveedor> Proveedores = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsHojaVida");
            Proveedores = (List<SmsProveedor>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Proveedores;
    }

    @Override
    public void registrarProveedor(SmsProveedor proveedor) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(proveedor);
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
    public void modificarProveedor(SmsProveedor proveedor) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(proveedor);
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
    public void eliminarProveedor(SmsProveedor proveedor) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(proveedor);
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
