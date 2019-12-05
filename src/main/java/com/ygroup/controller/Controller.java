package com.ygroup.controller;

import java.util.Date;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.ygroup.model.res.featureAnalysisRes;
import com.ygroup.model.res.featureGraphRes;
import com.ygroup.service.YgroupService;

@RestController


/*
 * 
 * 
 *
 * Every API should be preceeded by v1, its just kept to keep an understanding,in which version we are currently building the app
 */
@RequestMapping("/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

	@Autowired
	private YgroupService service;

	
	// Its just to get the Date on which the API is executed, to keep a record of change log
	@GetMapping(value = "/ping")
	public String getCurrentTimeStamp() {
		return "Application recieved ping on " + new Date().toString();
	}

	// It responds with the list of distinct brands in the brand column for filters (SKU Selection)
	@GetMapping(value = "/brands")
	public ResponseEntity<List<String>> getBrands() {
		return new ResponseEntity<>(service.getBrands(), HttpStatus.OK);
	}
	
	// It responds with the list of distinct plants in the plants column for filters (FG Selection)
	@GetMapping(value = "/plants")
	public ResponseEntity<List<String>> getPlants() {
		return new ResponseEntity<>(service.getPlants(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/animalFlag")
	public ResponseEntity<List<String>> getAnimalFlag() {
		return new ResponseEntity<>(service.getAnimalFlag(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/maxweek")
	public ResponseEntity<Integer> getmaxweek() {
		return new ResponseEntity<>(service.getmaxweek(), HttpStatus.OK);
	}
	
	
	
	// It responds with the list of distinct unitPerPack in the unitPerPack column for filters (FG Selection)
	@PostMapping(value = "/unitPerPack")
	public ResponseEntity<List<String>> getunitPerPack() {
		return new ResponseEntity<>(service.getunitPerPack(), HttpStatus.OK);
	}
	
	
	// It responds with the list of distinct alcohol percentage in the alcohol percentage column for filters (FG Selection)
	@GetMapping(value = "/alcoholPercentage")
	public ResponseEntity<List<String>> getalcoholpercentage() {
		return new ResponseEntity<>(service.getalcoholpercentage(), HttpStatus.OK);
	}
	
	
	// It responds with the list of distinct Sub-Brand in the brand column for filters (FG Selection)
	@GetMapping(value = "/subBrand")
	public ResponseEntity<List<String>> getsubbrand() {
		return new ResponseEntity<>(service.getsubbrand(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/salesoffice")
	public ResponseEntity<List<String>> getsalesoffice() {
		return new ResponseEntity<>(service.getsalesoffice(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/packsize")
	public ResponseEntity<List<String>> getpacksize() {
		return new ResponseEntity<>(service.getpacksize(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@GetMapping(value = "/trade")
	public ResponseEntity<List<String>> getTradetype() {
		return new ResponseEntity<>(service.gettradetype(), HttpStatus.OK);
	}
	
	
//	
//	@PostMapping(value = "/")
//	public ResponseEntity<List<String>> (PIPOMapping pipo_filter) {
//		
//		System.out.println("\n harshitttt---"+ pipo_filter.toString());
//		return new ResponseEntity<>(service.fetch_pipo_material_list(), HttpStatus.OK);
//	}
	
	
	
	@PostMapping(value = "/fetch_material_list_pipo")
	public List<String> fetch_pipo_material_list(@RequestBody PIPOMapping pipo_filter) {
		System.out.println("Start Time--------"+System.currentTimeMillis());
		//List<SavePlanReq> savePlanReq = null;
		List<String> a=service.fetch_pipo_material_list(pipo_filter);
		System.out.println("KUhfuhvr---"+a.toString());
		return a;
	}
	
	
	
	
	
	@GetMapping(value = "/fetchHorizon")
	public int fetchhorizon() {
		System.out.println("Start Time--------"+System.currentTimeMillis());
		//List<SavePlanReq> savePlanReq = null;
		int a= service.fetchHorizon();
		//System.out.println("KUhfuhvr---"+a.toString());
		return a;
	}
	
	
	
	@PostMapping(value = "/saveHorizon")
	public void saveHorizon(@RequestBody usersEntity horizon) {
		System.out.println("Start Time--------"+System.currentTimeMillis());
		
		System.out.println("Harshit --"+horizon.toString());
		//List<SavePlanReq> savePlanReq = null;
		service.saveHorizon(horizon);
		//System.out.println("KUhfuhvr---"+a.toString());
	
	}
	
	
	@PostMapping(value = "/deletefilter")
	public List<String> deletefilter(@RequestBody String delfilter) {
		System.out.println("Start Time--------"+System.currentTimeMillis());
		//List<SavePlanReq> savePlanReq = null;
	service.deletefilter(delfilter);
		//System.out.println("KUhfuhvr---"+a.toString());
		return null;
	}
	
	
	
	
//	
	@PostMapping(value = "/addSKU_pipo_final")
	public List<String> addSKU_pipo_final(@RequestBody PIPOEntity pipo_filter) {
		System.out.println("Start Time--------"+System.currentTimeMillis());
		//List<SavePlanReq> savePlanReq = null;
		service.addSKU_pipo_final(pipo_filter);
		//System.out.println("KUhfuhvr---"+a.toString());
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// It responds with the list of distinct CPG in the cpg column for filters (FG Selection)
	@GetMapping(value = "/cpg")
	public ResponseEntity<List<String>> getCPGs() {
		return new ResponseEntity<>(service.getCPGs(), HttpStatus.OK);
	}

	
	// It responds with the list of distinct Forecasting Group based on the filters (Brands, subbrands etc..)
	@PostMapping(value = "/forecastingGroup")
	public ResponseEntity<List<String>> getForecastingGroups(@RequestBody ForecastingGroupsReq forecastingGroupsReq) {
		return new ResponseEntity<>(service.getForecastingGroups(forecastingGroupsReq), HttpStatus.OK);
	}
	
	
	
	
	// It responds with the list of distinct Forecasting Group based on the filters (Brands, subbrands etc..)
	@PostMapping(value = "/sales")
	public ResponseEntity<List<String>> getSales() {
		return new ResponseEntity<>(service.getSales(), HttpStatus.OK);
	}
	
	
	
	// It responds with the list of distinct Forecasting Group based on the filters (Brands, subbrands etc..)
	@PostMapping(value = "/tradetype")
	public ResponseEntity<List<String>> getTradetype1() {
		return new ResponseEntity<>(service.getTradetype(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/materialgroup")
	public ResponseEntity<List<String>> getmaterialgroup() {
		return new ResponseEntity<>(service.getmaterialgroup(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/globalbevcat")
	public ResponseEntity<List<String>> getglobalbevcat() {
		return new ResponseEntity<>(service.getglobalbevcat(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/baseunit")
	public ResponseEntity<List<String>> getbaseunit() {
		return new ResponseEntity<>(service.getbaseunit(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/packtype")
	public ResponseEntity<List<String>> getpacktype(){
		return new ResponseEntity<>(service.getPacktype(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/fgid")
	public ResponseEntity<List<Integer>> getForecastingid(){
		return new ResponseEntity<>(service.getForecastingid(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/forecastinggroup")
	public ResponseEntity<List<String>> getForecastingGroup(){
		return new ResponseEntity<>(service.getForecastingGroup(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	@GetMapping(value = "/alcoholpercentage")
	public ResponseEntity<List<String>> getAlcohol(){
		return new ResponseEntity<>(service.getalcoholper(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/animal")
	public ResponseEntity<List<String>> getanimal(){
		return new ResponseEntity<>(service.getanimal(), HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value = "/cpg")
	public ResponseEntity<List<String>> getcpg1(@RequestBody CPGreq CPG) {
		return new ResponseEntity<>(service.getcpg1(CPG), HttpStatus.OK);
	}

	
    // It the main controller API that fetches the data(ML,APO, Forecast value add, comments etc) and populates the graph,feature analysis and table
	// Takes startweek, endweek, customer planning group, Forecasting group, plants as the request
	
	@PostMapping(value = "/demandTable")
	public ResponseEntity<GraphRes> getDemandTable(@RequestBody DemandTableReq demandTableReq) {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDemandTable(demandTableReq), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/demandTable_L")
	public ResponseEntity<GraphRes> getDemandTable_L(@RequestBody DemandTableReq demandTableReq) {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDemandTable_L(demandTableReq), HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/demandTable_L_month")
	public ResponseEntity<GraphRes> getDemandTable_L_month(@RequestBody DemandTableReq demandTableReq) {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDemandTable_L_month(demandTableReq), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@PostMapping(value = "/demandTable_UOM")
	public ResponseEntity<GraphRes> getDemandTable_UOM(@RequestBody DemandTableReq demandTableReq) {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDemandTable_UOM(demandTableReq), HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value = "/demandTable_UOM_monthly")
	public ResponseEntity<GraphRes> getDemandTable_UOM_monthly(@RequestBody DemandTableReq demandTableReq) {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDemandTable_UOM_monthly(demandTableReq), HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(value = "/download")
	public ResponseEntity<downLoadPlan> getPlan() {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDownload(), HttpStatus.OK);
	}
	

	
	
    // It responds with the feature analysis graph results
	// Takes startweek, endweek, customer planning group, Forecasting group, plants as the request
	@PostMapping(value = "/demandTable_feature_analysis")
	public ResponseEntity<featureGraphRes> getFeatureAnalysis(@RequestBody DemandTableReq demandTableReq) {
		//service.getFeatureAnalysis(demandTableReq);
	//	return null;
		return new ResponseEntity<>(service.getFeatureAnalysis(demandTableReq), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/demandTable_feature_analysis_monthly")
	public ResponseEntity<featureGraphRes> getFeatureAnalysis_monthly(@RequestBody DemandTableReq demandTableReq) {
		//service.getFeatureAnalysis(demandTableReq);
	//	return null;
		return new ResponseEntity<>(service.getFeatureAnalysis_monthly(demandTableReq), HttpStatus.OK);
	}
	
	
	

	
	
	   // It respondes with the  data(ML,APO, Forecast value add, comments etc) and populates the graph,feature analysis and table(MONTHLY)
		// Takes startweek, endweek, customer planning group, Forecasting group, plants as the request
	@PostMapping(value = "/demandTable_monthly")
	public ResponseEntity<GraphRes> getDemandTable_monthly(@RequestBody DemandTableReq demandTableReq) {
		return new ResponseEntity<>(service.getDemandTable_monthly(demandTableReq), HttpStatus.OK);
	}
	
	
	  // It respondes with the  data(ML,APO, Forecast value add, comments etc) and populates the graph,feature analysis and table(Y)
			// Takes startweek, endweek, customer planning group, Forecasting group, plants as the request
	@PostMapping(value = "/demandTable_yearly")
	public ResponseEntity<GraphRes> getDemandTable_yearly(@RequestBody DemandTableReq demandTableReq) {
		return new ResponseEntity<>(service.getDemandTable_yearly(demandTableReq), HttpStatus.OK);
	}
	
	
	
	  // To increase performance we have kept the indexed data in Cache. so that data transmission can be done at a lesser time
		@GetMapping(value = "/Cache")
		public void getCacheTable() {
			service.getCacheTable();
		}
		
		
		
		// It takes up the single week's value and based upon the Customer Planner Group, Plants, Forecasting Group and that particular week 
		// in temporary data
		@PostMapping(value = "/savePlanData")
		public void savePlanData(@RequestBody List<SavePlanReq> savePlanReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//SavePlanReq savePlanReq = null;
			service.savePlanData(savePlanReq);
		}
		
		
		
//		@PostMapping(value = "/changedfilterSKU")
//		public void changedFilterSKU(@RequestBody com.fusionhawk.model.req.changedFilter list) {
//			System.out.println("Start Time--------"+System.currentTimeMillis());
//			//SavePlanReq savePlanReq = null;
//			service.changedFilterSKU(list);
//		}
		
		
		@PostMapping(value = "/changedfilterSKU")
		public ResponseEntity<List<Integer>> changedFilterSKU(@RequestBody com.ygroup.model.req.changedFilter list) {
			return new ResponseEntity<>(service.changedFilterSKU(list), HttpStatus.OK);
		}
		
		
		@PostMapping(value = "/changedfilterCPG")
		public ResponseEntity<List<String>> changedFilterCPG(@RequestBody com.ygroup.model.req.changedFilter list) {
			return new ResponseEntity<>(service.changedFilterCPG(list), HttpStatus.OK);
		}
		
		
		@PostMapping(value = "/forecastingGroup12")
		public ResponseEntity<List<String>> forecastingGroup_list() {
			return new ResponseEntity<>(service.forecastingGroup_List(), HttpStatus.OK);
		}
		
		
		
//		@PostMapping(value = "/changedfilterCPG")
//		public void changedFilterCPG(@RequestBody com.fusionhawk.model.req.changedFilter list) {
//			System.out.println("Start Time--------"+System.currentTimeMillis());
//			//SavePlanReq savePlanReq = null;
//			service.changedFilterCPG(list);
//		}
//		
		
		//After the user clicks on the Save plan, it takes up all the comments and make swap that temporary every week's data to Final Data
		@PostMapping(value = "/confirmPlanData")
		public String confirmPlanData(@RequestBody List<SavePlanReq> savePlanReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			
			//List<SavePlanReq> savePlanReq = null;
			String message = service.confirmPlanData(savePlanReq);
			return message;
		}
		
		
		@PostMapping(value = "/editcomment")
		public String editcomment(@RequestBody EditComment editcomment) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			
			//List<SavePlanReq> savePlanReq = null;
			List<String> message = service.editComment(editcomment);
			return null;
		}
		
		
		// A Post request with request data of All the customer planning Group, Forecasting Group and Plants, saved as their favourite SKU
		@PostMapping(value = "/saveFilter")
		public String saveFilter(@RequestBody SaveFilterReq saveFilterReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			String message = service.saveFilter(saveFilterReq);
			return message;
		}
		
		
		
		
		// FROM TO SKU
		
	
		
		
		
		@PostMapping(value = "/savePIPOsku")
		public String savePIPO(@RequestBody PIPOMapping saveFilterReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			String message = service.savePIPOSKU(saveFilterReq);
			return message;
		}
		
		
		
		
		@PostMapping(value = "/mapFG")
		public String mapFG(@RequestBody mapFGreq saveFilterReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			String message = service.mapFG(saveFilterReq);
			return message;
		}
		
		
		
		
		
		
		
		@PostMapping(value = "/prevnull")
		public String prevnull(@RequestBody default_filter_res saveLogReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			System.out.println("Harshit1233--"+saveLogReq.toString());
			String message = service.change_filter_prev_null(saveLogReq);
			return message;
		}
		
		
		
		
		@PostMapping(value = "/setdefault")
		public String set_default1(@RequestBody default_filter_res saveLogReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			System.out.println("Harshit1233--"+saveLogReq.toString());
			String message = service.set_default(saveLogReq);
			return message;
		}
		
		
		// A Post request with request data user, responds with all the saved filters
		@PostMapping(value = "/fetchFilter")
		public ResponseEntity<List<FetchFilterListRes>> fetchFilter() {
			return new ResponseEntity<>(service.fetchFilter(), HttpStatus.OK);
		}
		
		
		@PostMapping(value = "/fetchpipoMapping")
		public ResponseEntity<List<PIPOMapping>> fetchPIPO() {
			return new ResponseEntity<>(service.fetchPIPO(), HttpStatus.OK);
		}
		
		
		
		
		@PostMapping(value = "/pipo")
		public ResponseEntity<List<PIPOEntity>> fetchpipo() {
			return new ResponseEntity<>(service.fetchpipo(), HttpStatus.OK);
		}
		
		
		// A Post request with request data user, responds with all the saved filters
		@PostMapping(value = "/logs")
		public ResponseEntity<List<LogResponse>> fetchlogs() {
			return new ResponseEntity<>(service.fetchlogs(), HttpStatus.OK);
		}
		
		
		// A Post request with request data user, responds with all the saved filters
		@PostMapping(value = "/allcomments")
		public ResponseEntity<List<String>> fetchComments() {
			return new ResponseEntity<>(service.fetchcomments(), HttpStatus.OK);
		}
		
		
		
		
		// To save a view, with a request of Final Forecast, ML, APO etc. and enters into the MySQL Database
		@PostMapping(value = "/saveView")
		public String saveView(@RequestBody SaveViewReq saveViewReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			String message = service.saveView(saveViewReq);
			return message;
		}
		
		// To fetch list of views, for a particular user, with a request of Final Forecast, ML, APO etc. and enters into the MySQL Database
		@PostMapping(value = "/fetchView")
		public ResponseEntity<List<FetchViewListRes>> fetchView() {
			return new ResponseEntity<>(service.fetchView(), HttpStatus.OK);
		}
		
		
		// It is used to delete Temp data whatever left over in the cache.
		@PostMapping(value = "/deleteTempData")
		public String deleteTempData() {
		
			
			//List<SavePlanReq> savePlanReq = null;
			String message = service.deleteTempData();
			return message;
		}

}
