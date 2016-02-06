CREATE TABLE item (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  description VARCHAR(250),
  longitude FLOAT,
  latitude FLOAT,
  capacity INT
)