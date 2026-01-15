pipeline {
    agent any

    tools {
        maven 'Maven3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/valeria-llerena/AmazonAutom.git'
            }
        }

        stage('Build & Test') {
            steps {
                // En Windows usa 'bat', en Linux/Mac usa 'sh'
                bat 'mvn clean verify'
            }
        }

        stage('Serenity Report') {
            steps {
                // Esto generará el reporte de Serenity si tienes el plugin instalado
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/serenity',
                    reportFiles: 'index.html',
                    reportName: 'Serenity Report'
                ])
            }
        }
    }
}