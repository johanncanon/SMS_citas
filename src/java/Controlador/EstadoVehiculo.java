/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IEstadoVehiculoDao;
import DAO.ImpEstadoVehiculoDao;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsVehiculo;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class EstadoVehiculo {

    private SmsEstadovehiculo estVehiculo;
    private SmsVehiculo vehiculo;
    private List<SmsEstadovehiculo> estVehiculos;

    public EstadoVehiculo() {
        estVehiculo = new SmsEstadovehiculo();
        vehiculo = new SmsVehiculo();
    }

    public SmsEstadovehiculo getEstVehiculo() {
        return estVehiculo;
    }

    public void setEstVehiculo(SmsEstadovehiculo estVehiculo) {
        this.estVehiculo = estVehiculo;
    }

    public SmsVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(SmsVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<SmsEstadovehiculo> getEstVehiculos() {
        IEstadoVehiculoDao linkDao = new ImpEstadoVehiculoDao();
        estVehiculos = linkDao.mostrarEstadoVehiculo();
        return estVehiculos;
    }

    public void setEstVehiculos(List<SmsEstadovehiculo> estVehiculos) {
        this.estVehiculos = estVehiculos;
    }

    /**
     * ************************** METODOS DEL CRUD ****************************
     */
    
    public void registrarEstVeh(SmsEstadovehiculo estV){
        estVehiculo = estV;
        IEstadoVehiculoDao linkDao = new ImpEstadoVehiculoDao();
        linkDao.registrarEstadoVehiculo(estVehiculo);
    }
    
    public void modificarEstVeh(SmsEstadovehiculo estV){
        estVehiculo = estV;
        IEstadoVehiculoDao linkDao = new ImpEstadoVehiculoDao();
        linkDao.modificarEstadoVehiculo(estVehiculo);
    }
    
    public void eliminarEstVeh(SmsEstadovehiculo estV){
        estVehiculo = estV;
        IEstadoVehiculoDao linkDao = new ImpEstadoVehiculoDao();
        linkDao.eliminarEstadoVehiculo(estVehiculo);
    }
    
    
}
