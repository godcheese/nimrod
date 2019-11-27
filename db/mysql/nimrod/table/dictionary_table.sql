
-- 数据字典表
DROP TABLE
IF EXISTS `dictionary`;

CREATE TABLE `dictionary` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `key_name` varchar(255) NOT NULL COMMENT '字典键名',
  `key` varchar(255) NOT NULL COMMENT '字典键',
  `value_name` varchar(255) NOT NULL COMMENT '字典值名',
  `value_slug` varchar(255) NOT NULL COMMENT '字典值别名',
  `value` text COMMENT '字典值',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用（0=否，1=是，默认=0）',
  `dictionary_category_id` bigint(20) unsigned NOT NULL COMMENT '字典分类 id',
  `sort` bigint(20) unsigned DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '数据字典表';