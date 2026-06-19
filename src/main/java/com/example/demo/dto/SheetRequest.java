package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SheetRequest {
	
	 private Long lineId;
	    private int month;
	    private int year;
	    private BigDecimal openingBf;
	    private LocalDate startDate;
	    
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public Long getLineId() {
			return lineId;
		}
		public void setLineId(Long lineId) {
			this.lineId = lineId;
		}
		public int getMonth() {
			return month;
		}
		public void setMonth(int month) {
			this.month = month;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		public BigDecimal getOpeningBf() {
			return openingBf;
		}
		public void setOpeningBf(BigDecimal openingBf) {
			this.openingBf = openingBf;
		}
	    
	    
}
