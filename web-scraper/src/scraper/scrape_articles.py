from enum import Enum

import newspaper
import requests
from bs4 import BeautifulSoup


class ScrapedSites(Enum):
    EURO_HOOPS = 1
    WIKIPEDIA = 2


sitesUrlMap = {
    ScrapedSites.EURO_HOOPS: "https://www.eurohoops.net/index.php?s={playerFirstName}+{playerLastName}&lang=en",
    ScrapedSites.WIKIPEDIA: "https://en.wikipedia.org/w/index.php?search={playerFirstName}+{"
                            "playerLastName}+euroleague+basketball&title=Special:Search&profile=advanced&fulltext=1"
                            "&ns0=1"
}


def get_search_url(firstname, lastname, site=ScrapedSites.EURO_HOOPS):
    site_url = sitesUrlMap[site]
    return site_url.format(playerFirstName=firstname, playerLastName=lastname)


def scrape_articles(firstname, lastname, articleNumber=5):
    response = requests.get("https://www.eurohoops.net/index.php?s=vasilije+micic&lang=en")
    if response.status_code != 200:
        print(f"Failed to retrieve the page: {response.status_code}")
        return

    # Parse the HTML content
    soup = BeautifulSoup(response.text, 'html.parser')

    # Find all <article> tags
    articles = soup.find_all('article')

    # Extract URLs from <a> tags within <article> tags
    article_links = []
    for article in articles:
        for a_tag in article.find_all('a', href=True):
            link = a_tag['href']
            # Convert relative link to absolute URL
            article_links.append(link)

    return article_links


def get_article_content(url):
    article = newspaper.article(
        url)
    article.download()
    article.parse()
    print(article.text)
    print(article.top_image)
    print(article.authors)
