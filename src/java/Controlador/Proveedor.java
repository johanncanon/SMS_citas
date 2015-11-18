/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IProveedorDao;
import DAO.ImpProveedorDao;
import Modelo.SmsProveedor;
import Modelo.SmsUsuario;

/**
 *
 * @author Desarrollo_Planit
 */
public class Proveedor extends Usuario{

    SmsProveedor proveedor;
    
    public Proveedor() {
        super();
        proveedor = new SmsProveedor();
    }

    public SmsProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(SmsProveedor proveedor) {
        
        this.proveedor = proveedor;
    }
    
    //Metodos de la clase
    public void registrarProveedor(SmsProveedor p, SmsUsuario u){
    usuario = u; proveedor = p;
    proveedor.setSmsUsuario(usuario);
    IProveedorDao provedorDao= new  ImpProveedorDao();
    provedorDao.registrarProveedor(proveedor);
    }
    
    public void modificarProveedor(SmsProveedor p, SmsUsuario u){
    usuario = u; proveedor = p;
    proveedor.setSmsUsuario(usuario);
    IProveedorDao provedorDao= new  ImpProveedorDao();
    provedorDao.registrarProveedor(proveedor);
    }
    
    public void eliminarProveedor(SmsProveedor p){
    proveedor = p;
    IProveedorDao provedorDao= new  ImpProveedorDao();
    provedorDao.eliminarProveedor(proveedor);    
    }
    
    
    
    
}
