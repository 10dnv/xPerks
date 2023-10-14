CREATE TABLE transaction(
    transaction_id           BIGINT NOT NULL,
    sender_id                BIGINT NOT NULL,
    receiver_id              BIGINT NOT NULL,
    approver_id              BIGINT NOT NULL,
    status                   VARCHAR(50) NOT NULL,
    amount                   VARCHAR(10) NOT NULL,
    description              VARCHAR(500),
    CONSTRAINT PK_Transaction PRIMARY KEY(transaction_id),
    CONSTRAINT FK_User_Sender FOREIGN KEY(sender_id) REFERENCES user_details(user_id),
    CONSTRAINT FK_User_Receiver FOREIGN KEY(receiver_id) REFERENCES user_details(user_id),
    CONSTRAINT FK_User_Approver FOREIGN KEY(approver_id) REFERENCES user_details(user_id),
    CONSTRAINT Ck_Sender_Receiver CHECK (sender_id <> receiver_id AND receiver_id <> approver_id AND sender_id <> approver_id)
);

CREATE SEQUENCE transaction_seq
START 10
INCREMENT 1
OWNED BY transaction.transaction_id;