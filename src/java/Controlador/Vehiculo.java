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

    //Conexion con el DAO
    ICategoriaDao cateDao;
    IProveedorDao provDao;
    ICiudadDao ciuDao;
    IReferenciaDao refDao;
    IVehiculoDao vehDao;
    IUsuarioDao usuDao;

    SmsCategoria categoria;
    SmsUsuario usuario;
    SmsProveedor proveedor;
    SmsCiudad ciudad;
    SmsReferencia referencia;

    public Vehiculo() {
        vehiculo = new SmsVehiculo();
        estado_Vehiculo = new SmsEstadovehiculo();

        cateDao = new ImpCategoriaDao();
        provDao = new ImpProveedorDao();
        ciuDao = new ImpCiudadDao();
        refDao = new ImpReferenciaDao();
        vehDao = new ImpVehiculoDao();
        usuDao = new ImpUsuarioDao();

        categoria = new SmsCategoria();
        usuario = new SmsUsuario();
        proveedor = new SmsProveedor();
        ciudad = new SmsCiudad();
        referencia = new SmsReferencia();
    }

    //Metodos
    public void modificarVehiculo(SmsProveedor p, SmsCategoria c, SmsUsuario u, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        categoria = c;
        usuario = u;
        proveedor = p;
        ciudad = ci;
        referencia = r;
        vehiculo = v;

        usuario = usuDao.consultarUsuario(usuario).get(0);

        //Consulta categoria      
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);//Reasigna Categoria

        //Consulta proveedor        
        proveedor = provDao.consultarProveedorUsuario(usuario).get(0);
        vehiculo.setSmsProveedor(proveedor);//Reasigna Proveedor

        //Consulta ciudad        
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);//Reasigna Ciudad

        //Consulta referencia        
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);//Reasigna Referencia

        vehDao.modificarVehiculo(vehiculo);//Se modifica el vehiculo

    }

    public void registrarVehiculo(SmsProveedor p, SmsCategoria c, SmsUsuario u, SmsCiudad ci, SmsReferencia r, SmsVehiculo v) {
        categoria = c;
        usuario = u;
        proveedor = p;
        ciudad = ci;
        referencia = r;
        vehiculo = v;

        usuario = usuDao.consultarUsuario(usuario).get(0);

        //Consulta categoria
        categoria = cateDao.consultarCategoria(categoria).get(0);
        vehiculo.setSmsCategoria(categoria);//Asigna Categoria

        //Consulta proveedor
        proveedor = provDao.consultarProveedorUsuario(usuario).get(0);
        vehiculo.setSmsProveedor(proveedor);//Asigna Proveedor

        //Consulta ciudad
        ciudad = ciuDao.consultarCiudad(ciudad).get(0);
        vehiculo.setSmsCiudad(ciudad);//Asigna Ciudad

        //Consulta referencia
        referencia = refDao.consultarReferencias(referencia).get(0);
        vehiculo.setSmsReferencia(referencia);//Asigna Referencia

        vehDao.registrarVehiculo(vehiculo);//Registra el Vehiculo
    }

    public void eliminarVehiculo(SmsVehiculo v) {
        vehiculo = v;
        vehDao.eliminarVehiculo(vehiculo);
    }

    public List<SmsVehiculo> consultarVehiculo(SmsVehiculo vehiculo) {
        vehiculos = new ArrayList<>();
        vehiculos = vehDao.consultarVehiculo(vehiculo);
        return vehiculos;
    }

    public List<SmsVehiculo> cargarVehiculos() {
        vehiculos = new ArrayList<>();
        vehiculos = vehDao.mostrarVehiculo();
        return vehiculos;
    }

    public List<SmsVehiculo> consultarVehiculosDisponible(SmsAgenda a, SmsCiudad c) {
        vehiculos = new ArrayList<>();
        String ciudadVeh = c.getCiudadNombre();

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

        vehiculos = vehDao.consultarVehiculosDisponibles(FechaInicio, FechaLlegada, HoraInicio, HoraLlegada, ciudadVeh, espacioinicio, espacioLlegada);
        return vehiculos;
    }

    public List<SmsVehiculo> consultarVehiculosCiudad(SmsCiudad c) {
        vehiculos = new ArrayList<>();
        ciudad = c;
        vehiculos = vehDao.consultarVehiculosCiudad(ciudad);
        return vehiculos;
    }

    public List<SmsVehiculo> filtrarVehiculosCiudad(SmsCiudad c, SmsCategoria cat) {
        vehiculos = new ArrayList<>();
        ciudad = c;
        String categoriaVeh = cat.getCategoriaNombre();
        vehiculos = vehDao.filtrarVehiculosCiudad(ciudad, categoriaVeh);
        return vehiculos;
    }

    public List<SmsVehiculo> filtrarVehiculosDisponibles(SmsAgenda a, SmsCiudad c, SmsCategoria cat) {
        vehiculos = new ArrayList<>();
        String ciudadVeh = c.getCiudadNombre();
        String categoriaVeh = cat.getCategoriaNombre();

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
        vehiculos = linkDao.filtrarVehiculosDisponibles(FechaInicio, FechaLlegada, HoraInicio, HoraLlegada, ciudadVeh, categoriaVeh, espacioinicio, espacioLlegada);
        return vehiculos;
    }

}
