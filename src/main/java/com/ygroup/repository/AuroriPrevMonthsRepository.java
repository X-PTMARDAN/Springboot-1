package com.ygroup.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ygroup.model.res.AuroriPrevMonths;
import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.DemandTableRes_All;



@Repository
public interface AuroriPrevMonthsRepository extends JpaRepository<AuroriPrevMonths, String> {
	
	String fetchDemandTablePrevWeeksQuery = "SELECT calendar_yearweek + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearweek";
	
	
	String fsg5 = "SELECT calendar_yearweek + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	String fetchDemandTablePrevWeeksQuery_all = "SELECT SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	
	
	
	String fetchDemandTablePrevWeeksQuery_all_monthly = "SELECT SUM(apo_calculated_sales_estimate) as apo,  calendar_yearmonth + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchDemandTablePrevWeeksQuery_all_month = "SELECT SUM(apo_calculated_sales_estimate) as apo,  calendar_yearmonth + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	
	
	String fetchDemandTablePrevWeeksQuery1 = "SELECT calendar_yearweek + :x AS week, SUM(final_total_sales) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	
	String fetchDemandTablePrevWeeksQuery1_monthly = "SELECT calendar_yearmonth + :x AS week, SUM(final_total_sales) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	
	
	
	String fetchDemandTablePrevWeeksQuery_monthly = "SELECT calendar_yearmonth + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchDemandTablePrevWeeksQuery_monthly_all = "SELECT calendar_yearweek + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"GROUP BY calendar_yearweek";

	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery_all, nativeQuery = true)
	List<DemandTableRes> fetchDemandTablePrevWeeks_all(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery1, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevWeeks1(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery1_monthly, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevWeeks1_monthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery_monthly, nativeQuery = true)
	List<AuroriPrevMonths> fetchDemandTablePrevMonthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);

}
