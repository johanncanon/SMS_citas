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
    
    private SmsMarca marcaView;
    private List<SmsMarca> marcasView;
    private List<String> marcaStView;  
    
    
    public MarcaBean() {
        marcaView = new SmsMarca();
        marcaStView = new ArrayList<>();
    }
    
    
    /* GETTERS Y SETTERS **********************************************************************/
    public SmsMarca getMarcaView() {
        return marcaView;
    }

    public void setMarcaView(SmsMarca marcaView) {
        this.marcaView = marcaView;
    }

    public List<SmsMarca> getMarcasView() {
        /*IMarcaDao marcaDao = new ImpMarcaDao();
        marcas = marcaDao.mostrarMarcas();*/
        return marcasView;
    }

    public void setMarcasView(List<SmsMarca> marcasView) {
        this.marcasView = marcasView;
    }
    
    public List<String> getMarcaStView() {
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcasView = marcaDao.mostrarMarcas();
        for (int i = 0; i < marcasView.size(); i++) {
            marcaStView.add(marcasView.get(i).getMarcaNombre());
        }
        
        return marcaStView;
    }

    public void setMarcaStView(List<String> marcaStView) {
        this.marcaStView = marcaStView;
    }
    
    /*+++++++++++++++++++++++++*****************************************************************
    ****************    CREACION DE METODOS DEL BEAN      *********************************+++++*/
    
    public void modMarca(){
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcaDao.modificarMarca(marcaView);
        marcaView = new SmsMarca();
    }
    
    public void eliMarca(){
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcaDao.eliminarMarca(marcaView);
        marcaView = new SmsMarca();
    }
    
    public void regMarca(){
        IMarcaDao marcaDao = new ImpMarcaDao();
        marcaDao.registrarMarca(marcaView);
        marcaView = new SmsMarca();
    }
    
    
}
