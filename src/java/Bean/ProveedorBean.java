/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Proveedor;
import DAO.IProveedorDao;
import DAO.ImpProveedorDao;
import Modelo.SmsCiudad;
import Modelo.SmsProveedor;
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
public class ProveedorBean implements Serializable {

    //Objetos necesarios para vista
    protected SmsUsuario usuarioView;
    protected SmsProveedor proveedorView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Relacion con el controlador
    protected Proveedor proveedor;

    //lista de Id de proveedor
    private List<SmsProveedor> proveedoresListView;
    private List<String> nombresProveedoresView;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public ProveedorBean() {
        usuarioView = new SmsUsuario();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        proveedor = new Proveedor();

        buscar = null;
        estado = 0;
        nombre = "Registrar Proveedor";
    }

    @PostConstruct
    public void init() {
        proveedoresListView = proveedor.cargarProveedor();
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<SmsProveedor> getProveedoresListView() {
        return proveedoresListView;
    }

    public void setProveedoresListView(List<SmsProveedor> proveedoresListView) {
        this.proveedoresListView = proveedoresListView;
    }

    public List<String> getNombresProveedoresView() {
        nombresProveedoresView = new ArrayList<>();
        IProveedorDao linkDao = new ImpProveedorDao();
        proveedoresListView = linkDao.mostrarProveedores();

        for (int i = 0; i < proveedoresListView.size(); i++) {
            nombresProveedoresView.add(proveedoresListView.get(i).getSmsUsuario().getUsuarioNombre());
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

    //Metodos    
    public void registrar() {
        rolView.setRolNombre("Proveedor");
        proveedor.registrarUsuario(usuarioView, ciudadView, rolView);
        proveedor.registrarProveedor(proveedorView, usuarioView);

        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        proveedorView = new SmsProveedor();
        proveedoresListView = proveedor.cargarProveedor();
    }

    public void modificar() {
        proveedor.modificarUsuario(usuarioView, ciudadView);
        proveedor.modificarProveedor(proveedorView, usuarioView);
        
        proveedorView = new SmsProveedor();
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        proveedoresListView = proveedor.cargarProveedor();
    }

    public void eliminar() {
        proveedor.eliminarProveedor(proveedorView);
        proveedor.eliminarUsuario(usuarioView);
        proveedorView = new SmsProveedor();
        usuarioView = new SmsUsuario();
        proveedoresListView = proveedor.cargarProveedor();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Proveedor";
            usuarioView = proveedorView.getSmsUsuario();
            usuarioView = proveedor.consultarUsuario(usuarioView).get(0);
            ciudadView = usuarioView.getSmsCiudad();
        } else if (estado == 2) {
            nombre = "Eliminar Proveedor";        
            usuarioView = proveedorView.getSmsUsuario();
            usuarioView = proveedor.consultarUsuario(usuarioView).get(0);
            ciudadView = usuarioView.getSmsCiudad();
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

}
