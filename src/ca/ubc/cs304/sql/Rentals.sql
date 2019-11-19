-- Rent(rid, vid, cellphone, fromDate, fromTime, toDate, toTime, odometer, cardName, cardNo,
-- ExpDate, confNo) 

CREATE TABLE IF NOT EXISTS rentals (
  rid INTEGER(10) NOT NULL AUTO_INCREMENT,
  vlicense VARCHAR2(10) NOT NULL,
  dlicense INTEGER(20) NOT NULL,
  fromDate DATE NOT NULL,
  fromTime TIME NOT NULL,
  toDate DATE NOT NULL,
  toTime TIME NOT NULL,
  odometer INTEGER(10),
  cardName VARCHAR2(30) NOT NULL,
  cardNo INTEGER(16) NOT NULL,
  expDate DATE NOT NULL,
  confNo INTEGER(10),
  PRIMARY KEY (rid),
  FOREIGN KEY (vlicense) REFERENCES vehicles (vlicense),
  FOREIGN KEY (dlicense) REFERENCES customers (dlicense),
  FOREIGN KEY (confNo) REFERENCES reservations (confNo)
)