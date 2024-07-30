# Write your MySQL query statement below

# 一个数据库表只能有一个主键，不允许两个主键。但是允许两个字段联合起来设置为主键，这叫联合主键。

# 进行问题的拆解。
#
select a.user_id, round(sum(if(b.action = 'confirmed',1,0)) / count(*),2) as confirmation_rate
from Signups a
         left join Confirmations b
                   on a.user_id = b.user_id
group by a.user_id;