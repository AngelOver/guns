package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizInvoice;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-06
 */
public interface IBizInvoiceService extends IService<BizInvoice> {

  /**
	* 发票列表
	* data:2018年3月6日 15:06:00
	* 
	*/
	List<Map<String, Object>> getInvoiceList(Map<String, Object> map);
	
}
