/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Referencia;
import Modelo.SmsMarca;
import Modelo.SmsReferencia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class ReferenciaBean implements Serializable {

    //Objetos de vista
    private SmsReferencia referenciaView;
    private List<SmsReferencia> referenciasListView;    
    private List<String> nombresReferenciaListView;
    private SmsMarca marcaView;

    //Relacion con el controlador
    private Referencia referenciaController;
    
     //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;
    
    public ReferenciaBean() {
        referenciaView = new SmsReferencia();        
        referenciaController = new Referencia();
        referenciasListView = new ArrayList<>();
        nombresReferenciaListView = new ArrayList<>();
        marcaView = new SmsMarca();   
        
        buscar = null;
        estado = 0;
        nombre = "Registrar Referencia";
    }    
    
    @PostConstruct
    public void init(){
        referenciasListView = referenciaController.cargarReferencias();
    }
    /**
     * ******************************************************
     ********* getters y setters *************
     */
    public SmsMarca getMarcaView() {
        return marcaView;
    }

    public void setMarcaView(SmsMarca marcaView) {
        this.marcaView = marcaView;
    }

    public Referencia getReferenciaController() {
        
        return referenciaController;
    }

    public void setReferenciaController(Referencia referenciaController) {
        this.referenciaController = referenciaController;
    }   

    public SmsReferencia getReferenciaView() {
        return referenciaView;
    }

    public void setReferenciaView(SmsReferencia referenciaView) {
        this.referenciaView = referenciaView;
    }

    public List<SmsReferencia> getReferenciasListView() {        
        return referenciasListView;
    }

    public void setReferenciasListView(List<SmsReferencia> referenciasListView) {
        this.referenciasListView = referenciasListView;
    }

    public List<String> getNombresReferenciaListView() {
        nombresReferenciaListView = new ArrayList<>();        
        referenciasListView = referenciaController.cargarReferencias();
        for (int i = 0; i < referenciasListView.size(); i++) {
            nombresReferenciaListView.add(referenciasListView.get(i).getReferenciaNombre());
        }
        return nombresReferenciaListView;
    }

    public void setNombresReferenciaListView(List<String> nombresReferenciaListView) {
        this.nombresReferenciaListView = nombresReferenciaListView;
    }   

    public SmsMarca getMarcasView() {
        return marcaView;
    }

    public void setMarcasView(SmsMarca marcaView) {
        this.marcaView = marcaView;
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
    
    
    

    /* METODOS DEL BEAN
     ********************************************************************************/
    public void modificar() {
        referenciaController.modificarReferencia(referenciaView, marcaView);
        referenciaView = new SmsReferencia();
        marcaView = new SmsMarca();
        referenciasListView = referenciaController.cargarReferencias();
    }

    public void eliminar() {
        referenciaController.eliminarReferencia(referenciaView, marcaView);
        referenciaView = new SmsReferencia();
        marcaView = new SmsMarca();
        referenciasListView = referenciaController.cargarReferencias();
    }

    public void registrar() {
        referenciaController.registrarReferencia(referenciaView, marcaView);
        referenciaView = new SmsReferencia();
        marcaView = new SmsMarca();
        referenciasListView = referenciaController.cargarReferencias();
    }    
    
    public void filtrar() {
        referenciasListView = new ArrayList<>();
        if (buscar == null) {
            referenciasListView = referenciaController.cargarReferencias();
        } else {
            referenciasListView = referenciaController.filtrarReferencias(buscar);
        }
    }
    //Metodos Propios
    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Referencia";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Referencia";
        }
    }

    public void seleccionarCRUD(int i) {
        estado = i;
        if (estado == 1) {
            marcaView = referenciaView.getSmsMarca();
            nombre = "Modificar Referencia";
        } else if (estado == 2) {
            marcaView = referenciaView.getSmsMarca();
            nombre = "Eliminar Referencia";
        }
    }
    
    
}
