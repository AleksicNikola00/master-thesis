CREATE TABLE basketball_team
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    name        VARCHAR(255),
    description VARCHAR(255),
    image_url   VARCHAR(255),
    UNIQUE (name)
);