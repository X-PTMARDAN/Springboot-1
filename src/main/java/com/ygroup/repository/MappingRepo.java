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
import com.ygroup.model.res.LogResponse;
import com.ygroup.model.res.UOMResponse;
import com.ygroup.model.res.featureAnalysisRes;
import com.ygroup.model.res.mappingResp;

@Repository
public interface MappingRepo extends JpaRepository<mappingResp, String> {


	String materialMapping="Select material as materialdesc,hl,pc from ygroup.Mapping WHERE material IN (SELECT DISTINCT(material) from AGGREGATED_TABLE_UPDATED where ForecastingGroup IN (:forecastingGroupList))";

	
	@Query(value = materialMapping, nativeQuery = true)
	List<mappingResp> materialMapping(@Param("forecastingGroupList") List<String> forecastingGroupList);
	
}
