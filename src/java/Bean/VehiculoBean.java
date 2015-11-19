/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IVehiculoDao;
import Controlador.Vehiculo;
import DAO.ICategoriaDao;
import DAO.ICiudadDao;
import DAO.IProveedorDao;
import DAO.IReferenciaDao;
import DAO.ImpCategoriaDao;
import DAO.ImpCiudadDao;
import DAO.ImpProveedorDao;
import DAO.ImpReferenciaDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
import Modelo.SmsVehiculo;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class VehiculoBean {

    /**
     * Creates a new instance of VehiculoBean
     */
    private Vehiculo vehiculo;
    private SmsCategoria catView;
    private SmsCiudad ciuView;
    private SmsProveedor provView;
    private SmsReferencia refeView;
    private SmsVehiculo vehiculoView;
    private SmsEstadovehiculo estVehiView;//instancia de Estado Vehiculo
    private List<SmsVehiculo> vehiculosView;
    private List<String> vehiculosViewS;

    
    public VehiculoBean() {
        vehiculo = new Vehiculo();
        vehiculoView = new SmsVehiculo();
        catView = new SmsCategoria();
        ciuView = new SmsCiudad();
        provView = new SmsProveedor();
        refeView = new SmsReferencia();
        estVehiView = new SmsEstadovehiculo();
        
    }

    /**
     * **************************************************************************
     */
    public SmsCategoria getCatView() {
        return catView;
    }

    public void setCatView(SmsCategoria catView) {
        this.catView = catView;
    }

    public SmsCiudad getCiuView() {
        return ciuView;
    }

    public void setCiuView(SmsCiudad ciuView) {
        this.ciuView = ciuView;
    }

    public SmsProveedor getProvView() {
        return provView;
    }

    public void setProvView(SmsProveedor provView) {
        this.provView = provView;
    }

    public SmsReferencia getRefeView() {
        return refeView;
    }

    public void setRefeView(SmsReferencia refeView) {
        this.refeView = refeView;
    }

    public SmsEstadovehiculo getEstVehiView() {
        return estVehiView;
    }

    public void setEstVehiView(SmsEstadovehiculo estVehiView) {
        this.estVehiView = estVehiView;
    }

    public List<String> getVehiculosViewS() {
        return vehiculosViewS;
    }

    public void setVehiculosViewS(List<String> vehiculosViewS) {
        this.vehiculosViewS = vehiculosViewS;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public SmsVehiculo getVehiculoView() {
        return vehiculoView;
    }

    public void setVehiculoView(SmsVehiculo veh) {
        this.vehiculoView = veh;
    }
    /*  ********  LISTAS DE OBJETOS DE RELACIONES CON VEHICULO  ********************************************************/
    /*  *****************************************************************************************************************/

    public List<SmsVehiculo> getVehiculosView() {
        IVehiculoDao vehDao = new ImpVehiculoDao();
        vehiculosView = vehDao.mostrarVehiculo();
        return vehiculosView;
    }

    public void setVehiculosView(List<SmsVehiculo> veh) {
        this.vehiculosView = veh;
    }

    /*  *****************************************************************************************************************/
    //Definicion de metodos VEHICULO
    public void registrar() {
        vehiculo.regisrtarVehiculo(catView, provView, ciuView, refeView, vehiculoView);
        estVehiView.setSmsVehiculo(vehiculoView);
        refeView = new SmsReferencia();
        catView = new SmsCategoria();
        provView = new SmsProveedor();
        ciuView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
    }

    public void modificar() {
        vehiculo.modficarVehiculo(catView, provView, ciuView, refeView, vehiculoView);
        estVehiView.setSmsVehiculo(vehiculoView);
        refeView = new SmsReferencia();
        catView = new SmsCategoria();
        provView = new SmsProveedor();
        ciuView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
    }

    public void eliminar() {
        vehiculo.eliminarVehiculo(catView, provView, ciuView, refeView, vehiculoView);
        estVehiView.setSmsVehiculo(vehiculoView);
        refeView = new SmsReferencia();
        catView = new SmsCategoria();
        provView = new SmsProveedor();
        ciuView = new SmsCiudad();
        vehiculoView = new SmsVehiculo();
    }

    /**
     * ***************** DEFINICION DE METODOS DE ESTADO DE VEHICULO
     * *********************
     */
}
