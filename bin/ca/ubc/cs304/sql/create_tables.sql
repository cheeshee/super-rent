-- add this to your remote and run start create_tables.sql before running project in intelliJ
CREATE TABLE vehicleTypes (
  vtname VARCHAR2(30) NOT NULL,
  features VARCHAR2(30),
  wrate FLOAT(10) NOT NULL,
  drate FLOAT(10) NOT NULL,
  hrate FLOAT(10) NOT NULL,
  wirate FLOAT(10) NOT NULL,
  dirate FLOAT(10) NOT NULL,
  hirate FLOAT(10) NOT NULL,
  krate FLOAT(10) NOT NULL,
  PRIMARY KEY (vtname)
);

CREATE TABLE customers (
    dlicense NUMBER(18) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    cellphone NUMBER(18),
    address VARCHAR2(30),
    PRIMARY KEY (dlicense)
);

CREATE TABLE reservations (
  confNo NUMBER(18) NOT NULL,
  vtname VARCHAR2(30) NOT NULL,
  vlicense VARCHAR2(10) NOT NULL,
  dlicense NUMBER(18) NOT NULL,
  fromDate DATE NOT NULL,
  fromTime TIMESTAMP NOT NULL,
  toDate DATE NOT NULL,
  toTime TIMESTAMP NOT NULL,
  PRIMARY KEY (confNo),
  FOREIGN KEY (vtname)    REFERENCES vehicleTypes (vtname),
  FOREIGN KEY (dlicense) REFERENCES customers (dlicense)
);

CREATE TABLE vehicles (
  vlicense VARCHAR2(10) NOT NULL,
  make VARCHAR2(30) NOT NULL,
  model VARCHAR2(30) NOT NULL,
  year NUMBER(4) NOT NULL,
  color VARCHAR2(10) NOT NULL,
  odometer NUMBER(9),
  status VARCHAR2(30),
  vtname VARCHAR2(30) NOT NULL,
  location VARCHAR2(50) NOT NULL,
  city VARCHAR2(30) NOT NULL,
  PRIMARY KEY (vlicense),
  FOREIGN KEY (vtname) REFERENCES vehicleTypes (vtname),
  CHECK (status='rented' OR status='maintenance' OR status='available')
);

CREATE TABLE  rentals (
  rid INTEGER NOT NULL,
  vlicense VARCHAR2(10) NOT NULL,
  dlicense NUMBER(18) NOT NULL,
  fromDate DATE NOT NULL,
  fromTime TIMESTAMP NOT NULL,
  toDate DATE NOT NULL,
  toTime TIMESTAMP NOT NULL,
  odometer NUMBER(9),
  cardName VARCHAR2(30) NOT NULL,
  cardNo NUMBER(18) NOT NULL,
  expDate DATE NOT NULL,
  confNo NUMBER(18),
  PRIMARY KEY (rid),
  FOREIGN KEY (vlicense) REFERENCES vehicles (vlicense),
  FOREIGN KEY (dlicense) REFERENCES customers (dlicense),
  FOREIGN KEY (confNo) REFERENCES reservations (confNo)
);

CREATE TABLE returns (
  rid NUMBER(18) NOT NULL,
  return_date DATE NOT NULL,
  time TIMESTAMP NOT NULL,
  odometer NUMBER(9),
  fulltank NUMBER(1),
  value FLOAT(30),
  PRIMARY KEY (rid),
  FOREIGN KEY (rid) REFERENCES rentals(rid),
  CHECK(fulltank = 0 OR fulltank = 1)
);
