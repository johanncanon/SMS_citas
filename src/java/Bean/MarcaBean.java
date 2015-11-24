/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IMarcaDao;
import DAO.ImpMarcaDao;
import Controlador.Marca;
import Modelo.SmsMarca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */

public class MarcaBean implements Serializable{
    
    //Objetos de vista
    private SmsMarca marcaView;   
    private List<SmsMarca> marcasListView;
    private List<String> nombresMarcaView;
   
    //Relacion con el controlador 
    private Marca marca;
     
    public MarcaBean() {
        marcaView = new SmsMarca();
        marcasListView = new ArrayList<>();
        nombresMarcaView = new ArrayList<>();
        marca = new Marca();
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    
    /* GETTERS Y SETTERS **********************************************************************/
    public SmsMarca getMarcaView() {
        return marcaView;
    }

    public void setMarcaView(SmsMarca marcaView) {
        this.marcaView = marcaView;
    }

    public List<SmsMarca> getMarcasListView() {
        return marcasListView;
    }

    public void setMarcasListView(List<SmsMarca> marcasListView) {
        this.marcasListView = marcasListView;
    }

    public List<String> getNombresMarcaView() {
        nombresMarcaView = new ArrayList<>();
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcasListView = marcaDao.mostrarMarcas();
        for (int i = 0; i < marcasListView.size(); i++) {
            nombresMarcaView.add(marcasListView.get(i).getMarcaNombre());
        }
        return nombresMarcaView;
    }

    public void setNombresMarcaView(List<String> nombresMarcaView) {
        this.nombresMarcaView = nombresMarcaView;
    } 
    

    
    
    /*+++++++++++++++++++++++++*****************************************************************
    ****************    CREACION DE METODOS DEL BEAN      *********************************+++++*/
    
    public void modificar(){
        marca.modificarMarca(marcaView);
        marcaView = new SmsMarca();
    }
    
    public void eliminar(){
        marca.eliminarMarca(marcaView);
        marcaView = new SmsMarca();
    }
    
    public void registrar(){
        marca.registrarMarca(marcaView);
        marcaView = new SmsMarca();
    }
    
    
}
