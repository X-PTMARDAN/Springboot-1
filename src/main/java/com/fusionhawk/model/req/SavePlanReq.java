package com.fusionhawk.model.req;




// FORMAT OF SAVE PLAN REQUEST DATA
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavePlanReq {
	
	
	private String pk_combination;

	private List<String> Sku;

	private List<String> cpg;
	
	private List<String> plant;

	private int calendarWeek;
	
	private int fva;
	
	private int finalForecast;
	
	private String user;
	
	private String comments1;

	private String comments2;
	
	private boolean isTemp;
	
	private int finalForecastTemp;
	
	public List<String> getSku() {
		return Sku;
	}

	public void setSku(List<String> sku) {
		Sku = sku;
	}

	public List<String> getCpg() {
		return cpg;
	}

	public void setCpg(List<String> cpg) {
		this.cpg = cpg;
	}

	public List<String> getPlant() {
		return plant;
	}

	public void setPlant(List<String> plant) {
		this.plant = plant;
	}

	public int getCalendarWeek() {
		return calendarWeek;
	}

	public void setCalendarWeek(int calendarWeek) {
		this.calendarWeek = calendarWeek;
	}

	public int getFva() {
		return fva;
	}

	public void setFva(int fva) {
		this.fva = fva;
	}


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getComments1() {
		return comments1;
	}

	public void setComments1(String comments1) {
		this.comments1 = comments1;
	}

	public String getComments2() {
		return comments2;
	}
	
	public void setComments2(String comments2) {
		this.comments2 = comments2;
	}
	
	public String getPk_combination() {
		return pk_combination;
	}

	public void setPk_combination(String pk_combination) {
		this.pk_combination = pk_combination;
	}

	public int getFinalForecast() {
		return finalForecast;
	}

	public void setFinalForecast(int finalForecast) {
		this.finalForecast = finalForecast;
	}

	public boolean isTemp() {
		return isTemp;
	}

	public void setTemp(boolean isTemp) {
		this.isTemp = isTemp;
	}

	public int getFinalForecastTemp() {
		return finalForecastTemp;
	}

	public void setFinalForecastTemp(int finalForecastTemp) {
		this.finalForecastTemp = finalForecastTemp;
	}

}
