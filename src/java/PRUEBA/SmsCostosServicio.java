package PRUEBA;
// Generated 17/11/2015 12:16:55 PM by Hibernate Tools 4.3.1



/**
 * SmsCostosServicio generated by hbm2java
 */
public class SmsCostosServicio  implements java.io.Serializable {


     private int idSmsCostosServicio;
     private SmsCategoria smsCategoria;
     private SmsServicios smsServicios;
     private String smsPrecio;

    public SmsCostosServicio() {
    }

    public SmsCostosServicio(int idSmsCostosServicio, SmsCategoria smsCategoria, SmsServicios smsServicios, String smsPrecio) {
       this.idSmsCostosServicio = idSmsCostosServicio;
       this.smsCategoria = smsCategoria;
       this.smsServicios = smsServicios;
       this.smsPrecio = smsPrecio;
    }
   
    public int getIdSmsCostosServicio() {
        return this.idSmsCostosServicio;
    }
    
    public void setIdSmsCostosServicio(int idSmsCostosServicio) {
        this.idSmsCostosServicio = idSmsCostosServicio;
    }
    public SmsCategoria getSmsCategoria() {
        return this.smsCategoria;
    }
    
    public void setSmsCategoria(SmsCategoria smsCategoria) {
        this.smsCategoria = smsCategoria;
    }
    public SmsServicios getSmsServicios() {
        return this.smsServicios;
    }
    
    public void setSmsServicios(SmsServicios smsServicios) {
        this.smsServicios = smsServicios;
    }
    public String getSmsPrecio() {
        return this.smsPrecio;
    }
    
    public void setSmsPrecio(String smsPrecio) {
        this.smsPrecio = smsPrecio;
    }




}


