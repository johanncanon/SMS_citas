/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IMarcaDao;
import DAO.ImpMarcaDao;
import Controlador.Marca;
import Modelo.SmsMarca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class MarcaBean implements Serializable {

    //Objetos de vista
    private SmsMarca marcaView;
    private SmsMarca auxMarcaView;
    private List<SmsMarca> marcasListView;
    private List<String> nombresMarcaView;

    //Relacion con el controlador 
    private Marca marcaController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public MarcaBean() {
        marcaView = new SmsMarca();
        marcasListView = new ArrayList<>();
        nombresMarcaView = new ArrayList<>();
        marcaController = new Marca();
        auxMarcaView = new SmsMarca();

        buscar = null;
        estado = 0;
        nombre = "Registrar Marca";
    }
    @PostConstruct
    public void init(){
        marcasListView = marcaController.cargarMarcas();
    }

    /* GETTERS Y SETTERS **********************************************************************/
    public Marca getMarcaController() {
        return marcaController;
    }

    public void setMarcaController(Marca marcaController) {
        this.marcaController = marcaController;
    }

    public SmsMarca getMarcaView() {
        return marcaView;
    }

    public void setMarcaView(SmsMarca marcaView) {
        this.marcaView = marcaView;
    }

    public List<SmsMarca> getMarcasListView() {       
        return marcasListView;
    }

    public void setMarcasListView(List<SmsMarca> marcasListView) {
        this.marcasListView = marcasListView;
    }

    public List<String> getNombresMarcaView() {
        nombresMarcaView = new ArrayList<>();
        marcasListView = marcaController.cargarMarcas();
        for (int i = 0; i < marcasListView.size(); i++) {
            nombresMarcaView.add(marcasListView.get(i).getMarcaNombre());
        }
        return nombresMarcaView;
    }

    public void setNombresMarcaView(List<String> nombresMarcaView) {
        this.nombresMarcaView = nombresMarcaView;
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

    public SmsMarca getAuxMarcaView() {
        return auxMarcaView;
    }

    public void setAuxMarcaView(SmsMarca auxMarcaView) {
        this.auxMarcaView = auxMarcaView;
    }
    
    

    /*+++++++++++++++++++++++++*****************************************************************
     ****************    CREACION DE METODOS DEL BEAN      *********************************+++++*/
    public void modificar() {
        marcaController.modificarMarca(marcaView);
        marcaView = new SmsMarca();
        marcasListView = marcaController.cargarMarcas();
    }

    public void eliminar() {
        marcaController.eliminarMarca(marcaView);
        marcaView = new SmsMarca();
        marcasListView = marcaController.cargarMarcas();
    }

    public void registrar() {
        marcaController.registrarMarca(marcaView);
        marcaView = new SmsMarca();        
        marcasListView = marcaController.cargarMarcas();
    }

    public void filtrar() {
        marcasListView = new ArrayList<>();
        if (buscar == null) {
        marcasListView = marcaController.cargarMarcas();
        } else {            
            marcasListView = marcaController.filtrarMarcas(buscar);
            marcaView = new SmsMarca();
        }
    }

    //Metodos Propios
    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Marca";
        } else if (estado == 2) {
            eliminar();
            estado = 0;
            nombre = "Registrar Marca";
        }
    }

    public void seleccionarCRUD(int i) {
        estado = i;
        if (estado == 1) {
            nombre = "Modificar Marca";            
        } else if (estado == 2) {
            nombre = "Eliminar Marca";            
        }
    }
}
