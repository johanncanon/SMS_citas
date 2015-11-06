/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.ICategoriaDao;
import DAO.ImpCategoriaDao;
import Modelo.SmsCategoria;
import java.io.Serializable;
import java.util.List;


public class CategoriaBean implements Serializable{

     private SmsCategoria categoria;
     private List<SmsCategoria> categorias;
     private List<String> listaCategorias;
     
     
    public CategoriaBean() {
        categoria = new SmsCategoria();
    }

    public SmsCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(SmsCategoria categoria) {
        this.categoria = categoria;
    }

    
    public List<SmsCategoria> getCategorias() {// *****************  DEVOLVER LISTADO DE CATEGORIAS
        ICategoriaDao categoriasDao = new ImpCategoriaDao();
        categorias = categoriasDao.mostrarCategorias();
        return categorias;
    }

    public void setCategorias(List<SmsCategoria> categorias) {
        this.categorias = categorias;
    }

    public List<String> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<String> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    
    
    
    //METODOS QUE DEVUELVEN DATOS PARA VISTAS
    
    public void modCategoria (){
        ICategoriaDao categotiaDao = new ImpCategoriaDao();
        categotiaDao.modificarCategoria(categoria);
        categoria = new SmsCategoria();
    }
    
    public void regCategoria(){
        ICategoriaDao categoriaDao = new ImpCategoriaDao();
        categoriaDao.registrarCategoria(categoria);
        categoria = new SmsCategoria();
    }
    
    public void eliCategoria(){
        ICategoriaDao categoriaDao = new ImpCategoriaDao();
        categoriaDao.eliminarCategoria(categoria);
        categoria = new SmsCategoria();
    }
    
    
    
}
