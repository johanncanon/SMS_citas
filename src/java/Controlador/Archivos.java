/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Desarrollo_Planit
 */
public class Archivos {
    
    UploadedFile archivo;
    
    
    public void UploadFile(UploadedFile file) throws IOException{
        archivo = file;
        String filePath = "c:/prueba/";
        byte[] bytes = null;
        if (null != archivo) {
            bytes = archivo.getContents();
            String filename = archivo.getFileName();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
            stream.write(bytes);
            stream.close();
        }
    }
}
