package com.example.demo.dto;

import java.math.BigDecimal;

public class PaymentDTO {
	
	 private BigDecimal payment;
	    private BigDecimal charges;
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
	    
	    
}
