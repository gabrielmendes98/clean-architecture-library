CREATE TABLE author
(
    id         CHAR(32)     NOT NULL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL
);