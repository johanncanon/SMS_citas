/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IUsuarioDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsRol;
import Modelo.SmsUsuario;

/**
 *
 * @author Desarrollo_Planit
 */
public class Rol {

    SmsRol rol;

    public Rol() {
        rol = new SmsRol();
    }

    //Metodos de la clase
    public boolean validarRol(SmsUsuario usuario, SmsRol rolUsuario) {
        boolean valor = false;
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        SmsUsuario u = usuarioDao.consultarUsuario(usuario).get(0);
                
        for(SmsRol r: u.getSmsRols()){//Recorre la coleccion del objeto u, guarda el objeto consultado en r 
            if(r.getRolNombre().equalsIgnoreCase(rolUsuario.getRolNombre())){//compara el objeto r con el objeto rolUsuario
            valor = true;
            }
        }
        return valor;
    } 

        
    

}
