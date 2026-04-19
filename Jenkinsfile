pipeline {
    agent any

    parameters {
        string(name: 'IMAGE_TAG', defaultValue: 'latest', description: 'Docker image tag to deploy')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'stage', 'prod'], description: 'Target environment')
    }

    stages {
        stage('Show Parameters') {
            steps {
                echo "IMAGE_TAG=${params.IMAGE_TAG}"
                echo "ENVIRONMENT=${params.ENVIRONMENT}"
            }
        }
    }
}
