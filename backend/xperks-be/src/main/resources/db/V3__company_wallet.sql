CREATE TABLE company_wallet(
    company_wallet_id       BIGINT NOT NULL,
    amount                  INT NOT NULL,
    CONSTRAINT PK_Company_Wallet PRIMARY KEY (company_wallet_id)
);

--dummy data
INSERT INTO company_wallet (company_wallet_id, amount)
VALUES (1, 5000);