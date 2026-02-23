create table jdata.PERSONS (
 name VARCHAR(50), surname VARCHAR(50), age INTEGER,
 phone_number VARCHAR(50), city_of_living VARCHAR(50)

)

ALTER TABLE jdata.persons ADD CONSTRAINT persons_pk PRIMARY KEY (name, surname, age)
