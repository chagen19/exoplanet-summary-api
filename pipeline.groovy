//docker run --rm -ti -p 8080:8080 -p 50000:50000 -v jenkins-home:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --name jenkins jenkins-docker-jdk11
pipeline {
    agent any
    options {
        ansiColor('xterm')
        buildDiscarder(logRotator(daysToKeepStr: '30'))
        disableConcurrentBuilds()
    }
    stages {
        stage('Clone sources') {
            steps {
                git url: 'https://github.com/chagen19/exoplanet-summary-api/'
            }
        }
        stage('Build') {
            steps {
                sh label: 'Gradle build', script: "./gradlew clean build -x test"
                sh label: 'Docker build', script: "./gradlew -Pversion=1.1.0 docker"
                sh label: 'Docker images', script: "docker images"
            }

        }
        stage('Test') {
            steps {
                sh label: 'Gradle test', script: "./gradlew test"
            }
            post('Publish JUnit') {
                always {
                    junit allowEmptyResults: true, testResults: '**/build/test-results/**/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([usernamePassword(credentialsId: '6995015c-227f-41a5-ab37-2405273c4a0c', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USER')]) {
                    sh label: 'Docker login', script: "docker login -u $DOCKER_USER -p $DOCKER_PASSWORD"
                }
                sh label: 'Docker push', script: "./gradlew dockerPush"
                sh label: 'Docker logout', script: "docker logout"
            }
        }
    }
}