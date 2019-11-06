package com.fusionhawk.service.impl;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusionhawk.service.FusionhawkService;
import com.ygroup.config.Transformer;
import com.ygroup.config.Transformer_feature;
import com.ygroup.entity.CacheTableEntity;
import com.ygroup.entity.FilterEntity;
import com.ygroup.entity.PIPOEntity;
import com.ygroup.entity.PIPOMapping;
import com.ygroup.entity.SavePlanEntity;
import com.ygroup.entity.ViewEntity;
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
import com.ygroup.model.res.AuroriPrevMonths;
import com.ygroup.model.res.DemandTableRes;
import com.ygroup.model.res.FetchFilterListRes;
import com.ygroup.model.res.FetchViewListRes;
import com.ygroup.model.res.FilterListRes;
import com.ygroup.model.res.GraphRes;
import com.ygroup.model.res.LogResponse;
import com.ygroup.model.res.UserCommentsRes;
import com.ygroup.model.res.UserPlanRes;
import com.ygroup.model.res.UserPlanRes_UOM;
import com.ygroup.model.res.downLoadPlan;
import com.ygroup.model.res.featureAnalysisRes;
import com.ygroup.model.res.featureGraphRes;
import com.ygroup.model.res.mappingResp;
import com.ygroup.model.res.oneRowPlan;
import com.ygroup.repository.AuroriPrevMonth_UOM_REPO;
import com.ygroup.repository.AuroriPrevMonthsRepository;
import com.ygroup.repository.BeaconRepository;
import com.ygroup.repository.CacheRepository;
import com.ygroup.repository.FilterRepository;
import com.ygroup.repository.LogRepo;
import com.ygroup.repository.MappingRepo;
import com.ygroup.repository.PIPOMappingRepo;
import com.ygroup.repository.SavePlanRepository;
import com.ygroup.repository.UOMRepo;
import com.ygroup.repository.UserCommentRepository;
import com.ygroup.repository.UserPlanRepo_UOM;
import com.ygroup.repository.UserPlanRepository;
import com.ygroup.repository.ViewRepository;
import com.ygroup.repository.downloadPlan;
import com.ygroup.repository.pipoRepo;

import lombok.extern.slf4j.Slf4j;

// Thats the main implementation Class, where all the methods have their implementation

@Service
@Slf4j
public class FusionhawkServiceImpl implements FusionhawkService {

	@Autowired
	private BeaconRepository repository;
	
	

	@Autowired
	private UOMRepo uomRepo;
	
	
	@Autowired
	private PIPOMappingRepo pipoMapping;
	
	
	@Autowired
	private downloadPlan download12;

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
	private pipoRepo piporepo;
	
	
	@Autowired
	private com.ygroup.repository.pipoSKU piposku;

	@Autowired
	private ViewRepository viewRepository;
	
	
	@Autowired
	private LogRepo logRepo;
	
	
	@Autowired
	private AuroriPrevMonth_UOM_REPO aurorirepo;
	
	@Autowired
	private UserPlanRepo_UOM userplan_uom;
	
	
	
	
	
	
	@Autowired
	private MappingRepo mappingRepo;
//	
//	@Autowired
//	private LogRepository logRepository;
	
	
	
	
	
	// fetch all the distinct brands from the MySQL aggregated Table
//	// Query written in BeaconRepository
//	@Override
//	public List<String> getBrands() {
//		return repository.fetchBrands();
//	}
	
	// Query written in BeaconRepository
	@Override
	public List<String> getBrands() {
		//List<String> a=["Cardinal","Bilz PanachÃ©","FeldschlÃ¶sschen","EVE","Somersby"];
		
		String[] array = {"Cardinal","Bilz PanachÃ©","FeldschlÃ¶sschen","EVE","Somersby"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		//return repository.fetchBrands();
	}

	// fetch all the distinct plants from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getPlants() {
		
		String[] array = {"G001","G012","G011","G013","G018","G015","G002","G016","G017","G014"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
	
		
	//	return repository.fetchPlants();
	}
	

	// fetch all the distinct unitPerPack from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getAnimalFlag() {
		
		String[] array = {"HORSE","MULE","MAD BULL","JACK RABBIT","MAMMOTH++","MAMMOTH","KANGAROO","PUPPY"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
	
		//return repository.fetchanimal();
	}
	
	
	@Override
	public List<String> getPackType() {
		return repository.fetch_packsize();
	}
	
	

	// fetch all the distinct alcohol percentage from the MySQL aggregated Table
	// Query written in BeaconRepository
	@Override
	public List<String> getalcoholpercentage() {
		
		
		String[] array = {"0.0","0.45","0.5","2.0","2.4","3.1","4.3","4.5","4.6","4.7","4.8","5.0","5.2","5.3","5.4","5.5","5.9","6.0","7.0"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
		//return repository.fetchalcoholpercentage();
	}
	
	
	
	@Override
	public List<String> editComment(EditComment data) {
		
		String key=data.getKey();
		String com = data.getData();
		userCommentRepository.editCommentSQL(com,key);
		return null;
	}
	
	
	// fetch all the distinct SubBrand from the MySQL aggregated Table
		// Query written in BeaconRepository
	@Override
	public List<String> getsubbrand() {
		
		
		
		
		String[] array = {"Cardinal SpÃ©ciale","Bilz PanachÃ©","FeldschlÃ¶sschen 2.4","FeldschlÃ¶sschen Alkoholfrei L","EVE Litchi","EVE Passion Fruit","Eve Strawberry Mojito","FeldschlÃ¶sschen BÃ¼gel","FeldschlÃ¶sschen Amber","EVE Fruit Spritzer Litchi & Ap","EVE Fruit Spritzer Passionsfru","EVE Lemon Twist","Somersby Apple Cider","EVE Grapefruit Cosmopolitan","EVE various","FeldschlÃ¶sschen Braufrisch","Somersby Apple","Cardinal Rousse","FeldschlÃ¶sschen Dunkel","FeldschlÃ¶sschen Weizen","FeldschlÃ¶sschen Hopfenperle","FeldschlÃ¶sschen Original","FeldschlÃ¶sschen Hopfen","Cardinal Draft Original","Cardinal Draft","Cardinal Lime Cut","FeldschlÃ¶sschen Frucht Panach","FeldschlÃ¶sschen Premium","FeldschlÃ¶sschen Naturfrisch","FeldschlÃ¶sschen FrÃ¼hlingsbie","Cardinal Blonde","FeldschlÃ¶sschen Stark","Cardinal Brunette","FeldschlÃ¶sschen PanachÃ©","Eve Caipirinha","Cardinal Weihnachtsbier","FeldschlÃ¶sschen Weihnachtsbie","FeldschlÃ¶sschen Weihnachtsges","FeldschlÃ¶sschen Winterbier","EVE Wild Orange","Cardinal Vodka & Citrus","Eve Tea Yellow Tea & Mango","Eve Tea White Tea & Litchi","FeldschlÃ¶sschen Bio Lager","FeldschlÃ¶sschen Alkoholfrei","FeldschlÃ¶sschen various","Eve Hugo","Somersby Red Rhubarb","Somersby Elderflower Lime","FeldschlÃ¶sschen Pale Ale","FeldschlÃ¶sschen Alkoholfrei W","FeldschlÃ¶sschen Hopfen/ Dunke","Somersby Apple/ElderflowerLime","Cardinal Summer Beer","Cardinal Blanche","Somersby Elderflower Lime-Red ","Cardinal Blonde/Blanche","FeldschlÃ¶sschen ICE","FeldschlÃ¶sschen Lehrlingsbier","Cardinal BiÃ¨re d'automne"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
	//	System.out.println("tg->"+repository.fetchsubbrand().toString());
	//	return repository.fetchsubbrand();
	}
	
	
	
	@Override
	public List<String> getsalesoffice() {
		
		
		
		String[] array = {"KAM On Trade","Independent On Trade","Wholesaler","Off Trade","Other","Intercompany"};

	    List<String> list = Arrays.asList(array);
	    
	    return list;
	    
	    
	    
	//	System.out.println("tg->"+repository.fetchsales().toString());
	//	return repository.fetchsales();
	}
	
	
	@Override
	public List<String> gettradetype() {
		
		
		String[] array = {"On-trade","Third Party","Off-trade","Various Sales"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
		
		
		
	//	System.out.println("tg->"+repository.fetchtrade().toString());
	//	return repository.fetchtrade();
	}
	
	
	
	@Override
	public List<String> forecastingGroup_List() {
		
		//System.out.println("tg->"+repository.fetchtrade().toString());
		return repository.forecastingGroup();
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
		
		
		
		String[] array = {"G04 - CH Aldi","G05 - CH Aligro","G06 - CH Cadar","G07 - CH Coop","G09 - CH Denner SD","G11 - CH Landi","G12 - CH Lekkerland","G13 - CH LeShop","G14 - CH Manor","G15 - CH migrolino","G16 - CH Migros","G17 - CH OTTO's","G18 - CH Rio GetrÃ¤nke","G19 - CH CC Aligro","G20 - CH SDH / Convenience","G21 - CH Spar/maxi/TopCC","G22 - CH transGourmet","G23 - CH Ã¼brige KAM 3","G24 - CH Valora AG","G26 - CH Volg","G30 - CH Lidl","G01 - CH Ind. On Trade","G02 - CH KAM On Trade","G03 - CH Wholesaler","G27 - CH NebengeschÃ¤ft","G28 - CH Export"};


	//String[] array = {"G04","G05","G06","G07","G09","G11","G12","G13","G14","G15","G16","G17","G18","G19","G20","G21","G22","G23","G24","G26","G30","G01","G02","G03","G27","G28"};

		
			    List<String> list = Arrays.asList(array);
	    
	    return list;
		
		//return repository.fetchCPGs();
	}

	
	
	
	// Query written in BeaconRepository
	@Override
	public List<String> getSales() {
		
		
		String[] array = {"KAM On Trade","Independent On Trade","Wholesaler","Off Trade","Other","Intercompany"};

	    List<String> list = Arrays.asList(array);
	    
	    return list;
		//return repository.fetchsalesoffice();
	}
	
	
	@Override
	public int getmaxweek() {
		
		
//		String[] array = {"KAM On Trade","Independent On Trade","Wholesaler","Off Trade","Other","Intercompany"};
//
//	    List<String> list = Arrays.asList(array);
//	    
//	    return list;
		return repository.getmaxweek();
	}
	
	
	
	
	
	
	
	
	@Override
	public List<String> getTradetype() {
		
		String[] array = {"On-trade","Third Party","Off-trade","Various Sales"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
		//return repository.fetchtradetype();
	}

	
	@Override
	public List<Integer> getForecastingid() {
		return repository.fetchforecastingid();
	}
	
	
	@Override
	public List<String> getForecastingGroup() {
		return repository.fetchforecastinggroup();
	}
	
	
	
	
	@Override
	public List<String> getmaterialgroup() {
		
		
		String[] array = {"Own Produced","Distribution License"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
		
	//	return repository.fetchmaterial();
	}

	
	@Override
	public List<String> getglobalbevcat() {
		
		String[] array = {"Core Beer","NAB","Craft & Speciality","Cider"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
		
		
		
	//	return repository.fetch_global_bev_cat();
		
	}
	
	
	@Override
	public List<String> getbaseunit() {
		
		
		
		String[] array =	{"L","KEG","CAR","CRT","TRA","QPL","HPL","PAL"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
	
		//return repository.fetch_base();
		
	}
	
	
	@Override
	public List<String> getPacktype() {
		
		
		

		String[] array =	{"OTHERS_ ONE WAY","KEG_ RETURNABLE","GLASS BTL_ ONE WAY","GLASS BTL_ RETURNABL","CAN_ ONE WAY","PET BTL_ ONE WAY","DM-FLEX_ ONE WAY","DM-SELECT_ ONE WAY","GLASS BTL ONE WAY_ R","DM-MODULAR_ ONE WAY"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
		
	
	    
		
		//return repository.fetch_packtype();
		
	}
	
	
	@Override
	public List<String> getalcoholper() {
		
		
		
		String[] array =	{"0.0","0.45","0.5","2.0","2.4","3.1","4.3","4.5","4.6","4.7","4.8","5.0","5.2","5.3","5.4","5.5","5.9","6.0","7.0"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
	    
	    
		
	//	return repository.fetchalcoholpercentage();
		
	}
	
	
	
	
	
	@Override
	public List<String> fetch_pipo_material_list(PIPOMapping material) {
		System.out.print("Hars---"+material);
		List<String> material_list=repository.fetch_pipo_material_list(material.getFromid());
		return material_list;
		
	}

	
	
	@Override
	public List<String> getanimal() {
		
		
		String[] array =	{"HORSE","MULE","MAD BULL","JACK RABBIT","MAMMOTH++","MAMMOTH","KANGAROO","PUPPY"};
	    List<String> list = Arrays.asList(array);
	    
	    return list;
	    
		
		
		
	
		
	}
	
	
	@Override
	public downLoadPlan getDownload() {
		
		downLoadPlan download=new downLoadPlan();
		
		List<oneRowPlan> a=download12.download_query();
		
		//List<oneRowPlan> b=download12.download_query_1();
		
		//System.out.println("Harshit--"+b.toString());
		
		
			
		
		System.out.println("CHECKKK------"+a.toString());
		download.setRow(a);
		
		//download.setRow1(b);
		
		return download;
		
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
	
	private static class Util {

		public static String listToString(List<String> listOfString) {
		StringBuilder stringBuilder = new StringBuilder("(");
		for(String element : listOfString) {
		stringBuilder.append('\''+element+ "\',");
		}
		stringBuilder.setCharAt(stringBuilder.lastIndexOf(","), ')');
		return stringBuilder.toString();
		}
	}
	
	
	
	@Override
	public List<String> changedFilterSKU(changedFilter list) {
		
	
		StringBuilder stringBuilder = new StringBuilder("select DISTINCT(ForecastingGroup) As ForecastingGroup from AGGREGATED_TABLE_UPDATED where ForecastingGroup != ''");
		if(list.getSubbrand() != null && !list.getSubbrand().isEmpty()) {
		stringBuilder.append(" And Sub_Brand IN " + Util.listToString(list.getSubbrand()));
		}
		
		System.out.println("cjkdhfksdf--"+stringBuilder.toString());
		if(list.getAlcoholper() != null && !list.getAlcoholper().isEmpty()) {
		stringBuilder.append(" AND Alcohol_Percentage IN "+Util.listToString(list.getAlcoholper()));
		}

		if(list.getBrands() != null && !list.getBrands().isEmpty()) {
		stringBuilder.append(" AND Brand in " + Util.listToString(list.getBrands()));
		}
		if(list.getAnimalFlag() != null && !list.getAnimalFlag() .isEmpty()) {
			stringBuilder.append(" AND Animal_Flags in " + Util.listToString(list.getAnimalFlag()  ));
		}
		
		
		
		
		if(list.getMaterialGroup() != null && !list.getMaterialGroup() .isEmpty()) {
			stringBuilder.append(" AND Materialgroup_1_name in " + Util.listToString(list.getMaterialGroup() ));
		}
		if(list.getBaseunit() != null && !list.getBaseunit().isEmpty()) {
		stringBuilder.append(" AND base_unit_of_measure_characteristic IN " + Util.listToString(list.getBaseunit()) );
		}
	
		
		if(list.getGlobalbev() != null && !list.getGlobalbev().isEmpty()) {
			stringBuilder.append(" AND global_bev_cat_name IN " + Util.listToString(list.getGlobalbev()) );
			}
		
		
	
		
		
		if(list.getPackType() != null && !list.getPackType().isEmpty()) {
			stringBuilder.append(" AND pack_type_name in " + Util.listToString(list.getPackType()  ));
		}
		stringBuilder.append(";");

		
		System.out.println("Check---"+stringBuilder.toString());
		String sqlQuery_1 = stringBuilder.toString();
		List<String> a = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			a = em.createNativeQuery(sqlQuery_1)
					.unwrap(org.hibernate.Query.class).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		System.out.println("Check---"+a.toString());
	//	System.out.println("Check---"+stringBuilder.toString().toString());

//		List<String> a= em.createNativeQuery(stringBuilder.toString(), String.class).getResultList();
		
		
		
		return a;
	
	}
	
	
	
	@Override
	public List<String> changedFilterCPG(changedFilter list) {
		
		
		StringBuilder stringBuilder = new StringBuilder("select DISTINCT(concat(customer_planning_group,\"-\",customer_group_planning_name)) As ForecastingGroup from AGGREGATED_TABLE_UPDATED where customer_planning_group != ''");
		if(list.getSalesOffice() != null && !list.getSalesOffice().isEmpty()) {
		stringBuilder.append(" And sales_office IN " + Util.listToString(list.getSalesOffice()));
		}
		
		System.out.println("cjkdhfksdf--"+stringBuilder.toString());
		if(list.getTradeType() != null && !list.getTradeType() .isEmpty()) {
		stringBuilder.append(" AND trade_type IN "+Util.listToString(list.getTradeType()));
		}

		
		stringBuilder.append(";");

		
		System.out.println("Sfsfg---"+stringBuilder.toString());
		
		
		String sqlQuery_1 = stringBuilder.toString();
		List<String> a = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			a = em.createNativeQuery(sqlQuery_1)
					.unwrap(org.hibernate.Query.class).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		System.out.println("Check---"+a.toString());
	//	System.out.println("Check---"+stringBuilder.toString().toString());

//		List<String> a= em.createNativeQuery(stringBuilder.toString(), String.class).getResultList();
		
		
		
		return a;
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public GraphRes getDemandTable(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		
		System.out.println("234567234->345->"+(endWeek));
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
		
		startWeek = demandTableReq.getPrevactuals();
		
		
	
		
		String sqlQuery_1 =repository.fetchFeatureTable_featureAnalysis_ML;
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		try {
		
		System.out.println("SDfs---"+currYearDemandList_featureAnalysis.toString());
		}catch(Exception e)
		{
			
		}
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);
			
			
			System.out.println("Check -->"+demandTableReq.getForecastingGroups());
			
			
			System.out.println("Check12 -->"+demandTableReq.getCustomerPlanningGroup());
			
			System.out.println("Check123 -->"+demandTableReq.getPlants());
			
			System.out.println("fsg453 -->"+prevYearStartWeek);
			
			System.out.println("fsg45387 -->"+prevYearEndWeek);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		
		System.out.println("2345 -->"+demandTableReq.getForecastingGroups());
		
		
		System.out.println("2343565 -->"+demandTableReq.getCustomerPlanningGroup());
		
		System.out.println("124356576 -->"+demandTableReq.getPlants());
		
		
		System.out.println("1233456576 -->"+demandTableReq.getStartWeek());
		
		
		System.out.println("23456789 -->"+demandTableReq.getStartWeek());
		
		
		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		
		List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		response.setCombinedcomment(abc);
		
		
		String sqlQuery2 = UserCommentRepository.fetchUserCommentsQuery;
		
		System.out.println("kjhjg34544444444444,--->"+currYearUserList.toString());
		List<UserCommentsRes> userComments1 = null;
		try {
			userComments1 = em.createNativeQuery(sqlQuery2)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		try {
		System.out.println("**************************");
		System.out.println("harshit------"+userComments1.toString());
		}catch(Exception e)
		{
			
		}
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				// For Brands
//				List<String> global_bev_cat = repository.fetch_global_bev_cat();
//				response.getReq().setGlobalBev(global_bev_cat);
//				
//				
//				
//				
//			
//			List<String> materialgroup = repository.fetchmaterial();
//				response.getReq().setMaterialgroup(materialgroup);
//			
//				
//				
//				List<String> baseunit = repository.fetch_base();
//				response.getReq().setBaseunit(baseunit);
//				
//				
//				
//				List<String> pack_type = repository.fetch_packtype();
//				response.getReq().setPack_type(pack_type);
//				
////				
////				List<String> pack_size = repository.fetch_packsize();
////				response.getReq().setPack_size(pack_size);
////				
//				
//				
//				List<String> cpgname = repository.fetchcpgname();
//				response.getReq().setCpgname(cpgname);
//				
//				
//				
//				
//				
//				
//				
//				List<String> Animal_flag = repository.fetchanimal();
//				response.getReq().setAnimal_Flags(Animal_flag);
//				
//				
//				
//				
//				
//		
//
//				
				
				
				
				
				
				
				
		
				
				
				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);
		
		
		System.out.println("dhfsd---"+currYearAuroriPrevMonthsDemandList.toString());
		
//		// For -24 weeks
//		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = auroriPrevMonthsRepository
//				.fetchDemandTablePrevWeeks_all(demandTableReq.getForecastingGroups(),
//						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
//						demandTableReq.getEndWeek() - 1, 0);
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		

		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		
		
		
		
		
		
		
		System.out.println("Checkiii344556----"+demandTableReq.getForecastingGroups());
		
		System.out.println("Checkiii3445----"+demandTableReq.getCustomerPlanningGroup());
		System.out.println("Checkiii34----"+startWeek);

		System.out.println("Checkiii12----"+ (demandTableReq.getStartWeek() - 1));
		System.out.println("Checkiii----"+currYearAuroriPrevMonthsDemandList_all.toString());
		
		
		

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
				listType);
	

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = BeaconRepository.fetchDemandTableQuery;
		
		System.out.println("kjhjg345,--->"+sqlQuery.toString());
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
		
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		response.setStart(start);
		}catch(Exception e)
		{
			
		}
			System.out.println("123456789sdfghjklcvbnm,--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		
		//////////////
		
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
				}
			}
		}

		
		
		
		
		
		
		
		//////////
		

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
		
		
		response.setAllComments(userComments);
		//response.setCo
		
		
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
		
		System.out.println("CHECK121_45_abcde--->"+currYearDemandList.toString());
		
		System.out.println("CHECK121_45_abcd--->"+currYearAuroriPrevMonthsDemandList_all.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		
		
		currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);
		
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
	//	response.setRes(currYearDemandPrevMonthsDemandList);
		
		
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public GraphRes getDemandTable_L(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		
		System.out.println("234567234->345->"+(endWeek));
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
		
		startWeek = demandTableReq.getPrevactuals();
		
		
		
		String sqlQuery_1 =repository.fetchFeatureTable_featureAnalysis_ML;
		
		
		System.out.println("OIIOIOUIOU-----"+sqlQuery_1);
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		try {
		
		System.out.println("SDfs---"+currYearDemandList_featureAnalysis.toString());
		}catch(Exception e)
		{
			
		}
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);
			
			
			System.out.println("Check -->"+demandTableReq.getForecastingGroups());
			
			
			System.out.println("Check12 -->"+demandTableReq.getCustomerPlanningGroup());
			
			System.out.println("Check123 -->"+demandTableReq.getPlants());
			
			System.out.println("fsg453 -->"+prevYearStartWeek);
			
			System.out.println("fsg45387 -->"+prevYearEndWeek);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);
		
		
		List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		response.setCombinedcomment(abc);
		

		
		

		// For fva, finalforecast values of input weeks
		
		System.out.println("2345 -->"+prevYearDemandList.toString());
		
		
		System.out.println("2343565 -->"+demandTableReq.getCustomerPlanningGroup());
		
		System.out.println("124356576 -->"+demandTableReq.getPlants());
		
		
		System.out.println("1233456576 -->"+demandTableReq.getStartWeek());
		
		
		System.out.println("23456789 -->"+demandTableReq.getStartWeek());
		
		
		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
	
		String sqlQuery2 = UserCommentRepository.fetchUserCommentsQuery;
		
		System.out.println("kjhjg34544444444444,--->"+currYearUserList.toString());
		List<UserCommentsRes> userComments1 = null;
		try {
			userComments1 = em.createNativeQuery(sqlQuery2)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		try {
		System.out.println("**************************");
		System.out.println("harshit------"+userComments1.toString());
		}catch(Exception e)
		{
			
		}
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				// For Brands
//				List<String> global_bev_cat = repository.fetch_global_bev_cat();
//				response.getReq().setGlobalBev(global_bev_cat);
//				
//				
//				
//				
//			
//			List<String> materialgroup = repository.fetchmaterial();
//				response.getReq().setMaterialgroup(materialgroup);
//			
//				
//				
//				List<String> baseunit = repository.fetch_base();
//				response.getReq().setBaseunit(baseunit);
//				
//				
//				
//				List<String> pack_type = repository.fetch_packtype();
//				response.getReq().setPack_type(pack_type);
//				
////				
////				List<String> pack_size = repository.fetch_packsize();
////				response.getReq().setPack_size(pack_size);
////				
//				
//				
//				List<String> cpgname = repository.fetchcpgname();
//				response.getReq().setCpgname(cpgname);
//				
//				
//				
//				
//				
//				
//				
//				List<String> Animal_flag = repository.fetchanimal();
//				response.getReq().setAnimal_Flags(Animal_flag);
//				
//				
//				
//				
//				
//		
//
//				
				
				
				
				
				
				
				
		
				
				
				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);

		
		
		System.out.println("Dfgfgfg-f--"+currYearAuroriPrevMonthsDemandList.toString());
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		System.out.println("Check----"+currYearAuroriPrevMonthsDemandList_all.toString());
		
		
		
		
		
		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		
		

//		
//		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
//				listType);

		
		
		
		
		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
				listType);

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = BeaconRepository.fetchDemandTableQuery;
		
		System.out.println("kjhjg345,--->"+sqlQuery.toString());
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
		
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		response.setStart(start);
		}catch(Exception e)
		{
			
		}
			System.out.println("123456789sdfghjklcvbnm,--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					
					
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
				}
			}
		}
		
		
		
		
		
		
		
		

		for (DemandTableRes currYear : currYearDemandList) {
			for (DemandTableRes prevYear : prevYearDemandList) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					
					System.out.println("787----"+prevYear.getActuals());
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
		
		response.setAllComments(userComments);
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		
		
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);
		System.out.println("CHECK121_45767768--->"+currYearDemandPrevMonthsDemandList);
		
		System.out.println("TYTYGDFDFDFFD--->"+currYearAuroriPrevMonthsDemandList_all);
		
		
		System.out.println("CHECK121_45767678--->"+currYearDemandPrevMonthsDemandList.toString());


		
		for(DemandTableRes curr: currYearDemandPrevMonthsDemandList)
		{
			
//			System.out.println("Fdfdf--");
//			//double x1 = (Math.random()*((3-1)+1))+1;
//			
			try {
				System.out.println("Test123--"+(Double.parseDouble(curr.getActuals()))*100);
			curr.setActuals(String.valueOf(Double.parseDouble(curr.getActuals())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setActuals(null);
			}
//			try {
//				System.out.println("Test12356--"+Double.parseDouble(curr.getApo())*1000);
//			curr.setApo(String.valueOf(Double.parseDouble(curr.getApo())*1000));
//			}catch(Exception e)
//			{
//				System.out.println("Test123rty--");
//				curr.setApo(null);
//			}
//			try {
//				System.out.println("Test123567--"+Double.parseDouble(curr.getMl())*1000);
//			curr.setMl(String.valueOf(Double.parseDouble(curr.getMl())*1000));
//			}catch(Exception e)
//			{
//				System.out.println("Test123rty--");
//				curr.setMl(null);
//			}
//			try {
//			curr.setHarshit(String.valueOf(Double.parseDouble(curr.getHarshit())*1000));
//			}catch(Exception e)
//			{
//				curr.setHarshit(null);
//			}
//			try {
//			curr.setFva(String.valueOf(Double.parseDouble(curr.getFva())*1000));
//			}catch(Exception e)
//			{
//				curr.setFva(null);
//			}
//			try {
//			//	curr.setActualslastyear(actualslastyear);
//				curr.setActualslastyear(String.valueOf(Double.parseDouble(curr.getActualslastyear())*1000));
//				}catch(Exception e)
//				{
//					curr.setFva(null);
//				}
			
			
			
			
			
//			curr.setApo(apo);
//			curr.getMl()
//			
//			curr.getHarshit()
//			
//			curr.getFva()
//			curr.getActualslastyear()
//			
//			curr.
//			
//			String j=String.valueOf(x1);
//			curr.setHarshit(j);
		}
		System.out.println("CHECK121_4576767843256ytrgf--->"+currYearDemandPrevMonthsDemandList.toString());
		System.out.println("CHECK121_45767--->"+currYearDemandPrevMonthsDemandList.size());
		
		
		System.out.println("yufthgvjbgyjfghvjbgv--->"+currYearAuroriPrevMonthsDemandList_all.toString());
		for(DemandTableRes curr: currYearAuroriPrevMonthsDemandList_all)
		{
			//double x1 = (Math.random()*((3-1)+1))+1;
			
			
			System.out.println("Fdfdf--");
			//double x1 = (Math.random()*((3-1)+1))+1;
			
			try {
				System.out.println("Test123--"+(Double.parseDouble(curr.getActuals()))*100);
			curr.setActuals(String.valueOf(Double.parseDouble(curr.getActuals())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setActuals(null);
			}
			try {
				System.out.println("Test12356--"+Double.parseDouble(curr.getApo())*100);
			curr.setApo(String.valueOf(Double.parseDouble(curr.getApo())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setApo(null);
			}
			try {
				System.out.println("Test123567--"+Double.parseDouble(curr.getMl())*100);
			curr.setMl(String.valueOf(Double.parseDouble(curr.getMl())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setMl(null);
			}
			try {
				System.out.println("OEPNPOEOF--"+Double.parseDouble(curr.getHarshit())*100);
			curr.setHarshit(String.valueOf(Double.parseDouble(curr.getHarshit())*100));
			}catch(Exception e)
			{
				System.out.println("OPENORR--");
				curr.setHarshit(null);
			}
			try {
			curr.setFva(String.valueOf(Double.parseDouble(curr.getFva())*100));
			}catch(Exception e)
			{
				curr.setFva(null);
			}
			try {
				System.out.println("OEPNPOEOF--"+Double.parseDouble(curr.getActualslastyear())*100);
			//	curr.setActualslastyear(actualslastyear);
				curr.setActualslastyear(String.valueOf(Double.parseDouble(curr.getActualslastyear())*100));
				}catch(Exception e)
				{
					System.out.println("OPENORR--");
					curr.setActualslastyear(null);
				}
			
			
			
//			
//			String j=String.valueOf(x1);
//			curr.setHarshit(j);
		}
		
		
		System.out.println("yufthgvjbgyjfghvjbgv45678--->"+currYearAuroriPrevMonthsDemandList_all.toString());
			//response.setRes(currYearDemandPrevMonthsDemandList);
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public GraphRes getDemandTable_L_month(DemandTableReq demandTableReq) {
		
		
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
		startWeek = demandTableReq.getPrevactuals();
		
		
		String sqlQuery_1 = repository.fetchFeatureTable_featureAnalysis_monthly_ML;
		
		System.out.println("Checking--"+sqlQuery_1);
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
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
		
		
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				
//				
//				// For Trade Plan
//				List<String> tradeplan = repository.fetchtradetype();
//				response.getReq().setTrade(tradeplan);
//				
//				
//				
//				
//				
//				// For Sales office
//				List<String> salesoffice = repository.fetchsalesoffice();
//				response.getReq().setSales(salesoffice);
				
				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevMonthly(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);
		
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all_month;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		


		
		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		
		

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
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
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		
		response.setStart(start);
		}catch(Exception e)
		{
			
		}
			System.out.println("CHECK121--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
//			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
//		
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		
		List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		response.setCombinedcomment(abc);
		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
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
		
		response.setAllComments(userComments);
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		
		
		
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);


System.out.println("Suvid12----"+currYearDemandPrevMonthsDemandList.toString());


for(int i =0;i<currYearDemandPrevMonthsDemandList.size();i++)
{
	System.out.println("Fdfdf---");
	if(currYearDemandPrevMonthsDemandList.get(i).getCalenderYearWeek()==201910)
	{
		double a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0,e1=0,e2=0,f2=0;
		try {
		 a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		try {
		 b1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getHarshit()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 e1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getApo());
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		
		try {
		 a2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getActuals()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 b2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getHarshit()); 
	 
		}catch(Exception e)
		{
			
		}
		
		try {
		 e2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getApo()); 
		  
		}catch(Exception e)
		{
			
		}
		
		
		try {
			 f2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getFva()); 
			  
			}catch(Exception e)
			{
				
			}
		
		DemandTableRes abc1=new DemandTableRes();
		abc1.setActuals(String.valueOf(a1+a2));
		
		abc1.setMl(String.valueOf(b1+b2));
		
		
		
		
		abc1.setActualslastyear(String.valueOf(c1+c2));
		abc1.setHarshit(String.valueOf(d1+d2));
		
		abc1.setApo(String.valueOf(d1+d2));
		
		abc1.setCalenderYearWeek(201910);
		
		abc1.setFva(String.valueOf(f2));
		

		
		
		
		System.out.println("Checking1---"+currYearDemandPrevMonthsDemandList.get(i));
		System.out.println("Checking1---"+currYearDemandPrevMonthsDemandList.get(i+1));
		
		currYearDemandPrevMonthsDemandList.remove(i);
		//currYearDemandPrevMonthsDemandList.remove(i+1);
		
		currYearDemandPrevMonthsDemandList.set(i, abc1);
		
		break;
		
		
		
	}
}









for(int i =0;i<currYearAuroriPrevMonthsDemandList_all.size();i++)
{
	System.out.println("In lOOP--");
	if(currYearAuroriPrevMonthsDemandList_all.get(i).getCalenderYearWeek()==201910)
	{
		double a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0,e1=0,e2=0,f2=0;
		try {
		 a1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		try {
		 b1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getHarshit()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 e1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getApo());
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		
		try {
		 a2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getActuals()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 b2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getHarshit()); 
	 
		}catch(Exception e)
		{
			
		}
		
		try {
		 e2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getApo()); 
		  
		}catch(Exception e)
		{
			
		}
		
		
		try {
			 f2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getFva()); 
			  
			}catch(Exception e)
			{
				
			}
		
		DemandTableRes abc1=new DemandTableRes();
		abc1.setActuals(String.valueOf(a1+a2));
		
		abc1.setMl(String.valueOf(b1+b2));
		
		
		
		
		abc1.setActualslastyear(String.valueOf(c1+c2));
		abc1.setHarshit(String.valueOf(d1+d2));
		
		abc1.setApo(String.valueOf(d1+d2));
		
		abc1.setCalenderYearWeek(201910);
		
		abc1.setFva(String.valueOf(f2));
		

		
		
		
		
		System.out.println("Checking1---"+currYearAuroriPrevMonthsDemandList_all.get(i));
		System.out.println("Checking1---"+currYearAuroriPrevMonthsDemandList_all.get(i+1));
		currYearAuroriPrevMonthsDemandList_all.remove(i);
		//currYearAuroriPrevMonthsDemandList_all.remove(i+1);
		
		currYearAuroriPrevMonthsDemandList_all.set(i, abc1);
		
		break;
		
		
		
	}
}


		
		
		for(DemandTableRes curr: currYearDemandPrevMonthsDemandList)
		{
			//double x1 = (Math.random()*((3-1)+1))+1;
			
			
			
			try {
				System.out.println("Test123--"+(Double.parseDouble(curr.getActuals()))*100);
			curr.setActuals(String.valueOf(Double.parseDouble(curr.getActuals())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setActuals(null);
			}
			
//			curr.setApo(apo);
//			curr.getMl()
//			
//			curr.getHarshit()
//			
//			curr.getFva()
//			curr.getActualslastyear()
//			
//			curr.
//			
//			String j=String.valueOf(x1);
//			curr.setHarshit(j);
		}
		
		
		for(DemandTableRes curr: currYearAuroriPrevMonthsDemandList_all)
		{
			//double x1 = (Math.random()*((3-1)+1))+1;
			
			
			System.out.println("Fdfdf--");
			//double x1 = (Math.random()*((3-1)+1))+1;
			
			try {
				System.out.println("Test123--"+(Double.parseDouble(curr.getActuals()))*100);
			curr.setActuals(String.valueOf(Double.parseDouble(curr.getActuals())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setActuals(null);
			}
			try {
				System.out.println("Test12356--"+Double.parseDouble(curr.getApo())*100);
			curr.setApo(String.valueOf(Double.parseDouble(curr.getApo())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setApo(null);
			}
			try {
				System.out.println("Test123567--"+Double.parseDouble(curr.getMl())*100);
			curr.setMl(String.valueOf(Double.parseDouble(curr.getMl())*100));
			}catch(Exception e)
			{
				System.out.println("Test123rty--");
				curr.setMl(null);
			}
			try {
				System.out.println("OEPNPOEOF--"+Double.parseDouble(curr.getHarshit())*100);
			curr.setHarshit(String.valueOf(Double.parseDouble(curr.getHarshit())*100));
			}catch(Exception e)
			{
				System.out.println("OPENORR--");
				curr.setHarshit(null);
			}
			try {
			curr.setFva(String.valueOf(Double.parseDouble(curr.getFva())*100));
			}catch(Exception e)
			{
				curr.setFva(null);
			}
			try {
				System.out.println("OEPNPOEOF--"+Double.parseDouble(curr.getActualslastyear())*100);
			//	curr.setActualslastyear(actualslastyear);
				curr.setActualslastyear(String.valueOf(Double.parseDouble(curr.getActualslastyear())*100));
				}catch(Exception e)
				{
					System.out.println("OPENORR--");
					curr.setActualslastyear(null);
				}
			
//			curr.setApo(apo);
//			curr.getMl()
//			
//			curr.getHarshit()
//			
//			curr.getFva()
//			curr.getActualslastyear()
//			
//			curr.
//			
//			String j=String.valueOf(x1);
//			curr.setHarshit(j);
		}
		

		
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
	//	response.setRes(currYearDemandPrevMonthsDemandList);
		return response;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public GraphRes getDemandTable_UOM_L(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		
		System.out.println("234567234->345->"+(endWeek));
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
		
		startWeek = demandTableReq.getPrevactuals();
		
		
		
		String sqlQuery_1 =repository.fetchFeatureTable_featureAnalysis_ML;
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		try {
		System.out.println("SDfs---"+currYearDemandList_featureAnalysis.toString());
		}catch(Exception e)
		{
			
		}
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);
			
			
			System.out.println("Check -->"+demandTableReq.getForecastingGroups());
			
			
			System.out.println("Check12 -->"+demandTableReq.getCustomerPlanningGroup());
			
			System.out.println("Check123 -->"+demandTableReq.getPlants());
			
			System.out.println("fsg453 -->"+prevYearStartWeek);
			
			System.out.println("fsg45387 -->"+prevYearEndWeek);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		
		System.out.println("2345 -->"+demandTableReq.getForecastingGroups());
		
		
		System.out.println("2343565 -->"+demandTableReq.getCustomerPlanningGroup());
		
		System.out.println("124356576 -->"+demandTableReq.getPlants());
		
		
		System.out.println("1233456576 -->"+demandTableReq.getStartWeek());
		
		
		System.out.println("23456789 -->"+demandTableReq.getStartWeek());
		
		
		List<UserPlanRes> currYearUserList = userRepository.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);
		
		
	

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		
		List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		response.setCombinedcomment(abc);
		
		
		
		String sqlQuery2 = UserCommentRepository.fetchUserCommentsQuery;
		
		System.out.println("kjhjg34544444444444,--->"+currYearUserList.toString());
		List<UserCommentsRes> userComments1 = null;
		try {
			userComments1 = em.createNativeQuery(sqlQuery2)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		try {
		System.out.println("**************************");
		System.out.println("harshit------"+userComments1.toString());
		}catch(Exception e)
		{
			
		}
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				// For Brands
//				List<String> global_bev_cat = repository.fetch_global_bev_cat();
//				response.getReq().setGlobalBev(global_bev_cat);
//				
//				
//				
//				
//			
//			List<String> materialgroup = repository.fetchmaterial();
//				response.getReq().setMaterialgroup(materialgroup);
//			
//				
//				
//				List<String> baseunit = repository.fetch_base();
//				response.getReq().setBaseunit(baseunit);
//				
//				
//				
//				List<String> pack_type = repository.fetch_packtype();
//				response.getReq().setPack_type(pack_type);
//				
////				
////				List<String> pack_size = repository.fetch_packsize();
////				response.getReq().setPack_size(pack_size);
////				
//				
//				
//				List<String> cpgname = repository.fetchcpgname();
//				response.getReq().setCpgname(cpgname);
//				
//				
//				
//				
//				
//				
//				
//				List<String> Animal_flag = repository.fetchanimal();
//				response.getReq().setAnimal_Flags(Animal_flag);
//				
//				
//				
//				
//				
//		
//
//				
				
				
				
				
				
				
				
		
				
				
				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);
		
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		


		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		

		
	

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
				listType);
		
		
		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = BeaconRepository.fetchDemandTableQuery;
		
		System.out.println("kjhjg345,--->"+sqlQuery.toString());
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
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		response.setStart(start);
		}catch(Exception e)
		{
			
		}
		
			System.out.println("123456789sdfghjklcvbnm,--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
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
		
		response.setAllComments(userComments);
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);
		
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
		//response.setRes(currYearDemandPrevMonthsDemandList);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public GraphRes getDemandTable_UOM(DemandTableReq demandTableReq) {
		
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		
		System.out.println("234567234->345->"+(endWeek));
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
		
		startWeek = demandTableReq.getPrevactuals();
		
		
		
		String sqlQuery_1 =repository.fetchFeatureTable_featureAnalysis_ML;
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		try {
		System.out.println("SDfs---"+currYearDemandList_featureAnalysis.toString());
		}catch(Exception e)
		{
			
		}
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);
			
			
			System.out.println("Check -->"+demandTableReq.getForecastingGroups());
			
			
			System.out.println("Check12 -->"+demandTableReq.getCustomerPlanningGroup());
			
			System.out.println("Check123 -->"+demandTableReq.getPlants());
			
			System.out.println("fsg453 -->"+prevYearStartWeek);
			
			System.out.println("fsg45387 -->"+prevYearEndWeek);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks1(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		
		System.out.println("2345 -->"+demandTableReq.getForecastingGroups());
		
		
		System.out.println("2343565 -->"+demandTableReq.getCustomerPlanningGroup());
		
		System.out.println("124356576 -->"+demandTableReq.getPlants());
		
		
		System.out.println("1233456576 -->"+demandTableReq.getStartWeek());
		
		
		System.out.println("23456789 -->"+demandTableReq.getStartWeek());
		
		
		List<UserPlanRes_UOM> currYearUserList_1 = userplan_uom.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);
		
		List<UserPlanRes> currYearUserList=null;
		
		List<mappingResp> mappingList = mappingRepo.materialMapping(demandTableReq.getForecastingGroups());
		
		
		 for (mappingResp mapping : mappingList) {	
				for (UserPlanRes_UOM currDemand : currYearUserList_1) {

					
					System.out.println("TYT -- "+currDemand.getForecast());
					
					System.out.println("TYT2345 -- "+mapping.getMaterialdesc());
					
					
					
					
					if (mapping.getMaterialdesc().equals(currDemand.getForecast())) {
						
					//	currDemand.setApo(((mapping.getPc())/mapping.getHl())*(currDemand.getApo()));
						double a=Double.parseDouble(mapping.getPc());
						double b=Double.parseDouble(mapping.getHl());
						double c=Double.parseDouble(currDemand.getFva());
						
//						double d=Double.parseDouble(currDemand.getMl());
//						double e=Double.parseDouble(currDemand.getHarshit());
//						double f=Double.parseDouble(currDemand.getActuals());
					
						
						
						
						double fin_apo=(a/b)*c;
						//currDemand.setFva(fva);
						currDemand.setFva(String.valueOf(fin_apo));
						System.out.println("TESTING---PC - "+a+" HL- "+b+" APO - "+c+" final-- "+fin_apo);

					}
					
					
				}
			}
		    
			currYearUserList = new ArrayList<UserPlanRes>();
		    for(int i=demandTableReq.getStartWeek();i<endWeek;i++)
		    {
		    	
		    	if(i<201953 || i>201999)
		    	{
		    		
		    	
		    	double total_ml=0;
		  
		    	for (UserPlanRes_UOM currDemand : currYearUserList_1) {
		    	      if(currDemand.getCalendarWeek()==i)
		    	      {
		    	    	 total_ml+=Double.parseDouble(currDemand.getFva());
		    	    	 
		    	    	 
		    	      }
		    	}
		    	
		    	
		    	UserPlanRes check=new UserPlanRes();
//		    	check.setActuals(String.valueOf(total_actuals));
//		    	check.setApo(String.valueOf(total_apo));
		    	
		    	
		    	check.setFva(String.valueOf(total_ml));
		    	//check.setMl(String.valueOf(total_ml));
		    	
		    	check.setCalendarWeek(i);
		    	
		    //	check.setCalendarWeek(calendarWeek);
		    	currYearUserList.add(check);
		    	
		    	//System.out.println("^&*&GB---"+check.toString());
		    	
		    	//resp.add(check);
		    	}
		      
		    }

		
		
			List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
					demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
			
			
			response.setCombinedcomment(abc);
		

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		String sqlQuery2 = UserCommentRepository.fetchUserCommentsQuery;
		
		System.out.println("kjhjg34544444444444,--->"+currYearUserList.toString());
		List<UserCommentsRes> userComments1 = null;
		try {
			userComments1 = em.createNativeQuery(sqlQuery2)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		try {
		System.out.println("**************************");
		System.out.println("harshit------"+userComments1.toString());
		}catch(Exception e)
		{
			
		}
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				// For Brands
//				List<String> global_bev_cat = repository.fetch_global_bev_cat();
//				response.getReq().setGlobalBev(global_bev_cat);
//				
//				
//				
//				
//			
//			List<String> materialgroup = repository.fetchmaterial();
//				response.getReq().setMaterialgroup(materialgroup);
//			
//				
//				
//				List<String> baseunit = repository.fetch_base();
//				response.getReq().setBaseunit(baseunit);
//				
//				
//				
//				List<String> pack_type = repository.fetch_packtype();
//				response.getReq().setPack_type(pack_type);
//				
////				
////				List<String> pack_size = repository.fetch_packsize();
////				response.getReq().setPack_size(pack_size);
////				
//				
//				
//				List<String> cpgname = repository.fetchcpgname();
//				response.getReq().setCpgname(cpgname);
//				
//				
//				
//				
//				
//				
//				
//				List<String> Animal_flag = repository.fetchanimal();
//				response.getReq().setAnimal_Flags(Animal_flag);
//				
//				
//				
//				
//				
//		
//
//				
				
				
				
				
				
				
				
		
				
				
				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks1(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);
		
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		


		
		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		
		

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
				listType);

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = repository.fetchDemandTableQuery1;
		
		System.out.println("kjhjg345,--->"+sqlQuery.toString());
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
		
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		response.setStart(start);
		}catch(Exception e)
		{
			
		}
		
		
			System.out.println("123456789sdfghjklcvbnm,--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
//			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
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
		
		response.setAllComments(userComments);
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);
		
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
		//response.setRes(currYearDemandPrevMonthsDemandList);
		return response;
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public GraphRes getDemandTable_UOM_monthly(DemandTableReq demandTableReq) {
		
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		
		//response.getSecondGraphRes()
		Integer startWeek = demandTableReq.getStartWeek();
		Integer endWeek = demandTableReq.getEndWeek();
		Integer prevYearStartWeek = startWeek - 100;
		
		System.out.println("234567234->345->"+(endWeek));
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
		
		startWeek = demandTableReq.getPrevactuals();
		
		
		String sqlQuery_1 = repository.fetchFeatureTable_featureAnalysis_monthly_ML;
	//	String sqlQuery_1 =repository.fetchFeatureTable_featureAnalysis_ML;
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		
		try {
		System.out.println("SDfs---"+currYearDemandList_featureAnalysis.toString());
		}catch(Exception e)
		{
			
		}
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);
			
			
			System.out.println("Check -->"+demandTableReq.getForecastingGroups());
			
			
			System.out.println("Check12 -->"+demandTableReq.getCustomerPlanningGroup());
			
			System.out.println("Check123 -->"+demandTableReq.getPlants());
			
			System.out.println("fsg453 -->"+prevYearStartWeek);
			
			System.out.println("fsg45387 -->"+prevYearEndWeek);

		// For actuals previous year  fetchDemandTableByWeeks
		List<DemandTableRes> prevYearDemandList = repository.fetchDemandTableByWeeks1_monthly(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), prevYearStartWeek, prevYearEndWeek, 100);

		// For fva, finalforecast values of input weeks
		
		System.out.println("2345 -->"+demandTableReq.getForecastingGroups());
		
		
		System.out.println("2343565 -->"+demandTableReq.getCustomerPlanningGroup());
		
		System.out.println("124356576 -->"+demandTableReq.getPlants());
		
		
		System.out.println("1233456576 -->"+demandTableReq.getStartWeek());
		
		
		System.out.println("23456789 -->"+demandTableReq.getStartWeek());
		
		
		List<UserPlanRes_UOM> currYearUserList_1 = userplan_uom.fetchUserPlanByWeeks(demandTableReq.getForecastingGroups(),
				demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), demandTableReq.getStartWeek(),
				endWeek);
		
		List<UserPlanRes> currYearUserList=null;
		
		List<mappingResp> mappingList = mappingRepo.materialMapping(demandTableReq.getForecastingGroups());
		
		
		 for (mappingResp mapping : mappingList) {	
				for (UserPlanRes_UOM currDemand : currYearUserList_1) {

					
					System.out.println("TYT -- "+currDemand.getForecast());
					
					System.out.println("TYT2345 -- "+mapping.getMaterialdesc());
					
					
					
					
					if (mapping.getMaterialdesc().equals(currDemand.getForecast())) {
						
					//	currDemand.setApo(((mapping.getPc())/mapping.getHl())*(currDemand.getApo()));
						double a=Double.parseDouble(mapping.getPc());
						double b=Double.parseDouble(mapping.getHl());
						double c=Double.parseDouble(currDemand.getFva());
						
//						double d=Double.parseDouble(currDemand.getMl());
//						double e=Double.parseDouble(currDemand.getHarshit());
//						double f=Double.parseDouble(currDemand.getActuals());
					
						
						
						
						double fin_apo=(a/b)*c;
						//currDemand.setFva(fva);
						currDemand.setFva(String.valueOf(fin_apo));
						System.out.println("TESTING---PC - "+a+" HL- "+b+" APO - "+c+" final-- "+fin_apo);

					}
					
					
				}
			}
		    
			currYearUserList = new ArrayList<UserPlanRes>();
		    for(int i=demandTableReq.getStartWeek();i<endWeek;i++)
		    {
		    	
		    	if(i<201953 || i>201999)
		    	{
		    		
		    	
		    	double total_ml=0;
		  
		    	for (UserPlanRes_UOM currDemand : currYearUserList_1) {
		    	      if(currDemand.getCalendarWeek()==i)
		    	      {
		    	    	 total_ml+=Double.parseDouble(currDemand.getFva());
		    	    	 
		    	    	 
		    	      }
		    	}
		    	
		    	
		    	UserPlanRes check=new UserPlanRes();
//		    	check.setActuals(String.valueOf(total_actuals));
//		    	check.setApo(String.valueOf(total_apo));
		    	
		    	
		    	check.setFva(String.valueOf(total_ml));
		    	//check.setMl(String.valueOf(total_ml));
		    	
		    	check.setCalendarWeek(i);
		    	
		    //	check.setCalendarWeek(calendarWeek);
		    	currYearUserList.add(check);
		    	
		    	//System.out.println("^&*&GB---"+check.toString());
		    	
		    	//resp.add(check);
		    	}
		      
		    }

		
		
			List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
					demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
			
			
			response.setCombinedcomment(abc);
		

		// For comments of input weeks
		List<UserCommentsRes> userComments = userCommentRepository.fetchUserComments(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		String sqlQuery2 = UserCommentRepository.fetchUserCommentsQuery_monthly_1;
		
		System.out.println("kjhjg34544444444444,--->"+currYearUserList.toString());
		List<UserCommentsRes> userComments1 = null;
		try {
			userComments1 = em.createNativeQuery(sqlQuery2)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", demandTableReq.getStartWeek())
					.setParameter("endWeek", endWeek)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		try {
		System.out.println("**************************");
		System.out.println("harshit------"+userComments1.toString());
		}catch(Exception e)
		{
			
		}
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				// For Brands
//				List<String> global_bev_cat = repository.fetch_global_bev_cat();
//				response.getReq().setGlobalBev(global_bev_cat);
//				
//				
//				
//				
//			
//			List<String> materialgroup = repository.fetchmaterial();
//				response.getReq().setMaterialgroup(materialgroup);
//			
//				
//				
//				List<String> baseunit = repository.fetch_base();
//				response.getReq().setBaseunit(baseunit);
//				
//				
//				
//				List<String> pack_type = repository.fetch_packtype();
//				response.getReq().setPack_type(pack_type);
//				
////				
////				List<String> pack_size = repository.fetch_packsize();
////				response.getReq().setPack_size(pack_size);
////				
//				
//				
//				List<String> cpgname = repository.fetchcpgname();
//				response.getReq().setCpgname(cpgname);
//				
//				
//				
//				
//				
//				
//				
//				List<String> Animal_flag = repository.fetchanimal();
//				response.getReq().setAnimal_Flags(Animal_flag);
//				
//				
//				
//				
//				
//		
//
//				
				
				
				
				
				
				
				
		
				
				
				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks1_monthly(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);
		
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all_monthly;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		


		
		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks_monthly(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		
		

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
				listType);

		// For input weeks
//		List<DemandTableRes> currYearDemandList = repository.fetchDemandTableByWeeks(
//				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
//				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek, 0);
		String sqlQuery = repository.fetchDemandTableQuery1_monthly_1;
		
		System.out.println("kjhjg345,--->"+sqlQuery.toString());
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
		
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		response.setStart(start);
		}catch(Exception e)
		{
			
		}
		
		
			System.out.println("123456789sdfghjklcvbnm,--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
//			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
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
		
		response.setAllComments(userComments);
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);







for(int i =0;i<currYearDemandPrevMonthsDemandList.size();i++)
{
	System.out.println("Fdfdf---");
	if(currYearDemandPrevMonthsDemandList.get(i).getCalenderYearWeek()==201910)
	{
		double a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0,e1=0,e2=0,f2=0;
		try {
		 a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		try {
		 b1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getHarshit()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 e1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getApo());
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		
		try {
		 a2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getActuals()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 b2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getHarshit()); 
	 
		}catch(Exception e)
		{
			
		}
		
		try {
		 e2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getApo()); 
		  
		}catch(Exception e)
		{
			
		}
		
		
		try {
			 f2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getFva()); 
			  
			}catch(Exception e)
			{
				
			}
		
		DemandTableRes abc1=new DemandTableRes();
		abc1.setActuals(String.valueOf(a1+a2));
		
		abc1.setMl(String.valueOf(b1+b2));
		
		
		
		
		abc1.setActualslastyear(String.valueOf(c1+c2));
		abc1.setHarshit(String.valueOf(d1+d2));
		
		abc1.setApo(String.valueOf(d1+d2));
		
		abc1.setCalenderYearWeek(201910);
		
		abc1.setFva(String.valueOf(f2));
		

		
		
		
		System.out.println("Checking1---"+currYearDemandPrevMonthsDemandList.get(i));
		System.out.println("Checking1---"+currYearDemandPrevMonthsDemandList.get(i+1));
		
		currYearDemandPrevMonthsDemandList.remove(i);
		currYearDemandPrevMonthsDemandList.remove(i+1);
		
		currYearDemandPrevMonthsDemandList.set(i, abc1);
		
		break;
		
		
		
	}
}









for(int i =0;i<currYearAuroriPrevMonthsDemandList_all.size();i++)
{
	System.out.println("In lOOP--");
	if(currYearAuroriPrevMonthsDemandList_all.get(i).getCalenderYearWeek()==201910)
	{
		double a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0,e1=0,e2=0,f2=0;
		try {
		 a1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		try {
		 b1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getHarshit()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 e1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getApo());
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		
		try {
		 a2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getActuals()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 b2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getHarshit()); 
	 
		}catch(Exception e)
		{
			
		}
		
		try {
		 e2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getApo()); 
		  
		}catch(Exception e)
		{
			
		}
		
		
		try {
			 f2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getFva()); 
			  
			}catch(Exception e)
			{
				
			}
		
		DemandTableRes abc1=new DemandTableRes();
		abc1.setActuals(String.valueOf(a1+a2));
		
		abc1.setMl(String.valueOf(b1+b2));
		
		
		
		
		abc1.setActualslastyear(String.valueOf(c1+c2));
		abc1.setHarshit(String.valueOf(d1+d2));
		
		abc1.setApo(String.valueOf(d1+d2));
		
		abc1.setCalenderYearWeek(201910);
		
		abc1.setFva(String.valueOf(f2));
		

		
		
		
		
		System.out.println("Checking1---"+currYearAuroriPrevMonthsDemandList_all.get(i));
		System.out.println("Checking1---"+currYearAuroriPrevMonthsDemandList_all.get(i+1));
		currYearAuroriPrevMonthsDemandList_all.remove(i);
		//currYearAuroriPrevMonthsDemandList_all.remove(i+1);
		
		currYearAuroriPrevMonthsDemandList_all.set(i, abc1);
		
		break;
		
		
		
	}
}
		
		
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
		//response.setRes(currYearDemandPrevMonthsDemandList);
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
		startWeek = demandTableReq.getPrevactuals();
		
		
		String sqlQuery_1 = repository.fetchFeatureTable_featureAnalysis_monthly_ML;
		
		System.out.println("Checking--"+sqlQuery_1);
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
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
		try {
		
			System.out.println("23456--->"+currYearDemandList1.toString());
			
			
			response.setSecondGraphRes(currYearDemandList1);
			
		}catch(Exception e)
		{
			
		}

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
		
		
		
//		// For Brands
//				List<String> brands = repository.fetchBrands();
//				response.getReq().setBrands(brands);
//				
//				
//				
//				// For Brands
//				List<String> alcoholper = repository.fetchalcoholpercentage();
//				response.getReq().setAlcoholper(alcoholper);
//				
//				
//				
//				// For Brands
//				List<String> subbrand = repository.fetchsubbrand();
//				response.getReq().setSubbrand(subbrand);
//				
//				
//				
//				
//				// For Trade Plan
//				List<String> tradeplan = repository.fetchtradetype();
//				response.getReq().setTrade(tradeplan);
//				
//				
//				
//				
//				
//				// For Sales office
//				List<String> salesoffice = repository.fetchsalesoffice();
//				response.getReq().setSales(salesoffice);
				
				
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevMonthly(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), startWeek,
						demandTableReq.getStartWeek() - 1, 0);
		
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all_month;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		


		List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		response.setCombinedcomment(abc);
		
		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		
		
		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
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
		
		try {
		int start=currYearDemandList.get(0).getCalenderYearWeek();
		response.setStart(start);
		}catch(Exception e) {
			
		}
		
			System.out.println("CHECK121--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
			
//			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
//		
			
		
		
		
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
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
		
		
		response.setAllComments(userComments);
		System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);
		




for(int i =0;i<currYearDemandPrevMonthsDemandList.size();i++)
{
	System.out.println("Fdfdf---");
	if(currYearDemandPrevMonthsDemandList.get(i).getCalenderYearWeek()==201910)
	{
		double a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0,e1=0,e2=0,f2=0;
		try {
		 a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		try {
		 b1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getHarshit()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 e1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getApo());
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		
		try {
		 a2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getActuals()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 b2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getHarshit()); 
	 
		}catch(Exception e)
		{
			
		}
		
		try {
		 e2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getApo()); 
		  
		}catch(Exception e)
		{
			
		}
		
		
		try {
			 f2=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i+1).getFva()); 
			  
			}catch(Exception e)
			{
				
			}
		
		DemandTableRes abc1=new DemandTableRes();
		abc1.setActuals(String.valueOf(a1+a2));
		
		abc1.setMl(String.valueOf(b1+b2));
		
		
		
		
		abc1.setActualslastyear(String.valueOf(c1+c2));
		abc1.setHarshit(String.valueOf(d1+d2));
		
		abc1.setApo(String.valueOf(d1+d2));
		
		abc1.setCalenderYearWeek(201910);
		
		abc1.setFva(String.valueOf(f2));
		

		
		
		
		System.out.println("Checking1---"+currYearDemandPrevMonthsDemandList.get(i));
		System.out.println("Checking1---"+currYearDemandPrevMonthsDemandList.get(i+1));
		
		currYearDemandPrevMonthsDemandList.remove(i);
		currYearDemandPrevMonthsDemandList.remove(i+1);
		
		currYearDemandPrevMonthsDemandList.set(i, abc1);
		
		break;
		
		
		
	}
}









for(int i =0;i<currYearAuroriPrevMonthsDemandList_all.size();i++)
{
	System.out.println("In lOOP--");
	if(currYearAuroriPrevMonthsDemandList_all.get(i).getCalenderYearWeek()==201910)
	{
		double a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0,e1=0,e2=0,f2=0;
		try {
		 a1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		try {
		 b1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getHarshit()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 e1=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i).getApo());
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		
		try {
		 a2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getActuals()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 b2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getMl()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 c2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getActualslastyear()); 
		// a1=Double.parseDouble(currYearDemandPrevMonthsDemandList.get(i).getActuals());  
		}catch(Exception e)
		{
			
		}
		
		try {
		 d2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getHarshit()); 
	 
		}catch(Exception e)
		{
			
		}
		
		try {
		 e2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getApo()); 
		  
		}catch(Exception e)
		{
			
		}
		
		
		try {
			 f2=Double.parseDouble(currYearAuroriPrevMonthsDemandList_all.get(i+1).getFva()); 
			  
			}catch(Exception e)
			{
				
			}
		
		DemandTableRes abc1=new DemandTableRes();
		abc1.setActuals(String.valueOf(a1+a2));
		
		abc1.setMl(String.valueOf(b1+b2));
		
		
		
		
		abc1.setActualslastyear(String.valueOf(c1+c2));
		abc1.setHarshit(String.valueOf(d1+d2));
		
		abc1.setApo(String.valueOf(d1+d2));
		
		abc1.setCalenderYearWeek(201910);
		
		abc1.setFva(String.valueOf(f2));
		

		
		
		
		
		System.out.println("Checking1---"+currYearAuroriPrevMonthsDemandList_all.get(i));
		System.out.println("Checking1---"+currYearAuroriPrevMonthsDemandList_all.get(i+1));
		currYearAuroriPrevMonthsDemandList_all.remove(i);
		//currYearAuroriPrevMonthsDemandList_all.remove(i+1);
		
		currYearAuroriPrevMonthsDemandList_all.set(i, abc1);
		
		break;
		
		
		
	}
}

System.out.println("Suvid12345----"+currYearAuroriPrevMonthsDemandList_all.toString());
		



		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
	//	response.setRes(currYearDemandPrevMonthsDemandList);
		return response;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// YEARLY VIEW 
	
	// IMPLEMENTATION SAME AS PREVIOUS METHOD
	// THE DIFFERENCE is ITS for the 
	
	
	@Override
	public GraphRes getDemandTable_yearly(DemandTableReq demandTableReq) {
		GraphRes response = new GraphRes();
		response.setReq(demandTableReq);
		
		System.out.println("fdf-->"+(String.valueOf(demandTableReq.getStartWeek())).substring(0, 4));
		String h=(String.valueOf(demandTableReq.getStartWeek())).substring(0, 4)+"00";
		
		String h1=(String.valueOf(demandTableReq.getStartWeek())).substring(0, 4)+"52";
		
	//	demandTableReq.setStartWeek(Integer.parseInt(h));
		
		
		int a=Integer.parseInt(h);
		
		int b=Integer.parseInt(h1);
		demandTableReq.setEndWeek(Integer.parseInt(h1));
		
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
		
		
       startWeek = demandTableReq.getPrevactuals();
		
		
		
		String sqlQuery_1 = repository.fetchFeatureTable_featureAnalysis_ML;
		List<featureAnalysisRes> currYearDemandList_featureAnalysis = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			currYearDemandList_featureAnalysis = em.createNativeQuery(sqlQuery_1)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", a)
					.setParameter("endWeek", b)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
//		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}

		
		
		
		try {
			System.out.println("23456--->"+currYearDemandList_featureAnalysis.toString());
			
			
			response.setSecondGraphRes(currYearDemandList_featureAnalysis);
		}catch(Exception e)
		{
			
		}

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
		
		
		
		
		
//		// For Brands
//		List<String> brands = repository.fetchBrands();
//		response.getReq().setBrands(brands);
//		
//		
//		
//		// For Brands
//		List<String> alcoholper = repository.fetchalcoholpercentage();
//		response.getReq().setAlcoholper(alcoholper);
//		
//		
//		
//		// For Brands
//		List<String> subbrand = repository.fetchsubbrand();
//		response.getReq().setSubbrand(subbrand);
//		
//		
//		// For Brands
//		List<String> global_bev_cat = repository.fetch_global_bev_cat();
//		response.getReq().setGlobalBev(global_bev_cat);
//		
//		
//		
//		
//	
//	List<String> materialgroup = repository.fetchmaterial();
//		response.getReq().setMaterialgroup(materialgroup);
//	
//		
//		
//		List<String> baseunit = repository.fetch_base();
//		response.getReq().setBaseunit(baseunit);
//		
//		
//		
//		List<String> pack_type = repository.fetch_packtype();
//		response.getReq().setPack_type(pack_type);
//		
////		
////		List<String> pack_size = repository.fetch_packsize();
////		response.getReq().setPack_size(pack_size);
////		
//		
//		
//		List<String> cpgname = repository.fetchcpgname();
//		response.getReq().setCpgname(cpgname);
//		
//		
//		
//		
//		
//		
//		
//		List<String> Animal_flag = repository.fetchanimal();
//		response.getReq().setAnimal_Flags(Animal_flag);
//		
//		
//		
//		
//		
//		
//		// For Trade Plan
//		List<String> tradeplan = repository.fetchtradetype();
//		response.getReq().setTrade(tradeplan);
//		
//		
//		
//		
//		
//		// For Sales office
//		List<String> salesoffice = repository.fetchsalesoffice();
//		response.getReq().setSales(salesoffice);
//
//		
//		
		
		
		
		List<String> abc=repository.fetchcomments_all(demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), demandTableReq.getStartWeek(), endWeek);
		
		
		response.setCombinedcomment(abc);
				
				
				

		// For -24 weeks
		List<AuroriPrevMonths> currYearAuroriPrevMonthsDemandList = auroriPrevMonthsRepository
				.fetchDemandTablePrevWeeks(demandTableReq.getForecastingGroups(),
						demandTableReq.getCustomerPlanningGroup(), demandTableReq.getPlants(), Integer.parseInt(h),
						demandTableReq.getStartWeek() - 1, 0);
		
		
		
String sqlQuery12 = auroriPrevMonthsRepository.fetchDemandTablePrevWeeksQuery_all;
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+sqlQuery12.toString());
		List<DemandTableRes> currYearAuroriPrevMonthsDemandList_all = null;
		try {
			currYearAuroriPrevMonthsDemandList_all = em.createNativeQuery(sqlQuery12)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", Integer.parseInt(h))
					.setParameter("endWeek", demandTableReq.getStartWeek() - 1)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		

		Integer previous_actualsly_start=startWeek-100;
		
		Integer previous_actualsly_end=(demandTableReq.getStartWeek() - 1)-100;
		
		
		List<DemandTableRes> prevYearLY = repository.fetchDemandTableByWeeks(
				demandTableReq.getForecastingGroups(), demandTableReq.getCustomerPlanningGroup(),
				demandTableReq.getPlants(), previous_actualsly_start, previous_actualsly_end, 100);
		
		try {
		
		System.out.println("kjhjg345234trgfdertgfer,--->"+currYearAuroriPrevMonthsDemandList_all.toString());
		
		}catch(Exception e)
		{
			
		}
		

		Type listType = new TypeToken<List<DemandTableRes>>() {
		}.getType();

		List<DemandTableRes> currYearDemandPrevMonthsDemandList = modelMapper.map(currYearAuroriPrevMonthsDemandList,
				listType);
		
		
		

		List<DemandTableRes> currYearDemandPrevMonthsDemandList_all = modelMapper.map(currYearAuroriPrevMonthsDemandList_all,
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
		
		
		try {
			int start=currYearDemandList.get(0).getCalenderYearWeek();
			response.setStart(start);
			}catch(Exception e)
			{
				
			}
		
			System.out.println("CHECK121--->"+currYearDemandList.toString());
			
			
			for(DemandTableRes curr: currYearDemandList)
			{
				curr.setActuals(null);
			}
			
		
		
			
//			for(DemandTableRes curr: currYearDemandList)
//			{
//				double x1 = (Math.random()*((3-1)+1))+1;
//				
//				String j=String.valueOf(x1);
//				curr.setHarshit(j);
//			}
		System.out.println("CHECK121_45--->"+currYearDemandList.toString());
		

		
		

		for (DemandTableRes currDemand : currYearDemandList) {
			for (UserPlanRes currUser : currYearUserList) {
				if (currDemand.getCalenderYearWeek() == currUser.getCalendarWeek()) {
					currDemand.setFinalforecast(currUser.getFinalForecast());
					currDemand.setFva(currUser.getFva());
				}
			}
		}
		
		
		
		
		
		for (DemandTableRes currYear : currYearDemandPrevMonthsDemandList_all) {
			for (DemandTableRes prevYear : prevYearLY) {
				if (currYear.getCalenderYearWeek() == prevYear.getCalenderYearWeek()) {
					currYear.setActualslastyear(prevYear.getActuals());
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
		response.setAllComments(userComments);
		
		System.out.println("CHECK121--->"+currYearDemandList.toString());
		
		
		
	//	System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
	//	currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		
		currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		//response.setRes(currYearDemandPrevMonthsDemandList);
currYearAuroriPrevMonthsDemandList_all.addAll(currYearDemandList);
		
		response.setRes_table(currYearAuroriPrevMonthsDemandList_all);
		response.setRes(currYearAuroriPrevMonthsDemandList_all);
		//response.setRes(currYearDemandList);
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
		
		System.out.println("WAQT THAM SA GYA"+demandTableReq.getWhich_feature());
		int x = 0;
		if (weekNumber > 24) {
			weekNumber = weekNumber - 24;
		} else {
			weekNumber = 52 - (24 - weekNumber);
			x = 100;
		}
		startWeek = (startWeek - x) + weekNumber;

		// For actuals previous year
		
		
startWeek = demandTableReq.getPrevactuals();

String sqlQuery_1=null;

if(demandTableReq.getWhich_feature().equals("Baseline"))
{
	sqlQuery_1=repository.fetchFeatureTable_featureAnalysis_ML;
}
else if(demandTableReq.getWhich_feature().equals("Open"))
{
	sqlQuery_1=repository.fetchFeatureTable_featureAnalysis_open_orders;
}
else if(demandTableReq.getWhich_feature().equals("Promo"))
{
	sqlQuery_1=repository.fetchFeatureTable_featureAnalysis_temperature;
}
	

		//String sqlQuery_1 = BeaconRepository.fetchFeatureTable_featureAnalysis;
		List<featureAnalysisRes> currYearDemandList_featureAnalysis1 = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			
			System.out.println("WAQT THAM SA GYA"+sqlQuery_1);
			currYearDemandList_featureAnalysis1 = em.createNativeQuery(sqlQuery_1)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis1)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
//		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis1.toString());
			
	
		
		

		
		
		
		//System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		//currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		//response.setRes(currYearDemandList);
		//response1.setRes(currYearDemandList1);
			
			response1.setSecondGraphRes(currYearDemandList_featureAnalysis1);
		return response1;
	}

	
	
	
	
	@Override
	public featureGraphRes getFeatureAnalysis_monthly(DemandTableReq demandTableReq) {
		
		
		
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
		
		
startWeek = demandTableReq.getPrevactuals();



String sqlQuery_1=null;

if(demandTableReq.getWhich_feature().equals("Baseline"))
{
	sqlQuery_1=repository.fetchFeatureTable_featureAnalysis_monthly_ML;
}
else if(demandTableReq.getWhich_feature().equals("Open"))
{
	sqlQuery_1=repository.fetchFeatureTable_featureAnalysis_monthly_open_orders;
}
else if(demandTableReq.getWhich_feature().equals("Promo"))
{
	sqlQuery_1=repository.fetchFeatureTable_featureAnalysis_temperature;
}

		//String sqlQuery_1 = BeaconRepository.fetchFeatureTable_featureAnalysis_monthly;
		List<featureAnalysisRes> currYearDemandList_featureAnalysis1 = null;
		
		//System.out.println("567uiyjhgfre--->"+currYearDemandList1.toString());
		
		
		try {
			currYearDemandList_featureAnalysis1 = em.createNativeQuery(sqlQuery_1)
					.setParameter("forecastingGroupList", demandTableReq.getForecastingGroups())
					.setParameter("cpgList", demandTableReq.getCustomerPlanningGroup())
					.setParameter("plantList", demandTableReq.getPlants())
					.setParameter("startWeek", startWeek)
					.setParameter("endWeek", endWeek)
					.setParameter("x", 0)
					.unwrap(org.hibernate.Query.class).setResultTransformer(new Transformer_feature()).list();
		} catch (Exception e) {
			log.info("Exception occurred Hawww", e);
		}
		
		
//		for(featureAnalysisRes curr: currYearDemandList_featureAnalysis1)
//		{
//			double x1 = (Math.random()*((3-1)+1))+1;
//			
//			double x2 = (Math.random()*((3-1)+1))+1;
//			
//			double x3 = (Math.random()*((3-1)+1))+1;
//			
//			String j=String.valueOf(x1);
//			String j2=String.valueOf(x2);
//			String j3=String.valueOf(x3);
//			curr.setProperty(j);
//			curr.setProperty2(j2);
//			
//			curr.setProperty3(j3);
//		}
//		
		
		
			System.out.println("23456--->"+currYearDemandList_featureAnalysis1.toString());
			
	
		
		

		
		
		
		//System.out.println("CHECK121_45--->"+currYearDemandPrevMonthsDemandList.toString());
		
//		currYea
		//currYearDemandPrevMonthsDemandList.addAll(currYearDemandList);
		//response.setRes(currYearDemandList);
		//response1.setRes(currYearDemandList1);
			
			response1.setSecondGraphRes(currYearDemandList_featureAnalysis1);
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
		
		System.out.println("09090----"+selectFromCache.toString());

		List<CacheTableEntity> cacheTableEntity = cacheRepository.fetchCacheTableByKey(selectFromCache);
		//System.out.pr
		return cacheTableEntity;
	}
	
	
	
	

	@Override
	public List<String> getcpg1(CPGreq cpg) {
		
List<String> a = cpg.getFilterSales();
		
		
		List<String> b = cpg.getFilterTrade();
		
	
		
		
		System.out.println("CHECK--->"+a.isEmpty());
		System.out.println("CHECK--->"+b.isEmpty());
	
		//String mainQuery = " WHERE 1 ";
		if (a.isEmpty() && b.isEmpty())
		{
			System.out.println("Harshit1->");
			return repository.cpg_groups();
		}
		
		else if (a.isEmpty())
		{
			 String regexp,regexp1;
				
					
					System.out.println("ab");
					regexp1 = "^".concat(b.get(0));
					if (b.size() > 1) {
						for (int i = 1; i < b.size(); i++) {
							regexp1 = regexp1.concat("|^").concat(b.get(i));
						}
					}
					
					return repository.cpg_groups_b(regexp1);
					
		}
		
		else if (b.isEmpty())
		{
			System.out.println("Harshit3->");
			
			
			 String regexp,regexp1;
				System.out.println("ab");
					regexp = "^".concat(a.get(0));
					if (a.size() > 1) {
						for (int i = 1; i < a.size(); i++) {
							regexp = regexp.concat("|^").concat(a.get(i));
						}
					}
					
					
					
			return repository.cpg_groups_a(regexp);
		}
		else {
			
			System.out.println("Harshit3->");
			
			
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
					
					
				//	return repository.fetchForecastingGroups_ab(regexp,regexp1);
					
					
			return repository.cpg_groups_ab(regexp,regexp1);
			
		}

	//	List<CacheTableEntity> cacheTableEntity = cacheRepository.fetchCacheTableByKey(selectFromCache);
		//System.out.pr
	//	return cacheTableEntity;
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
	public void savePlanData(List<SavePlanReq> savePlanReq1) {
		
		for (SavePlanReq savePlanReq : savePlanReq1) {

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
		List<Integer> sku1 = repository.fetchDemandTableByFG(sku);
		
		
		System.out.println("SDfs---"+sku1.toString());
		//sku = fetchedSku;
		List<String> skuListSelectFromCache = new LinkedList<String>();
		for (Integer skus : sku1) {
			for (String cpgs : cpg) {
				for (String plants : plant) {
					
					
					System.out.println("popopo12121---"+cpgs+"f");
					skuListSelectFromCache.add(String.valueOf(savePlanReq.getCalendarWeek()) + String.valueOf(skus) + String.valueOf(cpgs).trim() + String.valueOf(plants).trim());
					
					
					
					//String week=savePlanReq.getCalendarWeek().
					System.out.println("popopo---"+(String.valueOf(savePlanReq.getCalendarWeek()) + String.valueOf(skus) + cpgs + plants).trim());
					
					
					System.out.println("popopo12121---"+(savePlanReq.getCalendarWeek()));
					//System.out.println("'"+savePlanReq.getCalendarWeek() + skus + cpgs + plants+"',");
				}
			}
		}
		System.out.println("----------------------------------------------------");
		// List<SavePlanEntity> planTableResponse =
		// savePlanRepository.fetchSavePlanTableByKey(skuListSelectFromCache);
		//List<CacheTableEntity> cacheTableEntity1 = getCacheTable(skuListSelectFromCache);
		
		List<CacheTableEntity> cacheTableEntity1 = getCacheTable(skuListSelectFromCache);
		
		Set<CacheTableEntity> cacheTableEntity= new HashSet<>();
		cacheTableEntity.addAll(cacheTableEntity1);
		
		
		System.out.println("DFdfd--"+cacheTableEntity.toString());
		
		// call krta hu ok
		
//		List<String> a12 = null;
//		
//		
//		List<CacheTableEntity> abc=null;
//		
//		abc.add(cacheTableEntity.get(0));
//		
//		for(CacheTableEntity c:cacheTableEntity1)
//		{
//			for(CacheTableEntity d:abc)
//		}
		
		
		//a.add("dfd");
		
//		for(String a:skuListSelectFromCache)
//		{
//			int flag=0;
//			
//			
//			for(CacheTableEntity c:cacheTableEntity1)
//			{
//				if(a.equals(c.getKey()))
//				{
//					
//					if(flag==1)
//					{
//						System.out.println("Removed");
//						cacheTableEntity.remove(c);
//						System.out.println("dfdfdf---"+cacheTableEntity.toString());
//					}
//					else if(flag==0)
//					{
//						System.out.println("First time occured");
//						flag=1;
//					}
//				}
//			}
//		}
//		
		
	
		
		
		Map<String, Double> cacheTableResponseMap = new HashMap<String, Double>();
		
		System.out.println("iu4ht35hf--"+cacheTableEntity.toString());
		
		
		
		
		
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
			
			System.out.println("totla--"+totalML);
			// }
		}
		
		System.out.println("______----------________");
		long start = System.currentTimeMillis();
		SavePlanEntity savePlanEntity = null;
List<SavePlanEntity> savePlanEntityList = new ArrayList<SavePlanEntity>();

		for (Integer skus : sku1) {
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
						PIPOMapping a=null;
						List<PIPOMapping> ab=pipoMapping.findbyid(skus);
						
						System.out.println("HElloo----"+ab.toString());
						try {
						
						 a=ab.get(0);
						}catch(Exception e)
						{
							System.out.println("HElloo12345---"+e.toString());
						}
						
						
						
						if(a!=null) {
							
							System.out.println("HElloo12345 inside null---"+a.toString());
							if(a.getFromweek()<=savePlanReq.getCalendarWeek())
							{
								
								System.out.println("HElloo12345678 inside null---"+a.toString());
								if(a.getState().equals("Transistion"))
								{
									skus=a.getToid();	
								}
								else {
							        break;
								}
								
							}
							   
							}
						
						
						savePlanEntity.setPk_combination(String.valueOf(savePlanReq.getUser()).trim() + String.valueOf(savePlanReq.getCalendarWeek()).trim() + String.valueOf(skus).trim() + String.valueOf(cpgs).trim() + String.valueOf(plants).trim());

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
					
					try {
					System.out.println("CHECKK4rgfb---"+skus);
					}catch(Exception e)
					{
						
					}
					
					 String ml=repository.fetchFG(skus);
					//System.out.println(savePlanReq.getCalendarWeek() + skus + cpgs + plants);
					System.out.println("CHECK4545->"+cacheTableResponseMap.get(savePlanReq.getCalendarWeek() + skus + cpgs + plants));
					
					int calendar_yearMonth;
					
					System.out.println("UJU->"+cpgs+"--"+plants+"---"+savePlanReq.getCalendarWeek()+"---"+skus);
					
					try {
					
					 
							 
							 List<Integer> a4=repository.fetchcalendarMonth(cpgs,plants,savePlanReq.getCalendarWeek(),skus);
							 calendar_yearMonth=a4.get(0);
					 
					 System.out.println("Month Checking---"+calendar_yearMonth);
					}catch(Exception e)
					{
						System.out.println("≈≈---"+e.toString());
						calendar_yearMonth=0;
					}
					System.out.println("DEBUG->"+calendar_yearMonth);
					
					System.out.println("DEBUG12->"+skus);
					if(null == cacheTableResponseMap.get(String.valueOf(savePlanReq.getCalendarWeek()).trim() + String.valueOf(skus).trim() + String.valueOf(cpgs).trim() + String.valueOf(plants).trim())) {
						
						currentML = 0d;
					} else {
						currentML = cacheTableResponseMap.get(String.valueOf(savePlanReq.getCalendarWeek()).trim() + String.valueOf(skus).trim() + String.valueOf(cpgs).trim() + String.valueOf(plants).trim());
						
						System.out.println("Test1--"+currentML);
					}
					
					int id=0;
					
					// Fields added
					
					savePlanEntity.setMl(currentML);
					
					savePlanEntity.setId(id);
					
					
					savePlanEntity.setForecasting(ml);
					

					
					
					savePlanEntity.setCalendar_yearMonth(calendar_yearMonth);

					if(currentML==0.0 && totalML==0.0)
					{
						System.out.println("HOOO GYAA");
						//savePlanEntity.setFva(savePlanReq.getFva());
						
						savePlanEntity.setFinalForecastTemp(savePlanReq.getFinalForecast()/sku1.size());
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
						
						System.out.print("harshit---"+savePlanReq.getFva()/sku1.size());
						savePlanEntity.setFva(savePlanReq.getFva()/sku1.size());
					}
					else {
						System.out.print("haree1--"+currentML);
						System.out.print("haree2--"+totalML);
						System.out.print("haree3--"+savePlanReq.getFva());
						System.out.print("haree4--"+savePlanReq.toString());
						System.out.print("harshit121---"+(currentML / totalML) * savePlanReq.getFva());
					savePlanEntity.setFva((currentML / totalML)* savePlanReq.getFva());
					}
					
					
					System.out.println("ewugfyerfbehrb----------"+savePlanEntity.toString());

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
		}
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
		List<Integer> fetchedSku12 = repository.fetchDemandTableByFG(skuList);
		
		
		
		
		List<String> combinations = new LinkedList<String>();
		
		for (Integer skus : fetchedSku12) {
			for (String cpgs : cpgList) {
				for (String plants : plantList) {
					combinations.add(String.valueOf(skus).trim() + String.valueOf(cpgs).trim() + String.valueOf(plants).trim());

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
			
			
			System.out.println("Debug131212-->"+combinationKeyWithUserList.toString());
			
			
				
			
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
	
	
	
	
	
	

	
	
	
	
	@Override
	public String savePIPOSKU(PIPOMapping saveFilterReq) {
		
		
		System.out.println("DSFsfsfsd---");

		PIPOMapping filterEntity = new PIPOMapping();
		
		System.out.println("DSFsfsfsd---"+saveFilterReq.toString());
		filterEntity.setFromid(saveFilterReq.getFromid());
		System.out.println("DSFsfsfsd---"+saveFilterReq.getFromid());
		
		
		filterEntity.setFromid(saveFilterReq.getFromid());
		filterEntity.setToid(saveFilterReq.getToid());
		
		filterEntity.setState(saveFilterReq.getState());
		
		
		filterEntity.setFromweek(saveFilterReq.getFromweek());
		
		filterEntity.setFgid(saveFilterReq.getFgid());
	//	filterEntity.setFromweek(saveFilterReq.getFromweek());
		
		
		
		
	
		pipoMapping.save(filterEntity);
		
		
		repository.makepreviousprimenull(saveFilterReq.getFromid());
		
		
		repository.makenewprimary(saveFilterReq.getToid());

		return "Filter Saved";
	}
	
	
	
	
	
	
	
	@Override
	public String addSKU_pipo_final(PIPOEntity saveFilterReq) {

		PIPOEntity filterEntity = new PIPOEntity();
		
		//filterEntity.setFGID(saveFilterReq.getFGID());
		//filterEntity.setForecastinggroup(saveFilterReq.getForecastinggroup());
		System.out.println("skdhfksu--"+String.valueOf(saveFilterReq.getMaterial()));
		filterEntity.setMaterial(String.valueOf(saveFilterReq.getMaterial()));
		//filterEntity.setMaterial(saveFilterReq.getMaterial());
		filterEntity.setSku(saveFilterReq.getSku());
		
		filterEntity.setMinimum(saveFilterReq.getMinimum());
	
		
		repository.addvalue(String.valueOf(saveFilterReq.getMaterial()), saveFilterReq.getSku());
		//piporepo.save(filterEntity);

		return "Filter Saved";
	}
	
	
	
	public String saveLog(SaveLogReq saveLogReq) {
		
		System.out.println("Harshit-->"+saveLogReq.getUsername());

//		SaveLogEntity saveLogEntity = new SaveLogEntity();
//		
//		
//		saveLogEntity.setUsername(saveLogReq.getUsername());
//		saveLogEntity.setActivity(saveLogReq.getActivity());
//		saveLogEntity.setDatetimestamp(saveLogReq.getDatetimestamp());		
	
//		LogRepository logRespository = new

		repository.savelogs(saveLogReq.getUsername(),saveLogReq.getActivity(),saveLogReq.getDatetimestamp());

		return "Log Saved";
	}
	
	
	
	
	@Override
	public String set_default(default_filter_res saveLogReq) {
		
		//System.out.println("Harshit-->"+saveLogReq.getUsername());

//		SaveLogEntity saveLogEntity = new SaveLogEntity();
//		
//		
//		saveLogEntity.setUsername(saveLogReq.getUsername());
//		saveLogEntity.setActivity(saveLogReq.getActivity());
//		saveLogEntity.setDatetimestamp(saveLogReq.getDatetimestamp());		
	
//		LogRepository logRespository = new

		repository.change_filter_to_default(saveLogReq.getVal());

		return "Log Saved";
	}
	
	
	@Override
	public String change_filter_prev_null(default_filter_res saveLogReq) {
		
		//System.out.println("Harshit-->"+saveLogReq.getUsername());

//		SaveLogEntity saveLogEntity = new SaveLogEntity();
//		
//		
//		saveLogEntity.setUsername(saveLogReq.getUsername());
//		saveLogEntity.setActivity(saveLogReq.getActivity());
//		saveLogEntity.setDatetimestamp(saveLogReq.getDatetimestamp());		
	
//		LogRepository logRespository = new

		repository.change_filter_prev_null(saveLogReq.getVal());

		return "Log Saved";
	}

	
	
	@Override
	public String mapFG(mapFGreq saveLogReq) {
		
		//System.out.println("Harshit-->"+saveLogReq.getUsername());

//		SaveLogEntity saveLogEntity = new SaveLogEntity();
//		
//		
//		saveLogEntity.setUsername(saveLogReq.getUsername());
//		saveLogEntity.setActivity(saveLogReq.getActivity());
//		saveLogEntity.setDatetimestamp(saveLogReq.getDatetimestamp());		
	
//		LogRepository logRespository = new
		
		List<String> a=repository.fetchForecastingGroups_fgid(saveLogReq.getFg());
		String fgid=a.get(0);

		repository.mapFG(saveLogReq.getFg(),fgid,saveLogReq.getMaterial());

		return "Log Saved";
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
			fetchFilterListRes.setDefault_Val(filterEntitys.getValuedefault());
			fetchFilterListRes.setPlant(plantList);
			fetchFilterListResList.add(fetchFilterListRes);
		}
		return fetchFilterListResList;
	}
	
	
	
	
	
	@Override
	public List<PIPOMapping> fetchPIPO() {
		List<PIPOMapping> fetchFilterListResList = new LinkedList<PIPOMapping>();
		PIPOMapping fetchFilterListRes = null;
		List<PIPOMapping> filterEntity = (List<PIPOMapping>) pipoMapping.findAll();
		for (PIPOMapping filterEntitys : filterEntity) {
			fetchFilterListRes = new PIPOMapping();
			
			
			fetchFilterListRes.setFromid(filterEntitys.getFromid());
			fetchFilterListRes.setToid(filterEntitys.getToid());
			fetchFilterListRes.setFromweek(filterEntitys.getFromweek());
			
			fetchFilterListRes.setState(filterEntitys.getState());
			
			
			fetchFilterListRes.setFgid(filterEntitys.getFgid());
			//fetchFilterListRes.setState(filterEntitys.getState());
			
			fetchFilterListResList.add(fetchFilterListRes);
		}
		return fetchFilterListResList;
	}
	
	
	
	
	
	@Override
	public List<PIPOEntity> fetchpipo() {
		List<PIPOEntity> fetchFilterListResList = new LinkedList<PIPOEntity>();
		PIPOEntity fetchFilterListRes = null;
		List<PIPOEntity> filterEntity = piporepo.findAll();
		System.out.println("ffgfgf---"+filterEntity.toString());
		for (PIPOEntity filterEntitys : filterEntity) {
			try {
			fetchFilterListRes = new PIPOEntity();
			
			fetchFilterListRes.setMaterial(filterEntitys.getMaterial());
			fetchFilterListRes.setFGID(filterEntitys.getFGID());
			fetchFilterListRes.setSku(filterEntitys.getSku());
			
			//fetchFilterListRes.setForecastingGroup(filterEntitys.getForecastingGroup());
			fetchFilterListRes.setForecastinggroup(filterEntitys.getForecastinggroup());
			fetchFilterListRes.setSku(filterEntitys.getSku());
			fetchFilterListRes.setMinimum(filterEntitys.getMinimum());
			fetchFilterListRes.setMaximum(filterEntitys.getMaximum());
			fetchFilterListRes.setPrime(filterEntitys.getPrime());
			//fetchFilterListRes.setCnt(filterEntitys.getCnt());
			fetchFilterListResList.add(fetchFilterListRes);
			}catch(Exception e)
			{
				
			}
		}
		return fetchFilterListResList;
	}
	
	
	
	@Override
	public List<LogResponse> fetchlogs() {
		//List<LogResponse> fetchlogsList = new LinkedList<LogResponse>();
		LogResponse fetchlogs = null;
		
	//	System.out.println("43567---"+repository.fetchlogs().toString());
		List<LogResponse> logs = logRepo.fetchlogs();
		
		System.out.println("Checkut"+logs.toString());
//		for (SaveLogEntity savelogEntity : savelogEntitys) {
//			fetchlogs = new LogResponse();
//			fetchlogs.setUsername(savelogEntity.getUsername());
//			fetchlogs.setActivity(savelogEntity.getActivity());
//			fetchlogs.setDatetimestamp(savelogEntity.getDatetimestamp());
//
//
//			fetchlogsList.add(fetchlogs);
//		}
	//	System.out.println("Harshit--->"+logs.toString());
		return logs;
	}
	
	
	
	
	@Override
	public List<String> fetchcomments() {
		//List<LogResponse> fetchlogsList = new LinkedList<LogResponse>();
		LogResponse fetchlogs = null;
		
	//	System.out.println("43567---"+repository.fetchlogs().toString());
		List<String> logs = logRepo.fetchcomments();
		
		System.out.println("Checkut"+logs.toString());
//		for (SaveLogEntity savelogEntity : savelogEntitys) {
//			fetchlogs = new LogResponse();
//			fetchlogs.setUsername(savelogEntity.getUsername());
//			fetchlogs.setActivity(savelogEntity.getActivity());
//			fetchlogs.setDatetimestamp(savelogEntity.getDatetimestamp());
//
//
//			fetchlogsList.add(fetchlogs);
//		}
	//	System.out.println("Harshit--->"+logs.toString());
		return logs;
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
			savePlanEntity.setFva(0);
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

	@Override
	public List<String> getunitPerPack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphRes getDemandTable_UOM_HL(DemandTableReq demandTableReq) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
