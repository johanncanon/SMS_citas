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
        
        for (SmsRol role : roles) {
            rol = role;
            for (SmsRol r : usuario.getSmsRols()) {
                if (r.getRolNombre().equalsIgnoreCase(rol.getRolNombre())) {
                    ruta = "./vistas/AdminP/Dashboard-Admin-Principal.xhtml";
                }
            }
        }
        
        return ruta;
    }

}
