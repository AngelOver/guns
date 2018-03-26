package com.stylefeng.guns.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizInvoice;
import com.stylefeng.guns.common.persistence.dao.BizInvoiceMapper;
import com.stylefeng.guns.modular.system.service.IBizInvoiceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-06
 */
@Service
public class BizInvoiceServiceImpl extends ServiceImpl<BizInvoiceMapper, BizInvoice> implements IBizInvoiceService {
	
	@Autowired
	private BizInvoiceMapper bizInvoiceMapper;

	
  /**
	* 发票列表
	* data:2018年3月6日 15:06:00
	* 
	*/
	@Override
	public List<Map<String, Object>> getInvoiceList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		 /*String id = map.get("id")+"";
		 String type = map.get("type")+"";
		 String begin_time = map.get("begin_time")+"";
		 String end_time = map.get("end_time")+"";
		 String order_num = map.get("order_num")+"";
		 String service_type = map.get("service_type")+"";
		 String apply_name = map.get("apple_name")+"";*/		
		return  bizInvoiceMapper.getInvoiceList(map);
	}
	
}
