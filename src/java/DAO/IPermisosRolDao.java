/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.SmsPermisosRol;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface IPermisosRolDao {

    //Declaracion de metodos CRUD
    public List<SmsPermisosRol> mostrarPermisosRol();

    public void registrarPermisosRol(SmsPermisosRol permisosRol);

    public void modificarPermisosRol(SmsPermisosRol permisosRol);

    public void eliminarPermisosRol(SmsPermisosRol permisosRol);

}
