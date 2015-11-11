/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsReferencia;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpReferenciaDao implements IReferenciaDao {

    private FacesMessage message;

    @Override
    public List<SmsReferencia> mostrarReferencia() {
        Session session = null;
        List<SmsReferencia> referencias = null;
        try {
            session = NewHibernateUtil.getSessionFactory();
            Query query = session.createQuery("from SmsReferencia");
            referencias = (List<SmsReferencia>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return referencias;
    }

    @Override
    public void registrarReferencia(SmsReferencia referencia) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory();
            session.beginTransaction();
            session.save(referencia);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Referencia registrada", "" + referencia.getReferenciaNombre() );
        }catch(HibernateException e){
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        }finally{
            if(session != null){
                session.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public void modificarReferencia(SmsReferencia referencia) {
       Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory();
            session.beginTransaction();
            session.update(referencia);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Referencia modificada", "" + referencia.getReferenciaNombre() );
        }catch(HibernateException e){
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        }finally{
            if(session != null){
                session.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public void eliminarReferencia(SmsReferencia referencia) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory();
            session.beginTransaction();
            session.delete(referencia);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Referencia eliminada", "" + referencia.getReferenciaNombre() );
        }catch(HibernateException e){
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        }finally{
            if(session != null){
                session.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
