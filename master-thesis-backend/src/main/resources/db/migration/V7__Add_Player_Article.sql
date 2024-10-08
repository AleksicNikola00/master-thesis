CREATE TABLE basketball_player_article
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    article_url VARCHAR(255) NOT NULL,
    article_id VARCHAR(255) NOT NULL,
    player_id BIGINT
);

ALTER TABLE basketball_player_article
    ADD CONSTRAINT FK_BASKETBALL_PLAYER_ARTICLE_ON_PLAYER
        FOREIGN KEY (player_id) REFERENCES basketball_player (id);

ALTER TABLE basketball_player
    ADD article_count INTEGER NOT NULL 
        DEFAULT 0;

CREATE OR REPLACE FUNCTION update_article_count()
RETURNS TRIGGER AS $$
    BEGIN
            UPDATE basketball_player
            SET article_count = article_count + 1
            WHERE id = NEW.player_id;
            RETURN NEW;
    END;
$$ LANGUAGE plpgsql;
   
CREATE TRIGGER increment_article_count
AFTER INSERT ON basketball_player_article
FOR EACH ROW EXECUTE FUNCTION update_article_count();
    