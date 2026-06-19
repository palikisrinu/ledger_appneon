package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lines")
public class Line {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "line_name", nullable = false, unique = true)
	    private String lineName;

	    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
	    @JsonIgnore
	    private List<LedgerSheet> sheets;
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getLineName() {
			return lineName;
		}

		public void setLineName(String lineName) {
			this.lineName = lineName;
		}

		public List<LedgerSheet> getSheets() {
			return sheets;
		}

		public void setSheets(List<LedgerSheet> sheets) {
			this.sheets = sheets;
		}

		@Override
		public String toString() {
			return "Line [id=" + id + ", lineName=" + lineName + "]";
		}	
		
		
}
