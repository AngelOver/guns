--0206  biz_agent update

alter table biz_agent add recommend tinyint(20)  default 0 comment  '推荐'; 
alter table biz_agent add authentication varchar(255) comment '认证'; 
alter table biz_agent add position varchar(255) comment '职位'; 
alter table biz_agent add imgUrl varchar(255) comment '照片';
alter table biz_agent add business varchar(500) comment '负责业务'; 
alter table biz_agent add experience varchar(500) comment '工作经验'; 
alter table biz_agent add customer varchar(500) comment '服务客户'; 
alter table biz_agent add expertise varchar(500) comment '专长'; 
alter table guns.biz_agent add  example varchar(500) comment '案例'; 
alter table guns.biz_agent add  example2 varchar(500) comment '案例2';


--0305 biz_demands_quote
alter table guns.biz_demands_quote add  passport_id int(11) comment '会员ID';
