package com.fusionhawk.service.impl;

import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fusionhawk.controller.*;
import com.fusionhawk.entity.*;

import com.fusionhawk.model.req.*;
import com.fusionhawk.model.res.DemandTableRes;

import com.fusionhawk.service.*;




import com.fusionhawk.config.Transformer;
import com.fusionhawk.entity.CacheTableEntity;
import com.fusionhawk.entity.FilterEntity;
import com.fusionhawk.entity.SavePlanEntity;
import com.fusionhawk.entity.ViewEntity;
import com.fusionhawk.model.req.DemandTableReq;
import com.fusionhawk.model.req.ForecastingGroupsReq;
import com.fusionhawk.model.req.SaveFilterReq;
import com.fusionhawk.model.req.SavePlanReq;
import com.fusionhawk.model.req.SaveViewReq;
import com.fusionhawk.model.res.AuroriPrevMonths;
import com.fusionhawk.model.res.DemandTableRes;
import com.fusionhawk.model.res.DemandTableResponse_Updated;
import com.fusionhawk.model.res.FetchFilterListRes;
import com.fusionhawk.model.res.FetchViewListRes;
import com.fusionhawk.model.res.FilterListRes;
import com.fusionhawk.model.res.FilterListRes.Filter;
import com.fusionhawk.model.res.GraphRes;
import com.fusionhawk.model.res.UserCommentsRes;
import com.fusionhawk.model.res.UserPlanRes;
import com.fusionhawk.model.res.featureAnalysisRes;
import com.fusionhawk.model.res.featureGraphRes;
import com.fusionhawk.repository.AuroriPrevMonthsRepository;
import com.fusionhawk.repository.BeaconRepository;
import com.fusionhawk.repository.CacheRepository;
import com.fusionhawk.repository.FilterRepository;
import com.fusionhawk.repository.SavePlanRepository;
import com.fusionhawk.repository.UserCommentRepository;
import com.fusionhawk.repository.UserPlanRepository;
import com.fusionhawk.repository.ViewRepository;
import com.fusionhawk.service.FusionhawkService;

import lombok.extern.slf4j.Slf4j;

// Thats the main implementation Class, where all the methods have their implementation

@Service
@Slf4j
public class FusionhawkServiceImpl implements FusionhawkService {

	@Autowired
	private BeaconRepository repository;

	@Autowired
	private UserPlanRepository userRepository;

	@Autowired
	private UserCommentRepository userCommentRepository;

	@Autowired
	private AuroriPrevMonthsRepository auroriPrevMonthsRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@PersistenceContext
	EntityManager em;

	// abhik
	@Autowired
	private CacheRepository cacheRepository;

	@Autowired
	private SavePlanRepository savePlanRepository;

	@Autowired
	private FilterRepository filterRepository;

	@Autowired
	private ViewRepository viewRepository;
	
	
	
	// fetch all the distinct brands from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getBrands() {
		return repository.fetchBrands();
	}

	// fetch all the distinct plants from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getPlants() {
		return repository.fetchPlants();
	}
	

	// fetch all the distinct unitPerPack from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getunitPerPack() {
		return repository.fetchunitPerPack();
	}
	
	

	// fetch all the distinct alcohol percentage from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getalcoholpercentage() {
		return repository.fetchalcoholpercentage();
	}
	
	
	// fetch all the distinct SubBrand from the MySQL aggregated Table
		// Query written in BeaconRepository
	@Override
	public List<String> getsubbrand() {
		
		System.out.println("tg->"+repository.fetchsubbrand().toString());
		return repository.fetchsubbrand();
	}
	
	
	
//	@Override
//	public List<String> () {
//		// TODO Auto-generated method stub
//		return repository.fetchalcoholpercentage();
//	}
//	

	
  
	// fetch all the distinct CPGs from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getCPGs() {
		return repository.fetchCPGs();
	}

	
	

	
	/*
	 * GetDemandTable 
	 * 
	 * {
	 * "startWeek":201938,"endWeek":202003,
	 * "forecastingGroups":["Grimb Blonde BOT 4X6X0_25 ","Bilz PanachÃ© CAN 4X6X0_33 ","RhÃ¤z BOT 20X0_40 CrtR mCO2"],
	 * "customerPlanningGroup":["G01"],
	 * "plants":["G011"]
	 * }
	 * 
	 * startWeek - from where the user needs to plan
	 * endWeek -  endWeek for the plan (Horizon)
	 * prevYearStartWeek  - prev year start week(for Actuals Last year)
	 * prevYearEndWeek - prev year end week(for Actuals Last year)
	 * 
	 * Using these values data is being fetched from Database |  FeatureAnalysis per week(GroupBy calender_week) 
	 * 
	 * currYearDemandList_featureAnalysis - is the response for feature Analysis graph
	 * 
	 * 
	 * 
	 * USING THE SAME Start Week, End Week, CPG, Forecasting Group, Plant
	 * 
	 * We fetch ML, APO and integrate it into currYearDemandList 
	 * 
	 * 
	 * 
	 * prevYearDemandList -  This is the response for Actuals Last Year, Where the startweek same week's previour year.
	 * 
	 * 
	 * currYearUserList - It Fetch the value from Saved Plan table(plan_data) for a specific ML, CPG, Plant and Week (if available)
	 * 
	 * 
	 * 
	 * userComments -It fetch the comments for all Selected SKU. 
	 * 
	 * currYearAuroriPrevMonthsDemandList - Last 24 week's data. Which consists of only Actuals data
	 * 
	 * 
	 * 
	 * 
	 * Then we aggregate all the values based on the calendar_YearWeek
	 * for eg. if Forecast value add available for a specific week then its added to the currYearDemandList JSON. 
	 * 
	 * 
	 * Aggregated table in the form of GraphRes is formed and sent to FrontEnd
	 * 
	 * 
	 */

	@Override
	public GraphRes getDemandTable(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		Integer prevYearEndWeek = endWeek - 100;
		Integer weekNumber = startWeek % 100;
		startWeek = startWeek - weekNumber;
		int x = 0;
		if (weekNumber > 24) {
			weekNumber = weekNumber - 24;
		} else {
			weekNumber = 52 - (24 - weekNumber);
			x = 100;
		}
		startWeek = (startWeek - x) + weekNumber;
		
		
		
		String sqlQuery_1 = BeaconRepository.fetchFeatureTable_featureAnalysis;
		List<featureAnalysisRes> currYearDemandList_featureAnalysis = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			currYearDemandList_featureAnalysis = em.createNativeQuery(sqlQuery_1)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		// For Brands
				List<String> brands = repository.fetchBrands_filters(demandTableReq.getForecastingGroups());
				response.getReq().setBrands(brands);
				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = BeaconRepository.fetchDemandTableQuery;
		List<DemandTableRes> currYearDemandList = null;
		try {
			currYearDemandList = em.createNativeQuery(sqlQuery)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("CHECK121--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		

		for (DemandTableRes currYear : currYearDemandList) {
			for (DemandTableRes prevYear : prevYearDemandList) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
				}
			}
		}

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserCommentsRes commentObj : userComments) {
				if (currDemand.getCalenderYearWeek() == commentObj.getCalendarWeek()) {
					List<String> currCommentList = currDemand.getComment();
					if (currCommentList == null) {
						currCommentList = new ArrayList<String>();
					}
					currCommentList.add(commentObj.getComments2());
					currDemand.setComment(currCommentList);
				}
			}
		}
		
		
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		response.setRes(currYearDemandPrevMonthsDemandList);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	// IMPLEMENTATION EXACTLY SAME LIKE PREVIOUS METHOD
	// THE DIFFERENCE IS IN THIS METHOD THE SQL QUERY IS GROUPED BY CALENDER YEAR MONTH
	
	
	@Override
	public GraphRes getDemandTable_monthly(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		Integer prevYearEndWeek = endWeek - 100;
		Integer weekNumber = startWeek % 100;
		startWeek = startWeek - weekNumber;
		int x = 0;
		if (weekNumber > 24) {
			weekNumber = weekNumber - 24;
		} else {
			weekNumber = 52 - (24 - weekNumber);
			x = 100;
		}
		startWeek = (startWeek - x) + weekNumber;
		
		
		
		String sqlQuery_1 = BeaconRepository.fetchFeatureTable_featureAnalysis;
		List<featureAnalysisRes> currYearDemandList1 = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			currYearDemandList1 = em.createNativeQuery(sqlQuery_1)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("23456--->"+currYearDemandList1.toString());
			
			
			response.setSecondGraphRes(currYearDemandList1);

		// For actuals previous year
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByMonths(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByMonths(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments_Monthly(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		// For Brands
				List<String> brands = repository.fetchBrands_filters(demandTableReq.getForecastingGroups());
				response.getReq().setBrands(brands);
				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevMonthly(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = BeaconRepository.fetchDemandTableQuery_Month;
		List<DemandTableRes> currYearDemandList = null;
		try {
			currYearDemandList = em.createNativeQuery(sqlQuery)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("CHECK121--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		

		for (DemandTableRes currYear : currYearDemandList) {
			for (DemandTableRes prevYear : prevYearDemandList) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
				}
			}
		}

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserCommentsRes commentObj : userComments) {
				if (currDemand.getCalenderYearWeek() == commentObj.getCalendarWeek()) {
					List<String> currCommentList = currDemand.getComment();
					if (currCommentList == null) {
						currCommentList = new ArrayList<String>();
					}
					currCommentList.add(commentObj.getComments2());
					currDemand.setComment(currCommentList);
				}
			}
		}
		
		
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		response.setRes(currYearDemandPrevMonthsDemandList);
		return response;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// YEARLY VIEW 
	
	// IMPLEMENTATION SAME AS PREVIOUS METHOD
	// THE DIFFERENCE is ITS for the 
	
	
	@Override
	public GraphRes getDemandTable_yearly(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		Integer prevYearEndWeek = endWeek - 100;
		Integer weekNumber = startWeek % 100;
		startWeek = startWeek - weekNumber;
		int x = 0;
		if (weekNumber > 24) {
			weekNumber = weekNumber - 24;
		} else {
			weekNumber = 52 - (24 - weekNumber);
			x = 100;
		}
		startWeek = (startWeek - x) + weekNumber;
		
		
		
		String sqlQuery_1 = BeaconRepository.fetchFeatureTable_featureAnalysis;
		List<featureAnalysisRes> currYearDemandList1 = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			currYearDemandList1 = em.createNativeQuery(sqlQuery_1)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("23456--->"+currYearDemandList1.toString());
			
			
			response.setSecondGraphRes(currYearDemandList1);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		// For Brands
				List<String> brands = repository.fetchBrands_filters(demandTableReq.getForecastingGroups());
				response.getReq().setBrands(brands);
				
				
				
				

//		// For -24 weeks
//		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
//				.fetchDemandTablePrevWeeks(demandTableReq.getForecastingGroups(),
//						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
//						demandTableReq.getStartWeek() - 1, 0);

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

//		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
//				listType);

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = BeaconRepository.fetchDemandTableQuery;
		List<DemandTableRes> currYearDemandList = null;
		try {
			currYearDemandList = em.createNativeQuery(sqlQuery)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("CHECK121--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		

		for (DemandTableRes currYear : currYearDemandList) {
			for (DemandTableRes prevYear : prevYearDemandList) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
				}
			}
		}

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserCommentsRes commentObj : userComments) {
				if (currDemand.getCalenderYearWeek() == commentObj.getCalendarWeek()) {
					List<String> currCommentList = currDemand.getComment();
					if (currCommentList == null) {
						currCommentList = new ArrayList<String>();
					}
					currCommentList.add(commentObj.getComments2());
					currDemand.setComment(currCommentList);
				}
			}
		}
		
		
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
	//	System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
	//	currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		response.setRes(currYearDemandList);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	/*
	 * 
	 * SIMILAR TO DEMAND GRAPH METHOD 
	 * 
	 * 
	 * {
	 * "startWeek":201938,"endWeek":202003,
	 * "forecastingGroups":["Grimb Blonde BOT 4X6X0_25 ","Bilz PanachÃ© CAN 4X6X0_33 ","RhÃ¤z BOT 20X0_40 CrtR mCO2"],
	 * "customerPlanningGroup":["G01"],
	 * "plants":["G011"]
	 * }
	 * 
	 * Whenever you change the drop down for different options Feature Analysis graph 
	 * It just changes the Feature Analysis Graph
	 * 
	 * currYearDemandList_Analysis fetches the data from the aggregated table about temperature, Open orders etc..
	 * 
	 * Similar to Demand Table method it takes start week,endWeek, CPG, plants, Forecasting group etc and provides the sum of 
	 * value per week accordingly
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	@Override
	public featureGraphRes getFeatureAnalysis(DemandTableReq demandTableReq) {
		
		
		
		featureGraphRes response1 = new featureGraphRes();
		//response.setReq(demandTableReq);
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		Integer prevYearEndWeek = endWeek - 100;
		Integer weekNumber = startWeek % 100;
		startWeek = startWeek - weekNumber;
		
		System.out.println("WAQT THAM SA GYA");
		int x = 0;
		if (weekNumber > 24) {
			weekNumber = weekNumber - 24;
		} else {
			weekNumber = 52 - (24 - weekNumber);
			x = 100;
		}
		startWeek = (startWeek - x) + weekNumber;

		// For actuals previous year
		

	


		String sqlQuery = BeaconRepository.fetchFeatureTable_featureAnalysis;
		List<featureAnalysisRes> currYearDemandList1 = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			currYearDemandList1 = em.createNativeQuery(sqlQuery)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		
			System.out.println("23456--->"+currYearDemandList1.toString());
			
	
		
		

		
		
		
		//System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		//currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		//response.setRes(currYearDemandList);
		//response1.setRes(currYearDemandList1);
			
			response1.setSecondGraphRes(currYearDemandList1);
		return response1;
	}

	
	
	
	
	
	
	
	
	//
	
	
	@Override
	public List<String> getForecastingGroups(ForecastingGroupsReq req) {
		List<String> a = req.getFilterBrands();
		
		
		List<String> b = req.getFilterSubBrandName();
		
		
		List<String> c = req.getFilterUnitsPerPack();
		
		List<String> d = req.getFilterAcoholPerc();
		
		
		System.out.println("CHECK--->"+a.isEmpty());
		System.out.println("CHECK--->"+b.isEmpty());
		System.out.println("CHECK--->"+c.isEmpty());
		System.out.println("CHECK--->"+d.isEmpty());
		String mainQuery = " WHERE 1 ";
		if (a.isEmpty() && b.isEmpty() && c.isEmpty() && d.isEmpty())
		{
			System.out.println("Harshit->");
			return repository.fetchForecastingGroups();
		}
		
		else {
		
					 if(a.isEmpty() && b.isEmpty() && c.isEmpty())
					{
						 
						 String regexp;
					System.out.println("d");
						regexp = "^".concat(d.get(0));
						if (d.size() > 1) {
							for (int i = 1; i < d.size(); i++) {
								regexp = regexp.concat("|^").concat(d.get(i));
							}
						}
						
						return repository.fetchForecastingGroups_d(regexp);
						
						
						//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
						
						
						
					}
					 
					 
					 else if(a.isEmpty() && b.isEmpty() && d.isEmpty())
						{
						 
						 String regexp;
						System.out.println("c");
							regexp = "^".concat(c.get(0));
							if (c.size() > 1) {
								for (int i = 1; i < c.size(); i++) {
									regexp = regexp.concat("|^").concat(c.get(i));
								}
							}
							
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
							
							return repository.fetchForecastingGroups_c(regexp);
							
						}
					 
					 
					 
					 else if(a.isEmpty() && c.isEmpty() && d.isEmpty())
						{
						 String regexp;
						System.out.println("b");
							regexp = "^".concat(b.get(0));
							if (b.size() > 1) {
								for (int i = 1; i < b.size(); i++) {
									regexp = regexp.concat("|^").concat(b.get(i));
								}
							}
							
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
							
							return repository.fetchForecastingGroups_b(regexp);
							
							
							
						}
					 
					 
					 
					 else if(b.isEmpty() && c.isEmpty() && d.isEmpty())
						{
						 String regexp;
						System.out.println("a");
							regexp = "^".concat(a.get(0));
							if (a.size() > 1) {
								for (int i = 1; i < a.size(); i++) {
									regexp = regexp.concat("|^").concat(a.get(i));
								}
							}
							
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
							
							return repository.fetchForecastingGroups_a(regexp);
							
							
							
						}
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(a.isEmpty() && b.isEmpty())
						{
						 
						 String regexp,regexp1;
							System.out.println("cd");
								regexp = "^".concat(c.get(0));
								if (c.size() > 1) {
									for (int i = 1; i < c.size(); i++) {
										regexp = regexp.concat("|^").concat(c.get(i));
									}
								}
								
								
								
								
								System.out.println("cd");
								regexp1 = "^".concat(d.get(0));
								if (d.size() > 1) {
									for (int i = 1; i < d.size(); i++) {
										regexp1 = regexp1.concat("|^").concat(d.get(i));
									}
								}
								
								
								
								return repository.fetchForecastingGroups_cd(regexp,regexp1);
								
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
							
							
							
						}
					 
					 
					 
					 
					 
					 
					 else if(a.isEmpty() && c.isEmpty())
						{
						 
						 String regexp,regexp1;
							System.out.println("bd");
								regexp = "^".concat(b.get(0));
								if (b.size() > 1) {
									for (int i = 1; i < b.size(); i++) {
										regexp = regexp.concat("|^").concat(b.get(i));
									}
								}
								
								
								
								
								System.out.println("bd");
								regexp1 = "^".concat(d.get(0));
								if (d.size() > 1) {
									for (int i = 1; i < d.size(); i++) {
										regexp1 = regexp1.concat("|^").concat(d.get(i));
									}
								}
								
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
								
								return repository.fetchForecastingGroups_bd(regexp,regexp1);
							
							
							
						}
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(a.isEmpty() && d.isEmpty())
						{
						 String regexp,regexp1;
							System.out.println("bc");
								regexp = "^".concat(b.get(0));
								if (b.size() > 1) {
									for (int i = 1; i < b.size(); i++) {
										regexp = regexp.concat("|^").concat(b.get(i));
									}
								}
								
								
								
								
								System.out.println("bc");
								regexp1 = "^".concat(c.get(0));
								if (c.size() > 1) {
									for (int i = 1; i < c.size(); i++) {
										regexp1 = regexp1.concat("|^").concat(c.get(i));
									}
								}
								
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
								
								
								return repository.fetchForecastingGroups_bc(regexp,regexp1);
							
							
							
						}
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(b.isEmpty() && d.isEmpty())
						{
						 
						 String regexp,regexp1;
							System.out.println("ac");
								regexp = "^".concat(a.get(0));
								if (a.size() > 1) {
									for (int i = 1; i < a.size(); i++) {
										regexp = regexp.concat("|^").concat(a.get(i));
									}
								}
								
								
								
								
								System.out.println("ac");
								regexp1 = "^".concat(c.get(0));
								if (c.size() > 1) {
									for (int i = 1; i < c.size(); i++) {
										regexp1 = regexp1.concat("|^").concat(c.get(i));
									}
								}
								
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
								
								
								return repository.fetchForecastingGroups_ac(regexp,regexp1);
							
							
							
						}
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(b.isEmpty() && c.isEmpty())
						{
						 
						 String regexp,regexp1;
							System.out.println("ad");
								regexp = "^".concat(a.get(0));
								if (a.size() > 1) {
									for (int i = 1; i < a.size(); i++) {
										regexp = regexp.concat("|^").concat(a.get(i));
									}
								}
								
								
								
								
								System.out.println("ad");
								regexp1 = "^".concat(d.get(0));
								if (d.size() > 1) {
									for (int i = 1; i < d.size(); i++) {
										regexp1 = regexp1.concat("|^").concat(d.get(i));
									}
								}
								
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
								
								
								
								return repository.fetchForecastingGroups_ad(regexp,regexp1);
							
							
							
						}
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(c.isEmpty() && d.isEmpty())
						{
						 String regexp,regexp1;
							System.out.println("ab");
								regexp = "^".concat(a.get(0));
								if (a.size() > 1) {
									for (int i = 1; i < a.size(); i++) {
										regexp = regexp.concat("|^").concat(a.get(i));
									}
								}
								
								
								
								
								System.out.println("ab");
								regexp1 = "^".concat(b.get(0));
								if (b.size() > 1) {
									for (int i = 1; i < b.size(); i++) {
										regexp1 = regexp1.concat("|^").concat(b.get(i));
									}
								}
								
							//mainQuery+=" AND Brand REGEXP "+ regexp+" ";
								
								
								return repository.fetchForecastingGroups_ab(regexp,regexp1);
							
							
							
						}
					 
					 else if(a.isEmpty())
					{
						 
						 
						 String subbrand,subbrand1,subbrand2;
						 System.out.println("bcd");
						subbrand = "^".concat(b.get(0));
						if (b.size() > 1) {
							for (int i = 1; i < b.size(); i++) {
								subbrand = subbrand.concat("|^").concat(b.get(i));
							}
						}
						
						
						
						
						
						 System.out.println("bcd");
							subbrand1 = "^".concat(c.get(0));
							if (c.size() > 1) {
								for (int i = 1; i < c.size(); i++) {
									subbrand1 = subbrand1.concat("|^").concat(c.get(i));
								}
							}
							
							
							
							 System.out.println("bcd");
								subbrand2 = "^".concat(d.get(0));
								if (d.size() > 1) {
									for (int i = 1; i < d.size(); i++) {
										subbrand2 = subbrand2.concat("|^").concat(d.get(i));
									}
								}
						
					
								return repository.fetchForecastingGroups_bcd(subbrand,subbrand1,subbrand2);
						
					}
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(b.isEmpty())
					{
						 String subbrand,subbrand1,subbrand2;
						 System.out.println("acd");
						subbrand = "^".concat(a.get(0));
						if (a.size() > 1) {
							for (int i = 1; i < a.size(); i++) {
								subbrand = subbrand.concat("|^").concat(a.get(i));
							}
						}
						
						
						
						
						
						 System.out.println("acd");
							subbrand1 = "^".concat(c.get(0));
							if (c.size() > 1) {
								for (int i = 1; i < c.size(); i++) {
									subbrand1 = subbrand1.concat("|^").concat(c.get(i));
								}
							}
							
							
							
							 System.out.println("acd");
								subbrand2 = "^".concat(d.get(0));
								if (d.size() > 1) {
									for (int i = 1; i < d.size(); i++) {
										subbrand2 = subbrand2.concat("|^").concat(d.get(i));
									}
								}
						
								return repository.fetchForecastingGroups_acd(subbrand,subbrand1,subbrand2);

						
						
					}
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(c.isEmpty())
						{
						 String subbrand,subbrand1,subbrand2;
							 System.out.println("abd");
							subbrand = "^".concat(a.get(0));
							if (a.size() > 1) {
								for (int i = 1; i < a.size(); i++) {
									subbrand = subbrand.concat("|^").concat(a.get(i));
								}
							}
							
							
							
							
							
							 System.out.println("abd");
								subbrand1 = "^".concat(b.get(0));
								if (b.size() > 1) {
									for (int i = 1; i < b.size(); i++) {
										subbrand1 = subbrand1.concat("|^").concat(b.get(i));
									}
								}
								
								
								
								 System.out.println("abd");
									subbrand2 = "^".concat(d.get(0));
									if (d.size() > 1) {
										for (int i = 1; i < d.size(); i++) {
											subbrand2 = subbrand2.concat("|^").concat(d.get(i));
										}
									}
							
						
									return repository.fetchForecastingGroups_abd(subbrand,subbrand1,subbrand2);

							
						}
					 
					 
					 
					 
					 
					 
					 
					 
					 
					 else if(d.isEmpty())
						{
						 String subbrand,subbrand1,subbrand2;
							 System.out.println("abc");
							subbrand = "^".concat(a.get(0));
							if (a.size() > 1) {
								for (int i = 1; i < a.size(); i++) {
									subbrand = subbrand.concat("|^").concat(a.get(i));
								}
							}
							
							
							
							
							
							 System.out.println("abc");
								subbrand1 = "^".concat(b.get(0));
								if (b.size() > 1) {
									for (int i = 1; i < b.size(); i++) {
										subbrand1 = subbrand1.concat("|^").concat(b.get(i));
									}
								}
								
								
								
								 System.out.println("abc");
									subbrand2 = "^".concat(c.get(0));
									if (c.size() > 1) {
										for (int i = 1; i < c.size(); i++) {
											subbrand2 = subbrand2.concat("|^").concat(c.get(i));
										}
									}
							
						
									return repository.fetchForecastingGroups_abc(subbrand,subbrand1,subbrand2);

							
						}
					 
					 
					 else {
						 
						 String subbrand,subbrand1,subbrand2,subbrand3;
						
								 System.out.println("abcd");
								subbrand = "^".concat(a.get(0));
								if (a.size() > 1) {
									for (int i = 1; i < a.size(); i++) {
										subbrand = subbrand.concat("|^").concat(a.get(i));
									}
								}
								
								
								
								
								
								 System.out.println("abcd");
									subbrand1 = "^".concat(b.get(0));
									if (b.size() > 1) {
										for (int i = 1; i < b.size(); i++) {
											subbrand1 = subbrand1.concat("|^").concat(b.get(i));
										}
									}
									
									
									
									 System.out.println("abcd");
										subbrand2 = "^".concat(c.get(0));
										if (c.size() > 1) {
											for (int i = 1; i < c.size(); i++) {
												subbrand2 = subbrand2.concat("|^").concat(c.get(i));
											}
										}
								
										
										
										
										
										 System.out.println("abcd");
											subbrand3 = "^".concat(d.get(0));
											if (d.size() > 1) {
												for (int i = 1; i < d.size(); i++) {
													subbrand3 = subbrand3.concat("|^").concat(d.get(i));
												}
											}
									
							
								
											return repository.fetchForecastingGroups_abcd(subbrand,subbrand1,subbrand2,subbrand3);

							
					 }
					 

					 
					 
					 
					// return repository.fetchForecastingGroups_Updated(mainQuery);
		
		
		}	
	}
	
	

	
	
	
	/*
	 * 
	 * 
	 * It fetches all the data from the database and store in cache, so that performance can be increase
	 * 
	 * 
	 */
	@Override
	public List<CacheTableEntity> getCacheTable() {
		// TODO Auto-generated method stub
		List l = new LinkedList();
		l.add("Week1sku1cpg1P1");
		l.add("Week1sku1cpg1P2");
		List<CacheTableEntity> l1 = cacheRepository.fetchCacheTableByKey(l);
		System.out.print(l1.toString());
		return null;
	}

	@Override
	public List<CacheTableEntity> getCacheTable(List<String> selectFromCache) {

		List<CacheTableEntity> cacheTableEntity = cacheRepository.fetchCacheTableByKey(selectFromCache);
		//System.out.pr
		return cacheTableEntity;
	}

	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * savePlanData, Whenever a user logs in forecast value add it get stored in a Temp column in the database,based on SKU, CPG, Plant and paricular week
	 * after calculating the ML ratio, 
	 * 
	 * 
	 * 
	 * A Sample response. 
	 * 
	 * {"fg":["G02"],
	 * "plant":["G012"],"sku":["Bilz PanachÃ© CAN 4X6X0.33 ","Bilz PanachÃ© BOT 10X0.33 ","Bilz PanachÃ© PET 4X6X0.50 "],
	 * "user":"admin","finalForecast":100,"fva":100,"calendarWeek":201940,"comments1":"comment1"}
	 * 
	 * 
	 * 1-so in the first part, based on the sku, plant, cpg and that paricular week, we have added different combinations to the linkedlist
	 * that is done by creating a linked list based on plants, cpg, sku
	 * 
	 * as cacheTable has a column pk_combination 
	 * 
	 * for G01 as cpg, G001 as plant Grimbergen as SKU and 201938 as calender_yearweek - pk_combination be Grimbergen G001 G01 201938
	 * 
	 * Then after adding pk_combination in the database it fetches all the cache data (Data we kept, just to make the performance fast)
	 * and it fetch the ML of the combination and divide with the total ML so as to find the ratio and save.
	 * 
	 * 
	 * and then save that linked list in the plan_data
	 * 
	 * 
	 * 
	 * NOTE ALL THE DATA HERE IS STORED IN TEMPERORY COLUMN
	 */

	@Override
	public void savePlanData(SavePlanReq savePlanReq) {

//		savePlanReq = new SavePlanReq();
//		savePlanReq.setCalendarWeek(201935);
//		savePlanReq.setComments1("Comments1");
//		savePlanReq.setComments2("Comments2");
		List<String> cpg = savePlanReq.getCpg();
		
		
		System.out.println("CHECKZ1->"+cpg.toString());
//		cpg.add("cpg1");
//		cpg.add("cpg2");
//		savePlanReq.setCpg(cpg);
		List<String> plant = savePlanReq.getPlant();
		System.out.println("CHECKZ22->"+plant.toString());
//		plant.add("P1");
//		plant.add("P2");
//		savePlanReq.setPlant(plant);
		List<String> sku = savePlanReq.getSku();
		
		
		System.out.println("CHECKZ33->"+sku.toString());
//		sku.add("sku1");
//		sku.add("sku2");
//		savePlanReq.setSku(sku);
//		savePlanReq.setUser("User");
//		savePlanReq.setFinalForecast(1010);
//		savePlanReq.setFva(10);
		List<String> fetchedSku = repository.fetchDemandTableByFG(sku);
		sku = fetchedSku;
		List<String> skuListSelectFromCache = new LinkedList<String>();
		for (String skus : sku) {
			for (String cpgs : cpg) {
				for (String plants : plant) {
					skuListSelectFromCache.add(savePlanReq.getCalendarWeek() + skus + cpgs + plants);
					//System.out.println("'"+savePlanReq.getCalendarWeek() + skus + cpgs + plants+"',");
				}
			}
		}
		System.out.println("----------------------------------------------------");
		// List<SavePlanEntity> planTableResponse =
		// savePlanRepository.fetchSavePlanTableByKey(skuListSelectFromCache);
		List<CacheTableEntity> cacheTableEntity = getCacheTable(skuListSelectFromCache);
		Map<String, Double> cacheTableResponseMap = new HashMap<String, Double>();
		Double totalML = 0d;
		/*
		 * for (SavePlanEntity planTableResponses : planTableResponse) {
		 * cacheTableResponseMap.put(planTableResponses.getPk_combination(),
		 * planTableResponses.getFinalForecast()); totalML = totalML +
		 * planTableResponses.getFinalForecast(); }
		 */

		for (CacheTableEntity cacheTableResponse : cacheTableEntity) {
			/* if(null == cacheTableResponseMap.get(cacheTableResponse.getKey())) { */
			
			
			cacheTableResponseMap.put(cacheTableResponse.getKey(), cacheTableResponse.getML_Value());
			totalML = totalML + cacheTableResponse.getML_Value();
			// }
		}
		
		System.out.println("______----------________");
		long start = System.currentTimeMillis();
		SavePlanEntity savePlanEntity = null;
List<SavePlanEntity> savePlanEntityList = new ArrayList<SavePlanEntity>();

		for (String skus : sku) {
			for (String cpgs : cpg) {
				for (String plants : plant) {
					//long start = System.currentTimeMillis();
					/*
					 * savePlanEntity = savePlanRepository .findOne(savePlanReq.getUser() +
					 * savePlanReq.getCalendarWeek() + skus + cpgs + plants);
					 */
					savePlanEntity = null;
					//if (null == savePlanEntity) {
						savePlanEntity = new SavePlanEntity();
						savePlanEntity.setPk_combination(
								savePlanReq.getUser() + savePlanReq.getCalendarWeek() + skus + cpgs + plants);

					//}
						if("admin201935Grimb Blonde BOT 4X6X0.25 FopNG02G012".equals(savePlanReq.getUser() + savePlanReq.getCalendarWeek() + skus + cpgs + plants)) {
							System.out.println("Debug");
						}
						if("admin201935Grimb Blonde KEG 1X20.00 RG02G012".equals(savePlanReq.getUser() + savePlanReq.getCalendarWeek() + skus + cpgs + plants)) {
							System.out.println("Debug");
						}
						if("admin201935Grimb Blonde KEG 1X20.00 RG18G012".equals(savePlanReq.getUser() + savePlanReq.getCalendarWeek() + skus + cpgs + plants)) {
							System.out.println("Debug");
						}
						
					savePlanEntity.setUser(savePlanReq.getUser());
					
				
					savePlanEntity.setCalendarWeek(savePlanReq.getCalendarWeek());
					savePlanEntity.setSku(skus);
					savePlanEntity.setCpg(cpgs);
					savePlanEntity.setPlant(plants);
					

					
					double currentML = 0d;
					//System.out.println(savePlanReq.getCalendarWeek() + skus + cpgs + plants);
					System.out.println("CHECK4545->"+cacheTableResponseMap.get(savePlanReq.getCalendarWeek() + skus + cpgs + plants));
					
					int calendar_yearMonth;
					
					System.out.println("UJU->"+cpgs+"--"+plants+"---"+savePlanReq.getCalendarWeek()+"---"+skus);
					
					try {
					
					 calendar_yearMonth=repository.fetchcalendarMonth(cpgs,plants,savePlanReq.getCalendarWeek(),skus);
					}catch(Exception e)
					{
						calendar_yearMonth=0;
					}
					System.out.println("DEBUG->"+calendar_yearMonth);
					
					System.out.println("DEBUG12->"+skus);
					if(null == cacheTableResponseMap.get(savePlanReq.getCalendarWeek() + skus + cpgs + plants)) {
						
						currentML = 0d;
					} else {
						currentML = cacheTableResponseMap.get(savePlanReq.getCalendarWeek() + skus + cpgs + plants);
					}
					
					
					
					savePlanEntity.setCalendar_yearMonth(calendar_yearMonth);

					if(currentML==0.0 && totalML==0.0)
					{
						System.out.println("HOOO GYAA");
						//savePlanEntity.setFva(savePlanReq.getFva());
						
						savePlanEntity.setFinalForecastTemp(savePlanReq.getFinalForecast()/fetchedSku.size());
					}
					else {
				
					
					savePlanEntity.setFinalForecastTemp((currentML / totalML)* savePlanReq.getFinalForecast());
					}
					
					
					savePlanEntity.setFinalForecast(0d);
					//savePlanEntity.setComments1(savePlanReq.getComments1());
					//savePlanEntity.setComments2(savePlanReq.getComments2());
					savePlanEntity.setTempValue(true);
					
					if(currentML==0.0 && totalML==0.0)
					{
						System.out.println("HOOO GYAA");
						savePlanEntity.setFva(savePlanReq.getFva()/fetchedSku.size());
					}
					else {
					savePlanEntity.setFva(
							(currentML / totalML)
									* savePlanReq.getFva());
					}

					//savePlanRepository.save(savePlanEntity);
					
					try {
					System.out.println("2345678-->"+savePlanEntityList.toString());
					savePlanEntityList.add(savePlanEntity);
					}catch(Exception e)
					{
						System.out.println("Harshit-->"+savePlanEntityList.toString());
					}

					

					//long end = System.currentTimeMillis();
					//System.out.println("Time taken --------" + (end - start)/100);

				}
			}
		}
		
		savePlanRepository.save(savePlanEntityList);
		long end = System.currentTimeMillis();

		System.out.println("Done" + (end-start)/1000);
		/*
		 * long start = System.currentTimeMillis();
		 *  
		 * long end =	 System.currentTimeMillis(); 
		 * System.out.println("Done" + (end-start)/1000);
		 */

	}

	
	
	
	/*
	 * 
	 * 
	 * 
	 * THIS METHOD IS INVOKED WHEN THE USER CLICKS ON SAVE A PLAN.
	 * using the pk_combination(sku+cpg+plant+week) 
	 *
	 * in the first part we make a linked list of the combination available (REQUEST DATA)
	 * and then based on that combination it saves the comment - as the combination has sku so comments are stored at SKU level
	 * 
	 * 
	 * then it iterate over the array of combination and for every combination the temperory data is changed to final forecast data
	 * 
	 * 
	 */
	public String confirmPlanData(List<SavePlanReq> savePlanReq) {

		List<SavePlanEntity> savePlanEntityList = new ArrayList<SavePlanEntity>();
		SavePlanReq savePlanReqUnit = savePlanReq.get(0);
		List<String> skuList = savePlanReqUnit.getSku();
		List<String> cpgList = savePlanReqUnit.getCpg();
		List<String> plantList = savePlanReqUnit.getPlant();
		List<String> fetchedSku = repository.fetchDemandTableByFG(skuList);
		skuList = fetchedSku;

		List<String> combinations = new LinkedList<String>();
		
		for (String skus : skuList) {
			for (String cpgs : cpgList) {
				for (String plants : plantList) {
					combinations.add(skus + cpgs + plants);

				}
			}
		}

		//SavePlanEntity savePlanEntity = null;
		List<String> combinationKeyWithUserList = new LinkedList<String>();
		for (SavePlanReq savePlanReqs : savePlanReq) {
			
			for (String combination : combinations) {

				String combinationKey = savePlanReqs.getCalendarWeek() + combination;
				String combinationKeyWithUser = savePlanReqs.getUser() + combinationKey;
				System.out.println(combinationKeyWithUser);
				combinationKeyWithUserList.add(combinationKeyWithUser);
				
			}
			List<SavePlanEntity> savePlanEntity = savePlanRepository.fetchSavePlanTableByKey(combinationKeyWithUserList);
			
			
			
				
			
			try {

				for (SavePlanEntity savePlanEntityUnit : savePlanEntity) {		
					savePlanEntityUnit.setComments1(savePlanReqs.getComments1());
					savePlanEntityUnit.setComments2(savePlanReqs.getComments2());
				}
			
			}catch(Exception e)
			{
				System.out.println("No comments");
			}
			
			savePlanEntityList.addAll(savePlanEntity);
			combinationKeyWithUserList = new LinkedList<String>();
		}
		long start = System.currentTimeMillis();
		savePlanRepository.save(savePlanEntityList);
		long end = System.currentTimeMillis();

		System.out.println("Done" + (end-start)/1000);
		start = System.currentTimeMillis();
		List<SavePlanEntity> savePlanEntityToConfirm = savePlanRepository.fetchDataToConfirm("admin");
		
		
		System.out.println("Harshit1344-->"+savePlanEntityToConfirm);
		for (SavePlanEntity savePlanEntity : savePlanEntityToConfirm) {
			savePlanEntity.setFinalForecast(savePlanEntity.getFinalForecastTemp());
			savePlanEntity.setTempValue(false);
		}
		savePlanRepository.save(savePlanEntityToConfirm);
		end = System.currentTimeMillis();
		System.out.println("Done" + (end-start)/1000);
		return "Plan Saved";

	}

	
	
	/*
	 * 
	 * Method used to save the favourite Filter(Plant, CPG, Forecasting Group)
	 * 
	 * 
	 */
	public String saveFilter(SaveFilterReq saveFilterReq) {

		FilterEntity filterEntity = new FilterEntity();

		filterEntity.setCpg(saveFilterReq.getCpg());
		filterEntity.setFilterName(saveFilterReq.getFilterName());
		filterEntity.setUser(saveFilterReq.getUser());
		filterEntity.setSku(saveFilterReq.getSku());
		filterEntity.setPlant(saveFilterReq.getPlant());

		filterRepository.save(filterEntity);

		return "Filter Saved";
	}

	
	
	

	/*
	 * 
	 * Whenever a user logs in, it fetch all the favourite filers(Plant, CPG, Forecasting Group) for that particular user. 
	 * 
	 * 
	 * 
	 * 
	 */
	@Override
	public List<FetchFilterListRes> fetchFilter() {
		List<FetchFilterListRes> fetchFilterListResList = new LinkedList<FetchFilterListRes>();
		FetchFilterListRes fetchFilterListRes = null;
		List<FilterEntity> filterEntity = filterRepository.findByUser("admin");
		for (FilterEntity filterEntitys : filterEntity) {
			fetchFilterListRes = new FetchFilterListRes();
			fetchFilterListRes.setName(filterEntitys.getFilterName());
			List<String> skuList = Arrays.asList(filterEntitys.getSku().split("\\s*,\\s*"));
			// List<String> skuList = Arrays.asList(skuArray);

			List<String> cpgList = Arrays.asList(filterEntitys.getCpg().split("\\s*,\\s*"));
			List<String> plantList = Arrays.asList(filterEntitys.getPlant().split("\\s*,\\s*"));
			fetchFilterListRes.setSku(skuList);
			fetchFilterListRes.setCpg(cpgList);
			fetchFilterListRes.setPlant(plantList);
			fetchFilterListResList.add(fetchFilterListRes);
		}
		return fetchFilterListResList;
	}
	
	
	
	
	
	
	
	/*
	 * 
	 * Whenever a user logs in, it fetch all the saved views for that particular user. 
	 * 
	 * 
	 * 
	 * 
	 */

	@Override
	public List<FetchViewListRes> fetchView() {
		List<FetchViewListRes> fetchViewListResList = new LinkedList<FetchViewListRes>();
		FetchViewListRes fetchViewListRes = null;
		List<ViewEntity> viewEntity = viewRepository.findByUser("admin");
		for (ViewEntity viewEntitys : viewEntity) {
			fetchViewListRes = new FetchViewListRes();
			fetchViewListRes.setName(viewEntitys.getViewName());
			fetchViewListRes.setStartWeek(viewEntitys.getStartWeek());
			fetchViewListRes.setEndWeek(viewEntitys.getEndWeek());
			fetchViewListRes.setName(viewEntitys.getViewName());
			List<String> skuList = Arrays.asList(viewEntitys.getSku().split("\\s*,\\s*"));
			// List<String> skuList = Arrays.asList(skuArray);

			List<String> cpgList = Arrays.asList(viewEntitys.getCpg().split("\\s*,\\s*"));
			List<String> plantList = Arrays.asList(viewEntitys.getPlant().split("\\s*,\\s*"));
			List<String> weeklyFinalForecast = Arrays.asList(viewEntitys.getWeeklyFinalForecast().split("\\s*,\\s*"));
			fetchViewListRes.setWeeklyFinalForecast(weeklyFinalForecast);
			fetchViewListRes.setSku(skuList);
			fetchViewListRes.setCpg(cpgList);
			fetchViewListRes.setPlant(plantList);
			fetchViewListResList.add(fetchViewListRes);
		}
		return fetchViewListResList;
	}
	
	
	
	/*
	 * 
	 * Method used to save the favourite Filter(Plant, CPG, Forecasting Group)
	 * 
	 * 
	 */
	

	@Override
	public String saveView(SaveViewReq saveViewReq) {
		// TODO Auto-generated method stub
		ViewEntity viewEntity = new ViewEntity();
		
		
		System.out.print("harshit"+saveViewReq.toString());

		viewEntity.setCpg(saveViewReq.getCpg());
		viewEntity.setViewName(saveViewReq.getViewName());
		viewEntity.setUser(saveViewReq.getUser());
		viewEntity.setSku(saveViewReq.getSku());
		viewEntity.setPlant(saveViewReq.getPlant());
		viewEntity.setWeeklyFinalForecast(saveViewReq.getWeeklyFinalForecast());
		viewEntity.setStartWeek(saveViewReq.getStartWeek());
		viewEntity.setEndWeek(saveViewReq.getEndWeek());
		viewEntity.setPlant(saveViewReq.getPlant());
		
		viewRepository.save(viewEntity);
		// List<FilterEntity> filterEntity =
		// filterRepository.findByUserAndFilterName("user", "filter");
		return "View Saved";
	}
	
	
	/*
	 * 
	 * Method used to delete the temperory data for a particular user (here - admin)
	 * 
	 * 
	 */
	public String deleteTempData() {
		
		System.out.println("Start Time12345678--------"+System.currentTimeMillis());
		
		List<SavePlanEntity> savePlanEntityToDelete = savePlanRepository.fetchDataToConfirm("admin");
		for (SavePlanEntity savePlanEntity : savePlanEntityToDelete) {
			savePlanEntity.setFinalForecastTemp(0);
			savePlanEntity.setTempValue(false);
		}
		
		savePlanRepository.save(savePlanEntityToDelete);
		return "Temp Data Deleted";
	}

	@Override
	public FilterListRes getFiltersList() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
