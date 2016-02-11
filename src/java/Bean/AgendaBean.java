/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Empleado;
import Controlador.SendEmail;
import Controlador.Usuario;
import Controlador.Vehiculo;
import DAO.IAgendaDao;
import DAO.ICiudadDao;
import DAO.IReservacionDao;
import DAO.IUsuarioDao;
import DAO.ImpAgendaDao;
import DAO.ImpCiudadDao;
import DAO.ImpReservacionDao;
import DAO.ImpUsuarioDao;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    ReservacionBean reservacionController;
    LugarBean lugarController;
    Vehiculo vehiculoController;
    Empleado empleadoController;
    Usuario usuarioController;
    SendEmail emailController;

    //Sesion  
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    //variables para vista de reservacion    
    private List<SmsAgenda> vistasReserva;

    //VARIABLES PARA CREAR EL SCHEDULE DE PRIMEFACES
    private ScheduleModel eventoModelo;
    private ScheduleEvent evento;

    //Comunicacion con el dao
    protected IAgendaDao agDao;
    protected ICiudadDao ciuDao;
    protected IReservacionDao resDao;
    protected IUsuarioDao usuDao;

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
        usuarioController = new Usuario();
        emailController = new SendEmail();

        SelecVeh = false;
        SelecCon = false;

        //VARIABLES PARA MOSTRAR RESERVACION DE LA AGENDA       
        vistasReserva = new ArrayList<>();

        //VARIABLES PARA CREAR EL CALENDARIO DE PRIMEFACES
        eventoModelo = new DefaultScheduleModel();
        evento = new DefaultScheduleEvent();

        agDao = new ImpAgendaDao();
        ciuDao = new ImpCiudadDao();
        resDao = new ImpReservacionDao();
        usuDao = new ImpUsuarioDao();

        reservacionController = new ReservacionBean();
        lugarController = new LugarBean();
    }

    @PostConstruct
    public void init() {
        //Obtenemos la informacion de sesion del usuario autentificado 
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        sesion = (SmsUsuario) httpServletRequest.getSession().getAttribute("Sesion");

        consultarReservacionesSegunUsuario();
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
        agendaView.setSmsVehiculo(vehiculoView);//Asignamos el vehiculo a la agenda

        if (empleadoView.getIdEmpleado() != null) {//Validamos si se escogio conductor en la agenda
            agendaView.setSmsEmpleado(empleadoView); //Si hay conductor escogido lo asignamos a la agenda
        }
        agDao.registrarAgenda(agendaView);//Registramos agenda

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }

        //obtenemos los datos completos de la agenda recien registrada
        if (empleadoView.getIdEmpleado() != null) {//Si se escogio empleado
            //Registramos la reservacion
            reservacionController.registrarReservacion(agendaView, ciudadView, reservaView, clienteView);
        } else { //Si no hay empleado elegido
            reservacionController.registrarReservacionSinConductor(agendaView, ciudadView, reservaView, clienteView);
        }

        //Enviamos mensajes al administrador del sistema, el cliente y el conductor
        if (empleadoView.getIdEmpleado() != null) {
            emailController.sendEmailAdministrador(empleadoView, vehiculoView, reservaView, agendaView, clienteView);
            emailController.sendEmailCliente(empleadoView, vehiculoView, reservaView, agendaView, clienteView);
            emailController.sendEmailConductor(empleadoView, vehiculoView, reservaView, agendaView, clienteView);
        } else {
            emailController.sendEmailAdministradorWithout(vehiculoView, reservaView, agendaView, clienteView);
            emailController.sendEmailClienteWithout(vehiculoView, reservaView, agendaView, clienteView);
        }

        consultarReservacionesSegunUsuario(); //Recargamos las lista de reservaciones que se muestran en las vistas
        addEventoCalendario();

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

        //Habilitamos la seleccion de vehiculos y conductores
        SelecVeh = false;
        SelecCon = false;

        //seleccion a que vista retornara segun el rol del usuario logueado
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
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reservacion Realizada", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
        //Dormimos la aplicacion para mostrar los mensajes
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }
        return Ruta; //retornamos
    }

    public String eliminarAgenda() {
        boolean valor = validarEliminarReservacion(MagendaView);
        String Ruta = "";
        if (valor) {
            agDao.eliminarAgenda(MagendaView);

            MagendaView = new SmsAgenda();
            MclienteView = new SmsUsuario();
            MciudadView = new SmsCiudad();
            MreservaView = new SmsReservacion();
            MvehiculoView = new SmsVehiculo();
            MempleadoView = new SmsEmpleado();

            switch (sesion.getSmsRol().getRolNombre()) {
                case "Administrador Principal":
                    Ruta = "AdminPPrincipal";
                    break;

                case "Administrador Secundario":
                    Ruta = "AdminSGeneral";
                    break;

                case "Cliente":
                    Ruta = "ClienteDash";
                    break;
            }

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Imposible cancelar la reservacion", "La reservacion se hara valida en menos de dos horas");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        consultarReservacionesSegunUsuario(); //Recargamos las lista de reservaciones que se muestran en las vistas
        addEventoCalendario();

        return Ruta;
    }

    //Especificos 
    ///Controla el flujo de la vista de reservacion
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;//reset in case user goes back
            return "confirmacion";
        } else {
            switch (event.getNewStep()) {
                case "Vehiculo":
                    if (agDao.mostrarAgenda().isEmpty()) {
                        vehiculosListView = new ArrayList<>();
                        vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
                    } else {
                        vehiculosListView = new ArrayList<>();
                        vehiculosListView = vehiculoController.consultarVehiculosDisponible(agendaView, ciudadView);
                    }
                    break;
                case "Conductor":
                    if (agDao.mostrarAgenda().isEmpty()) {
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
            if (agDao.mostrarAgenda().isEmpty()) {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
            } else {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.consultarVehiculosDisponible(agendaView, ciudadView);
            }
        } else {
            if (agDao.mostrarAgenda().isEmpty()) {
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
    public void consultarReservacionesSegunUsuario() { //carga la agendas de las reservaciones hechan en el sistema segun el tipo de usuario conectado

        vistasReserva = new ArrayList<>();

        switch (sesion.getSmsRol().getRolNombre()) {

            case "Administrador Principal":
                vistasReserva = agDao.mostrarAgenda();
                break;
            case "Administrador Secundario":
                vistasReserva = agDao.mostrarAgenda();
                break;
            case "Cliente":
                vistasReserva = agDao.mostrarAgendaReservacionCliente(sesion);
                break;
            case "Empleado":
                empleadoView = empleadoController.consultarEmpleado(sesion).get(0);//Consultamos la informacion de usuario correspondiente al conductor
                vistasReserva = agDao.mostrarAgendaReservacionConductores(empleadoView);
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
        MagendaView = agDao.consultarAgendaId(MagendaView).get(0);
        MreservaView = resDao.consultarReservacionAgenda(MagendaView).get(0);

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

    public boolean validarEliminarReservacion(SmsAgenda agenda) {
        boolean valido = true;

        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");

        java.util.Date fechaActual = new Date();
        java.util.Date HoraActual = new Date();

        String FechaInicio = formatDate.format(fechaActual);
        String HActual = formatTime.format(fechaActual);

        try {
            HoraActual = formatTime.parse(HActual);
            fechaActual = formatDate.parse(FechaInicio);

        } catch (ParseException pe) {
            pe.getMessage();
        }

        Calendar fechaInicioAgenda = Calendar.getInstance();
        fechaInicioAgenda.setTime(agenda.getAgendaHoraInicio());
        fechaInicioAgenda.add(Calendar.HOUR, -1);
        fechaInicioAgenda.add(Calendar.MINUTE, -59);

        Date FInicioAgenda = fechaInicioAgenda.getTime();

        if (agenda.getAgendaFechaInicio().equals(fechaActual)) {
            if (FInicioAgenda.before(HoraActual)) {
                valido = false;
            }
        }

        return valido;
    }
}
