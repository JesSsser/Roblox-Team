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
        stage('SonarQube Analysis') {
            	    steps {
                       // Execute SonarQube analysis using Maven
        	     	    withCredentials([string(credentialsId: 'sona-token', variable: 'SONAR_TOKEN')]) {
                            sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_TOKEN}"
        		   }
            		}
        	    }
    stage('NEXUS') {
                steps {
      		        script {
              		        sh 'mvn deploy -DskipTests=true'
    			}
              }

           }
        }
        }
