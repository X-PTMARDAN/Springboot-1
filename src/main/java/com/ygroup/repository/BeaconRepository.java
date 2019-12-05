package com.ygroup.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.DemandTableResponse_Updated;
import com.ygroup.model.res.UOMResponse;
import com.ygroup.model.res.featureAnalysisRes;

@Repository
public interface BeaconRepository extends JpaRepository<DemandTableRes, String> {

	String fetchDemandTableQuery = "SELECT SUM(apo_calculated_sales_estimate) as apo, SUM(promotion_forecast) as promo, calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	
	String fetchDemandTableQuery_monthly = "SELECT SUM(apo_calculated_sales_estimate) as apo,  calendar_yearmonth + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	@Query(value = "Select distinct(CONCAT(comments1,\"|\",Calendar_Week,\"|\",forecasting,\"|\",cpg,\"|\",plant)) from plan_data where comments1 IS NOT NULL AND forecasting IS NOT NULL AND plant IN (:plantList) AND cpg IN (:cpgList) AND Calendar_Week BETWEEN :startWeek AND :endWeek AND forecasting IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))", nativeQuery = true)
    List<String> fetchcomments_all(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek);
	
	
	String fetchDemandTableQuery1 = "SELECT SUM(final_apo) as apo,  calendar_yearweek + :x AS week, SUM(final_pred_pc) as ml, SUM(open_orders)*26 as harshit,SUM(promotion_forecast)*26 as promo, SUM(final_total_sales) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND final_pred_pc IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	String fetchDemandTableQuery1_monthly_1 = "SELECT SUM(final_apo) as apo,  calendar_yearmonth + :x AS week, SUM(final_pred_pc) as ml, SUM(open_orders)*26 as harshit,SUM(promotion_forecast)*26 as promo, SUM(final_total_sales) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND final_pred_pc IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	
	String fetchDemandTableQuery1_monthly = "SELECT SUM(final_apo) as apo,  calendar_yearmonth + :x AS week, SUM(final_pred_pc) as ml, SUM(open_orders) as harshit, SUM(final_total_sales) as actuals\n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE  plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" + 
			" \n" + 
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND final_pred_pc IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	
	String fetchDemandPO_UOM="SELECT ForecastingGroup,SUM(apo_calculated_sales_estimate) as apo,  calendar_yearweek + :x AS week, SUM(predictions) as ml, SUM(open_orders) as harshit, SUM(total_sales_volume) as actuals  FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group IN (:cpgList) AND plant IN (:plantList) AND calendar_yearweek BETWEEN :startWeek AND :endWeek AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList)) AND predictions IS NOT NULL GROUP BY ForecastingGroup,calendar_yearweek";
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis_temperature_monthly = "SELECT RAND(6)  as property, RAND(6) as property2, MAX(Average_temperature) as property3, calendar_yearmonth + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis_temperature = "SELECT RAND(6)  as property, RAND(6) as property2, MAX(Average_temperature) as property3, calendar_yearweek + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	String fetchFeatureTable_featureAnalysis_open_orders = "SELECT SUM(open_orders)  as property, RAND(6) as property2, MAX(Average_temperature) as property3, calendar_yearweek + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	String fetchFeatureTable_featureAnalysis_open_orders_PC = "SELECT SUM(open_orders)*26  as property, RAND(6) as property2, MAX(Average_temperature) as property3, calendar_yearweek + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	String fetchFeatureTable_featureAnalysis_ML = "SELECT SUM(predictions-promotion_forecast)  as property, RAND(6) as property2, SUM(promotion_forecast) as property3, calendar_yearweek + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis_ML_PC = "SELECT SUM(final_pred_pc-promotion_forecast*25)  as property, RAND(6) as property2, SUM(promotion_forecast*25) as property3, calendar_yearweek + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearweek";
	
	
	
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis_monthly_temperature = "SELECT RAND(6)  as property, RAND(6) as property2, AVG(Average_temperature) as property3, calendar_yearmonth + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchFeatureTable_featureAnalysis_monthly_open_orders = "SELECT SUM(open_orders)  as property, RAND(6) as property2, AVG(Average_temperature) as property3, calendar_yearmonth + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	String fetchFeatureTable_featureAnalysis_monthly_open_orders_PC = "SELECT SUM(open_orders)*26  as property, RAND(6) as property2, AVG(Average_temperature) as property3, calendar_yearmonth + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis_monthly_ML = "SELECT SUM(predictions-promotion_forecast)  as property, RAND(6) as property2, SUM(promotion_forecast) as property3, calendar_yearmonth + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	
	
	
	
	String fetchFeatureTable_featureAnalysis_monthly_ML_PC = "SELECT SUM(final_pred_pc-promotion_forecast*25)  as property, RAND(6) as property2, SUM(promotion_forecast*25) as property3, calendar_yearmonth + :x AS week \n" + 
			"FROM AGGREGATED_TABLE_UPDATED \n" + 
			"WHERE plant IN (:plantList) AND customer_planning_group IN (:cpgList) \n" +  
			"AND calendar_yearweek BETWEEN :startWeek AND :endWeek \n" + 
			"AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList))\n" + 
			"AND predictions IS NOT NULL GROUP BY calendar_yearmonth";
	
	
	
	
	String fetchDemandTableQuery_Month = "SELECT SUM(apo_calculated_sales_estimate) as apo, calendar_yearmonth + :x AS week, SUM(predictions) as ml,SUM(open_orders) as harshit,SUM(promotion_forecast) as promo, SUM(total_sales_volume) as actuals\n" + 
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
	
	
	
	
	
	
	
	
	///////
	String fetchDemandTableByFG = "SELECT DISTINCT(material) from AGGREGATED_TABLE_UPDATED where ForecastingGroup IN (:key)";
	//jatin
	//Fetch ForeCast On the basis of different filters
		String fetchForeCastOnFilter = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND (Brand REGEXP :regexp AND (Sub_Brand REGEXP (:subBrand) "
				+ " REGEXP Alcohol_Percentage IN (:alcoholPerc) REGEXP UnitPerPack IN (:unitPerPack))";
		
		
	String fetchForecastingGroups_Updated_string = "SELECT DISTINCT(material) from AGGREGATED_TABLE_UPDATED (:mainQuery)";
		
	
	
	
	///
		
	// Fetch brands
	@Query(value = "SELECT DISTINCT(Brand) FROM AGGREGATED_TABLE_UPDATED WHERE Brand!='' ", nativeQuery = true)
	List<String> fetchBrands();
	
	
	@Query(value = "SELECT DISTINCT(Brand) FROM AGGREGATED_TABLE_UPDATED WHERE Brand!='' AND ForecastingGroup IN (select DISTINCT(ForecastingGroup) from AGGREGATED_TABLE_UPDATED where material in(:forecastingGroupList)) ", nativeQuery = true)
	List<String> fetchBrands_filters(@Param("forecastingGroupList") List<String> forecastingGroupList);

	// Fetch plants
	@Query(value = "SELECT DISTINCT(plant) FROM AGGREGATED_TABLE_UPDATED WHERE plant!='' ", nativeQuery = true)
	List<String> fetchPlants();
	
	
	@Query(value = "SELECT DISTINCT(FGID) FROM AGGREGATED_TABLE_UPDATED WHERE plant!='' ", nativeQuery = true)
	List<Integer> fetchforecastingid();
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE plant!='' ", nativeQuery = true)
	List<String> fetchforecastinggroup();
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(unitPerPack) As unitPerPack FROM AGGREGATED_TABLE_UPDATED WHERE unitPerPack!='' ", nativeQuery = true)
	List<String> fetchunitPerPack();
	
	
	
	
	

	
	
	
	@Query(value = "SELECT DISTINCT(Alcohol_percentage) As Alcohol_percentage FROM AGGREGATED_TABLE_UPDATED WHERE Alcohol_percentage!='' ORDER BY Alcohol_percentage ASC ", nativeQuery = true)
	List<String> fetchalcoholpercentage();
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) As customer_planning_group FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!=''  ", nativeQuery = true)
	List<String> cpg_groups();
	
	
	
	


	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' AND sales_office REGEXP :regexp AND trade_type REGEXP :regexp1  ", nativeQuery = true)
	List<String> cpg_groups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' AND sales_office REGEXP :regexp  ", nativeQuery = true)
	List<String> cpg_groups_a(@Param("regexp") String regexp);
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' AND trade_type REGEXP :regexp1  ", nativeQuery = true)
	List<String> cpg_groups_b(@Param("regexp1") String regexp1);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE material=:sku ", nativeQuery = true)
	List<String> fgmapsku(@Param("sku") String sku);
	
	
	
	@Query(value = "SELECT DISTINCT(Sub_Brand) As Sub_Brand FROM AGGREGATED_TABLE_UPDATED WHERE Sub_Brand!='' ", nativeQuery = true)
	List<String> fetchsubbrand();
	
	
	
	@Query(value = "select distinct(concat(ForecastingGroup,\"-\",FGID,\"-\",material)) from AGGREGATED_TABLE_UPDATED", nativeQuery = true)
	List<String> forecastingGroup();
	
	
	
	@Query(value = "SELECT DISTINCT(sales_office) As Sales FROM AGGREGATED_TABLE_UPDATED WHERE sales_office!='' ", nativeQuery = true)
	List<String> fetchsales();
	
	
	
	
	@Query(value = "SELECT DISTINCT(trade_type) As Trade FROM AGGREGATED_TABLE_UPDATED WHERE trade_type!='' ", nativeQuery = true)
	List<String> fetchtrade();
	
	
	// New
	
	
	@Query(value = "SELECT DISTINCT(global_bev_cat) As GlobalBev FROM AGGREGATED_TABLE_UPDATED WHERE global_bev_cat!='' ", nativeQuery = true)
	List<String> fetch_global_bev_cat();
	
	
	@Query(value = "SELECT DISTINCT(materialgroup) As materialgroup FROM AGGREGATED_TABLE_UPDATED WHERE materialgroup!='' ", nativeQuery = true)
	List<String> fetchmaterial();
	
	
	@Query(value = "SELECT DISTINCT(base_unit_of_measure_characteristic) As baseunit FROM AGGREGATED_TABLE_UPDATED WHERE base_unit_of_measure_characteristic!='' ", nativeQuery = true)
	List<String> fetch_base();
	
	

	@Query(value = "SELECT DISTINCT(pack_type) As pack_type FROM AGGREGATED_TABLE_UPDATED WHERE pack_type!='' ", nativeQuery = true)
	List<String> fetch_packtype();
	
	
	
	
	@Query(value = "SELECT DISTINCT(pack_size) As pack_size FROM AGGREGATED_TABLE_UPDATED WHERE pack_size!='' ", nativeQuery = true)
	List<String> fetch_packsize();
	
	
	
	
	@Query(value = "SELECT DISTINCT(Animal_Flags) As Animal_Flags FROM AGGREGATED_TABLE_UPDATED WHERE Animal_Flags!='' ", nativeQuery = true)
	List<String> fetchanimal();
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_group_planning_name) As cpgname FROM AGGREGATED_TABLE_UPDATED WHERE customer_group_planning_name!='' ", nativeQuery = true)
	List<String> fetchcpgname();
	
	
	// END
	
	
	
	
	
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(trade_type) As Trade FROM AGGREGATED_TABLE_UPDATED WHERE trade_type!='' ", nativeQuery = true)
	List<String> fetchtradetype();
	
	
	@Query(value = "SELECT DISTINCT(sales_office) As Sales FROM AGGREGATED_TABLE_UPDATED WHERE sales_office!='' ", nativeQuery = true)
	List<String> fetchsalesoffice();
	
	

	// Fetch CPGs
	@Query(value = "Select distinct(customer_planning_group) from AGGREGATED_TABLE_UPDATED ", nativeQuery = true)
	List<String> fetchCPGs();
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' ", nativeQuery = true)
	List<String> fetchanimalFlag();
	
	
	
	
	@Query(value = "SELECT DISTINCT(customer_planning_group) FROM AGGREGATED_TABLE_UPDATED WHERE customer_planning_group!='' ", nativeQuery = true)
	List<String> fetchPackType();
	
	
	
	
	

	// Fetch Forecasting Groups
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' ", nativeQuery = true)
	List<String> fetchForecastingGroups();

	
	
	@Query(value = "Select max(calendar_yearweek) from AGGREGATED_TABLE_UPDATED ", nativeQuery = true)
	int getmaxweek();
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroupsByBrands(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "Select calendar_yearmonth from AGGREGATED_TABLE_UPDATED where customer_planning_group = :cpg AND plant = :plant AND calendar_yearweek = :week AND material = :name", nativeQuery = true)
	List<Integer> fetchcalendarMonth(@Param("cpg") String cpg,@Param("plant") String regexp,@Param("week") int week,@Param("name") int name);
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE material= :skus LIMIT 1", nativeQuery = true)
	String fetchFG(@Param("skus") Integer skus);
	
	
	
	@Query(value = "Select material as id from AGGREGATED_TABLE_UPDATED where material=:name LIMIT 1;", nativeQuery = true)
	int getleadskuid(@Param("name") String name);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Alcohol_Percentage REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_d(@Param("regexp") String regexp);
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_a(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_b(@Param("regexp") String regexp);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp ", nativeQuery = true)
	List<String> fetchForecastingGroups_c(@Param("regexp") String regexp);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND UnitPerPack REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_cd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_bd(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_bc(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND UnitPerPack REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_ac(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Alcohol_Percentage REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_ad(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :regexp AND Sub_Brand REGEXP :regexp1  ", nativeQuery = true)
	List<String> fetchForecastingGroups_ab(@Param("regexp") String regexp,@Param("regexp1") String regexp1);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Sub_Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_bcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND UnitPerPack REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_acd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	
	
	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND Alcohol_Percentage REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_abd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);
	

	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2  ", nativeQuery = true)
	List<String> fetchForecastingGroups_abc(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2);

	
	
	@Query(value = "SELECT DISTINCT(ForecastingGroup) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup!='' AND Brand REGEXP :subbrand AND Sub_Brand REGEXP :subbrand1 AND UnitPerPack REGEXP :subbrand2 AND Alcohol_Percentage REGEXP :subbrand3 ", nativeQuery = true)
	List<String> fetchForecastingGroups_abcd(@Param("subbrand") String subbrand,@Param("subbrand1") String subbrand1,@Param("subbrand2") String subbrand2,@Param("subbrand3") String subbrand3);

	
	
	
	@Query(value = "SELECT DISTINCT(FGID) FROM AGGREGATED_TABLE_UPDATED WHERE ForecastingGroup = :forecasting", nativeQuery = true)
	List<String> fetchForecastingGroups_fgid(@Param("forecasting") String forecasting);

	
	
	
	
	
	
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
	
	
	
	
	
	
	
	// PIPO MATERIAL LIST
	
	@Query(value = "SELECT material from pipo_final where Forecastinggroup IN (Select Forecastinggroup from pipo_final where material = :material)", nativeQuery = true)
	List<String> fetch_pipo_material_list(@Param("material") int i);
	
	
	
	
	
	
	
	
	
	
	
	

	
//	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
//	List<String> fetchFilterListPackaging(@Param("subBrand") List<String> subBrand , @Param("alcoholPerc") List<String> alcoholPerc 
//			, @Param("unitPerPack") List<String> unitPerPack , @Param("brand") List<String> brands);
//	
	// Fetch graphs
	@Query(value = "SELECT DISTINCT(lead_sku_name) FROM AGGREGATED_TABLE_UPDATED WHERE lead_sku_name!=''", nativeQuery = true)
	List<String> fetchDemandTable();

	@Query(value = fetchDemandTableQuery, nativeQuery = true)
	List<DemandTableRes> fetchDemandTableByWeeks(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	
	
	
	
	
	@Query(value = fetchDemandTableQuery_monthly, nativeQuery = true)
	List<DemandTableRes> fetchDemandTableByWeeks_monthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	
	
	
	
	
	
	
	
	@Query(value = fetchDemandPO_UOM, nativeQuery = true)
	List<UOMResponse> fetchDemandTableByWeeks_UOM(@Param("forecastingGroupList") List<String> forecastingGroupList,
			@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
			@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	

	
	

	@Query(value = fetchFeatureTable_featureAnalysis_temperature, nativeQuery = true)
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
	List<Integer> fetchDemandTableByFG(@Param("key") List<String> key);
	
	

	@Query(value = fetchForeCastOnFilter,nativeQuery = true)
	List<String> fetchForeCastOnFilter(@Param("regexp") String regexp, @Param("subBrand") String subBrand , @Param("alcoholPerc") String alcoholPerc, @Param("unitPerPack") String unitPerPack);

	
	
	

	
	
	 @Modifying
	 @Transactional
	@Query(value = "INSERT INTO UserLog VALUES(:Username,:activity,:datetime)", nativeQuery = true)
		void savelogs(@Param("Username") String username,@Param("activity") String activity,@Param("datetime") String datetime);
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE filter_data SET valuedefault='Default' where filter_name = :name", nativeQuery = true)
		void change_filter_to_default(@Param("name") String name);
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE filter_data SET valuedefault=NULL where filter_name = :name", nativeQuery = true)
		void change_filter_prev_null(@Param("name") String name);
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE pipo_final SET Forecastinggroup= :fg, FGID=:fgid  where material = :material", nativeQuery = true)
		void mapFG(@Param("fg") String fg,@Param("fgid") String fgid,@Param("material") String material);
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "INSERT INTO pipo_final VALUES(:material,:skuname,NULL,NULL,201940,NULL,NULL)", nativeQuery = true)
		void addvalue(@Param("material") String material,@Param("skuname") String skuname);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE pipo_final SET prime = NULL where material = :material", nativeQuery = true)
		void makepreviousprimenull(@Param("material") int i);
	 
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "DELETE from filter_data where filter_name=:name", nativeQuery = true)
		void deletefilter(@Param("name") String name);
	 
	 
	 
	 
	 @Modifying
	 @Transactional
	@Query(value = "UPDATE pipo_final SET prime= 'PRIMARY' where material = :material", nativeQuery = true)
		void makenewprimary(@Param("material") int material);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		@Query(value = fetchDemandTableQuery1, nativeQuery = true)
		List<DemandTableRes> fetchDemandTableByWeeks1(@Param("forecastingGroupList") List<String> forecastingGroupList,
				@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
				@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	 
		
		
		
		
		
		
		
		 
		
	 
	 
	 
	 
	 
		 
			@Query(value = fetchDemandTableQuery1_monthly, nativeQuery = true)
			List<DemandTableRes> fetchDemandTableByWeeks1_monthly(@Param("forecastingGroupList") List<String> forecastingGroupList,
					@Param("cpgList") List<String> cpgList, @Param("plantList") List<String> plantList,
					@Param("startWeek") Integer startWeek, @Param("endWeek") Integer endWeek, @Param("x") Integer x);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
}
