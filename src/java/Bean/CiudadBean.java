/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Ciudad;
import Modelo.SmsCiudad;
import Modelo.SmsPais;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

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
    private String buscar;

    public CiudadBean() {
        ciudadView = new SmsCiudad();
        ciudadesListView = new ArrayList<>();
        nombresCiudadesListView = new ArrayList<>();
        ciudadController = new Ciudad();
        paisView = new SmsPais();

        buscar = null;
        estado = 0;
        nombre = "Registrar Ciudad";
    }

    @PostConstruct
    public void init() {
        ciudadesListView = ciudadController.getCiudades();
    }

    //Getters & Setters    
    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciu) {
        this.ciudadView = ciu;
    }

    public List<SmsCiudad> getCiudadesListView() {
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

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
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
        ciudadesListView = ciudadController.getCiudades();
    }

    public void modificar() {
        ciudadController.modificarCiudad(ciudadView, paisView);
        ciudadView = new SmsCiudad();
        paisView = new SmsPais();
        ciudadesListView = ciudadController.getCiudades();
    }

    public void eliminar() {
        ciudadController.eliminarCiudad(ciudadView);
        ciudadView = new SmsCiudad();
        paisView = new SmsPais();
        ciudadesListView = ciudadController.getCiudades();
    }
    
    public void filtrar() {
        ciudadesListView = new ArrayList<>();
        if (buscar == null) {
            ciudadesListView = ciudadController.cargarCiudades();
        } else {
            ciudadesListView = ciudadController.filtrarCiudades(buscar);
        }
    }
}
