package com.ygroup.repository;
import java.util.List;
//abhik
import org.springframework.data.repository.CrudRepository;

import com.ygroup.entity.FilterEntity;


public interface pipoSKU extends CrudRepository<com.ygroup.entity.pipoSKU, Long> {
	
	List<com.ygroup.entity.pipoSKU> findAll();
	
}