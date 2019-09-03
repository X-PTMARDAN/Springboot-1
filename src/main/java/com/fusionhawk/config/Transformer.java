package com.fusionhawk.config;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.fusionhawk.model.res.DemandTableRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transformer implements ResultTransformer {

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] objects, String[] strings) {
		DemandTableRes demandTableRes = new DemandTableRes();

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;
			try {
				switch (strings[i]) {
				case "apo":
					demandTableRes.setApo(String.valueOf(objects[i]));
					break;
				case "week":
					BigDecimal week =  new BigDecimal(String.valueOf(objects[i]));
					demandTableRes.setCalenderYearWeek(week.intValue());
					break;
				case "ml":
					demandTableRes.setMl(String.valueOf(objects[i]));
					break;
				case "actuals":
					demandTableRes.setActuals(String.valueOf(objects[i]));
				}
			} catch (Exception e) {
				log.error("Exception occurred while mapping column from fetched DB resultSet.", e);
			}
		}

		log.info("Mapped response: {}", demandTableRes);
		return demandTableRes;
	}

}
