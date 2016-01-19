/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
    //METODO PARA SACAR LAS CIUDADES DE LA RESERVACION DE LOS CLIENTES
    public List<SmsReservacion> mostrarReservacionHecha(SmsUsuario usuarioID);    
}
