/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Model.SmsCiudad;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class CiudadBean {

    /**
     * Creates a new instance of CiudadBean
     */
    private SmsCiudad ciudad;
    private List<SmsCiudad> ciudades;
    
    public CiudadBean() {
    }

    public SmsCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(SmsCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<SmsCiudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<SmsCiudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    
    
    
}
