package com.ygroup.model.res;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TABLE_NAME")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class DemandTableResponse_Updated {

	@Id
	@Column(name = "week")
	private int calenderYearWeek;
	


	private String apo;
	
	
	

	
	
	
	private String ml;
	private String open1;
	
	@Transient
	private String actuals;
	
	@Transient
	private String actualslastyear;
	
	@Transient
	private String fva;
	
	@Transient
	private String finalforecast;
	
	@Transient
	private List<String> comment;
	
}
