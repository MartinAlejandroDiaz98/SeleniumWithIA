pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/tu-repo.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dbrowser=chrome -Dheadless=true'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
    }
}
