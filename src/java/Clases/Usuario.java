/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsRol;
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

    public Usuario() {
    }

    //Metodos de la clase   
    public String iniciarSesion(SmsUsuario usuario) {

        String ruta = "";        
        Rol rol = new Rol();
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.setAttribute("Sesion", usuario);        
        ruta = rol.validarRol(usuario);   
        
        return ruta;
    }

    public String cerrarSesion() {
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        String valor = "/login.xhtml";
        return valor;
    }

}
