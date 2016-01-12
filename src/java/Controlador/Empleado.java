/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IEmpleadoDao;
import DAO.IHojaVidaDao;
import DAO.IUsuarioDao;
import DAO.ImpEmpleadoDao;
import DAO.ImpHojaVidaDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsAgenda;
import Modelo.SmsCiudad;
import Modelo.SmsEmpleado;
import Modelo.SmsHojavida;
import Modelo.SmsUsuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Empleado extends Usuario {

    private SmsEmpleado empleado;
    private List<SmsEmpleado> empleados;

    //Relaciones con otras clases
    SmsHojavida hojaVida;

    public Empleado() {
        super();
        hojaVida = new SmsHojavida();
        empleado = new SmsEmpleado();
        empleados = new ArrayList<>();
    }

    //Getters & setters
    public SmsEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(SmsEmpleado empleado) {
        this.empleado = empleado;
    }

    public SmsHojavida getHojaVida() {
        return hojaVida;
    }

    public void setHojaVida(SmsHojavida hojaVida) {
        this.hojaVida = hojaVida;
    }

    //Metodos de la clase
    //CRUD    
    public void registrarEmpleado(SmsUsuario u, SmsHojavida h) {
        usuario = u;
        hojaVida = h;
        IUsuarioDao usuDao = new ImpUsuarioDao();
        usuario = usuDao.consultarUsuario(usuario).get(0);        
        empleado.setSmsUsuario(usuario);
        
        if(hojaVida.getHojaVidaNombre() != null && hojaVida.getHojaVidaRuta() != null){
            IHojaVidaDao hojaDao = new ImpHojaVidaDao();
            hojaVida = hojaDao.consultarHojaVida(hojaVida).get(0);
            empleado.setSmsHojavida(hojaVida);
        }else{
        }
                
        IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
        empleadoDao.registrarEmpleado(empleado);
    }

    public void modificarEmpleado(SmsUsuario u, SmsHojavida h, SmsEmpleado e) {
        usuario = u;
        hojaVida = h;
        empleado = e;
        IUsuarioDao usuDao = new ImpUsuarioDao();
        usuario = usuDao.consultarUsuario(usuario).get(0);        
        empleado.setSmsUsuario(usuario);
        
        if(hojaVida.getHojaVidaNombre() != null && hojaVida.getHojaVidaRuta() != null){
            IHojaVidaDao hojaDao = new ImpHojaVidaDao();
            hojaVida = hojaDao.consultarHojaVida(hojaVida).get(0);
            empleado.setSmsHojavida(hojaVida);
        }else{
        }
        IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
        empleadoDao.modificarEmpleado(empleado);
    }

    public void eliminarEmpleado(SmsUsuario u) {
        usuario = u;
        
        IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
        empleado = empleadoDao.consultarEmpleado(usuario).get(0);

        hojaVida = empleado.getSmsHojavida();
        
        IUsuarioDao usuarioDao = new ImpUsuarioDao();
        usuarioDao.eliminarUsuario(usuario);

        IHojaVidaDao hojaDao = new ImpHojaVidaDao();
        hojaDao.eliminarHojaVida(hojaVida);
    }   
    
    public List<SmsEmpleado> mostrarEmpleados(){
        empleados = new ArrayList<>();
        IEmpleadoDao linkDAO = new ImpEmpleadoDao();
        empleados = linkDAO.mostrarEmpleados();
       return empleados; 
    }
    
    public List<SmsEmpleado> consultarEmpleado(SmsUsuario u) {
        usuario = u;
        empleados = new ArrayList<>();
        IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
        empleados = empleadoDao.consultarEmpleado(usuario);
        return empleados;
    }
    
    public List<SmsEmpleado> consultarEmpleadosCiudad(SmsCiudad c) {
        ciudad = c;
        empleados = new ArrayList<>();
        IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
        empleados = empleadoDao.consultarEmpleadosCiudad(ciudad);
        return empleados;
    }
    
     public List<SmsEmpleado> consultarEmpleadosDisponibles(SmsAgenda a, SmsCiudad c) {
        ciudad = c; 
        empleados = new ArrayList<>();
        
        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");
        String FechaInicio = formatDate.format(a.getAgendaFechaInicio());
        String FechaLlegada = formatDate.format(a.getAgendaFechaLlegada());
        String HoraInicio = formatTime.format(a.getAgendaHoraInicio());
        String HoraLlegada = formatTime.format(a.getAgendaHoraLlegada());
        
        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(a.getAgendaHoraInicio());
        calInicio.add(Calendar.HOUR, -1);
        calInicio.add(Calendar.MINUTE, -59);

        Calendar calLlegada = Calendar.getInstance();
        calLlegada.setTime(a.getAgendaHoraLlegada());
        calLlegada.add(Calendar.HOUR, 1);
        calLlegada.add(Calendar.MINUTE, 59);
        
        Date hespacioInicio = calInicio.getTime();
        Date hespacioLlegada = calLlegada.getTime();

        String espacioinicio = formatTime.format(hespacioInicio);
        String espacioLlegada = formatTime.format(hespacioLlegada);

        
        IEmpleadoDao empleadoDao = new ImpEmpleadoDao();
        empleados = empleadoDao.consultarEmpleadosDisponibles(FechaInicio, FechaLlegada, HoraInicio, HoraLlegada, ciudad.getCiudadNombre(), espacioinicio, espacioLlegada);
        List<SmsEmpleado> lista = new ArrayList<>();
        for(int i = 0; i<empleados.size() ; i++){
        lista.add(empleadoDao.consultarEmpleado(empleados.get(i).getSmsUsuario()).get(0));
        }
        return lista;
    }
    

}
