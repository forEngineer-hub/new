CREATE TABLE `review_sankou` (
  `review_id` bigint NOT NULL,
  `goods_id` bigint NOT NULL,
  `review_sankou_user_id` bigint NOT NULL,
  `submit_date` date DEFAULT NULL,
  PRIMARY KEY (`review_id`,`goods_id`,`review_sankou_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci