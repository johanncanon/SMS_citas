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
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class VehiculoBean {

    //Objetos de vista 
    private SmsVehiculo vehiculoView;
    private SmsVehiculo DVehiculoView;
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
        DVehiculoView = new SmsVehiculo();
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

    public SmsVehiculo getDVehiculoView() {
        return DVehiculoView;
    }

    public void setDVehiculoView(SmsVehiculo DVehiculoView) {
        this.DVehiculoView = DVehiculoView;
    }

    
    //Definicion de metodos VEHICULO
    public void registrar() {

        //En caso de subir NO una fotografia del vehiculo, el sistema asigna al vehiculo una fotografia default
        if (vehiculoView.getVehFotoRuta() == null && vehiculoView.getVehFotoNombre() == null) {
            String ruta = fileController.getPathDefaultVehiculo();
            vehiculoView.setVehFotoRuta(ruta);
            vehiculoView.setVehFotoNombre("Default.png");
        }

        //Registramos el vehiculo
        vehiculoController.registrarVehiculo(proveedorView, categoriaView, usuarioView, ciudadView, refenciaView, vehiculoView);

        //consultamos el vehiculo recien registrado
        vehiculoView = vehiculoController.consultarVehiculo(vehiculoView).get(0);
        estadoVehiculoView.setSmsVehiculo(vehiculoView); //relacionamos el vehiculo con los valores asignados en la seccion de estado

        estadoVehiculoController.registrarEstVeh(estadoVehiculoView);//registramos el estado

        //Reiniciamos valores para las variables llamadas desde las vista
        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        //limpiamos objetos
        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
        usuarioView = new SmsUsuario();
        estadoVehiculoView = new SmsEstadovehiculo();

        //Actualizamos la lista que muestra los vehiculos registrados en el sistema
        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    public void modificar() {
        //Ejecutamos la modificacion del vehiculo
        vehiculoController.modificarVehiculo(proveedorView, categoriaView, usuarioView, ciudadView, refenciaView, vehiculoView);
        
        //Consultamos el vehiculo recien registrado
        vehiculoView = vehiculoController.consultarVehiculo(vehiculoView).get(0);
        estadoVehiculoView.setSmsVehiculo(vehiculoView); //Relacionamos el estado de vehiculo con el vehiculo.

        estadoVehiculoController.registrarEstVeh(estadoVehiculoView);//Registramos el estado del vehiculo

        //Reiniciamos valores para las variables llamadas desde las vista
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
        
        //Actualizamos la lista que muestra los vehiculos registrados en el sistema
        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    public void eliminar() {
        vehiculoController.eliminarVehiculo(DVehiculoView);
        
        if(vehiculoView.equals(DVehiculoView)){
        usuarioView = new SmsUsuario();
        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();       
        estadoVehiculoView = new SmsEstadovehiculo();
        nombre = "Registrar Vehiculo";
        estado = 0;
        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;
        }
        
        DVehiculoView = new SmsVehiculo();
        vehiculosListView = vehiculoController.cargarVehiculos();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {//MODIFICACION
            nombre = "Modificar Vehiculo";
            //Asignamos a cada componente su correspondiente valor extraido del vehiculo seleccionado
            ciudadView = vehiculoView.getSmsCiudad();
            refenciaView = vehiculoView.getSmsReferencia();
            categoriaView = vehiculoView.getSmsCategoria();
            proveedorView = vehiculoView.getSmsProveedor();
            
            //Consultamos los datos de usuario del proveedor correspondiente
            usuarioView = usuarioController.consultarUsuario(proveedorView.getSmsUsuario()).get(0);
            //Consultamos el estado del vehiculo
            estadoVehiculoView = estadoVehiculoController.consultarEstado(vehiculoView).get(0);

            //Si el vehiculo tiene una foto asignada damos valores a nuestras variables para mostrar que foto esta asignada
            if (vehiculoView.getVehFotoNombre() != null && vehiculoView.getVehFotoRuta() != null) {
                subirArchivo = "Modificar Fotografia";
                estadoArchivo = "Foto subida:" + vehiculoView.getVehFotoNombre();
                habilitarSubir = false;
            } else { //En caso de no existir fotografia, indicamos en la vista la posibilidad de subir una foto para el vehiculo
                subirArchivo = "Subir Fotografia";
                habilitarSubir = false;
            }
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Vehiculo";
        }
    }

    public void uploadPhoto(FileUploadEvent e) throws IOException {
        try {
            UploadedFile uploadedPhoto = e.getFile();
            String destination;

            HashMap<String, String> map = fileController.getMapPathFotosVehiculos();
            destination = map.get("path");
            if (null != uploadedPhoto) {
                fileController.uploadFile(IOUtils.toByteArray(uploadedPhoto.getInputstream()), uploadedPhoto.getFileName(), destination);
                vehiculoView.setVehFotoNombre(uploadedPhoto.getFileName());
                vehiculoView.setVehFotoRuta(map.get("url") + uploadedPhoto.getFileName());
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
