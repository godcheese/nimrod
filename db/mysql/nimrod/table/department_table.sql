
-- 部门表
DROP TABLE
IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级部门 id',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id` (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '部门表';
