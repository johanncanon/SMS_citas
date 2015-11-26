package Modelo;
// Generated 26-nov-2015 15:26:50 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsVehiculo generated by hbm2java
 */
public class SmsVehiculo  implements java.io.Serializable {


     private Integer idVehiculo;
     private SmsCategoria smsCategoria;
     private SmsCiudad smsCiudad;
     private SmsProveedor smsProveedor;
     private SmsReferencia smsReferencia;
     private String vehPlaca;
     private String vehModelo;
     private String vehColor;
     private Integer vehNumPersonas;
     private Integer vehNumMalGrande;
     private Integer vehNumMalPequeña;
     private String vehFotoNombre;
     private String vehFotoRuta;
     private Set<SmsAgenda> smsAgendas = new HashSet<SmsAgenda>(0);
     private Set<SmsEstadovehiculo> smsEstadovehiculos = new HashSet<SmsEstadovehiculo>(0);

    public SmsVehiculo() {
    }

	
    public SmsVehiculo(SmsCategoria smsCategoria, SmsCiudad smsCiudad, SmsProveedor smsProveedor, SmsReferencia smsReferencia) {
        this.smsCategoria = smsCategoria;
        this.smsCiudad = smsCiudad;
        this.smsProveedor = smsProveedor;
        this.smsReferencia = smsReferencia;
    }
    public SmsVehiculo(SmsCategoria smsCategoria, SmsCiudad smsCiudad, SmsProveedor smsProveedor, SmsReferencia smsReferencia, String vehPlaca, String vehModelo, String vehColor, Integer vehNumPersonas, Integer vehNumMalGrande, Integer vehNumMalPequeña, String vehFotoNombre, String vehFotoRuta, Set<SmsAgenda> smsAgendas, Set<SmsEstadovehiculo> smsEstadovehiculos) {
       this.smsCategoria = smsCategoria;
       this.smsCiudad = smsCiudad;
       this.smsProveedor = smsProveedor;
       this.smsReferencia = smsReferencia;
       this.vehPlaca = vehPlaca;
       this.vehModelo = vehModelo;
       this.vehColor = vehColor;
       this.vehNumPersonas = vehNumPersonas;
       this.vehNumMalGrande = vehNumMalGrande;
       this.vehNumMalPequeña = vehNumMalPequeña;
       this.vehFotoNombre = vehFotoNombre;
       this.vehFotoRuta = vehFotoRuta;
       this.smsAgendas = smsAgendas;
       this.smsEstadovehiculos = smsEstadovehiculos;
    }
   
    public Integer getIdVehiculo() {
        return this.idVehiculo;
    }
    
    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public SmsCategoria getSmsCategoria() {
        return this.smsCategoria;
    }
    
    public void setSmsCategoria(SmsCategoria smsCategoria) {
        this.smsCategoria = smsCategoria;
    }
    public SmsCiudad getSmsCiudad() {
        return this.smsCiudad;
    }
    
    public void setSmsCiudad(SmsCiudad smsCiudad) {
        this.smsCiudad = smsCiudad;
    }
    public SmsProveedor getSmsProveedor() {
        return this.smsProveedor;
    }
    
    public void setSmsProveedor(SmsProveedor smsProveedor) {
        this.smsProveedor = smsProveedor;
    }
    public SmsReferencia getSmsReferencia() {
        return this.smsReferencia;
    }
    
    public void setSmsReferencia(SmsReferencia smsReferencia) {
        this.smsReferencia = smsReferencia;
    }
    public String getVehPlaca() {
        return this.vehPlaca;
    }
    
    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }
    public String getVehModelo() {
        return this.vehModelo;
    }
    
    public void setVehModelo(String vehModelo) {
        this.vehModelo = vehModelo;
    }
    public String getVehColor() {
        return this.vehColor;
    }
    
    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
    }
    public Integer getVehNumPersonas() {
        return this.vehNumPersonas;
    }
    
    public void setVehNumPersonas(Integer vehNumPersonas) {
        this.vehNumPersonas = vehNumPersonas;
    }
    public Integer getVehNumMalGrande() {
        return this.vehNumMalGrande;
    }
    
    public void setVehNumMalGrande(Integer vehNumMalGrande) {
        this.vehNumMalGrande = vehNumMalGrande;
    }
    public Integer getVehNumMalPequeña() {
        return this.vehNumMalPequeña;
    }
    
    public void setVehNumMalPequeña(Integer vehNumMalPequeña) {
        this.vehNumMalPequeña = vehNumMalPequeña;
    }
    public String getVehFotoNombre() {
        return this.vehFotoNombre;
    }
    
    public void setVehFotoNombre(String vehFotoNombre) {
        this.vehFotoNombre = vehFotoNombre;
    }
    public String getVehFotoRuta() {
        return this.vehFotoRuta;
    }
    
    public void setVehFotoRuta(String vehFotoRuta) {
        this.vehFotoRuta = vehFotoRuta;
    }
    public Set<SmsAgenda> getSmsAgendas() {
        return this.smsAgendas;
    }
    
    public void setSmsAgendas(Set<SmsAgenda> smsAgendas) {
        this.smsAgendas = smsAgendas;
    }
    public Set<SmsEstadovehiculo> getSmsEstadovehiculos() {
        return this.smsEstadovehiculos;
    }
    
    public void setSmsEstadovehiculos(Set<SmsEstadovehiculo> smsEstadovehiculos) {
        this.smsEstadovehiculos = smsEstadovehiculos;
    }




}


