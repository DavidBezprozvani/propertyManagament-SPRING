INSERT INTO Owner (id, name, surname, social_number) VALUES
(1, 'John', 'Doe', '111'),
(2, 'Don', 'Johnson', '222'),
(3, 'Vito', 'Corleone', '333'),
(4, 'Oscar', 'Wilde', '444'),
(5, 'Victor', 'Hugo', '555'),
(6, 'Salvador', 'Dali', '666');

INSERT INTO Property (id, address, market_value, size, type, owner_id) VALUES
(1, 'Backend alley 69, London', 1000000.00, 120.69, 'HOUSE', 1),
(2, 'Java Street 911, Los Angeles', 200000.99, 59.44, 'APARTMENT', 2),
(3, 'Hello World 420, Amsterdam', 3000000.00, 105.44, 'INDUSTRIAL', 3),
(4, 'Convention street 1, Istanbul', 400000.00, 35.33, 'COMMERCIAL', 4),
(5, 'Frontend alley 33, London', 543210.00, 45.00, 'APARTMENT', 5),
(6, 'Thomas Shelby street 123, Birmingham', 654321.23, 109.69, 'HOUSE', 6);
