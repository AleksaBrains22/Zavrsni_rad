package com.iktpreobuka.backend.services;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.backend.models.EmailObject;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(EmailObject object) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(object.getTo());
		mail.setSubject(object.getSubject());
		mail.setText(object.getText());
		emailSender.send(mail);
	}

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		String text = "<html><body><table style = 'border:2px solid black'" + "<tr><td>" + object.getText()
				+ "</td></tr>" + "</body></htlm>";

		helper.setText(text, true);
		emailSender.send(mail);
	}

	@Override
	public void sendMessageWithAttachement(EmailObject object, String pathToAttachment) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		helper.setText(object.getText(), false);

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(file.getFilename(), file);

		emailSender.send(mail);
	}
}
