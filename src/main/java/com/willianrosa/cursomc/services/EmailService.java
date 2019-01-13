package com.willianrosa.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.willianrosa.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
