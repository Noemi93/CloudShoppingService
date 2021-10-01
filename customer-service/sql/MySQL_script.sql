CREATE database [StoreDatabase];
use StoreDatabase;

CREATE TABLE tbl_regions (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE tbl_customers (
  `id` int NOT NULL AUTO_INCREMENT,
  `numberID` varchar(300) NOT NULL,
  `firstName` varchar(300) NOT NULL,
  `lastName` varchar(300) NOT NULL,
  `email` varchar(300) NOT NULL,
  `photoUrl` varchar(300) NOT NULL,
  `region_id` int NOT NULL,
  `state` varchar(300) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (region_id) REFERENCES tbl_regions(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
