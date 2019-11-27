/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : nimrod

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 26/11/2019 16:52:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE "QRTZ_BLOB_TRIGGERS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "TRIGGER_NAME" varchar(190) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  "BLOB_DATA" blob,
  PRIMARY KEY ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  KEY "SCHED_NAME" ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  CONSTRAINT "qrtz_blob_triggers_ibfk_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE "QRTZ_CALENDARS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "CALENDAR_NAME" varchar(190) NOT NULL,
  "CALENDAR" blob NOT NULL,
  PRIMARY KEY ("SCHED_NAME","CALENDAR_NAME")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE "QRTZ_CRON_TRIGGERS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "TRIGGER_NAME" varchar(190) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  "CRON_EXPRESSION" varchar(120) NOT NULL,
  "TIME_ZONE_ID" varchar(80) DEFAULT NULL,
  PRIMARY KEY ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  CONSTRAINT "qrtz_cron_triggers_ibfk_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('quartzScheduler', 'com.gioov.nimrod.quartz.job.SimpleJob', '1', '0/50 * * * * ? *', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE "QRTZ_FIRED_TRIGGERS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "ENTRY_ID" varchar(95) NOT NULL,
  "TRIGGER_NAME" varchar(190) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  "INSTANCE_NAME" varchar(190) NOT NULL,
  "FIRED_TIME" bigint(13) NOT NULL,
  "SCHED_TIME" bigint(13) NOT NULL,
  "PRIORITY" int(11) NOT NULL,
  "STATE" varchar(16) NOT NULL,
  "JOB_NAME" varchar(190) DEFAULT NULL,
  "JOB_GROUP" varchar(190) DEFAULT NULL,
  "IS_NONCONCURRENT" varchar(1) DEFAULT NULL,
  "REQUESTS_RECOVERY" varchar(1) DEFAULT NULL,
  PRIMARY KEY ("SCHED_NAME","ENTRY_ID"),
  KEY "IDX_QRTZ_FT_TRIG_INST_NAME" ("SCHED_NAME","INSTANCE_NAME"),
  KEY "IDX_QRTZ_FT_INST_JOB_REQ_RCVRY" ("SCHED_NAME","INSTANCE_NAME","REQUESTS_RECOVERY"),
  KEY "IDX_QRTZ_FT_J_G" ("SCHED_NAME","JOB_NAME","JOB_GROUP"),
  KEY "IDX_QRTZ_FT_JG" ("SCHED_NAME","JOB_GROUP"),
  KEY "IDX_QRTZ_FT_T_G" ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  KEY "IDX_QRTZ_FT_TG" ("SCHED_NAME","TRIGGER_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE "QRTZ_JOB_DETAILS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "JOB_NAME" varchar(190) NOT NULL,
  "JOB_GROUP" varchar(190) NOT NULL,
  "DESCRIPTION" varchar(250) DEFAULT NULL,
  "JOB_CLASS_NAME" varchar(250) NOT NULL,
  "IS_DURABLE" varchar(1) NOT NULL,
  "IS_NONCONCURRENT" varchar(1) NOT NULL,
  "IS_UPDATE_DATA" varchar(1) NOT NULL,
  "REQUESTS_RECOVERY" varchar(1) NOT NULL,
  "JOB_DATA" blob,
  PRIMARY KEY ("SCHED_NAME","JOB_NAME","JOB_GROUP"),
  KEY "IDX_QRTZ_J_REQ_RECOVERY" ("SCHED_NAME","REQUESTS_RECOVERY"),
  KEY "IDX_QRTZ_J_GRP" ("SCHED_NAME","JOB_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('quartzScheduler', 'com.gioov.nimrod.quartz.job.SimpleJob', '1', '', 'com.gioov.nimrod.quartz.job.SimpleJob', '0', '1', '1', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE "QRTZ_LOCKS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "LOCK_NAME" varchar(40) NOT NULL,
  PRIMARY KEY ("SCHED_NAME","LOCK_NAME")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_LOCKS` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE "QRTZ_PAUSED_TRIGGER_GRPS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  PRIMARY KEY ("SCHED_NAME","TRIGGER_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE "QRTZ_SCHEDULER_STATE" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "INSTANCE_NAME" varchar(190) NOT NULL,
  "LAST_CHECKIN_TIME" bigint(13) NOT NULL,
  "CHECKIN_INTERVAL" bigint(13) NOT NULL,
  PRIMARY KEY ("SCHED_NAME","INSTANCE_NAME")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE "QRTZ_SIMPLE_TRIGGERS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "TRIGGER_NAME" varchar(190) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  "REPEAT_COUNT" bigint(7) NOT NULL,
  "REPEAT_INTERVAL" bigint(12) NOT NULL,
  "TIMES_TRIGGERED" bigint(10) NOT NULL,
  PRIMARY KEY ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  CONSTRAINT "qrtz_simple_triggers_ibfk_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE "QRTZ_SIMPROP_TRIGGERS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "TRIGGER_NAME" varchar(190) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  "STR_PROP_1" varchar(512) DEFAULT NULL,
  "STR_PROP_2" varchar(512) DEFAULT NULL,
  "STR_PROP_3" varchar(512) DEFAULT NULL,
  "INT_PROP_1" int(11) DEFAULT NULL,
  "INT_PROP_2" int(11) DEFAULT NULL,
  "LONG_PROP_1" bigint(20) DEFAULT NULL,
  "LONG_PROP_2" bigint(20) DEFAULT NULL,
  "DEC_PROP_1" decimal(13,4) DEFAULT NULL,
  "DEC_PROP_2" decimal(13,4) DEFAULT NULL,
  "BOOL_PROP_1" varchar(1) DEFAULT NULL,
  "BOOL_PROP_2" varchar(1) DEFAULT NULL,
  PRIMARY KEY ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  CONSTRAINT "qrtz_simprop_triggers_ibfk_1" FOREIGN KEY ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP") REFERENCES "QRTZ_TRIGGERS" ("SCHED_NAME", "TRIGGER_NAME", "TRIGGER_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE "QRTZ_TRIGGERS" (
  "SCHED_NAME" varchar(120) NOT NULL,
  "TRIGGER_NAME" varchar(190) NOT NULL,
  "TRIGGER_GROUP" varchar(190) NOT NULL,
  "JOB_NAME" varchar(190) NOT NULL,
  "JOB_GROUP" varchar(190) NOT NULL,
  "DESCRIPTION" varchar(250) DEFAULT NULL,
  "NEXT_FIRE_TIME" bigint(13) DEFAULT NULL,
  "PREV_FIRE_TIME" bigint(13) DEFAULT NULL,
  "PRIORITY" int(11) DEFAULT NULL,
  "TRIGGER_STATE" varchar(16) NOT NULL,
  "TRIGGER_TYPE" varchar(8) NOT NULL,
  "START_TIME" bigint(13) NOT NULL,
  "END_TIME" bigint(13) DEFAULT NULL,
  "CALENDAR_NAME" varchar(190) DEFAULT NULL,
  "MISFIRE_INSTR" smallint(2) DEFAULT NULL,
  "JOB_DATA" blob,
  PRIMARY KEY ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP"),
  KEY "IDX_QRTZ_T_J" ("SCHED_NAME","JOB_NAME","JOB_GROUP"),
  KEY "IDX_QRTZ_T_JG" ("SCHED_NAME","JOB_GROUP"),
  KEY "IDX_QRTZ_T_C" ("SCHED_NAME","CALENDAR_NAME"),
  KEY "IDX_QRTZ_T_G" ("SCHED_NAME","TRIGGER_GROUP"),
  KEY "IDX_QRTZ_T_STATE" ("SCHED_NAME","TRIGGER_STATE"),
  KEY "IDX_QRTZ_T_N_STATE" ("SCHED_NAME","TRIGGER_NAME","TRIGGER_GROUP","TRIGGER_STATE"),
  KEY "IDX_QRTZ_T_N_G_STATE" ("SCHED_NAME","TRIGGER_GROUP","TRIGGER_STATE"),
  KEY "IDX_QRTZ_T_NEXT_FIRE_TIME" ("SCHED_NAME","NEXT_FIRE_TIME"),
  KEY "IDX_QRTZ_T_NFT_ST" ("SCHED_NAME","TRIGGER_STATE","NEXT_FIRE_TIME"),
  KEY "IDX_QRTZ_T_NFT_MISFIRE" ("SCHED_NAME","MISFIRE_INSTR","NEXT_FIRE_TIME"),
  KEY "IDX_QRTZ_T_NFT_ST_MISFIRE" ("SCHED_NAME","MISFIRE_INSTR","NEXT_FIRE_TIME","TRIGGER_STATE"),
  KEY "IDX_QRTZ_T_NFT_ST_MISFIRE_GRP" ("SCHED_NAME","MISFIRE_INSTR","NEXT_FIRE_TIME","TRIGGER_GROUP","TRIGGER_STATE"),
  CONSTRAINT "qrtz_triggers_ibfk_1" FOREIGN KEY ("SCHED_NAME", "JOB_NAME", "JOB_GROUP") REFERENCES "QRTZ_JOB_DETAILS" ("SCHED_NAME", "JOB_NAME", "JOB_GROUP")
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_TRIGGERS` VALUES ('quartzScheduler', 'com.gioov.nimrod.quartz.job.SimpleJob', '1', 'com.gioov.nimrod.quartz.job.SimpleJob', '1', '', 1568951270000, 1568951220000, 5, 'PAUSED', 'CRON', 1568728277000, 0, NULL, 2, );
COMMIT;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE "api" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT 'API 名称',
  "url" text COMMENT '请求地址（url）',
  "authority" varchar(191) NOT NULL COMMENT '权限（authority）',
  "api_category_id" bigint(20) unsigned NOT NULL COMMENT 'API 分类 id',
  "sort" bigint(20) unsigned DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_authority" ("authority") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='API 表';

-- ----------------------------
-- Records of api
-- ----------------------------
BEGIN;
INSERT INTO `api` VALUES (1, '分页获取所有电子邮件队列', '/api/mail/page_all', '/API/MAIL/PAGE_ALL', 21, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (2, '新增电子邮件', '/api/mail/add_one', '/API/MAIL/ADD_ONE', 21, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (3, '指定电子邮件 id，获取电子邮件', '/api/mail/one', '/API/MAIL/ONE', 21, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (4, '指定队列电子邮件 id，批量删除队列电子邮件', '/api/mail/delete_all', '/API/MAIL/DELETE_ALL', 21, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (5, '新增任务', '/api/quartz/job/add_one', '/API/QUARTZ/JOB/ADD_ONE', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (6, '指定 JobClassName、JobGroup，获取任务', '/api/quartz/job/one', '/API/QUARTZ/JOB/ONE', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (7, '分页获取所有任务', '/api/quartz/job/page_all', '/API/QUARTZ/JOB/PAGE_ALL', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (8, '指定 JobClassName list、JobGroup list，暂停所有任务', '/api/quartz/job/pause_all', '/API/QUARTZ/JOB/PAUSE_ALL', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (9, '指定 JobClassName list、JobGroup list，恢复所有任务', '/api/quartz/job/resume_all', '/API/QUARTZ/JOB/RESUME_ALL', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (10, '指定 JobClassName list、JobGroup list，删除所有任务', '/api/quartz/job/delete_all', '/API/QUARTZ/JOB/DELETE_ALL', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (11, '保存任务', '/api/quartz/job/save_one', '/API/QUARTZ/JOB/SAVE_ONE', 24, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (12, '指定任务运行日志 id，获取任务运行日志', '/api/quartz/job_runtime_log/one', '/API/QUARTZ/JOB_RUNTIME_LOG/ONE', 25, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (13, '分页获取所有任务运行日志', '/api/quartz/job_runtime_log/page_all', '/API/QUARTZ/JOB_RUNTIME_LOG/PAGE_ALL', 25, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (14, '清空所有任务运行日志', '/api/quartz/job_runtime_log/clear_all', '/API/QUARTZ/JOB_RUNTIME_LOG/CLEAR_ALL', 25, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (15, '新增数据字典分类', '/api/system/dictionary_category/add_one', '/API/SYSTEM/DICTIONARY_CATEGORY/ADD_ONE', 6, 0, '', '2019-09-27 01:02:29', NULL);
INSERT INTO `api` VALUES (16, '保存数据字典分类', '/api/system/dictionary_category/save_one', '/API/SYSTEM/DICTIONARY_CATEGORY/SAVE_ONE', 6, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (17, '指定数据字典分类 id list，批量删除数据字典分类', '/api/system/dictionary_category/delete_all', '/API/SYSTEM/DICTIONARY_CATEGORY/DELETE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (18, '指定数据字典分类 id，获取数据字典分类', '/api/system/dictionary_category/one', '/API/SYSTEM/DICTIONARY_CATEGORY/ONE', 6, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (19, '获取所有数据字典分类，以 Antd Table 形式展示', '/api/system/dictionary_category/list_all_as_antd_table', '/API/SYSTEM/DICTIONARY_CATEGORY/LIST_ALL_AS_ANTD_TABLE', 6, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (20, '获取所有数据字典分类，以 Antd TreeNode 形式展示', '/api/system/dictionary_category/list_all_as_antd_tree_node', '/API/SYSTEM/DICTIONARY_CATEGORY/LIST_ALL_AS_ANTD_TREE_NODE', 6, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (21, '新增数据字典', '/api/system/dictionary/add_one', '/API/SYSTEM/DICTIONARY/ADD_ONE', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (22, '保存数据字典', '/api/system/dictionary/save_one', '/API/SYSTEM/DICTIONARY/SAVE_ONE', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (23, '指定数据字典 id，批量删除数据字典', '/api/system/dictionary/delete_all', '/API/SYSTEM/DICTIONARY/DELETE_ALL', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (24, '指定数据字典 id，获取数据字典', '/api/system/dictionary/one', '/API/SYSTEM/DICTIONARY/ONE', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (25, '指定数据字典键，从内存中获取所有数据字典', '/api/system/dictionary/list_all_by_key', '/API/SYSTEM/DICTIONARY/LIST_ALL_BY_KEY', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (26, '同步数据字典到内存', '/api/system/dictionary/sync_to_memory', '/API/SYSTEM/DICTIONARY/SYNC_TO_MEMORY', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (27, '指定数据字典分类 id list，导出数据字典', '/api/system/dictionary/export_all_by_dictionary_category_id_list', '/API/SYSTEM/DICTIONARY/EXPORT_ALL_BY_DICTIONARY_CATEGORY_ID_LIST', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (28, '指定数据字典分类 id，导入数据字典', '/api/system/dictionary/import_all_by_dictionary_category_id', '/API/SYSTEM/DICTIONARY/IMPORT_ALL_BY_DICTIONARY_CATEGORY_ID', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (29, '指定数据字典分类 id，分页获取数据字典', '/api/system/dictionary/page_all_by_dictionary_category_id_list', '/API/SYSTEM/DICTIONARY/PAGE_ALL_BY_DICTIONARY_CATEGORY_ID_LIST', 7, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (30, '分页获取所有文件', '/api/system/file/page_all', '/API/SYSTEM/FILE/PAGE_ALL', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (31, '单个文件上传', '/api/system/file/upload_one', '/API/SYSTEM/FILE/UPLOAD_ONE', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (32, '保存文件', '/api/system/file/save_one', '/API/SYSTEM/FILE/SAVE_ONE', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (33, '指定文件 id list，批量删除文件', '/api/system/file/delete_all', '/API/SYSTEM/FILE/DELETE_ALL', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (34, '指定文件 id，获取文件', '/api/system/file/one', '/API/SYSTEM/FILE/ONE', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (35, '指定文件 guid，下载文件', '/api/system/file/download', '/API/SYSTEM/FILE/DOWNLOAD', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (36, '分页获取所有图片文件', '/api/system/file/page_all_image', '/API/SYSTEM/FILE/PAGE_ALL_IMAGE', 19, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (37, '分页获取所有操作日志', '/api/system/operation_log/page_all', '/API/SYSTEM/OPERATION_LOG/PAGE_ALL', 22, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (38, '指定操作日志 id，获取操作日志', '/api/system/operation_log/one', '/API/SYSTEM/OPERATION_LOG/ONE', 22, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (39, '清空所有操作日志', '/api/system/operation_log/clear_all', '/API/SYSTEM/OPERATION_LOG/CLEAR_ALL', 22, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (40, '获取验证码', '/api/system/verify_code', '/API/SYSTEM/VERIFY_CODE', 2, 0, '', '2019-10-24 13:50:31', NULL);
INSERT INTO `api` VALUES (41, '获取系统信息', '/api/system/system_info', '/API/SYSTEM/SYSTEM_INFO', 2, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (42, '新增 API 分类', '/api/user/api_category/add_one', '/API/USER/API_CATEGORY/ADD_ONE', 4, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (43, '保存 API 分类', '/api/user/api_category/save_one', '/API/USER/API_CATEGORY/SAVE_ONE', 4, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (44, '指定 API 分类 id list，批量删除 API 分类', '/api/user/api_category/delete_all', '/API/USER/API_CATEGORY/DELETE_ALL', 4, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (45, '指定 API 分类 id，获取所有 API 分类', '/api/user/api_category/one', '/API/USER/API_CATEGORY/ONE', 4, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (46, '获取所有 API 分类，以 Antd Table 形式展示', '/api/user/api_category/list_all_as_antd_table', '/API/USER/API_CATEGORY/LIST_ALL_AS_ANTD_TABLE', 4, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (47, '指定 API 分类 id，分页获取所有 API', '/api/user/api/page_all_by_api_category_id', '/API/USER/API/PAGE_ALL_BY_API_CATEGORY_ID', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (48, '新增 API', '/api/user/api/add_one', '/API/USER/API/ADD_ONE', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (49, '指定 API id list，批量删除 API', '/api/user/api/delete_all', '/API/USER/API/DELETE_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (50, '指定 API id，获取所有 API', '/api/user/api/one', '/API/USER/API/ONE', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (51, '指定角色 id、API 分类 id list，分页获取所有 API，以 Antd Table 形式展示', '/api/user/api/page_all_as_antd_table_by_role_id_and_api_category_id_list', '/API/USER/API/PAGE_ALL_AS_ANTD_TABLE_BY_ROLE_ID_AND_API_CATEGORY_ID_LIST', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (52, '指定 API 分类 id list，分页获取所有 API，以 Antd Table 形式展示', '/api/user/api/page_all_as_antd_table_by_api_category_id_list', '/API/USER/API/PAGE_ALL_AS_ANTD_TABLE_BY_API_CATEGORY_ID_LIST', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (53, '指定角色 id、API id list，批量授权', '/api/user/api/grant_all_by_role_id_and_api_id_list', '/API/USER/API/GRANT_ALL_BY_ROLE_ID_AND_API_ID_LIST', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (54, '指定角色 id、API id list，批量撤销授权', '/api/user/api/revoke_all_by_role_id_and_api_id_list', '/API/USER/API/REVOKE_ALL_BY_ROLE_ID_AND_API_ID_LIST', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (55, '指定视图页面 id、API 分类 id list，分页获取所有 API，以 Antd Table 形式展示', '/api/user/api/page_all_as_antd_table_by_view_page_id_and_api_category_id_list', '/API/USER/API/PAGE_ALL_AS_ANTD_TABLE_BY_VIEW_PAGE_ID_AND_API_CATEGORY_ID_LIST', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (56, '指定视图页面组件 id、API 分类 id list，分页获取 API，以 Antd Table 形式展示', '/api/user/api/page_all_as_antd_table_by_view_page_component_id_and_api_category_id_list', '/API/USER/API/PAGE_ALL_AS_ANTD_TABLE_BY_VIEW_PAGE_COMPONENT_ID_AND_API_CATEGORY_ID_LIST', 5, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (57, '新增部门', '/api/user/department/add_one', '/API/USER/DEPARTMENT/ADD_ONE', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (58, '保存部门', '/api/user/department/save_one', '/API/USER/DEPARTMENT/SAVE_ONE', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (59, '指定部门 id list，批量删除部门', '/api/user/department/delete_all', '/API/USER/DEPARTMENT/DELETE_ALL', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (60, '指定部门 id，获取部门', '/api/user/department/one', '/API/USER/DEPARTMENT/ONE', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (61, '获取所有部门，以 Antd TreeNode 形式展示', '/api/user/department/list_all_as_antd_tree_node', '/API/USER/DEPARTMENT/LIST_ALL_AS_ANTD_TREE_NODE', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (62, '获取所有部门，以 Antd Tree 形式展示', '/api/user/department/list_all_as_antd_tree', '/API/USER/DEPARTMENT/LIST_ALL_AS_ANTD_TREE', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (63, '获取所有部门，以 Antd Table 形式展示', '/api/user/department/list_all_as_antd_table', '/API/USER/DEPARTMENT/LIST_ALL_AS_ANTD_TABLE', 20, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (64, '分页获取所有角色', '/api/user/role/page_all', '/API/USER/ROLE/PAGE_ALL', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (65, '新增角色', '/api/user/role/add_one', '/API/USER/ROLE/ADD_ONE', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (66, '指定角色 id list，批量删除角色', '/api/user/role/delete_all', '/API/USER/ROLE/DELETE_ALL', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (67, '指定角色 id，获取角色', '/api/user/role/one', '/API/USER/ROLE/ONE', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (68, '指定用户 id，分页获取所有角色，以 Antd Table 形式展示', '/api/user/role/page_all_as_antd_table_by_user_id', '/API/USER/ROLE/PAGE_ALL_AS_ANTD_TABLE_BY_USER_ID', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (69, '指定用户 id、角色 id list，批量授权', '/api/user/role/grant_all_by_user_id_and_role_id_list', '/API/USER/ROLE/GRANT_ALL_BY_USER_ID_AND_ROLE_ID_LIST', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (70, '指定用户 id、角色 id list，批量撤销授权', '/api/user/role/revoke_all_by_user_id_and_role_id_list', '/API/USER/ROLE/REVOKE_ALL_BY_USER_ID_AND_ROLE_ID_LIST', 14, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (71, '分页获取所有用户', '/api/user/page_all', '/API/USER/PAGE_ALL', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (72, '指定部门 id，分页获取所有用户', '/api/user/page_all_by_department_id', '/API/USER/PAGE_ALL_BY_DEPARTMENT_ID', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (73, '新增用户', '/api/user/add_one', '/API/USER/ADD_ONE', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (74, '保存用户', '/api/user/save_one', '/API/USER/SAVE_ONE', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (75, '指定用户 id list，删除用户', '/api/user/fake_delete_all', '/API/USER/FAKE_DELETE_ALL', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (76, '指定用户 id list，撤销删除用户', '/api/user/revoke_fake_delete_all', '/API/USER/REVOKE_FAKE_DELETE_ALL', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (77, '指定用户 id list，批量永久删除用户', '/api/user/delete_all', '/API/USER/DELETE_ALL', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (78, '指定用户 id，获取用户（除密码和角色）', '/api/user/one', '/API/USER/ONE', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (79, '获取当前用户（用户 id、用户名、头像、电子邮箱、权限、部门）', '/api/user/get_current_user', '/API/USER/GET_CURRENT_USER', 15, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (80, '分页获取所有用户角色', '/api/user/user_role/page_all', '/API/USER/USER_ROLE/PAGE_ALL', 16, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (81, '新增用户角色', '/api/user/user_role/add_one', '/API/USER/USER_ROLE/ADD_ONE', 16, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (82, '指定用户 id、角色 id list，批量删除用户角色', '/api/user/user_role/delete_all_by_user_id_and_role_id_list', '/API/USER/USER_ROLE/DELETE_ALL_BY_USER_ID_AND_ROLE_ID_LIST', 16, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (83, '指定角色 id，分页获取所有父级视图菜单分类', '/api/user/view_menu_category/page_all_parent_by_role_id', '/API/USER/VIEW_MENU_CATEGORY/PAGE_ALL_PARENT_BY_ROLE_ID', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (84, '指定父级视图菜单分类 id、角色 id，获取所有视图菜单分类', '/api/user/view_menu_category/list_all_by_parent_id_and_role_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_BY_PARENT_ID_AND_ROLE_ID', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (85, '新增视图菜单分类', '/api/user/view_menu_category/add_one', '/API/USER/VIEW_MENU_CATEGORY/ADD_ONE', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (86, '保存视图菜单分类', '/api/user/view_menu_category/save_one', '/API/USER/VIEW_MENU_CATEGORY/SAVE_ONE', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (87, '指定视图菜单分类 id，批量删除视图菜单分类', '/api/user/view_menu_category/delete_all', '/API/USER/VIEW_MENU_CATEGORY/DELETE_ALL', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (88, '指定视图菜单分类 id，获取视图菜单分类', '/api/user/view_menu_category/one', '/API/USER/VIEW_MENU_CATEGORY/ONE', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (89, '指定角色 id，获取所有父级视图菜单分类', '/api/user/view_menu_category/list_all_parent_by_role_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_PARENT_BY_ROLE_ID', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (90, '指定用户 id，获取所有父级视图菜单分类', '/api/user/view_menu_category/list_all_parent_by_user_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_PARENT_BY_USER_ID', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (91, '指定用户 id、父级视图菜单分类 id，获取所有子级视图菜单分类', '/api/user/view_menu_category/list_all_child_by_parent_id_and_user_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_CHILD_BY_PARENT_ID_AND_USER_ID', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (92, '获取所有视图菜单分类', '/api/user/view_menu_category/list_all', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (93, '指定视图菜单分类名称，模糊搜索获取所有视图菜单分类', '/api/user/view_menu_category/search_all_by_name', '/API/USER/VIEW_MENU_CATEGORY/SEARCH_ALL_BY_NAME', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (94, '指定角色 id，获取视图菜单分类，以 Antd Table 形式展示', '/api/user/view_menu_category/list_all_as_antd_table_by_role_id', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_AS_ANTD_TABLE_BY_ROLE_ID', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (95, '获取视图菜单分类，以 Antd Table 形式展示', '/api/user/view_menu_category/list_all_as_antd_table', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_AS_ANTD_TABLE', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (96, '获取所有视图菜单分类，以 Antd TreeNode 形式展示', '/api/user/view_menu_category/list_all_as_antd_tree_node', '/API/USER/VIEW_MENU_CATEGORY/LIST_ALL_AS_ANTD_TREE_NODE', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (97, '指定角色 id、视图菜单分类 id list，批量授权', '/api/user/view_menu_category/grant_all_by_role_id_and_view_menu_category_id_list', '/API/USER/VIEW_MENU_CATEGORY/GRANT_ALL_BY_ROLE_ID_AND_VIEW_MENU_CATEGORY_ID_LIST', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (98, '指定角色 id、视图菜单分类 id list，批量撤销授权', '/api/user/view_menu_category/revoke_all_by_role_id_and_view_menu_category_id_list', '/API/USER/VIEW_MENU_CATEGORY/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_MENU_CATEGORY_ID_LIST', 17, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (99, '指定视图菜单分类 id、角色 id，分页获取所有视图菜单', '/api/user/view_menu/page_all_by_view_menu_category_id_and_role_id', '/API/USER/VIEW_MENU/PAGE_ALL_BY_VIEW_MENU_CATEGORY_ID_AND_ROLE_ID', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (100, '新增视图菜单', '/api/user/view_menu/add_one', '/API/USER/VIEW_MENU/ADD_ONE', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (101, '保存视图菜单', '/api/user/view_menu/save_one', '/API/USER/VIEW_MENU/SAVE_ONE', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (102, '指定视图菜单 id list，批量删除视图菜单', '/api/user/view_menu/delete_all', '/API/USER/VIEW_MENU/DELETE_ALL', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (103, '指定视图菜单 id，获取视图菜单', '/api/user/view_menu/one', '/API/USER/VIEW_MENU/ONE', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (104, '指定视图菜单名称，模糊搜索获取所有视图菜单', '/api/user/view_menu/search_all_by_name', '/API/USER/VIEW_MENU/SEARCH_ALL_BY_NAME', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (105, '获取当前用户视图菜单，以 Antd VueMenu 形式展示', '/api/user/view_menu/list_all_as_vue_menu_by_current_user', '/API/USER/VIEW_MENU/LIST_ALL_AS_VUE_MENU_BY_CURRENT_USER', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (106, '指定角色 id、视图菜单分类 id list，分页获取视图菜单，以 Antd Table 形式展示', '/api/user/view_menu/page_all_as_antd_table_by_role_id_and_menu_category_id_list', '/API/USER/VIEW_MENU/PAGE_ALL_AS_ANTD_TABLE_BY_ROLE_ID_AND_MENU_CATEGORY_ID_LIST', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (107, '指定视图菜单分类 id list，分页获取视图菜单，以 Antd Table 形式展示', '/api/user/view_menu/page_all_as_antd_table_by_view_menu_category_id_list', '/API/USER/VIEW_MENU/PAGE_ALL_AS_ANTD_TABLE_BY_VIEW_MENU_CATEGORY_ID_LIST', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (108, '指定角色 id、视图菜单 id list，批量授权', '/api/user/view_menu/grant_all_by_role_id_and_view_menu_id_list', '/API/USER/VIEW_MENU/GRANT_ALL_BY_ROLE_ID_AND_VIEW_MENU_ID_LIST', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (109, '指定角色 id、视图菜单 id list，批量撤销授权', '/api/user/view_menu/revoke_all_by_role_id_and_view_menu_id_list', '/API/USER/VIEW_MENU/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_MENU_ID_LIST', 18, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (110, '指定视图页面 id，API id list，批量关联', '/api/user/view_page_api/associate_all_by_view_page_id_and_api_id_list', '/API/USER/VIEW_PAGE_API/ASSOCIATE_ALL_BY_VIEW_PAGE_ID_AND_API_ID_LIST', 8, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (111, '指定视图页面 id，API id list，批量撤销关联', '/api/user/view_page_api/revoke_associate_all_by_view_page_id_and_api_id_list', '/API/USER/VIEW_PAGE_API/REVOKE_ASSOCIATE_ALL_BY_VIEW_PAGE_ID_AND_API_ID_LIST', 8, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (112, '分页获取所有父级视图页面分类', '/api/user/view_page_category/page_all_parent', '/API/USER/VIEW_PAGE_CATEGORY/PAGE_ALL_PARENT', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (113, '指定父级视图页面分类 id，获取所有视图页面分类', '/api/user/view_page_category/list_all_by_parent_id', '/API/USER/VIEW_PAGE_CATEGORY/LIST_ALL_BY_PARENT_ID', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (114, '新增视图页面分类', '/api/user/view_page_category/add_one', '/API/USER/VIEW_PAGE_CATEGORY/ADD_ONE', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (115, '保存视图页面分类', '/api/user/view_page_category/save_one', '/API/USER/VIEW_PAGE_CATEGORY/SAVE_ONE', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (116, '指定视图页面分类 id ，批量删除视图页面分类', '/api/user/view_page_category/delete_all', '/API/USER/VIEW_PAGE_CATEGORY/DELETE_ALL', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (117, '指定视图页面分类 id，获取视图页面分类', '/api/user/view_page_category/one', '/API/USER/VIEW_PAGE_CATEGORY/ONE', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (118, '指定角色 id，获取所有视图页面分类，以 Antd Table 形式展示', '/api/user/view_page_category/list_all_as_antd_table_by_role_id', '/API/USER/VIEW_PAGE_CATEGORY/LIST_ALL_AS_ANTD_TABLE_BY_ROLE_ID', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (119, '获取视图页面分类，以 Antd Table 形式展示', '/api/user/view_page_category/list_all_as_antd_table', '/API/USER/VIEW_PAGE_CATEGORY/LIST_ALL_AS_ANTD_TABLE', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (120, '获取所有视图页面分类，以 Antd TreeNode 形式展示', '/api/user/view_page_category/list_all_as_antd_tree_node', '/API/USER/VIEW_PAGE_CATEGORY/LIST_ALL_AS_ANTD_TREE_NODE', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (121, '指定视图页面 id，获取视图页面分类', '/api/user/view_page_category/get_one_by_view_page_id', '/API/USER/VIEW_PAGE_CATEGORY/GET_ONE_BY_VIEW_PAGE_ID', 9, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (122, '指定视图页面组件 id、API id list，批量关联', '/api/user/view_page_component_api/associate_all_by_view_page_component_id_and_api_id_list', '/API/USER/VIEW_PAGE_COMPONENT_API/ASSOCIATE_ALL_BY_VIEW_PAGE_COMPONENT_ID_AND_API_ID_LIST', 12, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (123, '指定视图页面组件 id、API id，批量撤销关联', '/api/user/view_page_component_api/revoke_associate_all_by_role_id_and_authority', '/API/USER/VIEW_PAGE_COMPONENT_API/REVOKE_ASSOCIATE_ALL_BY_ROLE_ID_AND_AUTHORITY', 12, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (124, '指定视图页面 id，分页获取所有视图页面组件', '/api/user/view_page_component/page_all_by_view_page_id', '/API/USER/VIEW_PAGE_COMPONENT/PAGE_ALL_BY_VIEW_PAGE_ID', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (125, '新增视图页面组件', '/api/user/view_page_component/add_one', '/API/USER/VIEW_PAGE_COMPONENT/ADD_ONE', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (126, '保存视图页面组件', '/api/user/view_page_component/save_one', '/API/USER/VIEW_PAGE_COMPONENT/SAVE_ONE', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (127, '指定视图页面组件 id，批量删除视图页面组件', '/api/user/view_page_component/delete_all', '/API/USER/VIEW_PAGE_COMPONENT/DELETE_ALL', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (128, '指定视图组件 id，获取视图组件', '/api/user/view_page_component/one', '/API/USER/VIEW_PAGE_COMPONENT/ONE', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (129, '指定角色 id、视图页面 id list，分页获取所有视图页面组件，以 Antd Table 形式展示', '/api/user/view_page_component/page_all_as_antd_table_by_role_id_and_view_page_id_list', '/API/USER/VIEW_PAGE_COMPONENT/PAGE_ALL_AS_ANTD_TABLE_BY_ROLE_ID_AND_VIEW_PAGE_ID_LIST', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (130, '指定视图页面 id list，分页获取视图页面组件，以 Antd Table 形式展示', '/api/user/view_page_component/page_all_as_antd_table_by_view_page_id_list', '/API/USER/VIEW_PAGE_COMPONENT/PAGE_ALL_AS_ANTD_TABLE_BY_VIEW_PAGE_ID_LIST', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (131, '指定角色 id、视图页面组件 id list，批量授权', '/api/user/view_page_component/grant_all_by_role_id_and_view_page_component_id_list', '/API/USER/VIEW_PAGE_COMPONENT/GRANT_ALL_BY_ROLE_ID_AND_VIEW_PAGE_COMPONENT_ID_LIST', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (132, '指定角色 id、视图页面组件 id list，批量撤销授权', '/api/user/view_page_component/revoke_all_by_role_id_and_view_page_component_id_list', '/API/USER/VIEW_PAGE_COMPONENT/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_PAGE_COMPONENT_ID_LIST', 11, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (133, '指定视图页面分类 id ，分页获取所有视图页面', '/api/user/view_page/page_all_by_view_page_category_id', '/API/USER/VIEW_PAGE/PAGE_ALL_BY_VIEW_PAGE_CATEGORY_ID', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (134, '新增视图页面', '/api/user/view_page/add_one', '/API/USER/VIEW_PAGE/ADD_ONE', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (135, '保存视图页面', '/api/user/view_page/save_one', '/API/USER/VIEW_PAGE/SAVE_ONE', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (136, '指定视图页面 id ，批量删除视图页面', '/api/user/view_page/delete_all', '/API/USER/VIEW_PAGE/DELETE_ALL', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (137, '指定视图页面 id，获取视图页面', '/api/user/view_page/one', '/API/USER/VIEW_PAGE/ONE', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (138, '指定角色 id，视图页面分类 id list，分页获取视图页面，以 Antd Table 形式展示', '/api/user/view_page/page_all_as_antd_table_by_role_id_and_view_page_category_id_list', '/API/USER/VIEW_PAGE/PAGE_ALL_AS_ANTD_TABLE_BY_ROLE_ID_AND_VIEW_PAGE_CATEGORY_ID_LIST', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (139, '指定视图页面分类 id list，分页获取所有视图页面，以 Antd Table 形式展示', '/api/user/view_page/page_all_as_antd_table_by_view_page_category_id_list', '/API/USER/VIEW_PAGE/PAGE_ALL_AS_ANTD_TABLE_BY_VIEW_PAGE_CATEGORY_ID_LIST', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (140, '指定角色 id、视图页面 id list，批量授权', '/api/user/view_page/grant_all_by_role_id_and_view_page_id_list', '/API/USER/VIEW_PAGE/GRANT_ALL_BY_ROLE_ID_AND_VIEW_PAGE_ID_LIST', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (141, '指定角色 id、视图页面 id list，批量撤销授权', '/api/user/view_page/revoke_all_by_role_id_and_view_page_id_list', '/API/USER/VIEW_PAGE/REVOKE_ALL_BY_ROLE_ID_AND_VIEW_PAGE_ID_LIST', 10, 0, '', NULL, NULL);
INSERT INTO `api` VALUES (142, '指定视图页面分类 id，获取所有视图页面', '/api/user/view_page/list_all_by_view_page_category_id', '/API/USER/VIEW_PAGE/LIST_ALL_BY_VIEW_PAGE_CATEGORY_ID', 10, 0, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for api_category
-- ----------------------------
DROP TABLE IF EXISTS `api_category`;
CREATE TABLE "api_category" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '分类名称',
  "parent_id" bigint(20) DEFAULT NULL COMMENT '父级分类 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='API 分类表';

-- ----------------------------
-- Records of api_category
-- ----------------------------
BEGIN;
INSERT INTO `api_category` VALUES (1, '系统管理', NULL, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (2, '系统配置', 1, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (3, '用户配置', 1, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (4, 'API 分类', 3, 0, '', '2019-06-27 11:39:12', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (5, 'API', 3, 0, '', '2019-06-27 11:39:23', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (6, '数据字典分类', 2, 0, '', '2019-09-27 01:01:22', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (7, '数据字典', 2, 0, '', '2019-09-26 08:32:16', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (8, '视图页面关联 API', 3, 0, '', '2019-06-27 11:39:31', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (9, '视图页面分类', 3, 0, '', '2019-06-27 11:39:59', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (10, '视图页面', 3, 0, '', '2019-06-27 11:39:39', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (11, '视图页面组件', 3, 0, '', '2019-06-27 11:39:46', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (12, '视图页面组件关联 API', 3, 0, '', '2019-06-27 11:39:52', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (13, '角色关联权限', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (14, '角色管理', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (15, '用户管理', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (16, '用户关联角色', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (17, '视图菜单分类', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (18, '视图菜单', 3, 0, '', '2018-06-20 14:36:43', '2018-06-20 14:36:43');
INSERT INTO `api_category` VALUES (19, '文件管理', 2, 0, '', '2019-06-27 11:38:43', '2019-06-27 11:38:43');
INSERT INTO `api_category` VALUES (20, '部门管理', 2, 0, '', '2019-07-16 12:50:58', '2019-07-16 12:50:03');
INSERT INTO `api_category` VALUES (21, '电子邮件管理', 2, 0, '', '2019-07-16 12:50:37', '2019-07-16 12:50:37');
INSERT INTO `api_category` VALUES (22, '操作日志', 2, 0, '', '2019-08-07 12:45:40', '2019-08-07 12:45:40');
INSERT INTO `api_category` VALUES (23, 'Quartz 任务', 2, 0, '', '2019-08-07 12:46:17', '2019-08-07 12:46:01');
INSERT INTO `api_category` VALUES (24, 'Quartz 任务管理', 21, 0, '', NULL, NULL);
INSERT INTO `api_category` VALUES (25, 'Quartz 任务日志', 21, 0, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE "department" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '部门名称',
  "parent_id" bigint(20) DEFAULT NULL COMMENT '父级部门 id',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES (1, '测试部门', NULL, '', '2018-12-20 06:08:12', '2018-12-20 03:43:04');
COMMIT;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE "dictionary" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "key" varchar(255) NOT NULL COMMENT '字典键',
  "key_name" varchar(255) NOT NULL COMMENT '字典键名',
  "value_name" varchar(255) NOT NULL COMMENT '字典值名',
  "value_slug" varchar(255) NOT NULL COMMENT '字典值别名',
  "value" text COMMENT '字典值',
  "dictionary_category_id" bigint(20) unsigned NOT NULL COMMENT '字典分类 id',
  "enabled" tinyint(1) unsigned DEFAULT NULL COMMENT '是否有效（0=否，1=是，默认=0）',
  "sort" bigint(20) unsigned DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_key_value_slug" ("key","value_slug") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='数据字典表';

-- ----------------------------
-- Records of dictionary
-- ----------------------------
BEGIN;
INSERT INTO `dictionary` VALUES (1, 'WEB', '网站配置', '网站名', 'NAME', 'Nimrod', 3, 1, 0, '', '2019-09-24 03:24:39', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (2, 'WEB', '网站配置', '页脚版权', 'FOOTER', 'Copyright &copy; 2019 Nimrod.All rights reserved.', 3, 1, 0, '', '2019-01-04 08:34:31', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (3, 'MAIL', '电子邮箱发信配置', '主机', 'HOST', 'smtp.exmail.qq.com', 4, 1, 0, '腾讯企业邮箱：https://exmail.qq.com/', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (4, 'MAIL', '电子邮箱发信配置', '协议', 'PROTOCOL', 'smtp', 4, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (5, 'MAIL', '电子邮箱发信配置', '端口号', 'PORT', '25', 4, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (6, 'MAIL', '电子邮箱发信配置', '用户名', 'USERNAME', 'no-reply@gioov.net', 4, 1, 0, '', '2019-02-28 08:34:20', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (7, 'MAIL', '电子邮箱发信配置', '密码', 'PASSWORD', '1i7efzx0AAGk', 4, 1, 0, '', '2019-11-07 02:16:18', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (8, 'MAIL', '电子邮箱发信配置', '显示邮箱', 'FROM', 'no-reply@gioov.net', 4, 1, 0, '', '2019-02-28 08:34:27', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (9, 'MAIL', '电子邮箱发信配置', '默认编码', 'DEFAULT_ENCODING', 'UTF-8', 4, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (10, 'MAIL', '电子邮箱发信配置', '测试连接', 'TEST_CONNECTION', 'false', 4, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (11, 'VIEW_PAGE_COMPONENT_TYPE', '视图页面组件类型', '按钮', 'BUTTON', '1', 5, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (12, 'VIEW_PAGE_COMPONENT_TYPE', '视图页面组件类型', '搜索框', 'SEARCH', '2', 5, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (16, 'SMS_STATUS', '信息状态', '等待', 'WAIT', '0', 7, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (17, 'SMS_STATUS', '信息状态', '失败', 'FAIL', '1', 7, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (18, 'SMS_STATUS', '信息状态', '成功', 'SUCCESS', '2', 7, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (19, 'IS_OR_NOT', '是或否', '否', 'NOT', '0', 8, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (20, 'IS_OR_NOT', '是或否', '是', 'IS', '1', 8, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (21, 'GENDER', '性别', '未知', 'UNKNOWN', '0', 9, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (22, 'GENDER', '性别', '男', 'MALE', '1', 9, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (23, 'GENDER', '性别', '女', 'FEMALE', '2', 9, 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary` VALUES (24, 'FILE', '文件上传配置', '上传路径', 'UPLOAD_PATH', '/upload', 11, 1, 0, '', '2019-10-25 07:46:40', '2018-11-19 07:41:07');
INSERT INTO `dictionary` VALUES (25, 'OPERATION_TYPE', '操作类型', '访问页面', 'PAGE', '0', 12, 1, 0, '', '2018-12-27 03:18:00', '2018-12-17 12:13:32');
INSERT INTO `dictionary` VALUES (26, 'OPERATION_TYPE', '操作类型', '调用 API', 'API', '1', 12, 1, 0, '', '2018-12-27 03:18:08', '2018-12-17 12:14:42');
INSERT INTO `dictionary` VALUES (27, 'FILE', '文件上传配置', '附件类型', 'TYPE', '.jpg,.png,.zip', 11, 1, 0, '允许上传的文件类型', '2019-01-09 06:53:11', '2018-12-17 13:08:13');
INSERT INTO `dictionary` VALUES (28, 'FILE', '文件上传配置', '最大单文件上传大小', 'MAX_FILE_SIZE', '200MB', 11, 1, 0, '允许上传的最大文件大小', '2019-10-25 07:01:08', '2018-12-17 13:10:23');
INSERT INTO `dictionary` VALUES (29, 'SYSTEM', '系统配置', '语言', 'LANGUAGE', 'zh', 13, 0, 0, '', '2019-01-09 06:32:49', '2018-12-19 08:25:09');
INSERT INTO `dictionary` VALUES (30, 'SYSTEM', '系统配置', '国家/区域', 'COUNTY', 'CN', 13, 0, 0, '', '2019-01-09 06:32:55', '2018-12-19 08:27:08');
INSERT INTO `dictionary` VALUES (31, 'SYSTEM', '系统配置', '时区标识符', 'TIME_ZONE_ID', 'GMT+8', 13, 1, 0, '', '2019-01-09 06:32:59', '2018-12-19 08:30:44');
INSERT INTO `dictionary` VALUES (32, 'SYSTEM', '系统配置', '日期格式', 'DATE_FORMAT_PATTERN', 'yyyy-MM-dd HH:mm:ss', 13, 0, 0, '', '2019-01-09 06:33:04', '2018-12-19 08:32:46');
INSERT INTO `dictionary` VALUES (33, 'FILE', '文件上传配置', '最大请求上传大小', 'MAX_REQUEST_SIZE', '1000MB', 11, 1, 0, '', '2019-10-25 07:01:17', '2018-12-21 01:05:54');
INSERT INTO `dictionary` VALUES (34, 'VERIFY_CODE', '验证码', '过期时间（秒）', 'EXPIRATION', '60', 14, 1, 0, '', '2019-01-09 06:56:53', '2019-01-09 06:56:53');
INSERT INTO `dictionary` VALUES (35, 'VERIFY_CODE', '验证码', '噪点', 'YAWP', 'false', 14, 1, 0, '', '2019-01-09 06:57:56', '2019-01-09 06:57:43');
INSERT INTO `dictionary` VALUES (36, 'VERIFY_CODE', '验证码', '字符长度', 'STRING_LENGTH', '4', 14, 1, 0, '建议字典值设置为4', '2019-01-10 01:02:27', '2019-01-09 06:58:19');
INSERT INTO `dictionary` VALUES (37, 'VERIFY_CODE', '验证码', '干扰线条数', 'INTER_LINE', '0', 14, 1, 0, '建议字典值设置为4', '2019-01-10 01:02:56', '2019-01-09 06:59:09');
INSERT INTO `dictionary` VALUES (38, 'QUARTZ_TRIGGER_STATE', 'Quartz 任务状态', '等待中', 'WAITING', 'WAITING', 15, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (39, 'QUARTZ_TRIGGER_STATE', 'Quartz 任务状态', '运行中', 'ACQUIRED', 'ACQUIRED', 15, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (40, 'QUARTZ_TRIGGER_STATE', 'Quartz 任务状态', '已暂停', 'PAUSED', 'PAUSED', 15, 1, 0, '', '2019-09-24 03:25:28', NULL);
INSERT INTO `dictionary` VALUES (41, 'QUARTZ_TRIGGER_STATE', 'Quartz 任务状态', '已阻塞', 'BLOCKED', 'BLOCKED', 15, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (42, 'QUARTZ_TRIGGER_STATE', 'Quartz 任务状态', '错误', 'ERROR', 'ERROR', 15, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (43, 'VERIFY_CODE', '验证码', '背景色', 'HEX_BACKGROUND_COLOR', '#0064c8', 14, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (44, 'VERIFY_CODE', '验证码', '字体色', 'FONT_COLOR', '#FFFFFF', 14, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (45, 'WEB', '网站配置', '网站地址', 'URL', 'http://localhost:8080', 3, 1, 0, '', NULL, NULL);
INSERT INTO `dictionary` VALUES (46, 'VERIFY_CODE', '验证码', '字体路径', 'FONT_PATH', '/fonts/Arial.ttf', 14, 1, 0, '', '2019-11-09 02:54:48', '2019-11-09 02:54:48');
COMMIT;

-- ----------------------------
-- Table structure for dictionary_category
-- ----------------------------
DROP TABLE IF EXISTS `dictionary_category`;
CREATE TABLE "dictionary_category" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '分类名称',
  "parent_id" bigint(20) unsigned DEFAULT NULL COMMENT '父级分类 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='数据字典分类表';

-- ----------------------------
-- Records of dictionary_category
-- ----------------------------
BEGIN;
INSERT INTO `dictionary_category` VALUES (1, '系统缺省字段', NULL, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (2, '通用缺省字段', NULL, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (3, '网站配置', 1, 0, '', '2019-09-26 12:47:14', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (4, '电子邮箱配置', 1, 0, '', '2019-09-24 03:15:58', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (5, '视图页面组件类型', 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (7, '信息状态', 1, 0, '', '2018-07-08 15:29:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (8, '是否', 2, 0, '', '2019-02-26 00:41:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (9, '性别', 2, 0, '', '2019-02-26 00:41:33', '2018-07-08 15:29:33');
INSERT INTO `dictionary_category` VALUES (11, '文件上传配置', 1, 0, '', '2018-11-19 07:39:44', '2018-11-19 07:39:44');
INSERT INTO `dictionary_category` VALUES (12, '操作类型', 1, 0, '', '2018-12-17 12:02:51', '2018-12-17 12:02:51');
INSERT INTO `dictionary_category` VALUES (13, '系统配置', 1, 0, '', '2018-12-19 08:23:57', '2018-12-19 08:23:57');
INSERT INTO `dictionary_category` VALUES (14, '验证码', 1, 0, '', '2019-01-04 08:23:15', '2019-01-04 08:23:15');
INSERT INTO `dictionary_category` VALUES (15, 'Quartz 任务状态', 1, 0, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE "file" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "user_id" bigint(20) unsigned DEFAULT NULL COMMENT '用户 id',
  "name" varchar(255) NOT NULL COMMENT '文件名',
  "guid" varchar(255) NOT NULL COMMENT '唯一标识符',
  "size" bigint(20) NOT NULL COMMENT '文件大小',
  "pretty_size" varchar(255) NOT NULL COMMENT '文件美化大小',
  "mime_type" varchar(255) DEFAULT '' COMMENT 'MIME 类型',
  "path" text COMMENT '文件路径',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文件表';

-- ----------------------------
-- Table structure for job_runtime_log
-- ----------------------------
DROP TABLE IF EXISTS `job_runtime_log`;
CREATE TABLE "job_runtime_log" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "job_class_name" varchar(255) DEFAULT '' COMMENT '任务类名',
  "job_group" varchar(255) DEFAULT NULL COMMENT '任务分组',
  "description" varchar(255) DEFAULT '' COMMENT '描述',
  "fire_time" datetime DEFAULT NULL COMMENT 'fireTime',
  "next_fire_time" datetime DEFAULT NULL COMMENT 'nextFireTime',
  "consuming_time" bigint(20) DEFAULT NULL COMMENT '任务运行耗时（毫秒）',
  "log" varchar(255) DEFAULT '' COMMENT '日志',
  "job_exception" varchar(255) DEFAULT '' COMMENT '任务异常信息',
  "gmt_created" datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='Quartz 任务运行日志';

-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE "mail" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "status" tinyint(1) NOT NULL COMMENT '发信状态',
  "from" varchar(255) DEFAULT '' COMMENT '发件人',
  "to" varchar(255) NOT NULL COMMENT '收件人',
  "subject" varchar(255) DEFAULT '' COMMENT '主题',
  "text" text COMMENT '内容',
  "html" tinyint(1) DEFAULT '0' COMMENT '是否为 html，0=否，1=是',
  "error" text COMMENT '发信报错信息',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='电子邮件表';

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE "operation_log" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "user_id" bigint(20) DEFAULT NULL COMMENT '访问用户 id',
  "ip_address" varchar(255) DEFAULT '' COMMENT '用户 IP',
  "operation_type" tinyint(1) DEFAULT NULL COMMENT '操作类型',
  "operation" text COMMENT '操作说明',
  "consuming_time" bigint(255) DEFAULT '0' COMMENT '操作耗时（毫秒）',
  "request_url" text COMMENT '请求地址',
  "request_method" varchar(50) DEFAULT '' COMMENT '请求方法',
  "request_parameter" text COMMENT '请求参数',
  "accept_language" varchar(255) DEFAULT '' COMMENT '请求语言',
  "referer" text COMMENT '请求来源',
  "user_agent" varchar(255) DEFAULT '' COMMENT '用户代理',
  "handler" text COMMENT 'Handler',
  "stack_trace" text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '异常堆栈',
  "session_id" varchar(255) DEFAULT '' COMMENT 'Session ID',
  "cookie" text COMMENT 'Cookie',
  "status" varchar(255) DEFAULT '' COMMENT '响应状态码',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7426 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE "role" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '角色名称',
  "value" varchar(191) NOT NULL COMMENT '角色值',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_value" ("value") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '普通用户', 'NORMAL_USER', '', '2018-06-27 21:22:40', '2018-06-27 21:22:40');
INSERT INTO `role` VALUES (2, '管理员', 'ADMIN', '', '2018-06-27 21:22:40', '2018-06-27 21:22:40');
INSERT INTO `role` VALUES (999, '系统管理员', 'SYSTEM_ADMIN', '', '2018-06-27 21:22:40', '2018-06-27 21:22:40');
COMMIT;

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE "role_authority" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "role_id" bigint(20) unsigned NOT NULL COMMENT '角色 id',
  "authority" varchar(255) NOT NULL COMMENT '权限（authority）',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色关联权限表';

-- ----------------------------
-- Table structure for role_view_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_view_menu`;
CREATE TABLE "role_view_menu" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "role_id" bigint(20) unsigned NOT NULL COMMENT '角色 id',
  "view_menu_id" bigint(20) unsigned NOT NULL COMMENT '视图菜单 id',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色关联视图菜单表';

-- ----------------------------
-- Records of role_view_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_view_menu` VALUES (2, 999, 2);
INSERT INTO `role_view_menu` VALUES (3, 999, 3);
INSERT INTO `role_view_menu` VALUES (4, 999, 4);
INSERT INTO `role_view_menu` VALUES (5, 999, 5);
INSERT INTO `role_view_menu` VALUES (6, 999, 6);
INSERT INTO `role_view_menu` VALUES (7, 999, 7);
INSERT INTO `role_view_menu` VALUES (8, 999, 8);
INSERT INTO `role_view_menu` VALUES (9, 999, 9);
INSERT INTO `role_view_menu` VALUES (10, 999, 10);
INSERT INTO `role_view_menu` VALUES (11, 999, 11);
INSERT INTO `role_view_menu` VALUES (12, 999, 12);
INSERT INTO `role_view_menu` VALUES (13, 999, 13);
INSERT INTO `role_view_menu` VALUES (14, 999, 14);
INSERT INTO `role_view_menu` VALUES (15, 999, 15);
INSERT INTO `role_view_menu` VALUES (16, 999, 16);
INSERT INTO `role_view_menu` VALUES (18, 999, 1);
COMMIT;

-- ----------------------------
-- Table structure for role_view_menu_category
-- ----------------------------
DROP TABLE IF EXISTS `role_view_menu_category`;
CREATE TABLE "role_view_menu_category" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "role_id" bigint(20) unsigned NOT NULL COMMENT '角色 id',
  "view_menu_category_id" bigint(20) unsigned NOT NULL COMMENT '视图菜单分类名称',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色关联视图菜单分类表';

-- ----------------------------
-- Records of role_view_menu_category
-- ----------------------------
BEGIN;
INSERT INTO `role_view_menu_category` VALUES (1, 999, 1);
INSERT INTO `role_view_menu_category` VALUES (2, 999, 2);
INSERT INTO `role_view_menu_category` VALUES (3, 999, 3);
INSERT INTO `role_view_menu_category` VALUES (4, 999, 4);
INSERT INTO `role_view_menu_category` VALUES (5, 999, 5);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE "user" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "username" varchar(191) NOT NULL COMMENT '用户名',
  "password" varchar(255) NOT NULL COMMENT '密码',
  "avatar" varchar(255) DEFAULT '' COMMENT '头像',
  "email" varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  "email_is_verified" tinyint(1) unsigned DEFAULT NULL COMMENT '电子邮箱是否验证（0=未验证，1=已验证，默认=0）',
  "department_id" bigint(20) unsigned NOT NULL COMMENT '部门 id',
  "enabled" tinyint(1) unsigned DEFAULT NULL COMMENT '是否启用（0=否，1=是，默认=0）',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_deleted" datetime DEFAULT NULL COMMENT '删除时间',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_username" ("username") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'normal_user', '$2a$10$IEK236NdbYiZzYVAHTl4qeIgPInJQwMqRh/c986PKwEN4/T1DbsSm', '', 'normal_user@outlook.com', 1, 1, 0, '测试备注', NULL, '2019-10-31 13:54:41', '2018-06-27 21:22:40');
INSERT INTO `user` VALUES (2, 'admin', '$2a$10$IEK236NdbYiZzYVAHTl4qeIgPInJQwMqRh/c986PKwEN4/T1DbsSm', '', 'admin@outlook.com', 1, 1, 1, '测试备注', NULL, '2019-11-05 02:29:23', '2018-06-27 21:22:40');
INSERT INTO `user` VALUES (999, 'system_admin', '$2a$10$IEK236NdbYiZzYVAHTl4qeIgPInJQwMqRh/c986PKwEN4/T1DbsSm', '', 'system_admin@outlook.com', 1, 1, 1, '测试备注', NULL, '2019-11-26 06:41:33', '2018-06-27 21:22:40');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE "user_role" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "user_id" bigint(20) unsigned NOT NULL COMMENT '用户 id',
  "role_id" bigint(20) unsigned NOT NULL COMMENT '角色 id',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户关联角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 999, 999);
INSERT INTO `user_role` VALUES (2, 1, 1);
INSERT INTO `user_role` VALUES (3, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for user_verify_code
-- ----------------------------
DROP TABLE IF EXISTS `user_verify_code`;
CREATE TABLE "user_verify_code" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "user_id" bigint(20) unsigned NOT NULL COMMENT '用户 id',
  "verify_from" varchar(191) NOT NULL COMMENT '用户绑定的电子邮箱或手机号码',
  "verify_code" varchar(191) NOT NULL COMMENT '电子邮箱或手机号码验证码',
  "gmt_expires" datetime NOT NULL COMMENT '过期时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_verify_code" ("verify_code") USING BTREE,
  KEY "uk_user_id" ("user_id") USING BTREE,
  KEY "uk_verify_from" ("verify_from") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户验证码表';

-- ----------------------------
-- Table structure for view_menu
-- ----------------------------
DROP TABLE IF EXISTS `view_menu`;
CREATE TABLE "view_menu" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '视图菜单名称',
  "icon" varchar(255) DEFAULT '' COMMENT '图标（icon）',
  "url" text COMMENT '请求地址（url）',
  "view_menu_category_id" bigint(20) unsigned NOT NULL COMMENT '视图菜单分类 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图菜单表';

-- ----------------------------
-- Records of view_menu
-- ----------------------------
BEGIN;
INSERT INTO `view_menu` VALUES (1, 'API 管理', 'api', '/user/api/list', 3, 0, '', '2019-06-06 02:52:53', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (2, '数据字典', 'book', '/system/dictionary/list', 2, 0, '', '2019-06-06 02:51:54', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (3, '视图页面管理', 'desktop', '/user/view_page/list', 3, 0, '', '2019-06-06 02:50:52', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (4, 'Druid Monitor', 'alibaba', '/druid/list', 2, 0, '', '2019-06-13 04:16:42', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (5, '用户管理', 'user', '/user/list', 3, 0, '', '2018-07-01 21:28:04', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (6, '角色管理', 'team', '/user/role/list', 3, 0, '', '2019-06-06 03:16:52', '2018-07-01 21:28:04');
INSERT INTO `view_menu` VALUES (7, '电子邮件管理', 'mail', '/mail/list', 2, 0, '', '2019-01-21 02:59:02', '2018-07-08 13:22:30');
INSERT INTO `view_menu` VALUES (8, '操作日志', 'exception', '/system/operation_log/list', 2, 0, '', '2019-08-07 12:30:57', '2018-08-06 16:47:15');
INSERT INTO `view_menu` VALUES (9, '文件管理', 'file', '/system/file/list', 2, 0, '', '2018-10-20 19:07:33', '2018-10-20 19:01:23');
INSERT INTO `view_menu` VALUES (10, '部门管理', 'cluster', '/user/department/list', 3, 0, '', '2018-12-20 02:43:28', '2018-12-20 02:43:28');
INSERT INTO `view_menu` VALUES (11, '视图菜单管理', 'bars', '/user/view_menu/list', 3, 0, '', '2019-06-13 04:21:31', '2019-06-13 11:37:14');
INSERT INTO `view_menu` VALUES (12, 'Quartz 任务管理', 'bars', '/quartz/job/list', 4, 0, '', '2019-06-17 06:31:23', '2019-06-17 06:28:37');
INSERT INTO `view_menu` VALUES (13, 'Quartz 任务日志', 'exception', '/quartz/job_runtime_log/list', 4, 0, '', NULL, NULL);
INSERT INTO `view_menu` VALUES (14, '工作台', 'dashboard', '/workbench', 1, 0, '', '2019-07-27 16:11:47', '2019-02-11 01:17:37');
COMMIT;

-- ----------------------------
-- Table structure for view_menu_category
-- ----------------------------
DROP TABLE IF EXISTS `view_menu_category`;
CREATE TABLE "view_menu_category" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '视图菜单分类名称',
  "icon" varchar(255) DEFAULT '' COMMENT '图标（icon）',
  "parent_id" bigint(20) DEFAULT NULL COMMENT '父级视图菜单分类 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图菜单分类表';

-- ----------------------------
-- Records of view_menu_category
-- ----------------------------
BEGIN;
INSERT INTO `view_menu_category` VALUES (1, '系统管理', 'fa fa-cog', NULL, 0, '', '2019-06-06 02:51:28', '2018-07-01 21:28:04');
INSERT INTO `view_menu_category` VALUES (2, '系统配置', 'fa fa-cog', 1, 0, '', '2019-06-13 04:16:55', '2018-07-01 21:28:04');
INSERT INTO `view_menu_category` VALUES (3, '用户配置', 'fa fa-user', 1, 0, '', '2019-06-13 07:22:32', '2018-07-01 21:28:04');
INSERT INTO `view_menu_category` VALUES (4, 'Quartz 任务', 'fa fa-bars', 2, 0, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for view_page
-- ----------------------------
DROP TABLE IF EXISTS `view_page`;
CREATE TABLE "view_page" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '视图页面名称',
  "url" text COMMENT '请求地址（url）',
  "authority" varchar(191) NOT NULL COMMENT '权限（authority）',
  "view_page_category_id" bigint(20) unsigned NOT NULL COMMENT '视图页面分类 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_authority" ("authority") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图页面表';

-- ----------------------------
-- Records of view_page
-- ----------------------------
BEGIN;
INSERT INTO `view_page` VALUES (1, 'API 管理', '/user/api/page_all', '/USER/API/PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (2, '数据字典', '/system/dictionary/page_all', '/SYSTEM/DICTIONARY/PAGE_ALL', 2, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (3, '视图页面管理', '/user/view_page/page_all', '/USER/VIEW_PAGE/PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (4, 'Druid Monitor', '/druid', '/DRUID', 2, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (5, '用户管理', '/user/page_all', '/USER/PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (6, '角色管理', '/user/role/page_all', '/USER/ROLE/PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (7, '电子邮件管理', '/mail/page_all', '/MAIL/PAGE_ALL', 2, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (8, '操作日志', '/system/operation_log/page_all', '/SYSTEM/OPERATION_LOG/PAGE_ALL', 2, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (9, '文件管理', '/system/file/page_all', '/SYSTEM/FILE/PAGE_ALL', 2, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (10, '部门管理', '/user/department/list_all', '/USER/DEPARTMENT/LIST_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (11, '视图菜单管理', '/user/view_menu/page_all', '/USER/VIEW_MENU/PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (12, 'Quartz 任务管理', '/quartz/job/page_all', '/QUARTZ/JOB/PAGE_ALL', 4, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (13, 'Quartz 任务日志', '/quartz/job_runtime_log/page_all', '/QUARTZ/JOB_RUNTIME_LOG/PAGE_ALL', 4, 0, '', NULL, NULL);
INSERT INTO `view_page` VALUES (14, '工作台', '/workbench', '/WORKBENCH', 1, 0, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for view_page_api
-- ----------------------------
DROP TABLE IF EXISTS `view_page_api`;
CREATE TABLE "view_page_api" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "view_page_id" bigint(20) unsigned NOT NULL COMMENT '视图页面 id',
  "api_id" bigint(20) unsigned NOT NULL COMMENT 'API id',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图页面关联 API 表';

-- ----------------------------
-- Table structure for view_page_category
-- ----------------------------
DROP TABLE IF EXISTS `view_page_category`;
CREATE TABLE "view_page_category" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "name" varchar(255) NOT NULL COMMENT '视图页面分类名称',
  "parent_id" bigint(20) DEFAULT NULL COMMENT '父级视图页面分类 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图页面分类表';

-- ----------------------------
-- Records of view_page_category
-- ----------------------------
BEGIN;
INSERT INTO `view_page_category` VALUES (1, '系统管理', NULL, 0, '', '2019-08-07 12:55:27', '2019-08-07 12:55:27');
INSERT INTO `view_page_category` VALUES (2, '系统配置', 1, 0, '', '2019-08-07 13:54:02', '2019-08-07 13:54:02');
INSERT INTO `view_page_category` VALUES (3, '用户配置', 1, 0, '', '2019-08-07 14:03:00', '2019-08-07 14:03:00');
INSERT INTO `view_page_category` VALUES (4, 'Quartz 任务', 2, 0, '', '2019-08-07 14:17:54', '2019-08-07 14:17:43');
COMMIT;

-- ----------------------------
-- Table structure for view_page_component
-- ----------------------------
DROP TABLE IF EXISTS `view_page_component`;
CREATE TABLE "view_page_component" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "view_page_component_type" tinyint(2) NOT NULL COMMENT '视图页面组件类型 id',
  "name" varchar(255) NOT NULL COMMENT '视图页面组件名称',
  "authority" varchar(191) NOT NULL COMMENT '权限（authority）',
  "view_page_id" bigint(20) unsigned NOT NULL COMMENT '视图页面 id',
  "sort" bigint(20) DEFAULT '0' COMMENT '排序',
  "remark" varchar(255) DEFAULT '' COMMENT '备注',
  "gmt_modified" datetime DEFAULT NULL COMMENT '更新时间',
  "gmt_created" datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY ("id") USING BTREE,
  UNIQUE KEY "uk_authority" ("authority") USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图页面组件表';

-- ----------------------------
-- Records of view_page_component
-- ----------------------------
BEGIN;
INSERT INTO `view_page_component` VALUES (1, 1, '新增发送邮件', '/COMPONENT/MAIL/SEND_ONE', 7, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (2, 1, '删除', '/COMPONENT/MAIL/DELETE_ALL', 7, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (3, 1, '删除', '/COMPONENT/MAIL/REFRESH', 7, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (4, 1, '新增', '/COMPONENT/QUARTZ/JOB/ADD_ONE', 12, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (5, 1, '编辑', '/COMPONENT/QUARTZ/JOB/EDIT_ONE', 12, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (6, 1, '删除', '/COMPONENT/QUARTZ/JOB/DELETE_ALL', 12, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (7, 1, '暂停', '/COMPONENT/QUARTZ/JOB/PAUSE_ALL', 12, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (8, 1, '恢复', '/COMPONENT/QUARTZ/JOB/RESUME_ALL', 12, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (9, 1, '刷新', '/COMPONENT/QUARTZ/JOB/REFRESH', 12, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (10, 1, '清空', '/COMPONENT/QUARTZ/JOB_RUNTIME_LOG/CLEAR_ALL', 13, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (11, 1, '新增', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_CATEGORY_ADD_ONE', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (12, 1, '编辑', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_CATEGORY_EDIT_ONE', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (13, 1, '删除', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_CATEGORY_DELETE_ALL', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (14, 1, '新增', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_ADD_ONE', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (15, 1, '编辑', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_EDIT_ONE', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (16, 1, '删除', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_DELETE_ALL', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (17, 1, '同步到内存', '/COMPONENT/SYSTEM/DICTIONARY/DICTIONARY_SYNC_TO_MEMORY', 2, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (18, 1, '单文件上传', '/COMPONENT/SYSTEM/FILE/UPLOAD_ONE', 9, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (19, 1, '编辑', '/COMPONENT/SYSTEM/FILE/EDIT_ONE', 9, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (20, 1, '删除', '/COMPONENT/SYSTEM/FILE/DELETE_ALL', 9, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (21, 1, '清空', '/COMPONENT/SYSTEM/OPERATION_LOG/CLEAR_ALL', 8, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (22, 1, '新增', '/COMPONENT/USER/ADD_ONE', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (23, 1, '编辑', '/COMPONENT/USER/EDIT_ONE', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (24, 1, '删除', '/COMPONENT/USER/FAKE_DELETE_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (25, 1, '撤销删除', '/COMPONENT/USER/REVOKE_FAKE_DELETE_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (26, 1, '永久删除', '/COMPONENT/USER/DELETE_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (27, 1, '角色分配', '/COMPONENT/USER/USER_ROLE_PAGE_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (28, 1, '新增', '/COMPONENT/USER/API/API_CATEGORY_ADD_ONE', 1, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (29, 1, '编辑', '/COMPONENT/USER/API/API_CATEGORY_EDIT_ONE', 1, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (30, 1, '删除', '/COMPONENT/USER/API/API_CATEGORY_DELETE_ALL', 1, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (31, 1, '新增', '/COMPONENT/USER/API/API_ADD_ONE', 1, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (32, 1, '编辑', '/COMPONENT/USER/API/API_EDIT_ONE', 1, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (33, 1, '删除', '/COMPONENT/USER/API/API_DELETE_ALL', 1, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (34, 1, '新增', '/COMPONENT/USER/DEPARTMENT/ADD_ONE', 10, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (35, 1, '编辑', '/COMPONENT/USER/DEPARTMENT/EDIT_ONE', 10, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (36, 1, '删除', '/COMPONENT/USER/DEPARTMENT/DELETE_ALL', 10, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (37, 1, '新增', '/COMPONENT/USER/ROLE/ADD_ONE', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (38, 1, '编辑', '/COMPONENT/USER/ROLE/EDIT_ONE', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (39, 1, '删除', '/COMPONENT/USER/ROLE/DELETE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (40, 1, '视图菜单管理', '/COMPONENT/USER/ROLE/VIEW_MENU_PAGE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (41, 1, '视图页面管理', '/COMPONENT/USER/ROLE/VIEW_PAGE_PAGE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (42, 1, 'API 管理', '/COMPONENT/USER/ROLE/API_PAGE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (43, 1, '授权', '/COMPONENT/USER/ROLE/API/API_GRANT_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (44, 1, '撤销授权', '/COMPONENT/USER/ROLE/API/API_REVOKE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (45, 1, '授权', '/COMPONENT/USER/ROLE/VIEW_MENU/VIEW_MENU_CATEGORY_GRANT_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (46, 1, '撤销授权', '/COMPONENT/USER/ROLE/VIEW_MENU/VIEW_MENU_CATEGORY_REVOKE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (47, 1, '授权', '/COMPONENT/USER/ROLE/VIEW_MENU/VIEW_MENU_GRANT_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (48, 1, '撤销授权', '/COMPONENT/USER/ROLE/VIEW_MENU/VIEW_MENU_REVOKE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (49, 1, '授权', '/COMPONENT/USER/ROLE/VIEW_PAGE/VIEW_PAGE_GRANT_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (50, 1, '撤销授权', '/COMPONENT/USER/ROLE/VIEW_PAGE/VIEW_PAGE_REVOKE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (51, 1, '授权', '/COMPONENT/USER/ROLE/VIEW_PAGE/VIEW_PAGE_COMPONENT_GRANT_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (52, 1, '撤销授权', '/COMPONENT/USER/ROLE/VIEW_PAGE/VIEW_PAGE_COMPONENT_REVOKE_ALL', 6, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (53, 1, '授权', '/COMPONENT/USER/USER_ROLE/GRANT_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (54, 1, '撤销授权', '/COMPONENT/USER/USER_ROLE/REVOKE_ALL', 5, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (55, 1, '新增', '/COMPONENT/USER/VIEW_MENU/VIEW_MENU_CATEGORY_ADD_ONE', 11, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (56, 1, '编辑', '/COMPONENT/USER/VIEW_MENU/VIEW_MENU_CATEGORY_EDIT_ONE', 11, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (57, 1, '删除', '/COMPONENT/USER/VIEW_MENU/VIEW_MENU_CATEGORY_DELETE_ALL', 11, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (58, 1, '新增', '/COMPONENT/USER/VIEW_MENU/VIEW_MENU_ADD_ONE', 11, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (59, 1, '编辑', '/COMPONENT/USER/VIEW_MENU/VIEW_MENU_EDIT_ONE', 11, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (60, 1, '删除', '/COMPONENT/USER/VIEW_MENU/VIEW_MENU_DELETE_ALL', 11, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (61, 1, '新增', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_CATEGORY_ADD_ONE', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (62, 1, '编辑', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_CATEGORY_EDIT_ONE', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (63, 1, '删除', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_CATEGORY_DELETE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (64, 1, '新增', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_ADD_ONE', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (65, 1, '编辑', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_EDIT_ONE', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (66, 1, '删除', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_DELETE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (67, 1, '关联 API', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_API_PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (68, 1, '新增', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_COMPONENT_ADD_ONE', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (69, 1, '编辑', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_COMPONENT_EDIT_ONE', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (70, 1, '删除', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_COMPONENT_DELETE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (71, 1, '关联 API', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_COMPONENT_API_PAGE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (72, 1, '关联', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_API/ASSOCIATE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (73, 1, '撤销关联', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_API/REVOKE_ASSOCIATE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (74, 1, '关联', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_COMPONENT_API/ASSOCIATE_ALL', 3, 0, '', NULL, NULL);
INSERT INTO `view_page_component` VALUES (75, 1, '撤销关联', '/COMPONENT/USER/VIEW_PAGE/VIEW_PAGE_COMPONENT_API/REVOKE_ASSOCIATE_ALL', 3, 0, '', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for view_page_component_api
-- ----------------------------
DROP TABLE IF EXISTS `view_page_component_api`;
CREATE TABLE "view_page_component_api" (
  "id" bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  "view_page_component_id" bigint(20) unsigned NOT NULL COMMENT '视图页面组件 id',
  "api_id" bigint(20) unsigned NOT NULL COMMENT 'API id',
  PRIMARY KEY ("id") USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='视图页面组件关联 API 表';

SET FOREIGN_KEY_CHECKS = 1;
