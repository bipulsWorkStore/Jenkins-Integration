pipeline {
    agent any
    tools {
        jdk 'Java 17' // Name as configured in Manage Jenkins > Tools
        maven 'Maven-3.9' // Name as configured in Manage Jenkins > Tools
    }
    stages {
        stage('Checkout Code') {
            steps {
                // Replace with your repository URL and branch if necessary
                git branch: 'main', url: 'https://github.com/bipulsWorkStore/Jenkins-Integration.git'
            }
        }
        stage('Build') {
            steps {
                // Clean and compile the project
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
            post {
                // Archive test results
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                // Package the application into a JAR file
                sh 'mvn package'
            }
            post {
                // Archive the generated JAR artifact
                success {
                    archiveArtifacts artifacts: 'target/*.jar'
                }
            }
        }
        stage('Run Application') {
            steps {
                // Note: This step starts the application as a background process using '&'.
                // A real deployment may involve deploying to a Tomcat server or other environment.
                sh 'java -jar target/*.jar &'
            }
        }
    }
}