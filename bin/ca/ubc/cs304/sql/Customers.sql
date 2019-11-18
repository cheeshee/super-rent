-- Customer (cellphone, name, address, dlicense) 

CREATE TABLE IF NOT EXISTS `customers` (
  `dlicense` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `cellphone` int(20),
  `address` varchar(30),
  PRIMARY KEY (`dlicense`)
)