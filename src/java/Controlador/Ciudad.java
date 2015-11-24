/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ICiudadDao;
import DAO.ImpCiudadDao;
import Modelo.SmsCiudad;
import Modelo.SmsPais;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Ciudad {

    private SmsCiudad ciudad;
    private List<SmsCiudad> ciudades;

    public Ciudad() {
        ciudad = new SmsCiudad();
    }

    public SmsCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(SmsCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<SmsCiudad> getCiudades() {
        ICiudadDao linkDao = new ImpCiudadDao();
        ciudades = linkDao.mostrarCiudades();
        return ciudades;
    }

    public void setCiudades(List<SmsCiudad> ciudades) {
        this.ciudades = ciudades;
    }

    //Metodos CRUD
    public void registrarCiudad(SmsCiudad c, SmsPais p) {
        ciudad = c;SmsPais pais = p;
        ciudad.setSmsPais(pais);
        ICiudadDao linkDao = new ImpCiudadDao();
        linkDao.registrarCiudad(ciudad);
    }
    
    public void modificarCiudad(SmsCiudad c, SmsPais p){
        ciudad = c;SmsPais pais = p;
        ciudad.setSmsPais(pais);
        ICiudadDao linkDao = new ImpCiudadDao();
        linkDao.modificarCiudad(ciudad);
    }
    
    public void eliminarCiudad(SmsCiudad c){
        ciudad = c;
        ICiudadDao linkDao = new ImpCiudadDao();
        linkDao.eliminarCiudad(ciudad);
    }
    
    public List<SmsCiudad> cargarCiudades(){
        ciudades = new ArrayList<>();
        ICiudadDao linkDao = new ImpCiudadDao();
        ciudades = linkDao.mostrarCiudades();
        return ciudades;
    }
}
