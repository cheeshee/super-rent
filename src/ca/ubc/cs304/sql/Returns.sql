-- Return(rid, date, time, odometer, fulltank, value) 

CREATE TABLE IF NOT EXISTS returns (
  rid INTEGER(10) NOT NULL,
  date DATE NOT NULL,
  time TIME NOT NULL,
  odometer INTEGER(10),
  fulltank BIT,    -- is this just true/false ??
  value FLOAT(30),  -- what even is this??
  PRIMARY KEY (rid),
  FOREIGN KEY (rid) REFERENCES rentals(rid)
)