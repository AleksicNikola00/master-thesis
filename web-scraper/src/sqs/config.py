import boto3
import os

# Set your LocalStack endpoint URL
os.environ['AWS_ACCESS_KEY_ID'] = 'test'
os.environ['AWS_SECRET_ACCESS_KEY'] = 'test'
os.environ['AWS_DEFAULT_REGION'] = 'us-east-1'

sqs = boto3.client('sqs', endpoint_url='http://localhost.localstack.cloud:4566')
