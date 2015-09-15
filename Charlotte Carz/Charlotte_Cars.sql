/********************************************************
* This script creates the database named Charlotte Cars
*********************************************************/

DROP DATABASE IF EXISTS Charlotte_Cars;
CREATE DATABASE Charlotte_Cars;
USE Charlotte_Cars;


/* This Script create the tables for the database*/

CREATE TABLE user_cred (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(45),
	password VARCHAR(20)

)
CREATE TABLE user_profile (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(45),
	last_name VARCHAR(45),
	mobile_no VARCHAR(45),  
	credi_card_no VARCHAR(45)
	expiration_mon INT,
	expiration_year INT,
	postal_code VARCHAR(10),
	email_id VARCHAR(45)
);



CREATE TABLE Ride (
Ride_id INT PRIMARY KEY AUTO_INCREMENT, 
Driver_id INT NOT NULL, 
User_id INT NOT NULL,
Pickup_location VARCHAR(45), 
Dest_location VARCHAR(45) ,
Date DATETIME,
Distance INT,
  CONSTRAINT Ride_fk_users
	FOREIGN KEY (user_id)
	REFERENCES User (user_id),
  CONSTRAINT Ride_fk_Driver
	FOREIGN KEY (driver_id)
    REFERENCES Driver (Driver_id)
);

CREATE TABLE Driver (
Driver_id INT PRIMARY KEY AUTO_INCREMENT, 
User_id INT, 
Mobile_no VARCHAR(10), 
Car_model VARCHAR(10),
Car_type VARCHAR(15), 
Car_pic BLOB, 
Number_plate VARCHAR(45),
Region VARCHAR(45),
CONSTRAINT Driver_fk_Users
	FOREIGN KEY (user_id)
	REFERENCES User (user_id)
);

CREATE TABLE Rating (
driver_id INT,
user_id INT,
driver_rating VARCHAR(10),
user_rating VARCHAR(10),
CONSTRAINT Rating_fk_Driver
	FOREIGN KEY (driver_id)
	REFERENCES Ride (driver_id),
CONSTRAINT Rating_fk_User
	FOREIGN KEY (user_id)
	REFERENCES User (user_id)
);

CREATE TABLE Billing (
User_id int,
bill_id int PRIMARY KEY, 
ride_id int, 
bill_amt INT,
CONSTRAINT Billing_fk_User
	FOREIGN KEY (user_id)
	REFERENCES User (user_id),
CONSTRAINT Billing_fk_Ride
	FOREIGN KEY (ride_id)
	REFERENCES ride (ride_id)
);



CREATE TABLE Payment (
payment_id int PRIMARY KEY,
user_id int, 
bill_id int, 
Credit_card VARCHAR(10), 
Credits VARCHAR(15),
CONSTRAINT Payment_fk_User
	FOREIGN KEY (user_id)
	REFERENCES User (user_id),
CONSTRAINT Payment_fk_Billing
	FOREIGN KEY (bill_id)
	REFERENCES Billing (bill_id)
);


/*insert*/

INSERT INTO User VALUES('Swapnil',704856957,'John Kirk Dr','Charlotte','NC',28262,'United States', 'sssk@gmail.com');
INSERT INTO Ride VALUES(001,0021,'UNCC','UpTown',DateTime);
INSERT INTO Driver VALUES('New_Driver',00021,7048569535 ,'plk584','sixseater',img,25845, 'NC');
INSERT INTO Rating VALUES(00025,006957,4,3);
INSERT INTO Billing VALUES(0032,4856957,4645,100);
INSERT INTO Payment VALUES(0325,76957,1254874598563254,200);

/*select*/

/*Select customers whose bill amount is more than 60$*/
SELECT user_id , user_name FROM billing b, users u WHERE b.user_id = u.user_id AND bil_amt>60;

/*Select Driver with rating more than 4*/
SELECT driver_id,user_name FROM driver d, rating r WHERE d.driver_id = r.driver_id AND driver_rating > 4;

/*Select all customers who travelled in Audi on 02-04-2015*/
SELECT Customers FROM Ride R, Driver D where Date='20150202' and R.Driver_id=D.Driver_id and D.Car_model='Audi';

/*Assertions:*/
/*CREATE ASSERTION DRIVER_ASSERT*/
CHECK (1=(SELECT Count(Ride_id) FROM Ride where Count(Ride_id)<2));

/*CREATE AEERT_BILL*/
CHECK (0=(SELECT BILL_AMT FROM Billing WHERE bill_amt!<4));



