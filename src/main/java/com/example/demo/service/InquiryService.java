package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Inquiry;
import com.example.demo.repository.InquiryRepository;

@Service
public class InquiryService 
{
    private final InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) 
    {
        this.inquiryRepository = inquiryRepository;
    }

    public List<Inquiry> getAllInquiries() 
    {
        return inquiryRepository.findAll();
    }

    public Optional<Inquiry> getInquiryById(Long id) 
    {
        return inquiryRepository.findById(id);
    }

    public Inquiry saveInquiry(Inquiry inquiry) 
    {
        return inquiryRepository.save(inquiry);
    }
}
