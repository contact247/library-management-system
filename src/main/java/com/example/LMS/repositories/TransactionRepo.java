package com.example.LMS.repositories;

import com.example.LMS.models.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo  extends JpaRepository<Transaction, Integer> {
}
