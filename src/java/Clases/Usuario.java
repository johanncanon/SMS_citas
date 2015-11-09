/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsUsuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Desarrollo_Planit
 */
public class Usuario {

    private HttpSession httpSession;
    private FacesMessage message;

    public Usuario() {
    }

    //Metodos de la clase
    public String iniciarSesion(SmsUsuario usuario) {
        String valor = null;

        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        List<SmsUsuario> user = usuarioDao.consultarUsuario(usuario);

        if (!user.isEmpty()) {//valida si el usuario existe en la BD
            if (user.get(0).getUsuarioEstadoUsuario() == 1) {//Evalua el estado de la cuenta de usuario, si esta activa o inactiva
                if (user.get(0).getUsuarioLogin().equalsIgnoreCase(usuario.getUsuarioLogin()) && user.get(0).getUsuarioPassword().equalsIgnoreCase(usuario.getUsuarioPassword())) {
                    //evalua el login y el password del usuario para iniciar sesion               
                    httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("Sesion", usuario);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", "Bienvenid@: " + usuario.getUsuarioNombre());
                    valor = "./AdminP/Dashboard-Admin-Principal.xhtml";
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                }
            } else if (user.get(0).getUsuarioEstadoUsuario() == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario inactivo, imposible iniciar sesion", null);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no existente", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return valor;
    }

    public String cerrarSesion() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        String valor = "/pruebas/Login.xhtml";
        return valor;
    }

}
