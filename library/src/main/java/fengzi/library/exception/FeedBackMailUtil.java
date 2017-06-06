//package fengzi.library.exception;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import fengzi.library.constants.AppConstant;
//
//public class FeedBackMailUtil {
//
//
//	public static Message createMessage(String subject, String messageBody,
//                                        Session session) throws MessagingException,
//			UnsupportedEncodingException {
//		Message message = new MimeMessage(session);
//		message.setFrom(new InternetAddress(fromMail, name));
//		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
//				toMail, name));
//		message.setSubject(subject);
//		message.setText(messageBody);
//		return message;
//	}
//
//	private static final String fromMail = AppConstant.MailInfo.FROMMAIL;
//	private static final String name = AppConstant.MailInfo.MAILNAME;
//	private static final String password = AppConstant.MailInfo.MAILPASSWORD;
//	private static final String toMail = AppConstant.MailInfo.TOMAIL;
//	private static final String host = AppConstant.MailInfo.HOST;
//
//	public static Session createSessionObject() {
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.port", "25");
//
//
//		return Session.getInstance(properties, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(name, password);
//			}
//		});
//	}
//
//
//
//}
