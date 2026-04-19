pipeline {
    agent any

    parameters {
        string(name: 'IMAGE_TAG', defaultValue: 'latest', description: 'Docker image tag to deploy')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'stage', 'prod'], description: 'Target environment')
    }

    environment {
        CONTAINER_NAME = 'allmylinks'
        IMAGE_REPOSITORY = 'ghcr.io/aabarmin/allmylinks.co.uk'
    }

    stages {
        stage('Pull Image') {
            steps {
                sh """
                    podman pull ${IMAGE_REPOSITORY}:${params.IMAGE_TAG}
                """
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh """
                    if podman container exists ${CONTAINER_NAME}; then
                      podman stop ${CONTAINER_NAME}
                    fi
                """
            }
        }

        stage('Replace Container') {
            steps {
                sh """
                    if podman container exists ${CONTAINER_NAME}; then
                      podman rm ${CONTAINER_NAME}
                    fi

                    podman run -d --name ${CONTAINER_NAME} ${IMAGE_REPOSITORY}:${params.IMAGE_TAG}
                """
            }
        }
    }
}
