
-- 视图菜单表
DROP TABLE
IF EXISTS `view_menu`;

CREATE TABLE `view_menu` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT '' COMMENT '图标（icon）',
  `url` text COMMENT '请求地址（url）',
  `menu_category_id` bigint(20) unsigned NOT NULL COMMENT '菜单分类 id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 id',
  `sort` bigint(20) DEFAULT 0 COMMENT '排序',
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
  COMMENT = '视图菜单表';
