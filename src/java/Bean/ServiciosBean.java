/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Servicio;
import Modelo.SmsServicios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ServiciosBean {

    //Objetos necesarios
    protected SmsServicios servicioView;
    protected List<SmsServicios> serviciosListView;
    protected List<String> nombreServiciosListView;

    //Relacion con el controlador
    protected Servicio servicioController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public ServiciosBean() {
        servicioController = new Servicio();
        servicioView = new SmsServicios();
        serviciosListView = new ArrayList<>();
        nombreServiciosListView = new ArrayList<>();
        
        buscar = null;
        estado = 0;
        nombre = "Registrar Servicio";
    }
    
    @PostConstruct
    public void init(){
        serviciosListView = servicioController.cargarServicio();
    }

    //Getters & Setters
    public SmsServicios getServicioView() {
        return servicioView;
    }

    public void setServicioView(SmsServicios servicioView) {
        this.servicioView = servicioView;
    }

    public List<SmsServicios> getServiciosListView() {        
        return serviciosListView;
    }

    public void setServiciosListView(List<SmsServicios> serviciosListView) {
        this.serviciosListView = serviciosListView;
    }

    public Servicio getServicioController() {
        return servicioController;
    }

    public void setServicioController(Servicio servicioController) {
        this.servicioController = servicioController;
    }

    public List<String> getNombreServiciosListView() {
        return nombreServiciosListView;
    }

    public void setNombreServiciosListView(List<String> nombreServiciosListView) {
        this.nombreServiciosListView = nombreServiciosListView;
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
    
    

    //Metodos Propios
    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Servicio";
        } 

    }

    public void seleccionarCRUD(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Servicio";
        }
    }

    //Metodos que se comunican con el controlador    
    public void registrar() {
        servicioController.registrarServicio(servicioView);
        servicioView = new SmsServicios();
        serviciosListView = servicioController.cargarServicio();
    }

    public void modificar() {
        servicioController.modificarServicio(servicioView);
        servicioView = new SmsServicios();
        serviciosListView = servicioController.cargarServicio();
    }

    public void eliminar() {
        servicioController.eliminarServicio(servicioView);
        servicioView = new SmsServicios();
        serviciosListView = servicioController.cargarServicio();
    }
    
    public void filtrar() {
        serviciosListView = new ArrayList<>();
        if (buscar == null) {
            serviciosListView = servicioController.cargarServicio();
        } else {
            serviciosListView = servicioController.filtrarServicio(buscar);
        }
    }

}
