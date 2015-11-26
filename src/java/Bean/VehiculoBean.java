/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Vehiculo;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
import Modelo.SmsVehiculo;
import java.util.List;
import javax.annotation.PostConstruct;

public class VehiculoBean {

    //Objetos de vista    
    private SmsCategoria categoriaView;
    private SmsCiudad ciudadView;
    private SmsProveedor proveedorView;
    private SmsReferencia refenciaView;
    private SmsVehiculo vehiculoView;
    private SmsEstadovehiculo estadoVehiculoView;
    private List<SmsVehiculo> vehiculosListView;
    private List<String> PlacasVehiculosListView;

    //Relacion con el controlodar
    private Vehiculo vehiculoController;

    public VehiculoBean() {
        vehiculoController = new Vehiculo();
        vehiculoView = new SmsVehiculo();
        categoriaView = new SmsCategoria();
        ciudadView = new SmsCiudad();
        proveedorView = new SmsProveedor();
        refenciaView = new SmsReferencia();
        estadoVehiculoView = new SmsEstadovehiculo();
    }

    @PostConstruct
    public void init(){
    }
    
    //Getters & Setters
    public SmsCategoria getCategoriaView() {
        return categoriaView;
    }

    public void setCategoriaView(SmsCategoria categoriaView) {
        this.categoriaView = categoriaView;
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciudadView) {
        this.ciudadView = ciudadView;
    }

    public SmsProveedor getProveedorView() {
        return proveedorView;
    }

    public void setProveedorView(SmsProveedor proveedorView) {
        this.proveedorView = proveedorView;
    }

    public SmsReferencia getRefenciaView() {
        return refenciaView;
    }

    public void setRefenciaView(SmsReferencia refenciaView) {
        this.refenciaView = refenciaView;
    }

    public SmsEstadovehiculo getEstadoVehiculoView() {
        return estadoVehiculoView;
    }

    public void setEstadoVehiculoView(SmsEstadovehiculo estadoVehiculoView) {
        this.estadoVehiculoView = estadoVehiculoView;
    }

    public List<SmsVehiculo> getVehiculosListView() {
        return vehiculosListView;
    }

    public void setVehiculosListView(List<SmsVehiculo> vehiculosListView) {
        this.vehiculosListView = vehiculosListView;
    }

    public List<String> getPlacasVehiculosListView() {
        return PlacasVehiculosListView;
    }

    public void setPlacasVehiculosListView(List<String> PlacasVehiculosListView) {
        this.PlacasVehiculosListView = PlacasVehiculosListView;
    }

    public Vehiculo getVehiculoController() {
        return vehiculoController;
    }

    public void setVehiculoController(Vehiculo vehiculoController) {
        this.vehiculoController = vehiculoController;
    }   

    public SmsVehiculo getVehiculoView() {
        return vehiculoView;
    }

    public void setVehiculoView(SmsVehiculo veh) {
        this.vehiculoView = veh;
    }       
  
 
    //Definicion de metodos VEHICULO
    public void registrar() {
        vehiculoController.registrarVehiculo(categoriaView, proveedorView, ciudadView, refenciaView, vehiculoView);
        estadoVehiculoView.setSmsVehiculo(vehiculoView);
        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
    }

    public void modificar() {
        vehiculoController.modficarVehiculo(categoriaView, proveedorView, ciudadView, refenciaView, vehiculoView);
        estadoVehiculoView.setSmsVehiculo(vehiculoView);
        refenciaView = new SmsReferencia();
        categoriaView = new SmsCategoria();
        proveedorView = new SmsProveedor();
        ciudadView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
    }

    public void eliminar() {
        vehiculoController.eliminarVehiculo(vehiculoView);
        estadoVehiculoView.setSmsVehiculo(vehiculoView);
        refenciaView= new SmsReferencia();
        categoriaView= new SmsCategoria();
        proveedorView= new SmsProveedor();
        ciudadView= new SmsCiudad();
        vehiculoView = new SmsVehiculo();
    }

}
