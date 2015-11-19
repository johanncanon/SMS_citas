/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IEmpleadoDao;
import DAO.ImpEmpleadoDao;
import Modelo.SmsEmpleado;
import Modelo.SmsHojavida;
import Modelo.SmsUsuario;

/**
 *
 * @author Desarrollo_Planit
 */
public class Empleado extends Usuario {

    SmsEmpleado empleado;

    //Relaciones con otras clases
    SmsHojavida hojaVida;

    public Empleado() {
        super();
        hojaVida = new SmsHojavida();
        empleado = new SmsEmpleado();
    }

    //Getters & setters
    public SmsEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(SmsEmpleado empleado) {
        this.empleado = empleado;
    }

    public SmsHojavida getHojaVida() {
        return hojaVida;
    }

    public void setHojaVida(SmsHojavida hojaVida) {
        this.hojaVida = hojaVida;
    }
    
    //Metodos de la clase
    //CRUD    
    public void registrarEmpleado(SmsUsuario u, SmsHojavida h){
    usuario = u; hojaVida = h;
    empleado.setSmsUsuario(usuario);
    empleado.setSmsHojavida(hojaVida);
    IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
    empleadoDao.registrarEmpleado(empleado);
    }
    
    public void modificarEmpleado(SmsUsuario u, SmsHojavida h, SmsEmpleado e){
    }
    
    public void eliminarEmpleado(SmsUsuario u, SmsHojavida h, SmsEmpleado e){
    
    }
    
    

}
