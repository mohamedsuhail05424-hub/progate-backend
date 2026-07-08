package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.RentPayment;
import com.example.demo.entity.RentPayment.Status;

@Repository
public interface RentPaymentRepository extends JpaRepository<RentPayment,Long>
{
    List<RentPayment> findByStatus(Status status);
}
