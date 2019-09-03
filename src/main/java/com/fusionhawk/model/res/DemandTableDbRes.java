package com.fusionhawk.model.res;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "beaconUpdated")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandTableDbRes {
	
	@Id
	@Column(name = "week")
	private int calenderYearWeek;
	
	private double actuals;

	private double apo;
	
	private double ml;
	
}
