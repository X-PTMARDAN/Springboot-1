package com.fusionhawk.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fusionhawk.entity.SavePlanEntity;

public interface SavePlanRepository extends CrudRepository<SavePlanEntity, String> {
	
	String fetchMLSavePlanTableQuery = "select * from plan_data where Pk_Combination in (:key)";

	String fetchDataToConfirm = "select * from plan_data where Temp_Value=1 and user= :user";
	
	//String updateTempValue = "update suvid_plan set Final_Forecast = Final_Forecast_Temp,Temp_Value = 0 where Temp_Value=1 and user= (:user)";
	//abhik
	@Query(value = fetchMLSavePlanTableQuery, nativeQuery = true)
	List<SavePlanEntity> fetchSavePlanTableByKey(@Param("key") List<String> key);
	
	@Query(value = fetchDataToConfirm, nativeQuery = true)
	List<SavePlanEntity> fetchDataToConfirm(@Param("user") String user);
	
	/*
	 * @Query(value = updateTempValue, nativeQuery = true) void
	 * updateTempValue(@Param("user") String user);
	 */
}