/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsVehiculo;
import DAO.ICategoriaDao;
import DAO.ICiudadDao;
import DAO.IVehiculoDao;
import DAO.ImpCategoriaDao;
import DAO.ImpCiudadDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
import java.util.List;


/**
 *
 * @author Desarrollo_Planit
 */
public class Vehiculo {
    private SmsVehiculo vehiculo; 
    private List<SmsVehiculo> vehiculos; 
    private SmsCategoria caetgoria;
    private SmsProveedor proveedor;
    private SmsCiudad ciudad;
    private SmsReferencia referencia;
    

    public Vehiculo(){
        vehiculo = new SmsVehiculo();
        caetgoria = new SmsCategoria();
        ciudad = new SmsCiudad();
        proveedor = new SmsProveedor();
        referencia = new SmsReferencia();
    }

 /*
  
    GETTERs y SETTERS ****************************************************/   
    
    public SmsVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(SmsVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<SmsVehiculo> getVehiculos() {
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.mostrarVehiculo();        
        return vehiculos;
    }

    public void setVehiculos(List<SmsVehiculo> vehiculos) {
        this.vehiculos = vehiculos;     
    }

    public SmsCategoria getCaetgoria() {
        return caetgoria;
    }

    public void setCaetgoria(SmsCategoria caetgoria) {
        this.caetgoria = caetgoria;
    }

    public SmsProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(SmsProveedor proveedor) {
        this.proveedor = proveedor;
    }

    public SmsCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(SmsCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public SmsReferencia getReferencia() {
        return referencia;
    }

    public void setReferencia(SmsReferencia referencia) {
        this.referencia = referencia;
    }

    
    
 /**********************   METODOS QUE SE CONECTAN CON DAOS   **********************************/   
    
    public void modficarVehiculo (Categoria cat, Proveedor prov, Ciudad ciu, Referencia ref, SmsVehiculo vehiculo){
     
    }
    
    public void eliminarVehiculo (Categoria cat, Proveedor prov, Ciudad ciu, Referencia ref, SmsVehiculo vehiculo){
        
    }
    
    public void regisrtarVehiculo (SmsCategoria cat, SmsProveedor prov, SmsCiudad ciu, SmsReferencia ref, SmsVehiculo vehiculo){
        this.caetgoria=cat;
        this.proveedor=prov;
        this.ciudad=ciu;
        this.referencia=ref;
        this.vehiculo=vehiculo;
        
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);
        /*******************  Categorias  ********************************/
        ICategoriaDao categoriaDao = new ImpCategoriaDao();
        caetgoria = categoriaDao.consultarCategorias(caetgoria).get(0);
        vehiculo.setSmsCategoria(caetgoria);
        /*******************  Proveedor  ********************************/
        /*******************  Categorias  ********************************/
        /*******************  Categorias  ********************************/
        
        
                
    }
    
    
}
