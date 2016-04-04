CREATE TABLE warehouse_item (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  warehouse_id INT,
  good_id INT,
  amount INT
)