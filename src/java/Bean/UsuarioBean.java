/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.ICiudadDao;
import DAO.IUsuarioDao;
import DAO.ImpCiudadDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Desarrollo_Planit
 */
public class UsuarioBean implements Serializable {

    //Instanciacion de los objetos
    protected SmsUsuario usuario;
    protected List<SmsRol> roles;
    protected List<SmsUsuario> usuarios;
    protected SmsCiudad ciudad;
    

    //instaciacion de objetos de sesion
    private HttpSession httpSession;
    private FacesMessage message;

    public UsuarioBean() {
        usuario = new SmsUsuario();
        ciudad = new SmsCiudad();
    }

    public SmsUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(SmsUsuario usuario) {
        this.usuario = usuario;
    }

    public List<SmsRol> getRoles() {
        return roles;
    }

    public void setRoles(List<SmsRol> roles) {
        this.roles = roles;
    }

    public List<SmsUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<SmsUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public SmsCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(SmsCiudad ciudad) {
        this.ciudad = ciudad;
    }
    
    

    //Declaracion de metodos
    //Metodos CRUD
    public void registrarUsuario() {
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuario.setSmsCiudad(ciudad);
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.registrarUsuario(usuario);
        //usuario = new SmsUsuario();
    }

    public void modificarUsuario() {
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.modificarUsuario(usuario);
        usuario = new SmsUsuario();
    }

    public void eliminarUsuario() {
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.eliminarUsuario(usuario);
        usuario = new SmsUsuario();
    }

    //Metodos de la clase
    public String iniciarSesion() {
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
                        valor = "/pruebas/Principal.xhtml";
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
        usuario = new SmsUsuario();
        String valor = "/pruebas/Login.xhtml";
        return valor;
    }    

    //Definicion de metodos para la asignacion de roles
    public void addRol(SmsRol rol) {
        //Agrega roles a una lista para asignar posteriormente a un usuario
        roles.add(rol);
    }

    public void asignarRol() {

    }

    public void modificarRol() {

    }

    public void eliminarRol() {

    }

}

