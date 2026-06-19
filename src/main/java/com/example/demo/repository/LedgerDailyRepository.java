package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.dto.LedgerDaily;

public interface LedgerDailyRepository extends JpaRepository<LedgerDaily, Long>{

//	@Query("SELECT l FROM LedgerDaily l WHERE l.sheet.id = :sheetId ORDER BY l.transactionDate DESC")
//	List<LedgerDaily> findBySheetIdOrderByDate(@Param("sheetId") Long sheetId);
	Optional<LedgerDaily> findTopBySheetIdOrderByTransactionDateDesc(Long sheetId);
	List<LedgerDaily> findBySheetIdOrderByTransactionDateAsc(Long sheetId);
	
	List<LedgerDaily> findBySheetIdAndTransactionDateGreaterThanEqualOrderByTransactionDateAsc(
	        Long sheetId,
	        LocalDate transactionDate
	);
}
