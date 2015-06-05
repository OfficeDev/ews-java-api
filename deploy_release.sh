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
set -e # Any subsequent(*) commands which fail will cause the shell script to exit immediately

# static variables
version="1.0"
usage="usage: deploy_release [-v|--version] [-g|--gpg] [-t|--tag] [-h|\?|--help]\n\nDocumentation <of Version: $version>"+="\n\t[-v|--version]\tset the new version number\n\t\t\t(pom.xml will be updated with this)"+="\n\t[-h|\?|--help]\tdisplay this help"+="\n\t[-t|--tag]\tshould a git tag be created? default=false"+="\n\t[-g|--gpg]\t the gpg passphrase"
RED='\033[0;31m'
NC='\033[0m'
GREEN='\033[0;32m'
DATEFORMAT="%r"

# Variables:
VERSION=
GPGPASS=
CREATETAG=false

function info {
	NOW=$(date +$DATEFORMAT);
	printf "${GREEN}[$NOW][INFO] $1${NC}\r\n";
}

function error {
	NOW=$(date +$DATEFORMAT);
	if [ "$2" = true ] ; then
		printf "${RED}[$NOW][ERROR] $1${NC}\r\n" && echo -e $usage; 
	else
		printf "${RED}[$NOW][ERROR] $1${NC}\r\n"; 
	fi
	exit 1;
}

function prompt {
	while [ -z $prompt ]; do read -p "Continue (y/n)?" choice;case "$choice" in y|Y ) prompt=true; break;; n|N ) exit 0;; esac; done; prompt=;
}

if [ $# -eq 0 ]; then
    error "No params set" true
fi

while [[ $# > 0 ]]
do
	key="$1"
	case $key in
		-v|--version)
		VERSION="$2"
		shift
		;;
		-t|--tag)
		CREATETAG="$2"
		shift
		;;
		-g|--gpg)
		GPGPASS="$2"
		shift
		;;
		-h|\?|--help)
		echo -e $usage
		exit 0
		;;
		*) error "Illegal argument" exit 1
		;;
	esac
	shift # past argument or value
done

# check if all needed parameters where set
[ -z $VERSION ] && error "Need to set VERSION" true
[ -z $CREATETAG ] && error "Need to set CREATETAG" true
[ -z $GPGPASS ] && error "Need to set GPGPASS" true

#
info "VERSION=$VERSION \t CREATETAG=$CREATETAG \tGPGPASS=$GPGPASS";
info "You are now at the following branch:"
git rev-parse --symbolic-full-name --abbrev-ref HEAD
prompt

info "${RED}Current branch needs to be clean. Only proceed if there are no local changes...${NC}"
git status -s
prompt

info "Updating current Branch"
git pull

info "START DEPLOYMENT"
info "Setting new Version to $VERSION (see mvn-update-version.log)"
mvn --batch-mode release:update-versions -DdevelopmentVersion=$VERSION
git status -s

info "Creating release version and deployment..."
prompt

mvn clean deploy -Dgpg.passphrase=$GPGPASS

if [ "$CREATETAG" = true ] ; then
	info "Creating TAG for $VERSION"
	prompt
	
	git tag -a $VERSION -m "Tag: $VERSION"
	
	info "Pushing TAG for $VERSION"
	prompt
	git push --tags
fi

info "Everything is fine. $VERSION has been deployed..."