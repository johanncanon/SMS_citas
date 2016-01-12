/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Permiso;
import Controlador.Rol;
import Modelo.SmsPermisos;
import Modelo.SmsRol;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class RolBean {

    //Instancia de objetos necesarios
    protected List<SmsRol> rolesListView;
    protected SmsRol rolView;
    protected SmsRol DRolView;
    protected List<String> permisosSeleccionados;
    protected List<SmsPermisos> permisosListView;

    //Relacion con el controlador
    protected Rol rolController;
    protected Permiso permisoController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public RolBean() {
        rolView = new SmsRol();
        DRolView = new SmsRol();
        rolesListView = new ArrayList<>();
        rolController = new Rol();
        permisoController = new Permiso();
        permisosSeleccionados = new ArrayList<>();
        buscar = null;
        estado = 0;
        nombre = "Registrar Rol";
    }

    @PostConstruct
    public void init() {
        rolesListView = rolController.cargarRoles();
    }

    //Getters & Setters
    public List<SmsRol> getRolesListView() {
        return rolesListView;
    }

    public void setRolesListView(List<SmsRol> rolesListView) {
        this.rolesListView = rolesListView;
    }

    public SmsRol getRolView() {
        return rolView;
    }

    public void setRolView(SmsRol rolView) {
        this.rolView = rolView;
    }

    public Rol getRolController() {
        return rolController;
    }

    public void setRolController(Rol rolController) {
        this.rolController = rolController;
    }

    public List<String> getPermisosSeleccionados() {
        return permisosSeleccionados;
    }

    public void setPermisosSeleccionados(List<String> permisosSeleccionados) {
        this.permisosSeleccionados = permisosSeleccionados;
    }

    public List<SmsPermisos> getPermisosListView() {
        return permisosListView;
    }

    public void setPermisosListView(List<SmsPermisos> permisosListView) {
        this.permisosListView = permisosListView;
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

    public SmsRol getDRolView() {
        return DRolView;
    }

    public void setDRolView(SmsRol DRolView) {
        this.DRolView = DRolView;
    }

    //Definicion de motodos CRUD    
    public void registrar() {
        asignarPermiso();
        rolController.RegistrarRol(rolView);
        rolView = new SmsRol();
        permisosSeleccionados.clear();
        rolesListView = rolController.cargarRoles();
    }

    public void modificar() {

        rolController.modificarRol(rolView);
        rolView = new SmsRol();
        rolesListView = rolController.cargarRoles();
        permisosSeleccionados.clear();
    }

    public void eliminar() {
        rolController.eliminarRol(DRolView);
        if (rolView.equals(DRolView)) {
            rolView = new SmsRol();
            estado = 0;
            nombre = "Registrar Rol";
        }
        DRolView = new SmsRol();
        rolesListView = rolController.cargarRoles();
        permisosSeleccionados.clear();
    }

    public void filtrar() {
        rolesListView = new ArrayList<>();
        if (buscar == null) {
            rolesListView = rolController.cargarRoles();
        } else {
            rolesListView = rolController.filtrarRol(buscar);
        }
    }

    //Definicion de metodos para asignacion de permisos a un rol
    public void asignarPermiso() {
        permisosListView = new ArrayList<>();
        for (int i = 0; i < permisosSeleccionados.size(); i++) {
            rolView.getSmsPermisoses().add(permisoController.consultarPermiso(permisosSeleccionados.get(i)).get(0));
        }
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        permisosSeleccionados.clear();
        if (estado == 1) {
            nombre = "Modificar Rol";
            List<SmsPermisos> permisos = permisoController.cargarPermisos();
            List<SmsRol> rolPermisos = rolController.consultarRol(rolView);
            String valor;
            for (int b = 0; b < permisos.size(); b++) {
                valor = permisoController.validarPermiso(rolPermisos.get(0), permisos.get(b));
                if (valor != null) {
                    permisosSeleccionados.add(valor);
                }
            }

        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Rol";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Rol";
        }
    }
}
