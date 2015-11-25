/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IMarcaDao;
import DAO.ImpMarcaDao;
import Modelo.SmsMarca;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Marca {

    SmsMarca marca;
    List<SmsMarca> marcas;

    public Marca() {//Contrusctor
        marca = new SmsMarca();
        marcas = new ArrayList<>();

    }
    /* ********************** Setters y Getters *****************************/

    public SmsMarca getMarca() {
        return marca;
    }

    public void setMarca(SmsMarca marca) {
        this.marca = marca;
    }

    public List<SmsMarca> getmMrcas() {
        IMarcaDao linkDao = new ImpMarcaDao();
        marcas = linkDao.mostrarMarcas();
        return marcas;
    }

    public void setMarcas(List<SmsMarca> marcas) {
        this.marcas = marcas;
    }

    /**
     * ********************** METODOS QUE SE COMUNICAN CON BEAN-MARCAS ********************************
     * @param marc
     */
    public void modificarMarca(SmsMarca marc){
        this.marca = marc;
        IMarcaDao linkDao = new ImpMarcaDao();
        linkDao.modificarMarca(marca);
    }

    public void eliminarMarca(SmsMarca marc) {
        this.marca = marc;
        IMarcaDao linkDao = new ImpMarcaDao();
        linkDao.eliminarMarca(marca);
    }

    public void registrarMarca(SmsMarca marc) {
        this.marca = marc;
        IMarcaDao linkDao = new ImpMarcaDao();
        linkDao.registrarMarca(marca);
    }
        
    public List<SmsMarca> consultarMarca(SmsMarca marc){
        this.marca = marc;
        IMarcaDao linkDao = new ImpMarcaDao();
        marcas = linkDao.consultarMarca(marca);
        return marcas;
    }
    
    public List<SmsMarca> cargarMarcas(){
       marcas = new ArrayList<>();
       IMarcaDao linkDao = new ImpMarcaDao();
       marcas = linkDao.mostrarMarcas();
       return marcas;
    }
    
}
