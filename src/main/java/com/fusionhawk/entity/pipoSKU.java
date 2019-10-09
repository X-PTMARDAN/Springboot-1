package com.fusionhawk.entity;
import javax.persistence.Column;
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
@Table(name = "pipo_sku")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class pipoSKU {
	
	@Id
	@GeneratedValue
    private String sku_from;
	
	private String sku_to;

	
	private String reason;
	
	private String week;
	

}
