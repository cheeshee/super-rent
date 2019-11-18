-- Customer (cellphone, name, address, dlicense) 

CREATE TABLE IF NOT EXISTS `customers` (
  `cellphone` int(20) NOT NULL,
  `name` varchar(30),
  `address` varchar(30) NOT NULL,
  `dlicense` int(20) NOT NULL,
  PRIMARY KEY (`cellphone`)
)