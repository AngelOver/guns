package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.model.Setup;
import com.stylefeng.guns.common.persistence.dao.SetupMapper;
import com.stylefeng.guns.modular.system.service.ISetupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统设置 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-29
 */
@Service
public class SetupServiceImpl extends ServiceImpl<SetupMapper, Setup> implements ISetupService {
	
}
