#!/bin/bash

TARGET_REPO=cloudcms-private-ssh
export TARGET_REPO

TARGET_REPO_URL=scp://maven.cloudcms.com/web/maven/private
export TARGET_REPO_URL

FILE=target/jodconverter-core-3.0-CLOUDCMS-SNAPSHOT.jar 
export FILE

GROUP_ID=org.artofsolving.jodconverter
export GROUP_ID

ARTIFACT_ID=jodconverter-core
export ARTIFACT_ID

VERSION=3.0-CLOUDCMS-SNAPSHOT
export VERSION

# push to private
mvn -X deploy:deploy-file -Dfile=$FILE -DrepositoryId=$TARGET_REPO -DgroupId=$GROUP_ID -DartifactId=ARTIFACT_ID -Dversion=$VERSION -Durl=$TARGET_REPO_URL -Dpackaging=jar -DpomFile=pom.xml
