/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ICategoriaDao;
import DAO.ICostosServiciosDAO;
import DAO.IServicioDao;
import DAO.ImpCategoriaDao;
import DAO.ImpCostosServiciosDAO;
import DAO.ImpServicioDao;
import Modelo.SmsCategoria;
import Modelo.SmsCostosServicio;
import Modelo.SmsServicios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CristianCamilo
 */
public class Costos {

    private SmsCostosServicio costo;
    private List<SmsCostosServicio> costos;

    public Costos() {
    }
   
    //Metodos
    public void registrarCosto(SmsCostosServicio c, SmsCategoria cat, SmsServicios s) {
        costo = c;
        SmsCategoria categoria = cat;
        SmsServicios servicio = s;

        ICategoriaDao catDao = new ImpCategoriaDao();
        categoria = catDao.consultarCategoria(categoria).get(0);

        IServicioDao serDao = new ImpServicioDao();
        servicio = serDao.ConsultarServicio(servicio).get(0);

        costo.setSmsCategoria(categoria);
        costo.setSmsServicios(servicio);

        ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
        cosDao.registrarCostoServicio(costo);
    }

    public void modificarCosto(SmsCostosServicio c, SmsCategoria cat, SmsServicios s) {
        costo = c;

        SmsCategoria categoria = cat;
        SmsServicios servicio = s;
        
        ICategoriaDao catDao = new ImpCategoriaDao();
        categoria = catDao.consultarCategoria(categoria).get(0);

        IServicioDao serDao = new ImpServicioDao();
        servicio = serDao.ConsultarServicio(servicio).get(0);

        costo.setSmsCategoria(categoria);
        costo.setSmsServicios(servicio);

        ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
        cosDao.modificarCostoServicio(costo);
    }

    public void eliminarCosto(SmsCostosServicio c) {
        costo = c;
        ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
        cosDao.eliminarCostoServicio(costo);
    }
    
    public List<SmsCostosServicio> cargarCostos(){
      costos = new ArrayList<>();      
      ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
      costos = cosDao.mostrarCostosServicios();
      return costos;
     }
    
    public List<SmsCostosServicio> filtrarCostos(String buscar){
      costos = new ArrayList<>();      
      ICostosServiciosDAO cosDao = new ImpCostosServiciosDAO();
      costos = cosDao.filtrarCostosServicios(buscar);
      return costos;
    }    
}
