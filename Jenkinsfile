pipeline {
    agent any

    environment {
        IMAGE_NAME = "beya/4infini3-devops"
        CONTAINER_NAME = "devops-app"
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

        stage('SonarQube Analysis') {
            steps {
                echo 'Analyse du code avec SonarQube...'
                withSonarQubeEnv('SonarQube') {
                    bat '''
                    mvn sonar:sonar ^
                    -Dsonar.projectKey=4INFINI3-G2-DevOps ^
                    -Dsonar.projectName=4INFINI3-G2-DevOps ^
                    -Dsonar.host.url=http://localhost:9000
                    '''
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging du projet...'
                bat 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Construction de l’image Docker...'
                bat '''
                docker build -t %IMAGE_NAME% .
                '''
            }
        }

        stage('Docker Run') {
            steps {
                echo 'Lancement du conteneur Docker...'
                bat '''
                docker rm -f %CONTAINER_NAME% || exit 0
                docker run -d --name %CONTAINER_NAME% -p 8081:8080 %IMAGE_NAME%
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline CI/CD terminé avec succès (Maven + SonarQube + Docker) !'
        }
        failure {
            echo 'Échec du pipeline CI/CD.'
        }
    }
}
