package com.ygroup.entity;
//abhik
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




//Its the entity(FORMAT IN WHICH DATA IS FETCHED FROM DATABASE) for Saved Plan for a particular user, indexed by pk_combination thats made by user+week+cpg+plant+sku



@Entity
@Table(name = "Plan_Data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class SavePlanEntity {
	
	@Id
	private String pk_combination;

	private int sku;
	
	private int id;
	
	
	private double ml;

	private String cpg;
	
	@Column(name = "calendar_yearmonth")
	private int calendar_yearMonth;
	
	private String plant;

	private int calendarWeek;
	
	private double fva;
	
	private double finalForecast;
	
	private String user;
	
	private String forecasting;
	
	private String comments1;

	private String comments2;
	
	private boolean tempValue;
	
	private double finalForecastTemp;
	
	/*
	 * public int getCalendarWeek() { return calendarWeek; }
	 * 
	 * public void setCalendarWeek(int calendarWeek) { this.calendarWeek =
	 * calendarWeek; }
	 * 
	 * public double getFva() { return fva; }
	 * 
	 * public void setFva(double fva) { this.fva = fva; }
	 * 
	 * 
	 * public String getUser() { return user; }
	 * 
	 * public void setUser(String user) { this.user = user; }
	 * 
	 * public String getComments1() { return comments1; }
	 * 
	 * public void setComments1(String comments1) { this.comments1 = comments1; }
	 * 
	 * public String getComments2() { return comments2; }
	 * 
	 * public void setComments2(String comments2) { this.comments2 = comments2; }
	 * 
	 * public String getPk_combination() { return pk_combination; }
	 * 
	 * public void setPk_combination(String pk_combination) { this.pk_combination =
	 * pk_combination; }
	 * 
	 * public double getFinalForecast() { return finalForecast; }
	 * 
	 * public void setFinalForecast(double finalForecast) { this.finalForecast =
	 * finalForecast; }
	 * 
	 * public boolean isTempValue() { return tempValue; }
	 * 
	 * public void setTempValue(boolean tempValue) { this.tempValue = tempValue; }
	 * 
	 * public double getFinalForecastTemp() { return finalForecastTemp; }
	 * 
	 * public void setFinalForecastTemp(double finalForecastTemp) {
	 * this.finalForecastTemp = finalForecastTemp; }
	 * 
	 * public String getSku() { return Sku; }
	 * 
	 * public void setSku(String sku) { Sku = sku; }
	 * 
	 * public String getCpg() { return cpg; }
	 * 
	 * public void setCpg(String cpg) { this.cpg = cpg; }
	 * 
	 * public String getPlant() { return plant; }
	 * 
	 * public void setPlant(String plant) { this.plant = plant; }
	 */
}
