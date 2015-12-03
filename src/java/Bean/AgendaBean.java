/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Agenda;
import Controlador.Reservacion;
import Modelo.SmsAgenda;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsReservacion;
import Modelo.SmsVehiculo;

public class AgendaBean {
    
    //Objetos necesarios en vista
    SmsAgenda agendaView;
    SmsVehiculo vehiculoView;
    SmsEmpleado empleadoView;
    SmsReservacion reservaView;
    SmsCiudad ciudad;
    
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
    
    //Metodos
    

}
