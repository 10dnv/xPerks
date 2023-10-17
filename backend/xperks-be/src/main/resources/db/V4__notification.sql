CREATE TABLE notification (
    notification_id         BIGINT NOT NULL,
    unread                  BOOLEAN DEFAULT FALSE,
    transaction_id          BIGINT NOT NULL,
    CONSTRAINT PK_Notification PRIMARY KEY (notification_id),
    CONSTRAINT FK_Transaction_Notification FOREIGN KEY (transaction_id) REFERENCES transaction (transaction_id)
);