/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.IMarcaDao;
import DAO.IReferenciaDao;
import DAO.ImpMarcaDao;
import DAO.ImpReferenciaDao;
import Modelo.SmsMarca;
import Modelo.SmsReferencia;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class ReferenciaBean implements Serializable {

    private SmsReferencia referencia;
    private List<SmsReferencia> referencias;
    
    private List<String> listaReferencia;
    private String marcas;

    
   

    public ReferenciaBean() {
        referencia = new SmsReferencia();
    }

    /*  INICIO DE GETTERS Y SETTERS  
     *********************************************************************************************/
    public SmsReferencia getReferencia() {
        return referencia;
    }

    public void setReferencia(SmsReferencia referencia) {
        this.referencia = referencia;
    }

    public List<SmsReferencia> getReferencias() {//CREACION DE LISTA DE REFERENCIAS DESDE EL BEAN
        IReferenciaDao referenciasDao = new ImpReferenciaDao();
        referencias = referenciasDao.mostrarReferencia();
        return referencias;
    }

    public void setReferencias(List<SmsReferencia> referencias) {
        this.referencias = referencias;
    }
    
    public String getMarcas() {//LISTA DE STRING
        return marcas;
    }

    public void setMarcas(String marca) {
        this.marcas = marca;
    }

    public List<String> getListaReferencia() {
        return listaReferencia;
    }

    public void setListaReferencia(List<String> listaReferencia) {
        this.listaReferencia = listaReferencia;
    }    
    


    /* METODOS DEL BEAN
     ********************************************************************************/
    public void modreferencia() {
        IReferenciaDao referenciaDao = new ImpReferenciaDao();
        referenciaDao.modificarReferencia(referencia);
        referencia = new SmsReferencia();
    }

    public void eliReferencia() {
        IReferenciaDao referenciaDao = new ImpReferenciaDao();
        referenciaDao.eliminarReferencia(referencia);
        referencia = new SmsReferencia();
    }

    public void regReferencia() {
        SmsMarca marca = new SmsMarca();
        IMarcaDao marcaDao = new ImpMarcaDao();
        marca = marcaDao.consultarMarca(marcas).get(0);
        
        referencia.setSmsMarca(marca);
        IReferenciaDao referenciaDao = new ImpReferenciaDao();
        referenciaDao.registrarReferencia(referencia);
        referencia = new SmsReferencia();
    }
}
