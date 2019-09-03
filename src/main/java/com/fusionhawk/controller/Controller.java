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

import com.fusionhawk.model.req.DemandTableReq;

import com.fusionhawk.model.req.ForecastingGroupsReq;
import com.fusionhawk.model.req.SaveFilterReq;
import com.fusionhawk.model.req.SavePlanReq;
import com.fusionhawk.model.req.SaveViewReq;
import com.fusionhawk.model.res.FetchFilterListRes;
import com.fusionhawk.model.res.FetchViewListRes;
import com.fusionhawk.model.res.FilterListRes;
import com.fusionhawk.model.res.GraphRes;
import com.fusionhawk.service.FusionhawkService;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

	@Autowired
	private FusionhawkService service;

	@GetMapping(value = "/ping")
	public String getCurrentTimeStamp() {
		return "Application recieved ping on " + new Date().toString();
	}

	@GetMapping(value = "/brands")
	public ResponseEntity<List<String>> getBrands() {
		return new ResponseEntity<>(service.getBrands(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/plants")
	public ResponseEntity<List<String>> getPlants() {
		return new ResponseEntity<>(service.getPlants(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/unitPerPack")
	public ResponseEntity<List<String>> getunitPerPack() {
		return new ResponseEntity<>(service.getunitPerPack(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/alcoholPercentage")
	public ResponseEntity<List<String>> getalcoholpercentage() {
		return new ResponseEntity<>(service.getalcoholpercentage(), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/subBrand")
	public ResponseEntity<List<String>> getsubbrand() {
		return new ResponseEntity<>(service.getsubbrand(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@GetMapping(value = "/cpg")
	public ResponseEntity<List<String>> getCPGs() {
		return new ResponseEntity<>(service.getCPGs(), HttpStatus.OK);
	}

	@PostMapping(value = "/forecastingGroup")
	public ResponseEntity<List<String>> getForecastingGroups(@RequestBody ForecastingGroupsReq forecastingGroupsReq) {
		return new ResponseEntity<>(service.getForecastingGroups(forecastingGroupsReq), HttpStatus.OK);
	}

	@GetMapping(value = "/filters")
	public ResponseEntity<FilterListRes> getFiltersList() {
		return new ResponseEntity<>(service.getFiltersList(), HttpStatus.OK);
	}

	@PostMapping(value = "/demandTable")
	public ResponseEntity<GraphRes> getDemandTable(@RequestBody DemandTableReq demandTableReq) {
		return new ResponseEntity<>(service.getDemandTable(demandTableReq), HttpStatus.OK);
	}
	
	
	
	
	@PostMapping(value = "/demandTable_monthly")
	public ResponseEntity<GraphRes> getDemandTable_monthly(@RequestBody DemandTableReq demandTableReq) {
		return new ResponseEntity<>(service.getDemandTable_monthly(demandTableReq), HttpStatus.OK);
	}
	
	//abhik
		@GetMapping(value = "/Cache")
		public void getCacheTable() {
			service.getCacheTable();
		}
		
		@PostMapping(value = "/savePlanData")
		public void savePlanData(@RequestBody SavePlanReq savePlanReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//SavePlanReq savePlanReq = null;
			service.savePlanData(savePlanReq);
		}
		
		@PostMapping(value = "/confirmPlanData")
		public String confirmPlanData(@RequestBody List<SavePlanReq> savePlanReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			
			//List<SavePlanReq> savePlanReq = null;
			String message = service.confirmPlanData(savePlanReq);
			return message;
		}
		
		@PostMapping(value = "/saveFilter")
		public String saveFilter(@RequestBody SaveFilterReq saveFilterReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			String message = service.saveFilter(saveFilterReq);
			return message;
		}
		
		@PostMapping(value = "/fetchFilter")
		public ResponseEntity<List<FetchFilterListRes>> fetchFilter() {
			return new ResponseEntity<>(service.fetchFilter(), HttpStatus.OK);
		}
		
		@PostMapping(value = "/saveView")
		public String saveView(@RequestBody SaveViewReq saveViewReq) {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			//List<SavePlanReq> savePlanReq = null;
			String message = service.saveView(saveViewReq);
			return message;
		}
		
		@PostMapping(value = "/fetchView")
		public ResponseEntity<List<FetchViewListRes>> fetchView() {
			return new ResponseEntity<>(service.fetchView(), HttpStatus.OK);
		}
		
		@PostMapping(value = "/deleteTempData")
		public String deleteTempData() {
			System.out.println("Start Time--------"+System.currentTimeMillis());
			
			//List<SavePlanReq> savePlanReq = null;
			String message = service.deleteTempData();
			return message;
		}

}
