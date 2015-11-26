/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ICategoriaDao;
import DAO.ImpCategoriaDao;
import Modelo.SmsCategoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Categoria {
    private SmsCategoria categoria;
    private List<SmsCategoria> categorias;
            
    //COnstructor
    public Categoria(){
        categoria = new SmsCategoria();
    }
    
    
/* ------------------- Getters y Setters ------------------------*/
    public SmsCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(SmsCategoria categoria) {
        this.categoria = categoria;
    }

    public List<SmsCategoria> getCategorias() {       
        return categorias;
    }

    public void setCategorias(List<SmsCategoria> categorias) {
        this.categorias = categorias;
    }
/* ------------------- Getters y Setters ------------------------*/
/* ------------------- Metodos con la Logica de CAtegoriaBean ------------------------*/
    
    public void modificarCategoria(SmsCategoria cat){
        categoria = cat;
        ICategoriaDao linkDao = new ImpCategoriaDao();
        linkDao.modificarCategoria(categoria);
    }
    
    public void eliminarCategoria(SmsCategoria cat){
        categoria = cat;
        ICategoriaDao linkDao = new ImpCategoriaDao();
        linkDao.eliminarCategoria(categoria);        
    }
    
    public void registrarCategoria(SmsCategoria cat){
        categoria = cat;
        ICategoriaDao linkDao = new ImpCategoriaDao();
        linkDao.registrarCategoria(categoria);        
    }
    
    public List<SmsCategoria> cargarCategorias(){
        categorias = new ArrayList<>();
        ICategoriaDao linkDao = new ImpCategoriaDao();
        categorias = linkDao.mostrarCategorias();
        return categorias;
    }
    
    public List<SmsCategoria> consultarCategoria(SmsCategoria cat){
        categoria = cat;
        categorias = new ArrayList<>();
        ICategoriaDao linkDao = new ImpCategoriaDao();
        categorias = linkDao.consultarCategoria(categoria);
        return categorias;
    }
    
    public List<SmsCategoria> filtrarCategoria(String valor){
        categorias = new ArrayList<>();
        ICategoriaDao linkDao = new ImpCategoriaDao();
        categorias = linkDao.filtrarCategorias(valor);
        return categorias;
    }
    
/* ------------------- Metodos con la Logica de CategoriaBean ------------------------*/
    
    
    
    
}
