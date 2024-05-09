package com.example.entity;

 
 
import java.time.LocalDateTime;
import java.util.List;

 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Sender_id;

    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private Long phoneNo;
    private String d_name;
    private Long d_phone_num;
    private String d_emailId;

    private int Receiver_id;
    private String c_firstName;
    private Long c_phoneNo;
    private String c_emailId;
    private String productname;
     
    private String orderId;

    
    
    

    private LocalDateTime OrderDateTime;
    private LocalDateTime deliveryDateTime;

     

    @PrePersist
    public void setDeliveryDateTime() {
         if (this.OrderDateTime != null) {
             this.deliveryDateTime = this.OrderDateTime.plusDays(1);
        }
    }
}
