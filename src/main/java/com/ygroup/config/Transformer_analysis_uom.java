package com.ygroup.config;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.UOMResponse;
import com.ygroup.model.res.UserCommentsRes;
import com.ygroup.model.res.featureAnalysisRes_uom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transformer_analysis_uom implements ResultTransformer {

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] objects, String[] strings) {
		UserCommentsRes uomresponse = new UserCommentsRes();

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;
			try {
				switch (strings[i]) {
				case "comments2":
					uomresponse.setComments2(String.valueOf(objects[i]));
					//uomresponse.set(String.valueOf(objects[i]));
					break;
				case "Calendar_Week":
					BigDecimal week =  new BigDecimal(String.valueOf(objects[i]));
					uomresponse.setCalendarWeek(week.intValue());
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
