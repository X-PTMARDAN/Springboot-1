package com.fusionhawk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fusionhawk.model.res.UserPlanRes;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlanRes, String> {
	
	String fetchUserPlanQuery = "SELECT Calendar_Week, SUM(fva) AS fva, SUM(Final_Forecast) AS Final_Forecast FROM plan_data\n" + 
			"WHERE cpg IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND Calendar_Week BETWEEN :startWeek AND :endWeek \n" + 
			"AND Sku IN (SELECT DISTINCT(Name) From TABLE_NAME where ForecastingGroup IN (:forecastingGroupList)) GROUP BY Calendar_Week";
	
	
	
	
	
	@Query(value = fetchUserPlanQuery, nativeQuery = true)
	List<UserPlanRes> fetchUserPlanByWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);
	
}
