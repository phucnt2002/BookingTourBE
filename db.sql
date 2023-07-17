drop table booking;
drop table tour_location;
drop table tour;
drop table location;
drop table tour_guide;
drop table customer;
drop table account;

DROP SEQUENCE account_seq;

CREATE TABLE account(
id NUMBER,
user_name  VARCHAR2(100) NOT NULL UNIQUE,
pass_word  VARCHAR2(100) NOT NULL,
status VARCHAR2(10),
role VARCHAR2(10) NOT NULL,
CONSTRAINT account_PK PRIMARY KEY(id)
);

CREATE SEQUENCE account_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER account_seq_tr
 BEFORE INSERT ON account FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT account_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/


DROP SEQUENCE tour_guide_seq;

CREATE TABLE tour_guide(
id NUMBER,
guide_name  VARCHAR2(100) NOT NULL,
guide_bio  VARCHAR2(4000) NOT NULL,
account_id NUMBER NOT NULL UNIQUE,
CONSTRAINT tour_guide_FK_account FOREIGN KEY (account_id) REFERENCES account(id),
CONSTRAINT tour_guide_PK PRIMARY KEY(id)
);

CREATE SEQUENCE tour_guide_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tour_guide_seq_tr
 BEFORE INSERT ON tour_guide FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT tour_guide_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/


DROP SEQUENCE customer_seq;

CREATE TABLE customer(
id NUMBER,
first_name  VARCHAR2(50) NOT NULL,
last_name  VARCHAR2(50) NOT NULL,
email  VARCHAR2(50) NOT NULL UNIQUE,
phone_number  VARCHAR2(20) NOT NULL UNIQUE,
address VARCHAR2(300),
account_id NUMBER NOT NULL UNIQUE,
CONSTRAINT customer_FK_account FOREIGN KEY (account_id) REFERENCES account(id),
CONSTRAINT customer_PK PRIMARY KEY(id)
);

CREATE SEQUENCE customer_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER customer_seq_tr
 BEFORE INSERT ON customer FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT customer_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

DROP SEQUENCE location_seq;

CREATE TABLE location(
id NUMBER,
location_name  VARCHAR2(100) NOT NULL UNIQUE,
address  VARCHAR2(500) NOT NULL,
city  VARCHAR2(50) NOT NULL,
country  VARCHAR2(50) NOT NULL,
CONSTRAINT location_PK PRIMARY KEY(id)
-- CONSTRAINT location_FK_tour FOREIGN KEY (tour_id) REFERENCES tour(id),
);

CREATE SEQUENCE location_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER location_seq_tr
 BEFORE INSERT ON location FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT location_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/


DROP SEQUENCE tour_seq;

CREATE TABLE tour(
id NUMBER,
tour_name  VARCHAR2(100) NOT NULL,
description  VARCHAR2(4000) NOT NULL,
price NUMBER,
duration NUMBER,
quality NUMBER,
time_start DATE,
time_end DATE,
-- location_id NUMBER NOT NULL,
guide_id NUMBER,
-- CONSTRAINT tour_FK_location FOREIGN KEY (location_id) REFERENCES location(id),
CONSTRAINT tour_FK_guide FOREIGN KEY (guide_id) REFERENCES tour_guide(id),
CONSTRAINT tour_PK PRIMARY KEY(id)
);

CREATE SEQUENCE tour_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tour_seq_tr
 BEFORE INSERT ON tour FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT tour_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

DROP SEQUENCE tour_location_seq;

CREATE TABLE tour_location(
id NUMBER,
tour_id NUMBER NOT NULL,
location_id NUMBER NOT NULL,
CONSTRAINT tour_location_FK_tour FOREIGN KEY (tour_id) REFERENCES tour(id),
CONSTRAINT tour_location_FK_location FOREIGN KEY (location_id) REFERENCES location(id),
CONSTRAINT tour_location_PK PRIMARY KEY(id)
);

CREATE SEQUENCE tour_location_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER tour_location_seq_tr
 BEFORE INSERT ON tour_location FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT tour_location_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/



DROP SEQUENCE booking_seq;

CREATE TABLE booking(
id NUMBER,
booking_date DATE,
note VARCHAR2(400),
tour_id NUMBER NOT NULL,
customer_id NUMBER NOT NULL,
CONSTRAINT booking_FK_tour FOREIGN KEY (tour_id) REFERENCES tour(id),
CONSTRAINT booking_FK_customer FOREIGN KEY (customer_id) REFERENCES customer(id),
CONSTRAINT booking_PK PRIMARY KEY(id)
);

CREATE SEQUENCE booking_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER booking_seq_tr
 BEFORE INSERT ON booking FOR EACH ROW
 WHEN (NEW.id IS NULL)
BEGIN
 SELECT booking_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/