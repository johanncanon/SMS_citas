/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.ICiudadDao;
import DAO.ImpCiudadDao;
import Controlador.Ciudad;
import Modelo.SmsCiudad;
import Modelo.SmsPais;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class CiudadBean {
    
    //Objetos de vista
    private SmsCiudad ciudadView;   
    private List<SmsCiudad> ciudadesListView;
    private List<String> nombresCiudadesListView;
    protected SmsPais pais;
    
    //Relacion con el controlador
    private Ciudad ciudadController;

    public CiudadBean() {
        ciudadView = new SmsCiudad();
        ciudadesListView= new ArrayList<>();
        pais = new SmsPais();
        
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciu) {
        this.ciudadView = ciu;
    }

    public List<SmsCiudad> getCiudadesListView() {
        ciudadesListView = ciudadController.getCiudades();
        return ciudadesListView;
    }

    public void setCiudadesListView(List<SmsCiudad> ciudades) {
        this.ciudadesListView = ciudades;
    }

    public Ciudad getCiudadController() {
        return ciudadController;
    }

    public void setCiudadController(Ciudad ciudadController) {
        this.ciudadController = ciudadController;
    }   

    public SmsPais getPais() {
        return pais;
    }

    public void setPais(SmsPais pais) {
        this.pais = pais;
    } 
      

    public List<String> getNombresCiudadesListView() {
        nombresCiudadesListView = new ArrayList<>();
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudadesListView = ciudadDao.mostrarCiudades();
        for (int i = 0; i < ciudadesListView.size(); i++) {
            nombresCiudadesListView.add(ciudadesListView.get(i).getCiudadNombre());
        }
        return nombresCiudadesListView;
    }

    public void setNombresCiudadesListView(List<String> nombresCiudadesListView) {
        this.nombresCiudadesListView = nombresCiudadesListView;
    }  
    


    //Definicion Metodos CRUD
    public void registrar() {
        ciudadController.registrarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
    }

    public void modificarCiudad() {
        ciudadController.modificarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
    }

    public void eliminarCiudad() {
        ciudadController.eliminarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
    }
}
