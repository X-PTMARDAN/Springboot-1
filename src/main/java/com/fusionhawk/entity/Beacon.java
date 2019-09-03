package com.fusionhawk.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "beaconUpdated")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Beacon {
	
	// Primary key - calender_yearweek, calendar_yearmonth, plant, customer_planning_group, lead_sku
	
	@EmbeddedId
	private BeaconPK id;
	
	@Column(name = "total_sales_volume")
	private String actuals;
	
	@Column(name = "apo_calculated_sales_estimate")
	private String apo;
	
	@Column(name = "predictions")
	private String ml;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "pack_type_name")
	private String packaging;

}
