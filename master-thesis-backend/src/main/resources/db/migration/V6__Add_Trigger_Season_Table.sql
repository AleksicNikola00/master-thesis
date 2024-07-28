-- Create a function to update games_count in basketball_season
CREATE OR REPLACE FUNCTION update_games_count()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE basketball_season
    SET games_count = games_count + 1
    WHERE id = NEW.season_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger that calls the function on insert
CREATE TRIGGER increment_games_count
AFTER INSERT ON basketball_game
FOR EACH ROW EXECUTE FUNCTION update_games_count();