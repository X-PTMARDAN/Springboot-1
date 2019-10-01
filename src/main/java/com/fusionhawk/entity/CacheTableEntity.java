package com.fusionhawk.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



// Its the entity(FORMAT IN WHICH DATA IS FETCHED FROM DATABASE) for the cache table, thats stored in the Cache to increase the performance  


@Entity
@Table(name = "CacheTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheTableEntity {
	
	@Id
	@Column(name = "ML_Key")
	private String key;
	
	private double ML_Value;
	
}
