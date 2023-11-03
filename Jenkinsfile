pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checks out the SCM project from the specific branch.
                // The branch name should be replaced with your actual branch name.
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/Amine']], userRemoteConfigs: [[url: 'https://github.com/JesSsser/Roblox-Team.git']]]
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
	stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    // You may want to add additional Maven properties or SonarQube options depending on your setup
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests --settings settings.xml'
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
