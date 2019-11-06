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
@Table(name = "pipo_mapping_updated")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class PIPOMapping {
	
	@Id
    private int fromid;
	
	private String state;
    private int toid;
    
    private int fromweek;
    
    
    private String fgid;
    
	
	

}
