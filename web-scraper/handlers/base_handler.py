from abc import ABC, abstractmethod
from sqs_service import read_messages


class BaseConsumer(ABC):
    def __init__(self, queue_url):
        self.queue_url = queue_url

    @abstractmethod
    def process(self, message):
        pass

    def consume(self):
        while True:
            read_messages(self.queue_url, self.process)
