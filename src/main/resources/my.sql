CREATE TABLE `imgku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_url` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `img_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片名称',
  `img_file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件本身的文件名',
  `img_recordreal_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '真实存储地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `xinwen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文章标题',
  `imgurls` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '文章类型',
  `content` text COLLATE utf8_bin COMMENT '文章内容',
  `description_nr` varchar(600) COLLATE utf8_bin DEFAULT NULL COMMENT '文章描述',
  `view_num` bigint(20) DEFAULT NULL COMMENT '文字阅读数',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1推荐',
  `schedule_ts` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '发布时间',
  `last_update_datetime` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `content_id_inx` (`content_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
