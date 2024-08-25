import os

from src.consumers.base_consumer import BaseConsumer
from src.scraper.scrape_articles import scrape_articles
from src.sqs.config import LOCALSTACK_URL

READ_QUEUE_NAME = os.getenv('LOCALSTACK_SQS_EUROLEAGUE_PLAYER_ARTICLE_REQUEST')
READ_QUEUE_URL = LOCALSTACK_URL + "/000000000000/" + READ_QUEUE_NAME


class EuroleaguePlayerArticlesConsumer(BaseConsumer):
    def __init__(self):
        super().__init__(READ_QUEUE_URL)

    def process(self, message_content):
        first_name = message_content['firstName'].lower().strip()
        last_name = message_content['lastName'].lower().strip()
        player_id = message_content['id']
        scrape_articles(player_id, first_name, last_name)
