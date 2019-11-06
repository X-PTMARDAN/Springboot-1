package com.ygroup.model.req;




// FORMAT OF SAVE PLAN REQUEST DATA
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavePlanReq {
	
	
	private String pk_combination;

	private List<String> Sku;

	private List<String> cpg;
	
	private List<String> plant;

	private int calendarWeek;
	
	private double fva;
	
	private double finalForecast;
	
	private String user;
	
	private String comments1;

	private String comments2;
	
	private boolean isTemp;
	
	private double finalForecastTemp;
	
	
}
