pipeline {
agent any

stages{
stage('clone and clean repo'){
steps {

bat "git clone https://github.com/EssidIbrahim/devops"
bat "mvn clean -f devops"
}
}
stage('compile'){
steps {

bat "mvn compile -f devops"
}
}

stage('Deploy'){
steps {

bat "mvn sonar:sonar -f devops"
}

}
}
}