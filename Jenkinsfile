#!groovy

pipeline {

    agent any

    tools {
         gradle "gradle-4.0"
    }

    stages {
        stage('Build & Unit-Tests') {
            steps {
                sh "gradle build"
            }
        }
    }
}