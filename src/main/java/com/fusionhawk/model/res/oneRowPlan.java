package com.fusionhawk.model.res;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TABLE_NAME")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class oneRowPlan {

	@Id
	@Column(name = "Calendar_Week")
	private int calenderYearWeek;
	
//	@Column(name="Yha isse database mapped column name dalna h")

	private String Sku;
	
	private String cpg;
	
	private String plant;
	
	@Column(name = "fva")
	private String fva;
	
	
	@Column(name = "Final_Forecast")
	private String Final_Forecast;
	
	
	private double ml;
	
	private int id;
	
	
	
}
