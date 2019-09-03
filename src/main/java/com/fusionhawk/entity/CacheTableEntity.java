package com.fusionhawk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//abhik
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
