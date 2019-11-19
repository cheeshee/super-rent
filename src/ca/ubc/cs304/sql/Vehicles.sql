-- Vehicle (vid, vlicense, make, model, year, color, odometer, status, vtname, location, city)

CREATE TABLE IF NOT EXISTS vehicles (
  vlicense VARCHAR2(10) NOT NULL,
  make VARCHAR2(30) NOT NULL,
  model VARCHAR2(30) NOT NULL,
  year INTEGER(4) NOT NULL,
  color VARCHAR2(10) NOT NULL,
  odometer INTEGER(10),
  status VARCHAR2(30),
  vtname VARCHAR2(30) NOT NULL,
  location VARCHAR2(50) NOT NULL,
  city VARCHAR2(30) NOT NULL,
  PRIMARY KEY (vlicense),
  FOREIGN KEY (vtname) REFERENCES vehicleTypes (vtname),
  CHECK (status="rented" OR status="maintenance" OR status="available")
)