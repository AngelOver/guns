package com.stylefeng.guns.modular.system.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dengshuang
 * @since 2018-01-19
 */
public interface IBizServiceCategoryService extends IService<BizServiceCategory> {

	List<Map<String, Object>> findTopCategoryList();
		
}
