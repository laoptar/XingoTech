-- ============================================================
-- XingoTech User 模块数据库迁移脚本
-- 变更说明：
--   1. user_id BIGINT AUTO_INCREMENT → VARCHAR(24)
--   2. user_token.user_id BIGINT → VARCHAR(24)
--   3. 新增 enabled 字段
-- ============================================================

-- ============================================================
-- 方案一：新表创建 + 数据迁移（推荐）
-- ============================================================

-- 1. user 表迁移
RENAME TABLE user TO user_old;

CREATE TABLE `user` (
  `user_id`      VARCHAR(24)  NOT NULL                COMMENT '用户主键(24位随机十六进制)',
  `user_name`    VARCHAR(64)  NOT NULL                COMMENT '用户名',
  `password`     VARCHAR(255) NOT NULL                COMMENT '密码(BCrypt加密)',
  `user_image`   VARCHAR(255) DEFAULT NULL            COMMENT '用户头像URL',
  `user_type`    VARCHAR(32)  DEFAULT NULL            COMMENT '用户类型',
  `mobile_phone` VARCHAR(20)  DEFAULT NULL            COMMENT '电话号码',
  `sex`          TINYINT      DEFAULT NULL            COMMENT '性别(0-男,1-女,2-未知)',
  `email`        VARCHAR(128) DEFAULT NULL            COMMENT '邮箱',
  `emp_name`     VARCHAR(64)  DEFAULT NULL            COMMENT '员工姓名',
  `emp_code`     VARCHAR(64)  DEFAULT NULL            COMMENT '员工编号',
  `office_phone` VARCHAR(20)  DEFAULT NULL            COMMENT '办公电话',
  `active_date`  DATETIME     DEFAULT NULL            COMMENT '生效日期',
  `disable_date` DATETIME     DEFAULT NULL            COMMENT '失效日期',
  `user_desc`    VARCHAR(500) DEFAULT NULL            COMMENT '描述',
  `enabled`      TINYINT      DEFAULT 1               COMMENT '是否启用(1-启用,0-禁用)',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 迁移旧数据（BIGINT id 转为 VARCHAR 字符串）
INSERT INTO `user` (user_id, user_name, password, email, mobile_phone, user_type, enabled)
SELECT CAST(id AS CHAR(24)), username, password, email, mobile, roles, 1
FROM user_old;

-- 2. user_token 表迁移
RENAME TABLE user_token TO user_token_old;

CREATE TABLE `user_token` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT  COMMENT '主键',
  `user_id`     VARCHAR(24)  NOT NULL                COMMENT '关联用户ID',
  `token`       TEXT         NOT NULL                COMMENT 'JWT 令牌',
  `platform`    VARCHAR(50)  DEFAULT NULL            COMMENT '平台标识',
  `login_time`  DATETIME     DEFAULT NULL            COMMENT '登录时间',
  `expire_time` DATETIME     DEFAULT NULL            COMMENT '过期时间',
  `status`      INT          DEFAULT 1               COMMENT '1-active, 0-inactive',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户令牌表';

INSERT INTO `user_token` (id, user_id, token, platform, login_time, expire_time, status)
SELECT id, CAST(user_id AS CHAR(24)), token, platform, login_time, expire_time, status
FROM user_token_old;

-- ============================================================
-- 方案二：ALTER TABLE 直接修改（大数据量时选用）
-- 注意：主键类型变更风险较高，建议使用方案一
-- ============================================================
-- ALTER TABLE user MODIFY user_id VARCHAR(24);
-- ALTER TABLE user ADD COLUMN enabled TINYINT DEFAULT 1 COMMENT '是否启用' AFTER user_desc;
-- ALTER TABLE user_token MODIFY user_id VARCHAR(24);
-- ALTER TABLE user DROP PRIMARY KEY;
-- ALTER TABLE user ADD PRIMARY KEY (user_id);