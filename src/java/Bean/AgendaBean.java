/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Agenda;
import Controlador.Empleado;
import Controlador.Reservacion;
import Controlador.Usuario;
import Controlador.Vehiculo;
import Modelo.SmsAgenda;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsReservacion;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.FlowEvent;

public class AgendaBean {

    //Objetos necesarios en vista
    protected SmsAgenda agendaView;
    protected SmsVehiculo vehiculoView;
    protected SmsEmpleado empleadoView;
    protected SmsReservacion reservaView;
    protected SmsCategoria categoriaView;
    protected SmsCiudad ciudadView;
    protected SmsUsuario clienteView;

    protected List<SmsVehiculo> vehiculosListView;
    protected List<SmsEmpleado> empleadosListView;
    protected List<SmsAgenda> agendaListView;

    private boolean skip = false;
    private boolean SelecVeh;
    private boolean SelecCon;

    //Relacion con los controladores
    Reservacion reservacionController;
    Agenda agendaController;
    Vehiculo vehiculoController;
    Empleado empleadoController;
    Usuario usuarioController;

    public AgendaBean() {

        agendaView = new SmsAgenda();
        vehiculoView = new SmsVehiculo();
        empleadoView = new SmsEmpleado();
        reservaView = new SmsReservacion();
        categoriaView = new SmsCategoria();
        ciudadView = new SmsCiudad();
        clienteView = new SmsUsuario();

        vehiculosListView = new ArrayList<>();
        empleadosListView = new ArrayList<>();
        agendaListView = new ArrayList<>();

        vehiculoController = new Vehiculo();
        empleadoController = new Empleado();
        agendaController = new Agenda();
        reservacionController = new Reservacion();
        usuarioController = new Usuario();

        SelecVeh = false;
        SelecCon = false;
    }

    public SmsAgenda getAgendaView() {
        return agendaView;
    }

    public void setAgendaView(SmsAgenda agendaView) {
        this.agendaView = agendaView;
    }

    public SmsVehiculo getVehiculoView() {
        return vehiculoView;
    }

    public void setVehiculoView(SmsVehiculo vehiculoView) {
        this.vehiculoView = vehiculoView;
    }

    public SmsEmpleado getEmpleadoView() {
        return empleadoView;
    }

    public void setEmpleadoView(SmsEmpleado empleadoView) {
        this.empleadoView = empleadoView;
    }

    public SmsReservacion getReservaView() {
        return reservaView;
    }

    public void setReservaView(SmsReservacion reservaView) {
        this.reservaView = reservaView;
    }

    public SmsCiudad getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(SmsCiudad ciudadView) {
        this.ciudadView = ciudadView;
    }

    public Reservacion getReservacionController() {
        return reservacionController;
    }

    public void setReservacionController(Reservacion reservacionController) {
        this.reservacionController = reservacionController;
    }

    public Agenda getAgendaController() {
        return agendaController;
    }

    public void setAgendaController(Agenda agendaController) {
        this.agendaController = agendaController;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public SmsCategoria getCategoriaView() {
        return categoriaView;
    }

    public void setCategoriaView(SmsCategoria categoriaView) {
        this.categoriaView = categoriaView;
    }

    public List<SmsVehiculo> getVehiculosListView() {
        return vehiculosListView;
    }

    public void setVehiculosListView(List<SmsVehiculo> vehiculosListView) {
        this.vehiculosListView = vehiculosListView;
    }

    public List<SmsEmpleado> getEmpleadosListView() {
        return empleadosListView;
    }

    public void setEmpleadosListView(List<SmsEmpleado> empleadosListView) {
        this.empleadosListView = empleadosListView;
    }

    public Vehiculo getVehiculoController() {
        return vehiculoController;
    }

    public void setVehiculoController(Vehiculo vehiculoController) {
        this.vehiculoController = vehiculoController;
    }

    public Empleado getEmpleadoController() {
        return empleadoController;
    }

    public void setEmpleadoController(Empleado empleadoController) {
        this.empleadoController = empleadoController;
    }

    public boolean isSelecVeh() {
        return SelecVeh;
    }

    public void setSelecVeh(boolean SelecVeh) {
        this.SelecVeh = SelecVeh;
    }

    public boolean isSelecCon() {
        return SelecCon;
    }

    public void setSelecCon(boolean SelecCon) {
        this.SelecCon = SelecCon;
    }

    public List<SmsAgenda> getAgendaListView() {
        return agendaListView;
    }

    public void setAgendaListView(List<SmsAgenda> agendaListView) {
        this.agendaListView = agendaListView;
    }

    public Usuario getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(Usuario usuarioController) {
        this.usuarioController = usuarioController;
    }

    public SmsUsuario getClienteView() {
        return clienteView;
    }

    public void setClienteView(SmsUsuario clienteView) {
        this.clienteView = clienteView;
    }

    //Metodos    
    //CRUD
    public void registrarAgenda() {
        clienteView = usuarioController.obtenerSesion();//obtiene la informacion del cliente que ha iniciado sesion        
        agendaController.registrarAgenda(empleadoView, vehiculoView, agendaView);
        reservacionController.registrarReservacion(clienteView, agendaView, ciudadView, reservaView);
    }

    //Especificos
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;//reset in case user goes back
            return "confirm";
        } else {

            if (ciudadView.getCiudadNombre() != null && agendaView.getAgendaFechaInicio() != null && agendaView.getAgendaFechaLlegada() != null
                    && agendaView.getAgendaHoraInicio() != null && agendaView.getAgendaHoraLlegada() != null) {
                agendaListView = agendaController.cargarAgendas();
                if (agendaListView.isEmpty()) {
                    vehiculosListView.clear();
                    empleadosListView.clear();
                    vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
                    empleadosListView = empleadoController.consultarEmpleadosCiudad(ciudadView);
                } else {
                    vehiculosListView.clear();
                    empleadosListView.clear();
                    vehiculosListView = vehiculoController.consultarVehiculosDisponible(agendaView, ciudadView);
                    empleadosListView = empleadoController.consultarEmpleadosDisponibles(agendaView, ciudadView);
                }
            }
            return event.getNewStep();
        }
    }

    public void SeleccionarVehiculo() {
        SelecVeh = true;
    }

    public void SeleccionarConductor() {
        SelecCon = true;
    }

}
