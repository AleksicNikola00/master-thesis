import json

from src.sqs.config import sqs


def handle_message(que_url, process_callback):
    response = sqs.receive_message(
        QueueUrl=que_url,
        MaxNumberOfMessages=1,
        WaitTimeSeconds=5
    )

    messages = response.get('Messages', [])

    if not messages:
        print('No messages received from que {} . Waiting for messages...'.format(que_url))
        return

    for message in messages:
        print('Received message:', message)

        message_content = json.loads(message['Body'])
        try:
            process_callback(message_content)
        except Exception as e:
            print("Error occurred while processing message [{}] on que[{}] : {}".format(message_content, que_url, e))
            raise e

        delete_message(que_url, message['ReceiptHandle'])


def send_message(queue_url, message):
    response = sqs.send_message(
        QueueUrl=queue_url,
        MessageBody=json.dumps(message)
    )
    return response['MessageId']


def delete_message(queue_url, receipt_handle):
    sqs.delete_message(
        QueueUrl=queue_url,
        ReceiptHandle=receipt_handle
    )
    print('Deleted message:', receipt_handle)
