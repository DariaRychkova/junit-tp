pipeline {
    agent {
        label ''
    }
    stages {
        stage('Checkout...') {
            steps {
                checkout scm
            }
        }
        stage("Publish") {
            steps {
                script {
                    echo "La valeur recue est : $params.INPUT_CHOICE"
                    if ("$params.INPUT_CHOICE" == "1") {
                        echo "Simple Clean Install Maven"
                        withMaven(maven: 'maven-3.8.1') {
                            bat 'mvn -Dmaven.test.skip=true clean install'
                        }
                    }
                    if ("$params.INPUT_CHOICE" == "2") {
                        echo "Analyse Avec SonarQube"
                        withSonarQubeEnv(installationName: 'sonarqube-indyli-server') {
                            bat 'mvn -Dmaven.test.skip=true sonar:sonar  -Dsonar.projectKey=junitci-business-teacher -Dsonar.token=squ_909961d10efddfe189f0aca640f18a3e9c9d8b72'
                        }
                    }
                }
            }
        }
    }
}
