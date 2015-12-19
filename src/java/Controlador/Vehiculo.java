/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsVehiculo;
import DAO.ICategoriaDao;
import DAO.ICiudadDao;
import DAO.IEstadoVehiculoDao;
import DAO.IProveedorDao;
import DAO.IReferenciaDao;
import DAO.IUsuarioDao;
import DAO.IVehiculoDao;
import DAO.ImpCategoriaDao;
import DAO.ImpCiudadDao;
import DAO.ImpEstadoVehiculoDao;
import DAO.ImpProveedorDao;
import DAO.ImpReferenciaDao;
import DAO.ImpUsuarioDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsAgenda;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
import Modelo.SmsUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Vehiculo {

    private SmsVehiculo vehiculo;
    private SmsEstadovehiculo estado_Vehiculo;
    private List<SmsVehiculo> vehiculos;
    private List<SmsEstadovehiculo> estado_Vehiculos;


    public Vehiculo() {
        vehiculo = new SmsVehiculo();
        estado_Vehiculo = new SmsEstadovehiculo();
    }

    //Getters & Setters
    public SmsVehiculo getVehiculo() {
        return vehiculo;
    }
    
    public void setEstado_Vehiculo(SmsEstadovehiculo estado_Vehiculo) {
        this.estado_Vehiculo = estado_Vehiculo;
    }

    public void setEstado_Vehiculos(List<SmsEstadovehiculo> estado_Vehiculos) {
        this.estado_Vehiculos = estado_Vehiculos;
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
    public void modificarVehiculo(SmsCategoria c, SmsUsuario u, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        SmsCategoria categoria = c;
        SmsUsuario usuario = u;
        SmsProveedor proveedor;
        SmsCiudad ciudad = ci;
        SmsReferencia referencia = r;
        vehiculo = v;

        IUsuarioDao usuDao = new ImpUsuarioDao();
        usuario = usuDao.consultarUsuario(usuario).get(0);

        //Consulta categoria
        ICategoriaDao cateDao = new ImpCategoriaDao();
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);

        //Consulta proveedor
        IProveedorDao provDao = new ImpProveedorDao();
        proveedor = provDao.consultarProveedores(usuario).get(0);
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

    public void registrarVehiculo(SmsCategoria c, SmsUsuario u, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        SmsCategoria categoria = c;
        SmsUsuario usuario = u;
        SmsProveedor proveedor;
        SmsCiudad ciudad = ci;
        SmsReferencia referencia = r;
        vehiculo = v;

        IUsuarioDao usuDao = new ImpUsuarioDao();
        usuario = usuDao.consultarUsuario(usuario).get(0);

        //Consulta categoria
        ICategoriaDao cateDao = new ImpCategoriaDao();
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);

        //Consulta proveedor
        IProveedorDao provDao = new ImpProveedorDao();
        proveedor = provDao.consultarProveedores(usuario).get(0);
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

    public void eliminarVehiculo(SmsVehiculo v) {
        vehiculo = v;
        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.eliminarVehiculo(vehiculo);
    }

    public List<SmsVehiculo> consultarVehiculo(SmsVehiculo vehiculo) {
        vehiculos = new ArrayList<>();
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.consultarVehiculo(vehiculo);
        return vehiculos;
    }

    public List<SmsVehiculo> cargarVehiculos() {
        vehiculos = new ArrayList<>();
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.mostrarVehiculo();
        return vehiculos;
    }

    public List<SmsVehiculo> consultarVehiculosDisponible(SmsAgenda a, SmsCiudad c) {
        vehiculos = new ArrayList<>();
        SmsAgenda agenda = a;
        SmsCiudad ciudad = c;
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.consultarVehiculosDisponibles(agenda, ciudad);
        return vehiculos;
    }

    public List<SmsVehiculo> consultarVehiculosCiudad(SmsCiudad c) {
        vehiculos = new ArrayList<>();
        SmsCiudad ciudad = c;
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.consultarVehiculosCiudad(ciudad);
        return vehiculos;
    }
    
    /*  METODO PARA SACAR LOS DATOS DEL ESTADO VEHICULO  */
    
    public List<SmsEstadovehiculo> consultarEstado_Vehiculos(SmsEstadovehiculo estVehiculo){
        
        vehiculos = new ArrayList<>();
        estado_Vehiculos = new ArrayList<>();
        SmsEstadovehiculo estadoVehoculo = estVehiculo;
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculo = linkDao.consultarVehiculoEstadoVehiculo(estadoVehoculo).get(0);
        
        for (SmsEstadovehiculo est_Veh: vehiculo.getSmsEstadovehiculos()) {            
            estado_Vehiculos.add(est_Veh);
            }
         
        return estado_Vehiculos;
    }
    
}
