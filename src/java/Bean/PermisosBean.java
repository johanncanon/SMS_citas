/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IPermisosDao;
import DAO.ImpPermisosDao;
import Model.SmsPermisos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class PermisosBean {

    private List<SmsPermisos> permisos;
    private SmsPermisos permiso;
    private List<String> ListaPermisos;
    

    public PermisosBean() {
        permiso = new SmsPermisos();
        permisos = new ArrayList<>();
        ListaPermisos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {       
        
    }

    public List<SmsPermisos> getPermisos() {
        permisos = new ArrayList<>();
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisos = permisoDao.mostrarPermisos();
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

    public List getListaPermisos() {
        //Consulta los permisos en la BD y carga los nombres en un array para mostrarlos en la vista
        ListaPermisos = new ArrayList<>();
        IPermisosDao permisoDao = new ImpPermisosDao();
        permisos = permisoDao.mostrarPermisos();
        for (int i = 0; i < permisos.size(); i++) {
            ListaPermisos.add(permisos.get(i).getPermisosNombre());
        }
        return ListaPermisos;
    }

    public void setListaPermisos(List ListaPermisos) {
        this.ListaPermisos = ListaPermisos;
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
