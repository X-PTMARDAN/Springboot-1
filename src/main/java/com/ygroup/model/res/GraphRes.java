package com.ygroup.model.res;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ygroup.model.req.DemandTableReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class GraphRes {
	
	private DemandTableReq req;
	
	private List<DemandTableRes> res;
	
	
	private List<DemandTableRes> res_table;
	
	
	public List<featureAnalysisRes> secondGraphRes;
	
	private int start;
	
	
	private List<UserCommentsRes> allComments;
	
	
	
	private List<String> combinedcomment;


	



}
