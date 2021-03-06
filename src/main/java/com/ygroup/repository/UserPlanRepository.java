package com.ygroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ygroup.model.res.UserPlanRes;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlanRes, String> {
	
	String fetchUserPlanQuery = "SELECT Calendar_Week, SUM(fva) AS fva, SUM(Final_Forecast) AS Final_Forecast,SUM(lockcell) as lockcell FROM plan_data\n" + 
			"WHERE cpg IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND Calendar_Week BETWEEN :startWeek AND :endWeek \n" + 
			"AND Sku IN (:forecastingGroupList)  GROUP BY Calendar_Week";
	
	
	
	
	String fetchUserPlanQuery_Month = "SELECT calendar_yearmonth As Calendar_Week,SUM(lockcell) as lockcell, SUM(fva) AS fva, SUM(Final_Forecast) AS Final_Forecast FROM plan_data\n" + 
			"WHERE cpg IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND Calendar_Week BETWEEN :startWeek AND :endWeek \n" + 
			"AND Sku IN (:forecastingGroupList) GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	/*
	 * SQL Query for a particular combination for CPG, Plant, startweek, endweek, and Forecasting Group,
	 * It fetch the SUM of Forecast value add and SUM of Final Forecast and all the data is grouped weekly
	 * 
	 * 
	 */
	@Query(value = fetchUserPlanQuery, nativeQuery = true)
	List<UserPlanRes> fetchUserPlanByWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);
	
	
	
	
	
	
	/*
	 * SQL Query for a particular combination for CPG, Plant, startweek, endweek, and Forecasting Group,
	 * It fetch the SUM of Forecast value add and SUM of Final Forecast 
	 * and all the data is grouped monthly
	 * 
	 */
	@Query(value = fetchUserPlanQuery_Month, nativeQuery = true)
	List<UserPlanRes> fetchUserPlanByMonths(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);






	
	
}
