pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-creds') // ID des credentials Jenkins
        DOCKER_IMAGE = "braiki89/eventconnect" // Nom de l'image et repo Docker Hub
        DOCKER_TAG = "latest"
    }

    stages {
        stage('Récupération du code source') {
            steps {
                git branch: 'main', url: 'https://github.com/braiki89-aymen/Aymen-braiki-EventConnect-Lot1-Back.git'
            }
        }

       stage('Build Maven') {
            steps {
                echo "📦 Build Maven"
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Construction de l’image Docker') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Connexion à Docker Hub') {
            steps {
                script {
                    sh "echo $DOCKER_HUB_CREDENTIALS_PSW | docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin"
                }
            }
        }

        stage('Push de l’image Docker') {
            steps {
                script {
                    sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        success {
            echo "✅ Build et Push terminés avec succès"
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo "❌ Erreur dans le pipeline"
        }
    }
}
