package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity
@Table(name = "ledger_daily")
public class LedgerDaily {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "transaction_date")
	    private LocalDate transactionDate;// Next BF

	    // Single values
	    private BigDecimal collection = BigDecimal.ZERO;
	    private BigDecimal phonepe = BigDecimal.ZERO;
	    private BigDecimal expenses = BigDecimal.ZERO;

	    // Totals
	    private BigDecimal totalPayments = BigDecimal.ZERO;
	    private BigDecimal totalCharges = BigDecimal.ZERO;

	    // Balance
	   
	    private BigDecimal closeingBalance;
		

	    @ManyToOne
	    @JoinColumn(name = "sheet_id", nullable = false)
	    @JsonIgnore
	    private LedgerSheet sheet;

	    @OneToMany(mappedBy = "daily", cascade = CascadeType.ALL)
	    
	    private List<PaymentEntry> paymentEntries;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	

		public LocalDate getTransactionDate() {
			return transactionDate;
		}

		public void setTransactionDate(LocalDate transactionDate) {
			this.transactionDate = transactionDate;
		}

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

		public BigDecimal getTotalPayments() {
			return totalPayments;
		}

		public void setTotalPayments(BigDecimal totalPayments) {
			this.totalPayments = totalPayments;
		}

		public BigDecimal getTotalCharges() {
			return totalCharges;
		}

		public void setTotalCharges(BigDecimal totalCharges) {
			this.totalCharges = totalCharges;
		}		
		public LedgerSheet getSheet() {
			return sheet;
		}

		public void setSheet(LedgerSheet sheet) {
			this.sheet = sheet;
		}

		public List<PaymentEntry> getPaymentEntries() {
			return paymentEntries;
		}

		public void setPaymentEntries(List<PaymentEntry> paymentEntries) {
			this.paymentEntries = paymentEntries;
		}
		  public BigDecimal getCloseingBalance() {
				return closeingBalance;
			}

			public void setCloseingBalance(BigDecimal closeingBalance) {
				this.closeingBalance = closeingBalance;
			}

			@Override
			public String toString() {
				return "LedgerDaily [id=" + id + ", transactionDate=" + transactionDate + ", collection=" + collection
						+ ", phonepe=" + phonepe + ", expenses=" + expenses + ", totalPayments=" + totalPayments
						+ ", totalCharges=" + totalCharges + ", closeingBalance=" + closeingBalance + ", sheet=" + sheet
						+ ", paymentEntries=" + paymentEntries + "]";
			}
			
	    
}
