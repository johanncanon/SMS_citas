/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Upload;
import Controlador.Rol;
import Controlador.Usuario;
import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Desarrollo_Planit
 */
//Bean para usuario
public class UsuarioBean implements Serializable {

    //Instanciacion de los objetos    
    protected SmsUsuario usuarioView;
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
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    private Boolean habilitarSubir;
    private String subirArchivo;
    private String estadoArchivo;
    private UploadedFile archivo;

    public UsuarioBean(){
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        usuarioController = new Usuario();
        habilitado = true;

        buscar = null;
        estado = 0;
        nombre = "Registrar Usuario";
        habilitarSubir = false;
        subirArchivo = "Subir fotografia";
        estadoArchivo = "Foto sin subir";
    }

    @PostConstruct
    public void init() {
        usuariosListView = usuarioController.cargarUsuarios();
        
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public Boolean getHabilitarSubir() {
        return habilitarSubir;
    }

    public void setHabilitarSubir(Boolean habilitarSubir) {
        this.habilitarSubir = habilitarSubir;
    }

    public String getSubirArchivo() {
        return subirArchivo;
    }

    public void setSubirArchivo(String subirArchivo) {
        this.subirArchivo = subirArchivo;
    }

    public String getEstadoArchivo() {
        return estadoArchivo;
    }

    public void setEstadoArchivo(String estadoArchivo) {
        this.estadoArchivo = estadoArchivo;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
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
    
    

    //Declaracion de metodos
    //Metodos CRUD
    public void registrar() {
        usuarioController.registrarUsuario(usuarioView, ciudadView, rolView);
        usuariosListView = usuarioController.cargarUsuarios();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;
        
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }

    public void modificar() {
        usuarioController.modificarUsuario(usuarioView, ciudadView, rolView);
        usuariosListView = usuarioController.cargarUsuarios();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }

    public void eliminar() {
        usuarioController.eliminarUsuario(usuarioView);        
        usuariosListView = usuarioController.cargarUsuarios();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;
        
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        Rol rolController = new Rol();
        List<SmsRol> roles = rolController.cargarRoles();
        boolean rol;
        if (estado == 1) {//MODIFICACION
            nombre = "Modificar Usuario";
            usuarioView = usuarioController.consultarUsuario(usuarioView).get(0);//Recargamos el usuario para tener acceso a los datos de ciudad
            ciudadView = usuarioView.getSmsCiudad();

            for (int b = 0; b < roles.size(); b++) {
                rol = rolController.validarRol(usuarioView, roles.get(b));
                if (rol) {
                    rolView = roles.get(b);
                }
            }

            if (usuarioView.getUsuarioFotoNombre() != null && usuarioView.getUsuarioFotoRuta() != null) {
                subirArchivo = "Modificar Fotografia";
                habilitarSubir = false;
            } else {
                subirArchivo = "Subir Fotografia";
                estadoArchivo = "Foto sin subir";
                habilitarSubir = false;
            }

        } else if (estado == 2) {//ELIMINACION
            nombre = "Eliminar Proveedor";
            usuarioView = usuarioController.consultarUsuario(usuarioView).get(0);
            ciudadView = usuarioView.getSmsCiudad();

            subirArchivo = "Subir Fotografia";
            estadoArchivo = "Foto sin subir";
            habilitarSubir = true;
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Usuario";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Usuario";
        }
    }

    //Metodo para subir fotografias
    public void upload() throws IOException {//Metodo para subir fotografia del usuario
        FacesMessage message = new FacesMessage();
        if (null != getArchivo()) {
            fileController.UploadFile(IOUtils.toByteArray(getArchivo().getInputstream()), getArchivo().getFileName());
            usuarioView.setUsuarioFotoNombre(getArchivo().getFileName());
            usuarioView.setUsuarioFotoRuta(fileController.getFilePath());
            habilitarSubir = true;
            if (estado == 0) {//Registro
                estadoArchivo = "Foto Subida con exito";
            } else if (estado == 1) {//Modificacion
                estadoArchivo = "Foto actualizada con exito";
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fotografia", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    //Metodos para iniciar Sesion
    public String login() {
        String ruta = "/login.xhtml";
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        List<SmsUsuario> user = usuarioDao.consultarDatosSesionUsuario(usuarioView);//Trae de la base de datos toda la informacion de usuario

        if (!user.isEmpty()) {//valida si el usuario existe en la BD
            if (user.get(0).getUsuarioEstadoUsuario() == 1) {//Evalua el estado de la cuenta de usuario, si esta activa o inactiva
                if (user.get(0).getUsuarioLogin().equalsIgnoreCase(usuarioView.getUsuarioLogin()) && user.get(0).getUsuarioPassword().equalsIgnoreCase(usuarioView.getUsuarioPassword())) {
                    ruta = usuarioController.iniciarSesion(usuarioView);//envia el objeto usuarioBean al metodo iniciarSesion para tomar este objeto como atributo de sesion
                    Usuario = usuarioController.obtenerSesion();
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
        String ruta = "Login";
        usuarioController.cerrarSesion();
        return ruta;
    }    

}
