#!groovy

pipeline {

    agent any

    tools {
         gradle "gradle-4.3.1"
    }

    stages {
        stage('Build & Unit-Tests') {
            steps {
                sh "gradle build"
            }
        }
    }
}