/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Empleado;
import Funciones.SendEmail;
import Controlador.Usuario;
import Modelo.SmsReservacion;
import DAO.ICiudadDao;
import DAO.ICostosServiciosDAO;
import DAO.IReservacionDao;
import DAO.IServicioDao;
import DAO.IUsuarioDao;
import DAO.IVehiculoDao;
import DAO.ImpCiudadDao;
import DAO.ImpCostosServiciosDAO;
import DAO.ImpReservacionDao;
import DAO.ImpServicioDao;
import DAO.ImpUsuarioDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsCostosServicio;
import Modelo.SmsEmpleado;
import Modelo.SmsLugares;
import Modelo.SmsServicios;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.io.Serializable;
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

/**
 *
 * @author Desarrollo_Planit
 */
public class ReservacionBean implements Serializable{

    private List<SmsReservacion> reservacionesListView;

    private SmsVehiculo vehiculoView;
    private SmsEmpleado empleadoView;
    private SmsReservacion reservaView;
    private SmsCategoria categoriaView;
    private SmsCiudad ciudadView;
    private SmsUsuario clienteView;

    private SmsVehiculo MvehiculoView;
    private SmsEmpleado MempleadoView;
    private SmsReservacion MreservaView;
    private SmsCategoria McategoriaView;
    private SmsCiudad MciudadView;
    private SmsUsuario MclienteView;
    private SmsUsuario MConductorView;

    private SmsCostosServicio costoServicioView;
    private SmsServicios servicioView;

    private SmsUsuario sesion; //objeto donde guardaremos los datos del usuario logueado

    private String HoraInicio;
    private String HoraEntrega;
    private String MinutosInicio;
    private String MinutosEntrega;

    private List<SmsVehiculo> vehiculosListView;
    private List<SmsEmpleado> empleadosListView;
    private List<SmsLugares> LugaresListView;
    private List<String> nombresLugaresListView;

    private boolean skip = false;//Controla la transicion entre las pestañas del panel de reserva
    //Controlan la seleccion de los vehiculos y los empleados
    private boolean SelecVeh;
    private boolean SelecCon;

    //Relacion con los controladores
    LugarBean lugarController;
    VehiculoBean vehiculoController;
    Empleado empleadoController;
    Usuario usuarioController;
    SendEmail emailController;

    //Sesion  
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    //variables para vista de reservacion    
    private List<SmsReservacion> listaReservaciones;

    //VARIABLES PARA CREAR EL SCHEDULE DE PRIMEFACES
    private ScheduleModel eventoModelo;
    private ScheduleEvent evento;

    //Comunicacion con el dao
    protected ICiudadDao ciuDao;
    protected IReservacionDao resDao;
    protected IUsuarioDao usuDao;

    public ReservacionBean() {

        vehiculoView = new SmsVehiculo();
        empleadoView = new SmsEmpleado();
        reservaView = new SmsReservacion();
        categoriaView = new SmsCategoria();
        ciudadView = new SmsCiudad();
        clienteView = new SmsUsuario();

        costoServicioView = new SmsCostosServicio();
        servicioView = new SmsServicios();

        MvehiculoView = new SmsVehiculo();
        MempleadoView = new SmsEmpleado();
        MreservaView = new SmsReservacion();
        McategoriaView = new SmsCategoria();
        MciudadView = new SmsCiudad();
        MclienteView = new SmsUsuario();
        MConductorView = new SmsUsuario();

        vehiculosListView = new ArrayList<>();
        empleadosListView = new ArrayList<>();
        LugaresListView = new ArrayList<>();
        nombresLugaresListView = new ArrayList<>();

        vehiculoController = new VehiculoBean();
        empleadoController = new Empleado();
        usuarioController = new Usuario();
        emailController = new SendEmail();

        SelecVeh = false;
        SelecCon = false;

        //VARIABLES PARA MOSTRAR RESERVACION DE LA AGENDA       
        listaReservaciones = new ArrayList<>();

        //VARIABLES PARA CREAR EL CALENDARIO DE PRIMEFACES
        eventoModelo = new DefaultScheduleModel();
        evento = new DefaultScheduleEvent();

        ciuDao = new ImpCiudadDao();
        resDao = new ImpReservacionDao();
        usuDao = new ImpUsuarioDao();

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

    public List<SmsReservacion> getReservacionesListView() {
        return reservacionesListView;
    }

    public void setReservacionesListView(List<SmsReservacion> reservacionesListView) {
        this.reservacionesListView = reservacionesListView;
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

    public SmsUsuario getClienteView() {
        return clienteView;
    }

    public void setClienteView(SmsUsuario clienteView) {
        this.clienteView = clienteView;
    }

    public List<SmsReservacion> getListaReservaciones() {
        return listaReservaciones;
    }

    public void setListaReservaciones(List<SmsReservacion> listaReservaciones) {
        this.listaReservaciones = listaReservaciones;
    }

    public SmsUsuario getMConductorView() {
        return MConductorView;
    }

    public void setMConductorView(SmsUsuario MConductorView) {
        this.MConductorView = MConductorView;
    }

    public String getMinutosInicio() {
        return MinutosInicio;
    }

    public void setMinutosInicio(String MinutosInicio) {
        this.MinutosInicio = MinutosInicio;
    }

    public String getMinutosEntrega() {
        return MinutosEntrega;
    }

    public void setMinutosEntrega(String MinutosEntrega) {
        this.MinutosEntrega = MinutosEntrega;
    }

    //Metodos    
    //CRUD
    public String registrarReservacion() {

        //asignacion
        reservaView.setSmsVehiculo(vehiculoView);//Asignamos el vehiculo a la agenda
        if (empleadoView.getIdEmpleado() != null) {//Validamos si se escogio conductor en la reservacion
            reservaView.setSmsEmpleado(empleadoView); //Si hay conductor escogido lo asignamos a la reservacion
        }
        ciudadView = ciuDao.consultarCiudad(ciudadView).get(0);
        if (sesion.getSmsRol().getIdRol() != 3) {
            clienteView = usuDao.consultarUsuario(clienteView).get(0);
        }
        reservaView.setSmsCiudad(ciudadView);
        reservaView.setSmsUsuario(clienteView);

        //Registro
        resDao.registrarReservacion(reservaView);

        //Enviamos mensajes al administrador del sistema, el cliente y el conductor
        if (empleadoView.getIdEmpleado() != null) {
            emailController.sendEmailAdministrador(empleadoView, vehiculoView, reservaView, clienteView);
            emailController.sendEmailCliente(empleadoView, vehiculoView, reservaView, clienteView);
            emailController.sendEmailConductor(empleadoView, vehiculoView, reservaView, clienteView);
        } else {
            emailController.sendEmailAdministradorWithout(vehiculoView, reservaView, clienteView);
            emailController.sendEmailClienteWithout(vehiculoView, reservaView, clienteView);
        }

        consultarReservacionesSegunUsuario(); //Recargamos las lista de reservaciones que se muestran en las vistas
        addEventoCalendario();

        //Limpieza de objetos
        empleadoView = new SmsEmpleado();
        vehiculoView = new SmsVehiculo();
        reservaView = new SmsReservacion();
        ciudadView = new SmsCiudad();

        vehiculosListView = new ArrayList<>();
        empleadosListView = new ArrayList<>();
        clienteView = new SmsUsuario();
        servicioView = new SmsServicios();

        //Habilitamos la seleccion de vehiculos y conductores
        SelecVeh = false;
        SelecCon = false;

        HoraInicio = "";
        HoraEntrega = "";
        MinutosEntrega = "";
        MinutosInicio = "";

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

    public String eliminarReservacion() {
        boolean valor = validarEliminarReservacion(MreservaView);
        String Ruta = "";
        if (valor) {
            resDao.eliminarReservacion(MreservaView);

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
                    SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
                    try {
                        reservaView.setReservacionHoraInicio(sdft.parse(HoraInicio + ":" + MinutosInicio));
                        reservaView.setReservacionHoraLlegada(sdft.parse(HoraEntrega + ":" + MinutosEntrega));
                    } catch (ParseException pe) {
                        pe.getMessage();
                    }

                    if (resDao.mostrarReservaciones().isEmpty()) {
                        vehiculosListView = new ArrayList<>();
                        vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
                    } else {
                        vehiculosListView = new ArrayList<>();
                        vehiculosListView = vehiculoController.consultarVehiculosDisponible(reservaView, ciudadView);
                    }
                    break;
                case "Conductor":
                    if (resDao.mostrarReservaciones().isEmpty()) {
                        empleadosListView = new ArrayList<>();
                        empleadosListView = empleadoController.consultarEmpleadosCiudad(ciudadView);
                    } else {
                        empleadosListView.clear();
                        empleadosListView = empleadoController.consultarEmpleadosDisponibles(reservaView, ciudadView);
                    }
                    break;
                case "Confirmacion":

                    if (sesion.getSmsRol().getRolNombre().equalsIgnoreCase("Cliente")) {//si el usuario logueado es de tipo cliente asignanos su informacion al objeto cliente
                        clienteView = sesion;
                    }

                    SimpleDateFormat formatTime;
                    formatTime = new SimpleDateFormat("HH:mm:ss");
                    HoraInicio = formatTime.format(reservaView.getReservacionHoraInicio());
                    HoraEntrega = formatTime.format(reservaView.getReservacionHoraLlegada());

                    int valor = calcularCostoReservacion(reservaView, servicioView, vehiculoView);
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
            if (resDao.mostrarReservaciones().isEmpty()) {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.consultarVehiculosCiudad(ciudadView);
            } else {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.consultarVehiculosDisponible(reservaView, ciudadView);
            }
        } else {
            if (resDao.mostrarReservaciones().isEmpty()) {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.filtrarVehiculosCiudad(ciudadView, categoriaView);
            } else {
                vehiculosListView = new ArrayList<>();
                vehiculosListView = vehiculoController.filtrarVehiculosDisponibles(reservaView, categoriaView);
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

        listaReservaciones = new ArrayList<>();

        switch (sesion.getSmsRol().getRolNombre()) {

            case "Administrador Principal":
                listaReservaciones = resDao.mostrarReservaciones();
                break;
            case "Administrador Secundario":
                listaReservaciones = resDao.mostrarReservaciones();
                break;
            case "Cliente":
                listaReservaciones = resDao.mostrarReservacionCliente(sesion);
                break;
            case "Empleado":
                empleadoView = empleadoController.consultarEmpleado(sesion).get(0);//Consultamos la informacion de usuario correspondiente al conductor
                listaReservaciones = resDao.mostrarReservacionConductores(empleadoView);
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
        for (int i = 0; i < listaReservaciones.size(); i++) {

            String FechaInicio = formatDate.format(listaReservaciones.get(i).getReservacionFechaInicio());
            String FechaLlegada = formatDate.format(listaReservaciones.get(i).getReservacionFechaLlegada());
            String HInicio = formatTime.format(listaReservaciones.get(i).getReservacionHoraInicio());
            String HLlegada = formatTime.format(listaReservaciones.get(i).getReservacionHoraLlegada());

            try {
                fechaInicio = formatCompleteDate.parse(FechaInicio + " " + HInicio);
                fechaLlegada = formatCompleteDate.parse(FechaLlegada + " " + HLlegada);
            } catch (ParseException pe) {
                pe.getMessage();
            }

            evento = new DefaultScheduleEvent("" + listaReservaciones.get(i).getIdReservacion(), fechaInicio, fechaLlegada);
            evento.setId("" + listaReservaciones.get(i).getIdReservacion());
            eventoModelo.addEvent(evento);
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        evento = (ScheduleEvent) selectEvent.getObject();
    }

    public String irVistaReserva() {
        String Ruta = "";
        MreservaView.setIdReservacion(Integer.parseInt(evento.getTitle()));
        MreservaView = resDao.consultarReservacionId(MreservaView).get(0);

        if (MreservaView.getSmsEmpleado() != null) {
            MempleadoView = MreservaView.getSmsEmpleado();
            MConductorView = usuDao.consultarUsuario(MempleadoView.getSmsUsuario()).get(0);
        }

        MvehiculoView = MreservaView.getSmsVehiculo();
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

    public boolean validarEliminarReservacion(SmsReservacion reserva) {
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
        fechaInicioAgenda.setTime(reserva.getReservacionHoraInicio());
        fechaInicioAgenda.add(Calendar.HOUR, -1);
        fechaInicioAgenda.add(Calendar.MINUTE, -59);

        Date FInicioAgenda = fechaInicioAgenda.getTime();

        if (reserva.getReservacionFechaInicio().equals(fechaActual)) {
            if (FInicioAgenda.before(HoraActual)) {
                valido = false;
            }
        }
        return valido;
    }

    //Metodos para la reservacion
    public int calcularCostoReservacion(SmsReservacion reserva, SmsServicios servicio, SmsVehiculo vehiculo) {
        //Instacia de variable propias del metodo
        int costo = 0;

        SmsCostosServicio costos;
        SmsServicios hora = new SmsServicios();
        SmsServicios dia = new SmsServicios();

        hora.setServiciosNombre("Plan hora");
        dia.setServiciosNombre("Plan dia 24 horas");

        //instancia de objetos relacionados a los DAO necesarios
        ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
        IServicioDao serDao = new ImpServicioDao();
        IVehiculoDao vehDao = new ImpVehiculoDao();

        //Consultamos el objeto completo que contiene la informacion de vehiculo y servicio
        vehiculo = vehDao.consultarVehiculo(vehiculo).get(0);
        servicio = serDao.ConsultarServicio(servicio).get(0);

        hora = serDao.ConsultarServicio(hora).get(0);
        dia = serDao.ConsultarServicio(dia).get(0);

        SmsCategoria categoria = vehiculo.getSmsCategoria();//obtenemos la categoria del vehiculo extrayendola del mismo
        costos = cosDao.consultarCostoServicio(servicio, categoria).get(0);//consultamos el costo segun servicio y categoria del vehiculo

        // Crear 2 instancias de Calendar
        Calendar calFechaInicio = Calendar.getInstance();
        Calendar calFechaLlegada = Calendar.getInstance();
        Calendar calHoraInicio = Calendar.getInstance();
        Calendar calHoraLlegada = Calendar.getInstance();
        //Variables necesarias para el calculo del costo de la reservacion       
        long milis1;
        long milis2;
        long diff;
        long diffHours;
        long diffDays;
        long diffWeek;
        long diffMonth;
        long diffHourDifferentDay;
        //asignamos a los objetos calendar la fecha de inicio con la hora de inicio y la fecha de llegada
        //con su hora de llegada
        calFechaInicio.setTime(reserva.getReservacionFechaInicio());
        calFechaLlegada.setTime(reserva.getReservacionFechaLlegada());
        calHoraInicio.setTime(reserva.getReservacionHoraInicio());
        calHoraLlegada.setTime(reserva.getReservacionHoraLlegada());

        switch (servicio.getServiciosNombre()) {
            case "Plan hora":
                // conseguir la representacion de la fecha en milisegundos
                milis1 = calFechaInicio.getTimeInMillis();
                milis2 = calFechaLlegada.getTimeInMillis();

                // calcular la diferencia en dias
                diff = milis2 - milis1;
                diffDays = diff / (24 * 60 * 60 * 1000);

                milis1 = calHoraInicio.getTimeInMillis();
                milis2 = calHoraLlegada.getTimeInMillis();

                // calcular la diferencia en horas
                diff = milis2 - milis1;
                diffHours = (diffDays * 24) + (diff / (60 * 60 * 1000));

                costo = ((int) diffHours) * costos.getSmsPrecio();
                break;
            case "Plan dia 12 horas":
                // conseguir la representacion de la fecha en milisegundos
                milis1 = calFechaInicio.getTimeInMillis();
                milis2 = calFechaLlegada.getTimeInMillis();

                // calcular la diferencia en dias
                diff = milis2 - milis1;
                diffDays = (diff / (24 * 60 * 60 * 1000)) * 2;

                milis1 = calHoraInicio.getTimeInMillis();
                milis2 = calHoraLlegada.getTimeInMillis();

                // calcular la diferencia en horas
                diff = milis2 - milis1;
                diffHours = (diff / (60 * 60 * 1000));

                if (diffHours > 12) {
                    diffDays = diffDays + (diffHours / 12);
                    diffHours = diffHours - (diffHours / 12);
                }

                costo = ((int) diffDays) * costos.getSmsPrecio();

                //Obtenemos el costo de la hora
                costos = cosDao.consultarCostoServicio(hora, categoria).get(0);
                costo = costo + (((int) diffHours) * costos.getSmsPrecio());
                break;
            case "Plan dia 24 horas":
                // conseguir la representacion de la fecha en milisegundos
                milis1 = calFechaInicio.getTimeInMillis();
                milis2 = calFechaLlegada.getTimeInMillis();

                // calcular la diferencia en dias
                diff = milis2 - milis1;
                diffDays = diff / (24 * 60 * 60 * 1000);

                milis1 = calHoraInicio.getTimeInMillis();
                milis2 = calHoraLlegada.getTimeInMillis();

                diff = milis1 - milis2;
                diffHourDifferentDay = (diffDays * 24) - (diff / (60 * 60 * 1000));

                diffDays = diffHourDifferentDay / 24;

                // calcular la diferencia en horas
                diffHours = diffHourDifferentDay - (diffDays * 24);

                costo = ((int) diffDays) * costos.getSmsPrecio();

                //Obtenemos el costo de la hora
                costos = cosDao.consultarCostoServicio(hora, categoria).get(0);
                costo = costo + (((int) diffHours) * costos.getSmsPrecio());
                break;
            case "Plan semana":
                // conseguir la representacion de la fecha en milisegundos
                milis1 = calFechaInicio.getTimeInMillis();
                milis2 = calFechaLlegada.getTimeInMillis();

                // calcular la diferencia en dias
                diff = milis2 - milis1;
                diffDays = (diff / (24 * 60 * 60 * 1000));
                diffWeek = diffDays / 7;

                costo = ((int) diffWeek) * costos.getSmsPrecio();
                costos = cosDao.consultarCostoServicio(dia, categoria).get(0);

                diffDays = diffDays - (diffWeek * 7);

                milis1 = calHoraInicio.getTimeInMillis();
                milis2 = calHoraLlegada.getTimeInMillis();

                diff = milis1 - milis2;
                diffHourDifferentDay = (diffDays * 24) - (diff / (60 * 60 * 1000));

                diffDays = diffHourDifferentDay / 24;
                // calcular la diferencia en horas
                diffHours = diffHourDifferentDay - (diffDays * 24);

                //Calculamos costo del dia
                costo = costo + ((int) diffDays * costos.getSmsPrecio());

                //Obtenemos el costo de la hora               
                costos = cosDao.consultarCostoServicio(hora, categoria).get(0);
                costo = costo + (((int) diffHours) * costos.getSmsPrecio());
                break;
            case "Plan mes":
                // conseguir la representacion de la fecha en milisegundos

                int diaInicio = reserva.getReservacionFechaInicio().getDay();
                int diaEntrega = reserva.getReservacionFechaLlegada().getDay();

                // calcular la diferencia en dias
                diffDays = diaEntrega - diaInicio;

                int startMes = (calFechaInicio.get(Calendar.YEAR) * 12) + calFechaInicio.get(Calendar.MONTH);
                int endMes = (calFechaLlegada.get(Calendar.YEAR) * 12) + calFechaLlegada.get(Calendar.MONTH);

                int daysInMonth = calFechaInicio.getActualMaximum(Calendar.DAY_OF_MONTH);

                milis1 = calHoraInicio.getTimeInMillis();
                milis2 = calHoraLlegada.getTimeInMillis();

                diff = milis1 - milis2;
                diffHourDifferentDay = (diffDays * 24) - (diff / (60 * 60 * 1000));

                diffDays = diffHourDifferentDay / 24;
                // calcular la diferencia en horas
                diffHours = diffHourDifferentDay - (diffDays * 24);

                //Diferencia en meses entre las dos fechas
                diffMonth = endMes - startMes;
                costo = ((int) diffMonth) * costos.getSmsPrecio();

                costos = cosDao.consultarCostoServicio(dia, categoria).get(0);
                costo = costo + ((int) diffDays * costos.getSmsPrecio());
                costos = cosDao.consultarCostoServicio(hora, categoria).get(0);
                costo = costo + (((int) diffHours) * costos.getSmsPrecio());
                break;
        }
        return costo;
    }

}
