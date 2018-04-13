CREATE DATABASE "Hamster"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE SCHEMA "User"
AUTHORIZATION postgres;

CREATE TABLE "User"."Credential"
(
    id bigserial,
    login character(200)[],
    password character(255)[],
    PRIMARY KEY (id),
    CONSTRAINT "UserCredential_id_uc" UNIQUE (login)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE "User"."Credential"
    OWNER to postgres;

CREATE TABLE "User"."Information"
(
    id bigserial,
    firstname character(100)[],
    lastname character(100)[],
    PRIMARY KEY (id),
    CONSTRAINT "UserInformation_id_fk" FOREIGN KEY (id)
        REFERENCES "User"."Credential" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
);

ALTER TABLE "User"."Information"
    OWNER to postgres;