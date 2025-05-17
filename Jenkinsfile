pipeline {
    agent any

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = 'your.ec2.public.ip'
        EC2_KEY = credentials('ec2-ssh-key') // Jenkins credential ID for PEM key
        
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-repo/airline-management.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests=false'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                // Copy Docker image or JAR to EC2 (choose one method below)

                // Option A: SSH into EC2 and run the Docker container
                sh """
                ssh -i $EC2_KEY $EC2_USER@$EC2_HOST << EOF
                    docker pull $DOCKER_IMAGE
                    docker stop airline-app || true && docker rm airline-app || true
                    docker run -d -p 8080:8080 --name airline-app $DOCKER_IMAGE
                EOF
                """
            }
        }
    }

    post {
        failure {
            echo '❌ Build failed!'
        }
        success {
            echo '✅ Build and deployment successful!'
        }
    }
}
