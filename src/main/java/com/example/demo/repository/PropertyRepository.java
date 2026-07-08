package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Property;
import com.example.demo.entity.Property.Status;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> 
{
    
    List<Property> findByStatus(Status status);
}