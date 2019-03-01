
-- 数据字典分类表
DROP TABLE
IF EXISTS `dictionary_category`;

CREATE TABLE `dictionary_category` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级分类 id',
  `sort` bigint(20) DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '数据字典分类表';

