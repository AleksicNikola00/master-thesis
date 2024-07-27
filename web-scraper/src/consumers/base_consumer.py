from abc import ABC, abstractmethod
from time import sleep

from src.sqs.sqs_service import read_messages


class BaseConsumer(ABC):
    def __init__(self, queue_url):
        self.queue_url = queue_url

    @abstractmethod
    def process(self, message):
        pass

    def consume(self):
        while True:
            try:
                read_messages(self.queue_url, self.process)
            except Exception as e:
                print("An exception occurred while reading from the que [{}]: {}".format(self.queue_url, e))
                sleep(60)
