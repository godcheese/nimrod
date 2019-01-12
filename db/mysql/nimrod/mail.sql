
-- 电子邮件表
DROP TABLE IF EXISTS `mail`;

CREATE TABLE `mail` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `status` tinyint(1) NOT NULL COMMENT '发信状态',
  `from` varchar(255) DEFAULT '' COMMENT '发件人',
  `to` varchar(255) NOT NULL COMMENT '收件人',
  `subject` varchar(255) DEFAULT '' COMMENT '主题',
  `text` text COMMENT '内容',
  `html` tinyint(1) DEFAULT 0 COMMENT '是否为 html，0=否，1=是',
  `error` text COMMENT '发信报错信息',
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
  COMMENT = '电子邮件表';
