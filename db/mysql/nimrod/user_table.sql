
-- 用户表
DROP TABLE
IF EXISTS `user`;

-- 123456
-- $2a$10$tYdoCZPLiG2bMX9Cn09JCOV.g0BZeblQ39Rq0HL.jJy5OtCnUMOmC

CREATE TABLE `user` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT '' COMMENT '电子邮箱',
  `email_is_verified` tinyint(1) unsigned NOT NULL COMMENT '电子邮箱是否验证通过（0=未验证，1=已验证，默认=0）',
  `department_id` bigint(20) unsigned NOT NULL COMMENT '部门 id',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
-- `is_deleted` tinyint(1) unsigned NOT NULL COMMENT '是否删除（0=未删除，1=已删除，默认=0）',
  `gmt_deleted` datetime DEFAULT NULL COMMENT '删除时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id` (`id`),
  UNIQUE KEY `uk_username` (`username`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '用户表';
