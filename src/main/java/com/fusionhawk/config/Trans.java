package com.fusionhawk.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.fusionhawk.model.res.DemandTableRes;
import com.fusionhawk.model.res.UOMResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Trans implements ResultTransformer {

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] objects, String[] strings) {
		List<String> uomresponse = new ArrayList<>();

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;
			try {
				switch (strings[i]) {
				case "ForecastingGroup":
					uomresponse.add(String.valueOf(objects[i]));
					//uomresponse.set(String.valueOf(objects[i]));
					break;
				
				}
			} catch (Exception e) {
				log.error("Exception occurred while mapping column from fetched DB resultSet.", e);
			}
		}

		log.info("Mapped response: {}", uomresponse);
		return uomresponse;
	}

}
