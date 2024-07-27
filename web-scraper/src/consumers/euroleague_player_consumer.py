import os
from time import sleep
from src.consumers.base_consumer import BaseConsumer
from src.sqs.config import LOCALSTACK_URL

QUEUE_NAME = os.getenv('LOCALSTACK_SQS_EUROLEAGUE_PLAYER')
QUEUE_URL = LOCALSTACK_URL + "/000000000000/" + QUEUE_NAME


class EuroleaguePlayerConsumer(BaseConsumer):
    def process(self, message):
        print("player " + message)
        sleep(5)

    def __init__(self):
        super().__init__(QUEUE_URL)
