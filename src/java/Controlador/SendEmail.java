/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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

    private void init() {

        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "camilorevenant@gmail.com");
        properties.put("mail.smtp.auth", "true");
        session = Session.getInstance(properties);
    }

    public void sendEmail() {

        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("desarrollo@planit.com.co"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("desarrollo@planit.com.co"));
            message.setSubject("Prueba");
            message.setText("Texto enviado desde SMSRenta");
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), "94060401823");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            return;
        }

    }

}
