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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class EstadoVehiculo {

    private SmsEstadovehiculo estVehiculo;
    private SmsVehiculo vehiculo;
    private List<SmsEstadovehiculo> estVehiculos;

    IEstadoVehiculoDao estVehDao = new ImpEstadoVehiculoDao();

    public EstadoVehiculo() {
        estVehiculo = new SmsEstadovehiculo();
        vehiculo = new SmsVehiculo();
    }

    public void registrarEstVeh(SmsEstadovehiculo estV) {
        estVehiculo = estV;
        //Obtenemos fecha actual en la cual se registra el estado del vehiculo
        java.util.Date fecha = new Date();
        estVehiculo.setFechaEstadoVehiculo(fecha);
        estVehDao.registrarEstadoVehiculo(estVehiculo);
    }

    public void modificarEstVeh(SmsEstadovehiculo estV) {
        estVehiculo = estV;
        estVehDao.modificarEstadoVehiculo(estVehiculo);
    }

    public void eliminarEstVeh(SmsEstadovehiculo estV) {
        estVehiculo = estV;
        estVehDao.eliminarEstadoVehiculo(estVehiculo);
    }

    public List<SmsEstadovehiculo> consultarEstado(SmsVehiculo v) {
        vehiculo = v;
        estVehiculos = new ArrayList<>();
        estVehiculos = estVehDao.consultarEstadoVehiculo(vehiculo);
        return estVehiculos;
    }

}
