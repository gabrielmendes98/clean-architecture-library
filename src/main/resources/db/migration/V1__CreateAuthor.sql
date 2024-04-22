CREATE TABLE author
(
    id         VARCHAR(32)  NOT NULL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL
);