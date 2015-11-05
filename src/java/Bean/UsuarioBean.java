/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.ICiudadDao;
import DAO.IRolDao;
import DAO.IUsuarioDao;
import DAO.ImpCiudadDao;
import DAO.ImpRolDao;
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
    protected SmsUsuario auxUsuario;   
    protected List<SmsUsuario> usuarios;
    
    //Relaciones con otras clases
    protected SmsCiudad ciudad;//asociacion
    protected List<String> roles; //agregacion
    
    //instaciacion de objetos de contexto
    private HttpSession httpSession;
    private FacesMessage message;

    public UsuarioBean() {
        usuario = new SmsUsuario();
        ciudad = new SmsCiudad();
        auxUsuario = new SmsUsuario();

    }

    public SmsUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(SmsUsuario usuario) {
        this.usuario = usuario;
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

    public SmsUsuario getAuxUsuario() {
        return auxUsuario;
    }

    public void setAuxUsuario(SmsUsuario auxUsuario) {
        this.auxUsuario = auxUsuario;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    

    //Declaracion de metodos
    //Metodos CRUD
    public void registrarUsuario() {        
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuario.setSmsCiudad(ciudad);         
        
        
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.registrarUsuario(usuario);
        
        auxUsuario = new SmsUsuario();
        auxUsuario= usuario;
        usuario = new SmsUsuario();
        
    }

    public void modificarUsuario() {
        auxUsuario.setUsuarioEstadoUsuario(1);
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.modificarUsuario(auxUsuario);
        auxUsuario = new SmsUsuario();
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
        usuario = new SmsUsuario();
        String valor = "/pruebas/Login.xhtml";
        return valor;
    }

    //Definicion de metodos para la asignacion de roles    

    public void asignarRol() {
        SmsRol rol = new SmsRol();
        IRolDao rolDao = new ImpRolDao();
        
        for (int i = 0; i < roles.size(); i++) { //Recorre el array de nombres de permisos
            rol = rolDao.consultarRol(roles.get(i)).get(0);
            //Realiza la consulta en la base de datos y guarda el objeto resultante en el objeto permiso
            auxUsuario.getSmsRols().add(rol);//agrega el permiso al rol           
        }
        modificarUsuario();
        roles = new ArrayList<>();
        auxUsuario = new SmsUsuario();
    }

    public void modificarRol() {
        
    }

    public void eliminarRol() {

    }
    
    public void consultarRol(){
    
    }

}
