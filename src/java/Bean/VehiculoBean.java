/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Upload;
import Controlador.EstadoVehiculo;
import Controlador.Usuario;
import Controlador.Vehiculo;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

public class VehiculoBean {

    //Objetos de vista 
    private SmsVehiculo vehiculoView;
    private List<SmsVehiculo> vehiculosListView;
    private List<String> PlacasVehiculosListView;
    private SmsUsuario usuarioView;

    private SmsCategoria categoriaView;
    private SmsCiudad ciudadView;
    private SmsProveedor proveedorView;
    private SmsReferencia refenciaView;
    private SmsEstadovehiculo estadoVehiculoView;

    //Relacion con el controlodar
    private Vehiculo vehiculoController;
    protected Upload fileController;
    private EstadoVehiculo estadoVehiculoController;
    private Usuario usuarioController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    private Boolean habilitarSubir;
    private String subirArchivo;
    private String estadoArchivo;
    private UploadedFile archivo;

    public VehiculoBean() {
        vehiculoController = new Vehiculo();
        vehiculoView = new SmsVehiculo();
        categoriaView = new SmsCategoria();
        ciudadView = new SmsCiudad();
        proveedorView = new SmsProveedor();
        refenciaView = new SmsReferencia();
        estadoVehiculoView = new SmsEstadovehiculo();
        estadoVehiculoController = new EstadoVehiculo();
        fileController = new Upload();
        usuarioView = new SmsUsuario();
        usuarioController = new Usuario();
        
        buscar = null;
        estado = 0;
        nombre = "Registrar Vehiculo";
        habilitarSubir = false;
        subirArchivo = "Subir fotografia";
        estadoArchivo = "Foto sin subir";
    }

    @PostConstruct
    public void init() {
        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    //Getters & Setters
    public SmsCategoria getCategoriaView() {
        return categoriaView;
    }

    public void setCategoriaView(SmsCategoria categoriaView) {
        this.categoriaView = categoriaView;
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciudadView) {
        this.ciudadView = ciudadView;
    }

    public SmsProveedor getProveedorView() {
        return proveedorView;
    }

    public void setProveedorView(SmsProveedor proveedorView) {
        this.proveedorView = proveedorView;
    }

    public SmsReferencia getRefenciaView() {
        return refenciaView;
    }

    public void setRefenciaView(SmsReferencia refenciaView) {
        this.refenciaView = refenciaView;
    }

    public SmsEstadovehiculo getEstadoVehiculoView() {
        return estadoVehiculoView;
    }

    public void setEstadoVehiculoView(SmsEstadovehiculo estadoVehiculoView) {
        this.estadoVehiculoView = estadoVehiculoView;
    }

    public List<SmsVehiculo> getVehiculosListView() {
        return vehiculosListView;
    }

    public void setVehiculosListView(List<SmsVehiculo> vehiculosListView) {
        this.vehiculosListView = vehiculosListView;
    }

    public List<String> getPlacasVehiculosListView() {
        return PlacasVehiculosListView;
    }

    public void setPlacasVehiculosListView(List<String> PlacasVehiculosListView) {
        this.PlacasVehiculosListView = PlacasVehiculosListView;
    }

    public Vehiculo getVehiculoController() {
        return vehiculoController;
    }

    public void setVehiculoController(Vehiculo vehiculoController) {
        this.vehiculoController = vehiculoController;
    }

    public SmsVehiculo getVehiculoView() {
        return vehiculoView;
    }

    public void setVehiculoView(SmsVehiculo veh) {
        this.vehiculoView = veh;
    }

    public Upload getFileController() {
        return fileController;
    }

    public void setFileController(Upload fileController) {
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

    public EstadoVehiculo getEstadoVehiculoController() {
        return estadoVehiculoController;
    }

    public void setEstadoVehiculoController(EstadoVehiculo estadoVehiculoController) {
        this.estadoVehiculoController = estadoVehiculoController;
    }

    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public Usuario getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(Usuario usuarioController) {
        this.usuarioController = usuarioController;
    }
    
    

    //Definicion de metodos VEHICULO
    public void registrar() {
        vehiculoController.registrarVehiculo(categoriaView, usuarioView, ciudadView, refenciaView, vehiculoView);
        vehiculoView = vehiculoController.consultarVehiculo(vehiculoView).get(0);
        estadoVehiculoView.setSmsVehiculo(vehiculoView);
        estadoVehiculoController.registrarEstVeh(estadoVehiculoView);

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
        usuarioView = new SmsUsuario();
        estadoVehiculoView = new SmsEstadovehiculo();
        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    public void modificar() {
        vehiculoController.modificarVehiculo(categoriaView, usuarioView, ciudadView, refenciaView, vehiculoView);
        vehiculoView = vehiculoController.consultarVehiculo(vehiculoView).get(0);
        estadoVehiculoView.setSmsVehiculo(vehiculoView);
        estadoVehiculoController.modificarEstVeh(estadoVehiculoView);

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
        usuarioView = new SmsUsuario();
        estadoVehiculoView = new SmsEstadovehiculo();
        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    public void eliminar() {
        vehiculoController.eliminarVehiculo(vehiculoView);

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
        usuarioView = new SmsUsuario();
        estadoVehiculoView = new SmsEstadovehiculo();

        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;

        if (estado == 1) {//MODIFICACION
            nombre = "Modificar Vehiculo";
            ciudadView = vehiculoView.getSmsCiudad();
            refenciaView = vehiculoView.getSmsReferencia();
            categoriaView = vehiculoView.getSmsCategoria();
            proveedorView = vehiculoView.getSmsProveedor();
            usuarioView = usuarioController.consultarUsuario(proveedorView.getSmsUsuario()).get(0);
            estadoVehiculoView = estadoVehiculoController.consultarEstado(vehiculoView).get(0);

            if (vehiculoView.getVehFotoNombre() != null && vehiculoView.getVehFotoRuta() != null) {
                subirArchivo = "Modificar Fotografia";
                estadoArchivo = "Foto subida:" + vehiculoView.getVehFotoNombre();
                habilitarSubir = false;
            } else {
                subirArchivo = "Subir Fotografia";
                habilitarSubir = false;
            }
        } else if (estado == 2) {//ELIMINACION
            nombre = "Eliminar Vehiculo";
            ciudadView = vehiculoView.getSmsCiudad();
            refenciaView = vehiculoView.getSmsReferencia();
            categoriaView = vehiculoView.getSmsCategoria();
            proveedorView = vehiculoView.getSmsProveedor();
            estadoVehiculoView = estadoVehiculoController.consultarEstado(vehiculoView).get(0);
            usuarioView = usuarioController.consultarUsuario(proveedorView.getSmsUsuario()).get(0);
            subirArchivo = "Subir Fotografia";
            estadoArchivo = "Foto subida:" + vehiculoView.getVehFotoNombre();
            habilitarSubir = true;
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Vehiculo";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Vehiculo";
        }
    }

    public void upload() throws IOException {
        FacesMessage message = new FacesMessage();
        if (null != getArchivo()) {
            fileController.UploadFile(IOUtils.toByteArray(getArchivo().getInputstream()), getArchivo().getFileName());
            vehiculoView.setVehFotoNombre(getArchivo().getFileName());
            vehiculoView.setVehFotoRuta(fileController.getFilePath());
            habilitarSubir = true;
            if (estado == 0) {
                estadoArchivo = "Foto Subida con exito";
            } else if (estado == 1) {
                estadoArchivo = "Foto actualizada con exito";
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fotografia", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
