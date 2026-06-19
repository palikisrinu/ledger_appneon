package com.example.demo.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.demo.dto.DailyRequest;
import com.example.demo.dto.LedgerDaily;
import com.example.demo.dto.LedgerSheet;
import com.example.demo.dto.Line;
import com.example.demo.dto.PaymentEntry;
import com.example.demo.dto.SheetRequest;
import com.example.demo.repository.LedgerSheetRepository;
import com.example.demo.repository.LineRepository;
import com.example.demo.service.LedgerService;

//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "http://10.150.191.138:5173"
	})
@RestController
@RequestMapping("/ledger")
public class Ledger_Controller {
	
	@Autowired
	private LineRepository linerepo;
	
	@Autowired
	private final LedgerService ledgerService;
	
	@Autowired
	private LedgerSheetRepository sheetRepo;
	
	public Ledger_Controller(LedgerService ledgerService) {
		this. ledgerService=ledgerService;
	}
		
	@PostMapping("/line")
    public Line createLine(@RequestBody Line line) {
        return linerepo.save(line);
    }
		
	 @PostMapping("/sheet")
	    public LedgerSheet createSheet(@RequestBody SheetRequest request) {

	        Line line = linerepo.findById(request.getLineId())
	                .orElseThrow(() -> new RuntimeException("Line not found"));

	        LedgerSheet sheet = new LedgerSheet();
	        sheet.setLine(line);
	        sheet.setMonth(request.getMonth());
	        sheet.setYear(request.getYear());
	        sheet.setOpeningBf(request.getOpeningBf());
	        sheet.setStartDate(request.getStartDate());
	        return sheetRepo.save(sheet);
	 }
	 
	 //creating the new daily entries...
	 @PostMapping("/daily/{sheetId}")
	    public ResponseEntity<LedgerDaily>  createDaily(  @PathVariable Long sheetId, @RequestBody DailyRequest request) {
		 
		  LedgerDaily result=  ledgerService.createDaily(sheetId,request);
	       return ResponseEntity.ok(result);
	    }
	 
	 //fetching the already existed lines....
	@GetMapping("/lines")
	public List<Line> getAllData(){
		return ledgerService.getAllData();
		
	}
	
	//as per the lines id fetching the sheets 
	@GetMapping("/lines/{lineId}/sheets")
	public List<LedgerSheet> getSheetsByLine(@PathVariable Long lineId){  
		return ledgerService.getSheetsByLine(lineId);
		
	}
	@GetMapping("/sheet/{sheetId}")
	public LedgerSheet getCurrentSheet(@PathVariable Long sheetId) {
		return ledgerService.getSheetById(sheetId);
	}
	//fetching the all the daily entries as per the sheet id
	 @GetMapping("/daily/{sheetId}")
	    public List<LedgerDaily> getDailyData(@PathVariable Long sheetId) {
		 
	        return ledgerService.getDailyData(sheetId);
	    }
	 
	 //fetching only the payments
	  @GetMapping("/payments/{dailyId}")
	    public List<PaymentEntry> getPayments(@PathVariable Long dailyId) {

	        return ledgerService.getPayments(dailyId);
	    }
	  
	  //updating the daily entries if getting some mistakes we can change as per the change all should be updated...
	  @PutMapping("/daily/{dailyId}")
	  public LedgerDaily updateDaily(
	          @PathVariable Long dailyId,
	          @RequestBody DailyRequest request) {
	
		
	      return   ledgerService.updateDaily(dailyId, request);
	  }
	  
	  @DeleteMapping("/daily/{id}")
	  public ResponseEntity<String> deleteDaily(@PathVariable Long id){
		  ledgerService.deleteDaily(id);
		  return ResponseEntity.ok("Entry deleted successfully");
	  }
}
