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
public interface LogRepo extends JpaRepository<LogResponse, String> {
	
	
	@Query(value = "SELECT Username,activity,datetimestamp FROM UserLog WHERE Username='admin'", nativeQuery = true)
    List<LogResponse> fetchlogs();
	
	
	@Query(value = "Select distinct(CONCAT(comments1,\"|\",Calendar_Week,\"|\",forecasting,\"|\",cpg,\"|\",plant)) from plan_data where comments1 IS NOT NULL AND forecasting IS NOT NULL", nativeQuery = true)
    List<String> fetchcomments();
	
}
