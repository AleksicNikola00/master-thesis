import boto3
from dotenv import load_dotenv
import os


load_dotenv(os.path.join(os.path.dirname(os.path.abspath(__file__)), '../../..', '.env'))

# Set your LocalStack endpoint URL
os.environ['AWS_ACCESS_KEY_ID'] = os.getenv('LOCALSTACK_ACCESS_KEY')
os.environ['AWS_SECRET_ACCESS_KEY'] = os.getenv('LOCALSTACK_SECRET_KEY')
os.environ['AWS_DEFAULT_REGION'] = os.getenv('LOCALSTACK_REGION')

LOCALSTACK_URL = os.getenv('LOCALSTACK_URL')

sqs = boto3.client('sqs', endpoint_url=LOCALSTACK_URL)
