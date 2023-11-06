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
        
         stage('Junit/Mockito') {
            steps {
                sh 'mvn test'
                // bat '.\\mvnw test'
            }
         

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
            stage('Build & Test with JaCoCo') {
                steps {
        // Exécuter Maven avec le goal 'test' qui déclenche JaCoCo
                     script {
                         
                           def mvnHome = tool 'Maven' 
                                sh "${mvnHome}/bin/mvn clean test"

        }
      }
      
        post {
            always {
                 // Publier le rapport JaCoCo
                jacoco(
                    execPattern: '**/**.exec', // Chemin d'accès au fichier exec de JaCoCo
                    classPattern: '**/classes', // Chemin d'accès aux fichiers de classe
                    sourcePattern: '**/src/main/java', // Chemin d'accès aux sources
   
    )
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
         stage('Build Docker Image') {
        steps {
            script {
               
                // Le point (.) indique que le contexte de build est le répertoire courant
                sh 'docker build -t kaddem-app:0.0.1 .'
            }
        }
    } 
     stage('Push Image to DockerHub') {
        steps {
            script {
                // Using 'withCredentials' to securely pass Docker Hub credentials
                withCredentials([usernamePassword(credentialsId: 'docker_id', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                    // Login to Docker Hub
                    sh 'echo $DOCKERHUB_PASS | docker login -u $DOCKERHUB_USER --password-stdin'
                    
                    // Push the image to Docker Hub
                    sh 'docker push kaddem-app:0.0.1'
                }
            }
        }
       

       
    }
}
}
  


