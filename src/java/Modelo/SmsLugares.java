/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Desarrollo_Planit
 */
public class SmsLugares {
    
    private Integer idLugar;
    private String lugarNombre;
    private String lugarDireccion;
    private SmsCiudad smsCiudad;

    public SmsLugares() {
    }

    public SmsLugares(SmsCiudad smsCiudad) {
        this.smsCiudad = smsCiudad;
    }

    public SmsLugares(String lugarNombre, String lugarDireccion, SmsCiudad smsCiudad) {
        this.lugarNombre = lugarNombre;
        this.lugarDireccion = lugarDireccion;
        this.smsCiudad = smsCiudad;
    }

    public Integer getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public String getLugarNombre() {
        return lugarNombre;
    }

    public void setLugarNombre(String lugarNombre) {
        this.lugarNombre = lugarNombre;
    }

    public SmsCiudad getSmsCiudad() {
        return smsCiudad;
    }

    public void setSmsCiudad(SmsCiudad smsCiudad) {
        this.smsCiudad = smsCiudad;
    }

    public String getLugarDireccion() {
        return lugarDireccion;
    }

    public void setLugarDireccion(String lugarDireccion) {
        this.lugarDireccion = lugarDireccion;
    }
    
    
    
    
         
    
}
