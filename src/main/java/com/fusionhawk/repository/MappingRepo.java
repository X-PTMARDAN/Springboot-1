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
import com.fusionhawk.model.res.mappingResp;

@Repository
public interface MappingRepo extends JpaRepository<mappingResp, String> {


	String materialMapping="Select materialDesc as materialdesc,hl,pc from ygroup.Mapping WHERE materialdesc IN (SELECT DISTINCT(Name) from Testing_Aurora where ForecastingGroup IN (:forecastingGroupList))";

	
	@Query(value = materialMapping, nativeQuery = true)
	List<mappingResp> materialMapping(@Param("forecastingGroupList") List<String> forecastingGroupList);
	
}
