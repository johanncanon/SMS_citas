/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Rol;
import Modelo.SmsRol;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class RolBean {

    //Instancia de objetos necesarios
    protected List<SmsRol> rolesListView;
    protected SmsRol rolView;
    
    //Relacion con el controlador
    protected Rol rolController;
    

    public RolBean() {
        rolView = new SmsRol();
        rolController = new Rol();
    }

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
      
    //Definicion de motodos CRUD    
    public void registrar() {
        rolController.RegistrarRol(rolView);
        rolView = new SmsRol();
    }

    public void modificar() {
        rolController.modificarRol(rolView);
        rolView = new SmsRol();
    }

    public void eliminar() {
        rolController.eliminarRol(rolView);
        rolView = new SmsRol();
    }

    //Definicion de metodos para asignacion de permisos a un rol
    public void asignarPermiso() {
        
    }
}
