package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.RentPayment;
import com.example.demo.entity.RentPayment.Status;
import com.example.demo.repository.RentPaymentRepository;

import java.util.Optional;

@Service
public class RentPaymentService 
{
    private final RentPaymentRepository rentPaymentRepository;

    public RentPaymentService(RentPaymentRepository rentPaymentRepository) 
    {
        this.rentPaymentRepository = rentPaymentRepository;
    }

    public List<RentPayment> getRentPaymentsByStatus(Status status) 
    {
        return rentPaymentRepository.findByStatus(status);
    }

    public List<RentPayment> getAllRentPayments() 
    {
        return rentPaymentRepository.findAll();
    }

    public Optional<RentPayment> getRentPaymentById(Long id) 
    {
        return rentPaymentRepository.findById(id);
    }

    public RentPayment saveRentPayment(RentPayment rentPayment) 
    {
        return rentPaymentRepository.save(rentPayment);
    }

    public void deleteRentPayment(Long id) 
    {
        rentPaymentRepository.deleteById(id);
    }
}
