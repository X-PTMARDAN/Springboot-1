package com.fusionhawk.model.res;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class mappingResp {
	
	@Id
	@Column(name = "materialdesc")
	public String materialdesc;
	
	
	public String hl;
	
	public String pc;



}
