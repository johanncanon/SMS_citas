/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author SISTEMAS
 */
public class SmsVistaReserva {
   
    private String marcaNombre;
    private String referenciaNombre;
    private String vehPlaca;
    private String usuarioNombre;
    
    //CONSTRUCTOR
    public SmsVistaReserva(){
      marcaNombre = new String();
      referenciaNombre = new String();
      vehPlaca = new String();
      usuarioNombre = new String();
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public String getReferenciaNombre() {
        return referenciaNombre;
    }

    public void setReferenciaNombre(String referenciaNombre) {
        this.referenciaNombre = referenciaNombre;
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
    
    
    
    
    
}
