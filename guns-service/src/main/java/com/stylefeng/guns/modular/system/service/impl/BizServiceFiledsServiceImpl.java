package com.stylefeng.guns.modular.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.common.persistence.model.BizServiceFieldValue;
import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.stylefeng.guns.common.persistence.dao.BizServiceFieldValueMapper;
import com.stylefeng.guns.common.persistence.dao.BizServiceFiledsMapper;
import com.stylefeng.guns.modular.system.dao.BizServiceFieldDao;
import com.stylefeng.guns.modular.system.service.IBizServiceFiledsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-22
 */
@Service
@Transactional
public class BizServiceFiledsServiceImpl extends ServiceImpl<BizServiceFiledsMapper, BizServiceFileds> implements IBizServiceFiledsService {

	@Resource
	BizServiceFieldDao bizServiceFiledsDao;
	
	@Resource
	BizServiceFieldValueMapper bizServiceFieldValueMapper;
	
	@Resource
	BizServiceFiledsMapper bizServiceFiledsMapper;
	
	@Autowired 
	private BizServiceServiceImpl bizserviceserviceimpl;
	
	/**
	 * 创建分类规格	 
	 * @author dengshuang
	 * @Date 2018年1月25日 11:00:18
	 */
	@Override
	public void createServiceFiled(BizServiceFileds bizServiceFileds,
			String dictValues) {
		// TODO Auto-generated method stub	
		//插入字段表
		bizServiceFiledsDao.insertField(bizServiceFileds);
		//插入对应的value表
		if (dictValues!=null&&!"".equals(dictValues.trim())) {
			BizServiceFieldValue value = new BizServiceFieldValue();
			value.setFiledId(bizServiceFileds.getFiledId());
			value.setFieldName(bizServiceFileds.getFieldName());
			value.setServiceId(bizServiceFileds.getCategoryId());
			value.setValue(dictValues);
			value.setFieldType(bizServiceFileds.getType());
			bizServiceFieldValueMapper.insertValue(value);
		}					
	}

	/**
	 * 修改分类规格	 
	 * @author dengshuang
	 * @Date 2018年1月26日 10:05:29
	 */
	@Override
	public void updateField(BizServiceFileds bizServiceFileds, String dictValues) {
		// TODO Auto-generated method stub
		//更新字段表
		bizServiceFiledsMapper.updateById(bizServiceFileds);
		//更新value表		
		 if (dictValues!=null&&!"".equals(dictValues.trim())) {
			 BizServiceFieldValue value = bizServiceFieldValueMapper.selectById(bizServiceFileds.getFiledId());
			   //如果有value值 就修改  没有加新增
			 	if (value==null) {
			 		BizServiceFieldValue values = new BizServiceFieldValue();
					values.setFiledId(bizServiceFileds.getFiledId());
					values.setFieldName(bizServiceFileds.getFieldName());
					values.setServiceId(bizServiceFileds.getCategoryId());
					values.setValue(dictValues);
					values.setFieldType(bizServiceFileds.getType());
					bizServiceFieldValueMapper.insertValue(values);
			 	}else{
			 		value.setFiledId(bizServiceFileds.getFiledId());
					value.setFieldName(bizServiceFileds.getFieldName());
					value.setServiceId(bizServiceFileds.getCategoryId());
					value.setValue(dictValues);
					value.setFieldType(bizServiceFileds.getType());
					bizServiceFieldValueMapper.updateById(value);
			 	}			 	
		}				
	}

	/**
	 * 发布商品属性	 
	 * @author dengshuang
	 * @Date 2018年3月1日 09:52:25
	 */
	@Override
	public Map<String, Object> getGoodFiled(Map<String, Object> result) {
		// TODO Auto-generated method stub		
		Map<String, Object> map = new HashMap<String,Object>();		
		List<Map<String, Object>> list = bizServiceFiledsMapper.getGoodFiled(result);
		for (Map<String, Object> map2 : list) {
			String type = map2.get("type")+"";
			if (type.equals("3")) {
				List<Map<String, Object>> checkList = new ArrayList<Map<String, Object>>();				
				String value = map2.get("value")+"";
				if (value!=null&&!"".equals(value.trim())) {
					String[] split = value.split(";");
					for (int i = 0; i < split.length; i++) {
						Map<String, Object> checkMap = new HashMap<String,Object>();
						checkMap.put("name", split[i]);
						checkMap.put("id", map2.get("filed_id"));
						checkMap.put("check", false);
						checkList.add(checkMap);
					}					
				}
				map2.put("value", checkList);
			}	
			if (type.equals("2")) {
				List<Map<String, Object>> checkList = new ArrayList<Map<String, Object>>();				
				String value = map2.get("value")+"";
				if (value!=null&&!"".equals(value.trim())) {
					Map<String, Object> def = new HashMap<String,Object>();
					def.put("name", "请选择");
					def.put("id", "");
					def.put("check", false);
					checkList.add(def);
					String[] split = value.split(";");
					for (int i = 0; i < split.length; i++) {
						Map<String, Object> checkMap = new HashMap<String,Object>();
						checkMap.put("name", split[i]);
						checkMap.put("id", i);
						checkMap.put("check", false);
						checkList.add(checkMap);
					}					
				}
				map2.put("value", checkList);
			}
		}
		map.put("list", list);				
		String name = bizserviceserviceimpl.getServiceCategoryName(result.get("serviceCategary_id")+"");
		map.put("name", name.replace('/', '>'));	
		bizserviceserviceimpl.setName("");
		return map;
	}
	
}
