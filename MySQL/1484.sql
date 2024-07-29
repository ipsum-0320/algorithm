# Write your MySQL query statement below1
select
    sell_date,
    count(distinct product) num_sold,
    group_concat(
            distinct product
            order by product
            separator ','
    ) products
from
    Activities
group by sell_date
order by sell_date

# 下面有两个需要讲解的：
# 1.group_concat 拿到分组查询的内容值，其中 product 指定了是哪一个内容。
# 2.可以使用 as 对字段或者表进行重命名。