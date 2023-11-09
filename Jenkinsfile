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
    	stage('Building Docker image') {
        	   steps {
        		 script {
        			// Generating image from Dockerfile
        			  sh 'docker build -t aminemosbeh/devopsproject-0.0.1.jar .'
        			}
        		 }
        	    }
        stage('Push Docker Image') {
            steps {


                // Log in to Docker Hub with your credentials
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                }

                // Push the Docker image to Docker Hub
                sh "docker push aminemosbeh/devopsproject-0.0.1.jar"
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
