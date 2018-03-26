package com.stylefeng.guns.schedule;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.common.persistence.model.BizCoupons;
import com.stylefeng.guns.common.persistence.model.BizNews;
import com.stylefeng.guns.modular.system.service.IBizCouponsService;
import com.stylefeng.guns.modular.system.service.IBizNewsService;
import com.stylefeng.guns.modular.system.service.IBizPassportCouponService;

@Component
@EnableScheduling
public class ScheduledTasks {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
    private IBizNewsService bizNewsService;
	
	@Autowired
	private IBizCouponsService bizCouponsService;

    @Autowired
    private IBizPassportCouponService bizPassportCouponService;
	
	
	@Scheduled(cron="0 0 23 * * ? ")
	@Transactional
	public void  checkAttend() throws IOException{
		
		logger.info("..................2018新闻爬取...............");
				
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpGet httpget = new HttpGet("https://news.wtoip.com/"); // 创建httpget实例
         
        CloseableHttpResponse respon = httpclient.execute(httpget); // 执行get请求
        HttpEntity entity=respon.getEntity(); // 获取返回实体
        String content=EntityUtils.toString(entity, "utf-8");
        respon.close(); // 关闭流和释放系统资源
        
        try {
			Document doc2=Jsoup.parse(content); // 解析网页 得到文档对象
			Elements listDiv = doc2.getElementsByAttributeValue("class","main_left_content_item clear");
			
			ArrayList<BizNews> list_news = new ArrayList<BizNews>();
			for(Element element : listDiv){
			  Elements links = element.getElementsByTag("a");

			  String linkHref = links.get(0).attr("href");

			  Document  doc = Jsoup.connect("https:"+linkHref).ignoreContentType(true).get();
			  
			  Elements list = doc.getElementsByAttributeValue("class","main_left_article");
			  for(Element ment:list){
			  Elements title = ment.getElementsByTag("h1");   
			  logger.info(title.text());
			  Elements contents = doc.getElementsByAttributeValue("class","j-article_content");          
			  Elements imgs = contents.get(0).getElementsByTag("img");
			  String img = "https:"+imgs.get(0).attr("src");
			  
			  logger.info("图片地址："+img);
			  
			  BizNews news = new BizNews();
			  news.setTitle(title.text());
			  news.setCover(img);
			  news.setContent(contents.get(0).html());
			  news.setStatus(0);
			  news.setApplyStatus(0);
			  news.setCreatedTime(new Date());
			  
			  list_news.add(news);
			        
			  }
			}
			bizNewsService.insertBatch(list_news);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("=====爬取新闻失败！======:"+e.getMessage());
		}
        
        
    }      
	
	
	//优惠券定时器  定时取消过期的优惠券	
	@Scheduled(cron = "0 0 12 * * ?")
	public void CancelExpireCoupons(){	
		logger.info("=============测试定时器的并且测试日志=============");
		logger.info("=============开始自动取消过期优惠卷=============");
		try {
			//全部的list
			List<BizCoupons> list = bizCouponsService.selectList(null);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
			for (BizCoupons bizCoupons : list) {
				//获得没有过期的优惠券
				if (bizCoupons.getStatus()==1) {									
					//得到有效时间
					if (bizCoupons.getExpires()!=null&&!"".equals(bizCoupons.getExpires().trim())) {
						String expires = bizCoupons.getExpires();
						//获得结束时间
						String[] split = expires.split("~");
						String end_time = split[1];
						Date et=sdf.parse(end_time);
						//时间已经结束
						if (et.before(new Date())) {
							bizCoupons.setStatus(2);
							bizCouponsService.updateById(bizCoupons);
							logger.info("优惠券    "+bizCoupons.getCouponName()+"    已经过期自动取消"+"取消时间:  "+new Date());						
						}
					}	
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("=============时间转换异常=============");
		}
	}
}