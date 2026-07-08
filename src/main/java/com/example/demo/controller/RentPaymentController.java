package com.example.demo.controller;

import com.example.demo.entity.RentPayment;
import com.example.demo.service.RentPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rent-payments")
public class RentPaymentController 
{
    @Autowired
    private RentPaymentService rentPaymentService;

    @GetMapping
    public List<RentPayment> getAllRentPayments()
    {
        return rentPaymentService.getAllRentPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentPayment> getRentPaymentById(@PathVariable Long id)
    {
        return rentPaymentService.getRentPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public RentPayment createRentPayment(@RequestBody RentPayment rentPayment)
    {
        if (rentPayment.getStatus() == null) {
            rentPayment.setStatus(RentPayment.Status.PENDING);
        }
        return rentPaymentService.saveRentPayment(rentPayment);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<RentPayment> updateRentPayment(@PathVariable Long id, @RequestBody RentPayment rentPaymentDetails)
    {
        return rentPaymentService.getRentPaymentById(id)
                .map(rentPayment -> {
                    rentPayment.setAmount(rentPaymentDetails.getAmount());
                    rentPayment.setPaymentDate(rentPaymentDetails.getPaymentDate());
                    rentPayment.setDueDate(rentPaymentDetails.getDueDate());
                    rentPayment.setStatus(rentPaymentDetails.getStatus());
                    return ResponseEntity.ok(rentPaymentService.saveRentPayment(rentPayment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<?> deleteRentPayment(@PathVariable Long id)
    {
        return rentPaymentService.getRentPaymentById(id)
                .map(rentPayment -> {
                    rentPaymentService.deleteRentPayment(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
