package com.ygroup.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ygroup.entity.FilterEntity;
import com.ygroup.entity.ViewEntity;



// Repository for View
public interface ViewRepository extends CrudRepository<ViewEntity, Long> {
	
	List<ViewEntity> findByUser(String user);
	
}