pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo 'Getting code from GIT..'
                    git branch: "Houssem",
                    url : "https://github.com/JesSsser/Roblox-Team.git"
            }
        }
        stage('MVN CLEAN TEST') {
            steps {
                echo 'Maven Cleaning'
                    sh 'mvn clean test'
            }
        }
        stage('MVN COMPILE') {
            steps {
                echo 'Maven Compile'
                    sh 'mvn compile'
            }
        }
        stage('SONARQUBE') {
            steps {
                echo 'Sonarqube'
                    sh 'mvn sonar:sonar  -Dsonar.token=sqa_2315760932fd000e2b85dd1fd4a14278afe94e48'
            }
        }
        stage('Nexus'){
            steps{
                echo 'Nexus'
               		 sh 'mvn deploy -DskipTests=true'
            }
        }
        stage('Docker image') {
	        steps {
    		    script {
    			    sh "docker login -u houssem13 -p 168T8461@"	
    			    sh 'docker build -t houssem13/kaddem-0.0.1.jar .'
    			}
		    }
	    }

        stage('Docker Build') {
    	    steps {
                script {
    			    sh "docker login -u houssem13 -p 168T8461@"			            
    			    // Pushing the Docker image
    			    sh 'docker push houssem13/kaddem-0.0.1.jar'
				}
			}
        }

        stage('Docker compose') {
            steps {
                sh 'docker-compose -f docker-compose.yml up -d'
            }
       	} 
    }
    post {
        always {
            emailext body: 'A Test EMail', recipients: 'houssemhosni13@gmail.com' , subject: 'Test'
        }
    }
}
