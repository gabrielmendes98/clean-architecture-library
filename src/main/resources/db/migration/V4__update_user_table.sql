ALTER TABLE "user"
    DROP COLUMN role;

ALTER TABLE "user"
    ADD role VARCHAR(255) NOT NULL;