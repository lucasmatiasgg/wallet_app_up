pipeline {
    agent any

    tools {
        maven "3.8.1"
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }

    stages {
        stage('Scan code') {
            steps {
                withSonarQubeEnv(installationName: 'SonarQube9.4', credentialsId: 'jenkinsUserInSonar') {
                    sh "mvn clean verify sonar:sonar -Dmaven.test.skip=true -f gestorTransaccional/pom.xml "
                }
            }
            options {
                timeout(time: 5, unit: "MINUTES")
            }
            post {
                success {
                    echo "Scan finalize succefully"
                }
            }
        }
        stage('Build code') {
            steps {
                sh "mvn -version"
                sh "mvn -Dmaven.test.skip=true  clean package -f gestorTransaccional/pom.xml "
            }
            options {
                timeout(time: 5, unit: "MINUTES")
            }
            post {
                success {
                    echo "Scan finalize succefully"
                }
            }
        }
        stage('Run Tests') {
            steps {
                sh "mvn test -f gestorTransaccional/pom.xml"
            }
            options {
                timeout(time: 5, unit: "MINUTES")
            }
            post {
                success {
                    echo "Test executed succefully"
                }
            }
        }
        stage('Deploy') {
            steps {
                echo "Start Deploy"
                script {
                  deploy adapters: [tomcat9(credentialsId: 'tomcat_credential', path: '', url: 'http://localhost:8080')], contextPath: '/api', onFailure: false, war: '**/*.war' 
                }
            }
            options {
                timeout(time: 10, unit: "MINUTES")
            }
            post {
                success {
                    echo "Build succefully"
                }
                always {
                    cleanWs()
                }
            }
        }
    }
}