    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Categoria;
import DAO.ICategoriaDao;
import DAO.ImpCategoriaDao;
import Modelo.SmsCategoria;
import java.io.Serializable;
import java.util.List;


public class CategoriaBean implements Serializable{

     private SmsCategoria categoriaView;
     private List<SmsCategoria> categoriasView;
     private List<String> listaCategoriasView;
     private Categoria categoria;//Instancia de la Clase Categoria del Paquete CONTROLADOR
     
     
    public CategoriaBean() {//CONSTRUCTOR
        
        categoriaView = new SmsCategoria();
        categoria = new Categoria();//Instancia de la Clase Categoria del Paquete CONTROLADOR
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SmsCategoria getCategoriaView(){
        return categoriaView;
    }

    public void setCategoriaView(SmsCategoria categoriaView) {
        this.categoriaView = categoriaView;
    }

    
    public List<SmsCategoria> getCategoriasView() {// *****************  DEVOLVER LISTADO DE CATEGORIAS
        ICategoriaDao categoriasDao = new ImpCategoriaDao();
        categoriasView = categoriasDao.mostrarCategorias();
        return categoriasView;
    }

    public void setCategoriasView(List<SmsCategoria> categoriasView) {
        this.categoriasView = categoriasView;
    }

    public List<String> getListaCategoriasView() {
        return listaCategoriasView;
    }

    public void setListaCategoriasView(List<String> listaCategoriasView) {
        this.listaCategoriasView = listaCategoriasView;
    }
    
    
    
    
    //METODOS QUE DEVUELVEN DATOS PARA VISTAS
    
    public void modCategoria (){
        //TRAER LA INFORMACION DE LA VISTA Y PASARLA AL PARAMETRO CORRESPODIENTE 
        //DE LA CLASE DEL PAQUETE CONTROLADOR
        categoria.modificarCategoria(categoriaView);
        categoriaView = new SmsCategoria();
        
    }
    
    public void regCategoria(){
        //TRAER LA INFORMACION DE LA VISTA Y PASARLA AL PARAMETRO CORRESPODIENTE 
        //DE LA CLASE DEL PAQUETE CONTROLADOR
        categoria.registrarrCategoria(categoriaView);
        categoriaView = new SmsCategoria();
        
    }
    
    public void eliCategoria(){
        //TRAER LA INFORMACION DE LA VISTA Y PASARLA AL PARAMETRO CORRESPODIENTE 
        //DE LA CLASE DEL PAQUETE CONTROLADOR
        categoria.eliminarCategoria(categoriaView);
        categoriaView = new SmsCategoria();
    }
    
    
    
}
