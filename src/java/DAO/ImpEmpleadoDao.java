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
            Query query = session.createQuery("select empleado from SmsEmpleado as empleado " +
                                              "where " +
                                              "(empleado.smsUsuario.smsCiudad.ciudadNombre = '" + ciudad + "' and empleado.idEmpleado not in (select rs.smsEmpleado.idEmpleado from SmsReservacion as rs)) or " +
                                              "(empleado.smsUsuario.smsCiudad.ciudadNombre = '" + ciudad + "' and " +
                                                "(" +
                                                    "('" + fechaInicio + "' = '" + fechaLlegada + "' and empleado.idEmpleado in(select rs.smsEmpleado.idEmpleado from SmsReservacion as rs) and " +
                                                        "(" +
                                                           "('" + espacioLlegada + "' < all (select rs.reservacionHoraInicio from SmsReservacion as rs where rs.smsEmpleado.idEmpleado = empleado.idEmpleado and rs.reservacionFechaInicio = '" + fechaInicio + "')) or " +
                                                           "('" + espacioInicio + "' > all (select rs.reservacionHoraLlegada from SmsReservacion as rs where rs.smsEmpleado.idEmpleado = empleado.idEmpleado and rs.reservacionFechaInicio = '" + fechaInicio + "')) or " +
                                                           "(" +
                                                                   "(not exists (from SmsReservacion as rs where rs.smsEmpleado.idEmpleado = empleado.idEmpleado and ((rs.reservacionHoraInicio >= '"+horaInicio+"' and (rs.reservacionHoraLlegada > '"+horaLlegada+"' or rs.reservacionHoraLlegada < '"+horaLlegada+"')) or (rs.reservacionHoraLlegada >= '"+horaInicio+"')))) and " +
                                                                    "(" +
                                                                        "('" + espacioLlegada + "' < all(select rs.reservacionHoraInicio from SmsReservacion as rs where rs.reservacionHoraInicio > '" + horaLlegada + "' and rs.smsEmpleado.idEmpleado = empleado.idEmpleado and rs.reservacionFechaInicio = '" + fechaInicio + "')) and " +
                                                                        "('" + espacioInicio + "' > all (select rs.reservacionHoraLlegada from SmsReservacion as rs where rs.reservacionHoraLlegada < '" + horaInicio + "' and rs.smsEmpleado.idEmpleado = empleado.idEmpleado and rs.reservacionFechaLlegada = '" + fechaInicio + "'))" +
                                                                    ")" +
                                                           ")" +
                                                        ")" +
                                                    ")" +
                                                    "or" +
                                                    "('" + fechaInicio + "' <> '" + fechaLlegada + "' and empleado.idEmpleado in(select rs.smsEmpleado.idEmpleado from SmsReservacion as rs) and " +
                                                        "('" + espacioInicio + "' > all (select rs.reservacionHoraLlegada from SmsReservacion as rs where rs.smsEmpleado = empleado.idEmpleado and rs.reservacionFechaInicio = '" + fechaInicio + "')) and " +
                                                        "('" + espacioLlegada + "' < all (select rs.reservacionHoraInicio from SmsReservacion as rs where rs.smsEmpleado = empleado.idEmpleado and rs.reservacionFechaLlegada = '" + fechaLlegada + "')) and " +
                                                        "('" + espacioInicio + "' > all (select rs.reservacionHoraLlegada from SmsReservacion as rs where rs.smsEmpleado = empleado.idEmpleado and rs.reservacionFechaLlegada = '" + fechaInicio + "')) and " +
                                                        "('" + espacioLlegada + "' < all (select rs.reservacionHoraInicio from SmsReservacion as rs where rs.smsEmpleado = empleado.idEmpleado and rs.reservacionFechaInicio = '" + fechaLlegada + "')) and " +
                                                        "(not exists((select rs from SmsReservacion as rs where rs.reservacionFechaInicio > '" + fechaInicio + "' and rs.reservacionFechaLlegada < '" + fechaLlegada + "' and rs.smsEmpleado.idEmpleado = empleado.idEmpleado) or (select rs from SmsReservacion as rs where rs.reservacionFechaInicio = '" + fechaInicio + "' and rs.reservacionFechaLlegada = '" + fechaLlegada + "' and rs.smsEmpleado.idEmpleado = empleado.idEmpleado))) " +
                                                        "or (not exists(select rs from SmsReservacion as rs where rs.reservacionFechaLlegada >= '" + fechaInicio + "'  and rs.smsEmpleado.idEmpleado = empleado.idEmpleado))" +
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
