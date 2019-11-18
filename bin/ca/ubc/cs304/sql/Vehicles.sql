-- Vehicle (vid, vlicense, make, model, year, color, odometer, status, vtname, location, city)

CREATE TABLE IF NOT EXISTS `vehicles` (
  `vlicense` varchar(10) NOT NULL,
  `make` varchar(30) NOT NULL,
  `model` varchar(30) NOT NULL,
  `year` int(4) NOT NULL,
  `color` varchar(10) NOT NULL,
  `odometer` int(10),
  `status` varchar(30) CHECK (`status`="rented" OR `status`="maintenance" OR `status`="available"),
  `vtname` varchar(30) NOT NULL,
  `location` varchar(50) NOT NULL,
  `city`varchar(30) NOT NULL,
  PRIMARY KEY (`vlicense`),
  -- FOREIGN KEY (`location`, `city`)  references branch (`location`, `city`)
  -- doesn't say we need to implement branch, so ignore for now
  FOREIGN KEY (`vtname`) references vehicleTypes (`vtname`)
)