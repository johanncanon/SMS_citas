 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.SmsCategoria;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public interface ICategoriaDao {
    
    public List<SmsCategoria> mostrarCategorias();
    public List<SmsCategoria> consultarCategorias(SmsCategoria categoria);
    public void registrarCategoria(SmsCategoria categoria);
    public void modificarCategoria(SmsCategoria categoria);
    public void eliminarCategoria(SmsCategoria categoria);    
    
}
