from time import sleep
from src.handlers.base_handler import BaseConsumer

QUEUE_URL = "https://localhost.localstack.cloud:4566/000000000000/localstack_euroleague_players_response"


class EuroleagueGameConsumer(BaseConsumer):
    def process(self, message):
        print("game " + message)
        sleep(5)

    def __init__(self):
        super().__init__(QUEUE_URL)
