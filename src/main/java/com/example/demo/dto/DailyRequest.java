package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyRequest {
		
	private Long sheetId;
	
//    private LocalDate transactionDate;
    private BigDecimal collection;
    private BigDecimal phonepe;
    private BigDecimal expenses;


    private List<PaymentDTO> payments;

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	

//	public LocalDate getTransactionDate() {
//		return transactionDate;
//	}
//
//	public void setTransactionDate(LocalDate transactionDate) {
//		this.transactionDate = transactionDate;
//	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public BigDecimal getPhonepe() {
		return phonepe;
	}

	public void setPhonepe(BigDecimal phonepe) {
		this.phonepe = phonepe;
	}

	public BigDecimal getExpenses() {
		return expenses;
	}

	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}

	

	public List<PaymentDTO> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentDTO> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "DailyRequest [sheetId=" + sheetId + ", collection="
				+ collection + ", phonepe=" + phonepe + ", expenses=" + expenses + ", payments=" + payments + "]";
	}

    
}
