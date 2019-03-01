
-- Quartz 定时任务运行日志
DROP TABLE IF EXISTS `job_runtime_log`;

CREATE TABLE `job_runtime_log` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `job_class_name` varchar(255) DEFAULT '' COMMENT '任务类名',
  `job_group` varchar(255) NOT NULL COMMENT '任务分组',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `fire_time` datetime COMMENT 'fireTime',
  `next_fire_time` datetime COMMENT 'nextFireTime',
  `job_run_time` bigint(20) COMMENT '任务运行耗时',
  `log` varchar(255) DEFAULT '' COMMENT '日志',
  `job_exception` varchar(255) DEFAULT '' COMMENT '任务异常信息',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id`(`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = 'Quartz 定时任务运行日志';
