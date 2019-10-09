package com.fusionhawk.service;

import java.util.List;

import com.fusionhawk.entity.CacheTableEntity;
import com.fusionhawk.entity.PIPOEntity;
import com.fusionhawk.entity.pipoSKU;
import com.fusionhawk.model.req.CPGreq;
import com.fusionhawk.model.req.DemandTableReq;
import com.fusionhawk.model.req.EditComment;
import com.fusionhawk.model.req.ForecastingGroupsReq;
import com.fusionhawk.model.req.SaveFilterReq;
import com.fusionhawk.model.req.SaveLogReq;
import com.fusionhawk.model.req.SavePlanReq;
import com.fusionhawk.model.req.SaveViewReq;
import com.fusionhawk.model.req.changedFilter;
import com.fusionhawk.model.req.default_filter_res;
import com.fusionhawk.model.res.FetchFilterListRes;
import com.fusionhawk.model.res.FetchViewListRes;
import com.fusionhawk.model.res.FilterListRes;
import com.fusionhawk.model.res.GraphRes;
import com.fusionhawk.model.res.LogResponse;
import com.fusionhawk.model.res.downLoadPlan;
import com.fusionhawk.model.res.featureGraphRes;

public interface FusionhawkService {

	public List<String> getBrands();

	public List<String> getPlants();
	
	public List<String> getunitPerPack();
	
	
	public List<String> getsubbrand();
	
	

	
	

	public List<String> getCPGs();

	public List<String> getForecastingGroups(ForecastingGroupsReq forecastingGroupsReq);

	public FilterListRes getFiltersList();

	public GraphRes getDemandTable(DemandTableReq demandTableReq);
	
	
	public featureGraphRes getFeatureAnalysis(DemandTableReq demandTableReq);
	//public GraphRes getDemandTable_monthly(DemandTableReq demandTableReq);

	// abhik
	public List<CacheTableEntity> getCacheTable();

	public void savePlanData(SavePlanReq savePlanReq);

	public List<CacheTableEntity> getCacheTable(List<String> selectFromCache);

	public String confirmPlanData(List<SavePlanReq> savePlanReq);

	public String saveFilter(SaveFilterReq saveFilterReq);
	
	public String saveLog(SaveLogReq saveLogReq);

	public List<FetchFilterListRes> fetchFilter();
	
	public String saveView(SaveViewReq saveViewReq);
	

	
	public List<FetchViewListRes> fetchView();

	public String deleteTempData();

	List<String> getalcoholpercentage();

	GraphRes getDemandTable_monthly(DemandTableReq demandTableReq);

	GraphRes getDemandTable_yearly(DemandTableReq demandTableReq);

	public List<String> editComment(EditComment data);

	List<String> getcpg1(CPGreq cpg);

	List<String> getsalesoffice();

	List<String> gettradetype();

	List<LogResponse> fetchlogs();

	GraphRes getDemandTable_UOM(DemandTableReq demandTableReq);

	List<String> fetchcomments();

	List<String> changedFilterSKU(changedFilter list);

	List<String> changedFilterCPG(changedFilter list);

	List<String> forecastingGroup_List();

	List<String> getTradetype();

	List<String> getSales();

	List<String> getAnimalFlag();

	List<String> getPackType();

	List<String> getmaterialgroup();

	List<String> getanimal();

	List<String> getalcoholper();

	List<String> getPacktype();

	List<String> getbaseunit();

	List<String> getglobalbevcat();

	downLoadPlan getDownload();

	GraphRes getDemandTable_UOM_HL(DemandTableReq demandTableReq);

	GraphRes getDemandTable_UOM_L(DemandTableReq demandTableReq);

	List<PIPOEntity> fetchpipo();

	String set_default(default_filter_res saveLogReq);

	String change_filter_prev_null(default_filter_res saveLogReq);

	String savePIPO(PIPOEntity saveFilterReq);

	String savePIPOSKU(pipoSKU saveFilterReq);

	//GraphRes getFeatureAnalysis(DemandTableReq demandTableReq);

	
}
