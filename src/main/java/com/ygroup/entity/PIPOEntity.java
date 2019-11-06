package com.ygroup.entity;
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
@Table(name = "pipo_final")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class PIPOEntity {
	
	@Id
	@GeneratedValue
	private String material;
	
	private String Sku;
	
	@Column(name = "Forecastinggroup")
	private String Forecastinggroup;
	
	private String FGID;
	
	private String minimum;
	
	private String maximum;
	
	private String prime;
	
	

}
