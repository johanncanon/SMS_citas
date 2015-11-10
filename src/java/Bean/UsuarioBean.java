/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Clases.Usuario;
import DAO.ICiudadDao;
import DAO.IRolDao;
import DAO.IUsuarioDao;
import DAO.ImpCiudadDao;
import DAO.ImpRolDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Desarrollo_Planit
 */
public class UsuarioBean implements Serializable {

    //Instanciacion de los objetos    
    protected SmsUsuario usuarioBean;
    protected SmsUsuario auxUsuarioBean;
    protected List<SmsUsuario> usuarios;

    //Relaciones con otras clases
    protected SmsCiudad ciudad;//asociacion
    protected List<String> roles;//agregacion   

    //Relacion con la clase
    protected Usuario usuario;
    

    //Contexto
    private FacesMessage message;

    public UsuarioBean() {
        usuarioBean = new SmsUsuario();
        auxUsuarioBean = new SmsUsuario();
        ciudad = new SmsCiudad();
        usuario = new Usuario();
    }

    public SmsUsuario getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(SmsUsuario usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List<SmsUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<SmsUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public SmsCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(SmsCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public SmsUsuario getAuxUsuarioBean() {
        return auxUsuarioBean;
    }

    public void setAuxUsuarioBean(SmsUsuario auxUsuarioBean) {
        this.auxUsuarioBean = auxUsuarioBean;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Declaracion de metodos
    //Metodos CRUD
    public void registrarUsuario() {
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuarioBean.setSmsCiudad(ciudad);

        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.registrarUsuario(usuarioBean);

        auxUsuarioBean = new SmsUsuario();
        auxUsuarioBean = usuarioBean;
        usuarioBean = new SmsUsuario();
    }

    public void modificarUsuario() {
        auxUsuarioBean.setUsuarioEstadoUsuario(1);
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.modificarUsuario(auxUsuarioBean);
        auxUsuarioBean = new SmsUsuario();
    }

    public void eliminarUsuario() {
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.eliminarUsuario(usuarioBean);
        usuarioBean = new SmsUsuario();
    }  

    //Metodos de funcionalidad
    public String iniciarSesion() {
        String ruta = "";        
        ruta = usuario.validarSesion(usuarioBean);     
        return ruta;
    }
    
    public String cerrarSesion(){
        String ruta="";
        ruta = usuario.cerrarSesion();
        return ruta;
    }

    //Definicion de metodos para la asignacion de roles   
    public void asignarRol() {
        SmsRol rol = new SmsRol();
        IRolDao rolDao = new ImpRolDao();
        for (int i = 0; i < roles.size(); i++) { //Recorre el array de nombres de permisos
            rol = rolDao.consultarRol(roles.get(i)).get(0);
            //Realiza la consulta en la base de datos y guarda el objeto resultante en el objeto permiso
            auxUsuarioBean.getSmsRols().add(rol);//agrega el permiso al rol           
        }
        modificarUsuario();
        roles = new ArrayList<>();
        auxUsuarioBean = new SmsUsuario();
    }

    public void modificarRol() {    
    }

    public void eliminarRol() {
    }

    public void consultarRol() {
    }

}
