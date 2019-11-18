-- Rent(rid, vid, cellphone, fromDate, fromTime, toDate, toTime, odometer, cardName, cardNo,
-- ExpDate, confNo) 

CREATE TABLE IF NOT EXISTS `rentals` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `vid` int(30) NOT NULL,
  `cellphone` int(20) NOT NULL,
  `fromDate` date NOT NULL,
  `fromTime` time NOT NULL,
  `toDate` date NOT NULL,
  `toTime` time NOT NULL,
  `odometer` int(10),
  `cardName` varchar(30) NOT NULL,
  `cardNo` int(16) NOT NULL,
  `expDate` date NOT NULL,
  `confNo`int(10),
  PRIMARY KEY (`rid`),
  -- FOREIGN KEY (`vid`) references forRent (`vid`),
  -- doesn't say to implement forRent
  FOREIGN KEY (`cellphone`) references customers (`cellphone`),
  -- FOREIGN KEY (`fromDate`)  references timePeriod (`fromDate`)
  -- doesn't say we need to implement timePeriod, so ignore for now
  FOREIGN KEY (`confNo`) references reservations (`confNo`)
)