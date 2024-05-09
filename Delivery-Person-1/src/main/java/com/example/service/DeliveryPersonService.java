package com.example.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.DeliveryPerson;
import com.example.repo.DeliveryPersonRepository;

@Service
public class DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public DeliveryPersonService(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    // Create operation
    public DeliveryPerson createDeliveryPerson(DeliveryPerson deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }

    // Read operation
    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    public Optional<DeliveryPerson> getDeliveryPersonById(int id) {
        return deliveryPersonRepository.findById(id);
    }

    // Update operation
    public DeliveryPerson updateDeliveryPerson(int id, DeliveryPerson updatedDeliveryPerson) {
        DeliveryPerson existingDeliveryPerson = deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery person not found with id: " + id));

        existingDeliveryPerson.setD_name(updatedDeliveryPerson.getD_name());
        existingDeliveryPerson.setD_phone_num(updatedDeliveryPerson.getD_phone_num());
        existingDeliveryPerson.setD_emailId(updatedDeliveryPerson.getD_emailId());
        existingDeliveryPerson.setPassword(updatedDeliveryPerson.getPassword());
        existingDeliveryPerson.setLicence(updatedDeliveryPerson.getLicence());
        existingDeliveryPerson.setVehicleNumber(updatedDeliveryPerson.getVehicleNumber());

        return deliveryPersonRepository.save(existingDeliveryPerson);
    }

    // Delete operation
    public void deleteDeliveryPerson(int id) {
        deliveryPersonRepository.deleteById(id);
    }
}
