package com.fusionhawk.model.req;

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

	private Integer startWeek;

	private Integer endWeek;

	private List<String> customerPlanningGroup;

	private List<String> plants;

	private List<String> forecastingGroups;
	
	
	private List<String> brands;

}
