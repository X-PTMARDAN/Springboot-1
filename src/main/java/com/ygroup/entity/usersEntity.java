package com.ygroup.entity;
//abhik
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Its the entity(FORMAT IN WHICH DATA IS FETCHED FROM DATABASE) for Saved Plan for a particular user, indexed by pk_combination thats made by user+week+cpg+plant+sku



@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class usersEntity {
	
	@Id
	@GeneratedValue
    private Integer id;
	
	private String user;
	
	private Integer horizon;
	


}
