package com.fusionhawk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fusionhawk.entity.CacheTableEntity;

@Repository
public interface CacheRepository extends JpaRepository<CacheTableEntity, String> {

	// abhik
	String fetchCacheTableQuery = "select * from CacheTable where ML_Key in (:key)";

	// abhik
	@Query(value = fetchCacheTableQuery, nativeQuery = true)
	List<CacheTableEntity> fetchCacheTableByKey(@Param("key") List<String> key);
}
