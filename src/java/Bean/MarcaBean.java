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

/**
 *
 * @author Desarrollo_Planit
 */
public class MarcaBean implements Serializable {

    //Objetos de vista
    private SmsMarca marcaView;
    private List<SmsMarca> marcasListView;
    private List<String> nombresMarcaView;

    //Relacion con el controlador 
    private Marca marcaController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;

    public MarcaBean() {
        marcaView = new SmsMarca();
        marcasListView = new ArrayList<>();
        nombresMarcaView = new ArrayList<>();
        marcaController = new Marca();

        estado = 0;
        nombre = "Registrar Marca";
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
        marcasListView = new ArrayList<>();
        marcasListView = marcaController.cargarMarcas();
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
    

    /*+++++++++++++++++++++++++*****************************************************************
     ****************    CREACION DE METODOS DEL BEAN      *********************************+++++*/
    public void modificar() {
        marcaController.modificarMarca(marcaView);
        marcaView = new SmsMarca();
    }

    public void eliminar() {
        marcaController.eliminarMarca(marcaView);
        marcaView = new SmsMarca();
    }

    public void registrar() {
        marcaController.registrarMarca(marcaView);
        marcaView = new SmsMarca();
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
