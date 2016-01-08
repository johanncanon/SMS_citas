/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        String fechaInicio;
        String fechaEntrega;
        String horaInicio;
        String horaEntrega;

        fechaInicio = sdf.format((Date) component.getAttributes().get("fechaInicio"));
        fechaEntrega = sdf.format((Date) component.getAttributes().get("fechaEntrega"));
        horaInicio = sdft.format((Date) component.getAttributes().get("horaInicio"));
        horaEntrega = sdft.format((Date) value);

        try {
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

        Calendar calInicio1 = Calendar.getInstance();
        calInicio1.setTime(hInicio);
        calInicio1.add(Calendar.HOUR, 1);

        Calendar calInicio2 = Calendar.getInstance();
        calInicio2.setTime(hActual);
        calInicio2.add(Calendar.HOUR, 2);

        Date hInicioMod1 = new Date();
        Date hInicioMod2 = new Date();

        try {
            hInicioMod1 = sdft.parse(sdft.format(calInicio1.getTime()));
            hInicioMod2 = sdft.parse(sdft.format(calInicio2.getTime()));
        } catch (ParseException pe) {
            pe.getMessage();
        }

        if (fInicio.before(fActual)) {
            FacesMessage message = new FacesMessage();
            message.setSummary("La fecha de inicio es anterior a la fecha actual");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } else if (fInicio.after(fActual)) {
            if (fEntrega.equals(fInicio)) {
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
            } else if (fEntrega.before(fInicio)) {
                FacesMessage message = new FacesMessage();
                message.setSummary("La fecha de entrega es anterior a la fecha de inicio");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        } else if (fInicio.equals(fActual)) {
            if (fEntrega.equals(fInicio)) {
                if (hInicio.after(hInicioMod2) ) {
                    if (hInicio.after(hInicioMod2)) {
                        if (hEntrega.before(hInicio) || hEntrega.equals(hInicio)) {
                            FacesMessage message = new FacesMessage();
                            message.setSummary("La hora de entrega es anterior o igual a la hora de inicio");
                            message.setSeverity(FacesMessage.SEVERITY_ERROR);
                            throw new ValidatorException(message);
                        } else if (hEntrega.after(hInicio) && hEntrega.before(hInicioMod1)) {
                            FacesMessage message = new FacesMessage();
                            message.setSummary("El tiempo de reserva debe ser minimo de una hora");
                            message.setSeverity(FacesMessage.SEVERITY_ERROR);
                            throw new ValidatorException(message);
                        }
                    }
                } else if (hInicio.equals(hInicioMod2 )) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("La hora de recojida debe tener por lo menos 2 horas de antelacion");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                } else if (hInicio.before(hInicioMod2) && hInicio.after(hActual)) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("La hora de recojida debe tener por lo menos 2 horas de antelacion");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                } else if (hInicio.before(hActual)) {
                    FacesMessage message = new FacesMessage();
                    message.setSummary("La hora de inicio es anterior a la hora actual");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message);
                }
            } else if (fEntrega.before(fInicio)) {
                FacesMessage message = new FacesMessage();
                message.setSummary("La fecha de entrega es anterior a la fecha de inicio");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
