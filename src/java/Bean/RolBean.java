/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IRolDao;
import DAO.ImpRolDao;
import Model.SmsPermisos;
import Model.SmsRol;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class RolBean {

    private List<SmsRol> Roles;
    

    private SmsRol rol;
    private SmsPermisos permiso;

    public RolBean() {
        rol = new SmsRol();
    }

    public List<SmsRol> getRoles() {
        return Roles;
    }

    public void setRoles(List<SmsRol> Roles) {
        this.Roles = Roles;
    }    

    public SmsRol getRol() {
        return rol;
    }

    public void setRol(SmsRol rol) {
        this.rol = rol;
    }

    public SmsPermisos getPermiso() {
        return permiso;
    }

    public void setPermiso(SmsPermisos permiso) {
        this.permiso = permiso;
    }

    //Definicion de motodos CRUD    
    public void registrarRol() {
        IRolDao rolDao = new ImpRolDao();
        rolDao.registrarRol(rol);
        rol = new SmsRol();
    }

    public void modificarRol() {
        IRolDao rolDao = new ImpRolDao();
        rolDao.modificarRol(rol);
        rol = new SmsRol();
    }

    public void eliminarRol() {
        IRolDao rolDao = new ImpRolDao();
        rolDao.eliminarRol(rol);
        rol = new SmsRol();
    }

    //Definicion de metodos para asignacion de permisos a un rol
    public void asignarPermiso() {

    }
}
