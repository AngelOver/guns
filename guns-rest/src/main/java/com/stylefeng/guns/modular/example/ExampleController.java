package com.stylefeng.guns.modular.example;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.common.SimpleObject;
import com.stylefeng.guns.common.persistence.dao.BizBannerMapper;
import com.stylefeng.guns.common.persistence.dao.UserMapper;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {
		
	
   @Resource
    private UserMapper userMapper;
   
   @Resource
   private  BizBannerMapper bizBannerMapper;

    @RequestMapping("")
    public ResponseEntity hello( @RequestBody SimpleObject simpleObject) {
        System.out.println(simpleObject.getUser());
        List user = bizBannerMapper.selectList(null);
        System.out.println(user.size());
        return ResponseEntity.ok("请求成功!");
    }
    
    @RequestMapping("test")
    @ResponseBody
    public String   test(@RequestBody String name){
    	System.out.println(name);
    	return "请求成功!";
    }
    
    
    
}
