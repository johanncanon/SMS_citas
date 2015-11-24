/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.ICiudadDao;
import DAO.ImpCiudadDao;
import Controlador.Ciudad;
import Controlador.Pais;
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
    protected SmsPais paisView;

    //Relacion con el controlador
    private Ciudad ciudadController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;

    public CiudadBean() {
        ciudadView = new SmsCiudad();
        ciudadesListView = new ArrayList<>();
        nombresCiudadesListView = new ArrayList<>();
        ciudadController = new Ciudad();
        paisView = new SmsPais();

        estado = 0;
        nombre = "Registrar Ciudad";
    }

    //Getters & Setters    
    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciu) {
        this.ciudadView = ciu;
    }

    public List<SmsCiudad> getCiudadesListView() {
        ciudadesListView = new ArrayList<>();
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

    public SmsPais getPaisView() {
        return paisView;
    }

    public void setPaisView(SmsPais paisView) {
        this.paisView = paisView;
    }

    public List<String> getNombresCiudadesListView() {
        nombresCiudadesListView = new ArrayList<>();
        ciudadesListView = ciudadController.cargarCiudades();
        for (int i = 0; i < ciudadesListView.size(); i++) {
            nombresCiudadesListView.add(ciudadesListView.get(i).getCiudadNombre());
        }
        return nombresCiudadesListView;
    }

    public void setNombresCiudadesListView(List<String> nombresCiudadesListView) {
        this.nombresCiudadesListView = nombresCiudadesListView;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {
            paisView.setPaisNombre(ciudadView.getSmsPais().getPaisNombre());
            nombre = "Modificar Ciudad";
        } else if (estado == 2) {
            paisView.setPaisNombre(ciudadView.getSmsPais().getPaisNombre());
            nombre = "Eliminar Ciudad";
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Ciudad";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Ciudad";
        }
    }

    //Definicion Metodos CRUD
    public void registrar() {
        ciudadController.registrarCiudad(ciudadView, paisView);
        ciudadView = new SmsCiudad();
        paisView = new SmsPais();
    }

    public void modificar() {
        ciudadController.modificarCiudad(ciudadView, paisView);
        ciudadView = new SmsCiudad();
        paisView = new SmsPais();
    }

    public void eliminar() {
        ciudadController.eliminarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
        paisView = new SmsPais();
    }
}
