/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsVehiculo;
import DAO.ICategoriaDao;
import DAO.ICiudadDao;
import DAO.IProveedorDao;
import DAO.IReferenciaDao;
import DAO.IVehiculoDao;
import DAO.ImpCategoriaDao;
import DAO.ImpCiudadDao;
import DAO.ImpProveedorDao;
import DAO.ImpReferenciaDao;
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
    
    public void modficarVehiculo (SmsCategoria cat, SmsProveedor prov, SmsCiudad ciu, SmsReferencia ref, SmsVehiculo veh){
        caetgoria = cat;
        proveedor = prov;
        ciudad = ciu;
        referencia = ref;
        vehiculo = veh;
        
        /*************************************************************/
        ICategoriaDao catDao = new ImpCategoriaDao();
        caetgoria = catDao.consultarCategorias(caetgoria).get(0);
        vehiculo.setSmsCategoria(caetgoria);
        /*************************************************************/
        IProveedorDao provDao = new ImpProveedorDao();
        proveedor= provDao.consultarProveedores(proveedor).get(0);
        vehiculo.setSmsProveedor(proveedor);
        /*************************************************************/
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);
        /*************************************************************/
        IReferenciaDao refdao = new ImpReferenciaDao();
        referencia = refdao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);
        /*************************************************************/
        
        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.modificarVehiculo(vehiculo);
        
    }
    
    public void eliminarVehiculo (SmsCategoria cat, SmsProveedor prov, SmsCiudad ciu, SmsReferencia ref, SmsVehiculo veh){
        caetgoria = cat;
        proveedor = prov;
        ciudad = ciu;
        referencia = ref;
        vehiculo = veh;
        
        /********************************************************************/
        ICategoriaDao cateDao = new ImpCategoriaDao();
        caetgoria = cateDao.consultarCategorias(caetgoria).get(0);
        vehiculo.setSmsCategoria(caetgoria);
        /********************************************************************/
        IProveedorDao proDao = new ImpProveedorDao();
        proveedor = proDao.consultarProveedores(proveedor).get(0);
        vehiculo.setSmsProveedor(proveedor);
        /********************************************************************/
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);
        /********************************************************************/
        IReferenciaDao refDao = new ImpReferenciaDao();
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);
        /********************************************************************/
        
        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.eliminarVehiculo(vehiculo);
        
    }
    
    public void regisrtarVehiculo (SmsCategoria cat, SmsProveedor prov, SmsCiudad ciu, SmsReferencia ref, SmsVehiculo veh){
        caetgoria=cat;
        proveedor=prov;
        ciudad=ciu;
        referencia=ref;
        vehiculo=veh;
        
        /*******************  Ciudad  ********************************/
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);
        /*******************  Categorias  ********************************/
        ICategoriaDao categoriaDao = new ImpCategoriaDao();
        caetgoria = categoriaDao.consultarCategorias(caetgoria).get(0);
        vehiculo.setSmsCategoria(caetgoria);
        /*******************  Proveedor  ********************************/
        IProveedorDao proveedorDao = new ImpProveedorDao();
        proveedor = proveedorDao.consultarProveedores(proveedor).get(0);
        vehiculo.setSmsProveedor(proveedor);
        /*******************  Referencia  ********************************/
        IReferenciaDao referenciaDao = new ImpReferenciaDao();
        referencia = referenciaDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);
        /*******************  Vehiculo  ********************************/
        
        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.registrarVehiculo(vehiculo);
                
    }
    
    
}
