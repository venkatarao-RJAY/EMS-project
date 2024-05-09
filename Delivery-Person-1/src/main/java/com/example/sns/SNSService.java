  package com.example.sns;

/*
 * import com.amazonaws.services.sns.AmazonSNS; import
 * com.amazonaws.services.sns.model.PublishRequest; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.stereotype.Service;
 * 
 * @Service public class SNSService {
 * 
 * @Autowired private AmazonSNS amazonSNS; // Inject the AmazonSNS client bean
 * created by AwsConfig
 * 
 * @Value("${sms.mobileNumber}") private String mobileNumber;
 * 
 * @Value("${aws.sns.topicArn}") private String topicArn; // Configure this in
 * your application.properties
 * 
 * public void sendSMS(String message, String phoneNumber) { PublishRequest
 * request = new PublishRequest() .withMessage(message)
 * .withPhoneNumber(phoneNumber);
 * 
 * amazonSNS.publish(request); } }
 */

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNSService {

    private static AmazonSNS amazonSNS;

    @Autowired
    public SNSService(AmazonSNS amazonSNS) {
        this.amazonSNS = amazonSNS;
    }

    public static void sendSms(String phoneNumber, String message) {
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber);

        PublishResult result = amazonSNS.publish(publishRequest);
        String messageId = result.getMessageId();

        System.out.println("SMS sent to " + phoneNumber + ". MessageId: " + messageId);
    }
}
