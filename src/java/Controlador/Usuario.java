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
    protected List<SmsUsuario> usuarios;

    //Relaciones con otras clases
    protected SmsCiudad ciudad;//asociacion
    protected List<String> listaRoles;//agregacion   

    public Usuario() {
        usuario = new SmsUsuario();
        ciudad = new SmsCiudad();
        usuarios = new ArrayList<>();
        listaRoles = new ArrayList<>();
    }

    //Metodos CRUD 
    public void registrarUsuario(SmsUsuario u, SmsCiudad c, SmsRol r) {
        usuario = u;
        ciudad = c;
        SmsRol rol = r;

        MD5 md = new MD5();
        usuario.setUsuarioPassword(md.getMD5(usuario.getUsuarioPassword()));//Se encripta la contreseña
        usuario.setUsuarioRememberToken(md.getMD5(usuario.getUsuarioRememberToken()));

        //el metodo recibe los atributos, agrega al atributo ciudad del objeto usuario un objeto correspondiente, 
        //de la misma forma comprueba el rol y lo asocia, por ultimo persiste el usuario en la base de datos
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuario.setSmsCiudad(ciudad);//Asociamos una ciudad a un usuario

        IRolDao rolDao = new ImpRolDao();
        rol = rolDao.consultarRol(rol).get(0);
        usuario.setSmsRol(rol);//Asociamos un rol a un usuario

        usuario.setUsuarioEstadoUsuario(1);//Asignamos un estado de cuenta

        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.registrarUsuario(usuario);//Registramos usuario
    }

    public void modificarUsuario(SmsUsuario u, SmsCiudad c, SmsRol r) {
        usuario = u;
        ciudad = c;
        SmsRol rol = r;

        MD5 md = new MD5();
        usuario.setUsuarioPassword(md.getMD5(usuario.getUsuarioPassword()));//Se encripta la contreseña
        usuario.setUsuarioRememberToken(md.getMD5(usuario.getUsuarioRememberToken()));

        //el metodo recibe el objeto usuario, con sus atributos modificados y lo persiste en la BD
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudad = ciudadDao.consultarCiudad(ciudad).get(0);
        usuario.setSmsCiudad(ciudad);//Asociamos una ciudad a un usuario

        IRolDao rolDao = new ImpRolDao();
        rol = rolDao.consultarRol(rol).get(0);
        usuario.setSmsRol(rol);//Asociamos un rol a un usuario

        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.modificarUsuario(usuario);
    }

    public void eliminarUsuario(SmsUsuario u) {
        usuario = u;
        //el metodo recibe el objeto usuario, se comunica con el dao y lo elimina de la base de datos
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.eliminarUsuario(usuario);
    }

    public List<SmsUsuario> cargarUsuarios() {
        //el metodo consulta de la base de datos todos los usuarios registrados y los retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.mostrarUsuario();
        return usuarios;
    }

    public List<SmsUsuario> consultarUsuario(SmsUsuario u) {
        usuario = u;
        //el metodo consulta de la base de datos toda la informacion de usuario registrado y lo retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.consultarUsuario(usuario);
        return usuarios;
    }

    public List<SmsUsuario> consultarUsuarioSesion(SmsUsuario u) {
        usuario = u;
        //el metodo consulta de la base de datos toda la informacion de un usuario mediante su nombre de sesion(Login)
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.consultarDatosSesionUsuario(usuario);
        return usuarios;
    }

    public List<SmsUsuario> consultarAdministradores() {
        usuarios = new ArrayList<>();
        //el metodo consulta de la base de datos todos los usuarios registrados con rol administrador y los retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.consultarUsuariosAdministradores();
        return usuarios;
    }

    public List<SmsUsuario> consultarClientes() {
        usuarios = new ArrayList<>();
        //el metodo consulta de la base de datos todos los usuarios registrados con rol cliente y los retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.consultarUsuariosClientes();
        return usuarios;
    }

    public List<SmsUsuario> consultarProveedores() {
        usuarios = new ArrayList<>();
        //el metodo consulta de la base de datos todos los usuarios registrados con rol proveedor y los retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.consultarUsuariosProveedores();
        return usuarios;
    }

    public List<SmsUsuario> consultarEmpleados() {
        usuarios = new ArrayList<>();
        //el metodo consulta de la base de datos todos los usuarios registrados con rol empleado(conductor) y los retorna en una lista
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.consultarUsuariosEmpleados();
        return usuarios;
    }

    public List<SmsUsuario> filtrarEmpleados(String valor) {
        usuarios = new ArrayList<>();
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.filtrarUsuariosEmpleados(valor);
        return usuarios;
    }

    public List<SmsUsuario> filtrarClientes(String valor) {
        usuarios = new ArrayList<>();
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.filtrarUsuariosClientes(valor);
        return usuarios;
    }

    public List<SmsUsuario> filtrarProveedores(String valor) {
        usuarios = new ArrayList<>();
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarios = usuarioDao.filtrarUsuariosProveedores(valor);
        return usuarios;
    }

    //Metodos de la sesion    
    public String iniciarSesion(SmsUsuario u) {
        usuario = u;
        //usuario = consultarUsuarioSesion(usuario).get(0);
        String ruta = "";
        Rol rol = new Rol();
        httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.setAttribute("Sesion", usuario);

        IRolDao rolLink = new ImpRolDao();
        List<SmsRol> roles = rolLink.mostrarRoles();//Consulta los roles existente en la BD
        boolean rolUsuario = false;

        for (int i = 0; i < roles.size(); i++) {
            rolUsuario = rol.validarRol(usuario, roles.get(i));//Envia el objeto usuario y el objeto rol, para comprobar el rol del usuario que intenta loguearse
            if (rolUsuario) {//valida si el rol es verdadero y consulta segun su valor, a cual dashboard debe direccionar al usuario
                switch (roles.get(i).getRolNombre()) {
                    case "Administrador Principal":
                        ruta = "AdminPPrincipal";
                        break;
                    case "Administrador Secundario":
                        ruta = "AdminSGeneral";
                        break;
                    case "Cliente":
                        ruta = "ClienteReservacion";
                        break;
                    case "Empleado":
                        ruta = "ConductorDash";
                        break;
                    case "Proveedor":
                        ruta = "ProveedorDash";
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

    public SmsUsuario obtenerSesion() {
        //Obtiene el usuario subido a la httpSession
        usuario = (SmsUsuario) httpSession.getAttribute("Sesion");
        return usuario;
    }
}
