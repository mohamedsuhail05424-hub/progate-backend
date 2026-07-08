package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.entity.Property.Status;
import com.example.demo.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService 
{
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getApprovedProperties() 
    {
        return propertyRepository.findByStatus(Status.APPROVED);
    }

    public List<Property> getAllProperties() 
    {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) 
    {
        return propertyRepository.findById(id);
    }

    public Property saveProperty(Property property) 
    {
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long id) 
    {
        propertyRepository.deleteById(id);
    }

}
