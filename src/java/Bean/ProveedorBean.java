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

/**
 *
 * @author Desarrollo_Planit
 */
public class ProveedorBean implements Serializable {

    //Objetos necesarios para vista
    protected SmsUsuario usuarioView;
    protected SmsUsuario auxUsuarioView;
    protected SmsProveedor proveedorView;
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;

    //Relacion con el controlador
    protected Proveedor proveedor;   

    //lista de Id de proveedor
    private List<SmsProveedor> proveedoresView;
    private List<String> listaProveedoresView;

    public ProveedorBean() {
        usuarioView = new SmsUsuario();
        auxUsuarioView = new SmsUsuario();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        rolView = new SmsRol();
        proveedor = new Proveedor();       
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

    public SmsUsuario getAuxUsuarioView() {
        return auxUsuarioView;
    }

    public void setAuxUsuarioView(SmsUsuario auxUsuarioView) {
        this.auxUsuarioView = auxUsuarioView;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    /**
     * ********************* Lista de Strings    ******************************
     * @return 
     */
    public List<SmsProveedor> getProveedoresView() {
        IProveedorDao linkDao = new ImpProveedorDao();
        proveedoresView = linkDao.mostrarProveedores();
        return proveedoresView;
    }

    public void setProveedoresView(List<SmsProveedor> proveedoresView) {
        this.proveedoresView = proveedoresView;
    }

    public List<String> getListaProveedoresView() {
        listaProveedoresView = new ArrayList<>();
        IProveedorDao linkDao = new ImpProveedorDao();
        proveedoresView = linkDao.mostrarProveedores();
        
        for (int i = 0; i < proveedoresView.size();i++){
            listaProveedoresView.add(proveedoresView.get(i).getSmsUsuario().getUsuarioNombre());
        }
        return listaProveedoresView;
    }

    public void setListaProveedoresView(List<String> listaProveedoresView) {
        this.listaProveedoresView = listaProveedoresView;
    }

    /**
     * ********************* Lista de Strings    ******************************
     */
    
    //Metodos    
    public void registrar() {
        rolView.setRolNombre("Proveedor");
        proveedor.registrarUsuario(usuarioView, ciudadView, rolView);
        proveedor.registrarProveedor(proveedorView, usuarioView);
       
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        proveedorView = new SmsProveedor();        
    }
    

    public void modificar() {
        proveedor.modificarUsuario(usuarioView, ciudadView);
        proveedor.modificarProveedor(proveedorView, usuarioView);
        proveedorView = new SmsProveedor();
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
    }

    public void eliminar() {
        proveedor.eliminarProveedor(proveedorView);
        proveedor.eliminarUsuario(usuarioView);
        proveedorView = new SmsProveedor();
        usuarioView = new SmsUsuario();
    }

}
