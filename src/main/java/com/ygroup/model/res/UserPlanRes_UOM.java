package com.ygroup.model.res;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlanRes_UOM {
	
	@Id
	private int calendarWeek;
	
	private String fva;

	private String finalForecast;
	
	private String forecast;

}
