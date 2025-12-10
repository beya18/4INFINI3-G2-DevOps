pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven 3.9', type: 'maven' // Nom du Maven configuré dans Jenkins
        JAVA_HOME  = tool name: 'JDK 17', type: 'jdk'      // Nom du JDK configuré dans Jenkins
        PATH       = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Clonage du code source depuis Git...'
                git branch: 'main', url: 'https://ton-repo-git.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation du projet...'
                sh 'mvn clean compile'
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires...'
                sh 'mvn test'
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
                sh 'mvn package -DskipTests'
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
