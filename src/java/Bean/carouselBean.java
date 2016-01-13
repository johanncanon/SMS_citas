/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SISTEMAS
 */
public class carouselBean {

    private File rutaImg;
    private String[] listaRutaImg;
    private List<String> imagenes;

    boolean existeRuta;
    /**
     * Creates a new instance of carouselBean
     */
    private int numero;

    public carouselBean() {
        rutaImg = new File("web/resources/images/upload/Vehiculos");        
        listaRutaImg = rutaImg.list();
        imagenes = new ArrayList<String>();
        existeRuta = rutaImg.exists();
    }

    public File getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(File rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String[] getListaRutaImg() {
        return listaRutaImg;
    }

    public void setListaRutaImg(String[] listaRutaImg) {
        this.listaRutaImg = listaRutaImg;
    }

    public List<String> getImagenes() {

        listaRutaImg = rutaImg.list();
        for (int i = 0; i < listaRutaImg.length; i++) {
            imagenes.add(listaRutaImg[i]);
        }
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

}
