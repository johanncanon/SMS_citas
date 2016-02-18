/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import DAO.IPaisDao;
import DAO.ImpPaisDao;
import Modelo.SmsPais;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class PaisBean implements Serializable{

    //Objetos de vista
    protected SmsPais paisView;
    protected SmsPais DPaisView;
    protected List<SmsPais> paisesListView;
    protected List<String> nombrePaisesListView;
    
    //Conexion con el DAO
     IPaisDao paisDao = new ImpPaisDao();
    
    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public PaisBean() {
        paisView = new SmsPais();
        DPaisView = new SmsPais();
        paisesListView = new ArrayList<>();
        nombrePaisesListView = new ArrayList<>();
        buscar = null;
        estado = 0;
        nombre = "Registrar Pais";
    }

    @PostConstruct
    public void init() {
        paisesListView = new ArrayList<>();
        paisesListView = paisDao.mostrarPaises();
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
        paisesListView = paisDao.mostrarPaises();
        for (int i = 0; i < paisesListView.size(); i++) {
            nombrePaisesListView.add(paisesListView.get(i).getPaisNombre());
        }
        return nombrePaisesListView;
    }

    public void setNombrePaisesListView(List<String> nombrePaisesListView) {
        this.nombrePaisesListView = nombrePaisesListView;
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
        paisDao.registrarPais(paisView);
        paisesListView = paisDao.mostrarPaises();
        paisView = new SmsPais();
    }

    public void modificar() {
        paisDao.modificarPais(paisView);
        paisesListView = paisDao.mostrarPaises();
        paisView = new SmsPais();
    }

    public void eliminar() {
        paisDao.eliminarPais(DPaisView);
        if (paisView.equals(DPaisView)) {
            paisView = new SmsPais();
            nombre = "Registrar Pais";
            estado = 0;
        }

        paisesListView = paisDao.mostrarPaises();
        DPaisView = new SmsPais();
    }

    public void filtrar() {
        paisesListView = new ArrayList<>();
        if (buscar == null) {
            paisesListView = paisDao.mostrarPaises();
        } else {
            paisesListView = paisDao.filtrarPais(buscar);
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
