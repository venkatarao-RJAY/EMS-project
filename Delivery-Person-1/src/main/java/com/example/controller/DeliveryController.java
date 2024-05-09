 
package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.example.entity.Customer;
import com.example.entity.DeliveryDetails;
import com.example.entity.DeliveryPerson;
import com.example.entity.Sender;
import com.example.repo.DeliveryDetailsRepository;
 import com.example.service.DeliveryDetailsService;
 
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	private final DeliveryDetailsService deliveryService;
	@Autowired
 DeliveryDetailsRepository dp;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired

	private AmazonSNS amazonSNS;


	@Autowired
	public DeliveryController(DeliveryDetailsService deliveryService ) {
		this.deliveryService = deliveryService;
 	}

 
	 
	    
	
	
	 
	@PostMapping("/CustomerOrder")
	public ResponseEntity<String> createReceiverDetails(@RequestBody Customer receiver) {
		try {
			// Save the receiver
			Customer savedReceiver = dp.save(receiver);

			// Create and save the sender
			Sender sender = new Sender();
			// sender.setFirstName(savedReceiver.getC_firstName());
			// sender.setLastName(savedReceiver.getC_lastName());
			sender.setC_emailId(savedReceiver.getC_emailId());
			// sender.setPassword(savedReceiver.getPassword());
			// sender.setPhoneNo(savedReceiver.getC_phoneNo());
			//// sender.setD_name(savedReceiver.getD_name());
			// sender.setPhone_num(savedReceiver.getPhone_num());
			sender.setC_firstName(savedReceiver.getC_firstName());
			sender.setC_phoneNo(savedReceiver.getC_phoneNo());
			sender.setProductname(savedReceiver.getProductname());
			sender.setOrderDateTime(savedReceiver.getOrderDateTime());
			sender.setDeliveryDateTime(savedReceiver.getDeliveryDateTime());
			sender.setOrderId(savedReceiver.getOrderId());
			sender.setReceiver_id(savedReceiver.getReceiver_id());
			sender = dp.save(sender);  

 			DeliveryDetails deliveryPerson = new DeliveryDetails();
			deliveryPerson.setC_firstName(savedReceiver.getC_firstName());
			deliveryPerson.setC_lastName(savedReceiver.getC_lastName());
			deliveryPerson.setC_emailId(savedReceiver.getC_emailId());
			deliveryPerson.setC_phoneNo(savedReceiver.getC_phoneNo());
 			deliveryPerson.setSender(sender); // Set sender relationship
			deliveryPerson.setCustomer(savedReceiver); // Set receiver relationship
			deliveryPerson.setOrderDateTime(LocalDateTime.now()); // Set order date time
			deliveryPerson.setDeliveryDateTime(LocalDateTime.now().plusDays(1));
			deliveryPerson.setOrderId(savedReceiver.getOrderId());
			deliveryPerson.setDeliveryStatus("Active");
			deliveryPerson.setDeliveryLocation(savedReceiver.getDeliverylocation());
			dp.save(deliveryPerson);

			String emailContent = "Dear " + savedReceiver.getC_firstName() + ",\n\n"
					+ "Thank you for your order! Your product, " + savedReceiver.getProductname()
					+ ", has been successfully ordered.\n" + "Your order ID is: " + savedReceiver.getOrderId() + ".\n\n"
					+ "Best regards,\n";

//Send the email with constructed content
			sendEmail(savedReceiver.getC_emailId(), "Order Confirmation", emailContent);
			 

			sendSms(savedReceiver.getC_phoneNo(), "Your order has been placed successfully.");

			
			return new ResponseEntity<>("Receiver, Sender, and DeliveryPerson created successfully",
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to create Receiver, Sender, and DeliveryPerson",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	@PutMapping("/{SenderUpdateOrderId}")
	public void updateSenderAndAssociatedEntities(String orderId, String firstName, String lastName, String emailId, String password, Long phoneNo, String d_name, Long d_phone_num, String d_emailId) {
	    // Retrieve the sender entity from the repository
	    Sender sender = dp.findByOrderId2(orderId); 
	    
	    if (sender == null) {
	        throw new IllegalArgumentException("Sender not found");
	    }
	     
	    // Update the specific fields in the sender entity
	     
 

	    // Update the specific fields in the sender entity
	    sender.setFirstName(firstName);
	    sender.setLastName(lastName);
	    sender.setEmailId(emailId);
	    sender.setPassword(password);
	    sender.setPhoneNo(phoneNo);
	    sender.setD_name(d_name);
	    sender.setD_phone_num(d_phone_num);
	    sender.setD_emailId(d_emailId);

	    // Save the updated sender entity
	    dp.save(sender);

	    // Update associated DeliveryPerson entities with the same senderId
	    List<DeliveryDetails> deliveryPersons = dp.findByOrderId1(orderId);
	    for (DeliveryDetails deliveryPerson : deliveryPersons) {
	         
	        deliveryPerson.setD_name(d_name);
	        deliveryPerson.setD_phone_num(d_phone_num);
	        deliveryPerson.setD_emailId(d_emailId);
	        dp.save(deliveryPerson);
	        
	        String emailContent = "Dear " + d_name + ",\n\n" +
	                 sender.getProductname() + ", has been successfully ordered.\n"  
		    		+"customer name"+sender.getC_firstName()+"mobileno"+sender.getC_phoneNo()+
	                "Your order ID is: " + sender.getOrderId() + ".\n\n" +
	                "Best regards,\n" ;
			sendEmail(sender.getD_emailId(), "Order Confirmation", emailContent);
			
			
			String emailContent1 = "Dear " + sender.getC_firstName() + ",\n\n"
					+ "Thank you for your order! Your product, " + sender.getProductname()
					+ ", has been successfully ordered.\n" + "Your order ID is: " + sender.getOrderId() + ".\n\n"
					+ "your deliveryperson is  mr  "+sender.getD_name()+"phone no"+sender.getD_phone_num();
			sendEmail(sender.getD_emailId(), "Order Confirmation", emailContent1);
			String sms1 = "Dear " + sender.getC_firstName() + ",\n\n"
					+ "Thank you for your order! Your product, " + sender.getProductname()
					+ ", has been successfully ordered.\n" + "Your order ID is: " + sender.getOrderId() + ".\n\n"
					+ "your deliveryperson is  mr  "+sender.getD_name()+"phone no"+sender.getD_phone_num();
			sendSms(sender.getD_phone_num(), "Your order has been placed successfully.");


	    }

	    // Update associated Receiver entities with the same senderId
	    List<Customer> receivers = dp.findByOrderId(orderId);
	    for (Customer receiver : receivers) {
	         
			
			  
			
	        //receiver.setD_emailId(d_emailId);
	        dp.save(receiver);
	        
	        String emailContent1 = "Dear " + sender.getC_firstName() + ",\n\n"
					+ "Thank you for your order! Your product, " + sender.getProductname()
					+ ", has been successfully ordered.\n" + "Your order ID is: " + sender.getOrderId() + ".\n\n"
					+ "your deliveryperson is  mr  "+sender.getD_name()+"phone no"+sender.getD_phone_num();
			sendEmail(sender.getD_emailId(), "Order Confirmation", emailContent1);
			sendSms(sender.getD_phone_num(), "Your order has been placed successfully.");

	    }
	     
		
		
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