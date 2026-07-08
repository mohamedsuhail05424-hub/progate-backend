package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Portfolio;
import com.example.demo.repository.PortfolioRepository;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class  PortfolioService 
{

    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) 
    {
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> getAllPortfolios() 
    {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> getPortfolioByIdOpt(Long id) 
    {
        return portfolioRepository.findById(id);
    }

    public Portfolio getPortfolioById (Long id) 
    {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found with id: " + id));
    }

    public Portfolio savePortfolio(Portfolio portfolio) 
    {
        return portfolioRepository.save(portfolio);
    }

    public void deletePortfolio(Long id) 
    {
        portfolioRepository.deleteById(id);
    }
}