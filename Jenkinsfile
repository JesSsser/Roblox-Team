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
        stage('Start Monitoring Services') {
            steps {
                script {
                    // Start Prometheus and Grafana with Docker Compose
                    sh 'docker-compose -f docker-compose.yml up -d prometheus grafana'
                }
            }
        }
         stage('JUNIT/JACOCO') {
    steps {
        script {
            // Set up Maven
            def mvnHome = tool 'Maven'
            // Run tests with Maven, this will also generate the JaCoCo reports
            sh "${mvnHome}/bin/mvn clean test"
        }
    }
    post {
        always {
            // Publish JUnit test results
            junit '**/target/surefire-reports/TEST-*.xml'
            
            // Publish JaCoCo test coverage report
            jacoco(
                execPattern: '**/**.exec', // Path to JaCoCo exec files
                classPattern: '**/classes', // Path to class files
                sourcePattern: '**/src/main/java' // Path to source files
            )
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
         
      stage('NEXUS') {
            steps {
  		        script {
          		        sh 'mvn deploy -DskipTests=true'
			}
          }
       
       }
         stage('DOCKER BUILD') {
        steps {
            script {
               
                // Le point (.) indique que le contexte de build est le répertoire courant
                sh 'docker build -t khardeni/kaddem-app:0.0.1 .'
            }
        }
         }
            stage('DOCKER PUSH') {
        steps {
            script {
               
        
                 // Using withCredentials to securely inject the Docker Hub token
            withCredentials([string(credentialsId: 'dock_token', variable: 'DOCKERHUB_TOKEN')]) {
                // Logging into Docker Hub using the token
                sh 'echo $DOCKERHUB_TOKEN | docker login -u khardeni --password-stdin'
            }
                    
                    // Push the image to Docker Hub
                    sh 'docker push khardeni/kaddem-app:0.0.1'
                }
            }
                
                }
        

        // Your existing stages like MAVEN, JUNIT/JACOCO, SonarQube Analysis, etc.

        stage('Docker Compose BUILD/RUN') {
            steps {
                script {
                    // Start your application and database with Docker Compose
                    sh 'docker-compose -f docker-compose.yml up -d app db'
                }
            }
        }

        // Any other stages you might have
    }


        // Add other stages as needed
    }

  /*  post {
        always {
            script {
                // This ensures that the Docker Compose services are always taken down after the build finishes
                sh 'docker-compose down'
            }
        }
    } */

 
       
  
