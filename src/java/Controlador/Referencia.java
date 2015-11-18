/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IReferenciaDao;
import DAO.ImpReferenciaDao;
import Modelo.SmsReferencia;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class Referencia {
    
    private SmsReferencia referencia;
    private List<SmsReferencia> referencias;

    public Referencia() {
        referencia = new SmsReferencia();
    }

    public SmsReferencia getReferencia() {
        return referencia;
    }

    public void setReferencia(SmsReferencia referencia) {
        this.referencia = referencia;
    }

    public List<SmsReferencia> getReferencias() {
        IReferenciaDao linkDao  = new ImpReferenciaDao();
        referencias = linkDao.mostrarReferencias();
        return referencias;
    }

    public void setReferencias(List<SmsReferencia> referencias) {
        this.referencias = referencias;
    }
    
    /*
    ** Metodos que se comunican con el Bean *************************************
    */
    
    public void modificarReferencia(SmsReferencia ref){
        this.referencia = ref;
        IReferenciaDao linkDao = new ImpReferenciaDao();
        linkDao.modificarReferencia(referencia);
    }
    
    public void eliminarReferencia(SmsReferencia ref){
       this.referencia = ref;
       IReferenciaDao linkDao = new ImpReferenciaDao();
       linkDao.eliminarReferencia(referencia);       
    }
    
    public void registrarReferencia(SmsReferencia ref){
        this.referencia = ref;
        IReferenciaDao linkDao = new ImpReferenciaDao();
        linkDao.registrarReferencia(referencia);
    }
    
}
