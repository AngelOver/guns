package com.stylefeng.guns.modular.noauth;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.fastdfs.ClientGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizBranch;
import com.stylefeng.guns.common.persistence.model.BizLinks;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.common.persistence.model.Setup;
import com.stylefeng.guns.modular.auth.util.FastDFSClient;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.dao.BizServiceCategoryDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizBranchService;
import com.stylefeng.guns.modular.system.service.IBizLinksService;
import com.stylefeng.guns.modular.system.service.IBizPaternerService;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.stylefeng.guns.modular.system.service.ISetupService;

/**
*
*首页公共接口：友情连接 分公司信息 公司介绍
* @author dengshuang
*
*/
@RestController
@RequestMapping("/noauth")
public class CommonWebController {
	
	 @Autowired
	 private ISetupService setupService;
	 
	 @Autowired
	 private IBizLinksService bizLinksService;
	    
	 @Autowired
	 private IBizPaternerService bizPaternerService;
	 
	 @Autowired
	 private IBizBranchService bizBranchService;
	 
	 @Autowired
	 private IBizServiceCategoryService bizServiceCategoryService;
	 
	 @Resource
	 BizServiceCategoryDao bizServiceCategoryDao;
	 
	 @Autowired
	 IBizAgentService bizAgentService;
	 
	 /**
	  * 公司介绍(LOGO 公司名称  介绍)
	  * data:2018年2月3日 11:22:53
	  */
	  @RequestMapping(value = "/companyList")
	  @ResponseBody
	  public Object companyList(HttpServletRequest request, HttpServletResponse response) {	  
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		Map<String, Object> map =new HashMap<String, Object>();
		List<Setup> list = setupService.selectList(null);
		map.put("datas", list);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
	    return map;		   
	  }
	  
	  /**
	   * 分公司信息(地址 电话 名称)
	   * data:2018年2月3日 11:34:47
	   */
	  @RequestMapping(value = "/branchList")
	  @ResponseBody
	  public Object branchList(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String, Object> map =new HashMap<String, Object>();	
		 List<BizBranch> list = bizBranchService.selectList(null);
		 map.put("datas", list);
		 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 map.put(Util.MESSAGE, "操作成功");
		 return map;
	  }
	  
	  /**
	   * 链接信息(url 名称)
	   * data:2018年2月5日 16:27:14
	   */
	  @RequestMapping(value = "/linkList")
	  @ResponseBody
	  public Object bizPaternrList(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String, Object> map =new HashMap<String, Object>();
		 List<BizLinks> list = bizLinksService.selectList(null);
		 map.put("datas", list);
		 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 map.put(Util.MESSAGE, "操作成功");
		 return map;
	  }
	  
	  /**
	   * 公司的基本信息
	   * data:2018年2月5日 17:33:37
	   * 服务热线 公司地址 公司邮箱
	   */
	  @RequestMapping(value = "/companyInfo")
	  @ResponseBody
	  public Object companyInfo(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String, Object> map =new HashMap<String, Object>();
		 List<BizLinks> list = bizLinksService.selectList(null);
		 map.put("datas", list);
		 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 map.put(Util.MESSAGE, "操作成功");
		 return map;
		 
	  }  
	  
	  /**
	   * 服务商首页的顶级分类
	   * data:2018年2月6日 14:28:09
	   * 分类名称  id
	   */
	  @RequestMapping(value = "/categoryList")
	  @ResponseBody
	  public Object categoryList(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String, Object> result =new HashMap<String, Object>();
		 List<Map<String ,Object>> list = new ArrayList<Map<String,Object>>();
		/* if (id!=null&&!"".equals(id.trim())) {*/
			  list =  bizServiceCategoryDao.findTopCategoryList();
			  for (Map<String, Object> map : list) {
				  String category_id = map.get("category_id")+"";
				  if (category_id!=null&&!"".equals(category_id.trim())) {
					  Wrapper<BizServiceCategory> wrapper = new EntityWrapper<>();
					  wrapper.eq("parent_category_id", category_id);
					  wrapper.eq("status", 1);
					  List<BizServiceCategory> Category = bizServiceCategoryService.selectList(wrapper);
					  map.put("list", Category);
					  result.put("datas", list.subList(0, 6));
					  result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
					  result.put(Util.MESSAGE, "操作成功");
				}			 
			}
		/* }else{
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }		 */		
		 return result;
	  }  
	  
	  /**
	   * 获取代理商的个人信息
	   * data:2018年3月9日 10:14:22
	   * 代理商id agent_id
	   */
	  @RequestMapping(value = "/getAgentInfo")
	  @ResponseBody
	  public Object getAgentInfo(HttpServletRequest request, HttpServletResponse response) {
			 response.setHeader("Access-Control-Allow-Origin", "*"); 
			 Map<String, Object> result =new HashMap<String, Object>();
			 String agent_id = request.getParameter("agent_id");
			 if (agent_id!=null&&!"".equals(agent_id.trim())) {
				 BizAgent agent = bizAgentService.selectById(Integer.parseInt(agent_id));
				 result.put("datas", agent);
			     result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			     result.put(Util.MESSAGE, "操作成功");
			 }else{
				 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
				 result.put(Util.MESSAGE, "参数不对");
			 }
			 return result;
	  }
	  
	  
	  /**
		 * 上传图片(上传到项目的webapp/static/img)
		 */
		@RequestMapping(value = "/upload")
		@ResponseBody
		public String upload(@RequestPart("file") MultipartFile picture,
				HttpServletRequest request, HttpServletResponse response) {
			System.out.println("进来了");
			response.setHeader("Access-Control-Allow-Origin", "*"); 
			//String path = request.getServletContext().getRealPath("");// 获取项目动态绝对路径
			String name = picture.getName()+UUID.randomUUID();
			File tempFile = null ;
			String fid = "";
			try {
				// 这里处理业务逻辑
				//获取配制的服务器文件
				ClientGlobal.init("fdfs_client.conf");
				InetAddress ip =  ClientGlobal.getG_tracker_group().tracker_servers[0].getAddress();
				tempFile = File.createTempFile(name, "temp");
				picture.transferTo(tempFile);
				Map<String, String> metaList = new HashMap<String, String>();
				// metaList.put("width","1024");
				// metaList.put("height","768");
				// metaList.put("author","杨信");
				// metaList.put("date","20161018");
				fid = FastDFSClient.uploadFile(tempFile, name, metaList);
				//fid = "http:\\\\"+ip.getHostAddress()+"\\"+fid;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭临时文件
				tempFile.deleteOnExit(); // 程序退出时删除临时文件
			}
			return fid;
		}
	  
	  
	  /**
	   * 测试方法 循环插入数据
	   * data:2018年2月7日 17:34:35	 
	   * 分类名称  id
	   */
	/*  @RequestMapping(value = "/demo")
	  @ResponseBody
	  public void demo(String id,HttpServletRequest request, HttpServletResponse response) {
		  String [] str = {"国内商标注册","商标担保注册","国际商标注册","商标注册","商标续展","商标转让","商标许可","补发商标注册证","商标变更"};	
		  String [] img = {"zhu.png","dan.png","guoji.png","shangbiao.png","xu.png","zhuan.png","xuke.png","bufa.png","bian.png"};	
		 
			  for (int j = 0; j < str.length; j++) {
				  BizServiceCategory index = new BizServiceCategory();
				  index.setParentCategoryId(10);
				  index.setAgentId(1);
				  index.setStatus(1);
				  index.setDescribe("");
				  index.setDetails("");
				  index.setSort(j+1);	
				  index.setCategoryName(str[j]);
				  index.setCategoryImg(img[j]);
				  bizServiceCategoryService.insert(index);
			}			 
		
	  }*/
	  
	  
	 /* public static void main(String[] args) {
		  String [] str = {"国内商标注册","商标担保注册","国际商标注册","商标注册","商标续展","商标转让","商标许可","补发商标注册证","商标变更"};
		  for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
	}*/
}
