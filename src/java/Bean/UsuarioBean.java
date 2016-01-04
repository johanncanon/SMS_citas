/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.MD5;
import Controlador.Upload;
import Controlador.Usuario;
import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    protected SmsUsuario modUsuarioView;
    protected List<SmsUsuario> usuariosListView;

    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Controles de componentes
    boolean habilitado;

    //Relacion con el controlador
    protected Usuario usuarioController;
    protected Upload fileController;

    //Contexto
    private FacesMessage message;

    //Sesion
    protected SmsUsuario Usuario;

    //Variables
    private String buscar;
    private Boolean habilitarEditarSesion;   
    private String pass;
    private String login;

    public UsuarioBean() {
        usuarioView = new SmsUsuario();
        modUsuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        usuarioController = new Usuario();
        habilitado = true;
        Usuario = new SmsUsuario();

        buscar = null;
        habilitarEditarSesion = false;       
    }

    @PostConstruct
    public void init() {
        usuariosListView = usuarioController.consultarAdministradores();
    }

    //Getters & Setters
    public List<SmsUsuario> getUsuariosListView() {
        return usuariosListView;
    }

    public void setUsuariosListView(List<SmsUsuario> usuariosListView) {
        this.usuariosListView = usuariosListView;
    }

    public Usuario getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(Usuario usuarioController) {
        this.usuarioController = usuarioController;
    }

    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
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

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public Upload getFileController() {
        return fileController;
    }

    public void setFileController(Upload fileController) {
        this.fileController = fileController;
    }

    public SmsUsuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(SmsUsuario Usuario) {
        this.Usuario = Usuario;
    }

    public SmsUsuario getModUsuarioView() {
        return modUsuarioView;
    }

    public void setModUsuarioView(SmsUsuario modUsuarioView) {
        this.modUsuarioView = modUsuarioView;
    }

    public Boolean getHabilitarEditarSesion() {
        return habilitarEditarSesion;
    }

    public void setHabilitarEditarSesion(Boolean habilitarEditarSesion) {
        this.habilitarEditarSesion = habilitarEditarSesion;
    }

    //Declaracion de metodos
    //Metodos CRUD
    public void registrar() {

        //asignamos al usuario la imagen de perfil default
        usuarioView.setUsuarioFotoRuta(fileController.getPathDefaultUsuario());

        //registramos el usuario y recargamos la lista de clientes
        usuarioController.registrarUsuario(usuarioView, ciudadView, rolView);
        usuariosListView = usuarioController.consultarAdministradores();

        //limpiamos objetos
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modUsuarioView = new SmsUsuario();
    }
    

    public String modificar() {
        MD5 md = new MD5();

        if (habilitarEditarSesion) { // en caso de modificar las contraseñas estas se encriptan de nuevo
            modUsuarioView.setUsuarioPassword(md.getMD5(modUsuarioView.getUsuarioPassword()));
            modUsuarioView.setUsuarioRememberToken(md.getMD5(modUsuarioView.getUsuarioRememberToken()));
        }

        usuarioController.modificarUsuario(modUsuarioView, ciudadView, rolView);
        usuariosListView = usuarioController.consultarAdministradores();

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modUsuarioView = new SmsUsuario();
        habilitarEditarSesion = false;

        String ruta = "RAdminPUsuario";
        return ruta;
    }

    public void eliminar() {
        usuarioController.eliminarUsuario(usuarioView);
        usuariosListView = usuarioController.consultarAdministradores();

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modUsuarioView = new SmsUsuario();
    }

    public void filtrar() {
        usuariosListView = new ArrayList<>();
        if (buscar == null) {
            usuariosListView = usuarioController.consultarAdministradores();
        } else {
            usuariosListView = usuarioController.filtrarAdministrador(buscar);
        }
    }

    //Metodos propios
    public String irModificarAdministrador() {
        ciudadView = modUsuarioView.getSmsCiudad();
        rolView = modUsuarioView.getSmsRol();

        String ruta = "AdminPEUsuario";
        return ruta;
    }

    public String regresar() {
        modUsuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        habilitarEditarSesion = false;
        String ruta = "AdminPUsuario";
        return ruta;
    }

    public void habilitarEdicion() {
        habilitarEditarSesion = true;
        pass = modUsuarioView.getUsuarioPassword();
        modUsuarioView.setUsuarioPassword(null);
        modUsuarioView.setUsuarioRememberToken(null);
    }

    public void deshabilitarEdicion() {
        habilitarEditarSesion = false;
        modUsuarioView.setUsuarioPassword(pass);
        modUsuarioView.setUsuarioRememberToken(pass);
    }
   
//Metodos para iniciar Sesion

    public String login() {
        String ruta = "/login.xhtml";
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        MD5 md = new MD5();
        Usuario.setUsuarioPassword(md.getMD5(Usuario.getUsuarioPassword()));
        List<SmsUsuario> user = usuarioDao.consultarDatosSesionUsuario(Usuario);//Trae de la base de datos toda la informacion de usuario

        if (!user.isEmpty()) {//valida si el usuario existe en la BD
            if (user.get(0).getUsuarioEstadoUsuario() == 1) {//Evalua el estado de la cuenta de usuario, si esta activa o inactiva
                if (user.get(0).getUsuarioLogin().equalsIgnoreCase(Usuario.getUsuarioLogin()) && user.get(0).getUsuarioPassword().equalsIgnoreCase(Usuario.getUsuarioPassword())) {
                    ruta = usuarioController.iniciarSesion(user.get(0));//envia el objeto usuarioBean al metodo iniciarSesion para tomar este objeto como atributo de sesion
                    Usuario = usuarioController.obtenerSesion();
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", "Bienvenid@: " + Usuario.getUsuarioNombre());
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                    Usuario = new SmsUsuario();
                }
            } else if (user.get(0).getUsuarioEstadoUsuario() == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario inactivo, imposible iniciar sesion", null);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no existente", null);
            Usuario = new SmsUsuario();
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return ruta;
    }

    public String logout() {
        String ruta = "Login";
        usuarioController.cerrarSesion();
        return ruta;
    }

}
