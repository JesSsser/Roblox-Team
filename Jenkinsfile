pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checks out the SCM project from the specific branch.
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/Amine']], userRemoteConfigs: [[url: 'https://github.com/JesSsser/Roblox-Team.git']]]
            }
        }
        stage('Maven Clean Compile') {
            steps {
                sh 'mvn clean'
	    	sh 'mvn compile'
            }
        }
	/* stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }*/
        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    	stage('Build Docker Image Back-End et push sur DockerHub') {
    steps {
        script {
            def DOCKERHUB_USERNAME= "aminemosbeh"
            def DOCKERHUB_TOKEN= "dckr_pat_GM1LZjJvmfq3hETClQAoBRxRFO8"
            def imageName = "'${DOCKERHUB_USERNAME}'/devops-project-2.1-back-end:2.0.0"
            def dockerfile = 'Dockerfile'

            // Se connecter à Docker Hub
            sh "docker login -u '${DOCKERHUB_USERNAME}' -p '${DOCKERHUB_TOKEN}'"

            // Build de l'image Docker
            sh "docker build -t '$imageName' -f '$dockerfile' ."
		
            sh "docker login -u '${DOCKERHUB_USERNAME}' -p '${DOCKERHUB_TOKEN}'"

            sh "docker push '$imageName'"

           
        }
     }
    }
		stage('Docker compose') {
            	steps {
                sh 'docker-compose -f docker-compose.yml up -d'
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
