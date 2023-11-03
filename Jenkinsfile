pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checks out the SCM project from the specific branch.
                // The branch name should be replaced with your actual branch name.
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/your-branch-name']], userRemoteConfigs: [[url: 'https://github.com/your-username/your-repo.git']]]
            }
        }
        
        stage('Clean') {
            steps {
                // Calls Maven to perform a clean.
                sh 'mvn clean'
            }
        }
        
        stage('Compile') {
            steps {
                // Calls Maven to compile the project.
                sh 'mvn compile'
            }
        }


    }

    post {
        // Define post-build actions here, such as notifications, etc.
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
