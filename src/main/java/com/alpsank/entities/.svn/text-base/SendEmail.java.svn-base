package com.alpsank.entities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail 
{
	private static final String username = "daddyhululu@gmail.com";
	private static final String password = "hululu44";
	private SendEmail()	{	}
	public static boolean SendMsg( String msg, String toEmail ) 
	{
   		Properties props = new Properties();
   		props.put("mail.smtp.auth", "true");
   		props.put("mail.smtp.starttls.enable", "true");
   		props.put("mail.smtp.host", "smtp.gmail.com");
   		props.put("mail.smtp.port", "587");
    
   		Session session = Session.getInstance(props,
   		  new javax.mail.Authenticator() {
   			protected PasswordAuthentication getPasswordAuthentication() {
   				return new PasswordAuthentication(username, password);
   			}
   		  });
    
   		try {
    
   			Message message = new MimeMessage(session);
   			message.setFrom(new InternetAddress(username));
   			message.setRecipients(Message.RecipientType.TO,
   				InternetAddress.parse(toEmail));
   			message.setSubject("Library CMS - Forgotten Password");
   			message.setText(msg);
    
   			Transport.send(message);
    
   		} catch (MessagingException e) {
   			return false;
   		}
   		return true;
	}
}
