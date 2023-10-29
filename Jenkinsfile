pipeline {
    agent any

    stages {
        stage('Checkout Git') {
            steps {
                // Checkout the code from your Git repository
                script {
                    checkout scm
                }
            }
        }

        stage('Maven Build') {
            steps {
                // Run the Maven clean and compile commands
                script {
                    sh 'mvn clean compile'
                }
            }
        }
    }
}
