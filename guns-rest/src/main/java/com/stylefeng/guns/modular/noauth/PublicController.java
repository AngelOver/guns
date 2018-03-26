package com.stylefeng.guns.modular.noauth;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizAgentCompany;
import com.stylefeng.guns.common.persistence.model.BizAgentPerson;
import com.stylefeng.guns.common.persistence.model.BizBanner;
import com.stylefeng.guns.common.persistence.model.BizDemands;
import com.stylefeng.guns.common.persistence.model.BizDemandsCateory;
import com.stylefeng.guns.common.persistence.model.BizDemandsQuote;
import com.stylefeng.guns.common.persistence.model.BizNews;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.common.persistence.model.BizPaterner;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.auth.util.FastDFSClient;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizAgentCompanyService;
import com.stylefeng.guns.modular.system.service.IBizAgentPersonService;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizBannerService;
import com.stylefeng.guns.modular.system.service.IBizDemandsCateoryService;
import com.stylefeng.guns.modular.system.service.IBizDemandsQuoteService;
import com.stylefeng.guns.modular.system.service.IBizDemandsService;
import com.stylefeng.guns.modular.system.service.IBizNewsService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.service.IBizPaternerService;

/**
 *
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/noauth")
public class PublicController {

	@Autowired
	private IBizBannerService bizBannerService;

	@Autowired
	private IBizPaternerService bizPaternerService;

	@Autowired
	private IBizAgentService bizAgentService;
	
	@Autowired
	private IBizAgentCompanyService bizAgentCompanyService;
	
	@Autowired
	private IBizAgentPersonService bizAgentPersonService;

	@Autowired
	private IBizDemandsCateoryService bizDemandsCateoryService;

	@Autowired
	private IBizDemandsService bizDemandsService;

	@Autowired
	private IBizDemandsQuoteService bizDemandsQuoteService;

	@Autowired
	private IBizNewsService bizNewsService;

	@Autowired
	private IBizPassportService bizPassportService;

	/**
	 * 获取列表1
	 */
	@RequestMapping(value = "/bannerlistTest")
	@ResponseBody
	public Object list(String condition, HttpServletRequest request,
			HttpServletResponse response) {
		// 如果没有条件查询所有
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(condition)) {
			Wrapper<BizBanner> wrapper = new EntityWrapper<>();
			wrapper.eq("status", 0);
			response.setHeader("Access-Control-Allow-Origin", "*");
			map.put("datas", bizBannerService.selectList(null));
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
			return map;
		}

		// 有条件，按条件查询
		Wrapper<BizBanner> wrapper = new EntityWrapper<>();
		wrapper.eq("status", 0);
		wrapper = wrapper.like("type", "%" + condition + "%").or()
				.like("title", "%" + condition + "%").or()
				.like("url", "%" + condition + "%");
		response.setHeader("Access-Control-Allow-Origin", "*");
		map.put("datas", bizBannerService.selectList(wrapper));
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}

	/**
	 * 获取合作商
	 */
	@RequestMapping(value = "/paternerList")
	@ResponseBody
	public Object paternerList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizPaterner> paternerPage = new Page<>(1, 10);
		paternerPage = bizPaternerService.selectPage(paternerPage);
		response.setHeader("Access-Control-Allow-Origin", "*");
		map.put("datas", paternerPage.getRecords());
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}

	/**
	 * 获取合作商
	 */
	@RequestMapping(value = "/agentList")
	@ResponseBody
	public Object agentList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizAgent> agentPage = new Page<>(1, 4);
		// 有条件，按条件查询
		Wrapper<BizAgent> wrapper = new EntityWrapper<>();
		wrapper.eq("recommend", 1).and().eq("is_star", 1).and()
				.eq("agent_status", 1).and().eq("apply_status", 1);
		// 待添加页面过滤代码
		agentPage = bizAgentService.selectPage(agentPage, wrapper);
		response.setHeader("Access-Control-Allow-Origin", "*");
		map.put("datas", agentPage.getRecords());
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	
	
	/**
	 * 获取代理所
	 */
	@RequestMapping(value = "/agentCompanyList")
	@ResponseBody
	public Object agentCompanyList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo	= request.getParameter("pageNo");
		String pageSize	= request.getParameter("pageSize");
		String query	= request.getParameter("query");
		String pid	= request.getParameter("pid");
		String status	= request.getParameter("status");
		String typeId	= request.getParameter("typeId");
		String showIndex = request.getParameter("showIndex");
		String orderBy	= request.getParameter("orderBy");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizAgentCompany> agentCompanyPage = new Page<>(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), StringUtils.isBlank(pageSize)?10:Integer.parseInt(pageSize));
		// 有条件，按条件查询
		Wrapper<BizAgentCompany> wrapper = new EntityWrapper<>();
		
		if(!StringUtils.isBlank(showIndex)){
			wrapper.and().eq("index_show", showIndex);
		}
		
		if(!StringUtils.isBlank(typeId)){
			wrapper.and().eq("trustree", typeId);
		}
		
		// 待添加页面过滤代码
		response.setHeader("Access-Control-Allow-Origin", "*");
		agentCompanyPage = bizAgentCompanyService.selectPage(agentCompanyPage,wrapper);
		map.put("datas", agentCompanyPage.getRecords());
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	/**
	 * 获取代理所
	 */
	@RequestMapping(value = "/agentPersonList")
	@ResponseBody
	public Object agentPersonList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo	= request.getParameter("pageNo");
		String pageSize	= request.getParameter("pageSize");
		String query	= request.getParameter("query");
		String pid	= request.getParameter("pid");
		String status	= request.getParameter("status");
		String typeId	= request.getParameter("typeId");
		String showIndex = request.getParameter("showIndex");
		String orderBy	= request.getParameter("orderBy");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizAgentPerson> agentPersonPage = new Page<>(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), StringUtils.isBlank(pageSize)?10:Integer.parseInt(pageSize));
		// 有条件，按条件查询
		Wrapper<BizAgentPerson> wrapper = new EntityWrapper<>();
		
		if(!StringUtils.isBlank(showIndex)){
			wrapper.and().eq("index_show", showIndex);
		}
		
		if(!StringUtils.isBlank(typeId)){
			wrapper.and().eq("trustree", typeId);
		}
		
		// 待添加页面过滤代码
		response.setHeader("Access-Control-Allow-Origin", "*");
		agentPersonPage = bizAgentPersonService.selectPage(agentPersonPage,wrapper);
		map.put("datas", agentPersonPage.getRecords());
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	
	
	

	/**
	 * 获取悬赏订单类别
	 */
	@RequestMapping(value = "/demandsCateoryList")
	@ResponseBody
	public Object demandsCateoryList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String typeId = request.getParameter("typeId");
		
		Page<BizDemandsCateory> demandsCateoryPage = new Page<>(1, 6);
		// 有条件，按条件查询
		Wrapper<BizDemandsCateory> wrapper = new EntityWrapper<>();
		wrapper.eq("status", 1);
		//有类型要求
		if(!StringUtils.isBlank(typeId)){
			wrapper.and().eq("parent_category_id", typeId);
		}
		// 待添加页面过滤代码
		demandsCateoryPage = bizDemandsCateoryService.selectPage(
				demandsCateoryPage, wrapper);
		response.setHeader("Access-Control-Allow-Origin", "*");
		map.put("datas", demandsCateoryPage.getRecords());
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	
	/**
	 * 获取悬赏订单类别
	 */
	@RequestMapping(value = "/demandsCateoryListDcApi")
	@ResponseBody
	public Object demandsCateoryListDcApi(String id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String typeId = request.getParameter("typeId");
		// 有条件，按条件查询
		Wrapper<BizDemandsCateory> wrapper = new EntityWrapper<>();
		wrapper.eq("status", 1);
		//有类型要求
		if(!StringUtils.isBlank(typeId)){
			wrapper.and().eq("parent_category_id", typeId);
		}
		// 待添加页面过滤代码
		List<BizDemandsCateory> bdcList= bizDemandsCateoryService.selectList(wrapper);
		JSONArray array = new JSONArray();
		for(BizDemandsCateory bdc : bdcList){
			JSONObject object = new JSONObject();
			object.put("id", bdc.getId());
			object.put("name", bdc.getCategoryName());
			array.add(object);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		map.put("datas", array);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	
	
	
	

	/**
	 * 需求类别
	 */
	@RequestMapping(value = "/demandsCateoryListApi")
	@ResponseBody
	public Object demandsCateoryListApi(String id, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BizDemandsCateory> demandsCateoryPage = new Page<>(1, 6);
		// 有条件，按条件查询
		// 获取所有分类
		Wrapper<BizDemandsCateory> wrapper = new EntityWrapper<>();
		wrapper.eq("status", 1);
		List<BizDemandsCateory> bdcList = bizDemandsCateoryService
				.selectList(wrapper);
		JSONArray array = new JSONArray();

		// 获取所有类别
		Map<String, JSONArray> tempMap = new HashMap<String, JSONArray>();
		for (BizDemandsCateory bdc : bdcList) {
			if (tempMap.get(String.valueOf(bdc.getParentCategoryId())) == null) {
				JSONArray ja = new JSONArray();
				JSONObject temp = new JSONObject();
				temp.put("typeId", bdc.getId());
				temp.put("typeName", bdc.getCategoryName());
				temp.put("check", false);
				ja.add(temp);
				tempMap.put(String.valueOf(bdc.getParentCategoryId()), ja);
			} else {
				JSONArray ja = tempMap.get(String.valueOf(bdc
						.getParentCategoryId()));
				JSONObject temp = new JSONObject();
				temp.put("typeId", bdc.getId());
				temp.put("typeName", bdc.getCategoryName());
				temp.put("check", false);
				ja.add(temp);
			}
		}

		// 按父类组织级别
		JSONArray parent = tempMap.get("0");

		for (int i = 0; i < parent.size(); i++) {
			JSONObject jTemp = parent.getJSONObject(i);
			if (i == 0) {
				jTemp.put("check", true);
			}
			jTemp.put("childType",
					tempMap.get(String.valueOf(jTemp.get("typeId"))));
			array.add(jTemp);
		}
		// 待添加页面过滤代码

		map.put("datas", array);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}

	/**
	 * 发布需求
	 */
	@RequestMapping(value = "/demandsAddApi")
	@ResponseBody
	public Object demandsAddApi(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");// 会员id
		if (ToolUtil.isOneEmpty(id)) {
			map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			map.put(Util.MESSAGE, "参数不对");
		} else {
			BizPassport passport = bizPassportService.selectById(id);
			BizDemands demands = new BizDemands();
			demands.setContentSupplement(request.getParameter("content"));
			demands.setIndustry1(0);
			demands.setIndustry2(0);
			demands.setIndustry3(0);
			demands.setTitle(request.getParameter("title"));
			demands.setPriceBudget(new BigDecimal(request.getParameter("price")));
			demands.setLabel(request.getParameter("types"));
			demands.setPassportId(Integer.valueOf(id));
			demands.setContacts(request.getParameter("username"));
			demands.setMobile(request.getParameter("phone"));
			demands.setReleaseTime(new Date());
			demands.setExpiryDate(1);
			demands.setStatus(1);
			bizDemandsService.insert(demands);
			map.put("datas", "保存成功！");
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
		}
		return map;
	}
	
	
	
	
	

	/**
	 * 抢单商家
	 */
	@RequestMapping(value = "/quoteListByDemandsApi")
	@ResponseBody
	public Object quoteListByDemandsApi(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		String dId = request.getParameter("did");
		String pId = request.getParameter("pid");
		//判断id有没有值，没有结束
		if(StringUtils.isBlank(dId)||StringUtils.isBlank(pId)){
			map.put("datas", "操作失败！");
			map.put(Util.RESULT, Util.RESULT_RC_EXCEPTION);
			map.put(Util.MESSAGE, "操作失败");
			return map;
		}
		//根据需求Id以及用户id查询抢单用户
		Wrapper<BizDemandsQuote> wrapper = new EntityWrapper<>();
		wrapper.eq("passport_id",Integer.valueOf(pId) ).and().eq("demand_id", Integer.valueOf(dId));
		List<BizDemandsQuote> qList = bizDemandsQuoteService
				.selectList(wrapper);
		
		List<Integer> agentList = new ArrayList<Integer>();
		List<Integer> passList = new ArrayList<Integer>();
		
		for(BizDemandsQuote  quote : qList){
			agentList.add(quote.getAgentId());
			passList.add(quote.getPassportId());
		}
		
		List<BizAgent> baList = bizAgentService.selectBatchIds(agentList);
		Map<Integer, BizAgent> baMap = baList.stream().collect(Collectors.toMap(BizAgent::getAgentId, Function.identity()));
	
		List<BizPassport> bpList = bizPassportService.selectBatchIds(passList);
		Map<Long, BizPassport>  bpMap = bpList.stream().collect(Collectors.toMap(BizPassport::getPassportId, Function.identity()));
		List array = new ArrayList();
		for(BizDemandsQuote  quote : qList){
			Map<String,Object> temp = BeanKit.beanToMap(quote);
			temp.put("agent", baMap.get(quote.getAgentId()));
			temp.put("passport", bpMap.get(new Long(quote.getPassportId())));
			array.add(temp);
		}
		map.put("datas", array);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	

	/**
	 * 订单悬赏
	 */
	@RequestMapping(value = "/demandsQuoteListApi")
	@ResponseBody
	public Object demandsQuoteListApi(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		String pid = request.getParameter("pid");// 会员id
		if (ToolUtil.isOneEmpty(pid)) {
			map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			map.put(Util.MESSAGE, "参数不对");
		} else {
			BizPassport passport = bizPassportService.selectById(pid);
			// Page<BizDemandsQuote> demandsQuotePage = new Page<>(1, 6);
			// 有条件，按条件查询
			// 获取所有分类
			Map<String, JSONArray> tempMap = new HashMap<String, JSONArray>();
			JSONArray array = new JSONArray();
			Wrapper<BizDemandsQuote> wrapper = new EntityWrapper<>();
			wrapper.eq("passport_id", pid);
			List<BizDemandsQuote> bdqList = bizDemandsQuoteService
					.selectList(wrapper);
			List<Integer> dids = new ArrayList<Integer>();
			Map<Integer, BizDemands> bdMap = new HashMap();
			// 获取所有定单的你类一次查出
			for (BizDemandsQuote bdq : bdqList) {
				dids.add(bdq.getDemandId());
			}
			List<BizDemands> bdList = bizDemandsService.selectBatchIds(dids);
			
			//放进map去重，方便后面拿取
			for (BizDemands bd : bdList) {
				bdMap.put(bd.getDemandId(), bd);
			}
			BizDemands bdTemp = null;
			for (BizDemandsQuote bdq : bdqList) {
				if (tempMap.get(String.valueOf(bdq.getStatus())) == null) {
					JSONArray ja = new JSONArray();
					JSONObject temp = new JSONObject();
					temp.put("orderId", bdq.getId());
					temp.put("catchOrderTime", bdq.getQuoteTime());
					bdTemp = bdMap.get(bdq.getDemandId());
					temp.put("orderTitle", bdTemp.getTitle());
					temp.put("linkName", bdTemp.getContacts());
					temp.put("linkPhone", bdTemp.getMobile());
					temp.put("myPrice", bdq.getPrice());
					ja.add(temp);
					tempMap.put(String.valueOf(bdq.getStatus()), ja);
				} else {
					JSONArray ja = tempMap.get(String.valueOf(bdq.getStatus()));
					JSONObject temp = new JSONObject();
					temp.put("orderId", bdq.getId());
					temp.put("catchOrderTime", bdq.getQuoteTime());
					bdTemp = bdMap.get(bdq.getDemandId());
					temp.put("orderTitle", bdTemp.getTitle());
					temp.put("linkName", bdTemp.getContacts());
					temp.put("linkPhone", bdTemp.getMobile());
					temp.put("myPrice", bdq.getPrice());
					ja.add(temp);
				}
			}

			for (int i = 0; i < 4; i++) {
				JSONArray arrayTemp = tempMap.get(String.valueOf(i));
				JSONObject oTemp = new JSONObject();
				if (i == 0) {
					oTemp.put("check", true);
				} else {
					oTemp.put("check", false);
				}
				oTemp.put("list", arrayTemp);
				array.add(oTemp);
			}
			map.put("datas", array);
		}
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}
	
	
	/**
	 * 获取悬赏订单
	 */
	@RequestMapping(value = "/getDemands")
	@ResponseBody
	public Object getDemands(String id, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		//对参数进行校验
		if(StringUtils.isBlank(id)){
			map.put("datas", "");
			map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			map.put(Util.MESSAGE, "操作失败");
			return map;
		}
		
		//通过id获取对象
		BizDemands  demands = bizDemandsService.selectById(id);
		map.put("datas", demands);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}

	/**
	 * 获取悬赏订单
	 */
	@RequestMapping(value = "/demandsList")
	@ResponseBody
	public Object demandsList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNo	= request.getParameter("pageNo");
		String query	= request.getParameter("query");
		String pid	= request.getParameter("pid");
		String status	= request.getParameter("status");
		String typeId	= request.getParameter("typeId");
		String dateStart= request.getParameter("dateStart");
		String dateEnd	= request.getParameter("dateEnd");
		String minPrice	= request.getParameter("minPrice");
		String maxPrice	= request.getParameter("maxPrice");
		String orderBy	= request.getParameter("orderBy");
		Page<BizDemands> demandsPage = new Page<>(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), 10);
		// 有条件，按条件查询
		Wrapper<BizDemands> wrapper = new EntityWrapper<>();
		wrapper.eq("status", StringUtils.isBlank(status)?1:status);
		if(!StringUtils.isBlank(query)){
			wrapper.and().like("title", query).or().like("content_supplement", query);
		}
		
		if(!StringUtils.isBlank(pid)){
			wrapper.and().ge("passport_id", pid);
		}
		if(!StringUtils.isBlank(dateStart)){
			wrapper.and().ge("release_time", dateStart);
		}
		
		if(!StringUtils.isBlank(dateEnd)){
			wrapper.and().le("release_time", dateEnd);
		}
		
		if(!StringUtils.isBlank(minPrice)){
			wrapper.and().ge("price_budget", minPrice);
		}
		
		if(!StringUtils.isBlank(maxPrice)){
			wrapper.and().le("price_budget", maxPrice);
		}
		
		if(!StringUtils.isBlank(orderBy)){
			wrapper.orderBy("release_time",false);
		}
		if(!StringUtils.isBlank(typeId)){
			wrapper.and().eq("industry1", typeId);
		}
	
		
		// 待添加页面过滤代码
		demandsPage = bizDemandsService.selectPage(demandsPage, wrapper);
		response.setHeader("Access-Control-Allow-Origin", "*");
		map.put("datas", demandsPage);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;

	}

	/**
	 * 获取新闻
	 */
	@RequestMapping(value = "/newsList")
	@ResponseBody
	public Object newsList(String id, HttpServletRequest request,
			HttpServletResponse response) {
		String size = "5";
		String type = "2";
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> mappage = new HashMap<String, Object>();
		size = request.getParameter("size");
		type = request.getParameter("type");
		String page = request.getParameter("page");
		Page<BizNews> newsPage = new Page<>(Integer.parseInt(page),
				Integer.parseInt(size));
		// 有条件，按条件查询
		Wrapper<BizNews> wrapper = new EntityWrapper<>();
		wrapper.eq("status", 0).and().eq("category_id", Integer.parseInt(type));
		// 待添加页面过滤代码
		int newstotal = bizNewsService.selectCount(wrapper);
		int pagetatol = (newstotal + Integer.parseInt(size) - 1)
				/ Integer.parseInt(size);
		mappage.put("totalPageNum", pagetatol);
		Page<BizNews> list = newsPage = bizNewsService.selectPage(newsPage,
				wrapper);
		maps.put("list", list);
		maps.put("pageCount", mappage);
		response.setHeader("Access-Control-Allow-Origin", "*");

		map.put("datas", maps);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;
	}

	/**
	 * 资讯详情
	 * 
	 * @author sjz
	 * @date 2018年3月3日下午14:50:20
	 * @version 1.0
	 */
	@RequestMapping(value = "/newsDetails")
	@ResponseBody
	public Object newsDetails(HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");

		String id = request.getParameter("newsId");
		Map<String, Object> map = new HashMap<String, Object>();

		BizNews news = bizNewsService.selectById(id);
		if (news != null) {
			map.put("datas", news);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
		}
		return map;
	}
	
	
	/**
	 * wang上传图片
	 */
	@RequestMapping(value = "/baiduImg")
	@ResponseBody
	public Object baiduImg(@RequestPart("upfile") MultipartFile picture,HttpServletRequest request,
			HttpServletResponse response) {
		// String path = request.getServletContext().getRealPath("");//
		// 获取项目动态绝对路径
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		String name = picture.getName() + UUID.randomUUID();
		File tempFile = null;
		String fid = "";
		try {
			// 这里处理业务逻辑
			// 获取配制的服务器文件
			ClientGlobal.init("fdfs_client.conf");
			InetAddress ip = ClientGlobal.getG_tracker_group().tracker_servers[0]
					.getAddress();
			tempFile = File.createTempFile(name, "temp");
			picture.transferTo(tempFile);
			Map<String, String> metaList = new HashMap<String, String>();
			// metaList.put("width","1024");
			// metaList.put("height","768");
			// metaList.put("author","杨信");
			// metaList.put("date","20161018");
			fid = FastDFSClient.uploadFile(tempFile, name, metaList);
			 fid = "http://"+ip.getHostAddress()+"/"+fid;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭临时文件
			tempFile.deleteOnExit(); // 程序退出时删除临时文件
		}		
			List<String> urlList = new ArrayList<String>();
			urlList.add(fid);
		    JSONObject  result = new JSONObject();
		    result.put("errno", 0);
		    result.put("data",urlList);
		    return  result;
	}

}
