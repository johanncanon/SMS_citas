/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IPermisosDao;
import DAO.ImpPermisosDao;
import Modelo.SmsPermisos;
import Modelo.SmsRol;
import java.util.ArrayList;
import java.util.List;



public class Permiso {
    
    protected SmsPermisos permiso; 
    protected List<SmsPermisos> permisos; 

    public Permiso() {
        permiso = new SmsPermisos();
        permisos = new ArrayList<>();
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
    
    public List<SmsPermisos> cargarPermisos(){
        permisos = new ArrayList<>();
        IPermisosDao permisosDao = new ImpPermisosDao();
        permisos = permisosDao.mostrarPermisos();
        return permisos;
    }
    
    public List<SmsPermisos> consultarPermiso(String valor){
        permisos = new ArrayList<>();
        IPermisosDao permisosDao = new ImpPermisosDao();
        permisos = permisosDao.consultarPermiso(valor);
        return permisos;
    }
    
    public String validarPermiso(SmsRol r, SmsPermisos p){
        String permiso = null;
        for(SmsPermisos per: r.getSmsPermisoses()){
            if(per.getPermisosNombre().equalsIgnoreCase(p.getPermisosNombre())){
            permiso = per.getPermisosNombre();
            }
        }
        return permiso;
    }
    
    public List<SmsPermisos> filtrarPermiso(String valor){
        permisos = new ArrayList<>();
        IPermisosDao permisosDao = new ImpPermisosDao();
        permisos = permisosDao.filtrarPermiso(valor);
        return permisos;
    }
    
}
