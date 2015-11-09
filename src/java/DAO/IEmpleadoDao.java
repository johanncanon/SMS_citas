/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsEmpleado;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IEmpleadoDao {
    
    //Definicion metodos CRUD
    public List<SmsEmpleado> mostrarEmpleados();
    public void registrarEmpleado(SmsEmpleado empleado);
    public void modificarEmpleado(SmsEmpleado empleado);
    public void eliminarEmpleado(SmsEmpleado empleado);
}
