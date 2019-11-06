package com.ygroup.repository;
import java.util.List;
//abhik
import org.springframework.data.repository.CrudRepository;

import com.ygroup.entity.FilterEntity;

public interface FilterRepository extends CrudRepository<FilterEntity, Long> {
	
	List<FilterEntity> findByUser(String user);
	
}