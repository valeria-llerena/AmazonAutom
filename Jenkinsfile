pipeline {
    agent any
    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/valeria-llerena/AmazonAutom'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean verify'
            }
        }

        stage('Serenity Report') {
            steps {
                publishHTML([
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: 'Serenity Report'
                ])
            }
        }
    }
}
