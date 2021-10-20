use newbee_mall_db;

CREATE TABLE `review` (
  `review_id` bigint NOT NULL,
  `goods_id` bigint unsigned NOT NULL,
  `review_user_id` bigint NOT NULL,
  `star` smallint DEFAULT NULL,
  `submit_date` date DEFAULT NULL,
  `review_title` varchar(100) DEFAULT NULL,
  `review_detail` varchar(1000) DEFAULT NULL,
  `image_url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`review_id`,`review_user_id`,`goods_id`),
  foreign key (goods_id) references tb_newbee_mall_goods_info(goods_id) on delete cascade,
  foreign key (review_user_id) references tb_newbee_mall_user(user_id) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci