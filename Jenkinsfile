pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checks out the SCM project from the specific branch.
                checkout scm: [$class: 'GitSCM', branches: [[name: '*/Amine']], userRemoteConfigs: [[url: 'https://github.com/JesSsser/Roblox-Team.git']]]
            }
        }
        stage('Maven Clean Compile') {
            steps {
                sh 'mvn clean'
	    	sh 'mvn compile'
            }
        }
	stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
    	stage('Docker') {
   	    steps {
		 script {
			/*  sh 'docker build -t aminemosbeh/kaddem.jar .'*/
                   	 // 'dockerhub' is the ID you've given to the credentials in Jenkins.
                   	 withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'DOCKERHUB_PSW', usernameVariable: 'DOCKERHUB_USR')]) {
                        // This will mask the password in the logs
                        sh "docker login -u $DOCKERHUB_USR -p $DOCKERHUB_PSW"		   }     
			sh 'docker push aminemosbeh/kaddem.jar'
			}
	    	}
	    }
    }
    post {
        // Define post-build actions here, such as notifications, etc.
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
