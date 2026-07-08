package com.example.demo.controller;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inquiries")
public class InquiryController 
{
    @Autowired
    private InquiryService inquiryService;

    @GetMapping
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public List<Inquiry> getAllInquiries()
    {
        return inquiryService.getAllInquiries();
    }

    @PostMapping
    public Inquiry createInquiry(@RequestBody Inquiry inquiry)
    {
        inquiry.setStatus(Inquiry.Status.NEW);
        return inquiryService.saveInquiry(inquiry);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AGENT') or hasRole('ADMIN')")
    public ResponseEntity<Inquiry> updateInquiry(@PathVariable Long id, @RequestBody Inquiry inquiryDetails)
    {
        return inquiryService.getInquiryById(id)
                .map(inquiry -> 
                {
                    inquiry.setStatus(inquiryDetails.getStatus());
                    return ResponseEntity.ok(inquiryService.saveInquiry(inquiry));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
