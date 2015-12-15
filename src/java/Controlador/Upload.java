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
public class Upload {

    UploadedFile archivo;
    String filePath = "C:/Users/CristianCamilo/Documents/NetBeansProjects/SMS_citas/web/resources/images/upload/";

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }    
    
    public void UploadFile(byte[] b, String nombre) throws IOException {        
        
        if (null != b) {
            byte[] bytes = b;
            String filename = nombre;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
            stream.write(bytes);
            stream.close();
        }
        
    }
    
    public void DownloadFile(){
    }
}
