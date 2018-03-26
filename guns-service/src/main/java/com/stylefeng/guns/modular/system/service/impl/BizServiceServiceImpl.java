package com.stylefeng.guns.modular.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import com.stylefeng.guns.common.persistence.model.BizService;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.common.persistence.model.BizServiceImg;
import com.stylefeng.guns.common.persistence.model.BizServiceInfo;
import com.stylefeng.guns.common.persistence.model.BizSpecificationValue;
import com.stylefeng.guns.common.persistence.dao.BizServiceCategoryMapper;
import com.stylefeng.guns.common.persistence.dao.BizServiceImgMapper;
import com.stylefeng.guns.common.persistence.dao.BizServiceInfoMapper;
import com.stylefeng.guns.common.persistence.dao.BizServiceMapper;
import com.stylefeng.guns.common.persistence.dao.BizSpecificationValueMapper;
import com.stylefeng.guns.modular.system.service.IBizServiceService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-20
 */
@Service
public class BizServiceServiceImpl extends ServiceImpl<BizServiceMapper, BizService> implements IBizServiceService {

	@Autowired
	private BizServiceMapper bizServiceMapper;
	
	@Autowired
	private BizServiceCategoryMapper bizServiceCategoryMapper;
	
	@Autowired
	private BizServiceInfoMapper bizServiceInfoMapper;
	
	@Autowired
	private BizServiceImgMapper bizserviceimgmapper;
	
	@Autowired
	private BizSpecificationValueMapper  bizSpecificationValueMapper;
	
	String name = "";
	 
	/**
	 * 商品管理 商品列表
	 * data:2018年2月8日 09:43:17
	 * 
	 */
	@Override
	public List<Map<String, Object>> getServiceList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = bizServiceMapper.getServiceList(map);
		for (Map<String, Object> map2 : list) {
			map2.put("check", false);
			//获得分类情况
			String category_id = map2.get("category_id")+"";
			String name1  = getServiceCategoryName(category_id);
			map2.put("cate_name", name1);
			name="";
			String release = map2.get("release")+"";
			String verify = map2.get("verify")+"";
			if (verify.equals("1")) {
				map2.put("verify", "审核通过");
			}else if(verify.equals("2")){
				map2.put("verify", "审核未通过");
			}else{
				map2.put("verify", "审核中");
			}
			if (release.equals("1")) {
				map2.put("release", "上架");
			}else{
				map2.put("release", "下架");
			}
			//获得价格
			String shop_price = "";
			String service_id = map2.get("service_id")+"";			
			Wrapper<BizSpecificationValue> spec = new EntityWrapper();
			spec.eq("service_id", service_id);
			List<BizSpecificationValue> specList = bizSpecificationValueMapper.selectList(spec);
			if (specList.size()>0) {
				for (BizSpecificationValue bizSpecificationValue : specList) {
					if (bizSpecificationValue.getShopPrice().compareTo(BigDecimal.ZERO)==0) {
						shop_price = shop_price+"面议/";
					}
					shop_price = shop_price+bizSpecificationValue.getShopPrice()+"/";
				}
				map2.put("shop_price", shop_price);
			}else{
				map2.put("shop_price", "面议");
			}
			
		}		
		return list;
	}
	
	/**
	 * 商品管理 商品列表
	 * data:2018年2月8日 09:43:17
	 * 
	 */
	@Override
	public List<Map<String, Object>> getServiceList2(Map<String, Object> map) {
		List<Map<String, Object>> list = bizServiceMapper.getServiceList(map);
		return list;
	}

	/**
	 * 递归获得总分类名称
	 * data:2018年2月11日 14:55:06
	 * 
	 */
	String getServiceCategoryName(String category_id) {
		// TODO Auto-generated method stub			
		BizServiceCategory cate = bizServiceCategoryMapper.selectById(category_id);		
		if (cate.getParentCategoryId()==0||cate.getParentCategoryId()==null) {
			name = cate.getCategoryName()+"/"+name;
			return name;
		}else{
			name = cate.getCategoryName()+"/"+name;			
			return getServiceCategoryName(cate.getParentCategoryId().toString());
		}		
	}

	/**
	 * 商家管理基本信息
	 * data:2018年2月12日 15:03:27
	 * agent_id
	 */
	@Override
	public Map<String, Object> getServiceInfo(String agent_id) {
		// TODO Auto-generated method stub
		return bizServiceMapper.getServiceInfo(agent_id);
	}
	
	
	/**
	 * 商品详情插入
	 * data:2018年3月5日 14:22:33
	 * map
	 */
	@Override
	public void publish_service(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String goodsName = map.get("goodsName") + "";
		String goodsSecTitle = map.get("goodsSecTitle") + "";
		String goodsKeyWord = map.get("goodsKeyWord") + "";
		String content_web = map.get("content_web") + "";// 商品详情网页版
		String content_tel = map.get("content_tel") + "";// 商品详情手机版
		String contractSelectId =  map.get("contractSelectId") + "";//合同主体
		String agent_id = map.get("agent_id") + "";
		String categoryId = map.get("serviceCategary_id")+"";
		/*String shop_price = map.get("shop_price") + "";*/
		String formList = map.get("formList") + "";	
		String img_1 = map.get("img_1") + "";
		String img_2 = map.get("img_2") + "";
		String img_3 = map.get("img_3") + "";
		String img_4 = map.get("img_4") + "";
		String img_5 = map.get("img_5") + "";
		String img_6 = map.get("img_6") + "";
		String value = map.get("value")+"";
		
		
		//插入商品表
		BizService  bizService  = new BizService();
		bizService.setAgentId(Integer.parseInt(agent_id));
		bizService.setCreatedAt(new Date());
		bizService.setCategoryId(Integer.parseInt(categoryId));
		bizService.setName(goodsName);
		if (contractSelectId!=null&&!"".equals(contractSelectId.trim())) {
			bizService.setContractSelectId(Integer.parseInt(contractSelectId));
		}		
		bizService.setGoodsKeyWord(goodsKeyWord);
		bizService.setGoodsSecTitle(goodsSecTitle);
		bizService.setServiceInfo(content_web);
		bizService.setServiceInfoTel(content_tel);
		bizService.setRelease(1);
		bizService.setVerify(3);		
		bizService.setStatus(1);
		bizService.setListPic(img_1);
		bizService.setSelected(1);
		/*bizService.setShopPrice(new BigDecimal(shop_price));*/
		bizServiceMapper.insertService(bizService);
		System.out.println(bizService.getServiceId());
		
		//插入图片
		BizServiceImg img = new BizServiceImg();
		img.setIsDefault(2);
		img.setServiceId(bizService.getServiceId());
		img.setContent(img_2);
		bizserviceimgmapper.insert(img);		
		img.setContent(img_3);
		bizserviceimgmapper.insert(img);
		img.setContent(img_4);
		bizserviceimgmapper.insert(img);		
		img.setContent(img_5);
		bizserviceimgmapper.insert(img);		
		img.setContent(img_6);
		bizserviceimgmapper.insert(img);
		
		//循环插入字段值
		 JSONArray jsonArray = JSON.parseArray(formList);			 
		 //遍历方式2
	        for (Object obj : jsonArray) {
	            JSONObject jsonObject = (JSONObject) obj;
	            System.out.println(jsonObject.get("field_name")+":他的值"+jsonObject.get("value"));
	           BizServiceInfo bizServiceInfo = new BizServiceInfo();
	      }		
	        
	     //循环插入规格表
	     JSONArray jsonSec = JSON.parseArray(value);  
	     for (Object obj : jsonSec) {
	            JSONObject jsonObject = (JSONObject) obj;
	            System.out.println(jsonObject.get("fieldName")+":他的值"+jsonObject.get("specName")+"xxxx"+jsonObject.get("cate_id"));
	            BizSpecificationValue bizSpecificationValue = new BizSpecificationValue();
	            bizSpecificationValue.setAgentId(Integer.parseInt(agent_id));
	            bizSpecificationValue.setServiceId(bizService.getServiceId());
	            if (jsonObject.get("type").equals("1")) {
	            	bizSpecificationValue.setShopPrice(new BigDecimal(jsonObject.get("price")+""));
				}else{
					bizSpecificationValue.setShopPrice(new BigDecimal(0));
				}	           
	            bizSpecificationValue.setSpecificationId(Integer.parseInt(jsonObject.get("cate_id")+""));
	            bizSpecificationValue.setValue(jsonObject.toString());
	            bizSpecificationValueMapper.insert(bizSpecificationValue);
	      }		
	}

	/**
	 * 通过分类id 查询 服务
	 * data:2018年3月17日 15:01:03
	 * categoryId
	 */
	@Override
	public List<Map<String, Object>> getServiceByCate_id(Integer categoryId) {
		// TODO Auto-generated method stub
		return bizServiceMapper.getServiceByCate_id(categoryId);
	}	
		
	/**
	 * 服务编辑修改
	 * data:2018年3月21日 21:52:23
	 * request
	 */
	@Override
	public void update_service(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String service_id = map.get("service_id")+"";//商品id
		String goodsName = map.get("goodsName") + "";
		String goodsSecTitle = map.get("goodsSecTitle") + "";
		String goodsKeyWord = map.get("goodsKeyWord") + "";
		String content_web = map.get("content_web") + "";// 商品详情网页版
		String content_tel = map.get("content_tel") + "";// 商品详情手机版
		String contractSelectId =  map.get("contractSelectId") + "";//合同主体
		String agent_id = map.get("agent_id") + "";
		String categoryId = map.get("serviceCategary_id")+"";
		/*String shop_price = map.get("shop_price") + "";*/
		String formList = map.get("formList") + "";	
		String img_1 = map.get("img_1") + "";
		String img_2 = map.get("img_2") + "";
		String img_3 = map.get("img_3") + "";
		String img_4 = map.get("img_4") + "";
		String img_5 = map.get("img_5") + "";
		String img_6 = map.get("img_6") + "";
		String value = map.get("value")+"";
		BizService bizService = bizServiceMapper.selectById(Integer.parseInt(service_id));
		bizService.setAgentId(Integer.parseInt(agent_id));
		bizService.setCreatedAt(new Date());
		bizService.setCategoryId(Integer.parseInt(categoryId));
		bizService.setName(goodsName);
		if (contractSelectId!=null&&!"".equals(contractSelectId.trim())) {
			bizService.setContractSelectId(Integer.parseInt(contractSelectId));
		}		
		bizService.setGoodsKeyWord(goodsKeyWord);
		bizService.setGoodsSecTitle(goodsSecTitle);
		bizService.setServiceInfo(content_web);
		bizService.setServiceInfoTel(content_tel);
		bizService.setRelease(1);
		bizService.setVerify(3);		
		bizService.setStatus(1);
		bizService.setListPic(img_1);
		bizService.setSelected(1);
		/*bizService.setShopPrice(new BigDecimal(shop_price));*/
		bizServiceMapper.updateById(bizService);
		System.out.println(bizService.getServiceId());
		 Wrapper<BizServiceImg> imgs = new EntityWrapper<>();
		 imgs.eq("service_id", service_id);
		 bizserviceimgmapper.delete(imgs);
		 		 
		//插入图片
			BizServiceImg img = new BizServiceImg();
			img.setIsDefault(2);
			img.setServiceId(bizService.getServiceId());
			img.setContent(img_2);
			bizserviceimgmapper.insert(img);		
			img.setContent(img_3);
			bizserviceimgmapper.insert(img);
			img.setContent(img_4);
			bizserviceimgmapper.insert(img);		
			img.setContent(img_5);
			bizserviceimgmapper.insert(img);		
			img.setContent(img_6);
			bizserviceimgmapper.insert(img);
			
		 //删除规格表中的记录	
			 Wrapper<BizSpecificationValue> spec = new EntityWrapper<>();
			 spec.eq("service_id", service_id);
			 bizSpecificationValueMapper.delete(spec);
			
		 //循环插入规格表
	     JSONArray jsonSec = JSON.parseArray(value);  
	     for (Object obj : jsonSec) {
	            JSONObject jsonObject = (JSONObject) obj;
	            System.out.println(jsonObject.get("fieldName")+":他的值"+jsonObject.get("specName")+"xxxx"+jsonObject.get("cate_id"));
	            BizSpecificationValue bizSpecificationValue = new BizSpecificationValue();
	            bizSpecificationValue.setAgentId(Integer.parseInt(agent_id));
	            bizSpecificationValue.setServiceId(bizService.getServiceId());
	            if (jsonObject.get("type").equals("1")) {
	            	bizSpecificationValue.setShopPrice(new BigDecimal(jsonObject.get("price")+""));
				}else{
					bizSpecificationValue.setShopPrice(new BigDecimal(0));
				}	           
	            bizSpecificationValue.setSpecificationId(Integer.parseInt(jsonObject.get("cate_id")+""));
	            bizSpecificationValue.setValue(jsonObject.toString());
	            bizSpecificationValueMapper.insert(bizSpecificationValue);
	      }			
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
