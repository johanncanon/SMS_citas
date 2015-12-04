/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Agenda;
import Controlador.Reservacion;
import Modelo.SmsAgenda;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsReservacion;
import Modelo.SmsVehiculo;
import org.primefaces.event.FlowEvent;

public class AgendaBean {

    //Objetos necesarios en vista
    SmsAgenda agendaView;
    SmsVehiculo vehiculoView;
    SmsEmpleado empleadoView;
    SmsReservacion reservaView;
    SmsCategoria categoriaView;
    SmsCiudad ciudadView;
    
    private boolean skip = false;

    //Relacion con los controladores
    Reservacion reservacionController;
    Agenda agendaController;

    
    public AgendaBean() {
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
    
    
    
    
    //Metodos
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;//reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
}
