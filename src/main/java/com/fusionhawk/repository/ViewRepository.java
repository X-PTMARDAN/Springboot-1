package com.fusionhawk.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fusionhawk.entity.FilterEntity;
import com.fusionhawk.entity.ViewEntity;
//abhik
public interface ViewRepository extends CrudRepository<ViewEntity, Long> {
	
	List<ViewEntity> findByUser(String user);
	
}