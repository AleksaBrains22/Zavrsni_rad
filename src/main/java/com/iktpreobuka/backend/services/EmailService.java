package com.iktpreobuka.backend.services;

import com.iktpreobuka.backend.models.EmailObject;

public interface EmailService {

	void sendSimpleMessage(EmailObject object);

	void sendTemplateMessage(EmailObject object) throws Exception;

	void sendMessageWithAttachement(EmailObject object, String pathToAttachment) throws Exception;
}
