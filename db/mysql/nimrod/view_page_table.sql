
-- 视图页面表
DROP TABLE
IF EXISTS `view_page`;

CREATE TABLE `view_page` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '页面名称',
  `url` text COMMENT '请求地址（url）',
  `authority` varchar(255) NOT NULL COMMENT '权限（authority）',
  `page_category_id` bigint(20) unsigned NOT NULL COMMENT '页面分类 id',
  `sort` bigint(20) DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id`(`id`),
  UNIQUE KEY `uk_authority` (`authority`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '视图页面表';