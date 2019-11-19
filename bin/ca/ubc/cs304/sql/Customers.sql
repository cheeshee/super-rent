-- Customer (cellphone, name, address, dlicense) 

CREATE TABLE IF NOT EXISTS customers (
  dlicense INTEGER(20) NOT NULL,
  name VARCHAR2(30) NOT NULL,
  cellphone INTEGER(20),
  address VARCHAR2(30),
  PRIMARY KEY (dlicense)
)