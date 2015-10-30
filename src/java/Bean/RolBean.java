/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IPermisosDao;
import DAO.IRolDao;
import DAO.ImpPermisosDao;
import DAO.ImpRolDao;
import Modelo.SmsPermisos;
import Modelo.SmsRol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class RolBean {

    private List<SmsRol> Roles;
    private SmsRol rol;
    private SmsPermisos permiso;
    
    private List<String> permisosSeleccionados;
    private List<String> ListaRoles;

    public RolBean() {
        rol = new SmsRol();
        permiso = new SmsPermisos();
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

    public List<String> getPermisosSeleccionados() {
        return permisosSeleccionados;
    }

    public void setPermisosSeleccionados(List<String> permisosSeleccionados) {
        this.permisosSeleccionados = permisosSeleccionados;
    }

    public List<String> getListaRoles() {
        ListaRoles = new ArrayList<>();
        IRolDao rolDao = new ImpRolDao();
        Roles = rolDao.mostrarRoles();
        for (int i = 0; i < Roles.size(); i++) {
            ListaRoles.add(Roles.get(i).getRolNombre());
        }
        return ListaRoles;
    }

    public void setListaRoles(List<String> ListaRoles) {
        this.ListaRoles = ListaRoles;
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
        IPermisosDao permisoDao = new ImpPermisosDao();

        /* Este segmento for, captura el objeto permiso segun un nombre y 
                los guarda en otro objeto para despues ser asignado a un rol*/
        
        for (int i = 0; i < permisosSeleccionados.size(); i++) { //Recorre el array de nombres de permisos
            permiso = permisoDao.consultarPermiso(permisosSeleccionados.get(i)).get(0);
            //Realiza la consulta en la base de datos y guarda el objeto resultante en el objeto permiso
            rol.getSmsPermisoses().add(permiso);//agrega el permiso al rol
           
        }        
        registrarRol();
        permisosSeleccionados = new ArrayList<>();
        
    }
}

