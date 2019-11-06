package com.ygroup.config;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.UOMResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transss implements ResultTransformer {

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] objects, String[] strings) {
		UOMResponse demandTableRes = new UOMResponse();

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
				case "harshit":
					demandTableRes.setHarshit(String.valueOf(objects[i]));
				case "forecasting":
					demandTableRes.setForecasting(String.valueOf(objects[i]));
				}
			} catch (Exception e) {
				log.error("Exception occurred while mapping column from fetched DB resultSet.", e);
			}
		}

		log.info("Mapped response: {}", demandTableRes);
		return demandTableRes;
	}

}
