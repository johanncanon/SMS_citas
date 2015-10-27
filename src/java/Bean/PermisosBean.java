/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IPermisosDao;
import DAO.ImpPermisosDao;
import Model.SmsPermisos;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class PermisosBean {

    private List<SmsPermisos> permisos;
    private SmsPermisos permiso;

    public PermisosBean() {
        permiso = new SmsPermisos();
    }

    public List<SmsPermisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<SmsPermisos> permisos) {
        this.permisos = permisos;
    }

    public SmsPermisos getPermiso() {
        return permiso;
    }

    public void setPermiso(SmsPermisos permiso) {
        this.permiso = permiso;
    }

    //Definicion de metodos CRUD    
    public void registrarPermiso() {
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisoDao.registrarPermiso(permiso);
        permiso = new SmsPermisos();
    }

    public void modificarPermiso() {
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisoDao.modificarPermiso(permiso);
        permiso = new SmsPermisos();
    }

    public void eliminarPermiso() {
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisoDao.eliminarPermiso(permiso);
        permiso = new SmsPermisos();
    }

}
