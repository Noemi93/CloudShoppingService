CREATE database [StoreDatabase];
use StoreDatabase;

CREATE TABLE `tbl_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

CREATE TABLE tbl_products (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(300) NOT NULL,
  description varchar(300) NOT NULL,
  stock decimal(15, 2) NOT NULL,
  price decimal(15, 2) NOT NULL,
  status varchar(300) NOT NULL,
  create_at datetime(6) NOT NULL,
  category_id int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES tbl_categories(id)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
