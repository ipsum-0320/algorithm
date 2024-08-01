# Write your MySQL query statement below

# 执行顺序很重要，where > 聚合函数 > having，因此在 where 中不能使用聚合函数，只能在 having 中使用。
# 注意使用 distinct 去重。
select customer_id from Customer group by customer_id having count(distinct product_key) = (select count(*) from Product);

# where 和 having 的区别:
# having 语句的存在弥补了 where 关键字不能与聚合函数联合使用的不足。
# having 是对分组后进行过滤，where 是对分组前进行过滤。

