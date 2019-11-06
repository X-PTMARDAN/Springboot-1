package com.ygroup.repository;
import java.util.List;
//abhik
import org.springframework.data.repository.CrudRepository;

import com.ygroup.entity.FilterEntity;
import com.ygroup.entity.PIPOEntity;

public interface pipoRepo extends CrudRepository<PIPOEntity, Long> {
	
	List<PIPOEntity> findAll();
	
}