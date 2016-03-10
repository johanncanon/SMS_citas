/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Funciones.Conector_BD;
import Modelo.SmsReservacion;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author SISTEMAS
 */
@Named(value = "reportePruebaBean")
@Dependent
public class reportePruebaBean {

    private SmsReservacion reservacion;

    /**
     * Creates a new instance of reportePruebaBean
     */
    public reportePruebaBean() {
        reservacion = new SmsReservacion();
    }

    public SmsReservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(SmsReservacion reservacion) {
        this.reservacion = reservacion;
    }

//    Metodo para exportar la informacion en PDF
    public void exportarPDF() throws JRException, IOException {
//        TRAEL METODO DE CONECCION A BASE DE EDATOS

        Conector_BD coneccion = new Conector_BD();

        Map parametros = new HashMap();
        parametros.put("ID Reporte Prueba: ", reservacion.getIdReservacion());

//        CREACINO DE ARCHIVO CON LA CALSE FILE
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/facturaPrueba.jasper"));

//        SE TRAE LA CLASE QUE CREA EL METODO  QUE JACE EL REPORTE O FACTURA
        try {

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametros, coneccion.getConexion());
        
//        ( jasper.getPath(), parametros, coneccion.getConexion() );


//        POR MEDIO DEL NAVEGDOR SE MANDA LA ORDEN PRA CREAR EL PDF
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=reportePrueba_1.pdf");
        ServletOutputStream stream = response.getOutputStream();

//                
        JasperExportManager.exportReportToPdfStream(jp, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

}
