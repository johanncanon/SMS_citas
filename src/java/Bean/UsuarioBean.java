/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Usuario;
import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Desarrollo_Planit
 */
//Bean para usuario
public class UsuarioBean implements Serializable {

    //Instanciacion de los objetos    
    protected SmsUsuario usuarioBean;
    protected SmsUsuario auxUsuarioBean;
    protected List<SmsUsuario> usuarios;   
    protected SmsCiudad ciudad;
    protected List<String> roles;

    //Relacion con el controlador
    protected Usuario usuario;

    //Contexto
    private FacesMessage message;

    public UsuarioBean() {
        usuarioBean = new SmsUsuario();
        auxUsuarioBean = new SmsUsuario();
        ciudad = new SmsCiudad();
        usuario = new Usuario();
    }

    public SmsUsuario getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(SmsUsuario usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List<SmsUsuario> getUsuarios() {
        usuarios = usuario.cargarUsuarios();
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

    public SmsUsuario getAuxUsuarioBean() {
        return auxUsuarioBean;
    }

    public void setAuxUsuarioBean(SmsUsuario auxUsuarioBean) {
        this.auxUsuarioBean = auxUsuarioBean;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Declaracion de metodos
    //Metodos CRUD
    public void registrar() {
        usuario.registrarUsuario(usuarioBean, ciudad);
        auxUsuarioBean = new SmsUsuario();
        auxUsuarioBean = usuarioBean;
        usuarioBean = new SmsUsuario();
    }
    
    public void registrarCuenta(){
        usuario.registrarDatosSesion(auxUsuarioBean);
        auxUsuarioBean = new SmsUsuario();
    }

    public void modificar() {
        usuario.modificarUsuario(usuarioBean);
        usuarioBean = new SmsUsuario();
    }

    public void eliminar() {
        usuario.eliminarUsuario(usuarioBean);
        usuarioBean = new SmsUsuario();
    }

    //Metodos de funcionalidad
    public String login() {
        String ruta = "/login.xhtml";
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        List<SmsUsuario> user = usuarioDao.consultarUsuario(usuarioBean);//Trae de la base de datos toda la informacion de usuario

        if (!user.isEmpty()) {//valida si el usuario existe en la BD
            if (user.get(0).getUsuarioEstadoUsuario() == 1) {//Evalua el estado de la cuenta de usuario, si esta activa o inactiva
                if (user.get(0).getUsuarioLogin().equalsIgnoreCase(usuarioBean.getUsuarioLogin()) && user.get(0).getUsuarioPassword().equalsIgnoreCase(usuarioBean.getUsuarioPassword())) {
                    ruta = usuario.iniciarSesion(usuarioBean);//envia el objeto usuarioBean al metodo iniciarSesion para tomar este objeto como atributo de sesion
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", "Bienvenid@: " + usuarioBean.getUsuarioNombre());
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
        return ruta;
    }

    public String logout() {
        String ruta = "/login.xhtml";
        usuario.cerrarSesion();
        return ruta;
    }
     
}
