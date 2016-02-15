/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsAgenda;
import Modelo.SmsEmpleado;
import Modelo.SmsReservacion;
import Modelo.SmsUsuario;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IReservacionDao {
    
    //Definicion de metodos CRUD
    public List<SmsReservacion> mostrarReservaciones();
    public void registrarReservacion(SmsReservacion reservacion);
    public void modificarReservacion(SmsReservacion reservacion);
    public void eliminarReservacion(SmsReservacion reservacion); 
    public List<SmsReservacion> consultarReservacionId(SmsReservacion reserva);
    public List<SmsReservacion> consultarReserva(SmsReservacion reserva); 
    public List<SmsReservacion> consultarReservacionSinEmpleado(SmsReservacion reserva); 
    public List<SmsReservacion> mostrarReservacionCliente(SmsUsuario usuario);
    public List<SmsReservacion> mostrarReservacionConductores(SmsEmpleado conductor);
    
}
