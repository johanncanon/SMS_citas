/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IHojaVidaDao;
import DAO.ImpHojaVidaDao;
import Modelo.SmsHojavida;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Desarrollo_Planit
 */
public class HojaVida {

    SmsHojavida hojaVida;

    public HojaVida() {
        hojaVida = new SmsHojavida();
    }   

    public void registrarHojaVida(UploadedFile archivo) {
        hojaVida.setHojaVidaNombre(archivo.getFileName());
        hojaVida.setHojaVidaContenido(archivo.getContents());
        IHojaVidaDao hojaDao = new ImpHojaVidaDao();
        hojaDao.registrarHojaVida(hojaVida);
    }
}
