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

    public Vehiculo() {
        vehiculo = new SmsVehiculo();        
    }

  
  
    //Getters & Setters
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
    
    //Metodos
    public void modficarVehiculo(SmsCategoria c, SmsProveedor p, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        SmsCategoria categoria = c;
        SmsProveedor proveedor = p;
        SmsCiudad ciudad = ci;
        SmsReferencia referencia = r;
        vehiculo = v;

        //Consulta categoria
        ICategoriaDao cateDao = new ImpCategoriaDao();
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);

        //Consulta proveedor
        IProveedorDao proDao = new ImpProveedorDao();
        proveedor = proDao.consultarProveedores(proveedor).get(0);
        vehiculo.setSmsProveedor(proveedor);

        //Consulta ciudad
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);

        //Consulta referencia
        IReferenciaDao refDao = new ImpReferenciaDao();
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);

        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.modificarVehiculo(vehiculo);

    }

    public void eliminarVehiculo(SmsVehiculo v) {
        vehiculo = v;

        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.eliminarVehiculo(vehiculo);

    }

    public void registrarVehiculo(SmsCategoria c, SmsProveedor p, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        SmsCategoria categoria = c;
        SmsProveedor proveedor = p;
        SmsCiudad ciudad = ci;
        SmsReferencia referencia = r;
        vehiculo = v;

        //Consulta categoria
        ICategoriaDao cateDao = new ImpCategoriaDao();
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);

        //Consulta proveedor
        IProveedorDao proDao = new ImpProveedorDao();
        proveedor = proDao.consultarProveedores(proveedor).get(0);
        vehiculo.setSmsProveedor(proveedor);

        //Consulta ciudad
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);

        //Consulta referencia
        IReferenciaDao refDao = new ImpReferenciaDao();
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);
        
        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.registrarVehiculo(vehiculo);
    }

}
