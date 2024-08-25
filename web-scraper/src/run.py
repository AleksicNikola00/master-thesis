from src.scraper.scrape_articles import scrape_articles

if __name__ == "__main__":
    # player_consumer = EuroleaguePlayerConsumer()
    #
    # executor = ThreadPoolExecutor(max_workers=3)
    #
    # executor.submit(player_consumer.consume)
    # executor.submit(scrape_articles)
    # executor.submit(startup_app)
    #
    # executor.shutdown(wait=True)
    scrape_articles()
