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
        String ruta = "/login.xhtml";        
        IRolDao rolLink = new ImpRolDao();
        List<SmsRol> roles = rolLink.mostrarRoles();

        for (int i = 0; i < roles.size(); i++) {
            if (usuario.getSmsRols().contains(roles.get(i))) {
                rol = roles.get(i);
            }
        }

        if (rol.getRolNombre().equalsIgnoreCase("Administrador principal")) {
            ruta = "./vistas/AdminP/Dashboard-Admin-Principal.xhtml";
        } else if (rol.getRolNombre().equalsIgnoreCase("Administrador secundario")) {
            ruta = "./vistas/AdminS/Dashboard-Admin-Secundario.xhtml";
        } else if (rol.getRolNombre().equalsIgnoreCase("Cliente")) {
            ruta = "./vistas/Cliente/Dashboard-Cliente.xhtml";
        } else if (rol.getRolNombre().equalsIgnoreCase("Empleado")) {
            ruta = "./vistas/Conductor/Dashboard-Conductor.xhtml";
        } else if (rol.getRolNombre().equalsIgnoreCase("Proveedor")) {
            ruta = "./vistas/Proveedor/Dashboard-Proveedor.xhtml";
        }

        return ruta;
    }

}
