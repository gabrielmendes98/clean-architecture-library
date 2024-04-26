CREATE TABLE "user"
(
    id         VARCHAR(32)                 NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    document   VARCHAR(255)                NOT NULL,
    password   VARCHAR(255)                NOT NULL,
    role       SMALLINT                    NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_document UNIQUE (document);