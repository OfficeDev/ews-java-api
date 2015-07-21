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

function on_exit {
    # clean up
    rm -f $HOME/.m2/settings.xml
    rm -f $HOME/.config/hub
}

set -e # Any subsequent(*) commands which fail will cause the shell script to exit immediately
trap on_exit EXIT # on exit do clean up

if [ "$TRAVIS_REPO_SLUG" != "OfficeDev/ews-java-api" ]; then
    echo "[DEPLOY] Skipping snapshot deployment for repo:'$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
    echo "[DEPLOY] Skipping snapshot deployment for a pull request."
elif [ "$TRAVIS_BRANCH" != "master" ]; then
    echo "[DEPLOY] Skipping snapshot deployment for branch:'$TRAVIS_BRANCH'."
elif [ "$TRAVIS_SECURE_ENV_VARS" == "false" ]; then
    echo "[DEPLOY] Skipping snapshot deployment due to TRAVIS_SECURE_ENV_VARS is set to '$TRAVIS_SECURE_ENV_VARS'."
elif [ "$TRAVIS_JDK_VERSION" != "oraclejdk7" ]; then
    echo "[DEPLOY] Skipping snapshot deployment for jdk:'$TRAVIS_JDK_VERSION'."
else
    echo "[DEPLOY] Deploying snapshot for commit:'$TRAVIS_COMMIT' @ build-id:'$TRAVIS_BUILD_ID'"
    # create settings.xml
    echo "<settings><servers><server><id>ossrh-snapshot</id><username>${OSSRHUSER}</username><password>${OSSRHPASS}</password></server></servers></settings>" > $HOME/.m2/settings.xml
    # deploy
    if [ -z "${GPG_PASSPHRASE}" ]; then
       echo "[INFO] Deploying unsigned artifacts"
       mvn clean deploy --settings="$HOME/.m2/settings.xml" -Dmaven.test.skip=true
    else
       echo "[INFO] Deploying signed artifacts"
       mvn clean deploy --settings="$HOME/.m2/settings.xml" -Dmaven.test.skip=true -Dgpg.passphrase=$GPG_PASSPHRASE
    fi
    echo "[DEPLOY] Done deploying snapshot"

    if [ -z "${GITHUB_USER}" ]; then
       echo "[GH-PAGES] Deployment of gh-pages skipped. No GITHUB_TOKEN set."
    else
       # download hub
       mkdir -m777 -v -p "$HOME/download" ; cd "$HOME/download" && { curl -L https://github.com/github/hub/releases/download/v2.2.1/hub-linux-amd64-2.2.1.tar.gz | tar xz ; cd -; }

       # init travis-user
       [[ -d "$HOME/.config" ]] || mkdir -m777 -v -p $HOME/.config
       echo -e "github.com:\n- user: ${GITHUB_USERNAME}\n  oauth_token: ${GITHUB_TOKEN}\n  protocol: https\n" > $HOME/.config/hub

       mvn clean site

       echo "[GH-PAGES] Deploying gh-pages for $TRAVIS_BRANCH"

       # clone actual gh-pages repo
       cd "$HOME/OfficeDev"
       echo "[GH-PAGES] Cloning travis-user fork"
       git clone -q --progress -b gh-pages --single-branch https://${GITHUB_TOKEN}@github.com/$TRAVIS_REPO_SLUG.git gh-pages && cd gh-pages
       git remote add upstream https://github.com/$TRAVIS_REPO_SLUG.git
       echo "[GH-PAGES] Updating origin if needed"
       git fetch -q --progress upstream gh-pages
       git merge -q --progress upstream/gh-pages gh-pages
       git push -fq --progress origin gh-pages

       echo "[GH-PAGES] Applying local changes"
       git rm -rf --ignore-unmatch "./docs/snapshots/$TRAVIS_BRANCH"
       mkdir -m777 -v -p "./docs/snapshots/$TRAVIS_BRANCH"

       # copy all the builded stuff into the snapshot dir
       cp -Rf "$HOME/$TRAVIS_REPO_SLUG/target/site/*" "./docs/snapshots/$TRAVIS_BRANCH"

       git add -A
       echo "[GH-PAGES] Committing changes to local repository"
       git commit --author "travis-ci <travis@travis-ci.org>" -m "[TRAVIS]Deploy mvn site [ci skip] @ $TRAVIS_BUILD_NUMBER"
       echo "[GH-PAGES] Pushing changes to origin gh-pages"
       git push -fq --progress origin gh-pages

       echo "[GH-PAGES] Creating a PR"
       eval "$HOME/download/hub-linux-amd64-2.2.1/hub pull-request -m "$(printf "[ci skip][TRAVIS] UPDATE [gh-pages/$TRAVIS_BRANCH] @ $TRAVIS_BUILD_NUMBER\n\n$TRAVIS_BUILD_NUMBER\nAutomated Pull Request :shipit:")" -b 'OfficeDev/ews-java-api:gh-pages' -h ${GITHUB_USERNAME}/ews-java-api:gh-pages"

       echo "[GH-PAGES] Finished site deployment. Pull request now needs to be merged by humans."
    fi
fi
exit 0