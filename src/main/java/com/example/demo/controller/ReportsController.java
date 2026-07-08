package com.example.demo.controller;

import com.example.demo.entity.Report;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reports")
public class ReportsController 
{
    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports()
    {
        return reportService.getAllReports();
    }

    @PostMapping
    public Report createReport(@RequestBody Report report)
    {
        if (report.getGeneratedDate() == null) {
            report.setGeneratedDate(java.time.LocalDateTime.now());
        }
        return reportService.saveReport(report);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<?> deleteReport(@PathVariable Long id)
    {
        return reportService.getReportById(id)
                .map(report -> {
                    reportService.deleteReport(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
