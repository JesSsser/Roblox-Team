pipeline {
	environment { 
        DOCKER_CREDENTIALS = credentials('dockerhub_id')
    }
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

        stage('Maven Clean') {
            steps {
                // Run the Maven clean command
                script {
                    sh 'mvn clean'
                }
            }
	}

        stage('Maven Compile') {
            steps {
                // Run the Maven clean command
                script {
                    sh 'mvn compile'
		}
            }	
        }

	stage('SonarQube Analysis') {
    	    steps {
               // Execute SonarQube analysis using Maven
	     	    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_TOKEN}"
		   }
    		}
	    }

	 stage('Nexus deploy') {
            steps {
                // Étape pour exécuter la commande "mvn deploy" avec l'option de "skip" des tests
  		 script {
               		 sh 'mvn deploy -DskipTests=true'
			}
                   }
               }

	stage('Building Docker image') {
	   steps {
                // Étape du build de l'image docker de l'application spring boot
		 script {
			// Generating image from Dockerfile
			  sh 'docker build -t jesssser/kaddem-0.0.1.jar .'
			}
		 }
	    }

	stage('Deploying Docker image') {
	   steps {
                // Étape du deployment de l'image docker de l'application spring boot
		 script {
                    // Log in to Docker registry using credentials
                           sh "docker login -u ${DOCKER_CREDENTIALS_USR} -p ${DOCKER_CREDENTIALS_PSW}"
                    
                    // Push Docker image
                           sh 'docker push jesssser/kaddem-0.0.1.jar'
                }
	   	 }
	     }
	
  	}
    }
