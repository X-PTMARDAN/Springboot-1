package com.ygroup.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



// Its the entity(FORMAT IN WHICH DATA IS FETCHED FROM DATABASE) for the cache table, thats stored in the Cache to increase the performance  


@Entity
@Table(name = "CacheTable")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheTableEntity {
	
	@Id
	@Column(name = "ML_Key")
	private String key;
	
	private double ML_Value;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheTableEntity other = (CacheTableEntity) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	
}
