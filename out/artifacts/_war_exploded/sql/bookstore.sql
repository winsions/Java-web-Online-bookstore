CREATE DATABASE bookstore;

CREATE TABLE USER(
  uid CHAR(32) PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  PASSWORD VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  code CHAR(64) NOT NULL,
  state BOOLEAN
);

INSERT  INTO USER VALUES ('1','zhangSan','123');
INSERT  INTO USER VALUES ('2','liSi','123');
INSERT  INTO USER VALUES ('3','wangWu','123');

SELECT * FROM USER;

///////////////////////////////////////////

CREATE TABLE category (
  cid CHAR(32) PRIMARY KEY,
  cname VARCHAR(100) NOT NULL
);

INSERT  INTO category(cid,cname) VALUES ('1','JavaSE');
INSERT  INTO category(cid,cname) VALUES ('2','JavaEE');
INSERT  INTO category(cid,cname) VALUES ('3','Javascript');

SELECT * FROM category;

///////////////////////////////////////////

CREATE TABLE book (
  bid CHAR(32) PRIMARY KEY,
  bname VARCHAR(100),
  price DECIMAL(5,1),
  author VARCHAR(20),
  image VARCHAR(200),
  cid CHAR(32),
  FOREIGN KEY (cid) REFERENCES category(cid)
);

INSERT  INTO book VALUES ('1','Java编程思想（第4版）','75.6','qdmmy6','book_img/9317290-1_l.jpg','1');
INSERT  INTO book VALUES ('2','Java核心技术卷1','68.5','qdmmy6','book_img/20285763-1_l.jpg','1');
INSERT  INTO book VALUES ('3','Java就业培训教程','39.9','张孝祥','book_img/8758723-1_l.jpg','1');
INSERT  INTO book VALUES ('4','Head First java','47.5','（美）塞若','book_img/9265169-1_l.jpg','1');
INSERT  INTO book VALUES ('5','JavaWeb开发详解','83.3','孙鑫','book_img/22788412-1_l.jpg','2');
INSERT  INTO book VALUES ('6','Struts2深入详解','63.2','孙鑫','book_img/20385925-1_l.jpg','2');
INSERT  INTO book VALUES ('7','精通Hibernate','30.0','孙卫琴','book_img/8991366-1_l.jpg','2');
INSERT  INTO book VALUES ('8','精通Spring2.x','63.2','陈华雄','book_img/20029394-1_l.jpg','2');
INSERT  INTO book VALUES ('9','Javascript权威指南','93.6','（美）弗兰纳根','book_img/22722790-1_l.jpg','3');

SELECT * FROM book;

/////////////////////////////////////////////

CREATE TABLE orders (
  oid CHAR(32) PRIMARY KEY,
  ordertime DATETIME,
  price DECIMAL(10,0),
  state SMALLINT(1),
  uid CHAR(32),
  address VARCHAR(200),
  FOREIGN KEY (uid) REFERENCES USER (uid)
);

SELECT * FROM orders;

/////////////////////////////////////////////

CREATE TABLE orderitem (
  iid CHAR(32) PRIMARY KEY,
  COUNT INT,
  subtotal DECIMAL(10,0),
  oid CHAR(32),
  bid CHAR(32),
  FOREIGN KEY (oid) REFERENCES orders (oid),
  FOREIGN KEY (bid) REFERENCES book (bid)
);

SELECT * FROM orderitem;