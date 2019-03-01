
-- 系统请求日志表
DROP TABLE
IF EXISTS `operation_log`;

CREATE TABLE `operation_log` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '访问用户 id',
  `ip_address` varchar(255) DEFAULT '' COMMENT '用户 IP',
  `operation_type` tinyint(1) DEFAULT NULL COMMENT '操作类型',
  `operation` text COMMENT '操作说明',
  `request_time` bigint(255) DEFAULT 0 COMMENT '请求耗时（毫秒）',
  `request_url` text COMMENT '请求地址',
  `request_method` varchar(50) DEFAULT '' COMMENT '请求方法',
  `request_parameter` text COMMENT '请求参数',
  `accept_language` varchar(255) DEFAULT '' COMMENT '请求语言',
  `referer` text COMMENT '请求来源',
  `user_agent` varchar(255) DEFAULT '' COMMENT '用户代理',
  `handler` text COMMENT 'Handler',
  `session_id` varchar(255) DEFAULT '' COMMENT 'Session ID',
  `cookie` text COMMENT 'Cookie',
  `content_type` varchar(255) DEFAULT '' COMMENT '响应文本类型',
  `status` varchar(255) DEFAULT '' COMMENT '响应状态码',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id`(`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '系统操作日志表';
