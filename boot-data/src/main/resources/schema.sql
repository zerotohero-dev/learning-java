DROP TABLE IF EXISTS stock;

CREATE TABLE STOCK(
  stock_id int,
  company_name varchar(50),
  symbol varchar(10),
  price decimal(10, 2)
);

INSERT INTO STOCK VALUES(1, 'VMWare', 'VMW', 56.25);
INSERT INTO STOCK VALUES(2, 'Cisco', 'CSCO', 156.25);
INSERT INTO STOCK VALUES(3, 'Google', 'VMW', 11.05);
