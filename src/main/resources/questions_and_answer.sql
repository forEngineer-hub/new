use newbee_mall_db;

CREATE TABLE question_and_answer (
  goods_id bigint unsigned COMMENT '商品ID',
  question_id bigint COMMENT '質問ID',
  question varchar(1000) COMMENT '質問内容',
  answer varchar(1000) COMMENT '回答内容',
  submit_date date COMMENT '質問提出日時',
  answers_date date COMMENT '回答日時',
  PRIMARY KEY (goods_id,question_id),
  foreign key (goods_id) references tb_newbee_mall_goods_info(goods_id) on delete cascade);

describe question_and_answer;

select * from question_and_answer;
select * from question_sankou;

select question_and_answer.goods_id,question_and_answer.question_id,question_and_answer.question,question_and_answer.submit_date,question_and_answer.answers_date,
        count(user_id) as help_num
from question_and_answer,question_sankou
where question_and_answer.goods_id = 10070
		and question_sankou.goods_id = question_and_answer.goods_id 
        and question_sankou.question_id = question_and_answer.question_id
group by question_and_answer.goods_id,question_and_answer.question_id,question_and_answer.question,question_and_answer.submit_date,question_and_answer.answers_date
order by help_num desc
limit 0,5;

select question_and_answer.goods_id,question_and_answer.question_id,question_and_answer.question,question_and_answer.submit_date,question_and_answer.answers_date,
        count(user_id) as help_num
from question_and_answer,question_sankou
where question_and_answer.goods_id = 10070
		and question_sankou.goods_id = question_and_answer.goods_id 
        and question_sankou.question_id = question_and_answer.question_id
group by question_and_answer.goods_id,question_and_answer.question_id,question_and_answer.question,question_and_answer.submit_date,question_and_answer.answers_date
order by submit_date desc
limit 0,5;





	select qa.goods_id,qa.question_id,
				qa.question,qa.answer,
				qs.submit_date,qs.answers_date,
		count(user_id)as help_num
		from question_and_answer as qa,question_sankou as qs
		where

			qa.goods_id = 10700

			and qs.goods_id=qa.goods_id
			and qs.question_id=qa.question_id

		group by qa.goods_id,
				qa.question_id,
				qa.answer,
				qs.submit_date,
				qs.answers_date
		order by 1

