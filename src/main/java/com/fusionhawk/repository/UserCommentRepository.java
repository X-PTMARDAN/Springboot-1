package com.fusionhawk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fusionhawk.model.res.UserCommentsRes;

public interface UserCommentRepository extends JpaRepository<UserCommentsRes, String> {
	
	String fetchUserCommentsQuery = "SELECT Calendar_Week, comments1 As comments2 FROM plan_data WHERE cpg IN (:cpgList) AND plant IN (:plantList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND Sku IN (SELECT DISTINCT(Name) from TABLE_NAME where ForecastingGroup IN (:forecastingGroupList)) AND comments1!='comment1'" ;
	
	@Query(value = fetchUserCommentsQuery, nativeQuery = true)
	List<UserCommentsRes> fetchUserComments(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);

}
