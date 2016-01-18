/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Lugar;
import Modelo.SmsCiudad;
import Modelo.SmsLugares;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Desarrollo_Planit
 */
public class LugarBean {

    //Objetos necesarios en vista
    private SmsLugares LugarView;
    private SmsLugares DLugarView;
    private List<SmsLugares> LugaresListView;
    private List<String> nombresLugaresListView;
    //Relaciones con otras clases
    private SmsCiudad CiudadView;

    //Controlador
    private Lugar LugarController;

    //Variables
    private int estado; //Controla la operacion a realizar
    private String nombre;
    private String buscar;

    public LugarBean() {
        LugarView = new SmsLugares();
        DLugarView = new SmsLugares();
        LugaresListView = new ArrayList<>();
        nombresLugaresListView = new ArrayList<>();
        CiudadView = new SmsCiudad();
        LugarController = new Lugar();

        buscar = null;
        estado = 0;
        nombre = "Registrar Lugar";
    }

    @PostConstruct
    public void init() {
        consultar();
    }

    public SmsLugares getLugarView() {
        return LugarView;
    }

    public void setLugarView(SmsLugares LugarView) {
        this.LugarView = LugarView;
    }

    public List<SmsLugares> getLugaresListView() {
        return LugaresListView;
    }

    public void setLugaresListView(List<SmsLugares> LugaresListView) {
        this.LugaresListView = LugaresListView;
    }

    public Lugar getLugarController() {
        return LugarController;
    }

    public void setLugarController(Lugar LugarController) {
        this.LugarController = LugarController;
    }

    public SmsCiudad getCiudadView() {
        return CiudadView;
    }

    public void setCiudadView(SmsCiudad CiudadView) {
        this.CiudadView = CiudadView;
    }

    public List<String> getNombresLugaresListView() {
        return nombresLugaresListView;
    }

    public void setNombresLugaresListView(List<String> nombresLugaresListView) {
        this.nombresLugaresListView = nombresLugaresListView;
    }

    public SmsLugares getDLugarView() {
        return DLugarView;
    }

    public void setDLugarView(SmsLugares DLugarView) {
        this.DLugarView = DLugarView;
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

    //Metodos CRUD
    public void registrar() {
        LugarController.registrarLugar(LugarView, CiudadView);
        LugaresListView = LugarController.consultarLugares();
        LugarView = new SmsLugares();
        CiudadView = new SmsCiudad();
    }

    public void modificar() {
        LugarController.modificarLugar(LugarView, CiudadView);
        LugaresListView = LugarController.consultarLugares();
        LugarView = new SmsLugares();
        CiudadView = new SmsCiudad();
    }

    public void eliminar() {
        LugarController.eliminarLugar(DLugarView);
        if (LugarView.equals(DLugarView)) {
            LugarView = new SmsLugares();
            CiudadView = new SmsCiudad();
            nombre = "Registrar Lugar";
            estado = 0;
        }
        DLugarView = new SmsLugares();
        LugaresListView = LugarController.consultarLugares();
    }

    public void consultar() {
        LugaresListView = new ArrayList<>();
        LugaresListView = LugarController.consultarLugares();
    }

    //Metodos propios
    public void seleccionarCrud(int i) {
        estado = i;
        if (estado == 1) {
            CiudadView.setCiudadNombre(LugarView.getSmsCiudad().getCiudadNombre());
            nombre = "Modificar Lugar";
        }
    }

    public void metodo() {
        if (estado == 0) {
            registrar();
        } else if (estado == 1) {
            modificar();
            estado = 0;
            nombre = "Registrar Lugar";
        }
    }

    public void filtrar() {
        LugaresListView = new ArrayList<>();
        if (buscar == null) {
            LugaresListView = LugarController.consultarLugares();
        } else {
            LugaresListView = LugarController.filtrarLugares(buscar);
        }
    }
    
    public void consultarLugaresCiudades(){
         nombresLugaresListView = new ArrayList<>();
         LugaresListView = new ArrayList<>();
         LugaresListView = LugarController.consultarLugaresCiudades(CiudadView.getCiudadNombre());
         for(int i=0 ; i<LugaresListView.size();i++){
             nombresLugaresListView.add(LugaresListView.get(i).getLugarNombre());
         }
    }
      
    
    public List<String> completeLugar(String query) {
        LugaresListView = new ArrayList<>();
        nombresLugaresListView = new ArrayList<>();
        LugaresListView = LugarController.consultarLugares();
        
        for (int i = 0; i < LugaresListView.size(); i++) {
            SmsLugares lugar = LugaresListView.get(i);
            if (lugar.getLugarNombre().toLowerCase().contains(query.toLowerCase())) {
                nombresLugaresListView.add(lugar.getLugarNombre());
            }
        }
       
        return nombresLugaresListView;
    }

}
