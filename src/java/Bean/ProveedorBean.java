/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Proveedor;
import Modelo.SmsCiudad;
import Modelo.SmsProveedor;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;

/**
 *
 * @author Desarrollo_Planit
 */
public class ProveedorBean implements Serializable{

    //Objetos necesarios para vista
    protected SmsUsuario usuarioView;
    protected SmsUsuario auxUsuarioView;
    protected SmsProveedor proveedorView;    
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    
    //Relacion con el controlador
    protected Proveedor proveedor;
    
    //Control de componentes
    protected boolean habilitado;
    
    public ProveedorBean() {
    usuarioView = new SmsUsuario();
    auxUsuarioView = new SmsUsuario();
    proveedorView = new SmsProveedor();
    ciudadView = new SmsCiudad();
    rolView = new SmsRol();
    proveedor = new Proveedor();
    habilitado = true;
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

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
    //Metodos    
    public void registrar(){        
    proveedor.registrarUsuario(usuarioView, ciudadView);
    proveedor.registrarProveedor(proveedorView, usuarioView);
    auxUsuarioView = usuarioView;
    usuarioView = new SmsUsuario();
    ciudadView = new SmsCiudad();
    proveedorView = new SmsProveedor();
    habilitado = false;
    }
    
    public void registrarCuenta(){
    rolView.setRolNombre("Proveedor");
    proveedor.registrarDatosSesion(usuarioView, rolView);
    usuarioView = new SmsUsuario();
    rolView = new SmsRol();
    habilitado = true;
    }
    
    public void modificar(){
    proveedor.modificarUsuario(usuarioView, ciudadView);
    proveedor.modificarProveedor(proveedorView, usuarioView);
    proveedorView = new SmsProveedor();
    usuarioView = new SmsUsuario();
    ciudadView = new SmsCiudad();            
    }
    
    public void eliminar(){
    proveedor.eliminarProveedor(proveedorView);
    proveedor.eliminarUsuario(usuarioView);
    proveedorView = new SmsProveedor();
    usuarioView = new SmsUsuario();    
    }
    
    
    
}
