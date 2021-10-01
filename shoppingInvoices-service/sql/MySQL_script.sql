CREATE database [StoreDatabase];
use StoreDatabase;

CREATE TABLE tbl_invoices (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_invoice` int NOT NULL,
  `description` varchar(300) NOT NULL,
  `customer_id` int NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `state` varchar(300) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE tbl_invoice_items (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int DEFAULT NULL,
  `product_id` int NOT NULL,
  `quantity` decimal(15, 2) NOT NULL,
  `price` decimal(15, 2) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (invoice_id) REFERENCES tbl_invoices(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
