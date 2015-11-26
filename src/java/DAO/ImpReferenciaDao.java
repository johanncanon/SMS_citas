/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsReferencia;
import java.util.ArrayList;
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
    private List<SmsReferencia> ArrayList;

    @Override
    public List<SmsReferencia> mostrarReferencias() {
        Session session = null;
        List<SmsReferencia> referencias = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsReferencia as referencia left join fetch referencia.smsMarca");
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
            session = NewHibernateUtil.getSessionFactory().openSession();
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
            session = NewHibernateUtil.getSessionFactory().openSession();
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
            session = NewHibernateUtil.getSessionFactory().openSession();
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

    /**
     * @param referencia*
     * @return ************************************************************************************/
    @Override
    public List<SmsReferencia> consultarReferencias(SmsReferencia referencia) {
        Session session = null;
        List<SmsReferencia> referencias = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsReferencia as referencia where referencia.referenciaNombre='" + referencia.getReferenciaNombre() + "'");
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
    public List<SmsReferencia> filtrarReferencias(String dato) {
Session session = null;
        List<SmsReferencia> referencias = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsReferencia as referencia left join fetch referencia.smsMarca where referencia.referenciaNombre LIKE '%" + dato + "%'");
            referencias = (List<SmsReferencia>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return referencias;    }
    
    
}
