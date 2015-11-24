/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ICiudadDao;
import DAO.IPaisDao;
import DAO.ImpCiudadDao;
import DAO.ImpPaisDao;
import Modelo.SmsCiudad;
import Modelo.SmsPais;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Pais {

    //Atributos
    private SmsPais pais;
    private List<SmsPais> paises;

    public Pais() {
        pais = new SmsPais();
        paises = new ArrayList<>();
    }

    //Getters & Setters
    public SmsPais getPais() {
        return pais;
    }

    public void setPais(SmsPais pais) {
        this.pais = pais;
    }

    public List<SmsPais> getPaises() {
        return paises;
    }

    public void setPaises(List<SmsPais> paises) {
        this.paises = paises;
    }

    //Metodos CRUD
    public void registrarPais(SmsPais p) {
        pais = p;
        IPaisDao paisDao = new ImpPaisDao();
        paisDao.registrarPais(pais);
    }

    public void modificarPais(SmsPais p) {
        pais = p;
        IPaisDao paisDao = new ImpPaisDao();
        paisDao.modificarPais(pais);
    }

    public void eliminarPais(SmsPais p) {
        pais = p;
        IPaisDao paisDao = new ImpPaisDao();
        paisDao.eliminarPais(pais);
    }

    public List<SmsPais> cargarPaises() {
        paises = new ArrayList<>();
        IPaisDao paisDao = new ImpPaisDao();
        paises = paisDao.mostrarPaises();
        return paises;
    }

    //Metodos
    public SmsPais validarPais(SmsCiudad ciudad) {
        SmsPais p = ciudad.getSmsPais();
        paises = cargarPaises();
        for (int i = 0; i < paises.size(); i++) {
            if (p.equals(paises.get(i))) {
                pais = paises.get(i);
            }
        }

        return pais;
    }
}
