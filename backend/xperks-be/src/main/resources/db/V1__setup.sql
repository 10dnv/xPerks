CREATE TABLE user_details (
    user_id                 BIGINT NOT NULL,
    first_name              VARCHAR(50) NOT NULL,
    last_name               VARCHAR(50) NOT NULL,
    date_of_birth           DATE NOT NULL,
    employment_date         DATE NOT NULL,
    email_address           VARCHAR(100) NOT NULL,
    superior_id             INT,
    version                 BIGINT NOT NULL,
    deleted                 INT NOT NULL,
    CONSTRAINT PK_UserDetails PRIMARY KEY(user_id),
    CONSTRAINT FK_User_Superior FOREIGN KEY(superior_id) REFERENCES user_details(user_id)
);

CREATE TABLE wallet (
    wallet_id               BIGINT NOT NULL,
    user_id                 INT NOT NULL,
    erd_address             VARCHAR(250) NOT NULL,
    balance                 NUMERIC(10,2),
    version                 BIGINT NOT NULL,
    deleted                 INT NOT NULL,
    CONSTRAINT PK_Wallet PRIMARY KEY(wallet_id),
    CONSTRAINT FK_UserDetails_Wallet FOREIGN KEY (user_id) REFERENCES user_details(user_id)
);
