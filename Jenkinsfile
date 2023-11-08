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

	stage('Prometheus And Grafana'){ 
		steps {
           
        sh "docker start prometheus"
	sh "docker start grafana"
		}
	}

     
	
	

    
	
	
	    
  	}
	post {  
         always {  
             echo 'This will always run'  
         }  
         success {  
             mail bcc: '', body: "<b>sucesss</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Work is done 🟢: Project name -> ${env.JOB_NAME}", to: "mouhibbengayes7@gmail.com";  
         }  
         failure {  
             mail bcc: '', body: "<b>failure</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "ERROR 🔴: Project name -> ${env.JOB_NAME}", to: "mouhibbengayes7@gmail.com";  
         }  
         
          
     }
	
}	
