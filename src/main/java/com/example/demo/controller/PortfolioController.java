package com.example.demo.controller;

import com.example.demo.entity.Portfolio;
import com.example.demo.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController 
{
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public List<Portfolio> getAllPortfolios()
    {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/{id}")
    public Portfolio getPortfolio(@PathVariable Long id)
    {
        return portfolioService.getPortfolioById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio)
    {
        return portfolioService.savePortfolio(portfolio);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Long id, @RequestBody Portfolio portfolioDetails)
    {
        return portfolioService.getPortfolioByIdOpt(id)
                .map(portfolio -> {
                    portfolio.setTotalInvestment(portfolioDetails.getTotalInvestment());
                    portfolio.setTotalIncome(portfolioDetails.getTotalIncome());
                    portfolio.setRentalYield(portfolioDetails.getRentalYield());
                    portfolio.setOccupancyRate(portfolioDetails.getOccupancyRate());
                    return ResponseEntity.ok(portfolioService.savePortfolio(portfolio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long id)
    {
        return portfolioService.getPortfolioByIdOpt(id)
                .map(portfolio -> {
                    portfolioService.deletePortfolio(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
