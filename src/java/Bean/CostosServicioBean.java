/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Costos;
import Modelo.SmsCategoria;
import Modelo.SmsCostosServicio;
import Modelo.SmsServicios;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author CristianCamilo
 */
public class CostosServicioBean {

    //Objetos necesarios ne vista
    private SmsCostosServicio costoView;
    private SmsCostosServicio DCostoview;
    private List<SmsCostosServicio> costosListView;

    private SmsCategoria categoriaView;
    private SmsServicios servicioView;

    //Relacion con el controlador
    private Costos costosController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public CostosServicioBean() {

        costoView = new SmsCostosServicio();
        DCostoview = new SmsCostosServicio();
        costosListView = new ArrayList<>();

        categoriaView = new SmsCategoria();
        servicioView = new SmsServicios();

        costosController = new Costos();

        buscar = null;
        estado = 0;

        nombre = "Registrar Costo Servicio";
    }

    @PostConstruct
    public void init() {
        costosListView = new ArrayList<>();
        costosListView = costosController.cargarCostos();
    }

    public SmsCostosServicio getCostoView() {
        return costoView;
    }

    public void setCostoView(SmsCostosServicio costoView) {
        this.costoView = costoView;
    }

    public SmsCostosServicio getDCostoview() {
        return DCostoview;
    }

    public void setDCostoview(SmsCostosServicio DCostoview) {
        this.DCostoview = DCostoview;
    }

    public List<SmsCostosServicio> getCostosListView() {
        return costosListView;
    }

    public void setCostosListView(List<SmsCostosServicio> costosListView) {
        this.costosListView = costosListView;
    }

    public Costos getCostosController() {
        return costosController;
    }

    public void setCostosController(Costos costosController) {
        this.costosController = costosController;
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

    public SmsCategoria getCategoriaView() {
        return categoriaView;
    }

    public void setCategoriaView(SmsCategoria categoriaView) {
        this.categoriaView = categoriaView;
    }

    public SmsServicios getServicioView() {
        return servicioView;
    }

    public void setServicioView(SmsServicios servicioView) {
        this.servicioView = servicioView;
    }

    //Metodos Crud
    public void registrar() {
        costosController.registrarCosto(costoView, categoriaView, servicioView);
        costosListView = costosController.cargarCostos();
        costoView = new SmsCostosServicio();
        categoriaView = new SmsCategoria();
        servicioView = new SmsServicios();
    }

    public void modificar() {
        costosController.modificarCosto(costoView, categoriaView, servicioView);
        costosListView = costosController.cargarCostos();
        costoView = new SmsCostosServicio();
        categoriaView = new SmsCategoria();
        servicioView = new SmsServicios();
    }
    
    public void filtrar(){
       costosListView = new ArrayList<>();
        if (buscar == null) {
            costosListView = costosController.cargarCostos();
        } else {
            costosListView = costosController.filtrarCostos(buscar);
        }
    }

    public void eliminar() {
        costosController.eliminarCosto(DCostoview);
        if (costoView.equals(DCostoview)) {
            costoView = new SmsCostosServicio();
            categoriaView = new SmsCategoria();
            servicioView = new SmsServicios();
            estado = 0;

            nombre = "Registrar Costo Servicio";
        }
        DCostoview = new SmsCostosServicio();
        costosListView = costosController.cargarCostos();

    }

    //Metodos Propios
    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Costo Servicio";
        }

    }

    public void seleccionarCRUD(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Costo Servicio";
            categoriaView = costoView.getSmsCategoria();
            servicioView = costoView.getSmsServicios();
        }
    }

}
