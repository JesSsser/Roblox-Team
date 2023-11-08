pipeline {
	environment { 
	GRAFANA_URL = 'http://192.168.33.10:3000'
        PROMETHEUS_URL = 'http://192.168.33.10:9090'
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

        stage('MVN Clean') {
            steps {
                script {
                    sh 'mvn clean'
                }
            }
	}

        stage('MVN Build') {
            steps {
                script {
                    sh 'mvn compile'
		}
            }	
        }
	/*   
	stage('Tests') {
            steps {
                sh 'mvn test'
                // bat '.\\mvnw test'
            }

        } 

	stage('Jacoco') {
    	    steps {
          	  	junit 'target/surefire-reports/*.xml'
          		jacoco()
		   }
        }


	stage('SonarQube') {
    	    steps {
               // Execute SonarQube analysis using Maven
	     	    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh "mvn sonar:sonar -Dsonar.login=${env.SONAR_TOKEN}"
		   }
    		}
	    } 
	    
	
	stage('Nexus') {
            steps {
  		 script {
               		 sh 'mvn deploy -DskipTests=true'
			}
                   }
               } 

	stage('Docker image') {
	   steps {
		 script {
			  sh 'docker build -t mouhibbg/kaddem-0.0.1.jar .'
			}
		 }
	    }

	stage('Push Docker Image') {
    		steps {
        		script {
			            def dockerUsername = 'mouhibbg'
			            def dockerPassword = 'mouhib147'
			            sh "docker login -u $dockerUsername -p $dockerPassword" 
			            sh 'docker push mouhibbg/kaddem-0.0.1.jar'
            }
        }
    }


	stage('Docker compose') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            			}
       			 } 

     */
	stage('Email Notifications') {
          steps {
        	script {
            def emailSubject
            def emailBody

            def failureCause = currentBuild.rawBuild.failure?.getStackTrace()?.toString()

            if (currentBuild.resultIsBetterOrEqualTo('FAILURE')) {
                emailSubject = "Pipeline Failed: ${currentBuild.fullDisplayName}"
                emailBody = """<p>Dear Team,</p>
                               <p>The Jenkins pipeline ${currentBuild.fullDisplayName} has failed.</p>
                               <p>Failure Cause:</p>
                               <pre>${failureCause}</pre>
                               <ul>
                                   <li><strong>Project/Module:</strong> ${JOB_NAME}</li>
                                   <li><strong>Build Number:</strong> ${BUILD_NUMBER}</li>
                                   <li><strong>Stage Name:</strong> Docker compose</li>
                               </ul>
                               <p>Best regards,<br>Your Jenkins Server</p>
                            """
            } else {
                emailSubject = "Pipeline Successful: ${currentBuild.fullDisplayName}"
                emailBody = """<p>Dear Team,</p>
                               <p>The Jenkins pipeline ${currentBuild.fullDisplayName} has completed successfully.</p>
                               <ul>
                                   <li><strong>Project/Module:</strong> ${JOB_NAME}</li>
                                   <li><strong>Build Number:</strong> ${BUILD_NUMBER}</li>
                                   <li><strong>Stage Name:</strong> Docker compose</li>
                               </ul>
                               <p>Best regards,<br>Your Jenkins Server</p>
                            """
            }

            emailext(
                subject: emailSubject,
                body: emailBody,
                to: 'mouhibbengayes7@gmail.com',
                replyTo: 'noreply@example.com',
                mimeType: 'text/html'
            )
        }
    }
}


    
	
	
	    
  	}
    }
