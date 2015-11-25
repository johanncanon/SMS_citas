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

public class PaisBean {

    //Objetos de vista
    protected SmsPais paisView;
    protected List<SmsPais> paisesListView;
    protected List<String> nombrePaisesListView;

    //Relacion con el controlador
    protected Pais paisController;

     //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    
    public PaisBean() {
        paisView = new SmsPais();
        paisesListView = new ArrayList<>();
        nombrePaisesListView = new ArrayList<>();
        paisController = new Pais();
        
        estado = 0;
        nombre = "Registrar Pais";
    }

    //Getters & Setters
    public SmsPais getPaisView() {
        return paisView;
    }

    public void setPaisView(SmsPais paisView) {
        this.paisView = paisView;
    }

    public List<SmsPais> getPaisesListView() {
        paisesListView = new ArrayList<>();
        paisesListView = paisController.cargarPaises();
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
    
    
    //Metodos que se comunicar con el controlador    
    public void registrar(){
        paisController.registrarPais(paisView);
        paisView = new SmsPais();
    }
    
    public void modificar(){
        paisController.modificarPais(paisView);
        paisView = new SmsPais();
    }
    
    public void eliminar(){
        paisController.eliminarPais(paisView);
        paisView = new SmsPais();
    }
    
    //Metodos Propios
    public void seleccionarCrud(int i) {
        estado = i;        
        if (estado == 1) {           
            nombre = "Modificar Pais";
        } else if (estado == 2) {
            nombre = "Eliminar Pais";            
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Pais";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Pais";
        }
    }
    
    
    

}