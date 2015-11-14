/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IRolDao;
import DAO.IUsuarioDao;
import DAO.ImpRolDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.util.ArrayList;
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

    //Metodos CRUD
    public void RegistrarRol(SmsRol r) {
        rol=r;
        IRolDao rolDao = new ImpRolDao();
        rolDao.registrarRol(rol);
       
    }

    public void modificarRol(SmsRol r) {
        rol=r;        
        IRolDao rolDao = new ImpRolDao();
        rolDao.modificarRol(rol);
        
    }

    public void eliminarRol(SmsRol r) {
        rol=r;
        IRolDao rolDao = new ImpRolDao();
        rolDao.eliminarRol(rol);
        
    }

    public List<SmsRol> cargarRoles() {
        List<SmsRol> roles = new ArrayList<>();
        IRolDao rolDao = new ImpRolDao();
        roles = rolDao.mostrarRoles();
        return roles;
    }

    //Metodos de la clase
    public boolean validarRol(SmsUsuario usuario, SmsRol rolUsuario) {
        boolean valor = false;
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        SmsUsuario u = usuarioDao.consultarUsuario(usuario).get(0);

        for (SmsRol r : u.getSmsRols()) {//Recorre la coleccion del objeto u, guarda el objeto consultado en r 
            if (r.getRolNombre().equalsIgnoreCase(rolUsuario.getRolNombre())) {//compara el objeto r con el objeto rolUsuario
                valor = true;
            }
        }
        return valor;
    }

}
