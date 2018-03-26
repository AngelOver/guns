package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizEvaluate;
import com.stylefeng.guns.common.persistence.dao.BizEvaluateMapper;
import com.stylefeng.guns.modular.system.service.IBizEvaluateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-02-08
 */
@Service
public class BizEvaluateServiceImpl extends ServiceImpl<BizEvaluateMapper, BizEvaluate> implements IBizEvaluateService {

	@Autowired
	private BizEvaluateMapper bizEvaluateMapper;
	
	
	/**
	 * 根据代理商id获得评价列表
	 * data:2018年3月9日 14:19:34
	 * 
	 */
	@Override
	public List<Map<String, Object>> getEvaluateList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bizEvaluateMapper.getEvaluateList(map);
	}
	
}
