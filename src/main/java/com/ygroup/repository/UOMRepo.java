package com.ygroup.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ygroup.model.res.AuroriPrevMonth_UOM;
import com.ygroup.model.res.AuroriPrevMonths;
import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.DemandTableResponse_Updated;
import com.ygroup.model.res.LogResponse;
import com.ygroup.model.res.UOMResponse;
import com.ygroup.model.res.featureAnalysisRes;

@Repository
public interface UOMRepo extends JpaRepository<UOMResponse, String> {
	
	String fetchDemandPO_UOM="SELECT material as forecasting, SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals  FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group IN (:cpgList) AND plant IN (:plantList) AND calendar_yearweek BETWEEN :startWeek AND :endWeek AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList)) AND predictions IS NOT NULL GROUP BY material,calendar_yearweek";

	
	String fetchDemandPO_UOM1="SELECT SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals  FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group IN (:cpgList) AND plant IN (:plantList) AND calendar_yearweek BETWEEN :startWeek AND :endWeek AND Name IN (:forecastingGroupList) AND predictions IS NOT NULL GROUP BY calendar_yearweek";

	
	@Query(value = fetchDemandPO_UOM, nativeQuery = true)
	List<UOMResponse> fetchDemandTableByWeeks_UOM(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	

	
	
	
	String fetchDemandTablePrevWeeksQuery = "SELECT material as forecasting calendar_yearweek + :x AS week, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			"  \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in (:forecastingGroupList))\n" + 
			"GROUP BY material,calendar_yearweek";
	
	
	
	
	
	
	String fetchDemandTableQuery = "SELECT material as forecasting, SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY material,calendar_yearweek";
	
	
		
	
	
	String fetchFeatureTable_featureAnalysis = "SELECT material as forecasting, RAND(6)  as property, calendar_yearweek + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY material,calendar_yearweek";
	
	
	
	
	String fetchDemandTableQuery_Month = "SELECT SUM(apo_calculated_sales_estimate) as apo, calendar_yearmonth + :x AS week, SUM(predictions) as ml,SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchDemandTableQuery_Updated = "SELECT SUM(apo_calculated_sales_estimate) as apo, SUM(open_orders) as open1, calendar_yearweek + :x AS week, SUM(predictions) as ml\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	String fetchDemandTableByFG = "SELECT DISTINCT(Name) from AGGREGATED_TABLE_UPDATED where ForecastingGroup IN (:key)";
	//jatin
	//Fetch ForeCast On the basis of different filters
		String fetchForeCastOnFilter = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND (Brand REGEXP :regexp AND (Sub_Brand REGEXP (:subBrand) "
				+ " REGEXP Alcohol_Percentage IN (:alcoholPerc) REGEXP UnitPerPack IN (:unitPerPack))";
		
		
	String fetchForecastingGroups_Updated_string = "SELECT DISTINCT(Name) from AGGREGATED_TABLE_UPDATED (:mainQuery)";
		
	
		
	// Fetch brands
	@Query(value = "SELECT DISTINCT(Brand) FROM AGGREGATED_TABLE_UPDATED WHERE Brand!='' LIMIT 6", nativeQuery = true)
	List<String> fetchBrands();
	
	
	
	@Query(value = fetchDemandTablePrevWeeksQuery, nativeQuery = true)
	List<AuroriPrevMonth_UOM> fetchDemandTablePrevWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	@Query(value = "SELECT DISTINCT(Brand) FROM AGGREGATED_TABLE_UPDATED WHERE Brand!='' AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList) LIMIT 6", nativeQuery = true)
	List<String> fetchBrands_filters(@Param("forecastingGroupList") List<String> forecastingGroupList);

	// Fetch plants
	@Query(value = "SELECT DISTINCT(plant) FROM AGGREGATED_TABLE_UPDATED WHERE plant!='' LIMIT 6", nativeQuery = true)
	List<String> fetchPlants();
	
	
	@Query(value = "SELECT DISTINCT(unitPerPack) As unitPerPack FROM AGGREGATED_TABLE_UPDATED WHERE unitPerPack!='' LIMIT 6", nativeQuery = true)
	List<String> fetchunitPerPack();
	
	
	
	@Query(value = "SELECT DISTINCT(Alcohol_percentage) As Alcohol_percentage FROM AGGREGATED_TABLE_UPDATED WHERE Alcohol_percentage!='' ORDER BY Alcohol_percentage ASC LIMIT 6", nativeQuery = true)
	List<String> fetchalcoholpercentage();
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) As customer_planning_group FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!=''  LIMIT 6", nativeQuery = true)
	List<String> cpg_groups();
	
	
	
	


	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' AND sales_office REGEXP :regexp AND trade_type REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> cpg_groups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' AND sales_office REGEXP :regexp  LIMIT 6", nativeQuery = true)
	List<String> cpg_groups_a(@Param("regexp") String regexp);
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' AND trade_type REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> cpg_groups_b(@Param("regexp1") String regexp1);
	
	
	
	@Query(value = "SELECT DISTINCT(Sub_Brand) As Sub_Brand FROM AGGREGATED_TABLE_UPDATED WHERE Sub_Brand!='' LIMIT 6", nativeQuery = true)
	List<String> fetchsubbrand();
	
	
	
	@Query(value = "SELECT DISTINCT(sales_office) As Sales FROM AGGREGATED_TABLE_UPDATED WHERE sales_office!='' LIMIT 6", nativeQuery = true)
	List<String> fetchsales();
	
	
	
	
	@Query(value = "SELECT DISTINCT(trade_type) As Trade FROM AGGREGATED_TABLE_UPDATED WHERE trade_type!='' LIMIT 6", nativeQuery = true)
	List<String> fetchtrade();
	
	
	// New
	
	
	@Query(value = "SELECT DISTINCT(global_bev_cat) As GlobalBev FROM AGGREGATED_TABLE_UPDATED WHERE global_bev_cat!='' LIMIT 6", nativeQuery = true)
	List<String> fetch_global_bev_cat();
	
	
	@Query(value = "SELECT DISTINCT(materialgroup) As materialgroup FROM AGGREGATED_TABLE_UPDATED WHERE materialgroup!='' LIMIT 6", nativeQuery = true)
	List<String> fetchmaterial();
	
	
	@Query(value = "SELECT DISTINCT(base_unit_of_measure_characteristic) As baseunit FROM AGGREGATED_TABLE_UPDATED WHERE base_unit_of_measure_characteristic!='' LIMIT 6", nativeQuery = true)
	List<String> fetch_base();
	
	

	@Query(value = "SELECT DISTINCT(pack_type) As pack_type FROM AGGREGATED_TABLE_UPDATED WHERE pack_type!='' LIMIT 6", nativeQuery = true)
	List<String> fetch_packtype();
	
	
	
	
	@Query(value = "SELECT DISTINCT(pack_size) As pack_size FROM AGGREGATED_TABLE_UPDATED WHERE pack_size!='' LIMIT 6", nativeQuery = true)
	List<String> fetch_packsize();
	
	
	
	
	@Query(value = "SELECT DISTINCT(Animal_Flags) As Animal_Flags FROM AGGREGATED_TABLE_UPDATED WHERE Animal_Flags!='' LIMIT 6", nativeQuery = true)
	List<String> fetchanimal();
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_group_planning_name) As cpgname FROM AGGREGATED_TABLE_UPDATED WHERE customer_group_planning_name!='' LIMIT 6", nativeQuery = true)
	List<String> fetchcpgname();
	
	
	// END
	
	
	
	
	
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(trade_type) As Trade FROM AGGREGATED_TABLE_UPDATED WHERE trade_type!='' LIMIT 6", nativeQuery = true)
	List<String> fetchtradetype();
	
	
	@Query(value = "SELECT DISTINCT(sales_office) As Sales FROM AGGREGATED_TABLE_UPDATED WHERE sales_office!='' LIMIT 6", nativeQuery = true)
	List<String> fetchsalesoffice();
	
	

	// Fetch CPGs
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' LIMIT 6", nativeQuery = true)
	List<String> fetchCPGs();

	// Fetch Forecasting Groups
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups();

	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroupsByBrands(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "Select calendar_yearmonth  from AGGREGATED_TABLE_UPDATED where customer_planning_group = :cpg AND plant = :plant AND calendar_yearweek = :week AND Name = :name", nativeQuery = true)
	int fetchcalendarMonth(@Param("cpg") String cpg,@Param("plant") String regexp,@Param("week") int week,@Param("name") String name);
	
	
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Alcohol_Percentage REGEXP :regexp LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_d(@Param("regexp") String regexp);
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_a(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_b(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_c(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_cd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_bd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_bc(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_ac(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_ad(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Sub_Brand REGEXP :regexp1  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_bcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_acd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_abd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	

	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2 LIMIT 6 ", nativeQuery = true)
	List<String> fetchForecastingGroups_abc(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);

	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2 AND Alcohol_Percentage REGEXP :subbrand3 LIMIT 6", nativeQuery = true)
	List<String> fetchForecastingGroups_abcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2,@Param("subbrand3") String subbrand3);

	
	
	
	
	
	
	
	@Query(value = fetchForecastingGroups_Updated_string, nativeQuery = true)
	List<String> fetchForecastingGroups_Updated(@Param("mainQuery") String mainQuery);
	
	
	
	

	// Fetch filters lists
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' ORDER BY customer_planning_group", nativeQuery = true)
	List<String> fetchFilterListCPG();
 
	@Query(value = "SELECT DISTINCT(plant) FROM AGGREGATED_TABLE_UPDATED WHERE plant!='' ORDER BY plant;", nativeQuery = true)
	List<String> fetchFilterListPlants();
	


	@Query(value = "SELECT DISTINCT(pack_type_name) FROM AGGREGATED_TABLE_UPDATED WHERE pack_type_name!=''", nativeQuery = true)
	List<String> fetchPackTypeName();
	
	
	
	
	@Query(value = "SELECT DISTINCT(pack_type_name) FROM AGGREGATED_TABLE_UPDATED WHERE pack_type_name!=''", nativeQuery = true)
	List<String> fetchFilterListPackaging();
	
	

	
//	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
//	List<String> fetchFilterListPackaging(@Param("subBrand") List<String> subBrand , @Param("alcoholPerc") List<String> alcoholPerc 
//			, @Param("unitPerPack") List<String> unitPerPack , @Param("brand") List<String> brands);
//	
	// Fetch graphs
	@Query(value = "SELECT DISTINCT(Name) FROM AGGREGATED_TABLE_UPDATED WHERE material!=''", nativeQuery = true)
	List<String> fetchDemandTable();

	@Query(value = fetchDemandTableQuery, nativeQuery = true)
	List<UOMResponse> fetchDemandTableByWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
//	@Query(value = fetchDemandPO_UOM, nativeQuery = true)
//	List<UOMResponse> fetchDemandTableByWeeks_UOM(@Param("forecastingGroupList") List<String> forecastingGroupList,
//			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
//			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	

	
	

	@Query(value = fetchFeatureTable_featureAnalysis, nativeQuery = true)
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
	

	@Query(value = fetchDemandTableByFG, nativeQuery = true)
	List<String> fetchDemandTableByFG(@Param("key") List<String> key);
	
	

	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
	List<String> fetchForeCastOnFilter(@Param("regexp") String regexp, @Param("subBrand") String subBrand , @Param("alcoholPerc") String alcoholPerc, @Param("unitPerPack") String unitPerPack);

	
	
	
	@Query(value = "SELECT Username,activity,datetimestamp FROM UserLog WHERE Username='admin'", nativeQuery = true)
     List<LogResponse> fetchlogs();
	
	
	 @Modifying
	 @Transactional
	@Query(value = "INSERT INTO UserLog VALUES(:Username,:activity,:datetime)", nativeQuery = true)
		void savelogs(@Param("Username") String username,@Param("activity") String activity,@Param("datetime") String datetime);
	
}
