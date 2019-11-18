-- Reservation (confNo, vtname, cellphone, fromDate, fromTime, toDate, toTime) 

CREATE TABLE IF NOT EXISTS `reservations` (
  `confNo` int(10) NOT NULL AUTO_INCREMENT,
  `vtname` varchar(30) NOT NULL,
  `cellphone` int(20) NOT NULL,
  `fromDate` date NOT NULL,
  `fromTime` time NOT NULL,
  `toDate` date NOT NULL,
  `toTime` time NOT NULL,
  PRIMARY KEY (`confNo`),
  FOREIGN KEY (`vtname`)    references vehicleTypes (`vtname`),
  FOREIGN KEY (`cellphone`) references customers (`cellphone`)
  -- FOREIGN KEY (`fromDate`)  references timePeriod (`fromDate`)
  -- doesn't say we need to implement timePeriod, so ignore for now

)