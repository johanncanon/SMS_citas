/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsUsuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Desarrollo_Planit
 */
public class ImpEmpleadoDao implements IEmpleadoDao {

    @Override
    public List<SmsEmpleado> mostrarEmpleados() {
        Session session = null;
        List<SmsEmpleado> empleados = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsEmpleado as empleado left join fetch empleado.smsUsuario as usuario left join fetch empleado.smsHojavida as hojaVida");
            empleados = (List<SmsEmpleado>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return empleados;
    }

    @Override
    public void registrarEmpleado(SmsEmpleado empleado) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(empleado);
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
    public void modificarEmpleado(SmsEmpleado empleado) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(empleado);
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
    public void eliminarEmpleado(SmsEmpleado empleado) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(empleado);
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
    public List<SmsEmpleado> consultarEmpleado(SmsUsuario usuario) {
        Session session = null;
        List<SmsEmpleado> empleados = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsEmpleado as empleado left join fetch empleado.smsUsuario as usuario left join fetch empleado.smsHojavida as hojaVida where usuario.idUsuario = '" + usuario.getIdUsuario() + "'");
            empleados = (List<SmsEmpleado>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return empleados;
    }

    @Override
    public List<SmsEmpleado> consultarEmpleadosDisponibles(String fechaInicio, String fechaLlegada, String horaInicio, String horaLlegada , String ciudad, String espacioInicio, String espacioLlegada) {
        Session session = null;
        List<SmsEmpleado> empleados = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("select empleado from SmsEmpleado as empleado, SmsAgenda as agenda left join fetch agenda.smsEmpleado as conductor " +
                                              "where " +
                                              "(empleado.smsUsuario.smsCiudad.ciudadNombre = '" + ciudad + "' and empleado.idEmpleado not in (select ag.smsEmpleado.idEmpleado from SmsAgenda as ag)) or " +
                                              "(empleado.smsUsuario.smsCiudad.ciudadNombre = '" + ciudad + "' and " +
                                                "(" +
                                                    "(agenda.agendaFechaInicio = '" + fechaInicio + "' and agenda.agendaFechaLlegada = '" + fechaLlegada + "' and agenda.agendaFechaInicio = agenda.agendaFechaLlegada and empleado.idEmpleado in(select ag.smsEmpleado.idEmpleado from SmsAgenda as ag) and " +
                                                        "(" +
                                                           "('" + espacioLlegada + "' < all (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = agenda.agendaFechaInicio)) or " +
                                                           "('" + espacioInicio + "' > all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = agenda.agendaFechaInicio)) or " +
                                                           "(" +
                                                                    "('" + horaInicio + "' <> all (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = agenda.agendaFechaInicio)) and " +
                                                                    "('" + horaLlegada + "' <> all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = agenda.agendaFechaInicio)) and " +
                                                                    "(not exists (from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaHoraInicio > '" + horaInicio + "' and ag.agendaHoraInicio < '" + horaLlegada + "' or ag.agendaHoraLlegada > '" + horaInicio + "' and ag.agendaHoraLlegada < '" + horaLlegada + "')) and " +
                                                                    "(" +
                                                                        "('" + espacioLlegada + "' < all(select ag.agendaHoraInicio from SmsAgenda as ag where ag.agendaHoraInicio > '" + horaLlegada + "' and ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = agenda.agendaFechaInicio)) and " +
                                                                        "('" + espacioInicio + "' > all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.agendaHoraLlegada < '" + horaInicio + "' and ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = agenda.agendaFechaInicio))" +
                                                                    ")" +
                                                           ")" +
                                                        ")" +
                                                    ")" +
                                                    "or" +
                                                    "("+
                                                        "('" + espacioInicio + "' > all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = '" + fechaInicio + "')) and " +
                                                        "('" + espacioLlegada + "' < all (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaLlegada = '" + fechaLlegada + "')) and " +
                                                        "('" + espacioInicio + "' > all (select ag.agendaHoraLlegada from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaLlegada = '" + fechaInicio + "')) and " +
                                                        "('" + espacioLlegada + "' < all (select ag.agendaHoraInicio from SmsAgenda as ag where ag.smsEmpleado = empleado.idEmpleado and ag.agendaFechaInicio = '" + fechaLlegada + "')) and " +
                                                        "(not exists((select ag from SmsAgenda as ag where ag.agendaFechaInicio > '" + fechaInicio + "' and ag.agendaFechaLlegada < '" + fechaLlegada + "' and ag.smsEmpleado = empleado.idEmpleado) or (select ag from SmsAgenda as ag where ag.agendaFechaInicio = '" + fechaInicio + "' and ag.agendaFechaLlegada = '" + fechaLlegada + "' and ag.smsEmpleado = empleado.idEmpleado)))" +
                                                        "or (not exists(select ag from SmsAgenda as ag where ag.agendaFechaLlegada >= '" + fechaInicio + "'  and ag.smsEmpleado = empleado.idEmpleado))" +
                                                    ")" +
                                                ")" +
                                            ") group by empleado.idEmpleado");
            
             empleados = (List<SmsEmpleado>) query.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return empleados;
    }

    @Override
    public List<SmsEmpleado> consultarEmpleadosCiudad(SmsCiudad ciudad) {
        Session session = null;
        List<SmsEmpleado> empleados = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SmsEmpleado as empleado left join fetch empleado.smsUsuario as usuario where usuario.smsCiudad.ciudadNombre = '" + ciudad.getCiudadNombre() + "'");
            empleados = (List<SmsEmpleado>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return empleados;
    }

}
