# 原始代码
select firstName, lastName, city, state from Person as P left join Address as A on P.personId = A.personId;

# 下面讲一下 left join on 和 where 的区别:
# on 条件是在生成临时表时使用的条件，而 where 则是在临时表生成好后，再对临时表进行过滤的条件。


