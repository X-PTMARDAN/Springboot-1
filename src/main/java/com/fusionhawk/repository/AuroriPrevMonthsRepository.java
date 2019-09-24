package com.fusionhawk.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fusionhawk.model.res.AuroriPrevMonths;



@Repository
public interface AuroriPrevMonthsRepository extends JpaRepository<AuroriPrevMonths, String> {
	
	String fetchDemandTablePrevWeeksQuery = "SELECT calendar_yearweek + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM Final_TABLE \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from Aurora where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	String fetchDemandTablePrevWeeksQuery_monthly = "SELECT calendar_yearmonth + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM Final_TABLE \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from Aurora where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"GROUP BY calendar_yearmonth";
	
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery_monthly, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevMonthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);

}
