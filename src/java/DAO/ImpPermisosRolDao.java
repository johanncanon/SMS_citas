/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.SmsPermisosRol;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpPermisosRolDao implements IPermisosRolDao {

    @Override
    public List<SmsPermisosRol> mostrarPermisosRol() {
        Session session = null;
        List<SmsPermisosRol> permisosRol = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsPermisosRol");
            permisosRol = (List<SmsPermisosRol>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return permisosRol;
    }

    @Override
    public void registrarPermisosRol(SmsPermisosRol permisosRol) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(permisosRol);
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
    public void modificarPermisosRol(SmsPermisosRol permisosRol) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(permisosRol);
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
    public void eliminarPermisosRol(SmsPermisosRol permisosRol) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(permisosRol);
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

}
