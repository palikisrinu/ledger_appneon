package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "ledger_sheets",
       uniqueConstraints = @UniqueConstraint(columnNames = {"line_id", "month", "year"}))
public class LedgerSheet {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int month;
	    private int year;
	    private LocalDate startDate;
	    
	    
	    public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		@Column(name = "opening_bf", nullable = false)
	    private BigDecimal openingBf;
		
		
	    @ManyToOne
	    @JoinColumn(name = "line_id", nullable = false)
	  
	    private Line line;

	    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private List<LedgerDaily> dailyEntries;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Line getLine() {
			return line;
		}

		public void setLine(Line line) {
			this.line = line;
		}

		public List<LedgerDaily> getDailyEntries() {
			return dailyEntries;
		}

		public void setDailyEntries(List<LedgerDaily> dailyEntries) {
			this.dailyEntries = dailyEntries;
		}

	
	    	    
}
