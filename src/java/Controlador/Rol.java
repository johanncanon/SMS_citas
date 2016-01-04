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
    List<SmsRol> roles;

    public Rol() {
        rol = new SmsRol();
        roles = new ArrayList<>();
    }

    //Metodos CRUD
    public void RegistrarRol(SmsRol r) {
        rol = r;
        IRolDao rolDao = new ImpRolDao();
        rolDao.registrarRol(rol);

    }

    public void modificarRol(SmsRol r) {
        rol = r;
        IRolDao rolDao = new ImpRolDao();
        rolDao.modificarRol(rol);

    }

    public void eliminarRol(SmsRol r) {
        rol = r;
        IRolDao rolDao = new ImpRolDao();
        rolDao.eliminarRol(rol);

    }

    public List<SmsRol> filtrarRol(String valor) {
        roles = new ArrayList<>();
        IRolDao rolDao = new ImpRolDao();
        roles = rolDao.filtrarRol(valor);
        return roles;

    }

    public List<SmsRol> cargarRoles() {
        List<SmsRol> roles = new ArrayList<>();
        IRolDao rolDao = new ImpRolDao();
        roles = rolDao.mostrarRoles();
        return roles;
    }

    public List<SmsRol> consultarRol(SmsRol r) {
        List<SmsRol> roles = new ArrayList<>();
        IRolDao rolDao = new ImpRolDao();
        roles = rolDao.consultarRol(r);
        return roles;
    }
    
    //Metodos de la clase
    public boolean validarRol(SmsUsuario usuario, SmsRol rolUsuario) {
        boolean valor = false;
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        SmsUsuario u = usuarioDao.consultarDatosSesionUsuario(usuario).get(0);

        if (u.getSmsRol().getRolNombre().equalsIgnoreCase(rolUsuario.getRolNombre())) {//compara el objeto r con el objeto rolUsuario
            valor = true;
        }

        return valor;
    }

}
