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
@FacesValidator("dateValidator")
public class dateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        String fechaInicio = sdf.format((Date) value);
        String fActual = sdf.format(fechaActual);
        
        Date fInicio = new Date();
        Date fAct = new Date();
    
        try {
            fInicio = sdf.parse(fechaInicio);
            fAct = sdf.parse(fActual);
        } catch (ParseException pe) {
            pe.getMessage();
        }
        
        if (fInicio.before(fAct)) {
                FacesMessage message = new FacesMessage();
                message.setSummary("La fecha de inicio es anterior a la fecha actual");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
    }

}
