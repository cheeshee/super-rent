-- Reservation (confNo, vtname, cellphone, fromDate, fromTime, toDate, toTime) 

CREATE TABLE IF NOT EXISTS reservations (
  confNo INTEGER(10) NOT NULL AUTO_INCREMENT,
  vtname VARCHAR2(30) NOT NULL,
  vlicense VARCHAR2(10) NOT NULL,
  dlicense INTEGER(20) NOT NULL,
  fromDate DATE NOT NULL,
  fromTime TIME NOT NULL,
  toDate DATE NOT NULL,
  toTime TIME NOT NULL,
  PRIMARY KEY (confNo),
  FOREIGN KEY (vtname) REFERENCES vehicleTypes (vtname),
  FOREIGN KEY (dlicense) REFERENCES customers (dlicense)
)