/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsUsuario;
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
public class ImpUsuarioDao implements IUsuarioDao {

    private FacesMessage message;

    @Override
    public List<SmsUsuario> mostrarPermisos() {
        Session session = null;
        List<SmsUsuario> usuarios = null;
        try {
            session = NewHibernateUtil.getSessionFactory();
            Query query = session.createQuery("from SmsUsuario");
            usuarios = (List<SmsUsuario>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return usuarios;

    }

    @Override
    public void registrarUsuario(SmsUsuario usuario) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory();
            session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario registrado", "" + usuario.getUsuarioNombre());
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
    public void modificarUsuario(SmsUsuario usuario) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory();
            session.beginTransaction();
            session.update(usuario);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "perfil creado", "" + usuario.getUsuarioLogin());
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
    public void eliminarUsuario(SmsUsuario usuario) {
        Session session = null;
        try {
            session = NewHibernateUtil.getSessionFactory();
            session.beginTransaction();
            session.delete(usuario);
            session.getTransaction().commit();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", "" + usuario.getUsuarioNombre());
        } catch (HibernateException e) {
            e.getMessage();
            session.getTransaction().rollback();
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imposible realizar la operacion", null);
        } finally {
            if (session != null) {
                session.close();
            }
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    @Override
    public List<SmsUsuario> consultarUsuario(SmsUsuario usuario) {
        Session session = null;
        List<SmsUsuario> usuarios = new ArrayList<>();
        try {
            session = NewHibernateUtil.getSessionFactory();
            Query query = session.createQuery("from SmsUsuario as usuario where usuario.usuarioLogin = '" + usuario.getUsuarioLogin() + "'");
            usuarios = (List<SmsUsuario>) query.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }       
        return usuarios;
    }

}
