#!/bin/bash

# The MIT License
# Copyright (c) 2012 Microsoft Corporation
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.

if [ "$TRAVIS_REPO_SLUG" != "OfficeDev/ews-java-api" ]; then 
	echo "[DEPLOY] Skipping snapshot deployment for repo:'$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
	echo "[DEPLOY] Skipping snapshot deployment for a pull request."
elif [ "$TRAVIS_SECURE_ENV_VARS" == "false" ]; then
	echo "[DEPLOY] Skipping snapshot deployment due to TRAVIS_SECURE_ENV_VARS is set to '$TRAVIS_SECURE_ENV_VARS'."
elif [ "$TRAVIS_JDK_VERSION" != "oraclejdk7" ]; then
	echo "[DEPLOY] Skipping snapshot deployment for jdk:'$TRAVIS_JDK_VERSION'."
else 
	echo "[DEPLOY] Deploying snapshot for commit:'$TRAVIS_COMMIT' @ build-id:'$TRAVIS_BUILD_ID'"	
	# create settings.xml
	echo "<settings><servers><server><id>ossrh-snapshot</id><username>${OSSRHUSER}</username><password>${OSSRHPASS}</password></server></servers></settings>" > $HOME/.m2/settings.xml
	# deploy
	if [ -z "${GPG_PASSPHRASE+xxx}" ]; then
	    echo "[INFO] Deploying unsigned artifacts"
            mvn clean deploy --settings="$HOME/.m2/settings.xml" -Dmaven.test.skip=true -Dcheckstyle.skip=true
        else
            echo "[INFO] Deploying signed artifacts"
            mvn clean deploy --settings="$HOME/.m2/settings.xml" -Dmaven.test.skip=true -Dcheckstyle.skip=true -Dgpg.passphrase=$GPG_PASSPHRASE
        fi
	# clean up
	rm -f $HOME/.m2/settings.xml
fi
