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
                     withCredentials([usernamePassword(credentialsId: 'sonar-credentials', passwordVariable: 'SONAR_PASSWORD', usernameVariable:     				'SONAR_USERNAME')]) {
                     sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_USERNAME}:${env.SONAR_PASSWORD}"
                   }
    		}
	    }
  	}
    }
