/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;

/**
 *
 * @author Desarrollo_Planit
 */
public class EmpleadoBean implements Serializable{

    
    protected SmsUsuario usuarioView;
    protected SmsEmpleado empleadoView;
    
    protected SmsCiudad ciudadView;
    protected SmsRol rolView;
    
    public EmpleadoBean() {
    }

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
    
    
    
}
