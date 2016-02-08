/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.ICiudadDao;
import DAO.ICostosServiciosDAO;
import DAO.IReservacionDao;
import DAO.IServicioDao;
import DAO.IUsuarioDao;
import DAO.IVehiculoDao;
import DAO.ImpAgendaDao;
import DAO.ImpCiudadDao;
import DAO.ImpCostosServiciosDAO;
import DAO.ImpReservacionDao;
import DAO.ImpServicioDao;
import DAO.ImpUsuarioDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsAgenda;
import Modelo.SmsCalificacion;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsCostosServicio;
import Modelo.SmsReservacion;
import Modelo.SmsServicios;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import Modelo.SmsVistaReserva;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Desarrollo_Planit
 */
public class Reservacion {

    private SmsReservacion reservacion;
    private List<SmsReservacion> reservaciones;

    //Relacion con otras clases
    private SmsAgenda agenda;
    private SmsUsuario cliente;
    private SmsCalificacion calificacion;

    //Sesion  
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;

    //variable para sacar ciudades segun registro del cliente
    private SmsUsuario usuarioID;
    private SmsReservacion reservaHecha;
    private List<SmsReservacion> vistaReserva;

    //Constructor
    public Reservacion() {
        usuarioID = new SmsUsuario();
        reservaHecha = new SmsReservacion();

    }

    //Metodos    
    public void registrarReservacion(SmsAgenda a, SmsCiudad c, SmsReservacion r, SmsUsuario cliente) {
        reservacion = r;
        agenda = a;
        SmsCiudad ciudad = c;
        IUsuarioDao usuDao = new ImpUsuarioDao();

        this.cliente = usuDao.consultarUsuario(cliente).get(0);

        //Consulta de objetos
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);

        //asignacion
        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(this.cliente);
        reservacion.setSmsAgenda(agenda);

        //Registro
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.registrarReservacion(reservacion);

    }

    public void modificacionReservacion(SmsUsuario u, SmsAgenda a, SmsCiudad c, SmsReservacion r) {

        reservacion = r;
        agenda = a;
        SmsCiudad ciudad = c;
        cliente = u;

        //Consulta de objetos
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);

        IAgendaDao agDao = new ImpAgendaDao();

        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(cliente);
        reservacion.setSmsAgenda(agenda);

        //Modificacion
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.modificarReservacion(reservacion);

    }

    public void eliminarReservacion(SmsReservacion r) {

        reservacion = r;

        //Eliminacion
        IReservacionDao resDao = new ImpReservacionDao();
        resDao.eliminarReservacion(reservacion);

    }

    public List<SmsReservacion> consultarReservacion(SmsAgenda a) {
        agenda = a;
        reservaciones = new ArrayList<>();
        IReservacionDao resDao = new ImpReservacionDao();
        reservaciones = resDao.consultarReservacionAgenda(agenda);
        return reservaciones;
    }

    //Metodos para la reservacion
    public int calcularCostoReservacion(SmsAgenda a, SmsServicios s, SmsVehiculo v) {
        //Instacia de variable propias del metodo
        int costo = 0;
        agenda = a;
        SmsVehiculo vehiculo = v;
        SmsServicios servicio = s;
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
        calFechaInicio.setTime(agenda.getAgendaFechaInicio());
        calFechaLlegada.setTime(agenda.getAgendaFechaLlegada());
        calHoraInicio.setTime(agenda.getAgendaHoraInicio());
        calHoraLlegada.setTime(agenda.getAgendaHoraLlegada());

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

                int diaInicio = agenda.getAgendaFechaInicio().getDay();
                int diaEntrega = agenda.getAgendaFechaLlegada().getDay();

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
