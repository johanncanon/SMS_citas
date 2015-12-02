/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Permiso;
import DAO.IPermisosDao;
import DAO.ImpPermisosDao;
import Modelo.SmsPermisos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class PermisosBean implements Serializable{

    //Objetos de vista
    private List<SmsPermisos> permisosListView;
    private SmsPermisos permisoView;
    private List<String> ListaPermisos;
    
    //relacion con el controlador
    Permiso permisoController;
    
    public PermisosBean() {
        permisoView = new SmsPermisos();
        permisosListView = new ArrayList<>();
        ListaPermisos = new ArrayList<>();        
        permisoController = new Permiso();
    }

    @PostConstruct
    public void init() {       
        
    }

    public List<SmsPermisos> getPermisosListView() {
        return permisosListView;
    }

    public void setPermisosListView(List<SmsPermisos> permisosListView) {
        this.permisosListView = permisosListView;
    }

    public SmsPermisos getPermisoView() {
        return permisoView;
    }

    public void setPermisoView(SmsPermisos permisoView) {
        this.permisoView = permisoView;
    }

    public List<String> getListaPermisos() {
        return ListaPermisos;
    }

    public void setListaPermisos(List<String> ListaPermisos) {
        this.ListaPermisos = ListaPermisos;
    }
      
    
    //Definicion de metodos CRUD    
    public void registrar(){
        permisoController.registrarPermiso(permisoView);
        permisoView = new SmsPermisos();
    }

    public void modificar(){
        permisoController.modificarPermiso(permisoView);
        permisoView = new SmsPermisos();
    }
    
    public void eliminar(){
        permisoController.eliminarPermiso(permisoView);
        permisoView = new SmsPermisos();
    }
    
}
