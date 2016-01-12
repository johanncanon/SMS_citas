/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Pais;
import Modelo.SmsPais;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class PaisBean {

    //Objetos de vista
    protected SmsPais paisView;
    protected SmsPais DPaisView;
    protected List<SmsPais> paisesListView;
    protected List<String> nombrePaisesListView;

    //Relacion con el controlador
    protected Pais paisController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public PaisBean() {
        paisView = new SmsPais();
        DPaisView = new SmsPais();
        paisesListView = new ArrayList<>();
        nombrePaisesListView = new ArrayList<>();
        paisController = new Pais();
        buscar = null;
        estado = 0;
        nombre = "Registrar Pais";
    }

    @PostConstruct
    public void init() {
        paisesListView = new ArrayList<>();
        paisesListView = paisController.cargarPaises();
    }

    //Getters & Setters
    public SmsPais getPaisView() {
        return paisView;
    }

    public void setPaisView(SmsPais paisView) {
        this.paisView = paisView;
    }

    public List<SmsPais> getPaisesListView() {
        return paisesListView;
    }

    public void setPaisesListView(List<SmsPais> paisesListView) {
        this.paisesListView = paisesListView;
    }

    public List<String> getNombrePaisesListView() {
        nombrePaisesListView = new ArrayList<>();
        paisesListView = paisController.cargarPaises();
        for (int i = 0; i < paisesListView.size(); i++) {
            nombrePaisesListView.add(paisesListView.get(i).getPaisNombre());
        }
        return nombrePaisesListView;
    }

    public void setNombrePaisesListView(List<String> nombrePaisesListView) {
        this.nombrePaisesListView = nombrePaisesListView;
    }

    public Pais getPaisController() {
        return paisController;
    }

    public void setPaisController(Pais paisController) {
        this.paisController = paisController;
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

    public SmsPais getDPaisView() {
        return DPaisView;
    }

    public void setDPaisView(SmsPais DPaisView) {
        this.DPaisView = DPaisView;
    }

    //Metodos que se comunicar con el controlador    
    public void registrar() {
        paisController.registrarPais(paisView);
        paisesListView = paisController.cargarPaises();
        paisView = new SmsPais();
    }

    public void modificar() {
        paisController.modificarPais(paisView);
        paisesListView = paisController.cargarPaises();
        paisView = new SmsPais();
    }

    public void eliminar() {
        paisController.eliminarPais(DPaisView);
        if (paisView.equals(DPaisView)) {
            paisView = new SmsPais();
            nombre = "Registrar Pais";
            estado = 0;
        }

        paisesListView = paisController.cargarPaises();
        DPaisView = new SmsPais();
    }

    public void filtrar() {
        paisesListView = new ArrayList<>();
        if (buscar == null) {
            paisesListView = paisController.cargarPaises();
        } else {
            paisesListView = paisController.filtrarPaises(buscar);
        }
    }

    //Metodos Propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Pais";
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Pais";
        }
    }

}
