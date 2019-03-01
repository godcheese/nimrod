
-- 角色表
DROP TABLE
IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `value` varchar(255) NOT NULL COMMENT '角色值',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id`(`id`),
  UNIQUE KEY `uk_value` (`value`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '角色表';
