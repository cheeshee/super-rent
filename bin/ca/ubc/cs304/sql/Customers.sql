-- Customer (cellphone, name, address, dlicense) 

CREATE TABLE IF NOT EXISTS `customers` (
  `dlicense` int(20) NOT NULL,
  `name` varchar(30),
  `cellphone` int(20) NOT NULL,
  `address` varchar(30) NOT NULL,
  PRIMARY KEY (`dlicense`)
)