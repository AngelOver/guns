package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.BizServiceFieldValue;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-22
 */
public interface BizServiceFieldValueMapper extends BaseMapper<BizServiceFieldValue> {

	
	/**
	 * 增加	 
	 * @author dengshuang
	 * @Date 2018年1月26日 11:02:26
	 */
	void insertValue(BizServiceFieldValue values);

}