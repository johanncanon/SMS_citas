package Model;
// Generated 24-oct-2015 11:24:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SmsCategoria generated by hbm2java
 */
public class SmsCategoria  implements java.io.Serializable {


     private Integer idCategoria;
     private String categoriaNombre;
     private String categoriaDescripcion;
     private Set<SmsVehiculo> smsVehiculos = new HashSet<SmsVehiculo>(0);

    public SmsCategoria() {
    }

    public SmsCategoria(String categoriaNombre, String categoriaDescripcion, Set<SmsVehiculo> smsVehiculos) {
       this.categoriaNombre = categoriaNombre;
       this.categoriaDescripcion = categoriaDescripcion;
       this.smsVehiculos = smsVehiculos;
    }
   
    public Integer getIdCategoria() {
        return this.idCategoria;
    }
    
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getCategoriaNombre() {
        return this.categoriaNombre;
    }
    
    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
    public String getCategoriaDescripcion() {
        return this.categoriaDescripcion;
    }
    
    public void setCategoriaDescripcion(String categoriaDescripcion) {
        this.categoriaDescripcion = categoriaDescripcion;
    }
    public Set<SmsVehiculo> getSmsVehiculos() {
        return this.smsVehiculos;
    }
    
    public void setSmsVehiculos(Set<SmsVehiculo> smsVehiculos) {
        this.smsVehiculos = smsVehiculos;
    }




}


