from config import sqs


def read_messages(que_url, process_callback):
    # process_callback(que_url)
    response = sqs.receive_message(
        QueueUrl=que_url,
        MaxNumberOfMessages=1,
        WaitTimeSeconds=10  # Long polling
    )

    messages = response.get('Messages', [])

    if not messages:
        print('No messages received from que {} . Waiting for messages...'.format(que_url))
        return

    for message in messages:
        print('Received message:', message['Body'])
        # Perform a trivial task (e.g., just echo the message)
        process_callback(message)
        # # Delete the message from the input queue
        delete_message(que_url, message['ReceiptHandle'])


def send_message(queue_url, message):
    response = sqs.send_message(
        QueueUrl=queue_url,
        MessageBody=message
    )
    return response['MessageId']


def delete_message(queue_url, receipt_handle):
    sqs.delete_message(
        QueueUrl=queue_url,
        ReceiptHandle=receipt_handle
    )
    print('Deleted message:', receipt_handle)
