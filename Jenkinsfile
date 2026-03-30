pipeline {
    agent any                      // Run on any available agent

    tools {
        jdk 'Java 17'                // Name you gave in Global Tool Configuration
        maven 'Maven-3.9'
    }

    environment {
        APP_NAME = 'BookApiWithH2DBRecord-0.0.1-SNAPSHOT'   // Change if your JAR name is different
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo '📥 Checking out code from Git...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '🔨 Building the project...'
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo '🧪 Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo '📦 Packaging into executable JAR...'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo '💾 Archiving JAR file...'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo '🚀 Deploying Spring Boot REST API...'
                
                // Kill any old instance (if running)
                sh '''
                    echo "Stopping previous application (if any)..."
                    pkill -f "${APP_NAME}.jar" || true
                    sleep 3
                '''
                
                // Deploy and start new JAR in background
                sh '''
                    echo "Starting new application..."
                    nohup java -jar target/*.jar > app.log 2>&1 &
                    echo $! > app.pid
                    echo "✅ Application started successfully!"
                '''
                
                // Wait a few seconds and do a simple health check
                sh '''
                    sleep 8
                    echo "🔍 Health check on /hello endpoint..."
                    curl -f http://localhost:8080/hello || echo "⚠️ Health check failed (check app.log)"
                '''
            }
        }
    }

    post {
        success {
            echo '🎉 Pipeline completed successfully! Your REST API is now live.'
        }
        failure {
            echo '❌ Pipeline failed. Check console output for details.'
        }
    }
}