package com.ygroup.entity;
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
@Table(name = "UserLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class SaveLogEntity {
	
	
	@Id
    private String Username;
	
	private String activity;
	
	private String datetimestamp;


}
