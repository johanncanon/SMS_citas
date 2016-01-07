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
@FacesValidator("dateTimeValidator")
public class dateTimeValidator implements Validator {

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
        Date hInicioMod1 = new Date();
        Date hInicioMod2 = new Date();

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
            hInicioMod1 = fInicio;
            hInicioMod1.setHours(hInicio.getHours() + 1);
        } catch (ParseException pe) {
            pe.getMessage();
        }

        if (fInicio.before(fActual)) {
            FacesMessage message = new FacesMessage();
            message.setSummary("La fecha de inicio es anterior a la fecha actual");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (fInicio.equals(fActual) || fInicio.after(fActual)) {
            if (fInicio.after(fActual)) {
                if (hEntrega.before(hInicio) || hEntrega.equals(hInicio)) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("La hora de entrega es anterior o igual a la hora de inicio");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                } else if (hEntrega.after(hInicio) && !hEntrega.after(hInicioMod1)) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("El tiempo de reserva debe ser minimo de una hora");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                }
            } else if (fInicio.equals(fActual)) {
                if (fInicio.after(hActual)) {
                    if (fInicio.after(hInicioMod2) || fInicio.equals(hInicioMod2)) {
                        if (hEntrega.before(hInicio) || hEntrega.equals(hInicio)) {
                            FacesMessage message = new FacesMessage();
                            message.setSummary("La hora de entrega es anterior o igual a la hora de inicio");
                            message.setSeverity(FacesMessage.SEVERITY_ERROR);
                            throw new ValidatorException(message);
                        } else if (hEntrega.after(hInicio) && !hEntrega.after(hInicioMod1)) {
                            FacesMessage message = new FacesMessage();
                            message.setSummary("El tiempo de reserva debe ser minimo de una hora");
                            message.setSeverity(FacesMessage.SEVERITY_ERROR);
                            throw new ValidatorException(message);
                        }
                    } else {
                        FacesMessage message = new FacesMessage();
                        message.setSummary("La hora de recojida debe tener por lo menos 2 horas de antelacion");
                        message.setSeverity(FacesMessage.SEVERITY_ERROR);
                        throw new ValidatorException(message);
                    }
                }
            }

        }

    }
}
