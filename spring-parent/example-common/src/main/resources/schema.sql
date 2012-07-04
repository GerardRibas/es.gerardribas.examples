create table CUSTOMERS(
	ID bigint IDENTITY primary key,
	NAME varchar(50),
	SURNAME varchar(100),
	CITY varchar(100)
);

CREATE TABLE PRODUCTS (
  ID bigint IDENTITY primary key, 
  NAME varchar(255) not null,
  DESCRIPTION varchar(255) not null,
  PRICE float not null,
  stock integer not null,
);

CREATE TABLE BILLS (
  id bigint IDENTITY primary key, 
  customerid bigint not null,
  date date not null,
  price float not null,
  FOREIGN KEY (customerid) references CUSTOMERS(ID)
);

CREATE TABLE BILLS_LINES (
  id bigint IDENTITY primary key, 
  billid bigint not null,
  productid bigint not null,
  price float not null,
  FOREIGN KEY (billid) references BILLS(ID),
  FOREIGN KEY (productid) references PRODUCTS(ID)
);