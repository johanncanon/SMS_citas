/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsAgenda;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsUsuario;
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

    public List<SmsEmpleado> consultarEmpleado(SmsUsuario usuario);

    public List<SmsEmpleado> consultarEmpleadosDisponibles(SmsAgenda agenda, SmsCiudad ciudad);
    
    public List<SmsEmpleado> consultarEmpleadosCiudad(SmsCiudad ciudad);
}
