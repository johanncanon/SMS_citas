/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Agenda;
import Controlador.Empleado;
import Controlador.Lugar;
import Controlador.Reservacion;
import Controlador.SendEmail;
import Controlador.Usuario;
import Controlador.Vehiculo;
import Modelo.SmsAgenda;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsCostosServicio;
import Modelo.SmsEmpleado;
import Modelo.SmsLugares;
import Modelo.SmsReservacion;
import Modelo.SmsServicios;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;

import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

public class AgendaBean {

    //Objetos necesarios en vista
    private SmsAgenda agendaView;
    private SmsVehiculo vehiculoView;
    private SmsEmpleado empleadoView;
    private SmsReservacion reservaView;
    private SmsCategoria categoriaView;
    private SmsCiudad ciudadView;
    private SmsUsuario clienteView;
    
    private SmsAgenda MagendaView;
    private SmsVehiculo MvehiculoView;
    private SmsEmpleado MempleadoView;
    private SmsReservacion MreservaView;
    private SmsCategoria McategoriaView;
    private SmsCiudad MciudadView;
    private SmsUsuario MclienteView;
    
    
   
    private SmsCostosServicio costoServicioView;
    private SmsServicios servicioView;
    private SmsUsuario sesion; //objeto donde guardaremos los datos del usuario logueado
    
    private String HoraInicio;
    private String HoraEntrega;

    private List<SmsVehiculo> vehiculosListView;
    private List<SmsEmpleado> empleadosListView;
    private List<SmsAgenda> agendaListView;
    private List<SmsLugares> LugaresListView;
    private List<String> nombresLugaresListView;

    private boolean skip = false;//Controla la transicion entre las pestañas del panel de reserva
    //Controlan la seleccion de los vehiculos y los empleados
    private boolean SelecVeh;
    private boolean SelecCon;

    //Relacion con los controladores
    Reservacion reservacionController;
    Agenda agendaController;
    Vehiculo vehiculoController;
    Empleado empleadoController;
    Usuario usuarioController;
    SendEmail emailController;
    Lugar lugarController;

    //Sesion  
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    //variables para vista de reservacion    
    private List<SmsAgenda> vistasReserva;

    //VARIABLES PARA CREAR EL SCHEDULE DE PRIMEFACES
    private ScheduleModel eventoModelo;
    private ScheduleEvent evento;

    public AgendaBean() {

        agendaView = new SmsAgenda();
        vehiculoView = new SmsVehiculo();
        empleadoView = new SmsEmpleado();
        reservaView = new SmsReservacion();
        categoriaView = new SmsCategoria();
        ciudadView = new SmsCiudad();
        clienteView = new SmsUsuario();
        costoServicioView = new SmsCostosServicio();
        servicioView = new SmsServicios();
        
        MagendaView = new SmsAgenda();
        MvehiculoView = new SmsVehiculo();
        MempleadoView = new SmsEmpleado();
        MreservaView = new SmsReservacion();
        McategoriaView = new SmsCategoria();
        MciudadView = new SmsCiudad();
        MclienteView = new SmsUsuario();

        vehiculosListView = new ArrayList<>();
        empleadosListView = new ArrayList<>();
        agendaListView = new ArrayList<>();
        LugaresListView = new ArrayList<>();
        nombresLugaresListView = new ArrayList<>();

        vehiculoController = new Vehiculo();
        empleadoController = new Empleado();
        agendaController = new Agenda();
        reservacionController = new Reservacion();
        usuarioController = new Usuario();
        emailController = new SendEmail();
        lugarController = new Lugar();

        SelecVeh = false;
        SelecCon = false;

        //VARIABLES PARA MOSTRAR RESERVACION DE LA AGENDA       
        vistasReserva = new ArrayList<>();

        //VARIABLES PARA CREAR EL SCHEDULE DE PRIMEFACES
        eventoModelo = new DefaultScheduleModel();
        evento = new DefaultScheduleEvent();

    }

    @PostConstruct
    public void init() {
         //Obtenemos la informacion de sesion del usuario autentificado 
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        sesion = (SmsUsuario) httpServletRequest.getSession().getAttribute("Sesion");

        reservacionClienteAgenda();
        addEventoCalendario();
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

    public SmsCostosServicio getCostoServicioView() {
        return costoServicioView;
    }

    public void setCostoServicioView(SmsCostosServicio costoServicioView) {
        this.costoServicioView = costoServicioView;
    }

    public SmsServicios getServicioView() {
        return servicioView;
    }

    public void setServicioView(SmsServicios servicioView) {
        this.servicioView = servicioView;
    }

    public List<SmsLugares> getLugaresListView() {
        return LugaresListView;
    }

    public void setLugaresListView(List<SmsLugares> LugaresListView) {
        this.LugaresListView = LugaresListView;
    }

    public List<String> getNombresLugaresListView() {
        return nombresLugaresListView;
    }

    public void setNombresLugaresListView(List<String> nombresLugaresListView) {
        this.nombresLugaresListView = nombresLugaresListView;
    }

    public List<SmsAgenda> getVistasReserva() {
        return vistasReserva;
    }

    public void setVistasReserva(List<SmsAgenda> vistasReserva) {
        this.vistasReserva = vistasReserva;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public String getHoraEntrega() {
        return HoraEntrega;
    }

    public void setHoraEntrega(String HoraEntrega) {
        this.HoraEntrega = HoraEntrega;
    }

    public ScheduleModel getEventoModelo() {
        return eventoModelo;
    }

    public void setEventoModelo(ScheduleModel eventoModelo) {
        this.eventoModelo = eventoModelo;
    }

    public ScheduleEvent getEvento() {
        return evento;
    }

    public void setEvento(ScheduleEvent evento) {
        this.evento = evento;
    }

    public SmsAgenda getMagendaView() {
        return MagendaView;
    }

    public void setMagendaView(SmsAgenda MagendaView) {
        this.MagendaView = MagendaView;
    }

    public SmsVehiculo getMvehiculoView() {
        return MvehiculoView;
    }

    public void setMvehiculoView(SmsVehiculo MvehiculoView) {
        this.MvehiculoView = MvehiculoView;
    }

    public SmsEmpleado getMempleadoView() {
        return MempleadoView;
    }

    public void setMempleadoView(SmsEmpleado MempleadoView) {
        this.MempleadoView = MempleadoView;
    }

    public SmsReservacion getMreservaView() {
        return MreservaView;
    }

    public void setMreservaView(SmsReservacion MreservaView) {
        this.MreservaView = MreservaView;
    }

    public SmsCategoria getMcategoriaView() {
        return McategoriaView;
    }

    public void setMcategoriaView(SmsCategoria McategoriaView) {
        this.McategoriaView = McategoriaView;
    }

    public SmsCiudad getMciudadView() {
        return MciudadView;
    }

    public void setMciudadView(SmsCiudad MciudadView) {
        this.MciudadView = MciudadView;
    }

    public SmsUsuario getMclienteView() {
        return MclienteView;
    }

    public void setMclienteView(SmsUsuario MclienteView) {
        this.MclienteView = MclienteView;
    }

    
    
    //Metodos    
    //CRUD
    public String registrarAgenda() {
      
        //Registramos los datos de agendamiento
        agendaController.registrarAgenda(empleadoView, vehiculoView, agendaView);
        //obtenemos los datos completos de la agenda recien registrada
        agendaView = agendaController.consultarAgenda(agendaView, vehiculoView, empleadoView).get(0);
        //Registramos la reservacion indicando agenda,ciudad,cliente y los datos de la reserva.
        reservacionController.registrarReservacion(agendaView, ciudadView, reservaView, clienteView);

        //Enviamos mensajes al administrador del sistema, el cliente y el conductor
        if (empleadoView.getIdEmpleado() != null) {
            emailController.sendEmailAdministrador(empleadoView, vehiculoView, reservaView, agendaView, clienteView);
            emailController.sendEmailCliente(empleadoView, vehiculoView, reservaView, agendaView, clienteView);
            emailController.sendEmailConductor(empleadoView, vehiculoView, reservaView, agendaView, clienteView);
        } else {
            emailController.sendEmailAdministradorWithout(vehiculoView, reservaView, agendaView, clienteView);
            emailController.sendEmailClienteWithout(vehiculoView, reservaView, agendaView, clienteView);
        }

        reservacionClienteAgenda(); //Recargamos las lista que se muestran en las vistas
        
        //Limpieza de objetos
        empleadoView = new SmsEmpleado();
        vehiculoView = new SmsVehiculo();
        agendaView = new SmsAgenda();
        reservaView = new SmsReservacion();
        ciudadView = new SmsCiudad();
        vehiculosListView = new ArrayList<>();
        empleadosListView = new ArrayList<>();
        clienteView = new SmsUsuario();
        servicioView = new SmsServicios();

        SelecVeh = false;
        SelecCon = false;
        String Ruta = "";
        switch (sesion.getSmsRol().getRolNombre()) {
            case "Administrador Principal":
                Ruta = "RetornarReservacionAdminP";
                break;

            case "Administrador Secundario":
                Ruta = "RetornarReservacionAdminS";
                break;

            case "Cliente":
                Ruta = "ClienteReservacion";
                break;
        }
        
       
        return Ruta;
    }

    public void eliminarAgenda(){
        agendaController.eliminarAgenda(agendaView);
        agendaView = new SmsAgenda();
        clienteView = new SmsUsuario();
        ciudadView = new SmsCiudad();
        reservaView = new SmsReservacion();
        vehiculoView = new SmsVehiculo();
        empleadoView = new SmsEmpleado();
    }
    
    
    //Especificos 
    ///Controla el flujo de la vista
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;//reset in case user goes back
            return "confirmacion";
        } else {
            switch (event.getNewStep()) {
                case "Vehiculo":
                    if (agendaController.cargarAgendas().isEmpty()) {
                        vehiculosListView = new ArrayList<>();
                        vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
                    } else {
                        vehiculosListView = new ArrayList<>();
                        vehiculosListView = vehiculoController.consultarVehiculosDisponible(agendaView, ciudadView);
                    }
                    break;
                case "Conductor":
                    if (agendaController.cargarAgendas().isEmpty()) {
                        empleadosListView = new ArrayList<>();
                        empleadosListView = empleadoController.consultarEmpleadosCiudad(ciudadView);
                    } else {
                        empleadosListView.clear();
                        empleadosListView = empleadoController.consultarEmpleadosDisponibles(agendaView, ciudadView);
                    }
                    break;
                case "Confirmacion":

                    if (sesion.getSmsRol().getRolNombre().equalsIgnoreCase("Cliente")) {//si el usuario logueado es de tipo cliente asignanos su informacion al objeto cliente
                        clienteView = sesion;
                    }

                    SimpleDateFormat formatTime;
                    formatTime = new SimpleDateFormat("HH:mm:ss");
                    HoraInicio = formatTime.format(agendaView.getAgendaHoraInicio());
                    HoraEntrega = formatTime.format(agendaView.getAgendaHoraLlegada());

                    int valor = reservacionController.calcularCostoReservacion(agendaView, servicioView, vehiculoView);
                    reservaView.setReservacionCosto(valor);
                    break;
            }

            switch (event.getOldStep()) {
                case "Agendamiento":
                    SelecCon = false;
                    SelecVeh = false;
                    vehiculoView = new SmsVehiculo();
                    empleadoView = new SmsEmpleado();
                    break;
            }
            return event.getNewStep();
        }
    }

    public void SeleccionarVehiculo() {
        SelecVeh = true;
    }

    public void SeleccionarNuevoVehiculo() {
        vehiculoView = new SmsVehiculo();
        SelecVeh = false;
    }

    public void SeleccionarConductor() {
        SelecCon = true;
    }

    public void SeleccionarNuevoConductor() {
        empleadoView = new SmsEmpleado();
        SelecCon = false;
    }

    public void filtrar() {

        if (categoriaView.getCategoriaNombre().isEmpty()) {
            if (agendaController.cargarAgendas().isEmpty()) {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
            } else {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.consultarVehiculosDisponible(agendaView, ciudadView);
            }
        } else {
            if (agendaController.cargarAgendas().isEmpty()) {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.filtrarVehiculosCiudad(ciudadView, categoriaView);
            } else {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.filtrarVehiculosDisponibles(agendaView, ciudadView, categoriaView);
            }
        }
    }

    //Lugares
    public void consultarLugaresCiudades() {
        nombresLugaresListView = new ArrayList<>();
        LugaresListView = new ArrayList<>();
        LugaresListView = lugarController.consultarLugaresCiudades(ciudadView.getCiudadNombre());
        for (int i = 0; i < LugaresListView.size(); i++) {
            nombresLugaresListView.add(LugaresListView.get(i).getLugarNombre());
        }
    }

    // CONTROLADOR PARA SACAR DATOS DE RESERVACION 
    public void reservacionClienteAgenda() { //carga la agendas de las reservaciones hechan en el sistema segun el tipo de usuario conectado

        vistasReserva = new ArrayList<>();

        switch (sesion.getSmsRol().getRolNombre()) {

            case "Administrador Principal":
                vistasReserva = agendaController.mostrarAgendas();
                break;
            case "Cliente":
                vistasReserva = agendaController.mostrarDatosReservacionCliente(sesion);
                break;
            case "Empleado":
                empleadoView = empleadoController.consultarEmpleado(sesion).get(0);//Consultamos la informacion de usuario correspondiente al conductor
                vistasReserva = agendaController.mostrarDatosReservacionConductor(empleadoView);
                break;

        }

    }

//CREACION DEL CALENDARIO PRIMEFACAES TIPO SCHEDULE ************************
    public void addEventoCalendario() {
        //instanciar objeto de tipo controlador para sacar el metodo que arroja 
        //los datos de tipo DATE

        Date fechaInicio = new Date();
        Date fechaLlegada = new Date();

        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        SimpleDateFormat formatCompleteDate;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");
        formatCompleteDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        eventoModelo = new DefaultScheduleModel();
        for (int i = 0; i < vistasReserva.size(); i++) {

            String FechaInicio = formatDate.format(vistasReserva.get(i).getAgendaFechaInicio());
            String FechaLlegada = formatDate.format(vistasReserva.get(i).getAgendaFechaLlegada());
            String HInicio = formatTime.format(vistasReserva.get(i).getAgendaHoraInicio());
            String HLlegada = formatTime.format(vistasReserva.get(i).getAgendaHoraLlegada());

            try {
                fechaInicio = formatCompleteDate.parse(FechaInicio + " " + HInicio);
                fechaLlegada = formatCompleteDate.parse(FechaLlegada + " " + HLlegada);
            } catch (ParseException pe) {
                pe.getMessage();
            }

            evento = new DefaultScheduleEvent("" + vistasReserva.get(i).getIdAgenda(), fechaInicio, fechaLlegada);
            evento.setId("" + vistasReserva.get(i).getIdAgenda());
            eventoModelo.addEvent(evento);
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        evento = (ScheduleEvent) selectEvent.getObject();
    }

    public String irVistaReserva() {
        String Ruta = "";
        MagendaView.setIdAgenda(Integer.parseInt(evento.getTitle()));
        MagendaView = agendaController.consultarAgendaID(MagendaView).get(0);
        MreservaView = reservacionController.consultarReservacion(MagendaView).get(0);

        MvehiculoView = MagendaView.getSmsVehiculo();
        MempleadoView = MagendaView.getSmsEmpleado();
        MclienteView = MreservaView.getSmsUsuario();
        MciudadView = MreservaView.getSmsCiudad();
        
        
        switch (sesion.getSmsRol().getRolNombre()) {
            case "Administrador Principal":
                Ruta = "VistaReservaAdminP";
                break;

            case "Administrador Secundario":
                Ruta = "VistaReservaAdminS";
                break;

            case "Cliente":
                Ruta = "VistaReservaCliente";
                break;
                
            case "Empleado":
                Ruta = "VistaReservaConductor";
                break;
        }
        
        
        return Ruta;
    }

}
