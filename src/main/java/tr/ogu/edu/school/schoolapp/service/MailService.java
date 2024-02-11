package tr.ogu.edu.school.schoolapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

import org.javatuples.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tr.ogu.edu.school.schoolapp.repository.SettingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

	private final SettingRepository settingRepository;

	private static final ConcurrentLinkedDeque<Mail> mailQueue = new ConcurrentLinkedDeque<>();
	private static final AtomicBoolean isMailSenderRunning = new AtomicBoolean(false);
	private JavaMailSenderImpl mailSender;

	@Getter
	@RequiredArgsConstructor
	private class Mail {
		private final String to;
		private final String subject;
		private final String body;
		private final String attachmentPath;
		private final List<Pair<String, String>> images;
	}

	public JavaMailSender getJavaMailSender() {
		if (mailSender == null) {
			mailSender = new JavaMailSenderImpl();
			mailSender.setHost(settingRepository.getMailHost().getValue());
			mailSender.setPort(Integer.parseInt(settingRepository.getMailPort().getValue()));
			mailSender.setUsername(settingRepository.getMailFrom().getValue());
			mailSender.setPassword(settingRepository.getMailPassword().getValue());
			Properties props = mailSender.getJavaMailProperties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.starttls.required", "true");
		}
		return mailSender;
	}

	@Async
	public void sendMail(String mailAddress, String mailSubject, String mailContent,
			List<Pair<String, String>> images) {
		sendMailWithAttachment(mailAddress, mailSubject, mailContent, null, images);
	}

	@Async
	public void sendMailWithAttachment(String to, String subject, String body, String attachmentPath,
			List<Pair<String, String>> images) {
		Mail mail = new Mail(to, subject, body, attachmentPath, images);
		mailQueue.push(mail);

		if (isMailSenderRunning.compareAndSet(false, true)) {
			log.info("E-posta gönderimi başlatıldı.");
			while (!mailQueue.isEmpty()) {
				Mail nextMail = mailQueue.poll();
				try {
					send(nextMail);
				} catch (RuntimeException e) {
					log.error("Asenkron mail gönderimi sırasında hata ile karşılaşıldı.", e);
					mailQueue.push(nextMail);
					try {
						Thread.sleep(300000); // 5 dakika bekleme
					} catch (InterruptedException e1) {
						log.warn("Mail gönderimi sırasında bekleme kesintiye uğradı.");
					}

				}
			}
			isMailSenderRunning.set(false);
			log.info("E-posta gönderimi sonlandı.");

		}
	}

	/**
	 * Beklemeye almadan en hızlı şekilde <b>eki olmayan</b> bir e-posta gönderimi
	 * için kullanılabilir. E-posta doğrulama, şifre sıfırlama için kullanılabilir.
	 * 
	 * @param mailAddress
	 * @param mailSubject
	 * @param mailContent
	 */
	public void sendUrgentMail(String to, String subject, String body, List<Pair<String, String>> images) {
		Mail mail = new Mail(to, subject, body, null, images);
		send(mail);
	}

	/**
	 * Beklemeye almadan en hızlı şekilde <b>eki olan</b> bir e-posta gönderimi için
	 * kullanılabilir. E-posta doğrulama, şifre sıfırlama için kullanılabilir.
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 * @param attachment
	 */
	public void sendUrgentMailWithAttachment(String to, String subject, String body, String attachment,
			List<Pair<String, String>> images) {
		Mail mail = new Mail(to, subject, body, attachment, images);
		send(mail);
	}

	private void send(Mail mail) {
		JavaMailSender javaMailSender = getJavaMailSender();
		String mailFrom = settingRepository.getMailFrom().getValue();
		String senderName = settingRepository.getMailSender().getValue();
		MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
			MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED,
					StandardCharsets.UTF_8.name());
			mailMessage.setFrom(mailFrom, senderName);
			mailMessage.setTo(mail.getTo());
			mailMessage.setSubject(mail.getSubject());

			for (Pair<String, String> pair : mail.getImages()) {
				byte[] bytes = Files.readAllBytes(Paths.get(pair.getValue1()));
				final InputStreamSource imageSource = new ByteArrayResource(bytes);
				mailMessage.addInline(pair.getValue0(), imageSource, "image/png");
			}

			if (mail.getAttachmentPath() != null) {
				FileSystemResource fileSystem = new FileSystemResource(new File(mail.getAttachmentPath()));
				mailMessage.addAttachment(fileSystem.getFilename(), fileSystem);
			}
			mailMessage.setText(mail.getBody(), true);
		};
		// javaMailSender.send(mimeMessagePreparator);
		try {
			send(settingRepository.getMailHost().getValue(), settingRepository.getMailPort().getValue(),
					settingRepository.getMailUsername().getValue(), settingRepository.getMailPassword().getValue(),
					mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Konusu {} olan mail {} adlı e-posta adresinden {} adlı e-posta adresine başarıyla gönderilmiştir",
				mail.getSubject(), mailFrom, mail.getTo());

	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void send(String host, String port, final String userName, final String password, Mail mail)
			throws AddressException, MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(mail.getTo()) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(mail.getSubject());
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(mail.getBody(), "text/html; charset=utf-8");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		for (Pair<String, String> pair : mail.getImages()) {
			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.setHeader("Content-ID", "<" + pair.getValue0() + ">");
			imagePart.setDisposition(MimeBodyPart.INLINE);

			String imageFilePath = pair.getValue1();
			try {
				imagePart.attachFile(imageFilePath);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			multipart.addBodyPart(imagePart);
		}

		MimeBodyPart attachmentPart = new MimeBodyPart();
		try {
			attachmentPart.attachFile(new File(mail.getAttachmentPath()));
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		multipart.addBodyPart(attachmentPart);

		msg.setContent(multipart);

		Transport.send(msg);
	}

}
