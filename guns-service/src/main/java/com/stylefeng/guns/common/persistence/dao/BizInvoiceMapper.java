package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.BizInvoice;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-06
 */
public interface BizInvoiceMapper extends BaseMapper<BizInvoice> {

  /**
	* 发票列表
	* data:2018年3月6日 15:06:00
	* 
	*/
	List<Map<String, Object>> getInvoiceList(@Param("map")Map<String, Object> map);

}