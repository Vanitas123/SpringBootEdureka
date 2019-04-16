CREATE TABLE hotel (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100),
  address VARCHAR(100) NOT NULL,
  totalRooms BIGINT NOT NULL,
  booked BIGINT NOT NULL
);

CREATE TABLE hotel_rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT,
    customer_id BIGINT,
    rating INT,
    comment VARCHAR(100));
    
ALTER TABLE hotel_rating ADD FOREIGN KEY (hotel_id) REFERENCES hotel(id);
ALTER TABLE hotel_rating ADD UNIQUE MyConstraint (hotel_id, customer_id);
    