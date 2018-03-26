package com.stylefeng.guns.common.persistence.dao;


import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author dengshuang
 * @since 2018-01-19
 */

public interface BizServiceCategoryMapper extends BaseMapper<BizServiceCategory> {

	List<Map<String, Object>> findTopCategoryList();
	

}