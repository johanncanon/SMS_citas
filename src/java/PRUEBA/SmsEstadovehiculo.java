package PRUEBA;
// Generated 17/11/2015 12:16:55 PM by Hibernate Tools 4.3.1



/**
 * SmsEstadovehiculo generated by hbm2java
 */
public class SmsEstadovehiculo  implements java.io.Serializable {


     private Integer idEstadoVeh;
     private SmsVehiculo smsVehiculo;
     private String estadoVehFrenos;
     private String estadoVehMotor;
     private String estadoVehSilleteria;
     private String estadoVehPintura;
     private String estadoVehKilometraje;

    public SmsEstadovehiculo() {
    }

	
    public SmsEstadovehiculo(SmsVehiculo smsVehiculo) {
        this.smsVehiculo = smsVehiculo;
    }
    public SmsEstadovehiculo(SmsVehiculo smsVehiculo, String estadoVehFrenos, String estadoVehMotor, String estadoVehSilleteria, String estadoVehPintura, String estadoVehKilometraje) {
       this.smsVehiculo = smsVehiculo;
       this.estadoVehFrenos = estadoVehFrenos;
       this.estadoVehMotor = estadoVehMotor;
       this.estadoVehSilleteria = estadoVehSilleteria;
       this.estadoVehPintura = estadoVehPintura;
       this.estadoVehKilometraje = estadoVehKilometraje;
    }
   
    public Integer getIdEstadoVeh() {
        return this.idEstadoVeh;
    }
    
    public void setIdEstadoVeh(Integer idEstadoVeh) {
        this.idEstadoVeh = idEstadoVeh;
    }
    public SmsVehiculo getSmsVehiculo() {
        return this.smsVehiculo;
    }
    
    public void setSmsVehiculo(SmsVehiculo smsVehiculo) {
        this.smsVehiculo = smsVehiculo;
    }
    public String getEstadoVehFrenos() {
        return this.estadoVehFrenos;
    }
    
    public void setEstadoVehFrenos(String estadoVehFrenos) {
        this.estadoVehFrenos = estadoVehFrenos;
    }
    public String getEstadoVehMotor() {
        return this.estadoVehMotor;
    }
    
    public void setEstadoVehMotor(String estadoVehMotor) {
        this.estadoVehMotor = estadoVehMotor;
    }
    public String getEstadoVehSilleteria() {
        return this.estadoVehSilleteria;
    }
    
    public void setEstadoVehSilleteria(String estadoVehSilleteria) {
        this.estadoVehSilleteria = estadoVehSilleteria;
    }
    public String getEstadoVehPintura() {
        return this.estadoVehPintura;
    }
    
    public void setEstadoVehPintura(String estadoVehPintura) {
        this.estadoVehPintura = estadoVehPintura;
    }
    public String getEstadoVehKilometraje() {
        return this.estadoVehKilometraje;
    }
    
    public void setEstadoVehKilometraje(String estadoVehKilometraje) {
        this.estadoVehKilometraje = estadoVehKilometraje;
    }




}


