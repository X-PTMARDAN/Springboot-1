package com.fusionhawk.model.req;



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
public class changedFilter {
	
	
	

	public List<String> salesOffice;
	public List<String> tradeType;
	public List<String> cpgname;
	
	
	public List<String> brands;
	public List<String> alcoholper;
	public List<String> subbrand;
	
	public List<String> animalFlag;
	public List<String> packType;
	public List<String> baseunit;
	
	public List<String> globalbev;
	
	
	public List<String> materialGroup;
	
	
}
