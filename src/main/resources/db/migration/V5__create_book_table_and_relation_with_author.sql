CREATE TABLE book
(
    id          VARCHAR(32)                 NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    description VARCHAR(255),
    status      VARCHAR(255)                NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    author_id   VARCHAR(32)                 NOT NULL,
    CONSTRAINT pk_book PRIMARY KEY (id)
);

ALTER TABLE book
    ADD CONSTRAINT uc_book_title UNIQUE (title);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES author (id);