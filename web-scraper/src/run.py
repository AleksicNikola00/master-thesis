from concurrent.futures import ThreadPoolExecutor

from src.consumers.euroleague_player_articles_consumer import EuroleaguePlayerArticlesConsumer
from src.consumers.euroleague_player_image_consumer import EuroleaguePlayerImageConsumer
from src.flask_init import startup_app

if __name__ == "__main__":
    player_image_consumer = EuroleaguePlayerImageConsumer()
    player_article_consumer = EuroleaguePlayerArticlesConsumer()

    executor = ThreadPoolExecutor(max_workers=3)

    executor.submit(player_image_consumer.consume)
    executor.submit(player_article_consumer.consume)
    executor.submit(startup_app)

    executor.shutdown(wait=True)
