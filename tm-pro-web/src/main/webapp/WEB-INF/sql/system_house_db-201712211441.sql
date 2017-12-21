/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : system_house_db

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-12-21 14:41:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_authorization
-- ----------------------------
DROP TABLE IF EXISTS `tb_authorization`;
CREATE TABLE `tb_authorization` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pid` int(10) unsigned DEFAULT NULL COMMENT '父级模块ID',
  `module_name` varchar(50) NOT NULL COMMENT '模块名称',
  `module_introduce` varchar(200) DEFAULT NULL COMMENT '模块说明',
  `authorization_code` varchar(100) DEFAULT NULL COMMENT '权限字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `authorization_code` (`authorization_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='权限字';

-- ----------------------------
-- Records of tb_authorization
-- ----------------------------
INSERT INTO `tb_authorization` VALUES ('1', null, '用户模块', '管理系统用户信息', null);
INSERT INTO `tb_authorization` VALUES ('2', '1', '用户管理', '用户管理', 'auth_user_manager');
INSERT INTO `tb_authorization` VALUES ('3', null, '发布模块', '发布更新数据', null);
INSERT INTO `tb_authorization` VALUES ('4', '3', '更新系统缓存数据', '使用ApplicationEvent事件机制更新系统缓存数据', 'auth_publisher_manager');
INSERT INTO `tb_authorization` VALUES ('5', null, '权限模块', '系统权限控制', null);
INSERT INTO `tb_authorization` VALUES ('6', '5', '角色管理', '系统角色管理', 'auth_role_manager');
INSERT INTO `tb_authorization` VALUES ('7', '1', '组织管理', '系统用户组织管理', 'auth_organization_manager');
INSERT INTO `tb_authorization` VALUES ('8', '9', '系统配置', '系统基本信息配置', 'auth_system_info_manager');
INSERT INTO `tb_authorization` VALUES ('9', null, '系统设置', '系统相关管理', null);
INSERT INTO `tb_authorization` VALUES ('10', '9', '任务调度', '任务调度管理', 'auth_job_manager');

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `type` varchar(4) NOT NULL COMMENT '类别',
  `pic_url` varchar(200) NOT NULL COMMENT '图片地址',
  `link_url` varchar(200) NOT NULL COMMENT '跳转链接',
  `status` varchar(4) NOT NULL DEFAULT 'N' COMMENT '展示状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源横幅滚动图表';

-- ----------------------------
-- Records of tb_banner
-- ----------------------------

-- ----------------------------
-- Table structure for tb_base_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_base_city`;
CREATE TABLE `tb_base_city` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) NOT NULL COMMENT '城市编码',
  `pcode` varchar(20) NOT NULL COMMENT '父级城市编码',
  `name` varchar(50) NOT NULL COMMENT '城市名称',
  `pinyin` varchar(500) NOT NULL COMMENT '城市名称拼音',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础城市数据';

-- ----------------------------
-- Records of tb_base_city
-- ----------------------------

-- ----------------------------
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统字典主键',
  `name` varchar(50) NOT NULL COMMENT '字典名称',
  `visit_code` varchar(100) NOT NULL COMMENT '字典访问编码',
  `remarks` varchar(255) DEFAULT NULL COMMENT '字典说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `visit_code` (`visit_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of tb_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for tb_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary_item`;
CREATE TABLE `tb_dictionary_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '字典项ID',
  `dict_id` int(10) unsigned NOT NULL COMMENT '所属字典',
  `name` varchar(32) NOT NULL COMMENT '字典项名称',
  `value` varchar(80) NOT NULL COMMENT '字典项值',
  PRIMARY KEY (`id`),
  KEY `dictionary_id` (`dict_id`),
  CONSTRAINT `tb_dictionary_item_ibfk_1` FOREIGN KEY (`dict_id`) REFERENCES `tb_dictionary` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典项';

-- ----------------------------
-- Records of tb_dictionary_item
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house
-- ----------------------------
DROP TABLE IF EXISTS `tb_house`;
CREATE TABLE `tb_house` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '房源ID',
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `pinyin` varchar(500) NOT NULL COMMENT '项目拼音',
  `feature` varchar(180) NOT NULL COMMENT '项目特色',
  `category` varchar(10) NOT NULL COMMENT '类别',
  `property_type` varchar(10) NOT NULL COMMENT '物业类型',
  `property_right` varchar(50) NOT NULL COMMENT '产权',
  `acreage` varchar(50) NOT NULL COMMENT '（户型）面积范围',
  `open_quotation_date` datetime NOT NULL COMMENT '开盘日期',
  `handed_house_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '交房时间',
  `province` varchar(20) NOT NULL COMMENT '省份',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `county` varchar(20) NOT NULL COMMENT '（区）县',
  `location` varchar(200) NOT NULL COMMENT '项目位置',
  `longitude` varchar(32) NOT NULL COMMENT '（地理位置）经度',
  `latitude` varchar(32) NOT NULL COMMENT '（地理位置）纬度',
  `sales_offices_location` varchar(200) NOT NULL COMMENT '售楼处地址',
  `sales_hotline` varchar(20) NOT NULL COMMENT '销售热线',
  `average_price` decimal(20,2) NOT NULL COMMENT '房源均价（单位价格）',
  `down_payment` varchar(100) NOT NULL COMMENT '首付信息',
  `developer` varchar(80) NOT NULL COMMENT '开发商',
  `builder` varchar(80) NOT NULL COMMENT '施工单位',
  `property_company` varchar(80) NOT NULL COMMENT '物业公司',
  `planning_area` varchar(50) NOT NULL COMMENT '规划面积',
  `covered_area` varchar(50) NOT NULL COMMENT '建筑面积',
  `parking` varchar(50) NOT NULL COMMENT '停车位信息',
  `building_amount` int(10) unsigned NOT NULL COMMENT '栋数',
  `house_amount` int(10) unsigned NOT NULL COMMENT '户数',
  `decoration` varchar(80) NOT NULL COMMENT '装修状况',
  `plot_ratio` varchar(20) NOT NULL COMMENT '容积率',
  `greening_ratio` varchar(20) NOT NULL COMMENT '绿化率',
  `sale_status` varchar(4) NOT NULL DEFAULT 'N' COMMENT '销售状态',
  `show_status` varchar(4) NOT NULL DEFAULT 'N' COMMENT '展示状态',
  `operator` varchar(50) NOT NULL COMMENT '最后操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源基础信息表';

-- ----------------------------
-- Records of tb_house
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_bwzf
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_bwzf`;
CREATE TABLE `tb_house_bwzf` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮我找房';

-- ----------------------------
-- Records of tb_house_bwzf
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_consult
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_consult`;
CREATE TABLE `tb_house_consult` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '房源ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户名称',
  `phone_no` varchar(20) NOT NULL COMMENT '手机号',
  `return_visit_status` varchar(4) NOT NULL COMMENT '回访状态',
  `return_visit_result` varchar(255) NOT NULL COMMENT '回访结果',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源预约咨询信息表';

-- ----------------------------
-- Records of tb_house_consult
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_dynamic`;
CREATE TABLE `tb_house_dynamic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL COMMENT '房源ID',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `author` varchar(80) NOT NULL COMMENT '作者（来源）',
  `introduction` varchar(255) NOT NULL COMMENT '引言',
  `content` text NOT NULL COMMENT '正文',
  `publish_date` datetime NOT NULL COMMENT '发布时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源动态';

-- ----------------------------
-- Records of tb_house_dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_hot
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_hot`;
CREATE TABLE `tb_house_hot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL COMMENT '房源ID',
  `hot_degree` int(10) unsigned NOT NULL COMMENT '热度（排序）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热门房源信息';

-- ----------------------------
-- Records of tb_house_hot
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_layout
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_layout`;
CREATE TABLE `tb_house_layout` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL COMMENT '房源ID',
  `name` varchar(50) NOT NULL COMMENT '户型名称',
  `acreage` decimal(10,2) NOT NULL COMMENT '面积',
  `price` decimal(20,2) NOT NULL COMMENT '价格（总价）',
  `support_facility` varchar(255) NOT NULL COMMENT '配套设施',
  `living_amount` int(10) unsigned NOT NULL COMMENT '居室',
  `hall_amount` int(10) unsigned NOT NULL COMMENT '厅数',
  `kitchen_amount` int(10) unsigned NOT NULL COMMENT '厨房数量',
  `washroom_amount` int(10) unsigned NOT NULL COMMENT '卫生间数量',
  `show_status` varchar(4) NOT NULL DEFAULT 'N' COMMENT '展示状态',
  `sale_status` varchar(4) NOT NULL DEFAULT 'N' COMMENT '销售状态',
  `operator` varchar(50) NOT NULL COMMENT '最后操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源户型信息';

-- ----------------------------
-- Records of tb_house_layout
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_pic`;
CREATE TABLE `tb_house_pic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL COMMENT '房源ID',
  `layout_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '户型ID',
  `category` varchar(10) NOT NULL COMMENT '类别',
  `picture_url` varchar(200) NOT NULL COMMENT '图片访问地址',
  `description` varchar(180) NOT NULL COMMENT '描述',
  `operator` varchar(50) NOT NULL COMMENT '最后操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源图片信息';

-- ----------------------------
-- Records of tb_house_pic
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_price_trend
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_price_trend`;
CREATE TABLE `tb_house_price_trend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL COMMENT '房源ID',
  `price` decimal(20,2) NOT NULL COMMENT '价格',
  `average_price` decimal(20,2) NOT NULL COMMENT '均价',
  `update_time` datetime NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源价格趋势';

-- ----------------------------
-- Records of tb_house_price_trend
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_qa
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_qa`;
CREATE TABLE `tb_house_qa` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `question` varchar(200) NOT NULL COMMENT '问题',
  `answer` text NOT NULL COMMENT '回答',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源问答';

-- ----------------------------
-- Records of tb_house_qa
-- ----------------------------

-- ----------------------------
-- Table structure for tb_house_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_house_tag`;
CREATE TABLE `tb_house_tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `house_id` int(10) unsigned NOT NULL COMMENT '房源ID',
  `tag_id` int(10) unsigned NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源标签关联';

-- ----------------------------
-- Records of tb_house_tag
-- ----------------------------

-- ----------------------------
-- Table structure for tb_job
-- ----------------------------
DROP TABLE IF EXISTS `tb_job`;
CREATE TABLE `tb_job` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job_id` varchar(20) NOT NULL COMMENT '任务ID',
  `job_name` varchar(50) NOT NULL COMMENT '任务（作业）名称',
  `group_id` int(10) unsigned NOT NULL COMMENT '所在组',
  `status` enum('NORMAL','NONE') NOT NULL COMMENT '任务状态',
  `cron` varchar(50) NOT NULL COMMENT '定时任务时间表达式',
  `clazz_name` varchar(100) NOT NULL COMMENT '作业类',
  `is_concurrent` tinyint(1) NOT NULL COMMENT '是否允许并发执行同一个任务',
  `is_startup_execution` tinyint(1) NOT NULL COMMENT '是否项目启动时自执行',
  `description` varchar(255) NOT NULL COMMENT '描述',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of tb_job
-- ----------------------------
INSERT INTO `tb_job` VALUES ('1', 'J-01', '默认', '1', 'NORMAL', '0/1 * * * * ?', 'org.tm.pro.web.quartz.job.impl.BaseJob', '0', '1', '默认任务', '1511420746686', '1511420746686');

-- ----------------------------
-- Table structure for tb_job_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_job_group`;
CREATE TABLE `tb_job_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(20) NOT NULL COMMENT '任务（作业）组ID',
  `group_name` varchar(50) NOT NULL COMMENT '任务（作业）组名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='定时任务组';

-- ----------------------------
-- Records of tb_job_group
-- ----------------------------
INSERT INTO `tb_job_group` VALUES ('1', 'G-01', '默认分组');

-- ----------------------------
-- Table structure for tb_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '登录日志ID',
  `login_name` varchar(50) NOT NULL COMMENT '登录名称',
  `login_pass` varchar(100) NOT NULL COMMENT '登录密码',
  `operating_system` varchar(50) NOT NULL COMMENT '操作系统',
  `browser` varchar(50) NOT NULL COMMENT '浏览器',
  `browser_version` varchar(50) NOT NULL COMMENT '浏览器版本',
  `login_result` varchar(200) NOT NULL COMMENT '登录结果',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6114 DEFAULT CHARSET=utf8 COMMENT='登录日志记录表';

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------
INSERT INTO `tb_login_log` VALUES ('6052', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-12 14:05:37');
INSERT INTO `tb_login_log` VALUES ('6053', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-12 14:10:07');
INSERT INTO `tb_login_log` VALUES ('6054', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-12 14:10:32');
INSERT INTO `tb_login_log` VALUES ('6055', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-12 14:10:40');
INSERT INTO `tb_login_log` VALUES ('6056', 'sizhongxia', '123456', 'Windows', 'Mozilla', 'Unknown', '登录成功', '2017-11-12 14:10:59');
INSERT INTO `tb_login_log` VALUES ('6057', 'sizhongxia', '123456', 'Windows', 'Chrome', '52.0.2743.116', '登录成功', '2017-11-12 14:11:35');
INSERT INTO `tb_login_log` VALUES ('6058', 'sizhongxia', '123456', 'Windows', 'Chrome', '52.0.2743.116', '登录成功', '2017-11-12 14:12:04');
INSERT INTO `tb_login_log` VALUES ('6059', 'sizhongxia', '123456', 'Windows', 'Chrome', '52.0.2743.116', '登录成功', '2017-11-12 14:12:09');
INSERT INTO `tb_login_log` VALUES ('6060', 'sizhongxia', '123456', 'Android', 'Chrome', '53.0.2785.49', '登录成功', '2017-11-12 14:13:15');
INSERT INTO `tb_login_log` VALUES ('6061', 'sizhongxia', '123456', 'Android', 'Chrome', '53.0.2785.49', '登录成功', '2017-11-12 15:04:18');
INSERT INTO `tb_login_log` VALUES ('6062', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-12 15:06:03');
INSERT INTO `tb_login_log` VALUES ('6063', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-12 15:17:51');
INSERT INTO `tb_login_log` VALUES ('6064', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-12 15:54:40');
INSERT INTO `tb_login_log` VALUES ('6065', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 09:44:18');
INSERT INTO `tb_login_log` VALUES ('6066', 'sizhongxia', '123456', 'Windows', 'Chrome', '61.0.3163.91', '登录成功', '2017-11-16 09:44:51');
INSERT INTO `tb_login_log` VALUES ('6067', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 09:45:03');
INSERT INTO `tb_login_log` VALUES ('6068', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 10:19:58');
INSERT INTO `tb_login_log` VALUES ('6069', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 12:44:19');
INSERT INTO `tb_login_log` VALUES ('6070', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 13:10:23');
INSERT INTO `tb_login_log` VALUES ('6071', 'sizhongxia', '123456', 'Windows', 'Chrome', '61.0.3163.100', '登录成功', '2017-11-16 14:16:40');
INSERT INTO `tb_login_log` VALUES ('6072', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 16:04:48');
INSERT INTO `tb_login_log` VALUES ('6073', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-16 16:45:29');
INSERT INTO `tb_login_log` VALUES ('6074', 'sizhongxia', '123456', 'Windows', 'Chrome', '60.0.3112.113', '登录成功', '2017-11-17 16:17:02');
INSERT INTO `tb_login_log` VALUES ('6075', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-17 18:39:03');
INSERT INTO `tb_login_log` VALUES ('6076', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-18 21:43:54');
INSERT INTO `tb_login_log` VALUES ('6077', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-21 14:42:16');
INSERT INTO `tb_login_log` VALUES ('6078', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-21 16:50:08');
INSERT INTO `tb_login_log` VALUES ('6079', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-22 09:50:15');
INSERT INTO `tb_login_log` VALUES ('6080', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-22 12:37:51');
INSERT INTO `tb_login_log` VALUES ('6081', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-22 16:28:34');
INSERT INTO `tb_login_log` VALUES ('6082', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-22 18:41:28');
INSERT INTO `tb_login_log` VALUES ('6083', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-23 12:39:41');
INSERT INTO `tb_login_log` VALUES ('6084', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-23 12:59:35');
INSERT INTO `tb_login_log` VALUES ('6085', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-23 14:09:23');
INSERT INTO `tb_login_log` VALUES ('6086', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-23 17:24:24');
INSERT INTO `tb_login_log` VALUES ('6087', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-23 18:02:53');
INSERT INTO `tb_login_log` VALUES ('6088', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-24 10:44:58');
INSERT INTO `tb_login_log` VALUES ('6089', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-24 10:45:12');
INSERT INTO `tb_login_log` VALUES ('6090', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-24 11:40:30');
INSERT INTO `tb_login_log` VALUES ('6091', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-24 13:21:54');
INSERT INTO `tb_login_log` VALUES ('6092', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-24 14:54:15');
INSERT INTO `tb_login_log` VALUES ('6093', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-24 16:40:30');
INSERT INTO `tb_login_log` VALUES ('6094', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-25 10:54:00');
INSERT INTO `tb_login_log` VALUES ('6095', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-25 18:22:57');
INSERT INTO `tb_login_log` VALUES ('6096', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-26 10:32:41');
INSERT INTO `tb_login_log` VALUES ('6097', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-26 12:31:40');
INSERT INTO `tb_login_log` VALUES ('6098', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-26 16:08:19');
INSERT INTO `tb_login_log` VALUES ('6099', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-26 17:12:05');
INSERT INTO `tb_login_log` VALUES ('6100', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-26 19:07:46');
INSERT INTO `tb_login_log` VALUES ('6101', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-27 14:34:20');
INSERT INTO `tb_login_log` VALUES ('6102', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-27 14:42:37');
INSERT INTO `tb_login_log` VALUES ('6103', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-27 15:12:39');
INSERT INTO `tb_login_log` VALUES ('6104', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-11-29 16:30:55');
INSERT INTO `tb_login_log` VALUES ('6105', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-12-01 13:57:07');
INSERT INTO `tb_login_log` VALUES ('6106', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-12-01 16:20:13');
INSERT INTO `tb_login_log` VALUES ('6107', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-12-11 14:31:04');
INSERT INTO `tb_login_log` VALUES ('6108', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-12-12 16:49:33');
INSERT INTO `tb_login_log` VALUES ('6109', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-12-13 11:34:42');
INSERT INTO `tb_login_log` VALUES ('6110', 'sizhongxia', '123456', 'Windows', 'Chrome', '62.0.3202.89', '登录成功', '2017-12-15 16:40:00');
INSERT INTO `tb_login_log` VALUES ('6111', 'sizhongxia', '123456', 'Windows', 'Chrome', '63.0.3239.84', '登录成功', '2017-12-18 14:48:11');
INSERT INTO `tb_login_log` VALUES ('6112', 'sizhongxia', '123456', 'Windows', 'Chrome', '63.0.3239.84', '登录成功', '2017-12-21 12:19:17');
INSERT INTO `tb_login_log` VALUES ('6113', 'sizhongxia', '123456', 'Windows', 'Chrome', '63.0.3239.84', '登录成功', '2017-12-21 13:22:15');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `author` varchar(80) NOT NULL COMMENT '作者（来源）',
  `introduction` varchar(255) NOT NULL COMMENT '引言',
  `content` text NOT NULL COMMENT '正文',
  `keyword` varchar(80) NOT NULL COMMENT '关键字',
  `publish_status` varchar(4) NOT NULL DEFAULT 'N' COMMENT '发布状态',
  `publish_date` datetime NOT NULL COMMENT '发布时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻资讯';

-- ----------------------------
-- Records of tb_news
-- ----------------------------

-- ----------------------------
-- Table structure for tb_online_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `tb_online_subscribe`;
CREATE TABLE `tb_online_subscribe` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) NOT NULL COMMENT '用户名称',
  `email` varchar(200) NOT NULL COMMENT '订阅邮件',
  `token` varchar(200) NOT NULL COMMENT '会话Token，取消订阅',
  `subscribe_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订阅时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上线新项目提醒';

-- ----------------------------
-- Records of tb_online_subscribe
-- ----------------------------

-- ----------------------------
-- Table structure for tb_organization
-- ----------------------------
DROP TABLE IF EXISTS `tb_organization`;
CREATE TABLE `tb_organization` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织ID',
  `organization_name` varchar(50) NOT NULL,
  `organization_desc` varchar(200) NOT NULL COMMENT '组织描述',
  `sort_number` int(10) unsigned NOT NULL DEFAULT '100' COMMENT '排序编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of tb_organization
-- ----------------------------
INSERT INTO `tb_organization` VALUES ('3', '默认', '默认机构', '100');

-- ----------------------------
-- Table structure for tb_picture
-- ----------------------------
DROP TABLE IF EXISTS `tb_picture`;
CREATE TABLE `tb_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `md5` varchar(40) NOT NULL COMMENT '文件MD5摘要',
  `name` varchar(100) NOT NULL COMMENT '文件名称',
  `size` bigint(20) NOT NULL COMMENT '文件大小',
  `type` varchar(20) NOT NULL COMMENT '文件类别',
  `url` varchar(200) NOT NULL COMMENT '文件访问链接',
  `upload_time` datetime NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传文件';

-- ----------------------------
-- Records of tb_picture
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `organization_id` int(10) unsigned NOT NULL COMMENT '组织机构ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) NOT NULL COMMENT '角色编码',
  `role_explain` varchar(200) NOT NULL COMMENT '角色说明',
  `using_state` varchar(1) NOT NULL COMMENT '使用状态',
  PRIMARY KEY (`id`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `tb_role_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `tb_organization` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('8', '3', '系统管理员', 'Admin', '', 'Y');

-- ----------------------------
-- Table structure for tb_role_authorization
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_authorization`;
CREATE TABLE `tb_role_authorization` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
  `authorization_code` varchar(200) NOT NULL COMMENT '授权权限字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`authorization_code`),
  KEY `authorization_code` (`authorization_code`),
  CONSTRAINT `tb_role_authorization_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_role_authorization_ibfk_2` FOREIGN KEY (`authorization_code`) REFERENCES `tb_authorization` (`authorization_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='角色授权记录表';

-- ----------------------------
-- Records of tb_role_authorization
-- ----------------------------
INSERT INTO `tb_role_authorization` VALUES ('35', '8', 'auth_job_manager');
INSERT INTO `tb_role_authorization` VALUES ('28', '8', 'auth_organization_manager');
INSERT INTO `tb_role_authorization` VALUES ('34', '8', 'auth_publisher_manager');
INSERT INTO `tb_role_authorization` VALUES ('31', '8', 'auth_role_manager');
INSERT INTO `tb_role_authorization` VALUES ('33', '8', 'auth_system_info_manager');
INSERT INTO `tb_role_authorization` VALUES ('29', '8', 'auth_user_manager');

-- ----------------------------
-- Table structure for tb_system_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_info`;
CREATE TABLE `tb_system_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `system_title` varchar(50) NOT NULL DEFAULT '×××管理系统' COMMENT '系统标题',
  `system_descript` varchar(200) NOT NULL DEFAULT '系统描述' COMMENT '系统描述',
  `login_fail_limit` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '是否启动登录限制',
  `login_fail_count` int(11) NOT NULL DEFAULT '5' COMMENT '允许同一账号错误登录次数',
  `login_fail_expired` int(11) NOT NULL DEFAULT '3600' COMMENT '超过登录次数限制时间（单位：秒）',
  `only_chrome` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '是否只允许Chrome浏览器登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统信息表';

-- ----------------------------
-- Records of tb_system_info
-- ----------------------------
INSERT INTO `tb_system_info` VALUES ('1', '互联网内容资源运营支撑平台', '互联网内容资源运营支撑平台', 'Y', '5', '3600', 'N');

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '房源标签ID',
  `name` varchar(10) NOT NULL,
  `pinyin` varchar(500) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签库';

-- ----------------------------
-- Records of tb_tag
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(50) NOT NULL COMMENT '登陆名称',
  `login_pass` varchar(100) NOT NULL COMMENT '登陆密码',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `organization_id` int(10) unsigned NOT NULL COMMENT '组织ID',
  `gender` varchar(1) NOT NULL DEFAULT 'M' COMMENT '用户性别 F(Female,女),M(Male,男)',
  `email` varchar(200) NOT NULL COMMENT '邮箱',
  `phone` varchar(50) NOT NULL COMMENT '手机',
  `telephone` varchar(50) NOT NULL COMMENT '电话',
  `locked_state` varchar(1) NOT NULL DEFAULT 'N' COMMENT '账号锁定状态(L:锁定,N:正常)',
  `disabled_state` varchar(1) NOT NULL DEFAULT 'N' COMMENT '账号禁用状态(D:禁用,N:正常)',
  `expired_time` bigint(13) unsigned NOT NULL DEFAULT '0' COMMENT '用户账号过期日期(0:永不过期)',
  `created_time` bigint(13) unsigned NOT NULL COMMENT '创建时间',
  `updated_time` bigint(13) unsigned NOT NULL COMMENT '最后更新时间',
  `last_login_time` bigint(13) unsigned NOT NULL COMMENT '最后登陆时间',
  PRIMARY KEY (`id`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `tb_organization` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('23', 'sizhongxia', 'd09a9fe317379727746ec06d60875ef7', '司仲夏', '3', 'M', '1217491317@qq.com', '18518436862', '123', 'N', 'N', '0', '1508474135758', '1510328626061', '1513833735076');
INSERT INTO `tb_user` VALUES ('25', '1111', '1', '1', '3', 'M', '11', '11', '1', 'N', 'N', '0', '1', '1', '1');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色主键',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色ID',
  `organization_id` int(11) NOT NULL COMMENT '组织ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `tb_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户角色记录表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('21', '23', '8', '3');
