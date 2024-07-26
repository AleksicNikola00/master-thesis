from time import sleep
from handlers.base_handler import BaseConsumer

QUEUE_URL = "https://localhost.localstack.cloud:4566/000000000000/localstack_euroleague_players_request"


class EuroleaguePlayerConsumer(BaseConsumer):
    def process(self, message):
        print("player " + message)
        sleep(5)

    def __init__(self):
        super().__init__(QUEUE_URL)
