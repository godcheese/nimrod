
-- 角色关联权限表
DROP TABLE
IF EXISTS `role_authority`;

CREATE TABLE `role_authority` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  `authority` varchar(255) NOT NULL COMMENT '权限（authority）',
  PRIMARY KEY `pk_id` (`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '角色关联权限表';
