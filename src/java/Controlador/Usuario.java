/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ICiudadDao;
import DAO.IRolDao;
import DAO.IUsuarioDao;
import DAO.ImpCiudadDao;
import DAO.ImpRolDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsCiudad;
import Modelo.SmsRol;
import Modelo.SmsUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Desarrollo_Planit
 */
public class Usuario {

    //Contexto y sesion
    private HttpSession httpSession;

    //Atributos
    protected SmsUsuario usuario;
    
    //Relaciones con otras clases
    protected SmsCiudad ciudad;//asociacion
    protected List<String> listaRoles;//agregacion   

    public Usuario() {
        usuario = new SmsUsuario();
        ciudad = new SmsCiudad();
        listaRoles = new ArrayList<>();
    }

    //Metodos CRUD 
    public void registrarUsuario(SmsUsuario u, SmsCiudad c) {        
        usuario=u;ciudad=c;
        //el metodo recibe los atributos, agrega al atributo ciudad del objeto usuario un objeto correspondiente, 
        //y persiste el usuario en la base de datos
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuario.setSmsCiudad(ciudad);
        
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.registrarUsuario(usuario);        
    }

    public void registrarDatosSesion(SmsUsuario u, SmsRol r) {
        usuario=u;SmsRol rol = r;
        //el metodo recibe el atributo usuario, modifica el valor de estado de cuenta del usuario registrado previamente, 
        //y modifica al usuario agregando en la BD los datos de login, pass y rememberpass
        usuario.setUsuarioEstadoUsuario(1);
        
        IRolDao rolDao = new ImpRolDao();
        rol = rolDao.consultarRol(rol).get(0);
        
        usuario.getSmsRols().add(rol);
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.modificarUsuario(usuario);
        
    }
    
    public void modificarUsuario(SmsUsuario u, SmsCiudad c) {
        usuario=u;ciudad=c;        
        //el metodo recibe el objeto usuario, con sus atributos modificados y lo persiste en la BD
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuario.setSmsCiudad(ciudad);
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.modificarUsuario(usuario);
        
    }

    public void eliminarUsuario(SmsUsuario u){
        usuario=u; 
        //el metodo recibe el objeto usuario se comunica con el dao y lo elimina de la base de datos
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.eliminarUsuario(usuario);
    }
    
    public List<SmsUsuario> cargarUsuarios(){
        //el metodo consulta de la base de datos todos los usuarios registrados y los retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        List<SmsUsuario> usuarios = usuarioDao.mostrarUsuario();        
        return usuarios;
    }
    
    
    
    
    
    
    
    //Metodos de la sesion    
    public String iniciarSesion(SmsUsuario u) {
        usuario=u;
        
        String ruta = "";
        Rol rol = new Rol();
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.setAttribute("Sesion", usuario);

        IRolDao rolLink = new ImpRolDao();
        List<SmsRol> roles = rolLink.mostrarRoles();//Consulta los roles existente en la BD
        boolean rolUsuario = false;

        for (int i = 0; i < roles.size(); i++) {
            rolUsuario = rol.validarRol(usuario, roles.get(i));//Envia el objeto usuario y el objeto rol, para comprobar el rol del usuario que intenta loguearse
            if (rolUsuario) {//valida si el rol es verdadero y consulta segun su valor, a cual dashboard debo direccionar al usuario
                switch (roles.get(i).getRolNombre()) {
                    case "Administrador Principal":
                        ruta = "./vistas/AdminP/Dashboard-Admin-Principal.xhtml";
                        break;
                    case "Administrador Secundario":
                        ruta = "./vistas/AdminS/Dashboard-Admin-Secundario.xhtml";
                        break;
                    case "Cliente":
                        ruta = "./vistas/Cliente/Dashboard-Cliente.xhtml";
                        break;
                    case "Empleado":
                        ruta = "./vistas/Conductor/Dashboard-Conductor.xhtml";
                        break;
                    case "Proveedor":
                        ruta = "./vistas/Proveedor/Dashboard-Proveedor.xhtml";
                        break;
                }
            }
        }
        return ruta;
    }

    public void cerrarSesion() {
        
        //Desmonta el objeto usuario subido al contexto de sesion y e invalida esta.
               
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();        
        
    }

}
