create table basketball_season
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    games_count  int not null default 0,
    year         int not null,
    is_completed boolean      default false
);

alter table basketball_season
    add constraint season_year_unique UNIQUE (year);
