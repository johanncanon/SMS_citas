package Model;
// Generated 27-oct-2015 12:34:17 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsMarca generated by hbm2java
 */
public class SmsMarca  implements java.io.Serializable {


     private Integer idMarca;
     private String marcaNombre;
     private Set<SmsReferencia> smsReferencias = new HashSet<SmsReferencia>(0);

    public SmsMarca() {
    }

    public SmsMarca(String marcaNombre, Set<SmsReferencia> smsReferencias) {
       this.marcaNombre = marcaNombre;
       this.smsReferencias = smsReferencias;
    }
   
    public Integer getIdMarca() {
        return this.idMarca;
    }
    
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }
    public String getMarcaNombre() {
        return this.marcaNombre;
    }
    
    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }
    public Set<SmsReferencia> getSmsReferencias() {
        return this.smsReferencias;
    }
    
    public void setSmsReferencias(Set<SmsReferencia> smsReferencias) {
        this.smsReferencias = smsReferencias;
    }




}


