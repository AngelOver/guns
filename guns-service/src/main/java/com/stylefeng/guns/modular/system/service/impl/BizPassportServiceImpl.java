package com.stylefeng.guns.modular.system.service.impl;
import javax.annotation.Resource;


import com.stylefeng.guns.common.persistence.dao.BizPassportMapper;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dengshuang
 * @since 2018-01-17
 */
@Service
public class BizPassportServiceImpl extends ServiceImpl<BizPassportMapper, BizPassport> implements IBizPassportService {

	@Resource
	BizPassportMapper bizPassportMapper;
	
	@Override
	public BizPassport selectByMobile(String mobile) {
		
		return bizPassportMapper.selectByMobile(mobile);
		
	}
	
}
