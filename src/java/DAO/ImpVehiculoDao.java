/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsReferencia;
import Modelo.SmsVehiculo;
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
public class ImpVehiculoDao implements IVehiculoDao{
    
    private FacesMessage message;

    @Override
    public List<SmsVehiculo> mostrarVehiculo() {
         Session session = null;
        List<SmsVehiculo> vehiculos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsVehiculo");
            vehiculos = (List<SmsVehiculo>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return vehiculos;
    }

    @Override
    public void registrarVehiculo(SmsVehiculo vehiculo) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(vehiculo);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehiculo registrado", "" + vehiculo.getVehPlaca() );
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
    public void modificarVehiculo(SmsVehiculo vehiculo) {
       Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(vehiculo);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehiculo modificado", "" + vehiculo.getVehPlaca() );
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
    public void eliminarVehiculo(SmsVehiculo vehiculo) {
        Session session = null;
        try{
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(vehiculo);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehiculo eliminado", "" + vehiculo.getVehPlaca() );
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
