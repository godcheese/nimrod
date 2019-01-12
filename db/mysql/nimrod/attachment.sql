
-- 附件表
DROP TABLE
IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `guid` varchar(255) DEFAULT '' COMMENT '唯一标识符',
  `size` bigint(20) unsigned DEFAULT 0 COMMENT '文件大小',
  `pretty_size` varchar(255) NOT NULL COMMENT '文件美化大小',
  `mime_type` varchar(255) DEFAULT '' COMMENT 'MIME 类型',
  `path` text COMMENT '文件路径',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id`(`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '附件表';
