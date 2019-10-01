package com.fusionhawk.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fusionhawk.model.res.AuroriPrevMonth_UOM;
import com.fusionhawk.model.res.AuroriPrevMonths;



@Repository
public interface AuroriPrevMonth_UOM_REPO extends JpaRepository<AuroriPrevMonth_UOM, String> {

	

	
	String fetchDemandTablePrevWeeksQuery = "SELECT lead_sku as forecasting calendar_yearweek + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM FINAL_AURORA_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY lead_sku,calendar_yearweek";
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery, nativeQuery = true)
	List<AuroriPrevMonth_UOM> fetchDemandTablePrevWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
}
