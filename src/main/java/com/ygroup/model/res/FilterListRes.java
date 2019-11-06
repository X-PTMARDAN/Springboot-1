package com.ygroup.model.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterListRes {

	private List<Filter> filters;
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Filter {
		
		private String name;
		
		private String key;
		
		private List<String> values;
		
	}

}
