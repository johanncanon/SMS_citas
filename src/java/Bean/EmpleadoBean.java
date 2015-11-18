/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Empleado;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsHojavida;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;

/**
 *
 * @author Desarrollo_Planit
 */
public class EmpleadoBean implements Serializable{

    //objetos necesarios en vista
    protected SmsUsuario usuarioView;
    protected SmsUsuario auxUsuarioView;
    protected SmsEmpleado empleadoView;    
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    protected SmsHojavida hojavida;
    
    //Control de componentes
    protected boolean habilitado;
    
    //Relacion con el controlador
    protected Empleado empleado;
    
    public EmpleadoBean() {
    }
    
    //Getters & Setters
    public SmsUsuario getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(SmsUsuario usuarioView) {
        this.usuarioView = usuarioView;
    }

    public SmsEmpleado getEmpleadoView() {
        return empleadoView;
    }

    public void setEmpleadoView(SmsEmpleado empleadoView) {
        this.empleadoView = empleadoView;
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciudadView) {
        this.ciudadView = ciudadView;
    }

    public SmsRol getRolView() {
        return rolView;
    }

    public void setRolView(SmsRol rolView) {
        this.rolView = rolView;
    }
    
    //Metodos que se comunican con el controlador    
    public void registrar(){
        empleado.registrarUsuario(usuarioView, ciudadView);
        empleado.registrarEmpleado(usuarioView, hojavida, empleadoView);
        auxUsuarioView = usuarioView;
        usuarioView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        hojavida = new SmsHojavida();
        empleadoView = new SmsEmpleado();
    }
    
    public void registrarCuenta(){
        rolView.setRolNombre("Empleado");
        empleado.registrarDatosSesion(usuarioView, rolView);
        usuarioView = new SmsUsuario();
        rolView = new SmsRol();
    }
    
    public void modificar(){       
    }
    
    public void eliminar(){        
    }
    
    
}
