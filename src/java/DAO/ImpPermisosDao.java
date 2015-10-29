/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.SmsPermisos;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpPermisosDao implements IPermisosDao {

    @Override
    public List<SmsPermisos> mostrarPermisos() {
        Session session = null;
        List<SmsPermisos> permisos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsPermisos");
            permisos = (List<SmsPermisos>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return permisos;

    }

    @Override
    public void registrarPermiso(SmsPermisos permiso) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(permiso);
            session.getTransaction().commit();
        }catch(HibernateException e){
            e.getMessage();
            session.getTransaction().rollback();
        }finally{
            if(session != null){
                session.close();
            }
        }            
    }

    @Override
    public void modificarPermiso(SmsPermisos permiso) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(permiso);
            session.getTransaction().commit();
        }catch(HibernateException e){
            e.getMessage();
            session.getTransaction().rollback();
        }finally{
            if(session != null){
                session.close();
            }
        }  
    }

    @Override
    public void eliminarPermiso(SmsPermisos permiso) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(permiso);
            session.getTransaction().commit();
        }catch(HibernateException e){
            e.getMessage();
            session.getTransaction().rollback();
        }finally{
            if(session != null){
                session.close();
            }
        }  
    }
    
    @Override
    public List<SmsPermisos> consultarPermiso(String permiso) {
        Session session = null;
        List<SmsPermisos> permisos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsPermisos as permiso where permiso.permisosNombre = '" + permiso + "'");
            permisos = (List<SmsPermisos>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return permisos;

    }

}
