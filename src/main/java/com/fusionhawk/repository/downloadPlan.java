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
import com.fusionhawk.model.res.oneRowPlan;

@Repository
public interface downloadPlan extends JpaRepository<oneRowPlan, String> {
	
	
	
	// Download 
		@Query(value = "Select * from plan_data where Final_Forecast!=0", nativeQuery = true)
		List<oneRowPlan> download_query();
		
		
		
		
		@Query(value = "Select calendar_yearweek as Calendar_Week, predictions as ml,Name as sku, material as id, customer_planning_group as cpg,apo_calculated_sales_estimate as Final_Forecast,FGID as fva, plant as plant from FINAL_AURORA_UPDATED_CHECK_1_ab where calendar_yearweek BETWEEN '201940' AND '201950'", nativeQuery = true)
		List<oneRowPlan> download_query_1();
		
		
		
}



