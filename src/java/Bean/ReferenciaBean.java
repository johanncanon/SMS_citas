/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Controlador.Referencia;
import DAO.IMarcaDao;
import DAO.IReferenciaDao;
import DAO.ImpMarcaDao;
import DAO.ImpReferenciaDao;
import Modelo.SmsMarca;
import Modelo.SmsReferencia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desarrollo_Planit
 */
public class ReferenciaBean implements Serializable {

    private SmsReferencia referenciaView;
    private List<SmsReferencia> referenciasView;
    private Referencia referencia;
    private List<String> listaReferenciaView;
    private String marcasView;

    
   

    public ReferenciaBean() {
        referenciaView = new SmsReferencia();
        marcasView = new String();
        referencia = new Referencia();
        
    }
/********************************************************
 *********       getters y setters        **************/
    
    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    
    public SmsReferencia getReferenciaView() {
        return referenciaView;
    }

    public void setReferenciaView(SmsReferencia referenciaView) {
        this.referenciaView = referenciaView;
    }

    public List<SmsReferencia> getReferenciasView() {//CREACION DE LISTA DE REFERENCIAS DESDE EL BEAN
        IReferenciaDao linkDao = new ImpReferenciaDao();
        referenciasView = linkDao.mostrarReferencias();
        return referenciasView;
    }

    public void setReferenciasView(List<SmsReferencia> referenciasView) {
        this.referenciasView = referenciasView;
    }
    
    public String getMarcasView() {//LISTA DE STRING
        return marcasView;
    }

    public void setMarcasView(String marcaView) {
        this.marcasView = marcaView;
    }

    public List<String> getListaReferenciaView() {
        listaReferenciaView = new ArrayList<>();
        IReferenciaDao linkDao = new ImpReferenciaDao();
        referenciasView = linkDao.mostrarReferencias();
        for (int i = 0; i < referenciasView.size(); i++) {
            listaReferenciaView.add(referenciasView.get(i).getReferenciaNombre());
            
        }
        
        return listaReferenciaView;
    }

    public void setListaReferenciaView(List<String> listaReferenciaView) {
        this.listaReferenciaView = listaReferenciaView;
    }    
    


    /* METODOS DEL BEAN
     ********************************************************************************/
    public void modificar() {
        referencia.modificarReferencia(referenciaView);
        referenciaView = new SmsReferencia();
    }

    public void eliminar() {
        referencia.eliminarReferencia(referenciaView);
        referenciaView = new SmsReferencia();
    }

    public void registrar() {
        SmsMarca marca = new SmsMarca();
        IMarcaDao marcaDao = new ImpMarcaDao();
        marca = marcaDao.consultarMarca(marcasView).get(0);
        
        referencia.registrarReferencia(referenciaView);
        referenciaView = new SmsReferencia();
    }
}
