/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.SmsHojavida;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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

    public void registrarHojaVida(UploadedFile archivo) throws IOException {

        InputStream in = archivo.getInputstream();
        Blob b;              
    }

    
}
