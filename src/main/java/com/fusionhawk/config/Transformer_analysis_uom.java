package com.fusionhawk.config;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.fusionhawk.model.res.DemandTableRes;
import com.fusionhawk.model.res.UOMResponse;
import com.fusionhawk.model.res.featureAnalysisRes_uom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transformer_analysis_uom implements ResultTransformer {

	@Override
	public List transformList(List collection) {
		return collection;
	}

	@Override
	public Object transformTuple(Object[] objects, String[] strings) {
		featureAnalysisRes_uom uomresponse = new featureAnalysisRes_uom();

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
				case "property":
					uomresponse.setProperty(Double.parseDouble(String.valueOf(objects[i])));
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
