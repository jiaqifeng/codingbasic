* source

参考自 https://github.com/JeffLi1993/springboot-learning-example @8d837b80633337dd9dc4c0d676af2c6bf1ba90a8

mysql> CREATE DATABASE springbootdb;
mysql> use springbootdb;
mysql> CREATE TABLE `city` (
    ->   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
    ->   `province_id` int(10) unsigned  NOT NULL COMMENT '省份编号',
    ->   `city_name` varchar(25) DEFAULT NULL COMMENT '城市名称',
    ->   `description` varchar(25) DEFAULT NULL COMMENT '描述',
    ->   PRIMARY KEY (`id`)
    -> ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
Query OK, 0 rows affected (0.04 sec)

mysql> INSERT city VALUES (1 ,1,'温岭市','BYSocket 的家在温岭。');
Query OK, 1 row affected (0.01 sec)

mysql> select * from city;
| id | province_id | city_name | description           |
|  1 |           1 | 温岭市    | BYSocket 的家在温岭。 |

1 row in set (0.00 sec)


mysql> INSERT city VALUES (2 ,1,'shenyang','家在温岭。');

