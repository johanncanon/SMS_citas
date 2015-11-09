/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
    
    
    
}