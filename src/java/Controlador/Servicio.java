/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IServicioDao;
import DAO.ImpServicioDao;
import Modelo.SmsServicios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Servicio {
    
    //Atributos
    private SmsServicios servicio;

    public Servicio() {
    }

  
    //Metodos
   
    public void registrarServicio(SmsServicios s){
    servicio = s;    
    IServicioDao servicioDao = new ImpServicioDao();
    servicioDao.registrarServicio(servicio);    
    } 
    
    public void modificarServicio(SmsServicios s){
    servicio = s;    
    IServicioDao servicioDao = new ImpServicioDao();
    servicioDao.modificarServicio(servicio);    
    }
    
    public void eliminarServicio(SmsServicios s){
    servicio = s;    
    IServicioDao servicioDao = new ImpServicioDao();
    servicioDao.eliminarServicio(servicio);    
    }
    
    public List<SmsServicios> cargarServicio(){
        List<SmsServicios> listaServicios = new ArrayList<>();
        IServicioDao servicioDao = new ImpServicioDao();
        listaServicios = servicioDao.mostrarServicios();
        return listaServicios;
    }
    
    public List<SmsServicios> filtrarServicio(String valor){
        List<SmsServicios> listaServicios = new ArrayList<>();
        IServicioDao servicioDao = new ImpServicioDao();
        listaServicios = servicioDao.filtrarServicios(valor);
        return listaServicios;
    }
}
