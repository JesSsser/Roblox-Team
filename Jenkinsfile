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
	     	script {
                    sh 'mvn sonar:sonar'
		   }
    		}
	    }
        stage('Deploy to Nexus') {
            steps {
               script {
                    sh 'mvn deploy'
                }
            }
        }
    }
    
  	}

