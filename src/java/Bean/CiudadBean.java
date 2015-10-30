/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.ICiudadDao;
import DAO.ImpCiudadDao;
import Modelo.SmsCiudad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class CiudadBean {

    private SmsCiudad ciudad;
    private List<SmsCiudad> ciudades;
    private List<String> listaCiudadades;

    public CiudadBean() {
        ciudad = new SmsCiudad();
    }

    public SmsCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(SmsCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<SmsCiudad> getCiudades() {
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudades = ciudadDao.mostrarCiudades();
        return ciudades;
    }

    public void setCiudades(List<SmsCiudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<String> getListaCiudadades() {
        listaCiudadades = new ArrayList<>();
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudades = ciudadDao.mostrarCiudades();
        for (int i = 0; i < ciudades.size(); i++) {
            listaCiudadades.add(ciudades.get(i).getCiudadNombre());
        }
        return listaCiudadades;
    }

    public void setListaCiudadades(List<String> listaCiudadades) {
        this.listaCiudadades = listaCiudadades;
    }

    //Definicion Metodos CRUD
    public void registrarCiudad() {
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudadDao.registrarCiudad(ciudad);
        ciudad = new SmsCiudad();
    }

    public void modificarCiudad() {
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudadDao.modificarCiudad(ciudad);
        ciudad = new SmsCiudad();
    }

    public void eliminarCiudad() {
        ICiudadDao ciudadDao = new ImpCiudadDao();
        ciudadDao.eliminarCiudad(ciudad);
        ciudad = new SmsCiudad();
    }
}
