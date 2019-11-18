-- Vehicle (vid, vlicense, make, model, year, color, odometer, status, vtname, location, city)

CREATE TABLE IF NOT EXISTS `vehicles` (
  `vid` int(30) NOT NULL AUTO_INCREMENT,
  `vlicense` varchar(10) NOT NULL,
  `make` varchar(30) NOT NULL,
  `model` varchar(30) NOT NULL,
  `year` int(4) NOT NULL,
  `color` varchar(10) NOT NULL,
  `odometer` int(10),
  `status` varchar(30) CHECK (`status`="for_rent" OR `status`="for_sale"),
  `vtname` varchar(30) NOT NULL,
  `location` varchar(50) NOT NULL,
  `city`varchar(30) NOT NULL,
  PRIMARY KEY (`vid`),
  -- FOREIGN KEY (`location`, `city`)  references branch (`location`, `city`)
  -- doesn't say we need to implement branch, so ignore for now
  FOREIGN KEY (`vtname`) references vehicleTypes (`vtname`)
)