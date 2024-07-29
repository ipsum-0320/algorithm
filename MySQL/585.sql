# Write your MySQL query statement below

-- 每个位于 from 后面的派生表都必须有自己的名称。
select ROUND(sum(T.tiv_2016), 2) as tiv_2016 from (
select pid, tiv_2016 from Insurance InsA where
InsA.tiv_2015 in (select InsB.tiv_2015 from Insurance InsB where InsA.pid <> InsB.pid)
and
(InsA.lat, InsA.lon) not in (select InsC.lat, InsC.lon from Insurance InsC where InsA.pid <> InsC.pid)
) as T;

# 一旦起了别名，那么就需要将别名的使用贯穿始终。
# 变量的作用于，子查询可以访问父查询的字段。
# (InsA.lat, InsA.lon) 表示按组处理。


