package com.stylefeng.guns.modular.system.service.impl;


import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.dao.BizServiceCategoryMapper;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengshuang
 * @since 2018-01-19
 */
@Service
public class BizServiceCategoryServiceImpl extends ServiceImpl<BizServiceCategoryMapper, BizServiceCategory> implements IBizServiceCategoryService {

	@Autowired
	private BizServiceCategoryMapper bizServiceCategoryMapper;
	
	@Override
	public List<Map<String, Object>> findTopCategoryList() {
		// TODO Auto-generated method stub
		return bizServiceCategoryMapper.findTopCategoryList();
	}
	
}
