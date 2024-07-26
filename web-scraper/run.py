from concurrent.futures import ThreadPoolExecutor
from handlers.euroleague_player_consumer import EuroleaguePlayerConsumer
from handlers.euroleague_game_consumer import EuroleagueGameConsumer

if __name__ == "__main__":
    player_consumer = EuroleaguePlayerConsumer()
    game_consumer = EuroleagueGameConsumer()

    executor = ThreadPoolExecutor(max_workers=2)

    executor.submit(player_consumer.consume)
    executor.submit(game_consumer.consume)

    executor.shutdown(wait=True)

