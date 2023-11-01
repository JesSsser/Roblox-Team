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
			   docker build -t kaddem-0.0.1.jar .
			}
		 }
	    }
	
  	}
    }
