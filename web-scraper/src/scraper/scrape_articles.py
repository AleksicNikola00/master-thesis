import os
import re
import urllib.parse
from enum import Enum

import newspaper
import requests
from bs4 import BeautifulSoup

from src.sqs.config import LOCALSTACK_URL
from src.sqs.sqs_service import send_message

WRITE_QUEUE_NAME = os.getenv('LOCALSTACK_SQS_EUROLEAGUE_PLAYER_ARTICLE_RESPONSE')
WRITE_QUEUE_URL = LOCALSTACK_URL + "/000000000000/" + WRITE_QUEUE_NAME


class ScrapedSites(Enum):
    EURO_HOOPS = 1
    WIKIPEDIA = 2


sitesUrlMap = {
    ScrapedSites.EURO_HOOPS: "https://www.eurohoops.net/index.php?s={playerFirstName}+{playerLastName}&lang=en",
    ScrapedSites.WIKIPEDIA: "https://en.wikipedia.org/w/index.php?search={playerFirstName}+{"
                            "playerLastName}+euroleague+basketball"
}


def scrape_articles(player_id, first_name, last_name, article_number=5):
    print("Scraping [{}] articles  for player [{}] with id [{}]".format(article_number,
                                                                        first_name + " " + last_name, player_id))
    scraped_wiki_articles = scrape_wikipedia_article(player_id, first_name, last_name)
    article_number -= scraped_wiki_articles
    scrape_newspaper_articles(player_id, first_name, last_name, article_number)
    print("Ended scraping articles  for player [{}] with id [{}]".format(
        first_name + " " + last_name, player_id))


def scrape_wikipedia_article(player_id, first_name, last_name):
    """Scrapes wikipedia article and sends it to SQS

        Returns
        -------
        number
            a number of scraped and sent articles
        """
    try:
        wikipedia_url = get_search_url(first_name, last_name, site=ScrapedSites.WIKIPEDIA)
        wikipedia_response = requests.get(wikipedia_url)

        soup = BeautifulSoup(wikipedia_response.text, 'html.parser')

        search_result_container = soup.find('div', class_='mw-search-results-container')

        first_result = search_result_container.find('ul').find('li').find('a')
        relative_url = first_result['href']
        absolute_url = urllib.parse.urljoin(wikipedia_url, relative_url)

        article = get_article_content(absolute_url)

        # Clean parsed article content
        content = article.text
        # Remove meta tags
        cleaned_content = re.sub(r'\[.*?]', '', content)

        # Remove statistics tables
        match = re.search(r'career statistics', cleaned_content, re.IGNORECASE)

        if match:
            cleaned_content = cleaned_content[:match.start()]

        # Convert multiple new lines to single new line
        cleaned_content = re.sub(r'\n+', '\n', cleaned_content)

        send_article_to_sqs(player_id, cleaned_content, absolute_url)
        print("Successfully scraped and sent wikipedia article about [{}]".format(first_name + " " + last_name))
        return 1
    except Exception as e:
        print("An error occurred while parsing wikipedia article about [{}]: {}".format(first_name, last_name), e)
        return 0


def scrape_newspaper_articles(player_id, first_name, last_name, article_number):
    try:
        search_url = get_search_url(first_name, last_name)
        response = requests.get(search_url)

        soup = BeautifulSoup(response.text, 'html.parser')

        articles = soup.find_all('article')

        for article_element, _ in zip(articles, range(article_number)):
            a_tag = article_element.find('a', href=True)
            link = a_tag['href']
            article = get_article_content(link)
            send_article_to_sqs(player_id, article.text, link)

        print("Successfully scraped and sent EuroHoops articles about [{}]".format(first_name + " " + last_name))
    except Exception as e:
        print("An error occurred while parsing EuroHoops articles about [{}]: {}".format(first_name, last_name), e)


def send_article_to_sqs(player_id, article_content, article_url):
    player_article_event = {
        'player_id': player_id,
        'article_content': article_content,
        'article_url': article_url
    }
    send_message(WRITE_QUEUE_URL, player_article_event)


def get_search_url(first_name, last_name, site=ScrapedSites.EURO_HOOPS):
    site_url = sitesUrlMap[site]
    return site_url.format(playerFirstName=first_name, playerLastName=last_name)


def get_article_content(url):
    article = newspaper.article(
        url)
    article.download()
    article.parse()
    return article
