CREATE TABLE basketball_game
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    attendance       INT NOT NULL,
    season INT NOT NULL,
    home_team_id BIGINT NOT NULL,
    away_team_id   BIGINT NOT NULL
);

ALTER TABLE basketball_game
ADD CONSTRAINT fk_game_home_team FOREIGN KEY (home_team_id)
REFERENCES basketball_team (id);

ALTER TABLE basketball_game
ADD CONSTRAINT fk_game_away_team FOREIGN KEY (away_team_id)
REFERENCES basketball_team (id);