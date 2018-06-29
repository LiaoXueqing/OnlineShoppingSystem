CREATE TABLE if not exists item (
  id varchar(36) NOT NULL,
  name varchar(128) DEFAULT NULL,
  units varchar(36) DEFAULT NULL,
  price double DEFAULT NULL,
  discount double DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8