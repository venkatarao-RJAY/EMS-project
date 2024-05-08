pipeline {
    agent any
    #this statge is use for checkout the git code from git repo
    stages {
        stage('Checkout') {
            steps {
                // Checkout code from Git repository
                git branch: 'bhargav', url: 'https://github.com/Bhargavramredd/b2b.git'
            }
        }
        stage('Build') {      # this stage is user maven build
            steps {
                // Change directory to where the pom.xml file is located
                dir('/var/lib/jenkins/workspace/admin/admin') {
                    // Build Maven project
                    sh '/opt/apache-maven-3.9.6/bin/mvn clean install'
                }
            }
        }
        stage('Build Docker Image') {     # this stage is used for to build the dcoker image
            steps {
                // Change directory to where the Dockerfile is located
                dir('/var/lib/jenkins/workspace/admin/admin') {
                    // Build Docker image
                    sh 'docker build -t bhargavrjaytech/b2badmin:latest .'
                }
            }
        }
        stage('Push Docker Image') {    # this stage used for to push the code to docker hub
            steps {
                // Push Docker image to Docker Hub
                sh 'docker login -u bhargavrjaytech -p Bhargav@123'
                sh 'docker push bhargavrjaytech/b2badmin:latest'
            }
        }
    }
    
    post {
        success {
            // Actions to perform if the build is successful
            echo 'Build succeeded! Deploying...'
            // Add deployment steps here if needed  
        }
    }
}




required plugins:

maven integration
docker plugins
docker pipeline
etc 