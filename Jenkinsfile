pipeline {

    agent any

    stages {
        stage('GIT') {
            steps {
                // Checkout the code from your Git repository
                script {
                    checkout scm
                }
            }
        }

        stage('MAVEN') {
            steps {
                // Run the Maven clean command
                script {
                    sh 'mvn clean compile'
		}
            }
        }
        }
        }
