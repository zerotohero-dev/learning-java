CREATE TABLE stock(
  stock_id int,
  company_name varchar2(50),
  symbol varchar2(10),
  price decimal(10, 2)
);

INSERT INTO stock VALUES(1, 'VMWare', 'VMW', 56.05);
INSERT INTO stock VALUES(2, 'AT&T', 'T', 37.74);
INSERT INTO stock VALUES(3, 'Facebook', 'F', 118.25);
