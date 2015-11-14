/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsEmpleado;

/**
 *
 * @author Desarrollo_Planit
 */
public class Empleado extends Usuario{
    
    SmsEmpleado empleado;
    
    
    public Empleado() {
        super();
    }

    public SmsEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(SmsEmpleado empleado) {
        this.empleado = empleado;
    }
    
    
    
    
    
}
