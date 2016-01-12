/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Desarrollo_Planit
 */
@FacesValidator("timeEndValidator")
public class timeEndValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = new Date();
        Date hora = new Date();

        String horaActual;
        String fechaActual;

        Date fInicio = new Date();
        Date fEntrega = new Date();
        Date hInicio = new Date();
        Date hEntrega = new Date();
        Date hActual = new Date();
        Date fActual = new Date();

        String fechaInicio;
        String fechaEntrega;
        String horaInicio;
        String horaEntrega;

        try {
            fechaInicio = sdf.format((Date) component.getAttributes().get("fechaInicio"));
            fechaEntrega = sdf.format((Date) component.getAttributes().get("fechaEntrega"));
            horaInicio = sdft.format((Date) component.getAttributes().get("horaInicio"));
            horaEntrega = sdft.format((Date) value);

            horaActual = sdft.format(hora);
            fechaActual = sdf.format(fecha);

            fInicio = sdf.parse(fechaInicio);
            fEntrega = sdf.parse(fechaEntrega);
            hInicio = sdft.parse(horaInicio);
            hEntrega = sdft.parse(horaEntrega);
            hActual = sdft.parse(horaActual);
            fActual = sdf.parse(fechaActual);
        } catch (ParseException pe) {
            pe.getMessage();
        }     
                        
        
        if (fEntrega.after(fInicio) || fEntrega.equals(fInicio)) {
            if (fInicio.equals(fActual)) {
                if (hInicio.before(hActual) || hInicio.equals(hActual)) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("La hora de inicio es anterior o igual a la hora actual");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                } else {
                    if (hEntrega.before(hInicio) || hEntrega.equals(hInicio)) {
                        FacesMessage message = new FacesMessage();
                        message.setSummary("La hora de entrega es anterior o igual a la hora de inicio");
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        throw new ValidatorException(message);
                    }
                }
            } else if (fInicio.after(fActual)) {
                if (hEntrega.before(hInicio) || hEntrega.equals(hInicio)) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("La hora de entrega es anterior o igual a la hora de inicio");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                }
            }
        }
    }
}