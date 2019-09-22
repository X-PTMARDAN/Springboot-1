package com.fusionhawk.model.res;

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
public class DemandTableRes {

	@Id
	@Column(name = "week")
	private int calenderYearWeek;
	
//	@Column(name="Yha isse database mapped column name dalna h")

	private String apo;
	
	
	
	private String actuals;
	
	@Column(name = "harshit")
	private String harshit;
	
	
	
	private String ml;
	
	@Transient
	private String actualslastyear;
	
	@Transient
	private String fva;
	
	@Transient
	private String finalforecast;
	
	@Transient
	private List<String> comment;
	
}
