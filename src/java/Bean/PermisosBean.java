/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Permiso;
import Modelo.SmsPermisos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class PermisosBean implements Serializable {

    //Objetos de vista
    private List<SmsPermisos> permisosListView;
    private SmsPermisos permisoView;
    private List<String> nombresPermisosListView;

    //relacion con el controlador
    Permiso permisoController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    
    public PermisosBean() {
        permisoView = new SmsPermisos();
        permisosListView = new ArrayList<>();
        nombresPermisosListView = new ArrayList<>();
        permisoController = new Permiso();
        
        buscar = null;
        estado = 0;
        nombre = "Registrar Permiso";
    }

    @PostConstruct
    public void init() {
        permisosListView = permisoController.cargarPermisos();
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

    public List<String> getNombresPermisosListView() {
        nombresPermisosListView = new ArrayList<>();
        permisosListView = permisoController.cargarPermisos();
        for (int i = 0; i < permisosListView.size(); i++) {
            nombresPermisosListView.add(permisosListView.get(i).getPermisosNombre());
        }
        return nombresPermisosListView;
    }

    public void setNombresPermisosListView(List<String> nombresPermisosListView) {
        this.nombresPermisosListView = nombresPermisosListView;
    }

    public Permiso getPermisoController() {
        return permisoController;
    }

    public void setPermisoController(Permiso permisoController) {
        this.permisoController = permisoController;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    
    //Definicion de metodos CRUD    
    public void registrar() {
        permisoController.registrarPermiso(permisoView);
        permisoView = new SmsPermisos();
        permisosListView = permisoController.cargarPermisos();
    }

    public void modificar() {
        permisoController.modificarPermiso(permisoView);
        permisoView = new SmsPermisos();
        permisosListView = permisoController.cargarPermisos();
    }

    public void eliminar() {
        permisoController.eliminarPermiso(permisoView);
        permisoView = new SmsPermisos();
        permisosListView = permisoController.cargarPermisos();
    }
    
    public void filtrar() {
        permisosListView = new ArrayList<>();
        if (buscar == null) {
            permisosListView = permisoController.cargarPermisos();
        } else {
            permisosListView = permisoController.filtrarPermiso(buscar);
        }
    }
    
    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {            
            nombre = "Modificar Permiso";
        } 
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Permiso";
        } 
    }

}
