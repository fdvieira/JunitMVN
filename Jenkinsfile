#!groovy

currentBuild.result = "SUCCESS"
String buildAgentLabel = "aws-node" //add your build agent name here
//noinspection GroovyUnusedAssignment
//@Library('Pipeline-Global-Library') _

try {
        // we poll with no schedule because we want polling enabled for webhooks only
   // pollSCM('')
    stage("Build") {
        node(buildAgentLabel) {
            envSetup()
            build()
        }
    }
	
    stage("Deploy") {
        node(buildAgentLabel) {
            deploy()
        }
    }

        stage("Static Analysis") {
            node(buildAgentLabel) {
                staticAnalysis()
            }
        }
       

        stage("Unit Testing") {
            node(buildAgentLabel) {
                unitTesting()
            }   
        }

    stage("Functional Testing") {
        node(buildAgentLabel) {
            functionalTesting()
        }
    }

    stage("Performance Testing") {
        node(buildAgentLabel) {
            performanceTesting()
        }
    }

    stage("Promote") {
        node(buildAgentLabel) {
            promote()
        }
    }

}
catch (err) {
    echo err.toString()
    currentBuild.result = "FAILURE"
}

def envSetup(){
    deleteDir() //delete jenkins working dir for this project
    checkout scm //clones the git repository
}

def build() {
    sh("mvn clean compile")
}

def staticAnalysis() {
    withSonarQubeEnv('SonarQube-Techops') {
        sh "mvn sonar:sonar"
    }
    timeout(time: 1, unit: 'HOURS') {
        def qg = waitForQualityGate()
        if (qg.status != 'OK') {
            error "Pipeline aborted due to SonarQube quality gate failure: ${qg.status}"
        }
    }
}

def unitTesting() {
    //sh("mvn clean test")
	echo "bfa testing"
}

def deploy() {
    sh 'mvn clean deploy -Dmaven.test.skip=true'
}

def functionalTesting() {}

def performanceTesting() {}

def promote() {}