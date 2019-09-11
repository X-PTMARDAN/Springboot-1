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


//Its the entity(FORMAT IN WHICH DATA IS FETCHED FROM DATABASE) for the Filters, thats stored as the preferred filters(CPG, Plants, Forecasting Group) for a particular user


@Entity
@Table(name = "Filter_Data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class FilterEntity {
	
	@Id
	@GeneratedValue
    private Long id;
	
	private String user;
	
	private String filterName;
	
	private String cpg;
	
	private String plant;
	
	private String sku;

}
