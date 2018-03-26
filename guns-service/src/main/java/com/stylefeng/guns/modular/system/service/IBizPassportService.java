package com.stylefeng.guns.modular.system.service;


import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.BizPassport;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengshung
 * @since 2018-01-17
 */
public interface IBizPassportService extends IService<BizPassport> {
	
	public BizPassport  selectByMobile(String mobile);
}
