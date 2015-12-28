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
    protected SmsLugares LugarView;
    protected List<SmsLugares> LugaresListView;

    //Relaciones con otras clases
    protected SmsCiudad CiudadView;

    //Controlador
    protected Lugar LugarController;

    public LugarBean() {
        LugarView = new SmsLugares();
        LugaresListView = new ArrayList<>();

        CiudadView = new SmsCiudad();
        LugarController = new Lugar();
    }
    
    @PostConstruct
    public void init(){
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

    //Metodos CRUD
    public void registrar() {
        LugarController.registrarLugar(LugarView, CiudadView);
        LugarView = new SmsLugares();
        CiudadView = new SmsCiudad();
    }

    public void modificar() {
        LugarController.modificarLugar(LugarView, CiudadView);
        LugarView = new SmsLugares();
        CiudadView = new SmsCiudad();
    }

    public void eliminar() {
        LugarController.eliminarLugar(LugarView);
        LugarView = new SmsLugares();
    }

    public void consultar() {
        LugaresListView = new ArrayList<>();
        LugaresListView = LugarController.consultarLugares();
    }

}
