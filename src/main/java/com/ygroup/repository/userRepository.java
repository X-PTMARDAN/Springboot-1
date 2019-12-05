package com.ygroup.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ygroup.entity.FilterEntity;
import com.ygroup.entity.ViewEntity;
import com.ygroup.entity.usersEntity;
import com.ygroup.model.req.usersReq;



// Repository for View
public interface userRepository extends CrudRepository<usersEntity, Long> {
	
	List<usersEntity> findByUser(String user);


	
}