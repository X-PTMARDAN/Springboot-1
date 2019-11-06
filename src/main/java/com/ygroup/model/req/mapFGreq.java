package com.ygroup.model.req;



//FORMAT OF FETCHING AND SAVING DATA(FOR FAVOURITE FILTERS) REQUEST'S DATA


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
public class mapFGreq {

	private String fg;
	
	private String material;
	
	
	//private List<String> filterBrands;
	
}
