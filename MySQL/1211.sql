# Write your MySQL query statement below
SELECT
    query_name,
    ROUND(AVG(rating/position), 2) quality, # 在聚合函数中的字段运算，还是属于同一记录的字段进行运算。
    ROUND((SUM(IF(rating < 3, 1, 0)) / COUNT(*)) * 100, 2) poor_query_percentage
FROM Queries
Where query_name IS NOT NULL
GROUP BY query_name;

# count(字段)、count(*)、count(1) 的区别：

# count(*)包括了所有的列，相当于行数，在统计结果的时候，不会忽略为NULL的值。
# count(1)包括了忽略所有列，用1代表代码行，在统计结果的时候，不会忽略为NULL的值。
# count(列名)只包括列名那一列，在统计结果的时候，会忽略列值为空（这里的空不是指空字符串或者0，而是表示null）的计数，即某个字段值为NULL时，不统计。
