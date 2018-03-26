package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.BizOrders;
import com.stylefeng.guns.common.persistence.dao.BizOrdersMapper;
import com.stylefeng.guns.modular.system.service.IBizOrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-25
 */
@Service
public class BizOrdersServiceImpl extends ServiceImpl<BizOrdersMapper, BizOrders> implements IBizOrdersService {
	
}
