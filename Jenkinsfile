pipeline {
    agent any

   
    stages {
        stage('Récupération du code source') {
            steps {
                git branch: 'main', url: 'https://github.com/braiki89-aymen/Aymen-braiki-EventConnect-Lot1-Back.git'
            }
        }

        stage('Construction du livrable') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }

    post {
        success {
            echo "✅ Build terminé avec succès"
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo "❌ Erreur dans le pipeline"
        }
    }
}
