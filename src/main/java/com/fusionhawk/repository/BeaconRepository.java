package com.fusionhawk.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fusionhawk.model.res.DemandTableRes;
import com.fusionhawk.model.res.DemandTableResponse_Updated;
import com.fusionhawk.model.res.featureAnalysisRes;

@Repository
public interface BeaconRepository extends JpaRepository<DemandTableRes, String> {

	String fetchDemandTableQuery = "SELECT SUM(apo_calculated_sales_estimate) as apo, calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(total_sales_volume) as actuals\n" + 
			"FROM TABLE_NAME \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from TABLE_NAME where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	String fetchFeatureTable1 = "SELECT RAND(6)  as apo, calendar_yearweek + :x AS week \n" + 
			"FROM TABLE_NAME \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from TABLE_NAME where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	String fetchDemandTableQuery_Month = "SELECT SUM(apo_calculated_sales_estimate) as apo, calendar_yearmonth + :x AS week, SUM(predictions) as ml, SUM(total_sales_volume) as actuals\n" + 
			"FROM TABLE_NAME \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from TABLE_NAME where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchDemandTableQuery_Updated = "SELECT SUM(apo_calculated_sales_estimate) as apo, calendar_yearweek + :x AS week, SUM(predictions) as ml\n" + 
			"FROM TABLE_NAME \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from TABLE_NAME where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"GROUP BY calendar_yearweek";
	
	
	
	
	
	String fetchDemandTableByFG = "SELECT DISTINCT(Name) from TABLE_NAME where ForecastingGroup IN (:key)";
	//jatin
	//Fetch ForeCast On the basis of different filters
		String fetchForeCastOnFilter = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND (Brand REGEXP :regexp AND (Sub_Brand REGEXP (:subBrand) "
				+ " REGEXP Alcohol_Percentage IN (:alcoholPerc) REGEXP UnitPerPack IN (:unitPerPack))";
		
		
	String fetchForecastingGroups_Updated_string = "SELECT DISTINCT(Name) from TABLE_NAME (:mainQuery)";
		
	
		
	// Fetch brands
	@Query(value = "SELECT DISTINCT(Brand) FROM TABLE_NAME WHERE Brand!=''", nativeQuery = true)
	List<String> fetchBrands();
	
	
	@Query(value = "SELECT DISTINCT(Brand) FROM TABLE_NAME WHERE Brand!='' AND ForecastingGroup IN (:forecastingGroupList)", nativeQuery = true)
	List<String> fetchBrands_filters(@Param("forecastingGroupList") List<String> forecastingGroupList);

	// Fetch plants
	@Query(value = "SELECT DISTINCT(plant) FROM TABLE_NAME WHERE plant!=''", nativeQuery = true)
	List<String> fetchPlants();
	
	
	@Query(value = "SELECT DISTINCT(unitPerPack) As unitPerPack FROM TABLE_NAME WHERE unitPerPack!=''", nativeQuery = true)
	List<String> fetchunitPerPack();
	
	
	
	@Query(value = "SELECT DISTINCT(Alcohol_percentage) As Alcohol_percentage FROM TABLE_NAME WHERE Alcohol_percentage!=''", nativeQuery = true)
	List<String> fetchalcoholpercentage();
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(Sub_Brand) As Sub_Brand FROM TABLE_NAME WHERE Sub_Brand!='' LIMIT 6", nativeQuery = true)
	List<String> fetchsubbrand();
	
	

	// Fetch CPGs
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM TABLE_NAME WHERE customer_planning_group!=''", nativeQuery = true)
	List<String> fetchCPGs();

	// Fetch Forecasting Groups
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!=''", nativeQuery = true)
	List<String> fetchForecastingGroups();

	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :regexp", nativeQuery = true)
	List<String> fetchForecastingGroupsByBrands(@Param("regexp") String regexp);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Alcohol_Percentage REGEXP :regexp", nativeQuery = true)
	List<String> fetchForecastingGroups_d(@Param("regexp") String regexp);
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :regexp", nativeQuery = true)
	List<String> fetchForecastingGroups_a(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp", nativeQuery = true)
	List<String> fetchForecastingGroups_b(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp", nativeQuery = true)
	List<String> fetchForecastingGroups_c(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1 ", nativeQuery = true)
	List<String> fetchForecastingGroups_cd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1 ", nativeQuery = true)
	List<String> fetchForecastingGroups_bd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1 ", nativeQuery = true)
	List<String> fetchForecastingGroups_bc(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1 ", nativeQuery = true)
	List<String> fetchForecastingGroups_ac(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1 ", nativeQuery = true)
	List<String> fetchForecastingGroups_ad(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Sub_Brand REGEXP :regexp1 ", nativeQuery = true)
	List<String> fetchForecastingGroups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2 ", nativeQuery = true)
	List<String> fetchForecastingGroups_bcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2 ", nativeQuery = true)
	List<String> fetchForecastingGroups_acd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2 ", nativeQuery = true)
	List<String> fetchForecastingGroups_abd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	

	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2 ", nativeQuery = true)
	List<String> fetchForecastingGroups_abc(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);

	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM TABLE_NAME WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2 AND Alcohol_Percentage REGEXP :subbrand3", nativeQuery = true)
	List<String> fetchForecastingGroups_abcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2,@Param("subbrand3") String subbrand3);

	
	
	
	
	
	
	
	@Query(value = fetchForecastingGroups_Updated_string, nativeQuery = true)
	List<String> fetchForecastingGroups_Updated(@Param("mainQuery") String mainQuery);
	
	
	
	

	// Fetch filters lists
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM TABLE_NAME WHERE customer_planning_group!='' ORDER BY customer_planning_group", nativeQuery = true)
	List<String> fetchFilterListCPG();
 
	@Query(value = "SELECT DISTINCT(plant) FROM TABLE_NAME WHERE plant!='' ORDER BY plant;", nativeQuery = true)
	List<String> fetchFilterListPlants();
	


	@Query(value = "SELECT DISTINCT(pack_type_name) FROM TABLE_NAME WHERE pack_type_name!=''", nativeQuery = true)
	List<String> fetchPackTypeName();
	
	
	
	
	@Query(value = "SELECT DISTINCT(pack_type_name) FROM TABLE_NAME WHERE pack_type_name!=''", nativeQuery = true)
	List<String> fetchFilterListPackaging();
	
	

	
	
	// ye le yahi krde paste 
	
//	// Jatin
//	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
//	List<String> fetchFilterListPackaging(@Param("subBrand") List<String> subBrand , @Param("alcoholPerc") List<String> alcoholPerc 
//			, @Param("unitPerPack") List<String> unitPerPack , @Param("brand") List<String> brands);
//	
	// Fetch graphs
	@Query(value = "SELECT DISTINCT(lead_sku_name) FROM TABLE_NAME WHERE lead_sku_name!=''", nativeQuery = true)
	List<String> fetchDemandTable();

	@Query(value = fetchDemandTableQuery, nativeQuery = true)
	List<DemandTableRes> fetchDemandTableByWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	

	@Query(value = fetchFeatureTable1, nativeQuery = true)
	List<featureAnalysisRes> fetchFeatureTable2(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	
	
	@Query(value = fetchDemandTableQuery_Month, nativeQuery = true)
	List<DemandTableRes> fetchDemandTableByMonths(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	@Query(value = fetchDemandTableQuery_Updated, nativeQuery = true)
	List<DemandTableResponse_Updated> fetchDemandTableByWeeks_updated(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	//abhik
	@Query(value = fetchDemandTableByFG, nativeQuery = true)
	List<String> fetchDemandTableByFG(@Param("key") List<String> key);
	
	
	// Jatin aise comment krke paste kriyo bhai 
	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
	List<String> fetchForeCastOnFilter(@Param("regexp") String regexp, @Param("subBrand") String subBrand , @Param("alcoholPerc") String alcoholPerc, @Param("unitPerPack") String unitPerPack);


	
	
}
