
# Function to read environment variables from a .env file
function Get-EnvVariable {
    param (
        [string]$VarName
    )

    $FilePath = ".env"
    if (-Not (Test-Path $FilePath)) {
        throw "The .env file does not exist at path $FilePath"
    }

    $envVars = Get-Content $FilePath | Where-Object { $_ -match "^\s*[^#]" }
    foreach ($line in $envVars) {
        if ($line -match "^\s*$VarName\s*=\s*(.*)$") {
            return $matches[1].Trim()
        }
    }
    throw "The environment variable $VarName was not found in the .env file."
}

# Function to create an SQS queue using the awslocal command
function Create-SQSQueue {
    param (
        [string]$EnvVarName
    )

    # Read the queue name from the .env file
    $queueName = Get-EnvVariable -VarName $EnvVarName

    # Execute the awslocal command and capture the output
    try {
        $output = Invoke-Expression "awslocal sqs create-queue --queue-name $queueName" 2>&1
        $status = $LASTEXITCODE
        if ($status -eq 0) {
            Write-Host "Success: Queue '$queueName' created successfully."
            Write-Host "Output: $output"
        } else {
            Write-Host "Error: Failed to create queue '$queueName'."
            Write-Host "Output: $output"
        }
    } catch {
        Write-Host "Exception: An error occurred while creating queue '$queueName'."
        Write-Host "Exception: $_"
    }
}

Create-SQSQueue -EnvVarName "LOCALSTACK_SQS_EUROLEAGUE_PLAYER_IMAGE_REQUEST"
Create-SQSQueue -EnvVarName "LOCALSTACK_SQS_EUROLEAGUE_PLAYER_IMAGE_RESPONSE"
Create-SQSQueue -EnvVarName "LOCALSTACK_SQS_EUROLEAGUE_PLAYER_ARTICLE_REQUEST"
Create-SQSQueue -EnvVarName "LOCALSTACK_SQS_EUROLEAGUE_PLAYER_ARTICLE_RESPONSE"
