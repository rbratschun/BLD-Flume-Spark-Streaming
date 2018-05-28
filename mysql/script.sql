USE "statistics";
CREATE TABLE IF NOT EXISTS product_statistics (
	id int(11) NOT NULL AUTO_INCREMENT,
	minute varchar(20) NOT NULL,
	product_id int(11) NOT NULL,
	revenue DECIMAL(10,2) NOT NULL,
	purchases int(11) NOT NULL,
	views int(11) NOT NULL,
	timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);
