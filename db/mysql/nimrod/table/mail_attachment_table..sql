
-- 电子邮件附件表
DROP TABLE
IF EXISTS `mail_attachment`;

CREATE TABLE `mail_attachment` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `mail_id` bigint(20) NOT NULL COMMENT '电子邮件 id',
  `attachment_id` bigint(20) NOT NULL COMMENT '附件 id',
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '电子邮件附件表';
