import os

from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait

from src.consumers.base_consumer import BaseConsumer
from src.scraper.config import driver
from src.sqs.config import LOCALSTACK_URL
from src.sqs.sqs_service import send_message

READ_QUEUE_NAME = os.getenv('LOCALSTACK_SQS_EUROLEAGUE_PLAYER_REQUEST')
WRITE_QUEUE_NAME = os.getenv('LOCALSTACK_SQS_EUROLEAGUE_PLAYER_RESPONSE')

READ_QUEUE_URL = LOCALSTACK_URL + "/000000000000/" + READ_QUEUE_NAME
WRITE_QUEUE_URL = LOCALSTACK_URL + "/000000000000/" + WRITE_QUEUE_NAME

EUROLEAGUE_SITE_URL = "https://www.euroleaguebasketball.net/euroleague/players"


def generate_url(message):
    first_name = message['firstName'].lower().strip()
    last_name = message['lastName'].lower().strip()
    euroleague_id = message['euroleagueId'].strip()[1:]
    return EUROLEAGUE_SITE_URL + "/" + first_name + "-" + last_name + "/" + euroleague_id


class EuroleaguePlayerConsumer(BaseConsumer):
    def __init__(self):
        super().__init__(READ_QUEUE_URL)

    def process(self, message):
        print("Started processing message {}".format(message))
        try:
            url = generate_url(message)

            driver.get(url)
            img_element = WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, 'img.player-hero_image__mJ4gW'))
            )
            data_srcset = img_element.get_attribute('data-srcset')
            image_url = data_srcset.split('?')[0]

            player_image_event = {
                'id': message['id'],
                'imageUrl': image_url,
            }
            send_message(WRITE_QUEUE_URL, player_image_event)
        except Exception as e:
            print("Error occurred while processing message [{}]: {}".format(message, e))
            raise e
        # finally:
        #     driver.quit()
