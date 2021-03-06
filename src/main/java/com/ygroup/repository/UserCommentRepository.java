package com.ygroup.repository;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ygroup.model.res.UserCommentsRes;

public interface UserCommentRepository extends JpaRepository<UserCommentsRes, String> {
	
	//String fetchUserCommentsQuery = "SELECT Calendar_Week, CONCAT(comments1,\"|\",Sku,plant,cpg) As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (SELECT DISTINCT(Name) from AGGREGATED_TABLE_UPDATED where ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList)))) AND comments1!='comment1'" ;

	String fetchUserCommentsQuery = "SELECT Calendar_Week, CONCAT(comments1,\"|\",forecasting,\"|\",plant,\"|\",cpg,\"|\",Calendar_Week) As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (:forecastingGroupList) AND comments1!='comment1'" ;

	
	
	
	
	
	
	
	String fetchUserCommentsQuery_monthly_1 = "SELECT calendar_yearmonth, CONCAT(comments1,\"|\",forecasting,\"|\",plant,\"|\",cpg,\"|\",calendar_yearmonth) As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (:forecastingGroupList) AND comments1!='comment1'" ;

	//String fetchUserCommentsQuery = "SELECT calendar_yearmonth As Calendar_Week, CONCAT(comments1,\"|\",Calendar_Week,\"|\",forecasting,\"|\",cpg,\"|\",plant) As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (SELECT DISTINCT(material) from AGGREGATED_TABLE_UPDATED where ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList)))) AND comments1!='comment1'" ;

	String fetchUserCommentsQuery_monthly = "SELECT calendar_yearmonth As Calendar_Week, CONCAT(comments1,\"|\",forecasting,\"|\",plant,\"|\",cpg,\"|\",Calendar_Week) As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (:forecastingGroupList) AND comments1!='comment1'" ;

	
	//String fetchUserCommentsQuery_monthly = "SELECT calendar_yearmonth As Calendar_Week, distinct(CONCAT(comments1,\"|\",Calendar_Week,\"|\",forecasting,\"|\",cpg,\"|\",plant)) As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (SELECT DISTINCT(material) from AGGREGATED_TABLE_UPDATED where ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList)))) AND comments1!='comment1'" ;
	
	@Query(value = fetchUserCommentsQuery, nativeQuery = true)
	List<UserCommentsRes> fetchUserComments(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);
	
	
	
	
	@Query(value = fetchUserCommentsQuery_monthly, nativeQuery = true)
	List<UserCommentsRes> fetchUserComments_Monthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);
	
	
	
	

	 @Modifying
	 @Transactional
	@Query(value = "UPDATE plan_data SET comments1 = (:data) where Pk_Combination = (:key) LIMIT 1", nativeQuery = true)
	void editCommentSQL(@Param("data") String data,@Param("key") String key);

}
