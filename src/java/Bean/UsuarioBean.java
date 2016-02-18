/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Empleado;
import Funciones.MD5;
import Funciones.Upload;
import Controlador.Usuario;
import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsHojavida;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Desarrollo_Planit
 */
//Bean para usuario
public class UsuarioBean implements Serializable {

    //Instanciacion de los objetos    
    protected SmsUsuario usuarioView;
    protected SmsUsuario DUsuarioView;
    protected SmsUsuario modUsuarioView;
    protected SmsEmpleado modEmpleadoView;
    protected List<SmsUsuario> usuariosListView;
    protected SmsCiudad ciudadUsuario;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    protected SmsHojavida hojavidaView;

    //Controles de componentes
    boolean habilitado;

    //Relacion con el controlador
    protected Usuario usuarioController;
    protected Upload fileController;
    protected Empleado empleadoController;

    //Contexto
    private FacesMessage message;

    //Sesion
    protected SmsUsuario Usuario;

    //Variables
    private String buscar;
    private Boolean habilitarEditarSesion;
    private String pass;

    private Boolean habilitarSubirFoto;
    private String estadoFoto;

    private Boolean habilitarSubirArchivo;
    private String estadoArchivo;

    //Contexto y sesion
    private HttpSession httpSession;

    public UsuarioBean() {
        DUsuarioView = new SmsUsuario();
        usuarioView = new SmsUsuario();
        modUsuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modEmpleadoView = new SmsEmpleado();
                
        fileController = new Upload();
        usuarioController = new Usuario();
        empleadoController = new Empleado();
        
        Usuario = new SmsUsuario();
        ciudadUsuario = new SmsCiudad();
               
        habilitado = true;
        habilitarSubirFoto = false;
        buscar = null;
        habilitarEditarSesion = false;
        }

    @PostConstruct
    public void init() {
        usuariosListView = usuarioController.consultarAdministradores();
    }

    //Getters & Setters
    public SmsCiudad getCiudadUsuario() {
        return ciudadUsuario;
    }

    public void setCiudadUsuario(SmsCiudad ciudadUsuario) {
        this.ciudadUsuario = ciudadUsuario;
    }

    public List<SmsUsuario> getUsuariosListView() {
        return usuariosListView;
    }

    public void setUsuariosListView(List<SmsUsuario> usuariosListView) {
        this.usuariosListView = usuariosListView;
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

    public SmsUsuario getDUsuarioView() {
        return DUsuarioView;
    }

    public void setDUsuarioView(SmsUsuario DUsuarioView) {
        this.DUsuarioView = DUsuarioView;
    }

    public Boolean getHabilitarSubirFoto() {
        return habilitarSubirFoto;
    }

    public void setHabilitarSubirFoto(Boolean habilitarSubirFoto) {
        this.habilitarSubirFoto = habilitarSubirFoto;
    }

    public String getEstadoFoto() {
        return estadoFoto;
    }

    public void setEstadoFoto(String estadoFoto) {
        this.estadoFoto = estadoFoto;
    }

    public SmsHojavida getHojavidaView() {
        return hojavidaView;
    }

    public void setHojavidaView(SmsHojavida hojavidaView) {
        this.hojavidaView = hojavidaView;
    }

    public Boolean getHabilitarSubirArchivo() {
        return habilitarSubirArchivo;
    }

    public void setHabilitarSubirArchivo(Boolean habilitarSubirArchivo) {
        this.habilitarSubirArchivo = habilitarSubirArchivo;
    }

    public String getEstadoArchivo() {
        return estadoArchivo;
    }

    public void setEstadoArchivo(String estadoArchivo) {
        this.estadoArchivo = estadoArchivo;
    }

    public SmsEmpleado getModEmpleadoView() {
        return modEmpleadoView;
    }

    public void setModEmpleadoView(SmsEmpleado modEmpleadoView) {
        this.modEmpleadoView = modEmpleadoView;
    }

    //Declaracion de metodos
    //Metodos CRUD
    public void registrar() {

        //asignamos al usuario la imagen de perfil default
        usuarioView.setUsuarioFotoRuta(fileController.getPathDefaultUsuario());
        usuarioView.setUsuarioFotoNombre(fileController.getNameDefaultUsuario());
        //registramos el usuario y recargamos la lista de clientes
        usuarioController.registrarUsuario(usuarioView, ciudadView, rolView);
        usuariosListView = usuarioController.consultarAdministradores();

        //limpiamos objetos
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modUsuarioView = new SmsUsuario();
    }

    public void modificarPerfil() {
        
        MD5 md = new MD5();

        if (habilitarEditarSesion) { // en caso de modificar las contraseñas estas se encriptan de nuevo
            modUsuarioView.setUsuarioPassword(md.getMD5(modUsuarioView.getUsuarioPassword()));
            modUsuarioView.setUsuarioRememberToken(md.getMD5(modUsuarioView.getUsuarioRememberToken()));
        }

        usuarioController.modificarPerfilUsuario(modUsuarioView, ciudadUsuario);

        if (Usuario.getSmsRol().getIdRol() == 4) { // 4 = id del rol empleado
            modEmpleadoView = empleadoController.consultarEmpleado(Usuario).get(0);
            empleadoController.modificarEmpleado(modUsuarioView, hojavidaView, modEmpleadoView);
            estadoArchivo = "Hoja subida:" + hojavidaView.getHojaVidaNombre();
        }
         habilitarEditarSesion = false;
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
        usuarioController.eliminarUsuario(DUsuarioView);
        usuariosListView = usuarioController.consultarAdministradores();
        if (usuarioView.equals(DUsuarioView)) {
            usuarioView = new SmsUsuario();
        }
        DUsuarioView = new SmsUsuario();
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
    public String iniciarSesion() {
        String ruta = "/login.xhtml";
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        MD5 md = new MD5();
        usuarioView.setUsuarioPassword(md.getMD5(usuarioView.getUsuarioPassword()));
        SmsUsuario user;
       
        if (!usuarioDao.consultarDatosSesionUsuario(usuarioView).isEmpty()) {//valida si el usuario existe en la BD
            user = usuarioDao.consultarDatosSesionUsuario(usuarioView).get(0);
            if (user.getUsuarioEstadoUsuario() == 1) {//Evalua el estado de la cuenta de usuario, si esta activa o inactiva
                if (user.getUsuarioPassword()!= null && user.getUsuarioLogin().equalsIgnoreCase(usuarioView.getUsuarioLogin()) && user.getUsuarioPassword().equalsIgnoreCase(usuarioView.getUsuarioPassword())) {
                    //ruta = usuarioController.iniciarSesion(user.get(0));//envia el objeto usuarioBean al metodo iniciarSesion para tomar este objeto como atributo de sesion

                    rolView = user.getSmsRol();
                    httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("Sesion", user);

                    switch (rolView.getRolNombre()) {
                        case "Administrador Principal":
                            ruta = "AdminPPrincipal";
                            break;
                        case "Administrador Secundario":
                            ruta = "AdminSGeneral";
                            break;
                        case "Cliente":
                            ruta = "ClienteDash";
                            break;
                        case "Empleado":
                            ruta = "ConductorDash";
                            break;
                        case "Proveedor":
                            ruta = "ProveedorDash";
                            break;
                    }
                    
                  
                    Usuario = (SmsUsuario) httpSession.getAttribute("Sesion");
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", "Bienvenid@: " + Usuario.getUsuarioNombre());
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                    usuarioView = new SmsUsuario();
                }
            } else if (user.getUsuarioEstadoUsuario() == 0) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario inactivo, imposible iniciar sesion", null);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no existente", null);
            usuarioView = new SmsUsuario();
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        usuarioView = new SmsUsuario();
                
        
        return ruta;
    }

    public String cerrarSesion() {
        String ruta = "Login";
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return ruta;
    }

    public String ir_editarPerfil() {
        modUsuarioView = Usuario;
        ciudadUsuario = Usuario.getSmsCiudad();
        estadoFoto = "Foto subida:" + modUsuarioView.getUsuarioFotoNombre();

        String ruta = "";
        switch (Usuario.getSmsRol().getIdRol()) {
            case 1:
                ruta = "AdminPEdicionPerfil";           
                break;
            case 2:
                ruta = "AdminSEdicionPerfil";             
                break;
            case 3:
                ruta = "ClienteEdicionPerfil";
                break;
            case 4:
                modEmpleadoView = empleadoController.consultarEmpleado(modUsuarioView).get(0);
                hojavidaView = modEmpleadoView.getSmsHojavida();
                estadoArchivo = "Hoja subida:" + hojavidaView.getHojaVidaNombre();
                ruta = "CondEdicionPerfil";
                break;
            case 5:
                ruta = "ProveedorEdicionPerfil";
                break;
        }

        return ruta;
    }

    //Subida de archivos
    public void uploadPhoto(FileUploadEvent e) throws IOException {
        try {
            UploadedFile uploadedPhoto = e.getFile();
            String destination;

            HashMap<String, String> map = fileController.getMapPathFotosUsuario();
            destination = map.get("path");
            if (null != uploadedPhoto) {
                fileController.uploadFile(IOUtils.toByteArray(uploadedPhoto.getInputstream()), uploadedPhoto.getFileName(), destination);
                modUsuarioView.setUsuarioFotoNombre(uploadedPhoto.getFileName());
                modUsuarioView.setUsuarioFotoRuta(map.get("url") + uploadedPhoto.getFileName());
                habilitarSubirFoto = true;
                estadoFoto = "Foto actualizada con exito";
            }
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Su foto (" + uploadedPhoto.getFileName() + ")  se ha guardado con exito.", ""));
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    
}
