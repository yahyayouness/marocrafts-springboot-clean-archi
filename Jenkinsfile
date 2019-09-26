node {

	// pull request or feature branch
    if  (env.BRANCH_NAME != 'develop') {
        preparation()
        buildAndTest()
        analyseSonar()
    } // develop branch / production
    else
	  {
        preparation()
        buildAndTest()
        analyseSonar()
        //deploySnapshot()
		    //deployFrontSnapshot()
        //release()
    }
}


// Preparation
def preparation(){
	 stage('Preparation') { // for display purposes
		  // Get CREDIT CONSO code from GitHub repository
		  checkout scm
	 }
}

// Builder et Lancer les test
def buildAndTest(){
	stage('Build and Unit Test') {
		// Run the maven build with jdk_oracle_8
		env.JAVA_HOME = tool name :  'jdk_oracle_8'
		// Run clean install with tests
		 sh 'mvn clean install -U -V -f ${WORKSPACE}/credit-conso-api-parent/'
		// publish results test
		// junit '**/target/test-reports/unit/TEST-*.xml'
   }
}


// Lancer l'analyse Sonar
def analyseSonar(){
	stage('Analyse Sonar') {
	 withSonarQubeEnv('sonar-mikasa') {
		// requires SonarQube Scanner for Maven 3.2+
		sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.4.0.905:sonar -f ${WORKSPACE}/credit-conso-api-parent/'
	  }
   }

   stage("Quality Gate"){
    	 timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    	   def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
    	   if (qg.status != 'OK') {
    		 error "Pipeline aborted due to quality gate failure: ${qg.status}"
    	   }
	     }
   }

   
}

// Deployer les snapshots java generes
def deploySnapshot(){
	stage ('Deploy SNAPSHOT'){
	   //Deploy Raiser SNAPSHOT in MIKASA-NEXUS
	   sh 'mvn deploy -DskipTests -f ${WORKSPACE}/'
	}
}

// Deployer le snapshot front genere (zip)
def deployFrontSnapshot(){
   stage('Deploy front') {
       // Deploy Raiser Angular SNAPSHOT
       sh "mvn deploy:deploy-file -Dfile=${WORKSPACE}/raiser-framework-ui/raiser-framework-angular/raiser-framework-angular5-${pomVersion}.zip -Durl=https://nexus-snapshot-mikasa-irb.rb.echonet:8443/repository/raiser-snapshots -Dpackaging=zip -DrepositoryId=nexus -DgroupId=com.bnpparibas.raiserframework -DartifactId=raiser-framework-angular5 -Dversion=${pomVersion}"
    }
}

// Faire le release et deployer sur nexus
def release(){
	stage('Release ?') {
	   input 'Do you want to release a new raiser version ?'
	}

	stage('deploy release') {
	   //Deploy Raiser RELEASE in MIKASA-NEXUS
	   sh 'mvn release:clean -B release:prepare release:perform -Dmaven.test.skip=true -Darguments="-Dmaven.test.skip=true -Dmaven.javadoc.skip=true" -DignoreSnapshots=true -f ${WORKSPACE}/'

	}

   	stage('Deploy front') {
       // Deploy Raiser Angular RELEASE in MIKASA-NEXUS
       sh "mvn deploy:deploy-file -Dfile=${WORKSPACE}/raiser-framework-ui/raiser-framework-angular/raiser-framework-angular5-${pomVersion}.zip -Durl=https://nexus-snapshot-mikasa-irb.rb.echonet:8443/repository/raiser-release -Dpackaging=zip -DrepositoryId=nexus -DgroupId=com.bnpparibas.raiserframework -DartifactId=raiser-framework-angular5 -Dversion=${pomVersion}"
    }
}

// Emettre les notifs
def mattermostNotification(){
	stage('Mattermost Notification') {
	   mattermostSend  message:"A new version of RAISER is available - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
	}
}

//   stage('Run Integration Test ?') {
//    	input 'Do you want to run Integration Test ?'
//    }

//   stage ('Tomcat Integration Test') {
//		// Run the maven build with jdk_ibm_8
//		env.JAVA_HOME = tool name :  'jdk_oracle_8'
//		sh 'mvn test -PITestInTomcat -U -V -f ${WORKSPACE}/'
//		// publish results test
//      junit '**/target/test-reports/it/TEST-*.xml'
//   }

//   stage ('WAS Liberty Core Integration Test') {
//		// Run the maven build with jdk_ibm_8
//		env.JAVA_HOME = tool name :  'jdk_oracle_8'
//		sh 'mvn test -PITestInWLC -U -V -f ${WORKSPACE}/'
//		// publish results test
//        junit '**/target/test-reports/it/TEST-*.xml'
//   }
