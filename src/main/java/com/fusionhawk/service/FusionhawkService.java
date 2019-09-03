package com.fusionhawk.service;

import java.util.List;

import com.fusionhawk.entity.CacheTableEntity;
import com.fusionhawk.model.req.DemandTableReq;
import com.fusionhawk.model.req.ForecastingGroupsReq;
import com.fusionhawk.model.req.SaveFilterReq;
import com.fusionhawk.model.req.SavePlanReq;
import com.fusionhawk.model.req.SaveViewReq;
import com.fusionhawk.model.res.FetchFilterListRes;
import com.fusionhawk.model.res.FetchViewListRes;
import com.fusionhawk.model.res.FilterListRes;
import com.fusionhawk.model.res.GraphRes;

public interface FusionhawkService {

	public List<String> getBrands();

	public List<String> getPlants();
	
	public List<String> getunitPerPack();
	
	
	public List<String> getsubbrand();
	
	

	
	

	public List<String> getCPGs();

	public List<String> getForecastingGroups(ForecastingGroupsReq forecastingGroupsReq);

	public FilterListRes getFiltersList();

	public GraphRes getDemandTable(DemandTableReq demandTableReq);
	//public GraphRes getDemandTable_monthly(DemandTableReq demandTableReq);

	// abhik
	public List<CacheTableEntity> getCacheTable();

	public void savePlanData(SavePlanReq savePlanReq);

	public List<CacheTableEntity> getCacheTable(List<String> selectFromCache);

	public String confirmPlanData(List<SavePlanReq> savePlanReq);

	public String saveFilter(SaveFilterReq saveFilterReq);

	public List<FetchFilterListRes> fetchFilter();
	
	public String saveView(SaveViewReq saveViewReq);
	
	public List<FetchViewListRes> fetchView();

	public String deleteTempData();

	List<String> getalcoholpercentage();

	
}
