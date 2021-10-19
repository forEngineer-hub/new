use newbee_mall_db;

CREATE TABLE question_sankou (
  goods_id bigint unsigned COMMENT '商品ID',
  question_id bigint COMMENT '質問ID',
  user_id bigint COMMENT 'ユーザーID',
  submit_date date COMMENT '質問提出日時',
  answers_date date COMMENT '回答日時',
  PRIMARY KEY (goods_id,question_id,user_id),
  foreign key (goods_id) references tb_newbee_mall_goods_info(goods_id) on delete cascade);

describe question_and_answer;

select * from goods_detail;