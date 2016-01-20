/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.IAgendaDao;
import DAO.IEmpleadoDao;
import DAO.IReservacionDao;
import DAO.IUsuarioDao;
import DAO.ImpAgendaDao;
import DAO.ImpEmpleadoDao;
import DAO.ImpReservacionDao;
import DAO.ImpUsuarioDao;
import Modelo.SmsAgenda;
import Modelo.SmsEmpleado;
import Modelo.SmsReservacion;
import Modelo.SmsUsuario;
import Modelo.SmsVehiculo;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private final Properties properties = new Properties();
    private String password;
    private Session session;

    IEmpleadoDao empDao = new ImpEmpleadoDao();
    IReservacionDao resDao = new ImpReservacionDao();
    IUsuarioDao usuDao = new ImpUsuarioDao();
    IAgendaDao agDao = new ImpAgendaDao();

    private void init() {

        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "smsrenta@gmail.com");
        properties.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties);
    }

    public void sendEmailCliente(SmsEmpleado empleado, SmsVehiculo vehiculo, SmsReservacion reservacion, SmsAgenda agenda, SmsUsuario Cliente) {

        init();        
        IUsuarioDao usuDao = new ImpUsuarioDao();        
        SmsUsuario cliente = usuDao.consultarUsuario(Cliente).get(0);
        
        try {
            MimeMessage message = new MimeMessage(session);

            //quien envia
            message.setFrom(new InternetAddress("smsrenta@gmail.com"));

            // a donde se envia
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(""+cliente.getUsuarioEmail()));
            message.setSubject("SMSRenta informe de tu reservacion");
            message.setText("Hola, Señor/Señora "+ cliente.getUsuarioNombre() +", usted tiene una reservacion para el dia "+ agenda.getAgendaFechaInicio() +" que inicia a las "+ agenda.getAgendaHoraInicio() +""
                    + " y culmina a las "+ agenda.getAgendaHoraLlegada() +" del dia "+ agenda.getAgendaFechaLlegada() +", su conductor el señor/señora "+ empleado.getSmsUsuario().getUsuarioNombre() +""
                    + " y su vehiculo es identificado con placa "+ vehiculo.getVehPlaca()+". por ultimo el costo de su reservacion es de "+ reservacion.getReservacionCosto() +"");

            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", (String) properties.get("mail.smtp.user"), "Smsrenta2016");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            me.getMessage();
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }
    }
    
    public void sendEmailClienteWithout(SmsVehiculo vehiculo, SmsReservacion reservacion, SmsAgenda agenda, SmsUsuario Cliente) {

        init();        
        IUsuarioDao usuDao = new ImpUsuarioDao();        
        SmsUsuario cliente = usuDao.consultarUsuario(Cliente).get(0);
        
        try {
            MimeMessage message = new MimeMessage(session);

            //quien envia
            message.setFrom(new InternetAddress("smsrenta@gmail.com"));

            // a donde se envia
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(""+cliente.getUsuarioEmail()));
            message.setSubject("SMSRenta informe de tu reservacion");
            message.setText("Hola, Señor/Señora "+ cliente.getUsuarioNombre() +", usted tiene una reservacion para el dia "+ agenda.getAgendaFechaInicio() +" que inicia a las "+ agenda.getAgendaHoraInicio() +""
                    + " y culmina a las "+ agenda.getAgendaHoraLlegada() +" del dia "+ agenda.getAgendaFechaLlegada() +","
                    + " y su vehiculo es identificado con placa "+ vehiculo.getVehPlaca()+". por ultimo el costo de su reservacion es de "+ reservacion.getReservacionCosto() +"");

            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", (String) properties.get("mail.smtp.user"), "Smsrenta2016");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            me.getMessage();
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }
    }
    
    public void sendEmailAdministrador(SmsEmpleado empleado, SmsVehiculo vehiculo, SmsReservacion reservacion, SmsAgenda agenda, SmsUsuario Cliente) {

        init();
        try {
            MimeMessage message = new MimeMessage(session);

            //quien envia
            message.setFrom(new InternetAddress("smsrenta@gmail.com"));

            // a donde se envia
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("desarrollo@planit.com.co"));
            message.setSubject("Nueva reservacion en el sistema");
            message.setText("Hola, administrador principal, se ha registrado una nueva reservacion para el dia "+ agenda.getAgendaFechaInicio() +" que inicia a las "+ agenda.getAgendaHoraInicio() +""
                    + " y culmina a las "+ agenda.getAgendaHoraLlegada() +" del dia "+ agenda.getAgendaFechaLlegada() +", el conductor es el señor/señora "+ empleado.getSmsUsuario().getUsuarioNombre() +""
                    + " y el vehiculo es el identificado con placa "+ vehiculo.getVehPlaca()+", el lugar de recogida es: "+ reservacion.getReservacionLugarLlegada() +" y el de destino es "+ reservacion.getReservacionLugarDestino() +","
                    + " por ultimo el costo de su reservacion es de "+ reservacion.getReservacionCosto() +"");

            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", (String) properties.get("mail.smtp.user"), "Smsrenta2016");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            me.getMessage();
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }
    }

    public void sendEmailAdministradorWithout(SmsVehiculo vehiculo, SmsReservacion reservacion, SmsAgenda agenda, SmsUsuario Cliente) {

        init();
        try {
            MimeMessage message = new MimeMessage(session);

            //quien envia
            message.setFrom(new InternetAddress("smsrenta@gmail.com"));

            // a donde se envia
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress("desarrollo@planit.com.co"));
            message.setSubject("Nueva reservacion en el sistema");
            message.setText("Hola, administrador principal, se ha registrado una nueva reservacion para el dia "+ agenda.getAgendaFechaInicio() +" que inicia a las "+ agenda.getAgendaHoraInicio() +""
                    + " y culmina a las "+ agenda.getAgendaHoraLlegada() +" del dia "+ agenda.getAgendaFechaLlegada() +","
                    + " y el vehiculo es el identificado con placa "+ vehiculo.getVehPlaca()+", el lugar de recogida es: "+ reservacion.getReservacionLugarLlegada() +" y el de destino es "+ reservacion.getReservacionLugarDestino() +","
                    + " por ultimo el costo de su reservacion es de "+ reservacion.getReservacionCosto() +"");

            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", (String) properties.get("mail.smtp.user"), "Smsrenta2016");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            me.getMessage();
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }
    }

    public void sendEmailConductor(SmsEmpleado empleado, SmsVehiculo vehiculo, SmsReservacion reservacion, SmsAgenda agenda, SmsUsuario Cliente) {

        init();
        try {
            MimeMessage message = new MimeMessage(session);

            //quien envia
            message.setFrom(new InternetAddress("smsrenta@gmail.com"));

            // a donde se envia
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(""+empleado.getSmsUsuario().getUsuarioEmail()));
            message.setSubject("Nueva Reservacion fecha inicio: "+ agenda.getAgendaFechaInicio() +" a fecha llegada: " + agenda.getAgendaFechaLlegada() + "");
            message.setText("Hola, conductor/a "+ empleado.getSmsUsuario().getUsuarioNombre() +", usted tiene una reservacion para el dia "+ agenda.getAgendaFechaInicio() +" que inicia a las "+ agenda.getAgendaHoraInicio() +""
                    + " y culmina a las "+ agenda.getAgendaHoraLlegada() +" del dia "+ agenda.getAgendaFechaLlegada() +", su cliente el señor/señora "+ Cliente.getUsuarioNombre() +""
                    + " y su vehiculo es identificado con placa "+ vehiculo.getVehPlaca()+". por ultimo el lugar de recogida es: "+ reservacion.getReservacionLugarLlegada() +" y el de destino es "+ reservacion.getReservacionLugarDestino() +"");

            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", (String) properties.get("mail.smtp.user"), "Smsrenta2016");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            me.getMessage();
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }

    }

}
