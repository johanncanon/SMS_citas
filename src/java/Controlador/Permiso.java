/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IPermisosDao;
import DAO.ImpPermisosDao;
import Modelo.SmsPermisos;
import java.util.ArrayList;
import java.util.List;



public class Permiso {
    
    protected SmsPermisos permiso; 
    protected List<SmsPermisos> permisos; 

    public Permiso() {
        permiso = new SmsPermisos();
        permisos = new ArrayList<>();
    }

    public SmsPermisos getPermiso() {
        return permiso;
    }

    public void setPermiso(SmsPermisos permiso) {
        this.permiso = permiso;
    }

    public List<SmsPermisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<SmsPermisos> permisos) {
        this.permisos = permisos;
    }
    
    //metodos
    public void registrarPermiso(SmsPermisos p) {
        permiso = p;
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisoDao.registrarPermiso(permiso);
        
    }

    public void modificarPermiso(SmsPermisos p) {
        permiso = p;
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisoDao.modificarPermiso(permiso);
       
    }

    public void eliminarPermiso(SmsPermisos p) {
        permiso = p;
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisoDao.eliminarPermiso(permiso);
       
    }
    
    
}
