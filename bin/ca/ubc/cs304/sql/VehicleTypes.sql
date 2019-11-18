-- VehicleType(vtname, features, wrate, drate, hrate, wirate, dirate, hirate, krate)) 

CREATE TABLE IF NOT EXISTS `vehicleTypes` (
  `vtname` varchar(30) NOT NULL,
  `features` varchar(30),
  `wrate` float(10) NOT NULL,
  `drate` float(10) NOT NULL,
  `hrate` float(10) NOT NULL,
  `wirate` float(10) NOT NULL,
  `dirate` float(10) NOT NULL,
  `hirate` float(10) NOT NULL,
  `krate` float(10) NOT NULL,
  PRIMARY KEY (`vtname`)
)