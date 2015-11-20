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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class CiudadBean {
    
    private Ciudad ciudad;
    private SmsCiudad ciudadView;
    private List<SmsCiudad> ciudadesView;
    private List<String> listaCiudades;

    public CiudadBean() {
        ciudadView = new SmsCiudad();
        ciudadesView = new ArrayList<>();
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciu) {
        this.ciudadView = ciu;
    }

    public List<SmsCiudad> getCiudadesView() {
        ciudadesView = ciudad.getCiudades();
        return ciudadesView;
    }

    public void setCiudadesView(List<SmsCiudad> ciudades) {
        this.ciudadesView = ciudades;
    }

    public List<String> getListaCiudades() {
        listaCiudades = new ArrayList<>();
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudadesView = ciudadDao.mostrarCiudades();
        for (int i = 0; i < ciudadesView.size(); i++) {
            listaCiudades.add(ciudadesView.get(i).getCiudadNombre());
        }
        return listaCiudades;
    }

    public void setListaCiudades(List<String> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    //Definicion Metodos CRUD
    public void registrar() {
        ciudad.registrarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
    }

    public void modificarCiudad() {
        ciudad.modificarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
    }

    public void eliminarCiudad() {
        ciudad.eliminarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
    }
}
