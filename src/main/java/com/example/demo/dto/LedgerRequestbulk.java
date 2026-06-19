package com.example.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LedgerRequestbulk {
	
	

		private Double  manualBf;    // ✅ Manual BF
		@JsonProperty("Collection")
	    private Double  collection;
		@JsonProperty("Total Charges")
	    private Double  totalCharges;
		@JsonProperty("Payment")
	    private Double  totalPayments;
		@JsonProperty("BF")
	    private Double  balance;
	    @JsonProperty("Pending")
		private Double  pending;     // Next Pending
		@JsonFormat(pattern = "dd/MM/yyyy")
		@JsonProperty("Date")
	    private LocalDate TransactionDate;// Next BF
	    
	    private Double  phonepay;
	    
	    private Double  expenses;

		public Double getManualBf() {
			return manualBf;
		}

		public void setManualBf(Double manualBf) {
			this.manualBf = manualBf;
		}

		public Double getCollection() {
			return collection;
		}

		public void setCollection(Double collection) {
			this.collection = collection;
		}

		public Double getTotalCharges() {
			return totalCharges;
		}

		public void setTotalCharges(Double totalCharges) {
			this.totalCharges = totalCharges;
		}

		public Double getTotalPayments() {
			return totalPayments;
		}

		public void setTotalPayments(Double totalPayments) {
			this.totalPayments = totalPayments;
		}

		public Double getBalance() {
			return balance;
		}

		public void setBalance(Double balance) {
			this.balance = balance;
		}

		public Double getPending() {
			return pending;
		}

		public void setPending(Double pending) {
			this.pending = pending;
		}

		public LocalDate getTransactionDate() {
			return TransactionDate;
		}

		public void setTransactionDate(LocalDate transactionDate) {
			TransactionDate = transactionDate;
		}

		public Double getPhonepay() {
			return phonepay;
		}

		public void setPhonepay(Double phonepay) {
			this.phonepay = phonepay;
		}

		public Double getExpenses() {
			return expenses;
		}

		public void setExpenses(Double expenses) {
			this.expenses = expenses;
		}

		
}
