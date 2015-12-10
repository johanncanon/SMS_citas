/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import javax.inject.Named;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

/**
 *
 * @author SISTEMAS
 */
@Named(value = "cargaArchivosBean")

public class cargaArchivosBean {

    private UploadedFile archivo;

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setFile(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public void upload() {
        if (archivo != null) {
            FacesMessage message = new FacesMessage("exito, el archivo ", archivo.getFileName() + " ha sido cargado.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
