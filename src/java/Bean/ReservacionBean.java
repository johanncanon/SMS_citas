/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IAgendaDao;
import Modelo.SmsReservacion;
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
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsCostosServicio;
import Modelo.SmsServicios;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class ReservacionBean {

    private SmsReservacion reservacion;
    private String string;
    private List<SmsReservacion> reservaciones;
    private List<SmsReservacion> vistasReserva;
    
    IUsuarioDao usuDao;
    IReservacionDao resDao;
    ICiudadDao ciuDao;
    IAgendaDao agendaDao;

    public ReservacionBean() {
        reservacion = new SmsReservacion();
        string = new String();
        vistasReserva = new ArrayList<>();

        usuDao = new ImpUsuarioDao();
        resDao = new ImpReservacionDao();
        ciuDao = new ImpCiudadDao();
        agendaDao = new ImpAgendaDao();
              
        }

    public SmsReservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(SmsReservacion reservacion) {
        this.reservacion = reservacion;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<SmsReservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<SmsReservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public List<SmsReservacion> getVistasReserva() {
        return vistasReserva;
    }

    public void setVistasReserva(List<SmsReservacion> vistasReserva) {
        this.vistasReserva = vistasReserva;
    }

    //Metodos    
    public void registrarReservacion(SmsAgenda agenda, SmsCiudad ciudad, SmsReservacion reservacion, SmsUsuario cliente) {

        //Consulta de objetos
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        agenda = agendaDao.consultarAgenda(agenda).get(0);
        cliente = usuDao.consultarUsuario(cliente).get(0);
        
        //asignacion
        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(cliente);
        reservacion.setSmsAgenda(agenda);

        //Registro
        resDao.registrarReservacion(reservacion);
    }
    
    public void registrarReservacionSinConductor(SmsAgenda agenda, SmsCiudad ciudad, SmsReservacion reservacion, SmsUsuario cliente) {

        //Consulta de objetos
        cliente = usuDao.consultarUsuario(cliente).get(0);
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        agenda = agendaDao.consultarAgendaSinEmpleado(agenda).get(0);
        //asignacion
        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(cliente);
        reservacion.setSmsAgenda(agenda);

        //Registro
        resDao.registrarReservacion(reservacion);
    }
   
    public void modificacionReservacion(SmsUsuario cliente, SmsAgenda agenda, SmsCiudad ciudad, SmsReservacion reservacion) {

        //Consulta de objetos
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        agenda = agendaDao.consultarAgenda(agenda).get(0);
        cliente = usuDao.consultarUsuario(cliente).get(0);

        reservacion.setSmsCiudad(ciudad);
        reservacion.setSmsUsuario(cliente);
        reservacion.setSmsAgenda(agenda);

        //Modificacion
        resDao.modificarReservacion(reservacion);

    }

    public void eliminarReservacion(SmsReservacion reservacion) {
        //Eliminacion
        resDao.eliminarReservacion(reservacion);

    }

    public List<SmsReservacion> consultarReservacion(SmsAgenda agenda) {
        reservaciones = new ArrayList<>();
        reservaciones = resDao.consultarReservacionAgenda(agenda);
        return reservaciones;
    }

    //Metodos para la reservacion
    public int calcularCostoReservacion(SmsAgenda agenda, SmsServicios servicio, SmsVehiculo vehiculo) {
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
