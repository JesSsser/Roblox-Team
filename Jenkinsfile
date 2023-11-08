pipeline {
	environment { 
	GRAFANA_URL = 'http://192.168.33.10:3000'
        PROMETHEUS_URL = 'http://192.168.33.10:9090'
   		 }

    def DEFAULT_SUBJECT = "Your default subject"
    def DEFAULT_CONTENT = "Your default content"
    def PROJECT_NAME = env.JOB_NAME
    def BUILD_NUMBER = env.BUILD_NUMBER
    def BUILD_STATUS = currentBuild.resultIsBetterOrEqualTo('FAILURE') ? 'FAILURE' : 'SUCCESS'
    def CAUSE = currentBuild.causes.collect { it.shortDescription }.join(', ')
    def BUILD_URL = env.BUILD_URL
    def FAILED_TESTS = "Information about failed tests"
    def CHANGES = "Changes made since last build"
    def CHANGES_SINCE_LAST_SUCCESS = "Changes made since last successful build"

	
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
                    
                    if (currentBuild.resultIsBetterOrEqualTo('FAILURE')) {
                        emailSubject = "Pipeline Failed: ${DEFAULT_SUBJECT}"
                        emailBody = """
                            <p>Le sujet par défaut configuré dans la page de configuration Jenkins: ${DEFAULT_SUBJECT}</p>
                            <p>Le corps du message par défaut configuré dans la page de configuration Jenkins: ${DEFAULT_CONTENT}</p>
                            <p>Le nom du projet: ${PROJECT_NAME}</p>
                            <p>Le numéro de build courant: ${BUILD_NUMBER}</p>
                            <p>Le statut du build courant (échec, succès, etc.): ${BUILD_STATUS}</p>
                            <p>La cause du build: ${CAUSE}</p>
                            <p>Un lien vers la page correspondante du build sur Jenkins: ${BUILD_URL}</p>
                            <p>Information sur les tests unitaires échoués, si certains ont échoué: ${FAILED_TESTS}</p>
                            <p>Changements effectués depuis le dernier build: ${CHANGES}</p>
                            <p>Tous les changements effectués depuis le dernier build avec succès: ${CHANGES_SINCE_LAST_SUCCESS}</p>
                        """
                    } else {
                        emailSubject = "Pipeline Successful: ${DEFAULT_SUBJECT}"
                        emailBody = """
                            <p>Le sujet par défaut configuré dans la page de configuration Jenkins: ${DEFAULT_SUBJECT}</p>
                            <p>Le corps du message par défaut configuré dans la page de configuration Jenkins: ${DEFAULT_CONTENT}</p>
                            <p>Le nom du projet: ${PROJECT_NAME}</p>
                            <p>Le numéro de build courant: ${BUILD_NUMBER}</p>
                            <p>Le statut du build courant (échec, succès, etc.): ${BUILD_STATUS}</p>
                            <p>La cause du build: ${CAUSE}</p>
                            <p>Un lien vers la page correspondante du build sur Jenkins: ${BUILD_URL}</p>
                            <p>Information sur les tests unitaires échoués, si certains ont échoué: ${FAILED_TESTS}</p>
                            <p>Changements effectués depuis le dernier build: ${CHANGES}</p>
                            <p>Tous les changements effectués depuis le dernier build avec succès: ${CHANGES_SINCE_LAST_SUCCESS}</p>
                        """
                    }
                    
                    emailext(
                        subject: emailSubject,
                        body: emailBody,
                        to: 'mouhibbengayes7@gmail.com', // Replace with the recipient's email address
                        replyTo: 'noreply@example.com',
                        mimeType: 'text/html'
                    )
                }
            }
        }

    
	
	
	    
  	}
    }
