package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/properties")
public class PropertyController 
{
    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<Property> getApprovedProperties()
    {
        return propertyService.getApprovedProperties();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public List<Property> getAllProperties()
    {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id)
    {
        return propertyService.getPropertyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public Property createProperty(@RequestBody Property property)
    {
        if (property.getStatus() == null) {
            property.setStatus(Property.Status.PENDING);
        }
        return propertyService.saveProperty(property);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails)
    {
        return propertyService.getPropertyById(id)
                .map(property -> {
                    property.setPropertyName(propertyDetails.getPropertyName());
                    property.setType(propertyDetails.getType());
                    property.setLocation(propertyDetails.getLocation());
                    property.setPurchasePrice(propertyDetails.getPurchasePrice());
                    property.setCurrentValue(propertyDetails.getCurrentValue());
                    property.setRentalIncome(propertyDetails.getRentalIncome());
                    property.setAppreciationRate(propertyDetails.getAppreciationRate());
                    property.setStatus(propertyDetails.getStatus());
                    return ResponseEntity.ok(propertyService.saveProperty(property));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('AGENT')")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id)
    {
        return propertyService.getPropertyById(id)
                .map(property -> {
                    propertyService.deleteProperty(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
