package com.fusionhawk.model.req;
//abhik
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
public class SaveViewReq {
	
	
	
	private String user;
	
	private String viewName;

	private String cpg;
	
	private String plant;

	private String sku;
	
	private String weeklyFinalForecast;
	
	private String startWeek;
	
	private String endWeek;

}
