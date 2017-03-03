#!/bin/bash

TARGET_REPO=cloudcms-private-ssh
TARGET_REPO_URL=scpexe://maven.cloudcms.com/web/maven/private
VERSION=3.1-CLOUDCMS-SNAPSHOT
GROUP=org.artofsolving.jodconverter
ARTIFACT=jodconverter-core

mvn deploy:deploy-file -Dfile=target/$ARTIFACT-$VERSION.jar -DrepositoryId=$TARGET_REPO -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Durl=$TARGET_REPO_URL -Dpackaging=jar -DpomFile=pom.xml

