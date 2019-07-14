/*
Navicat MySQL Data Transfer

Source Server         : jiangjie
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : ohs

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-07-14 21:06:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ohs_column_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_column_config`;
CREATE TABLE `ohs_column_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column_alias` varchar(255) DEFAULT NULL,
  `column_name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `is_hide` varchar(255) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  `table_id` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_column_config
-- ----------------------------
INSERT INTO `ohs_column_config` VALUES ('53', '系统别名', 'sys_alias', '2019-07-14 16:24:33', '姜杰', '1', '200', '108', '2019-07-14 20:05:42', 'admin');
INSERT INTO `ohs_column_config` VALUES ('54', '系统中文名', 'sys_chinese_nme', '2019-07-14 19:57:44', '姜杰', '1', '200', '108', null, null);
INSERT INTO `ohs_column_config` VALUES ('55', '数据库用户名', 'schema_name', '2019-07-14 20:07:36', 'admin', '1', '200', '108', null, null);
INSERT INTO `ohs_column_config` VALUES ('56', '主键', 'id', '2019-07-14 20:25:24', 'admin', '1', '200', '109', null, null);
INSERT INTO `ohs_column_config` VALUES ('57', '父菜单名称', 'parent_menu_name', '2019-07-14 20:25:52', 'admin', '1', '200', '109', null, null);
INSERT INTO `ohs_column_config` VALUES ('58', '子菜单名称', 'sub_menu_name', '2019-07-14 20:26:25', 'admin', '1', '200', '109', null, null);
INSERT INTO `ohs_column_config` VALUES ('59', '主键', 'id', '2019-07-14 20:46:49', 'admin', '1', '200', '108', null, null);

-- ----------------------------
-- Table structure for ohs_enum_value_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_enum_value_config`;
CREATE TABLE `ohs_enum_value_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `enum_chinese_value` varchar(255) DEFAULT NULL,
  `enum_value` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_enum_value_config
-- ----------------------------

-- ----------------------------
-- Table structure for ohs_environment_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_environment_config`;
CREATE TABLE `ohs_environment_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_nme` varchar(255) DEFAULT NULL,
  `db_pwd` varchar(255) DEFAULT NULL,
  `env_alias` varchar(255) DEFAULT NULL,
  `env_ip` varchar(255) DEFAULT NULL,
  `env_name` varchar(255) DEFAULT NULL,
  `env_port` varchar(255) DEFAULT NULL,
  `env_typ` varchar(255) DEFAULT NULL,
  `interface_nme` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `sys_id` int(11) NOT NULL,
  `evn_alias` varchar(255) DEFAULT NULL,
  `evn_ip` varchar(255) DEFAULT NULL,
  `evn_name` varchar(255) DEFAULT NULL,
  `evn_port` varchar(255) DEFAULT NULL,
  `evn_typ` varchar(255) DEFAULT NULL,
  `db_type` varchar(255) DEFAULT NULL,
  `db_schema` varchar(255) DEFAULT NULL,
  `db_ip` varchar(255) DEFAULT NULL,
  `db_port` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_environment_config
-- ----------------------------
INSERT INTO `ohs_environment_config` VALUES ('25', 'root', 'jiangjie1', null, null, null, null, null, null, '2019-07-14 14:20:55', 'admin', '2019-07-14 20:34:11', 'admin', '200', 'ohs', '127.0.0.1', '本地开发', '3306', '1', '0', null, null, null);

-- ----------------------------
-- Table structure for ohs_interface_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_interface_config`;
CREATE TABLE `ohs_interface_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `interface_type` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_interface_config
-- ----------------------------

-- ----------------------------
-- Table structure for ohs_menu
-- ----------------------------
DROP TABLE IF EXISTS `ohs_menu`;
CREATE TABLE `ohs_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_menu_id` int(11) DEFAULT NULL,
  `parent_menu_name` varchar(255) DEFAULT NULL,
  `parent_url` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `sub_menu_desc` varchar(255) DEFAULT NULL,
  `sub_menu_id` int(11) DEFAULT NULL,
  `sub_menu_name` varchar(255) DEFAULT NULL,
  `sub_menu_url` varchar(255) DEFAULT NULL,
  `parent_menu_desc` varchar(255) DEFAULT NULL,
  `is_hide` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_menu
-- ----------------------------
INSERT INTO `ohs_menu` VALUES ('1', null, '公共参数配置', null, '2019-01-03 21:49:08', 'jiangjie', null, null, '0', null, null, null, null, '这是公共参数配置的介绍', '0');
INSERT INTO `ohs_menu` VALUES ('2', null, '数据定制化配置', null, '2019-01-03 21:49:44', 'jiangjie', null, null, '0', null, null, null, null, '这是数据定制化配置的介绍', '0');
INSERT INTO `ohs_menu` VALUES ('3', null, '自动化测试配置', null, '2019-01-03 21:50:12', 'jiangjie', null, null, '0', null, null, null, null, '这是自动化测试配置的介绍', '0');
INSERT INTO `ohs_menu` VALUES ('4', null, '数据查询', null, '2019-01-03 21:50:47', 'jiangjie', null, null, '1', null, null, null, null, '这是数据查询的介绍', '0');
INSERT INTO `ohs_menu` VALUES ('5', null, '自动化测试', null, '2019-01-03 21:51:03', 'jiangjie', null, null, '1', null, null, null, null, '这是自动化测试的介绍', '0');
INSERT INTO `ohs_menu` VALUES ('6', '1', '', null, '2019-01-03 21:51:51', 'jiangjie', null, null, '1', '一个系统对应多个环境（sit,uat,pro等环境）', null, '系统配置', 'systemConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('7', '1', null, null, '2019-01-03 21:52:48', 'jiangjie', null, null, '1', '一个系统必定分为多个功能模块', null, '模块配置', 'moduleConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('9', '1', null, null, '2019-01-03 21:53:24', 'jiangjie', null, null, '1', '新建账号（在登录界面进行申请，也可以直接分配）或者审批账号（登录界面申请的账号必须审批后才能使用）以及归属的系统（不能超过10个）', null, '人员配置', 'userConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('10', '2', null, null, '2019-01-03 21:53:49', 'jiangjie', null, null, '1', '一个系统有多张表', null, '表配置', 'tableConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('11', '2', null, null, '2019-01-03 21:54:26', 'jiangjie', null, null, '1', '一个表有多个字段', null, '字段配置', 'columnConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('12', '2', null, null, '2019-01-03 21:54:30', 'jiangjie', null, null, '1', '一个字段有多个枚举值或0个枚举值', null, '枚举值配置', 'enumValueConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('13', '2', null, null, '2019-01-03 21:54:51', 'jiangjie', null, null, '1', '简单的单表查询', null, '单表SQL配置', 'singleSqlConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('14', '2', null, null, '2019-01-03 21:55:28', 'jiangjie', null, null, '1', '复杂的多表查询（2张表以上的关联查询:inner join, left join, right join）', null, '多表SQL配置', null, null, '1');
INSERT INTO `ohs_menu` VALUES ('8', '1', null, null, '2019-01-03 21:56:46', 'jiangjie', null, null, '1', 'sit,uat,pro等环境', null, '环境配置', 'evnConfig', null, '0');
INSERT INTO `ohs_menu` VALUES ('15', '3', null, null, '2019-01-03 22:02:27', 'jiangjie', null, null, '1', '为系统下模块下添加要测试的系统接口', null, '接口配置', null, null, '0');
INSERT INTO `ohs_menu` VALUES ('16', '3', null, null, '2019-01-03 22:02:49', 'jiangjie', null, null, '1', '为接口新增测试案例', null, '案例配置', null, null, '0');
INSERT INTO `ohs_menu` VALUES ('19', '4', null, null, '2019-07-14 14:20:55', 'admin', null, null, '1', null, null, 'OHS', 'dataQuery', null, '0');

-- ----------------------------
-- Table structure for ohs_module_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_module_config`;
CREATE TABLE `ohs_module_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_alias` varchar(255) DEFAULT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `sys_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_module_config
-- ----------------------------
INSERT INTO `ohs_module_config` VALUES ('61', 'DIR_QRY1', '目录查询', '2019-07-14 14:31:43', 'admin', '2019-07-14 14:53:00', 'admin', '200');

-- ----------------------------
-- Table structure for ohs_module_table_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_module_table_config`;
CREATE TABLE `ohs_module_table_config` (
  `module_id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL,
  KEY `FKm02lela8sf1ayn9lxici0wfh7` (`table_id`),
  KEY `FK5xn0mwq1h10d98i9egd8k0cye` (`module_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_module_table_config
-- ----------------------------

-- ----------------------------
-- Table structure for ohs_single_query_sql
-- ----------------------------
DROP TABLE IF EXISTS `ohs_single_query_sql`;
CREATE TABLE `ohs_single_query_sql` (
  `single_sql_id` int(11) NOT NULL,
  `single_query_id` int(11) NOT NULL,
  KEY `FKhxsfdp4cjpyqhrq6qdtbjbqjm` (`single_query_id`),
  KEY `FK5lf89teup1v8ai3o6jftaylrd` (`single_sql_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_single_query_sql
-- ----------------------------

-- ----------------------------
-- Table structure for ohs_single_query_where_info
-- ----------------------------
DROP TABLE IF EXISTS `ohs_single_query_where_info`;
CREATE TABLE `ohs_single_query_where_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `key_info` varchar(255) DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `value_info` varchar(255) DEFAULT NULL,
  `key_chn_info` varchar(255) DEFAULT NULL,
  `single_sql_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_single_query_where_info
-- ----------------------------
INSERT INTO `ohs_single_query_where_info` VALUES ('57', '2019-07-14 20:48:22', 'admin', 'sys_alias', null, null, null, null, '系统别名', '39');
INSERT INTO `ohs_single_query_where_info` VALUES ('56', '2019-07-14 20:29:17', 'admin', 'id', null, null, null, null, '主键', '38');
INSERT INTO `ohs_single_query_where_info` VALUES ('58', '2019-07-14 20:48:41', 'admin', 'id', null, null, null, null, '主键', '39');

-- ----------------------------
-- Table structure for ohs_single_sql_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_single_sql_config`;
CREATE TABLE `ohs_single_sql_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `single_table_sql` varchar(255) DEFAULT NULL,
  `table_id` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_single_sql_config
-- ----------------------------
INSERT INTO `ohs_single_sql_config` VALUES ('39', '2019-07-14 20:48:41', 'admin', '61', '系统信息查询', 'select sys_alias, sys_chinese_nme, schema_name, id from OHS.ohs_sys_config where sys_alias={sys_alias} and id={id} ', '108', '2019-07-14 20:48:41', 'admin', '200');
INSERT INTO `ohs_single_sql_config` VALUES ('38', '2019-07-14 20:29:17', 'admin', '61', '菜单查询', 'select id, parent_menu_name, sub_menu_name from OHS.ohs_menu where id={id} ', '109', null, null, '200');

-- ----------------------------
-- Table structure for ohs_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_sys_config`;
CREATE TABLE `ohs_sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `sys_alias` varchar(255) DEFAULT NULL,
  `sys_chinese_nme` varchar(255) DEFAULT NULL,
  `schema_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_sys_config
-- ----------------------------
INSERT INTO `ohs_sys_config` VALUES ('200', '2019-07-14 14:20:55', 'admin', '2019-07-14 14:40:53', 'admin', 'OHS', '在线辅助系统', 'OHS');

-- ----------------------------
-- Table structure for ohs_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `ohs_sys_role`;
CREATE TABLE `ohs_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_sys_role
-- ----------------------------
INSERT INTO `ohs_sys_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `ohs_sys_role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for ohs_sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `ohs_sys_role_user`;
CREATE TABLE `ohs_sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_id` int(11) NOT NULL,
  `sys_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_sys_role_user
-- ----------------------------
INSERT INTO `ohs_sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `ohs_sys_role_user` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for ohs_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `ohs_sys_user`;
CREATE TABLE `ohs_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_sys_user
-- ----------------------------
INSERT INTO `ohs_sys_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `ohs_sys_user` VALUES ('2', 'abel', 'abel');

-- ----------------------------
-- Table structure for ohs_table_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_table_config`;
CREATE TABLE `ohs_table_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `schema_name` varchar(255) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `table_chn_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_table_config
-- ----------------------------
INSERT INTO `ohs_table_config` VALUES ('108', '2019-07-14 16:24:33', '姜杰', 'OHS', '200', 'ohs_sys_config', '2019-07-14 19:55:26', 'admin', '系统配置表');
INSERT INTO `ohs_table_config` VALUES ('109', '2019-07-14 17:50:18', 'admin', 'OHS', '200', 'ohs_menu', null, null, '菜单表');

-- ----------------------------
-- Table structure for ohs_testsuit_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_testsuit_config`;
CREATE TABLE `ohs_testsuit_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `interface_id` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `request_data` varchar(255) DEFAULT NULL,
  `response_data` varchar(255) DEFAULT NULL,
  `sys_id` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_testsuit_config
-- ----------------------------

-- ----------------------------
-- Table structure for ohs_user_config
-- ----------------------------
DROP TABLE IF EXISTS `ohs_user_config`;
CREATE TABLE `ohs_user_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `login_account` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ohs_user_config
-- ----------------------------
INSERT INTO `ohs_user_config` VALUES ('1', '2019-01-14 20:32:19', '姜杰', 'jiangjie666', '姜杰', 'jiangjie1', '0', null, null);
INSERT INTO `ohs_user_config` VALUES ('2', null, null, 'jiangjie666', '姜杰', 'jiangjie12', '0', '2019-01-14 20:32:27', '修改者');
INSERT INTO `ohs_user_config` VALUES ('6', '2019-01-27 22:28:13', '姜杰', '1231232', '123123', '1312312', '0', null, null);
INSERT INTO `ohs_user_config` VALUES ('7', '2019-01-27 22:29:05', '姜杰', '1', '1', '1', '1', null, null);
INSERT INTO `ohs_user_config` VALUES ('8', '2019-01-27 22:29:11', '姜杰', '1', '1', '1', '1', null, null);
INSERT INTO `ohs_user_config` VALUES ('9', '2019-01-27 22:29:17', '姜杰', '1', '1', '1', '1', null, null);
INSERT INTO `ohs_user_config` VALUES ('10', '2019-01-27 22:29:29', '姜杰', '1', '1', '1', '1', null, null);
INSERT INTO `ohs_user_config` VALUES ('11', '2019-01-27 22:29:37', '姜杰', '1', '1', '1', '1', null, null);
