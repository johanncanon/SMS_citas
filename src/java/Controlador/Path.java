/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Desarrollo_Planit
 */
public class Path {
    
   private File archivo;
   
   public String obtenerPath(String file){
       
       archivo = new File(file);
       return  archivo.getPath();
   
   }
    
}
