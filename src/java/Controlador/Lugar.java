/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ICiudadDao;
import DAO.ILugarDao;
import DAO.ImpCiudadDao;
import DAO.ImpLugarDao;
import Modelo.SmsCiudad;
import Modelo.SmsLugares;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Lugar {

    private SmsLugares lugar;
    private List<SmsLugares> lugares;

    public Lugar() {
    }

    //Metodos    
    public void registrarLugar(SmsLugares l, SmsCiudad c) {
        lugar = l;

        //Consultamos datos de ciudad
        ICiudadDao ciudadDao = new ImpCiudadDao();
        SmsCiudad ciudad = ciudadDao.consultarCiudad(c).get(0);

        ILugarDao lugarDao = new ImpLugarDao();
        lugar.setSmsCiudad(ciudad); //Relacionamos el lugar con una ciudad

        lugarDao.registrarLugar(lugar); //Registramos el lugar    
    }

    public void modificarLugar(SmsLugares l, SmsCiudad c) {
        lugar = l;

        //Consultamos datos de ciudad
        ICiudadDao ciudadDao = new ImpCiudadDao();
        SmsCiudad ciudad = ciudadDao.consultarCiudad(c).get(0);

        ILugarDao lugarDao = new ImpLugarDao();
        lugar.setSmsCiudad(ciudad); //Relacionamos el lugar con una ciudad

        lugarDao.modificarLugar(lugar); //modificamos el lugar    
    }

    public void eliminarLugar(SmsLugares l) {
        lugar = l;
        ILugarDao lugarDao = new ImpLugarDao();
        lugarDao.eliminarLugar(lugar);
    }

    public List<SmsLugares> consultarLugares() {
        lugares = new ArrayList<>();
        ILugarDao lugarDao = new ImpLugarDao();
        lugares = lugarDao.consultarLugares(); //Relacionamos el lugar con una ciudad
        return lugares;
    }
    
    public List<SmsLugares> filtrarLugares(String dato) {
        lugares = new ArrayList<>();
        ILugarDao lugarDao = new ImpLugarDao();
        lugares = lugarDao.filtrarLugar(dato); //Relacionamos el lugar con una ciudad
        return lugares;
    }
    
    public List<SmsLugares> consultarLugaresCiudades(String Ciudad) {
        lugares = new ArrayList<>();
        ILugarDao lugarDao = new ImpLugarDao();
        lugares = lugarDao.consultarLugarCiudad(Ciudad); //Relacionamos el lugar con una ciudad
        return lugares;
    }

}
