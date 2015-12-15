/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Path;
import java.io.IOException;

/**
 *
 * @author Desarrollo_Planit
 */
public class PathBean {

    private Path pathController;
    
    public PathBean() {
        pathController = new Path();
                
    }
    
    //Getters & setters

    public Path getPathController() {
        return pathController;
    }

    public void setPathController(Path pathController) {
        this.pathController = pathController;
    }    
    
    public String obtenerPathViews(int rol, int archivo){
    String ruta = "";    
    switch(rol){
        case 1:
            switch(archivo){
                case 1:
                ruta = obtenerPath("./vistas/AdminP/usuarios/PUsuario.xhtml");
                break;
                case 2:
                ruta = obtenerPath("./vistas/AdminP/reservas/PReserva.xhtml");
                break;
            }
        break;
    }
    return  ruta;
    
    }
    
    public String obtenerPath(String file){
    String ruta = pathController.obtenerPath(file);
    return ruta;
    }
}
