package com.ygroup.config;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.UOMResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transformer_1 implements ResultTransformer {

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] objects, String[] strings) {
		UOMResponse uomresponse = new UOMResponse();

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;
			try {
				switch (strings[i]) {
				case "forecasting":
					uomresponse.setForecasting(String.valueOf(objects[i]));
					//uomresponse.set(String.valueOf(objects[i]));
					break;
				case "week":
					BigDecimal week =  new BigDecimal(String.valueOf(objects[i]));
					uomresponse.setCalenderYearWeek(week.intValue());
					break;
				case "ml":
					uomresponse.setMl(String.valueOf(objects[i]));
					break;
				case "actuals":
					uomresponse.setActuals(String.valueOf(objects[i]));
					break;
				case "apo":
					uomresponse.setApo(String.valueOf(objects[i]));
					break;
				case "harshit":
					uomresponse.setHarshit(String.valueOf(objects[i]));
					break;
				case "promo":
					uomresponse.setPromo(String.valueOf(objects[i]));
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
