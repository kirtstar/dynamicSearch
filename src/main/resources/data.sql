DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS contract;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS order;

CREATE TABLE client(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(250) NOT NULL,
                        phone varchar(11));
);

CREATE TABLE product(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(250) NOT NULL,
                       price double precision);

CREATE TABLE contract(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         phone varchar(11),
                        client_id bigint
                            constraint contract_client_fkey
                            references client);

CREATE TABLE  order(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       number bigint,
                       contract_id bigint constraint order_contract_fkey
                   references contract);

create table order_product(
    order_id bigint constraint order_product_order_id_fkey
                          references order,
    product_id bigint constraint order_product_product_id
                          references product,
    primary key (order_id, product_id)
);