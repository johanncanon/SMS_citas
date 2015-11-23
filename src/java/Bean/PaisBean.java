/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Pais;
import Modelo.SmsPais;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class PaisBean {

    //Objetos de vista
    private SmsPais paisView;
    private List<SmsPais> paisesListView;
    private List<String> nombrePaisesListView;
    
    //Relacion con el controlador
    private Pais paisController;
    
    public PaisBean() {
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
    
}
