#!groovy

pipeline {
    agent any

    stages {
        stage('Build & Unit-Tests') {
            steps {
                sh "gradle build"
            }
        }
    }
}