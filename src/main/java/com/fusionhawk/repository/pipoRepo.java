package com.fusionhawk.repository;
import java.util.List;
//abhik
import org.springframework.data.repository.CrudRepository;

import com.fusionhawk.entity.FilterEntity;
import com.fusionhawk.entity.PIPOEntity;

public interface pipoRepo extends CrudRepository<PIPOEntity, Long> {
	
	List<PIPOEntity> findAll();
	
}