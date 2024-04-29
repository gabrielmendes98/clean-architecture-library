CREATE TABLE rent
(
    id            VARCHAR(32)                 NOT NULL,
    rent_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    return_date   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    returned_date TIMESTAMP WITHOUT TIME ZONE,
    book_id       VARCHAR(32)                 NOT NULL,
    user_id       VARCHAR(32)                 NOT NULL,
    CONSTRAINT pk_rent PRIMARY KEY (id)
);

ALTER TABLE rent
    ADD CONSTRAINT FK_RENT_ON_BOOK FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE rent
    ADD CONSTRAINT FK_RENT_ON_USER FOREIGN KEY (user_id) REFERENCES "user" (id);