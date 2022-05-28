pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "3.8.1"
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }

    stages {
        stage('Scan and Build code') {
            steps {
                sh "mvn -version"

                // Run Maven on a Unix agent.
                withSonarQubeEnv(installationName: 'SonarQube9.4', credentialsId: 'SonarQubeToken') {
                    sh "mvn -Dmaven.test.skip=true  clean package -f gestorTransaccional/pom.xml sonar:sonar"
                }

                
            }
            options {
                timeout(time: 5, unit: "MINUTES")
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    //archiveArtifacts 'target/*.jar'
                    echo "Pipeline finalize succefully"
                }
            }
        }
        stage('Run Tests') {
            steps {

                // Run Maven on a Unix agent.
                sh "mvn test -f gestorTransaccional/pom.xml"
                
            }
            options {
                timeout(time: 5, unit: "MINUTES")
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    //archiveArtifacts 'target/*.jar'
                    echo "Test executed succefully"
                }
            }
        }
        stage('Deploy') {
            steps {

                // Run Maven on a Unix agent.
                echo "Start Deploy"
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