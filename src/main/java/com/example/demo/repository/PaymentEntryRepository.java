package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.PaymentEntry;

public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, Long>{
	
	List<PaymentEntry> findByDailyId(Long dailyId);
	
	void deleteByDailyId(Long dailyId);
}
