
-- example 表
CREATE TABLE `example`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `sort` bigint(20) DEFAULT 0,
  `remark` varchar(255) DEFAULT '',
  `gmt_modified` datetime DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = 'example 表';