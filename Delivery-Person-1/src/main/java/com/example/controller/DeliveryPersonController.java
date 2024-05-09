package com.example.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.example.entity.DeliveryPerson;
import com.example.service.DeliveryPersonService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/deliverypersons")
public class DeliveryPersonController {
	
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired

	private AmazonSNS amazonSNS;


    private final DeliveryPersonService deliveryPersonService;

    @Autowired
    public DeliveryPersonController(DeliveryPersonService deliveryPersonService) {
        this.deliveryPersonService = deliveryPersonService;
    }

    @PostMapping
    public ResponseEntity<DeliveryPerson> createDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        DeliveryPerson createdDeliveryPerson = deliveryPersonService.createDeliveryPerson(deliveryPerson);

        // Send email
        String emailContent = "Dear " + deliveryPerson.getD_name() +
                "Your Registration was successful. Best regards,\n";
        sendEmail(deliveryPerson.getD_emailId(), "Registration Confirmation", emailContent);

        // Send SMS
        sendSms(deliveryPerson.getD_phone_num(), "Your Registration has been successful.");

        return new ResponseEntity<>(createdDeliveryPerson, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<DeliveryPerson>> getAllDeliveryPersons() {
        List<DeliveryPerson> deliveryPersons = deliveryPersonService.getAllDeliveryPersons();
        return new ResponseEntity<>(deliveryPersons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPerson> getDeliveryPersonById(@PathVariable int id) {
        Optional<DeliveryPerson> deliveryPerson = deliveryPersonService.getDeliveryPersonById(id);
        return deliveryPerson.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryPerson> updateDeliveryPerson(@PathVariable int id, @RequestBody DeliveryPerson updatedDeliveryPerson) {
        DeliveryPerson updatedPerson = deliveryPersonService.updateDeliveryPerson(id, updatedDeliveryPerson);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryPerson(@PathVariable int id) {
        deliveryPersonService.deleteDeliveryPerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    private void sendEmail(String to, String subject, String text) {
		try {
			// Create a MimeMessage
			MimeMessage message = mailSender.createMimeMessage();
			// Enable multipart mode
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			// Set email properties
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			// Send the email
			mailSender.send(message);
		} catch (MessagingException | MailException e) {
			// Handle exception
			e.printStackTrace();
		}
	}
	
	public String sendSms(@RequestBody long phoneNumber, @RequestBody String message) {
	    PublishRequest publishRequest = new PublishRequest()
	            .withMessage(message)
	            .withPhoneNumber(Long.toString(phoneNumber)); // Phone number should be passed as a string

	    PublishResult result = amazonSNS.publish(publishRequest);
	    System.out.println("SMS sent. MessageId: " + result.getMessageId());
	    return "SMS sent successfully to " + phoneNumber;
	}
}
