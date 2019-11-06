package com.ygroup.model.res;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Final_TABLE")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UOMResponse {
	


	
//	@Column(name="Yha isse database mapped column name dalna h")

	private String apo;
	
	
	@Id
	@Column(name = "week")
	private int calenderYearWeek;
	

	private String ml;

	
	private String harshit;
	
	private String actuals;
	
	
	private String forecasting;
	

	
	
}
