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
import Modelo.SmsRol;
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
    protected SmsUsuario usuarioView;
    protected SmsUsuario auxUsuarioView;
    protected List<SmsUsuario> usuarios;

    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Controles de componentes
    boolean habilitado;

    //Relacion con el controlador
    protected Usuario usuario;

    //Contexto
    private FacesMessage message;

    public UsuarioBean() {
        usuarioView = new SmsUsuario();
        auxUsuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        usuario = new Usuario();
        habilitado = true;
    }

    public List<SmsUsuario> getUsuarios() {
        usuarios = usuario.cargarUsuarios();
        return usuarios;
    }

    public void setUsuarios(List<SmsUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public SmsUsuario getAuxUsuarioView() {
        return auxUsuarioView;
    }

    public void setAuxUsuarioView(SmsUsuario auxUsuarioView) {
        this.auxUsuarioView = auxUsuarioView;
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciudadView) {
        this.ciudadView = ciudadView;
    }

    public SmsRol getRolView() {
        return rolView;
    }

    public void setRolView(SmsRol rolView) {
        this.rolView = rolView;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    //Declaracion de metodos
    //Metodos CRUD
    public void registrar() {
        usuario.registrarUsuario(usuarioView, ciudadView);
        auxUsuarioView = new SmsUsuario();
        auxUsuarioView = usuarioView;
        usuarioView = new SmsUsuario();
        habilitado = false;
    }

    public void registrarCuenta() {
        usuario.registrarDatosSesion(auxUsuarioView, rolView);
        auxUsuarioView = new SmsUsuario();
        habilitado = true;
    }

    public void modificar() {
        usuario.modificarUsuario(usuarioView);
        usuarioView = new SmsUsuario();
    }

    public void eliminar() {
        usuario.eliminarUsuario(usuarioView);
        usuarioView = new SmsUsuario();
    }

    //Metodos de funcionalidad
    public String login() {
        String ruta = "/login.xhtml";
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        List<SmsUsuario> user = usuarioDao.consultarUsuario(usuarioView);//Trae de la base de datos toda la informacion de usuario

        if (!user.isEmpty()) {//valida si el usuario existe en la BD
            if (user.get(0).getUsuarioEstadoUsuario() == 1) {//Evalua el estado de la cuenta de usuario, si esta activa o inactiva
                if (user.get(0).getUsuarioLogin().equalsIgnoreCase(usuarioView.getUsuarioLogin()) && user.get(0).getUsuarioPassword().equalsIgnoreCase(usuarioView.getUsuarioPassword())) {
                    ruta = usuario.iniciarSesion(usuarioView);//envia el objeto usuarioBean al metodo iniciarSesion para tomar este objeto como atributo de sesion
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", "Bienvenid@: " + usuarioView.getUsuarioNombre());
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
