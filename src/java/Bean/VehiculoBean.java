/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IVehiculoDao;
import Controlador.Vehiculo;
import DAO.ImpVehiculoDao;
import Modelo.SmsVehiculo;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class VehiculoBean {

    /**
     * Creates a new instance of VehiculoBean
     */
    private Vehiculo vehiculo;
    protected SmsVehiculo vehiculoView;
    protected List<SmsVehiculo> vehiculosView;

    public VehiculoBean() {
        vehiculo = new Vehiculo();
        vehiculoView = new SmsVehiculo();
    }

    public SmsVehiculo getVehiculoView() {
        return vehiculoView;
    }

    public void setVehiculoView(SmsVehiculo veh) {
        this.vehiculoView = veh;
    }

    public List<SmsVehiculo> getVehiculosView() {
        IVehiculoDao vehDao = new ImpVehiculoDao();
        vehiculosView = vehDao.mostrarVehiculo();
        return vehiculosView;
    }

    public void setVehiculosView(List<SmsVehiculo> veh) {
        this.vehiculosView = veh;
    }

    //Definicion de metodos
}
