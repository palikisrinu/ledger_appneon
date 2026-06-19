package com.example.demo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "ledger_payment_entries")
public class PaymentEntry {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private BigDecimal payment = BigDecimal.ZERO;
	    private BigDecimal charges = BigDecimal.ZERO;

	    private String note;

	    @ManyToOne
	    @JoinColumn(name = "daily_id", nullable = false)
	    @JsonIgnore
	    private LedgerDaily daily;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public BigDecimal getPayment() {
			return payment;
		}

		public void setPayment(BigDecimal payment) {
			this.payment = payment;
		}

		public BigDecimal getCharges() {
			return charges;
		}

		public void setCharges(BigDecimal charges) {
			this.charges = charges;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public LedgerDaily getDaily() {
			return daily;
		}

		public void setDaily(LedgerDaily daily) {
			this.daily = daily;
		}

		@Override
		public String toString() {
			return "PaymentEntry [id=" + id + ", payment=" + payment + ", charges=" + charges + "]";
		}
	    
	    
	    
}
