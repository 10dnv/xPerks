CREATE TABLE user_details (
    user_id                 BIGINT NOT NULL,
    first_name              VARCHAR(50) NOT NULL,
    last_name               VARCHAR(50) NOT NULL,
    date_of_birth           DATE NOT NULL,
    employment_date         DATE NOT NULL,
    email_address           VARCHAR(100) NOT NULL,
    password                VARCHAR(100) NOT NULL,
    superior_id             BIGINT NOT NULL,
    erd_address             VARCHAR(250) DEFAULT NULL,
    balance                 NUMERIC(10,2) DEFAULT 0,
    CONSTRAINT PK_UserDetails PRIMARY KEY(user_id),
    CONSTRAINT FK_User_Superior FOREIGN KEY(superior_id) REFERENCES user_details(user_id),
    CONSTRAINT UQ_User_Superior UNIQUE(user_id, superior_id)
);

INSERT INTO user_details(user_id, first_name, last_name, date_of_birth, employment_date, email_address, password, superior_id, erd_address, balance) VALUES
(1, 'Alex', 'Castillo', '1982-02-07', '2019-02-20', 'alex.c@xperks.com', '$2a$10$6MvQXllOIEGzxC8xaVdbyOubAgfEKND3Z9tUOKCWCcBZJr8tvf/26', 1, null, 0),
(2, 'Jackson', 'Harrington', '1987-10-04', '2021-10-02', 'jackson.h@xperks.com', '$2a$10$fczufh9Axqf5ZVpdtLmJ5.WIx8TtlPkqLGVCeSQm.p1DcfEF3JHMi', 1, null, 0),
(3, 'Nadia', 'Gordon', '1999-11-01', '2022-02-09', 'nadia.g@xperks.com', '$2a$10$sT2Xo4ZtSWg/fO1XJbTcyOy3ZOkA89wKSCn06ei6dc8yTk5wEkf.O', 1, null, 0),
(4, 'Madison', 'Lee', '1990-12-12', '2021-06-11', 'madison.l@xperks.com', '$2a$10$b3Pshj7nFcrgmDsZvsMMUOOJ1H5jbQ05ILC7uPKZcY2OKrT71G0eC', 2, null, 0),
(5, 'Martin', 'Smith', '1995-01-08', '2017-04-02', 'martin.s@xperks.com', '$2a$10$/p9Yn61nPWwjSBQWLGqwWOY/ptIZ3V9KLo.1P00snPq8aVbGuU65q', 2, null, 0),
(6, 'Sam', 'Flynn', '1982-02-17', '2018-11-01', 'sam.f@xperks.com', '$2a$10$t339TQSGtyCD0op4Z.77Xud6SBpOhg0TFBxJAMjsVqbJGzZwuHy3u', 1, null, 0),
(7, 'Stephanie', 'Glenn', '1989-02-11', '2021-07-25', 'stephanie.g@xperks.com', '$2a$10$IsBdcpA3UIaBnJTtZ8.tXOJldWifb5iSIfgAl8VFpRLfqaXlJPK8.', 6, null, 0),
(8, 'Grace', 'Roberson', '2000-09-07', '2023-01-13', 'grace.r@xperks.com', '$2a$10$Mi7mBAMpWsDDKIHv.eluWusTVsGHuzkCXhlMCSrA1X1ilaznMdJTm', 6, null, 0),
(9, 'Adam', 'Maxwell', '1992-11-10', '2022-04-09', 'adam.m@xperks.com', '$2a$10$hrbERDXpF5mBZbUgy7I4.eQCvZjblkNl6S7/blcEtLIRP/y3cqFAm', 2, null, 0),
(10, 'Barbara', 'Noble', '1994-04-29', '2021-05-22', 'barbara.n@xperks.com', '$2a$10$G2O2e4sL8PDg0wfZt0m8.ea11y9iXFBtTu5BQ6gFNFBY1i2pIBIo2', 1, null, 0),
(11, 'a', 'a', '1982-07-08', '2020-10-02', 'a@a.com', '$2a$10$/VO7DENbu2jVk.2vm64KYeuLV/DYl0VBLgFA29q64..OmvF010uPa', 6, null, 0)
