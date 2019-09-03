package com.fusionhawk.repository;
import java.util.List;
//abhik
import org.springframework.data.repository.CrudRepository;

import com.fusionhawk.entity.FilterEntity;

public interface FilterRepository extends CrudRepository<FilterEntity, Long> {
	
	List<FilterEntity> findByUser(String user);
	
}