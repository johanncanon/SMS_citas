/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IHojaVidaDao;
import DAO.ImpHojaVidaDao;
import Modelo.SmsHojavida;
import java.io.IOException;

/**
 *
 * @author Desarrollo_Planit
 */
public class HojaVida {

    SmsHojavida hojaVida;

    public HojaVida() {
        hojaVida = new SmsHojavida();
    }

    public void registrarHojaVida(SmsHojavida h){
        hojaVida = h;
        IHojaVidaDao hojaDao = new ImpHojaVidaDao();
        hojaDao.registrarHojaVida(hojaVida);
    }

    
}
