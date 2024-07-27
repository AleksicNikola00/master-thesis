from flask import Flask, jsonify
from src.sqs.config import sqs

app = Flask(__name__)


def startup_app():
    app.run(host='0.0.0.0', port=5000)


@app.route('/health', methods=['GET'])
def health_check():
    try:
        response = sqs.list_queues()
        if response['ResponseMetadata']['HTTPStatusCode'] == 200:
            return jsonify(status="healthy"), 200
        else:
            return jsonify(status="unhealthy"), 500
    except Exception as e:
        return jsonify(status="unhealthy", error=str(e)), 500


@app.route('/health/<queue_name>', methods=['GET'])
def check_queue_health(queue_name):
    try:
        sqs.get_queue_url(QueueName=queue_name)
        return jsonify(status="healthy", queue_name=queue_name), 200
    except sqs.exceptions.QueueDoesNotExist:
        return jsonify(status="unhealthy", queue_name=queue_name, error="Queue does not exist"), 404
    except Exception as e:
        return jsonify(status="unhealthy", queue_name=queue_name, error=str(e)), 500
