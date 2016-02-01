/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsVehiculo;
import DAO.ICategoriaDao;
import DAO.ICiudadDao;
import DAO.IProveedorDao;
import DAO.IReferenciaDao;
import DAO.IUsuarioDao;
import DAO.IVehiculoDao;
import DAO.ImpCategoriaDao;
import DAO.ImpCiudadDao;
import DAO.ImpProveedorDao;
import DAO.ImpReferenciaDao;
import DAO.ImpUsuarioDao;
import DAO.ImpVehiculoDao;
import Modelo.SmsAgenda;
import Modelo.SmsCategoria;
import Modelo.SmsCiudad;
import Modelo.SmsEstadovehiculo;
import Modelo.SmsProveedor;
import Modelo.SmsReferencia;
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
public class Vehiculo {

    private SmsVehiculo vehiculo;
    private SmsEstadovehiculo estado_Vehiculo;
    
    private List<SmsVehiculo> vehiculos;
    private List<SmsEstadovehiculo> estado_Vehiculos;

    public Vehiculo() {
        vehiculo = new SmsVehiculo();
        estado_Vehiculo = new SmsEstadovehiculo();
    }
   
    //Metodos
    public void modificarVehiculo(SmsProveedor p, SmsCategoria c, SmsUsuario u, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        SmsCategoria categoria = c;
        SmsUsuario usuario = u;
        SmsProveedor proveedor = p;
        SmsCiudad ciudad = ci;
        SmsReferencia referencia = r;
        vehiculo = v;

        IUsuarioDao usuDao = new ImpUsuarioDao();
        usuario = usuDao.consultarUsuario(usuario).get(0);

        //Consulta categoria
        ICategoriaDao cateDao = new ImpCategoriaDao();
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);//Reasigna Categoria

        //Consulta proveedor
        IProveedorDao provDao = new ImpProveedorDao();
        proveedor = provDao.consultarProveedorUsuario(usuario).get(0);
        vehiculo.setSmsProveedor(proveedor);//Reasigna Proveedor

        //Consulta ciudad
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);//Reasigna Ciudad

        //Consulta referencia
        IReferenciaDao refDao = new ImpReferenciaDao();
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);//Reasigna Referencia

        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.modificarVehiculo(vehiculo);

    }

    public void registrarVehiculo(SmsProveedor p, SmsCategoria c, SmsUsuario u, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        SmsCategoria categoria = c;
        SmsUsuario usuario = u;
        SmsProveedor proveedor;
        SmsCiudad ciudad = ci;
        SmsReferencia referencia = r;
        vehiculo = v;

        IUsuarioDao usuDao = new ImpUsuarioDao();
        usuario = usuDao.consultarUsuario(usuario).get(0);

        //Consulta categoria
        ICategoriaDao cateDao = new ImpCategoriaDao();
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);//Asigna Categoria

        //Consulta proveedor
        IProveedorDao provDao = new ImpProveedorDao();
        proveedor = provDao.consultarProveedorUsuario(usuario).get(0);
        vehiculo.setSmsProveedor(proveedor);//Asigna Proveedor

        //Consulta ciudad
        ICiudadDao ciuDao = new ImpCiudadDao();
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);//Asigna Ciudad

        //Consulta referencia
        IReferenciaDao refDao = new ImpReferenciaDao();
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);//Asigna Referencia

        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.registrarVehiculo(vehiculo);
    }

    public void eliminarVehiculo(SmsVehiculo v) {
        vehiculo = v;
        IVehiculoDao linkDao = new ImpVehiculoDao();
        linkDao.eliminarVehiculo(vehiculo);
    }

    public List<SmsVehiculo> consultarVehiculo(SmsVehiculo vehiculo) {
        vehiculos = new ArrayList<>();
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.consultarVehiculo(vehiculo);
        return vehiculos;
    }

    public List<SmsVehiculo> cargarVehiculos() {
        vehiculos = new ArrayList<>();
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.mostrarVehiculo();
        return vehiculos;
    }

    public List<SmsVehiculo> consultarVehiculosDisponible(SmsAgenda a, SmsCiudad c) {
        vehiculos = new ArrayList<>();
        String ciudad = c.getCiudadNombre();

        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(a.getAgendaHoraInicio());
        calInicio.add(Calendar.HOUR, -1);
        calInicio.add(Calendar.MINUTE, -59);

        Calendar calLlegada = Calendar.getInstance();
        calLlegada.setTime(a.getAgendaHoraLlegada());
        calLlegada.add(Calendar.HOUR, 2);
        
        Date hespacioInicio = calInicio.getTime();
        Date hespacioLlegada = calLlegada.getTime();

        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");

        String FechaInicio = formatDate.format(a.getAgendaFechaInicio());
        String FechaLlegada = formatDate.format(a.getAgendaFechaLlegada());
        String HoraInicio = formatTime.format(a.getAgendaHoraInicio());
        String HoraLlegada = formatTime.format(a.getAgendaHoraLlegada());
        String espacioinicio = formatTime.format(hespacioInicio);
        String espacioLlegada = formatTime.format(hespacioLlegada);

        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.consultarVehiculosDisponibles(FechaInicio, FechaLlegada, HoraInicio, HoraLlegada, ciudad, espacioinicio, espacioLlegada);
        return vehiculos;
    }

    public List<SmsVehiculo> consultarVehiculosCiudad(SmsCiudad c) {
        vehiculos = new ArrayList<>();
        SmsCiudad ciudad = c;
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.consultarVehiculosCiudad(ciudad);
        return vehiculos;
    }

    public List<SmsVehiculo> filtrarVehiculosCiudad(SmsCiudad c, SmsCategoria cat) {
        vehiculos = new ArrayList<>();
        SmsCiudad ciudad = c;
        String categoria = cat.getCategoriaNombre();
        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.filtrarVehiculosCiudad(ciudad, categoria);
        return vehiculos;
    }

    public List<SmsVehiculo> filtrarVehiculosDisponibles(SmsAgenda a, SmsCiudad c, SmsCategoria cat) {
        vehiculos = new ArrayList<>();
        String ciudad = c.getCiudadNombre();
        String categoria = cat.getCategoriaNombre();

        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(a.getAgendaHoraInicio());
        calInicio.add(Calendar.HOUR, -1);
        calInicio.add(Calendar.MINUTE, -59);

        Calendar calLlegada = Calendar.getInstance();
        calLlegada.setTime(a.getAgendaHoraLlegada());
        calLlegada.add(Calendar.HOUR, 2);

        Date hespacioInicio = calInicio.getTime();
        Date hespacioLlegada = calLlegada.getTime();

        SimpleDateFormat formatDate;
        SimpleDateFormat formatTime;
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatTime = new SimpleDateFormat("HH:mm:ss");
        String FechaInicio = formatDate.format(a.getAgendaFechaInicio());
        String FechaLlegada = formatDate.format(a.getAgendaFechaLlegada());
        String HoraInicio = formatTime.format(a.getAgendaHoraInicio());
        String HoraLlegada = formatTime.format(a.getAgendaHoraLlegada());
        String espacioinicio = formatTime.format(hespacioInicio);
        String espacioLlegada = formatTime.format(hespacioLlegada);

        IVehiculoDao linkDao = new ImpVehiculoDao();
        vehiculos = linkDao.filtrarVehiculosDisponibles(FechaInicio, FechaLlegada, HoraInicio, HoraLlegada, ciudad, categoria, espacioinicio, espacioLlegada);
        return vehiculos;
    }

}
