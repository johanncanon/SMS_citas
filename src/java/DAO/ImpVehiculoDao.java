/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsAgenda;
import Modelo.SmsCiudad;
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
public class ImpVehiculoDao implements IVehiculoDao {

    private FacesMessage message;

    @Override
    public List<SmsVehiculo> mostrarVehiculo() {
        Session session = null;
        List<SmsVehiculo> vehiculos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsVehiculo as vehiculo left join fetch vehiculo.smsCategoria as categoria left join fetch vehiculo.smsCiudad as ciudad "
                    + "left join fetch vehiculo.smsProveedor as proveedor left join fetch proveedor.smsUsuario as usuario left join fetch vehiculo.smsReferencia as referencia");
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
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(vehiculo);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehiculo registrado", "" + vehiculo.getVehPlaca());
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public void modificarVehiculo(SmsVehiculo vehiculo) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(vehiculo);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehiculo modificado", "" + vehiculo.getVehPlaca());
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public void eliminarVehiculo(SmsVehiculo vehiculo) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(vehiculo);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vehiculo eliminado", "" + vehiculo.getVehPlaca());
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public List<SmsVehiculo> consultarVehiculo(SmsVehiculo vehiculo) {
        Session session = null;
        List<SmsVehiculo> vehiculos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsVehiculo as vehiculo left join fetch vehiculo.smsCategoria as categoria left join fetch vehiculo.smsCiudad as ciudad"
                    + " left join fetch vehiculo.smsProveedor as proveedor left join fetch vehiculo.smsReferencia as referencia where vehiculo.vehPlaca = '" + vehiculo.getVehPlaca() + "'");
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
    public List<SmsVehiculo> consultarVehiculosDisponibles(SmsAgenda agenda, SmsCiudad ciudad) {
        Session session = null;
        List<SmsVehiculo> vehiculos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("select vehiculo from SmsVehiculo as vehiculo, SmsAgenda as agenda left join fetch agenda.smsVehiculo as vehiculoAgenda "
                    + "where "
                    + "(vehiculo.smsCiudad.ciudadNombre = '" + ciudad.getCiudadNombre() + "' and vehiculo.idVehiculo not in (select ag.smsVehiculo.idVehiculo from SmsAgenda as ag)) or "
                    + "(vehiculo.smsCiudad.ciudadNombre = '" + ciudad.getCiudadNombre() + "' and "
                            + "( "
                                + "(agenda.agendaFechaInicio = '" + agenda.getAgendaFechaInicio() + "' and agenda.agendaFechaLlegada = '" + agenda.getAgendaFechaLlegada() + "' and agenda.agendaFechaInicio = agenda.agendaFechaLlegada and vehiculo.idVehiculo in (select ag.smsVehiculo.idVehiculo from SmsAgenda as ag) and "
                                    + "( "
                                        + "('" + agenda.getAgendaHoraLlegada() + "' < all (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsVehiculo.idVehiculo = vehiculo.idVehiculo)) or "
                                        + "('" + agenda.getAgendaHoraInicio() + "' > all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsVehiculo.idVehiculo = vehiculo.idVehiculo)) or "
                                        + "((agenda.agendaHoraInicio <> '" + agenda.getAgendaHoraInicio() + "' and agenda.agendaHoraLlegada <> '" + agenda.getAgendaHoraLlegada() + "') and ( '" + agenda.getAgendaHoraInicio() + "' > any (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsVehiculo = vehiculo.idVehiculo)) and ('" + agenda.getAgendaHoraLlegada() + "'  < any (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsVehiculo = vehiculo.idVehiculo))) "
                                    + ") "
                                + ") "
                                + "or "
                                + "( "                                    
                                        + "('" + agenda.getAgendaHoraInicio() + "' > all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsVehiculo.idVehiculo = vehiculo.idVehiculo and ag.agendaFechaInicio = '" + agenda.getAgendaFechaInicio() + "')) and "
                                        + "('" + agenda.getAgendaHoraLlegada() + "' < all (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsVehiculo.idVehiculo = vehiculo.idVehiculo and ag.agendaFechaLlegada = '" + agenda.getAgendaFechaLlegada() + "')) and "
                                        + "(not exists((select ag from SmsAgenda as ag where ag.agendaFechaInicio > '" + agenda.getAgendaFechaInicio() + "' and ag.agendaFechaLlegada < '" + agenda.getAgendaFechaLlegada() + "' and ag.smsVehiculo.idVehiculo = vehiculo.idVehiculo) or (select ag from SmsAgenda as ag where ag.agendaFechaInicio = '" + agenda.getAgendaFechaInicio() + "' and ag.agendaFechaLlegada = '" + agenda.getAgendaFechaLlegada() + "' and ag.smsVehiculo.idVehiculo = vehiculo.idVehiculo))) "
                                 + ") "
                            + ") "
                    + ")  group by vehiculo.idVehiculo");
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
    public List<SmsVehiculo> consultarVehiculosCiudad(SmsCiudad ciudad) {
        Session session = null;
        List<SmsVehiculo> vehiculos = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsVehiculo as vehiculo left join fetch vehiculo.smsCiudad as ciudad where ciudad.ciudadNombre = '" + ciudad.getCiudadNombre() + "'");
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

}
