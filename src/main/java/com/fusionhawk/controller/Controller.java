package com.fusionhawk.controller;

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

import com.fusionhawk.model.req.CPGreq;
import com.fusionhawk.model.req.DemandTableReq;
import com.fusionhawk.model.req.EditComment;
import com.fusionhawk.model.req.ForecastingGroupsReq;
import com.fusionhawk.model.req.SaveFilterReq;
import com.fusionhawk.model.req.SaveLogReq;
import com.fusionhawk.model.req.SavePlanReq;
import com.fusionhawk.model.req.SaveViewReq;
import com.fusionhawk.model.res.FetchFilterListRes;
import com.fusionhawk.model.res.FetchViewListRes;
import com.fusionhawk.model.res.FilterListRes;
import com.fusionhawk.model.res.GraphRes;
import com.fusionhawk.model.res.LogResponse;
import com.fusionhawk.model.res.featureAnalysisRes;
import com.fusionhawk.model.res.featureGraphRes;
import com.fusionhawk.service.FusionhawkService;

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
	private FusionhawkService service;

	
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
	
	
	@PostMapping(value = "/animalFlag")
	public ResponseEntity<List<String>> getAnimalFlag() {
		return new ResponseEntity<>(service.getAnimalFlag(), HttpStatus.OK);
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
	
	
	
	@GetMapping(value = "/trade")
	public ResponseEntity<List<String>> getTradetype() {
		return new ResponseEntity<>(service.gettradetype(), HttpStatus.OK);
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
	
	
	@PostMapping(value = "/demandTable_UOM")
	public ResponseEntity<GraphRes> getDemandTable_UOM(@RequestBody DemandTableReq demandTableReq) {
		service.deleteTempData();
		return new ResponseEntity<>(service.getDemandTable_UOM(demandTableReq), HttpStatus.OK);
	}
	

	
	
    // It responds with the feature analysis graph results
	// Takes startweek, endweek, customer planning group, Forecasting group, plants as the request
	@PostMapping(value = "/demandTable_feature_analysis")
	public ResponseEntity<featureGraphRes> getFeatureAnalysis(@RequestBody DemandTableReq demandTableReq) {
		//service.getFeatureAnalysis(demandTableReq);
	//	return null;
		return new ResponseEntity<>(service.getFeatureAnalysis(demandTableReq), HttpStatus.OK);
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
		public void savePlanData(@RequestBody SavePlanReq savePlanReq) {
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
		public ResponseEntity<List<String>> changedFilterSKU(@RequestBody com.fusionhawk.model.req.changedFilter list) {
			return new ResponseEntity<>(service.changedFilterSKU(list), HttpStatus.OK);
		}
		
		
		@PostMapping(value = "/changedfilterCPG")
		public ResponseEntity<List<String>> changedFilterCPG(@RequestBody com.fusionhawk.model.req.changedFilter list) {
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
		
		
		
		
		@PostMapping(value = "/savelog")
		public String saveLog(@RequestBody SaveLogReq saveLogReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			System.out.println("Harshit1233--"+saveLogReq.toString());
			String message = service.saveLog(saveLogReq);
			return message;
		}
		
		
		// A Post request with request data user, responds with all the saved filters
		@PostMapping(value = "/fetchFilter")
		public ResponseEntity<List<FetchFilterListRes>> fetchFilter() {
			return new ResponseEntity<>(service.fetchFilter(), HttpStatus.OK);
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
