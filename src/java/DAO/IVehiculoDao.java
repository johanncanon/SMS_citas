/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsVehiculo;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IVehiculoDao {
    
    public List<SmsVehiculo> mostrarVehiculo();
    public void registrarVehiculo(SmsVehiculo vehiculo);
    public void modificarVehiculo(SmsVehiculo vehiculo);
    public void eliminarVehiculo(SmsVehiculo vehiculo);
    public List<SmsVehiculo> consultarVehiculo(SmsVehiculo vehiculo);
    public List<SmsVehiculo> consultarVehiculosDisponibles(String fechaInicio, String fechaLlegada, String horaInicio, String horaLlegada , String ciudad);
    public List<SmsVehiculo> consultarVehiculosCiudad(SmsCiudad ciudad);
    public List<SmsVehiculo> consultarVehiculoEstadoVehiculo(SmsEstadovehiculo estadoVeh);

    
        
}
