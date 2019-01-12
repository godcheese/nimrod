
-- 用户关联角色表
DROP TABLE
IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户 id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  PRIMARY KEY `pk_id` (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '用户关联角色表';
