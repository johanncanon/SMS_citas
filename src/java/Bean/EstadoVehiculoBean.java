/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Modelo.SmsEstadovehiculo;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import Controlador.EstadoVehiculo;
import DAO.IEstadoVehiculoDao;
import DAO.ImpEstadoVehiculoDao;

/**
 *
 * @author SISTEMAS
 */
@Named(value = "estadoVehiculoBean")
@Dependent
public class EstadoVehiculoBean {

    private SmsEstadovehiculo estVehView;
    private List<SmsEstadovehiculo> estVehiculosView;
       
    /**
     * Creates a new instance of EstadoVehiculoBean
     */
    public EstadoVehiculoBean() {
        estVehView = new SmsEstadovehiculo();
        estadoVehiculo = new EstadoVehiculo();
    }

    /**
     * *************************************
     */
       
    private EstadoVehiculo estadoVehiculo;
    
    public SmsEstadovehiculo getEstVehView() {
        return estVehView;
    }

    public void setEstVehView(SmsEstadovehiculo estVehView) {
        this.estVehView = estVehView;
    }

    public List<SmsEstadovehiculo> getEstVehiculosView() {
        IEstadoVehiculoDao linkDao = new ImpEstadoVehiculoDao();
        estVehiculosView = linkDao.mostrarEstadoVehiculo();
        return estVehiculosView;
    }

    public void setEstVehiculosView(List<SmsEstadovehiculo> estVehiculosView) {
        this.estVehiculosView = estVehiculosView;
    }

    public EstadoVehiculo getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(EstadoVehiculo estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    /**
     * ************************************
     */
    
    public void registrar() {
        estadoVehiculo.registrarEstVeh(estVehView);
        estVehView = new SmsEstadovehiculo();
    }
    
    public void modificar(){
        estadoVehiculo.modificarEstVeh(estVehView);
        estVehView = new SmsEstadovehiculo();
    }
    
    public void eliminar(){
        estadoVehiculo.eliminarEstVeh(estVehView);
        estVehView = new SmsEstadovehiculo();
    }
    
    

}
