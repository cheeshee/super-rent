-- Return(rid, date, time, odometer, fulltank, value) 

CREATE TABLE IF NOT EXISTS `returns` (
  `rid` int(10) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `odometer` int(10),
  `fulltank` bit,    -- is this just true/false ??
  `value` float(30),  -- what even is this??
  PRIMARY KEY (`rid`),
  FOREIGN KEY (`rid`) references rentals(`rid`)
)