package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DailyRequest;
import com.example.demo.dto.LedgerDaily;
import com.example.demo.dto.LedgerSheet;
import com.example.demo.dto.Line;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.dto.PaymentEntry;
import com.example.demo.dto.SheetRequest;
import com.example.demo.repository.LedgerDailyRepository;
import com.example.demo.repository.LedgerSheetRepository;
import com.example.demo.repository.LineRepository;
import com.example.demo.repository.PaymentEntryRepository;

import jakarta.transaction.Transactional;

@Service
public class LedgerService {
	
	
		private final LedgerSheetRepository sheetRepo;
	    private final LedgerDailyRepository dailyRepo;
	    private final PaymentEntryRepository paymentRepo;
	    private final LineRepository lineRepo;
	    public LedgerService(LedgerSheetRepository sheetRepo,
	                         LedgerDailyRepository dailyRepo,
	                         PaymentEntryRepository paymentRepo,
	                         LineRepository lineRepo) {
	        this.sheetRepo = sheetRepo;
	        this.dailyRepo = dailyRepo;
	        this.paymentRepo = paymentRepo;
	        this.lineRepo=lineRepo;
	    }
	    
	   
	    public LedgerDaily createDaily(Long sheetId, DailyRequest request ) {

	        LedgerSheet sheet = sheetRepo.findById(sheetId)
	                .orElseThrow(() -> new RuntimeException("Sheet not found"));
	        
//	        LedgerDaily previous = dailyRepo
//	                .findTopBySheetIdOrderByTransactionDateDesc(request.getSheetId())
//	                .orElse(null);
//	        
	        LedgerDaily previous = dailyRepo
	        		.findTopBySheetIdOrderByTransactionDateDesc(sheetId)
	                .orElse(null);
//	            System.out.println("sheet data: "+sheet);
	            System.out.println("previous data: "+previous);
	            System.out.println("Sheet ID: " + sheet.getId());
	            System.out.println("Opening BF: " + sheet.getOpeningBf());
	            System.out.println("Start Date: " + sheet.getStartDate());
	            
	        BigDecimal openingBalance;
	        LocalDate transactionDate;
	        
	        if (previous != null) {
	            openingBalance = previous.getCloseingBalance(); // carry forward
	        } else {
	            openingBalance = sheet.getOpeningBf(); // first day
	        }
	       
//	        System.out.println( "sheet data from the outside: ............ "+sheet);
	        LedgerDaily daily = new LedgerDaily();
	        daily.setSheet(sheet);
//	        daily.setTransactionDate(request.getTransactionDate());
	        daily.setCollection(request.getCollection());
	        daily.setPhonepe(request.getPhonepe());
	        daily.setExpenses(request.getExpenses());
	        
			
	        if (previous == null || previous.getTransactionDate() == null) {

	            transactionDate = sheet.getStartDate();

	        } else {

	            transactionDate =
	                    previous.getTransactionDate().plusDays(1);
	        }
			
			daily.setTransactionDate(transactionDate);
				        
				      
	        // 👉 Calculate totals
	        BigDecimal totalPayments = BigDecimal.ZERO;
	        BigDecimal totalCharges = BigDecimal.ZERO;

	        for (PaymentDTO p : request.getPayments()) {
	            totalPayments = totalPayments.add(p.getPayment());
	            totalCharges = totalCharges.add(p.getCharges());
	        }

	        daily.setTotalPayments(totalPayments);
	        daily.setTotalCharges(totalCharges);

	        // 👉 Calculate closing balance
	        BigDecimal closing = openingBalance
	                .add(request.getCollection())
	                .add(totalCharges)
	                .subtract(totalPayments);

	        daily.setCloseingBalance(closing);


	        
        LedgerDaily savedDaily = dailyRepo.save(daily);

//	         👉 Save payment entries
	        for (PaymentDTO p : request.getPayments()) {
	            PaymentEntry entry = new PaymentEntry();
	            entry.setDaily(savedDaily);
	            entry.setPayment(p.getPayment());
	            entry.setCharges(p.getCharges());

	            paymentRepo.save(entry);
	        } 
       
	        return savedDaily;
	    }

		public List<Line> getAllData() {
			List<Line> lines=lineRepo.findAll();
//			System.out.println("lines are the .........."+lines);
			return lines;
		}


		public List<LedgerSheet> getSheetsByLine(Long lineId) {

		    List<LedgerSheet> sheets = sheetRepo.findByLineId(lineId);

		   

		    return sheets;
		}
		
		 public List<LedgerDaily> getDailyData(Long sheetId) {
			List<LedgerDaily> x= dailyRepo.findBySheetIdOrderByTransactionDateAsc(sheetId);
			System.out.println(x);
		        return x;
		    }
		 
		   public List<PaymentEntry> getPayments(Long dailyId) {

		        return paymentRepo.findByDailyId(dailyId);
		   }
		   
		   
		   @Transactional
		   public LedgerDaily updateDaily(Long dailyId, DailyRequest request) {

		       // 1️⃣ Find current entry
		       LedgerDaily current = dailyRepo.findById(dailyId)
		               .orElseThrow(() -> new RuntimeException("Daily entry not found"));

		       // 2️⃣ Update values
		       current.setCollection(request.getCollection());
		       current.setPhonepe(request.getPhonepe());
		       current.setExpenses(request.getExpenses());

		       // 3️⃣ Delete old payment entries
		       paymentRepo.deleteByDailyId(current.getId());

		       // 4️⃣ Add new payment entries
		       BigDecimal totalPayments = BigDecimal.ZERO;
		       BigDecimal totalCharges = BigDecimal.ZERO;

		       for (PaymentDTO p : request.getPayments()) {

		           PaymentEntry entry = new PaymentEntry();

		           entry.setDaily(current);
		           entry.setPayment(p.getPayment());
		           entry.setCharges(p.getCharges());

		           paymentRepo.save(entry);

		           totalPayments = totalPayments.add(p.getPayment());
		           totalCharges = totalCharges.add(p.getCharges());
		       }

		       current.setTotalPayments(totalPayments);
		       current.setTotalCharges(totalCharges);

		       // 5️⃣ Get all entries from edited date onward
		       List<LedgerDaily> entries =
		               dailyRepo
		               .findBySheetIdAndTransactionDateGreaterThanEqualOrderByTransactionDateAsc(
		                       current.getSheet().getId(),
		                       current.getTransactionDate()
		               );

		       // 6️⃣ Previous closing
		       BigDecimal previousClosing;

		       List<LedgerDaily> previousList =
		               dailyRepo.findBySheetIdOrderByTransactionDateAsc(
		                       current.getSheet().getId());

		       int currentIndex = previousList.indexOf(current);

		       if (currentIndex > 0) {

		           previousClosing =
		                   previousList.get(currentIndex - 1).getCloseingBalance();

		       } else {

		           previousClosing =
		                   current.getSheet().getOpeningBf();
		       }

		       // 7️⃣ Recalculate all entries sequentially
		       for (LedgerDaily daily : entries) {

		           BigDecimal closing = previousClosing
		                   .add(daily.getCollection())
		                   .add(daily.getTotalCharges())
		                   .subtract(daily.getTotalPayments());

		           daily.setCloseingBalance(closing);

		           previousClosing = closing;

		           dailyRepo.save(daily);
		       }

		       return current;
		   }
		   
		   public LedgerSheet getSheetById(Long sheetId) {
			return sheetRepo.findById(sheetId) .orElseThrow(() ->
            new RuntimeException("Sheet not found"));
		   }


		   public void deleteDaily(Long id) {
			   LedgerDaily daily = dailyRepo.findById(id)
			            .orElseThrow(() -> new RuntimeException("Entry not found"));
			   dailyRepo.deleteById(id);
		   }
		}
	    
	   

