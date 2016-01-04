/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Upload;
import Controlador.Cliente;
import Controlador.MD5;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ClienteBean implements Serializable {

    //Objetos necesario para vista
    protected SmsUsuario clienteView;
    protected SmsUsuario modClienteView;
    protected List<SmsUsuario> clientesListView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Relacion con el controlador
    protected Cliente clienteController;
    protected Upload fileController;

    //Variables    
    private String buscar;
    private Boolean habilitarEditarSesion;
    private Boolean habilitarRegistroSesion;
    private String pass;
    
    public ClienteBean() {
        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        clienteController = new Cliente();
        fileController = new Upload();
        modClienteView = new SmsUsuario();        
        buscar = null;
        habilitarEditarSesion = false;
        habilitarRegistroSesion = false;
        }

    @PostConstruct
    public void init() {
        clientesListView = new ArrayList<>();
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

    public Upload getFileController() {
        return fileController;
    }

    public void setFileController(Upload fileController) {
        this.fileController = fileController;
    }
    
    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }    
  
    public SmsUsuario getModClienteView() {
        return modClienteView;
    }

    public void setModClienteView(SmsUsuario modClienteView) {
        this.modClienteView = modClienteView;
    }

    public Boolean getHabilitarEditarSesion() {
        return habilitarEditarSesion;
    }

    public void setHabilitarEditarSesion(Boolean habilitarEditarSesion) {
        this.habilitarEditarSesion = habilitarEditarSesion;
    }

    public Boolean getHabilitarRegistroSesion() {
        return habilitarRegistroSesion;
    }

    public void setHabilitarRegistroSesion(Boolean habilitarRegistroSesion) {
        this.habilitarRegistroSesion = habilitarRegistroSesion;
    }

    
    //Metodos     
    public void registrar() {
        //asignamos un rol al usuario
        rolView.setRolNombre("Cliente");
        
        //asignamos al usuario la imagen de perfil default
        clienteView.setUsuarioFotoRuta(fileController.getPathDefaultUsuario());
        
        //registramos el usuario y recargamos la lista de clientes
        clienteController.registrarUsuario(clienteView, ciudadView, rolView);
        clientesListView = clienteController.consultarClientes();

        //limpiamos objetos
        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modClienteView = new SmsUsuario();
    }

    public String modificar() {
        
        MD5 md = new MD5();
        
        //se asigna un rol al usuario
        rolView.setRolNombre("Cliente");
                
        if(habilitarEditarSesion){ // en caso de modificar las contraseñas estas se encriptan de nuevo
            modClienteView.setUsuarioPassword(md.getMD5(modClienteView.getUsuarioPassword()));
            modClienteView.setUsuarioRememberToken(md.getMD5(modClienteView.getUsuarioRememberToken()));
        }
        
        //Se modifica el usuario y se recarga la lista de clientes
        clienteController.modificarUsuario(modClienteView, ciudadView, rolView);
        clientesListView = clienteController.consultarClientes();
       
        //se limpian objetos
        clienteView = new SmsUsuario();
        modClienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        habilitarEditarSesion = false;
        
        String ruta = "RAdminPCliente";
        return ruta;
    }

    public void eliminar() {       
        clienteController.eliminarUsuario(clienteView);
        clientesListView = clienteController.consultarClientes();

        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        modClienteView = new SmsUsuario();
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
    public String irModificarCliente() {
        ciudadView = modClienteView.getSmsCiudad();
        rolView = modClienteView.getSmsRol();      
        
        String ruta = "AdminPECliente";
        return ruta;
    }

    public String regresar() {               
        modClienteView = new SmsUsuario();        
        habilitarEditarSesion = false;
        String ruta = "AdminPCliente";
        return ruta;
    }
    
    public void habilitarEdicion(){
        habilitarEditarSesion = true;
        pass = modClienteView.getUsuarioPassword();
        modClienteView.setUsuarioPassword(null);
        modClienteView.setUsuarioRememberToken(null);
    }

    public void deshabilitarEdicion(){
        habilitarEditarSesion = false;
        modClienteView.setUsuarioPassword(pass);
        modClienteView.setUsuarioRememberToken(pass);
    }
    
    public void habilitarRegistro() {
        habilitarRegistroSesion = true;
    }

    public void deshabilitarRegistro() {
        habilitarRegistroSesion = false;
    }
    

    

}
