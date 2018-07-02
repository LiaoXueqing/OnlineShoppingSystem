CREATE TABLE if not exists item (
  barcode varchar(36) NOT NULL,
  name varchar(128) DEFAULT NULL,
  units varchar(36) DEFAULT NULL,
  price double DEFAULT NULL,
  discount varchar(36) DEFAULT NULL,
  PRIMARY KEY (barcode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8