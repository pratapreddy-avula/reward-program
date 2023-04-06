
create table CUSTOMER(
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) not null
);

create table ORDER_DATA(
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
order_total DOUBLE NOT NULL,
customer_id INTEGER NOT NULL,
description VARCHAR(100),
reward_points DOUBLE,
order_date DATE
);