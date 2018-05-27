USE "statistics";
CREATE TABLE "product_statistics" (
	id int(11) NOT NULL,
	minute varchar(20) NOT NULL,
	product_id int(11) NOT NULL,
	revenue double(10,2) NOT NULL,
	purchases int(11) NOT NULL,
	views int(11) NOT NULL,
	"timestamp" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP 
);

ALTER TABLE "product_statistics" ADD PRIMARY KEY (id);
ALTER TABLE "product_statistics" MODIFY "id" int(11) NOT NULL AUTO_INCREMENT;
