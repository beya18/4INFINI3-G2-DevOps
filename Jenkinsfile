pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven 3.9', type: 'maven' // Nom du Maven configuré dans Jenkins
        JAVA_HOME  = tool name: 'JDK 17', type: 'jdk'      // Nom du JDK configuré dans Jenkins
        PATH       = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;%PATH%"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Clonage du code source depuis Git...'
                git branch: 'main', url: 'https://github.com/beya18/4INFINI3-G2-DevOps.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation du projet...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires...'
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging du projet...'
                bat 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'Build et tests terminés avec succès !'
        }
        failure {
            echo 'Build ou tests échoués !'
        }
    }
}
