package com.fusionhawk.entity;
//abhik
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "View_Data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class ViewEntity {
	
	@Id
	@GeneratedValue
    private Long id;
	
	private String user;
	
	private String viewName;
	
	private String cpg;
	
	private String plant;
	
	private String sku;
	
	private String startWeek;
	
	private String endWeek;
	
	private String weeklyFinalForecast;

}
