package com.ygroup.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
//abhik
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ygroup.entity.FilterEntity;
import com.ygroup.entity.PIPOMapping;

public interface PIPOMappingRepo extends CrudRepository<PIPOMapping, Long> {
	
	@Query(value = "Select * from pipo_mapping where fromid =:fromid", nativeQuery = true)
	List<PIPOMapping> findbyid(@Param("fromid") Integer skus);
	
	
}