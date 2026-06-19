package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.LedgerSheet;
import com.example.demo.dto.SheetRequest;

public interface LedgerSheetRepository extends JpaRepository<LedgerSheet, Long>{

		List<LedgerSheet> findByLineId(Long LineId);
}
