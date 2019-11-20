-- insert into existing tables

INSERT INTO customers VALUES(18901324, 'John Doe', 11920301234, '123 Cambie St');
INSERT INTO customers VALUES(67890234, 'Alex Ritter', 14564564567, '23 46 Ave');
INSERT INTO customers VALUES(19823478, 'Mickey Dee', 18908908904, '12340 University Dr');
INSERT INTO customers VALUES(11222222, 'Tanya Kaiser', null, null);
INSERT INTO customers VALUES(23423422, 'Alexa Cline', null, '5463 University Dr');
INSERT INTO customers VALUES(12344444, 'Willard Hinton', null, null);
INSERT INTO customers VALUES(67566655, 'Darius Stark', 13241123441, null);
INSERT INTO customers VALUES(09767845, 'Lucca Harrell', 11231234123, '123 Yeet Rd');
INSERT INTO customers VALUES(14235234, 'Nikola Stevens', 18928654345, '11 Art Blvd');
INSERT INTO customers VALUES(75635345, 'Izzy Brook', 10989877655, '456 Test Circle');
INSERT INTO customers VALUES(35634566, 'Nikola Stevens', 10202020202, '121 Byte Ave');
INSERT INTO customers VALUES(12456765, 'Eshaan Callaghan', 10980989992, null);
INSERT INTO customers VALUES(47683333, 'Conan Obrien', null, null);
INSERT INTO customers VALUES(12312456, 'Mildred Tapia', null, null);


INSERT INTO vehicleTypes VALUES('Economy', 'lowest price', 1000.60, 200.60, 30.50, 500.35, 100.87, 70.45, 0.32);
INSERT INTO vehicleTypes VALUES('Compact', null, 1050.20, 210.40, 34.50, 525.34, 105.12, 73.65, 0.38);
INSERT INTO vehicleTypes VALUES('Mid-size', null, 1067.43, 225.02, 38.23, 547.12, 115.34, 80.23, 0.38);
INSERT INTO vehicleTypes VALUES('Standard', null, 1056.20, 217.32, 36.22, 542.01, 111.20, 78.03, 0.37);
INSERT INTO vehicleTypes VALUES('Full-size', null, 1122.10, 274.10, 43.50, 568.30, 132.23, 83.39, 0.41);
INSERT INTO vehicleTypes VALUES('SUV', '4wd or awd', 1240.65, 290.10, 50.08, 575.35, 141.22, 86.22, 0.43);
INSERT INTO vehicleTypes VALUES('Truck', 'suitable for RVs and trailers', 1123.10, 280.65, 45.20, 572.12, 139.23, 84.39, 0.43);


INSERT INTO vehicles VALUES('123-ABC', 'Toyota', 'Corolla', 2007, 'white', 120321, 'available', 'Compact', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('12-ABC', 'Toyota', 'Corolla', 2007, 'white', 1223, 'maintenance', 'Compact', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WEIRD', 'Toyota', 'Corolla', 2007, 'white', 12313, 'rented', 'Compact', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('ASDF', 'Toyota', 'Corolla', 2004, 'white', 46433, 'available', 'Compact', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('123CF', 'Toyota', 'Corolla', 2007, 'white', 87654, 'available', 'Compact', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-FFF', 'Toyota', 'Corolla', 2007, 'white', 944333, 'available', 'Compact', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-123', 'Toyota', 'Corolla', 2008, 'white', 9755, 'available', 'Compact', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-123123', 'Toyota', 'Corolla', 2007, 'white', 3453, 'rented', 'Compact', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2341', 'Toyota', 'Corolla', 2007, 'white', 73345, 'available', 'Compact', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2422', 'Toyota', 'Corolla', 2007, 'white', 7333, 'available', 'Compact', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WEDDC', 'Toyota', 'Corolla', 2009, 'white', 1344, 'available', 'Compact', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GWAAAA', 'Toyota', 'Corolla', 2007, 'white', 34533, 'available', 'Compact', 'QWE', 'Victoria');

INSERT INTO vehicles VALUES('123-QQ', 'Fiat', '500', 2007, 'red', 120321, 'available', 'Economy', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('12-AQQ', 'Fiat', '500', 2007, 'blue', 1223, 'available', 'Economy', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WEIQQ', 'Fiat', '500', 2007, 'green', 12313, 'rented', 'Economy', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('ASQQ', 'Fiat', '500', 2004, 'black', 46433, 'available', 'Economy', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('12QQF', 'Fiat', '500', 2007, 'white', 87654, 'available', 'Economy', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QQF', 'Fiat', '500', 2007, 'red', 944333, 'available', 'Economy', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-QQ3', 'Fiat', '500', 2008, 'red', 9755, 'available', 'Economy', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QQ3123', 'Fiat', '500', 2007, 'red', 3453, 'available', 'Economy', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2Q41', 'Fiat', '500', 2007, 'red', 73345, 'available', 'Economy', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2Q22', 'Fiat', '500', 2007, 'red', 7333, 'rented', 'Economy', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WEDQC', 'Fiat', '500', 2009, 'red', 1344, 'available', 'Economy', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GWAQAA', 'Fiat', '500', 2007, 'red', 34533, 'available', 'Economy', 'QWE', 'Victoria');

INSERT INTO vehicles VALUES('1A23-QQ', 'Nissan', 'Altima', 2007, 'red', 120321, 'available', 'Mid-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('12A-AQQ', 'Nissan', 'Altima', 2007, 'blue', 1223, 'available', 'Mid-size', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WEIAQQ', 'Nissan', 'Altima', 2007, 'green', 12313, 'rented', 'Mid-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('ASQQA', 'Nissan', 'Altima', 2004, 'black', 46433, 'available', 'Mid-size', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('12QQFA', 'Nissan', 'Altima', 2007, 'white', 87654, 'available', 'Mid-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QQFA', 'Nissan', 'Altima', 2007, 'red', 944333, 'available', 'Mid-size', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-QAQ3', 'Nissan', 'Altima', 2008, 'red', 9755, 'available', 'Mid-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QQA3123', 'Nissan', 'Altima', 2007, 'red', 3453, 'available', 'Mid-size', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2QA41', 'Nissan', 'Altima', 2007, 'red', 73345, 'available', 'Mid-size', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2QA22', 'Nissan', 'Altima', 2007, 'red', 7333, 'rented', 'Mid-size', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WEDQCA', 'Nissan', 'Altima', 2009, 'red', 1344, 'available', 'Mid-size', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GWAQAAA', 'Nissan', 'Altima', 2007, 'red', 34533, 'available', 'Mid-size', 'QWE', 'Victoria');

INSERT INTO vehicles VALUES('B123-QQ', 'Volkswagen', 'Jetta', 2007, 'red', 120321, 'available', 'Standard', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('1B2-AQQ', 'Volkswagen', 'Jetta', 2007, 'blue', 1223, 'available', 'Standard', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WBEIQQ', 'Volkswagen', 'Jetta', 2007, 'green', 12313, 'rented', 'Standard', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('ASBQQ', 'Volkswagen', 'Jetta', 2004, 'black', 46433, 'available', 'Standard', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('12QBQF', 'Volkswagen', 'Jetta', 2007, 'white', 87654, 'available', 'Standard', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QBQF', 'Volkswagen', 'Jetta', 2007, 'red', 944333, 'available', 'Standard', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-QBQ3', 'Volkswagen', 'Jetta', 2008, 'red', 9755, 'available', 'Standard', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QBQ3123', 'Volkswagen', 'Jetta', 2007, 'red', 3453, 'available', 'Standard', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2QB41', 'Volkswagen', 'Jetta', 2007, 'red', 73345, 'available', 'Standard', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2QB22', 'Volkswagen', 'Jetta', 2007, 'red', 7333, 'rented', 'Standard', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WEDQCB', 'Volkswagen', 'Jetta', 2009, 'red', 1344, 'available', 'Standard', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GWAQABA', 'Volkswagen', 'Jetta', 2007, 'red', 34533, 'available', 'Standard', 'QWE', 'Victoria');

INSERT INTO vehicles VALUES('HB123-QQ', 'Chevrolet', 'Impala', 2007, 'red', 120321, 'available', 'Full-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('H1B2-AQQ', 'Chevrolet', 'Impala', 2007, 'blue', 1223, 'available', 'Full-size', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WHBEIQQ', 'Chevrolet', 'Impala', 2007, 'green', 12313, 'rented', 'Full-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('ASHBQQ', 'Chevrolet', 'Impala', 2004, 'black', 46433, 'available', 'Full-size', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('12HQBQF', 'Chevrolet', 'Impala', 2007, 'white', 87654, 'available', 'Full-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-HQBQF', 'Chevrolet', 'Impala', 2007, 'red', 944333, 'available', 'Full-size', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-HQBQ3', 'Chevrolet', 'Impala', 2008, 'red', 9755, 'available', 'Full-size', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-HQBQ3123', 'Chevrolet', 'Impala', 2007, 'red', 3453, 'available', 'Full-size', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-H2QB41', 'Chevrolet', 'Impala', 2007, 'red', 73345, 'available', 'Full-size', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-H2QB22', 'Chevrolet', 'Impala', 2007, 'red', 7333, 'rented', 'Full-size', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WEHDQCB', 'Chevrolet', 'Impala', 2009, 'red', 1344, 'available', 'Full-size', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GWHAQABA', 'Chevrolet', 'Impala', 2007, 'red', 34533, 'available', 'Full-size', 'QWE', 'Victoria');

INSERT INTO vehicles VALUES('B143-QQ', 'Infiniti', 'QX50', 2007, 'red', 120321, 'available', 'SUV', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('142-AQQ', 'Infiniti', 'QX50', 2007, 'blue', 1223, 'available', 'SUV', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WB4IQQ', 'Infiniti', 'QX50', 2007, 'green', 12313, 'rented', 'SUV', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('A4BQQ', 'Infiniti', 'QX50', 2004, 'black', 46433, 'available', 'SUV', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('124QF', 'Infiniti', 'QX50', 2007, 'white', 87654, 'available', 'SUV', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-Q4BQF', 'Infiniti', 'QX50', 2007, 'red', 944333, 'available', 'SUV', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-Q4Q3', 'Infiniti', 'QX50', 2008, 'red', 9755, 'available', 'SUV', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QBQ43123', 'Infiniti', 'QX50', 2007, 'red', 3453, 'available', 'SUV', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-4B41', 'Infiniti', 'QX50', 2007, 'red', 73345, 'available', 'SUV', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2Q422', 'Infiniti', 'QX50', 2007, 'red', 7333, 'rented', 'SUV', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WED4CB', 'Infiniti', 'QX50', 2009, 'red', 1344, 'available', 'SUV', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GW4BA', 'Infiniti', 'QX50', 2007, 'red', 34533, 'available', 'SUV', 'QWE', 'Victoria');


INSERT INTO vehicles VALUES('B93-QQ', 'Ford', 'F150', 2007, 'red', 120321, 'available', 'Truck', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('149-AQQ', 'Ford', 'F150', 2007, 'blue', 1223, 'available', 'Truck', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('WB49Q', 'Ford', 'F150', 2007, 'green', 12313, 'rented', 'Truck', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('A49QQ', 'Ford', 'F150', 2004, 'black', 46433, 'available', 'Truck', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('129QF', 'Ford', 'F150', 2007, 'white', 87654, 'available', 'Truck', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-Q9BQF', 'Ford', 'F150', 2007, 'red', 944333, 'available', 'Truck', 'Dempster', 'Vancouver');
INSERT INTO vehicles VALUES('U-Q93', 'Ford', 'F150', 2008, 'red', 9755, 'available', 'Truck', 'UBC', 'Vancouver');
INSERT INTO vehicles VALUES('U-QBQ493', 'Ford', 'F150', 2007, 'red', 3453, 'available', 'Truck', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-4941', 'Ford', 'F150', 2007, 'red', 73345, 'available', 'Truck', 'Bow', 'Calgary');
INSERT INTO vehicles VALUES('U-2922', 'Ford', 'F150', 2007, 'red', 7333, 'rented', 'Truck', 'ASD', 'Richmond');
INSERT INTO vehicles VALUES('WED49', 'Ford', 'F150', 2009, 'red', 1344, 'available', 'Truck', 'ZXC', 'Burnaby');
INSERT INTO vehicles VALUES('GW9', 'Ford', 'F150', 2007, 'red', 34533, 'available', 'Truck', 'QWE', 'Victoria');