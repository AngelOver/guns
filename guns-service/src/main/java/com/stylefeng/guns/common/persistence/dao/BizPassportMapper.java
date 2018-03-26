package com.stylefeng.guns.common.persistence.dao;



import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.BizPassport;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-17
 */
public interface BizPassportMapper extends BaseMapper<BizPassport> {
	
	public BizPassport selectByMobile(String mobile);

}