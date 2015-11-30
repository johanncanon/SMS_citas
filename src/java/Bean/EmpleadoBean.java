/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Archivos;
import Controlador.Empleado;
import Controlador.HojaVida;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsHojavida;
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
public class EmpleadoBean implements Serializable {

    //objetos necesarios en vista
    protected SmsUsuario usuarioView;
    protected SmsEmpleado empleadoView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    protected SmsHojavida hojavidaView;
    protected List<SmsUsuario> empleadosListView;

    //Relacion con el controlador
    protected Empleado empleadoController;
    protected HojaVida hojaVidaController;
    protected Archivos fileController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    private Boolean habilitarSubirFoto;
    private String subirFoto;
    private String estadoFoto;
    private UploadedFile archivo;
    private UploadedFile foto;
    private Boolean habilitarSubirArchivo;
    private String subirArchivo;
    private String estadoArchivo;
    private Boolean registroHojaVida;

    public EmpleadoBean() {
        usuarioView = new SmsUsuario();
        empleadoView = new SmsEmpleado();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        hojavidaView = new SmsHojavida();

        fileController = new Archivos();
        empleadoController = new Empleado();
        hojaVidaController = new HojaVida();

        buscar = null;
        estado = 0;
        nombre = "Registrar Empleado";

        habilitarSubirArchivo = false;
        subirFoto = "Subir fotografia";
        estadoFoto = "Foto sin subir";

        habilitarSubirFoto = false;
        subirArchivo = "Subir Hoja de vida";
        estadoArchivo = "Hoja de vida sin subir";

        registroHojaVida = false;
    }

    @PostConstruct
    public void init() {
        empleadosListView = empleadoController.consultarEmpleados();
    }

    //Getters & Setters
    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public SmsEmpleado getEmpleadoView() {
        return empleadoView;
    }

    public void setEmpleadoView(SmsEmpleado empleadoView) {
        this.empleadoView = empleadoView;
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

    public SmsHojavida getHojavidaView() {
        return hojavidaView;
    }

    public void setHojavidaView(SmsHojavida hojavidaView) {
        this.hojavidaView = hojavidaView;
    }

    public Boolean getRegistroHojaVida() {
        return registroHojaVida;
    }

    public void setRegistroHojaVida(Boolean registroHojaVida) {
        this.registroHojaVida = registroHojaVida;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public Empleado getEmpleadoController() {
        return empleadoController;
    }

    public void setEmpleadoController(Empleado empleadoController) {
        this.empleadoController = empleadoController;
    }

    public HojaVida getHojaVidaController() {
        return hojaVidaController;
    }

    public void setHojaVidaController(HojaVida hojaVidaController) {
        this.hojaVidaController = hojaVidaController;
    }

    public Archivos getFileController() {
        return fileController;
    }

    public void setFileController(Archivos fileController) {
        this.fileController = fileController;
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

    public Boolean getHabilitarSubirFoto() {
        return habilitarSubirFoto;
    }

    public void setHabilitarSubirFoto(Boolean habilitarSubirFoto) {
        this.habilitarSubirFoto = habilitarSubirFoto;
    }

    public String getSubirFoto() {
        return subirFoto;
    }

    public void setSubirFoto(String subirFoto) {
        this.subirFoto = subirFoto;
    }

    public String getEstadoFoto() {
        return estadoFoto;
    }

    public void setEstadoFoto(String estadoFoto) {
        this.estadoFoto = estadoFoto;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public Boolean getHabilitarSubirArchivo() {
        return habilitarSubirArchivo;
    }

    public void setHabilitarSubirArchivo(Boolean habilitarSubirArchivo) {
        this.habilitarSubirArchivo = habilitarSubirArchivo;
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

    public List<SmsUsuario> getEmpleadosListView() {
        return empleadosListView;
    }

    public void setEmpleadosListView(List<SmsUsuario> empleadosListView) {
        this.empleadosListView = empleadosListView;
    }

    //Metodos que se comunican con el controlador    
    public void registrar() {
        usuarioView.setUsuarioRazonSocial("SMS Renta");
        usuarioView.setUsuarioNit("");
        rolView.setRolNombre("Empleado");

        empleadoController.registrarUsuario(usuarioView, ciudadView, rolView);
        empleadoController.registrarEmpleado(usuarioView, hojavidaView);

        estadoFoto = "Foto sin subir";
        subirFoto = "Subir Fotografia";
        habilitarSubirFoto = false;

        estadoArchivo = "Hoja de vida sin subir";
        subirArchivo = "Subir Hoja de vida";
        habilitarSubirArchivo = false;
        registroHojaVida = false;

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();

        empleadosListView = empleadoController.consultarEmpleados();
    }

    public void modificar() {
        rolView.setRolNombre("Empleado");
        empleadoController.modificarUsuario(usuarioView, ciudadView, rolView);
        empleadoController.modificarEmpleado(usuarioView, hojavidaView, empleadoView);

        estadoFoto = "Foto sin subir";
        subirFoto = "Subir Fotografia";
        habilitarSubirFoto = false;

        estadoArchivo = "Hoja de vida sin subir";
        subirArchivo = "Subir Hoja de vida";
        habilitarSubirArchivo = false;
        registroHojaVida = false;

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        empleadoView = new SmsEmpleado();
        hojavidaView = new SmsHojavida();

        empleadosListView = empleadoController.consultarEmpleados();
    }

    public void eliminar() {
        empleadoController.eliminarEmpleado(usuarioView, hojavidaView, empleadoView);

        estadoFoto = "Foto sin subir";
        subirFoto = "Subir Fotografia";
        habilitarSubirFoto = false;

        estadoArchivo = "Hoja de vida sin subir";
        subirArchivo = "Subir Hoja de vida";
        habilitarSubirArchivo = false;
        registroHojaVida = false;

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        empleadoView = new SmsEmpleado();
        hojavidaView = new SmsHojavida();

        empleadosListView = empleadoController.consultarEmpleados();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;

        if (estado == 1) {//MODIFICACION
            nombre = "Modificar Empleado";
            ciudadView = usuarioView.getSmsCiudad();
            rolView = usuarioView.getSmsRol();

            empleadoView = empleadoController.consultarEmpleado(usuarioView).get(0);
            if (empleadoView.getSmsHojavida() != null) {
                hojavidaView = empleadoView.getSmsHojavida();
            }

            if (usuarioView.getUsuarioFotoNombre() != null && usuarioView.getUsuarioFotoRuta() != null) {
                subirFoto = "Modificar Fotografia";
                estadoFoto = "Foto subida:" + usuarioView.getUsuarioFotoNombre();
                habilitarSubirFoto = false;
            } else {
                subirFoto = "Subir Fotografia";
                habilitarSubirFoto = false;
            }

            if (hojavidaView.getHojaVidaNombre() != null && hojavidaView.getHojaVidaRuta() != null) {
                subirArchivo = "Modificar Hoja de vida";
                estadoArchivo = "Hoja subida:" + hojavidaView.getHojaVidaNombre();
                habilitarSubirArchivo = false;
            } else {
                subirArchivo = "Subir Hoja vida";
                habilitarSubirArchivo = false;
            }
        } else if (estado == 2) {//ELIMINACION
            nombre = "Eliminar Empleado";
            ciudadView = usuarioView.getSmsCiudad();
            rolView = usuarioView.getSmsRol();

            empleadoView = empleadoController.consultarEmpleado(usuarioView).get(0);
            if (empleadoView.getSmsHojavida() != null) {
                hojavidaView = empleadoView.getSmsHojavida();
            }

            subirFoto = "Subir Fotografia";
            habilitarSubirFoto = true;
            subirArchivo = "Subir Hoja vida";
            habilitarSubirArchivo = true;
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Empleado";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Empleado";
        }
    }

    //Subida de archivos
    public void uploadFoto() throws IOException {
        FacesMessage message = new FacesMessage();
        if (null != getFoto()) {
            fileController.UploadFile(IOUtils.toByteArray(getFoto().getInputstream()), getFoto().getFileName());
            usuarioView.setUsuarioFotoNombre(getFoto().getFileName());
            usuarioView.setUsuarioFotoRuta(fileController.getFilePath());
            habilitarSubirFoto = true;
            if (estado == 0) {
                estadoFoto = "Foto Subida con exito";
            } else if (estado == 1) {
                estadoFoto = "Foto actualizada con exito";
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fotografia", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    //Subida de archivos
    public void uploadArchivo() throws IOException {
        FacesMessage message = new FacesMessage();
        if (null != getArchivo()) {
            fileController.UploadFile(IOUtils.toByteArray(getArchivo().getInputstream()), getArchivo().getFileName());
            hojavidaView.setHojaVidaNombre(getArchivo().getFileName());
            hojavidaView.setHojaVidaRuta(fileController.getFilePath());
            habilitarSubirArchivo = true;
            if (estado == 0) {
                estadoArchivo = "Hoja de vida subida con exito";
            } else if (estado == 1) {
                estadoArchivo = "Hoja de vida actualizada con exito";
            }

            hojaVidaController.registrarHojaVida(hojavidaView);
            registroHojaVida = true;
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione Hoja de vida", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
