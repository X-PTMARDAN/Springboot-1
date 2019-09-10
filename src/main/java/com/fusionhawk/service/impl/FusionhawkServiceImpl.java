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
	
	@Override
	public List<String> getBrands() {
		return repository.fetchBrands();
	}

	@Override
	public List<String> getPlants() {
		return repository.fetchPlants();
	}
	
	
	@Override
	public List<String> getunitPerPack() {
		return repository.fetchunitPerPack();
	}
	
	
	
	@Override
	public List<String> getalcoholpercentage() {
		return repository.fetchalcoholpercentage();
	}
	
	
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

	

	@Override
	public List<String> getCPGs() {
		return repository.fetchCPGs();
	}
	
	public void test()
	{
		
	}

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
		
		//

	

		
		
//		else if (brandListReq.isEmpty() && subbrandListReq.isEmpty() && unitsPerPack.isEmpty())
//		{
//			System.out.println("Harshit->");
//			return repository.fetchForecastingGroups_brand_subbrand_unit();
//		}
		
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
		
			//jatin
		//Checks if any Filter is marked or not 
//				//If not returns all ForeCasting Groups
//				if (req.getFilterBrands().isEmpty() && req.getFilterSubBrandName().isEmpty()  
//						&& req.getFilterAcoholPerc().isEmpty() && req.getFilterUnitsPerPack().isEmpty())
//					return repository.fetchForecastingGroups();
//				else {/*
//					String regexp = "^".concat(brandListReq.get(0));
//					if (brandListReq.size() > 1) {
//						for (int i = 1; i < brandListReq.size(); i++) {
//							regexp = regexp.concat("|^").concat(brandListReq.get(i));
//						}
//					}
//					return repository.fetchForecastingGroupsByBrands(regexp);*/
//					return repository.fetchForecastingGroupsByBrands(req.getFilterSubBrandName(),req.getFilterUnitsPerPack(),req.getFilterUnitsPerPack(),req.getFilterBrands());
//				}
	
	}

	@Override
	public FilterListRes getFiltersList() {
		List<String> filterListCPG = repository.fetchFilterListCPG();
		List<String> filterListPlants = repository.fetchFilterListPlants();
		List<String> filterListPackaging = repository.fetchFilterListPackaging();
		FilterListRes response = new FilterListRes();

		List<Filter> filterList = new ArrayList<Filter>();
		Filter filter = new Filter();
		filter.setName("Customer Planning Group");
		filter.setKey("customerPlanningGroup");
		filter.setValues(filterListCPG);
		filterList.add(filter);

		filter = new Filter();
		filter.setName("Plant");
		filter.setKey("plants");
		filter.setValues(filterListPlants);
		filterList.add(filter);

		filter = new Filter();
		filter.setName("SKUS");
		filter.setKey("leadSKU");
		filter.setValues(filterListPackaging);
		filterList.add(filter);

		response.setFilters(filterList);
		return response;
	}

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
		
		
		
		String sqlQuery_1 = BeaconRepository.fetchFeatureTable1;
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
		
		
		
		String sqlQuery_1 = BeaconRepository.fetchFeatureTable1;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Monthly one Harshit 
	
	
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
		
		
		
		String sqlQuery_1 = BeaconRepository.fetchFeatureTable1;
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
		

	


		String sqlQuery = BeaconRepository.fetchFeatureTable1;
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

	
	
	
	// abhik
	
	
	
	
	
	
	// Harshit CHANGE//
	
	
//	@Override
//	public GraphRes getDemandTable_monthly(DemandTableReq demandTableReq) {
//		GraphRes response = new GraphRes();
//		response.setReq(demandTableReq);
//		Integer startWeek = demandTableReq.getStartWeek();
//		Integer endWeek = demandTableReq.getEndWeek();
//		Integer prevYearStartWeek = startWeek - 100;
//		Integer prevYearEndWeek = endWeek - 100;
//		Integer weekNumber = startWeek % 100;
//		startWeek = startWeek - weekNumber;
//		int x = 0;
//		if (weekNumber > 24) {
//			weekNumber = weekNumber - 24;
//		} else {
//			weekNumber = 52 - (24 - weekNumber);
//			x = 100;
//		}
//		startWeek = (startWeek - x) + weekNumber;
//
//		// For actuals previous year
//		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByMonths(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);
//
//		// For fva, finalforecast values of input weeks
//		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByMonths(demandTableReq.getForecastingGroups(),
//				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
//				endWeek);
//
//		// For comments of input weeks
//		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments_ByMonth(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
//		
//		
//		// For Brands
//				List<String> brands = repository.fetchBrands_filters(demandTableReq.getForecastingGroups());
//				response.getReq().setBrands(brands);
//				
//				
//				
//				
//
//		// For -24 weeks
//		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
//				.fetchDemandTablePrevMonths(demandTableReq.getForecastingGroups(),
//						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
//						demandTableReq.getStartWeek() - 1, 0);
//
//		Type listType = new TypeToken<List<DemandTableRes>>() {
//		}.getType();
//
//		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
//				listType);
//
//		// For input weeks
////		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
////				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
////				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
//		String sqlQuery = BeaconRepository.fetchDemandTableQuery_Month;
//		List<DemandTableRes> currYearDemandList = null;
//		try {
//			currYearDemandList = em.createNativeQuery(sqlQuery)
//					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
//					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
//					.setParameter("plantList", demandTableReq.getPlants())
//					.setParameter("startWeek", demandTableReq.getStartWeek())
//					.setParameter("endWeek", endWeek)
//					.setParameter("x", 0)
//					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
//		} catch (Exception e) {
//			log.info("Exception occurred Hawww", e);
//		}
//		
//		
//		
//		
//			System.out.println("CHECK121--->"+currYearDemandList.toString());
//			
//			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				curr.setActuals(null);
//			}
//			
//		
//		
//		
//		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
//		
//
//		
//		
//
//		for (DemandTableRes currDemand : currYearDemandList) {
//			for (UserPlanRes currUser : currYearUserList) {
//				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
//					currDemand.setFinalforecast(currUser.getFinalForecast());
//					currDemand.setFva(currUser.getFva());
//				}
//			}
//		}
//		
//		
//		
//		
//
//		for (DemandTableRes currYear : currYearDemandList) {
//			for (DemandTableRes prevYear : prevYearDemandList) {
//				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
//					currYear.setActualslastyear(prevYear.getActuals());
//				}
//			}
//		}
//
//		for (DemandTableRes currDemand : currYearDemandList) {
//			for (UserCommentsRes commentObj : userComments) {
//				if (currDemand.getCalenderYearWeek() == commentObj.getCalendarWeek()) {
//					List<String> currCommentList = currDemand.getComment();
//					if (currCommentList == null) {
//						currCommentList = new ArrayList<String>();
//					}
//					currCommentList.add(commentObj.getComments2());
//					currDemand.setComment(currCommentList);
//				}
//			}
//		}
//		
//		
//		System.out.println("CHECK121--->"+currYearDemandList.toString());
//		
//		
//		
//		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
//		
////		currYea
//		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
//		response.setRes(currYearDemandPrevMonthsDemandList);
//		
//		
//		System.out.println("TEHRJKFF->"+response.toString());
//		return response;
//	}
//	
//	
//	
//	
//	
//	
//	
	
	
	

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
					
					
					System.out.println("CHECK1-->"+savePlanEntity.getUser());
					System.out.println("CHECK2-->"+savePlanEntity.getCalendarWeek());
					System.out.println("CHECK3-->"+savePlanEntity.getCpg());
					System.out.println("CHECK4-->"+savePlanEntity.getSku());
					System.out.println("CHECK5-->"+savePlanEntity.getPlant());
					
					
					System.out.println("CHECK512345678-->"+savePlanReq.getFva());
					
					
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
					
					
					
					
					System.out.println("CHECKING123-->"+currentML);
					
					System.out.println("CHECKING1234-->"+totalML);
					
					
					System.out.println("CHECKING12345-->"+savePlanReq.getFinalForecast());
					
					
					System.out.println("CHECKING1234567-->"+(currentML / totalML)* savePlanReq.getFinalForecast());
					
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

	public String confirmPlanData(List<SavePlanReq> savePlanReq) {

		
		System.out.println("CHECK-->"+savePlanReq.toString());
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
			
			System.out.println("REIBURSE-->"+savePlanReqs.toString());
			for (String combination : combinations) {

				String combinationKey = savePlanReqs.getCalendarWeek() + combination;
				String combinationKeyWithUser = savePlanReqs.getUser() + combinationKey;
				System.out.println(combinationKeyWithUser);
				combinationKeyWithUserList.add(combinationKeyWithUser);
				//savePlanEntity = new SavePlanEntity();
				/*
				 * savePlanEntity.setPk_combination(combinationKeyWithUser);
				 * savePlanEntity.setComments1(savePlanReqs.getComments1());
				 * savePlanEntity.setComments2(savePlanReqs.getComments2());
				 * savePlanEntityList.add(savePlanEntity);
				 */
				//savePlanEntity = savePlanRepository.findOne(savePlanReqs.getUser() + combinationKey);
				/*
				 * if (null != savePlanEntity) {
				 * savePlanEntity.setComments1(savePlanReqs.getComments1());
				 * savePlanEntity.setComments2(savePlanReqs.getComments2()); if
				 * (savePlanEntity.isTempValue()) {
				 * savePlanEntity.setFinalForecast(savePlanEntity.getFinalForecastTemp());
				 * savePlanEntity.setTempValue(false); savePlanEntity.setFinalForecastTemp(0d);
				 * } savePlanRepository.save(savePlanEntity); }
				 */
				
			}
			List<SavePlanEntity> savePlanEntity = savePlanRepository.fetchSavePlanTableByKey(combinationKeyWithUserList);
			
			try {
			System.out.println("THE123451234->"+savePlanReqs.getComments1());
			}catch(Exception e)
			{
				
			}
			
			
			System.out.println("RETYUKJHGF#$%^&*->"+savePlanEntity.toString());
			
			try {

				for (SavePlanEntity savePlanEntityUnit : savePlanEntity) {
					
					System.out.println("THE12345->"+savePlanReqs.getComments1());
					
					
					
					savePlanEntityUnit.setComments1(savePlanReqs.getComments1());
					savePlanEntityUnit.setComments2(savePlanReqs.getComments2());
				}
			
			}catch(Exception e)
			{
				
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

	public String saveFilter(SaveFilterReq saveFilterReq) {

//		saveFilterReq = new SaveFilterReq();
//		saveFilterReq.setCpg("cpg3,cpg4");
//		saveFilterReq.setUser("User");
//		saveFilterReq.setFilterName("filtername1");
//		saveFilterReq.setPlant("P3,P4");
//		saveFilterReq.setSku("sku3,sku4");

		FilterEntity filterEntity = new FilterEntity();

		filterEntity.setCpg(saveFilterReq.getCpg());
		filterEntity.setFilterName(saveFilterReq.getFilterName());
		filterEntity.setUser(saveFilterReq.getUser());
		filterEntity.setSku(saveFilterReq.getSku());
		filterEntity.setPlant(saveFilterReq.getPlant());

		filterRepository.save(filterEntity);
		// List<FilterEntity> filterEntity =
		// filterRepository.findByUserAndFilterName("user", "filter");
		return "Filter Saved";
	}

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

	
}
