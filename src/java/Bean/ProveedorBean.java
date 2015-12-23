/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Upload;
import Controlador.Proveedor;
import Modelo.SmsCiudad;
import Modelo.SmsProveedor;
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
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Desarrollo_Planit
 */
public class ProveedorBean implements Serializable {

    //Objetos necesarios para vista
    protected SmsUsuario usuarioView;
    protected SmsProveedor proveedorView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    private UploadedFile archivo;

    //Relacion con el controlador
    protected Proveedor proveedorController;
    protected Upload fileController;

    //lista de Id de proveedor
    private List<SmsUsuario> proveedoresListView;
    private List<String> nombresProveedoresView;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    private Boolean habilitarSubir;
    private String subirArchivo;
    private String estadoArchivo;

    public ProveedorBean() {
        usuarioView = new SmsUsuario();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        proveedorController = new Proveedor();
        fileController = new Upload();

        buscar = null;
        estado = 0;
        nombre = "Registrar Proveedor";
        habilitarSubir = false;
        subirArchivo = "Subir fotografia";
        estadoArchivo = "Foto sin subir";
    }

    @PostConstruct
    public void init() {
        proveedoresListView = proveedorController.consultarProveedores();
    }

    //Getters & Setters
    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public SmsProveedor getProveedorView() {
        return proveedorView;
    }

    public void setProveedorView(SmsProveedor proveedorView) {
        this.proveedorView = proveedorView;
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

    public Proveedor getProveedorController() {
        return proveedorController;
    }

    public void setProveedorController(Proveedor proveedorController) {
        this.proveedorController = proveedorController;
    }

    public List<SmsUsuario> getProveedoresListView() {
        return proveedoresListView;
    }

    public void setProveedoresListView(List<SmsUsuario> proveedoresListView) {
        this.proveedoresListView = proveedoresListView;
    }

    public List<String> getNombresProveedoresView() {
        nombresProveedoresView = new ArrayList<>();
        proveedoresListView = proveedorController.consultarProveedores();

        for (int i = 0; i < proveedoresListView.size(); i++) {
            nombresProveedoresView.add(proveedoresListView.get(i).getUsuarioNombre());
        }
        return nombresProveedoresView;
    }

    public void setNombresProveedoresView(List<String> nombresProveedoresView) {
        this.nombresProveedoresView = nombresProveedoresView;
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

    public String getSubirArchivo() {
        return subirArchivo;
    }

    public void setSubirArchivo(String subirArchivo) {
        this.subirArchivo = subirArchivo;
    }

    public Boolean getHabilitarSubir() {
        return habilitarSubir;
    }

    public void setHabilitarSubir(Boolean habilitarSubir) {
        this.habilitarSubir = habilitarSubir;
    }

    public String getEstadoArchivo() {
        return estadoArchivo;
    }

    public void setEstadoArchivo(String estadoArchivo) {
        this.estadoArchivo = estadoArchivo;
    }

    public void filtrar() {
        proveedoresListView = new ArrayList<>();
        if (buscar == null) {
            proveedoresListView = proveedorController.consultarProveedores();
        } else {
            proveedoresListView = proveedorController.filtrarProveedores(buscar);
        }
    }

    //Metodos    
    public void registrar() {
        rolView.setRolNombre("Proveedor");
        proveedorController.registrarUsuario(usuarioView, ciudadView, rolView);
        proveedorController.registrarProveedor(proveedorView, usuarioView);
        proveedoresListView = proveedorController.consultarProveedores();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        proveedorView = new SmsProveedor();
        rolView = new SmsRol();

    }

    public void modificar() {
        rolView.setRolNombre("Proveedor");
        proveedorController.modificarUsuario(usuarioView, ciudadView, rolView);
        proveedorController.modificarProveedor(proveedorView, usuarioView);
        proveedoresListView = proveedorController.consultarProveedores();

        proveedorView = new SmsProveedor();
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;
    }

    public void eliminar() {
        proveedorController.eliminarProveedor(proveedorView);
        proveedorController.eliminarUsuario(usuarioView);
        proveedorView = new SmsProveedor();
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        proveedoresListView = proveedorController.consultarProveedores();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Proveedor";

            proveedorView = proveedorController.consultarProveedor(usuarioView).get(0);
            ciudadView = usuarioView.getSmsCiudad();

            if (usuarioView.getUsuarioFotoNombre() != null && usuarioView.getUsuarioFotoRuta() != null) {
                subirArchivo = "Modificar Fotografia";
                estadoArchivo = "Foto subida:" + usuarioView.getUsuarioFotoNombre();
                habilitarSubir = false;
            } else {
                subirArchivo = "Subir Fotografia";
                habilitarSubir = false;
            }
        } else if (estado == 2) {
            nombre = "Eliminar Proveedor";

            proveedorView = proveedorController.consultarProveedor(usuarioView).get(0);
            ciudadView = usuarioView.getSmsCiudad();

            subirArchivo = "Subir Fotografia";
            habilitarSubir = true;
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Proveedor";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Proveedor";
        }
    }

    public void uploadPhoto(FileUploadEvent e) throws IOException {
        try {
            UploadedFile uploadedPhoto = e.getFile();
            String destination;

            HashMap<String, String> map = fileController.getMapPathFotosUsuario();
            destination = map.get("path");
            if (null != uploadedPhoto) {
                fileController.uploadFile(IOUtils.toByteArray(uploadedPhoto.getInputstream()), uploadedPhoto.getFileName(), destination);
                usuarioView.setUsuarioFotoNombre(uploadedPhoto.getFileName());
                usuarioView.setUsuarioFotoRuta(map.get("path") + uploadedPhoto.getFileName());
                habilitarSubir = true;
                if (estado == 0) {
                    estadoArchivo = "Foto Subida con exito";
                } else if (estado == 1) {
                    estadoArchivo = "Foto actualizada con exito";
                }
            }
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Su foto (" + uploadedPhoto.getFileName() + ")  se ha guardado con exito.", ""));
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

}

