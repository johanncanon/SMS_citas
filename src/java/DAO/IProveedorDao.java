/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsProveedor;
import Modelo.SmsUsuario;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IProveedorDao {
    
   //Definicion de metodos CRUD
    public List<SmsProveedor> consultarProveedorUsuario(SmsUsuario usuario);
    public List<SmsProveedor> consultarProveedor(SmsProveedor proveedor);   
    public List<SmsProveedor> mostrarProveedores();
    public void registrarProveedor(SmsProveedor proveedor);
    public void modificarProveedor(SmsProveedor proveedor);
    public void eliminarProveedor(SmsProveedor proveedor);
    public List<SmsProveedor> filtrarProveedor(String dato);
}
