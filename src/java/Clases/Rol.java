/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DAO.IRolDao;
import DAO.ImpRolDao;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Rol {

    SmsRol rol;

    public Rol() {
        rol = new SmsRol();
    }

    //Metodos de la clase
    public String validarRol(SmsUsuario usuario) {
        String ruta="";
        IRolDao rolLink = new ImpRolDao();
        List<SmsRol> roles = rolLink.mostrarRoles();
        SmsRol RolUsuario = new SmsRol();

        for (SmsRol role : roles) {
            rol = role;
            for (SmsRol r : usuario.getSmsRols()) {
                if (r.getRolNombre().equalsIgnoreCase(rol.getRolNombre())) {
                    
                    switch (r.getRolNombre()) {
                        case "Administrador principal":
                            ruta = "./vistas/AdminP/Dashboard-Admin-Principal.xhtml";
                            break;
                        case "Administrador secundario":
                            ruta = "./vistas/AdminS/Dashboard-Admin-Secundario.xhtml";
                            break;
                        case "Cliente":
                            ruta = "./vistas/Cliente/Dashboard-Cliente.xhtml";
                            break;
                        case "Empleado":
                            ruta = "./vistas/Conductor/Dashboard-Conductor.xhtml";
                            break;
                        case "Proveedor":
                            ruta = "./vistas/Proveedor/Dashboard-Proveedor.xhtml";
                            break;
                    }
                }
            }
        }
        return ruta;
    }

}
