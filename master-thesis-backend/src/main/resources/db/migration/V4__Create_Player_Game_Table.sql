CREATE TABLE basketball_player_game (
    player_id BIGINT,
    game_id BIGINT,
    PRIMARY KEY (player_id, game_id)
);

ALTER TABLE basketball_player_game
ADD CONSTRAINT fk_basketball_player_game_player
FOREIGN KEY (player_id)
REFERENCES basketball_player (id);

ALTER TABLE basketball_player_game
ADD CONSTRAINT fk_basketball_player_game_game
FOREIGN KEY (game_id)
REFERENCES basketball_game (id);