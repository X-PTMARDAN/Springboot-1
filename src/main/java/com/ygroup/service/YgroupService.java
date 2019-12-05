package com.ygroup.service;

import java.util.List;


import com.ygroup.entity.CacheTableEntity;
import com.ygroup.entity.PIPOEntity;
import com.ygroup.entity.PIPOMapping;
import com.ygroup.entity.pipoSKU;
import com.ygroup.entity.usersEntity;
import com.ygroup.model.req.CPGreq;
import com.ygroup.model.req.DemandTableReq;
import com.ygroup.model.req.EditComment;
import com.ygroup.model.req.ForecastingGroupsReq;
import com.ygroup.model.req.SaveFilterReq;
import com.ygroup.model.req.SaveLogReq;
import com.ygroup.model.req.SavePlanReq;
import com.ygroup.model.req.SaveViewReq;
import com.ygroup.model.req.changedFilter;
import com.ygroup.model.req.default_filter_res;
import com.ygroup.model.req.mapFGreq;
import com.ygroup.model.req.materialREQ;
import com.ygroup.model.req.usersReq;
import com.ygroup.model.res.FetchFilterListRes;
import com.ygroup.model.res.FetchViewListRes;
import com.ygroup.model.res.FilterListRes;
import com.ygroup.model.res.GraphRes;
import com.ygroup.model.res.LogResponse;
import com.ygroup.model.res.downLoadPlan;
import com.ygroup.model.res.featureGraphRes;

public interface YgroupService {

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

	public void savePlanData(List<SavePlanReq> savePlanReq);

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

	List<Integer> changedFilterSKU(changedFilter list);

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



	
	
	
	
	
	String savePIPOSKU(PIPOMapping saveFilterReq);

	List<PIPOMapping> fetchPIPO();

	List<Integer> getForecastingid();

	List<String> getForecastingGroup();

	

	String mapFG(mapFGreq saveLogReq);

	featureGraphRes getFeatureAnalysis_monthly(DemandTableReq demandTableReq);

	List<String> fetch_pipo_material_list(PIPOMapping material);

	String addSKU_pipo_final(PIPOEntity saveFilterReq);

	GraphRes getDemandTable_L(DemandTableReq demandTableReq);

	GraphRes getDemandTable_L_month(DemandTableReq demandTableReq);

	GraphRes getDemandTable_UOM_monthly(DemandTableReq demandTableReq);

	int getmaxweek();

	

	String deletefilter(String filter);

	int fetchHorizon();

	

	List<String> getpacksize();

	String saveHorizon(usersEntity horizon);







	//GraphRes getFeatureAnalysis(DemandTableReq demandTableReq);

	
}
