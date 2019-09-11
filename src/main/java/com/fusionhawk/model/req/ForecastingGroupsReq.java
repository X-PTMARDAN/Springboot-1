package com.fusionhawk.model.req;


//FORMAT OF FETCHING DATA(FOR DIFFERENT FORECASTING GROUP) REQUEST'S DATA



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
public class ForecastingGroupsReq {

	private List<String> filterBrands;
	
	//jatin
	private List<String> filterSubBrandName;
	private List<String> filterAcoholPerc;
	private List<String> filterUnitsPerPack;

}
