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
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	String fetchDemandTablePrevWeeksQuery1 = "SELECT calendar_yearweek + :x AS week, SUM(final_total_sales) as actuals\n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	String fetchDemandTablePrevWeeksQuery_monthly = "SELECT calendar_yearmonth + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearmonth";
	

	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery1, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevWeeks1(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery_monthly, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevMonthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);

}
