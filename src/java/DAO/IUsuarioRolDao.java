/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.SmsUsuarioRol;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IUsuarioRolDao {

    //Declaracion de metodos CRUD
    public List<SmsUsuarioRol> mostrarUsuariosRol();

    public void registrarUsuarioRol(SmsUsuarioRol usuarioRol);

    public void modificarUsuarioRol(SmsUsuarioRol usuarioRol);

    public void eliminarUsuarioRol(SmsUsuarioRol usuarioRol);

}
