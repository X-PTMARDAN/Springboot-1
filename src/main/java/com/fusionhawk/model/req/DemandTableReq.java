package com.fusionhawk.model.req;




//FORMAT OF FETCHING DATA(FOR POPULATING GRAPH AND TABLE) REQUEST'S DATA




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
public class DemandTableReq {
	
	
	private Integer prevactuals;

	private Integer startWeek;

	private Integer endWeek;

	private List<String> customerPlanningGroup;

	private List<String> plants;

	private List<String> forecastingGroups;
	
	
	private List<String> brands;
	
	private List<String> subbrand;
	
	
	private List<String> alcoholper;
	
	private List<String> Trade;
	
	private List<String> Sales;
	
	
	private List<String> GlobalBev;
	
	
	private List<String> materialgroup;

	
	private List<String> baseunit;
	
	
	private List<String> pack_type;
	
	private List<String> pack_size;
	
	private List<String> cpgname;
	
	
	
	private List<String> Animal_Flags;
	
	

}
