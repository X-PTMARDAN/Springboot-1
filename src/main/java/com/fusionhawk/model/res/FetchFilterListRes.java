package com.fusionhawk.model.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//abhik
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchFilterListRes {

	private String Name;
	
	private List<String> sku;
	
	private List<String> cpg;
	
	private List<String> plant;
	
	private String default_Val;
	
	
}
