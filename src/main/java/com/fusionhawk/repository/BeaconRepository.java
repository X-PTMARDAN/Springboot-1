package com.fusionhawk.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fusionhawk.model.res.DemandTableRes;
import com.fusionhawk.model.res.DemandTableResponse_Updated;
import com.fusionhawk.model.res.LogResponse;
import com.fusionhawk.model.res.UOMResponse;
import com.fusionhawk.model.res.featureAnalysisRes;

@Repository
public interface BeaconRepository extends JpaRepository<DemandTableRes, String> {

	String fetchDemandTableQuery = "SELECT SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	String fetchDemandTableQuery1 = "SELECT SUM(final_apo) as apo,  calendar_yearweek + :x AS week, SUM(final_pred_pc) as ml, SUM(open_orders) as harshit, SUM(final_total_sales) as actuals\n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"AND final_pred_pc IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	
	String fetchDemandPO_UOM="SELECT ForecastingGroup,SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals  FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group IN (:cpgList) AND plant IN (:plantList) AND calendar_yearweek BETWEEN :startWeek AND :endWeek AND ForecastingGroup IN (:forecastingGroupList) AND predictions IS NOT NULL GROUP BY ForecastingGroup,calendar_yearweek";
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis = "SELECT RAND(6)  as property, RAND(6) as property2, RAND(6) as property3, calendar_yearweek + :x AS week \n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (:forecastingGroupList)\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	String fetchDemandTableQuery_Month = "SELECT SUM(apo_calculated_sales_estimate) as apo, calendar_yearmonth + :x AS week, SUM(predictions) as ml,SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from FINAL_AURORA_UPDATED_CHECK_1_ab where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchDemandTableQuery_Updated = "SELECT SUM(apo_calculated_sales_estimate) as apo, SUM(open_orders) as open1, calendar_yearweek + :x AS week, SUM(predictions) as ml\n" + 
			"FROM FINAL_AURORA_UPDATED_CHECK_1_ab \n" + 
			"WHERE customer_planning_group IN (:cpgList) \n" + 
			"AND plant IN (:plantList) \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND Name IN (SELECT DISTINCT(Name) from FINAL_AURORA_UPDATED_CHECK_1_ab where ForecastingGroup IN (:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	String fetchDemandTableByFG = "SELECT DISTINCT(material) from FINAL_AURORA_UPDATED_CHECK_1_ab where ForecastingGroup IN (:key)";
	//jatin
	//Fetch ForeCast On the basis of different filters
		String fetchForeCastOnFilter = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND (Brand REGEXP :regexp AND (Sub_Brand REGEXP (:subBrand) "
				+ " REGEXP Alcohol_Percentage IN (:alcoholPerc) REGEXP UnitPerPack IN (:unitPerPack))";
		
		
	String fetchForecastingGroups_Updated_string = "SELECT DISTINCT(Name) from FINAL_AURORA_UPDATED_CHECK_1_ab (:mainQuery)";
		
	
		
	// Fetch brands
	@Query(value = "SELECT DISTINCT(Brand) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE Brand!='' ", nativeQuery = true)
	List<String> fetchBrands();
	
	
	@Query(value = "SELECT DISTINCT(Brand) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE Brand!='' AND ForecastingGroup IN (:forecastingGroupList) ", nativeQuery = true)
	List<String> fetchBrands_filters(@Param("forecastingGroupList") List<String> forecastingGroupList);

	// Fetch plants
	@Query(value = "SELECT DISTINCT(plant) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE plant!='' ", nativeQuery = true)
	List<String> fetchPlants();
	
	
	@Query(value = "SELECT DISTINCT(unitPerPack) As unitPerPack FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE unitPerPack!='' ", nativeQuery = true)
	List<String> fetchunitPerPack();
	
	
	
	@Query(value = "SELECT DISTINCT(Alcohol_percentage) As Alcohol_percentage FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE Alcohol_percentage!='' ORDER BY Alcohol_percentage ASC ", nativeQuery = true)
	List<String> fetchalcoholpercentage();
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) As customer_planning_group FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!=''  ", nativeQuery = true)
	List<String> cpg_groups();
	
	
	
	


	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' AND sales_office REGEXP :regexp AND trade_type REGEXP :regexp1  ", nativeQuery = true)
	List<String> cpg_groups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' AND sales_office REGEXP :regexp  ", nativeQuery = true)
	List<String> cpg_groups_a(@Param("regexp") String regexp);
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' AND trade_type REGEXP :regexp1  ", nativeQuery = true)
	List<String> cpg_groups_b(@Param("regexp1") String regexp1);
	
	
	
	@Query(value = "SELECT DISTINCT(Sub_Brand) As Sub_Brand FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE Sub_Brand!='' ", nativeQuery = true)
	List<String> fetchsubbrand();
	
	
	
	@Query(value = "select distinct(concat(ForecastingGroup,\"-\",FGID,\"-\",lead_sku)) from FINAL_AURORA_UPDATED_CHECK_1_ab", nativeQuery = true)
	List<String> forecastingGroup();
	
	
	
	@Query(value = "SELECT DISTINCT(sales_office) As Sales FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE sales_office!='' ", nativeQuery = true)
	List<String> fetchsales();
	
	
	
	
	@Query(value = "SELECT DISTINCT(trade_type) As Trade FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE trade_type!='' ", nativeQuery = true)
	List<String> fetchtrade();
	
	
	// New
	
	
	@Query(value = "SELECT DISTINCT(global_bev_cat) As GlobalBev FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE global_bev_cat!='' ", nativeQuery = true)
	List<String> fetch_global_bev_cat();
	
	
	@Query(value = "SELECT DISTINCT(materialgroup) As materialgroup FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE materialgroup!='' ", nativeQuery = true)
	List<String> fetchmaterial();
	
	
	@Query(value = "SELECT DISTINCT(base_unit_of_measure_characteristic) As baseunit FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE base_unit_of_measure_characteristic!='' ", nativeQuery = true)
	List<String> fetch_base();
	
	

	@Query(value = "SELECT DISTINCT(pack_type) As pack_type FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE pack_type!='' ", nativeQuery = true)
	List<String> fetch_packtype();
	
	
	
	
	@Query(value = "SELECT DISTINCT(pack_size) As pack_size FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE pack_size!='' ", nativeQuery = true)
	List<String> fetch_packsize();
	
	
	
	
	@Query(value = "SELECT DISTINCT(Animal_Flags) As Animal_Flags FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE Animal_Flags!='' ", nativeQuery = true)
	List<String> fetchanimal();
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_group_planning_name) As cpgname FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_group_planning_name!='' ", nativeQuery = true)
	List<String> fetchcpgname();
	
	
	// END
	
	
	
	
	
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(trade_type) As Trade FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE trade_type!='' ", nativeQuery = true)
	List<String> fetchtradetype();
	
	
	@Query(value = "SELECT DISTINCT(sales_office) As Sales FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE sales_office!='' ", nativeQuery = true)
	List<String> fetchsalesoffice();
	
	

	// Fetch CPGs
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' ", nativeQuery = true)
	List<String> fetchCPGs();
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' ", nativeQuery = true)
	List<String> fetchanimalFlag();
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' ", nativeQuery = true)
	List<String> fetchPackType();
	
	
	
	
	

	// Fetch Forecasting Groups
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' ", nativeQuery = true)
	List<String> fetchForecastingGroups();

	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroupsByBrands(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "Select calendar_yearmonth  from FINAL_AURORA_UPDATED_CHECK_1_ab where customer_planning_group = :cpg AND plant = :plant AND calendar_yearweek = :week AND Name = :name", nativeQuery = true)
	int fetchcalendarMonth(@Param("cpg") String cpg,@Param("plant") String regexp,@Param("week") int week,@Param("name") String name);
	
	
	
	@Query(value = "Select lead_sku as id from FINAL_AURORA_UPDATED_CHECK_1_ab where material=:name LIMIT 1;", nativeQuery = true)
	int getleadskuid(@Param("name") String name);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Alcohol_Percentage REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_d(@Param("regexp") String regexp);
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_a(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_b(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_c(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_cd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_bd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_bc(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_ac(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_ad(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Sub_Brand REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_bcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_acd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_abd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	

	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_abc(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);

	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2 AND Alcohol_Percentage REGEXP :subbrand3 ", nativeQuery = true)
	List<String> fetchForecastingGroups_abcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2,@Param("subbrand3") String subbrand3);

	
	
	
	
	
	
	
	@Query(value = fetchForecastingGroups_Updated_string, nativeQuery = true)
	List<String> fetchForecastingGroups_Updated(@Param("mainQuery") String mainQuery);
	
	
	
	

	// Fetch filters lists
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE customer_planning_group!='' ORDER BY customer_planning_group", nativeQuery = true)
	List<String> fetchFilterListCPG();
 
	@Query(value = "SELECT DISTINCT(plant) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE plant!='' ORDER BY plant;", nativeQuery = true)
	List<String> fetchFilterListPlants();
	


	@Query(value = "SELECT DISTINCT(pack_type_name) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE pack_type_name!=''", nativeQuery = true)
	List<String> fetchPackTypeName();
	
	
	
	
	@Query(value = "SELECT DISTINCT(pack_type_name) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE pack_type_name!=''", nativeQuery = true)
	List<String> fetchFilterListPackaging();
	
	

	
//	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
//	List<String> fetchFilterListPackaging(@Param("subBrand") List<String> subBrand , @Param("alcoholPerc") List<String> alcoholPerc 
//			, @Param("unitPerPack") List<String> unitPerPack , @Param("brand") List<String> brands);
//	
	// Fetch graphs
	@Query(value = "SELECT DISTINCT(lead_sku_name) FROM FINAL_AURORA_UPDATED_CHECK_1_ab WHERE lead_sku_name!=''", nativeQuery = true)
	List<String> fetchDemandTable();

	@Query(value = fetchDemandTableQuery, nativeQuery = true)
	List<DemandTableRes> fetchDemandTableByWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	@Query(value = fetchDemandPO_UOM, nativeQuery = true)
	List<UOMResponse> fetchDemandTableByWeeks_UOM(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	

	
	

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

	
	
	

	
	
	 @Modifying
	 @Transactional
	@Query(value = "INSERT INTO UserLog VALUES(:Username,:activity,:datetime)", nativeQuery = true)
		void savelogs(@Param("Username") String username,@Param("activity") String activity,@Param("datetime") String datetime);
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE filter_data SET valuedefault='ygroup' where filter_name = :name", nativeQuery = true)
		void change_filter_to_default(@Param("name") String name);
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE filter_data SET valuedefault=NULL where filter_name = :name", nativeQuery = true)
		void change_filter_prev_null(@Param("name") String name);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		@Query(value = fetchDemandTableQuery1, nativeQuery = true)
		List<DemandTableRes> fetchDemandTableByWeeks1(@Param("forecastingGroupList") List<String> forecastingGroupList,
				@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
				@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
}
