/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Rol;
import DAO.IPermisosDao;
import DAO.ImpPermisosDao;
import Modelo.SmsPermisos;
import Modelo.SmsRol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class RolBean {

    //Instancia de objetos necesarios
    private List<SmsRol> roles;
    private SmsRol rolBean;
    
    //Relacion con el controlador
    protected Rol rol;

    private List<String> permisosSeleccionados;
    private List<String> ListaRoles;

    public RolBean() {
        rolBean = new SmsRol();
        rol = new Rol();
    }

    public List<SmsRol> getRoles() {
        roles = rol.cargarRoles();
        return roles;
    }

    public void setRoles(List<SmsRol> roles) {
        this.roles = roles;
    }    
    
    public SmsRol getRolBean() {
        return rolBean;
    }

    public void setRolBean(SmsRol rolBean) {
        this.rolBean = rolBean;
    }

    public List<String> getPermisosSeleccionados() {
        return permisosSeleccionados;
    }

    public void setPermisosSeleccionados(List<String> permisosSeleccionados) {
        this.permisosSeleccionados = permisosSeleccionados;
    }

    public List<String> getListaRoles() {
        ListaRoles = new ArrayList<>();       
        for (int i = 0; i < rol.cargarRoles().size(); i++) {
            ListaRoles.add(rol.cargarRoles().get(i).getRolNombre());
        }
        return ListaRoles;
    }

    public void setListaRoles(List<String> ListaRoles) {
        this.ListaRoles = ListaRoles;
    }

    //Definicion de motodos CRUD    
    public void registrar() {
        rol.RegistrarRol(rolBean);
        rolBean = new SmsRol();
    }

    public void modificar() {
        rol.modificarRol(rolBean);
        rolBean = new SmsRol();
    }

    public void eliminar() {
        rol.eliminarRol(rolBean);
        rolBean = new SmsRol();
    }

    //Definicion de metodos para asignacion de permisos a un rol
    public void asignarPermiso() {
        SmsPermisos permiso;
        permiso = new SmsPermisos();
        IPermisosDao permisoDao = new ImpPermisosDao();

        /* Este segmento for, captura el objeto permiso segun un nombre y 
         los guarda en otro objeto para despues ser asignado a un rol*/
        for (int i = 0; i < permisosSeleccionados.size(); i++) { //Recorre el array de nombres de permisos
            permiso = permisoDao.consultarPermiso(permisosSeleccionados.get(i)).get(0);
            //Realiza la consulta en la base de datos y guarda el objeto resultante en el objeto permiso
            rolBean.getSmsPermisoses().add(permiso);//agrega el permiso al rol

        }
        registrar();
        permisosSeleccionados = new ArrayList<>();

    }
}
