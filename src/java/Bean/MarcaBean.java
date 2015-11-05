/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IMarcaDao;
import DAO.ImpMarcaDao;
import Modelo.SmsMarca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Desarrollo_Planit
 */
@ManagedBean
@RequestScoped
public class MarcaBean implements Serializable{
    
    private SmsMarca marca;
    private List<SmsMarca> marcas;
    private List<String> marcaSt;  
    
    
    public MarcaBean() {
        marca = new SmsMarca();
        marcaSt = new ArrayList<>();
    }
    
    
    /* GETTERS Y SETTERS **********************************************************************/
    public SmsMarca getMarca() {
        return marca;
    }

    public void setMarca(SmsMarca marca) {
        this.marca = marca;
    }

    public List<SmsMarca> getMarcas() {
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcas = marcaDao.mostrarMarcas();
        return marcas;
    }

    public void setMarcas(List<SmsMarca> marcas) {
        this.marcas = marcas;
    }
    
    public List<String> getMarcaSt() {
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcas = marcaDao.mostrarMarcas();
        for (int i = 0; i < marcas.size(); i++) {
            marcaSt.add(marcas.get(i).getMarcaNombre());
        }
        
        return marcaSt;
    }

    public void setMarcaSt(List<String> marcaSt) {
        this.marcaSt = marcaSt;
    }
    
    /*+++++++++++++++++++++++++*****************************************************************
    ****************    CREACION DE METODOS DEL BEAN      *********************************+++++*/
    
    public void modMarca(){
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcaDao.modificarMarca(marca);
        marca = new SmsMarca();
    }
    
    public void eliMarca(){
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcaDao.eliminarMarca(marca);
        marca = new SmsMarca();
    }
    
    public void regMarca(){
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcaDao.registrarMarca(marca);
        marca = new SmsMarca();
    }
    
    
}
