package com.fusionhawk.repository;
import java.util.List;
//abhik
import org.springframework.data.repository.CrudRepository;

import com.fusionhawk.entity.FilterEntity;


public interface pipoSKU extends CrudRepository<com.fusionhawk.entity.pipoSKU, Long> {
	
	List<com.fusionhawk.entity.pipoSKU> findAll();
	
}