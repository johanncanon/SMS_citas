/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IVehiculoDao;
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
    
    protected SmsVehiculo vehiculo;
    protected List<SmsVehiculo> vehiculos;
    
    
    public VehiculoBean() {
    }

    public SmsVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(SmsVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<SmsVehiculo> getVehiculos() {
        IVehiculoDao vehDao = new ImpVehiculoDao();
        vehiculos = vehDao.mostrarVehiculo();
        return vehiculos;
    }

    public void setVehiculos(List<SmsVehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    //Definicion de metodos
    
    
    
    
    
}
