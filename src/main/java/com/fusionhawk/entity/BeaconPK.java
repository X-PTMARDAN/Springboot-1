package com.fusionhawk.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BeaconPK implements Serializable {

	private static final long serialVersionUID = 673149013656902511L;

	@Column(name = "calendar_yearmonth")
	private String calendarYearMonth;

	@Column(name = "calendar_yearweek")
	private String calendarYearWeek;

	@Column(name = "customer_planning_group")
	private String cpg;

	@Column(name = "plant")
	private String plant;

	@Column(name = "lead_sku")
	private String leadSku;

}
