
CREATE TABLE IF NOT EXISTS `endpoint` (
    `id`     BIGINT,
    `http_method`   VARCHAR(16)  NOT NULL,
    `http_url`      VARCHAR(64)  NOT NULL,
    `param_type`    INT NOT NULL DEFAULT 0 COMMENT '0: query 1: body',
    `sql_template`  VARCHAR(512) NOT NULL,
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
);


ALTER TABLE `endpoint` ADD CONSTRAINT
    `uk_http_method_http_url`  UNIQUE (`http_method`, `http_url`);


CREATE TABLE IF NOT EXISTS `endpoint_params` (
    `id`     BIGINT,
    `endpoint_id`   BIGINT    NOT NULL,
    `param_name` VARCHAR(64)  NOT NULL,
    `validator`  VARCHAR(512) NOT NULL,
    `validator_param` VARCHAR(512) NOT NULL,
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
);


CREATE TABLE IF NOT EXISTS `author` (
    `id`     BIGINT,
    `gender` VARCHAR(1),
    `name`   VARCHAR(64) NOT NULL,
    `email`  VARCHAR(64) NOT NULL,
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
);

CREATE INDEX IF NOT EXISTS
    `idx_name` ON `author` (`name`);
ALTER TABLE `author` ADD CONSTRAINT
    `uk_email`  UNIQUE (`email`);
ALTER TABLE `author` ADD CONSTRAINT
    `ck_gender` CHECK  `gender` IN ('M', 'F');
