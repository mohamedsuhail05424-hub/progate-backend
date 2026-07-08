package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;

@Service
public class ReportService 
{ 

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() 
    {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) 
    {
        return reportRepository.findById(id);
    }

    public Report saveReport(Report report) 
    {
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) 
    {
        reportRepository.deleteById(id);
    }
}
