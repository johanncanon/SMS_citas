/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsUsuario;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IUsuarioDao {

 //Declaracion de metodos CRUD
    public List<SmsUsuario> mostrarPermisos();

    public void registrarUsuario(SmsUsuario usuario);

    public void modificarUsuario(SmsUsuario usuario);

    public void eliminarUsuario(SmsUsuario usuario);
    
    public List<SmsUsuario> consultarUsuario(SmsUsuario usuario);

}
