/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Archivos;
import Controlador.Cliente;
import Controlador.Rol;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ClienteBean implements Serializable {

    //Objetos necesario para vista
    protected SmsUsuario clienteView;
    protected List<SmsUsuario> clientesListView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Relacion con el controlador
    protected Cliente clienteController;
    protected Archivos fileController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    private Boolean habilitarSubir;
    private String subirArchivo;
    private String estadoArchivo;
    private UploadedFile archivo;

    public ClienteBean() {
        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        clienteController = new Cliente();
        fileController = new Archivos();

        buscar = null;
        estado = 0;
        nombre = "Registrar Cliente";
        habilitarSubir = false;
        subirArchivo = "Subir fotografia";
        estadoArchivo = "Foto sin subir";
    }

    @PostConstruct
    public void init() {
        clientesListView = clienteController.consultarClientes();
    }

    //Getters & Setters
    public SmsUsuario getClienteView() {
        return clienteView;
    }

    public void setClienteView(SmsUsuario clienteView) {
        this.clienteView = clienteView;
    }

    public List<SmsUsuario> getClientesListView() {
        return clientesListView;
    }

    public void setClientesListView(List<SmsUsuario> clientesListView) {
        this.clientesListView = clientesListView;
    }

    public Cliente getClienteController() {
        return clienteController;
    }

    public void setClienteController(Cliente clienteController) {
        this.clienteController = clienteController;
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

    //Metodos     
    public void registrar() {
        rolView.setRolNombre("Cliente");
        clienteController.registrarUsuario(clienteView, ciudadView, rolView);
        clientesListView = clienteController.consultarClientes();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }

    public void modificar() {
        rolView.setRolNombre("Cliente");
        clienteController.modificarUsuario(clienteView, ciudadView, rolView);
        clientesListView = clienteController.consultarClientes();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }

    public void eliminar() {
        clienteController.eliminarUsuario(clienteView);
        clientesListView = clienteController.consultarClientes();

        estadoArchivo = "Foto sin subir";
        subirArchivo = "Subir Fotografia";
        habilitarSubir = false;

        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
    }
    
     public void filtrar() {
        clientesListView = new ArrayList<>();
        if (buscar == null) {
            clientesListView = clienteController.consultarClientes();
        } else {
           clientesListView = clienteController.filtrarClientes(buscar);
        }
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;       
        
        if (estado == 1) {//MODIFICACION
            nombre = "Modificar Cliente";
            ciudadView = clienteView.getSmsCiudad();
            rolView = clienteView.getSmsRol();

            if (clienteView.getUsuarioFotoNombre() != null && clienteView.getUsuarioFotoRuta() != null) {
                subirArchivo = "Modificar Fotografia";
                estadoArchivo = "Foto subida:" + clienteView.getUsuarioFotoNombre();
                habilitarSubir = false;
            } else {
                subirArchivo = "Subir Fotografia";
                habilitarSubir = false;
            }
        } else if (estado == 2) {//ELIMINACION
            nombre = "Eliminar Cliente";
            ciudadView = clienteView.getSmsCiudad();
            rolView = clienteView.getSmsRol();

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
            nombre = "Registrar Cliente";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Cliente";
        }
    }

    //Subida de archivos
    public void upload() throws IOException {
        FacesMessage message = new FacesMessage();
        if (null != getArchivo()) {
            fileController.UploadFile(IOUtils.toByteArray(getArchivo().getInputstream()), getArchivo().getFileName());
            clienteView.setUsuarioFotoNombre(getArchivo().getFileName());
            clienteView.setUsuarioFotoRuta(fileController.getFilePath());
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
