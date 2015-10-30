/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsRol;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpRolDao implements IRolDao{

    @Override
    public List<SmsRol> mostrarRoles() {
       
        Session session = null;
        List<SmsRol> roles = null;
        
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsRol");
            roles = (List<SmsRol>) query.list();
            
        }catch(HibernateException e){
            e.getMessage();
        }finally{
            if(session != null){
                session.close();
            }
        }return roles;
    }

    @Override
    public void registrarRol(SmsRol Rol) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(Rol);
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
    public void modificarRol(SmsRol Rol) {
       Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(Rol);
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
    public void eliminarRol(SmsRol Rol) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(Rol);
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
