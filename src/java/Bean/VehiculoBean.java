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
import DAO.IVehiculoDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.io.IOException;
import java.util.ArrayList;
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

    private SmsVehiculo MvehiculoView;
    private SmsCategoria McategoriaView;
    private SmsCiudad MciudadView;
    private SmsProveedor MproveedorView;
    private SmsReferencia MrefenciaView;
    private SmsEstadovehiculo MestadoVehiculoView;
    private SmsUsuario MusuarioView;

    //Relacion con el controlador
    protected Vehiculo vehiculoController;
    protected Upload fileController;
    protected EstadoVehiculo estadoVehiculoController;
    protected Usuario usuarioController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    private String modEstadoArchivo;
    private String estadoArchivo;
    private UploadedFile archivo;
    
    IVehiculoDao vehDao;

    public VehiculoBean() {
        vehiculoController = new Vehiculo();
        vehiculoView = new SmsVehiculo();
        DVehiculoView = new SmsVehiculo();
        categoriaView = new SmsCategoria();
        ciudadView = new SmsCiudad();
        proveedorView = new SmsProveedor();
        refenciaView = new SmsReferencia();
        estadoVehiculoView = new SmsEstadovehiculo();

        MvehiculoView = new SmsVehiculo();
        McategoriaView = new SmsCategoria();
        MciudadView = new SmsCiudad();
        MproveedorView = new SmsProveedor();
        MrefenciaView = new SmsReferencia();
        MestadoVehiculoView = new SmsEstadovehiculo();
        MusuarioView = new SmsUsuario();

        estadoVehiculoController = new EstadoVehiculo();
        fileController = new Upload();
        usuarioView = new SmsUsuario();
        usuarioController = new Usuario();

        buscar = null;
        estado = 0;

        nombre = "Registrar Vehiculo";
        modEstadoArchivo = "Foto sin subir";
        estadoArchivo = "Foto sin subir";
        
        vehDao = new ImpVehiculoDao();
    }

    @PostConstruct
    public void init() {
        vehiculosListView = vehDao.mostrarVehiculo();
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

    public SmsVehiculo getVehiculoView() {
        return vehiculoView;
    }

    public void setVehiculoView(SmsVehiculo veh) {
        this.vehiculoView = veh;
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

    public String getModEstadoArchivo() {
        return modEstadoArchivo;
    }

    public void setModEstadoArchivo(String modEstadoArchivo) {
        this.modEstadoArchivo = modEstadoArchivo;
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

    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public SmsVehiculo getDVehiculoView() {
        return DVehiculoView;
    }

    public void setDVehiculoView(SmsVehiculo DVehiculoView) {
        this.DVehiculoView = DVehiculoView;
    }

    public SmsVehiculo getMvehiculoView() {
        return MvehiculoView;
    }

    public void setMvehiculoView(SmsVehiculo MvehiculoView) {
        this.MvehiculoView = MvehiculoView;
    }

    public SmsCategoria getMcategoriaView() {
        return McategoriaView;
    }

    public void setMcategoriaView(SmsCategoria McategoriaView) {
        this.McategoriaView = McategoriaView;
    }

    public SmsCiudad getMciudadView() {
        return MciudadView;
    }

    public void setMciudadView(SmsCiudad MciudadView) {
        this.MciudadView = MciudadView;
    }

    public SmsProveedor getMproveedorView() {
        return MproveedorView;
    }

    public void setMproveedorView(SmsProveedor MproveedorView) {
        this.MproveedorView = MproveedorView;
    }

    public SmsReferencia getMrefenciaView() {
        return MrefenciaView;
    }

    public void setMrefenciaView(SmsReferencia MrefenciaView) {
        this.MrefenciaView = MrefenciaView;
    }

    public SmsEstadovehiculo getMestadoVehiculoView() {
        return MestadoVehiculoView;
    }

    public void setMestadoVehiculoView(SmsEstadovehiculo MestadoVehiculoView) {
        this.MestadoVehiculoView = MestadoVehiculoView;
    }

    public SmsUsuario getMusuarioView() {
        return MusuarioView;
    }

    public void setMusuarioView(SmsUsuario MusuarioView) {
        this.MusuarioView = MusuarioView;
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

        //limpiamos objetos
        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
        usuarioView = new SmsUsuario();
        estadoVehiculoView = new SmsEstadovehiculo();

        //Actualizamos la lista que muestra los vehiculos registrados en el sistema
        vehiculosListView = vehDao.mostrarVehiculo();
    }

    public String modificar() {
        //Ejecutamos la modificacion del vehiculo
        vehiculoController.modificarVehiculo(MproveedorView, McategoriaView, MusuarioView, MciudadView, MrefenciaView, MvehiculoView);

        //Consultamos el vehiculo recien registrado
        //MvehiculoView = vehiculoController.consultarVehiculo(MvehiculoView).get(0);
        MestadoVehiculoView.setSmsVehiculo(MvehiculoView); //Relacionamos el estado de vehiculo con el vehiculo.

        estadoVehiculoController.registrarEstVeh(MestadoVehiculoView);//Registramos el estado del vehiculo

        //Reiniciamos valores para las variables llamadas desde las vista
        modEstadoArchivo = "Foto sin subir";

        MrefenciaView = new SmsReferencia();
        McategoriaView = new SmsCategoria();
        MproveedorView = new SmsProveedor();
        MciudadView = new SmsCiudad();
        MvehiculoView = new SmsVehiculo();
        MusuarioView = new SmsUsuario();
        MestadoVehiculoView = new SmsEstadovehiculo();

        //Actualizamos la lista que muestra los vehiculos registrados en el sistema
        vehiculosListView = vehiculoController.cargarVehiculos();
        return "AdminPVehiculos";
    }

    public void eliminar() {
        //Eliminamos el vehiculo seleccionado
        vehiculoController.eliminarVehiculo(DVehiculoView);
        DVehiculoView = new SmsVehiculo();//Limpiamos el objeto que contenia el vehiculo a eliminar
        //Recargamos la lista de vehiculos
        vehiculosListView = vehDao.mostrarVehiculo();
    }
    
    //Metodos propios
    public String irModificarVehiculo() {
        //Asignamos a cada componente su correspondiente valor extraido del vehiculo seleccionado
        MciudadView = MvehiculoView.getSmsCiudad();
        MrefenciaView = MvehiculoView.getSmsReferencia();
        McategoriaView = MvehiculoView.getSmsCategoria();
        MproveedorView = MvehiculoView.getSmsProveedor();
        MusuarioView = MproveedorView.getSmsUsuario();

        //Consultamos el estado del vehiculo
        MestadoVehiculoView = estadoVehiculoController.consultarEstado(MvehiculoView).get(0);

        //Si el vehiculo tiene una foto asignada damos valores a nuestras variables para mostrar que foto esta asignada
        if (MvehiculoView.getVehFotoNombre() != null && MvehiculoView.getVehFotoRuta() != null) {
            modEstadoArchivo = "Foto subida:" + MvehiculoView.getVehFotoNombre();

        } else { //En caso de no existir fotografia, indicamos en la vista la posibilidad de subir una foto para el vehiculo
            modEstadoArchivo = "Foto sin subir";
        }
        return "AdminPEVehiculo";
    }

    public String regresar() {
        MvehiculoView = new SmsVehiculo();
        McategoriaView = new SmsCategoria();
        MciudadView = new SmsCiudad();
        MproveedorView = new SmsProveedor();
        MrefenciaView = new SmsReferencia();
        MestadoVehiculoView = new SmsEstadovehiculo();
        MusuarioView = new SmsUsuario();

        modEstadoArchivo = "Foto sin subir";
        return "AdminPVehiculos";
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
                MvehiculoView.setVehFotoNombre(uploadedPhoto.getFileName());
                MvehiculoView.setVehFotoRuta(map.get("url") + uploadedPhoto.getFileName());

                estadoArchivo = "Foto Subida con exito";
                modEstadoArchivo = "Foto actualizada con exito";
            }

            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Su foto (" + uploadedPhoto.getFileName() + ")  se ha guardado con exito.", ""));
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
