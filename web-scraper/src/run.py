from concurrent.futures import ThreadPoolExecutor
from consumers.euroleague_player_consumer import EuroleaguePlayerConsumer
from src.flask_init import startup_app

if __name__ == "__main__":
    player_consumer = EuroleaguePlayerConsumer()

    executor = ThreadPoolExecutor(max_workers=2)

    executor.submit(player_consumer.consume)
    executor.submit(startup_app)

    executor.shutdown(wait=True)
