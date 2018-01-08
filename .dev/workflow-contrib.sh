#!/bin/sh
#
#    MIT License
#    Copyright (c) 2016-2017 Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
#    Permission is hereby granted, free of charge, to any person obtaining a copy
#    of this software and associated documentation files (the "Software"), to deal
#    in the Software without restriction, including without limitation the rights
#    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
#    copies of the Software, and to permit persons to whom the Software is
#    furnished to do so, subject to the following conditions:
#    The above copyright notice and this permission notice shall be included in all
#    copies or substantial portions of the Software.
#    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
#    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
#    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
#    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
#    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
#    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
#    SOFTWARE.
#
#
# Author..............: pylapp
# Version.............: 5.0.0
# Since...............: 23/10/2017
# Description........:
#			Bash script which can make sync with repositories, get content from them and save modifications to some of them.
#			Script to use to as to make easier the use of both legacy repositories (forked repos), the inner repositories (fork repos) and additonal repositories (forge, GitLab etc.)
#

VERSION="v5.0.0"

# ############# #
# Configuration #
# ############# #

# From where files will be picked (i.e. where are folders cloned from legacy remote repo)?
SOURCE_FOLDER="."

# To where files should be pasted (i.e. where is the folder clone from inner remote repo)?
# TODO Define it
DESTINATION_FOLDER=""

# Where are we?
WHERE_WERE_WE=`pwd`

# Where is stored the SSH key for git?
# TODO Define it
GIT_SSH_KEY=""

# The working branch with the dev code for Appium-related repos
# TODO Define it, here your branch you created in the fork repo
APPIUM_WORKING_BRANCH=""
# The working branch with the dev code for Tapster-related repos
# TODO Define it
TAPSTER_WORKING_BRANCH=""

# The master branch with the complete and sure code
MASTER_BRANCH="master"

# The alias defined in 'git remote -v' for the fork legacy repo (e.g. my repo)
ALIAS_REMOTE_FORK="origin"

# The alias defined in 'git remote -v' for the forked legacy repo (e.g. their repo)
# Warning Do not forget to check it with 'git remote -v'
ALIAS_REMOTE_FORKED="upstream"


# ######### #
# Functions #
# ######### #

# \fn fUsageAndExit
# \brief Displays the usage and exits
fUsageAndExit(){
	echo "[$0 $VERSION]"
	echo "USAGE:"
	echo "sh workflow-contrib.sh {--help | --syncWithLegacy | --getFromLegacy | --getFromInner | --saveToLegacy your-commit-message | --saveToInner your-commit-message}"
	echo "sh workflow-contrib.sh {-h | -sync | -gfl | -gfi | -stl your-commit-message | -sti your-commit-message}"
	echo "\t --help	...................................: displays the help, i.e. this usage"
	echo "\t -h .......................................: displays the help, i.e. this usage"
	echo "\t --syncWithLegacy..........................: will fetch remote repo (upstream), get and merge its content to local repo"
	echo "\t -sync.....................................: will fetch remote repo (upstream), get and merge its content to local repo"
	echo "\t --getFromLegacy ..........................: retrieve files from existing remote legacy repo (e.g. GitHub)"
	echo "\t -gfl .....................................: retrieve files from existing remote legacy repo (e.g. GitHub)"
	echo "\t --getFromInner ...........................: retrieve files from existing remote inner repo (e.g. forge, GitLab, ...)"
	echo "\t -gfi .....................................: retrieve files from existing remote inner repo (e.g. forge, GitLab, ...)"
	echo "\t --saveToLegacy your-commit-message........: save files by adding, committing and pushing them to remote legacy repo (e.g. GitHub)"
	echo "\t -stl your-commit-message..................: save files by adding, committing and pushing them to remote legacy repo (e.g. GitHub)"
	echo "\t --saveToInner your-commit-message.........: save files by adding, committing and pushing them to remote inner repo (e.g. forge, GitLab, ...)"
	echo "\t -sti your-commit-message..................: save files by adding, committing and pushing them to remote inner repo (e.g. forge, GitLab, ...)"
	exit 0
}

# \fn fGetFromLegacy
# \brief Retrieves from remote repo (in GitHub) new versions of files
fGetFromLegacy(){

	# Add SSH KEY containing the key to use soa s to deal with legacy repo (SSH used instead of HTTP/HTTPS)
	ssh-add $GIT_SSH_KEY

	cd $SOURCE_FOLDER

	# List files and get into folders
	for foundFile in `ls .`; do
		if [ -d "$foundFile" ]; then
			cd $foundFile
			# For each git folder retrieve new versions using git pull
			if [ -d ".git" ]; then

				echo "NOTE: with $foundFile - found git repository, will get changes"

				# Here is the strategy:
				#		- we want to fetch the changes of the forked repo, the very legacy one
				#	  - we want to get these changes and have a git tree quite linear and clean
				#		Thus 'rebase' is used instead of 'merge', and 'fetch' has replaced 'pull'
				#
				# For the branch we are working on, a simple 'merge' might be good.

				# Step 1 - Update master
				echo "NOTE: Updating master..."
				git checkout $MASTER_BRANCH
				git fetch $ALIAS_REMOTE_FORKED
				git rebase -p $ALIAS_REMOTE_FORK

				# Step 2 - Add the news in the work branch
				if [ "$foundFile" = "tapsterbot" ]; then
					echo "NOTE: Updating $APPIUM_WORKING_BRANCH"
					git checkout $APPIUM_WORKING_BRANCH
				else
					echo "NOTE: Updating $TAPSTER_WORKING_BRANCH"
					git checkout $TAPSTER_WORKING_BRANCH
				fi
				git merge $MASTER_BRANCH

			else
				echo "NOTE: with $foundFile - not a git repository"
			fi
			cd ..
		fi
	done

	cd $WHERE_WERE_WE

}

# \fn fGetFromInner
# \brief Retrieves from remote repo (in OrangeForge) new versions of files using git pull
fGetFromInner(){
	cd $DESTINATION_FOLDER
	if [ -d ".git" ]; then
		echo "NOTE: found git repository, will get changes"
		git pull
	else
		echo "NOTE: not a git repository"
	fi
	cd $WHERE_WERE_WE
}

# \fn fSaveToLegacy
# \brief Saves to remote repo (in GitHub) new versions of files using git push
# \param A commit message
fSaveToLegacy(){

	# Add SSH KEY
	ssh-add $GIT_SSH_KEY

	cd $SOURCE_FOLDER

	# List folders
	for foundFile in `ls .`; do
		if [ -d "$foundFile" ]; then
			# For each folder add versions and push them
			cd $foundFile
			# For each git folder retrieve new versions using git pull
			if [ -d ".git" ]; then
				echo "NOTE: with $foundFile - found git repository, will save changes"
				if [ "$foundFile" = "tapsterbot" ]; then
					git checkout $TAPSTER_WORKING_BRANCH
				else
					git checkout $APPIUM_WORKING_BRANCH
				fi
				git status
				git add *
				git status
				#git commit # A commit message may be prompted and filled by the user
				git commit -m "$1"
				git status
				if [ "$foundFile" = "tapsterbot" ]; then
					git push $ALIAS_REMOTE_FORK $TAPSTER_WORKING_BRANCH
				else
					git push $ALIAS_REMOTE_FORK $APPIUM_WORKING_BRANCH
				fi
				git status
			else
				echo "NOTE: with $foundFile - not a git repository"
			fi
			cd ..
		fi
	done

	cd $WHERE_WERE_WE

}

# \fn fSaveToInner
# \brief Saves to remote repo (in Orange Forge) new versions of files using git push
# \param A commit message
fSaveToInner(){

	# Copy files
	cp -r $SOURCE_FOLDER/* $DESTINATION_FOLDER

	# Trigger Git workflows
	cd $DESTINATION_FOLDER
	if [ -d ".git" ]; then
		echo "NOTE: found git repository, will save changes"
		git status
		git add *
		git status
		#git commit -m "chore: saved files from working legacy repository to backup repository"
		git commit -m "$1"
		git status
		git push
		git status
	else
		echo "NOTE: not a git repository"
	fi

	# Go back
	cd $WHERE_WERE_WE

}

# \fn fSyncWithLegacy
# \brief Will fetch upstream and merge its content with local repo (in master branch)
fSyncWithLegacy(){

	# Example: for a dedicated repo
	# git remote -v, to see the remote repositories
	# git remote add upstream the-url-of-github-repo.git, to add the upstream
	# git remote -v, to check if new upstream added

	cd $SOURCE_FOLDER

	# List folders
	for foundFile in `ls .`; do
		if [ -d "$foundFile" ]; then

			cd $foundFile

			## WARNING Do not deal with quite-dead repo for Tapsterbot, timeout in fetch process
			#if [ "$foundFile" != "tapsterbot" ]; then
				# For each git folder retrieve new versions using git pull
				if [ -d ".git" ]; then
					echo "NOTE: with $foundFile - git repository"

					git checkout $MASTER_BRANCH
					git fetch $ALIAS_REMOTE_FORKED
					#git rebase -p $ALIAS_REMOTE_FORK
					#git push $ALIAS_REMOTE_FORK $MASTER_BRANCH
					git merge "$ALIAS_REMOTE_FORKED/master"
					git push $ALIAS_REMOTE_FORK $MASTER_BRANCH

					if [ "$foundFile" != "tapsterbot" ]; then
						git checkout $APPIUM_WORKING_BRANCH
					else
						git checkout $TAPSTER_WORKING_BRANCH
					fi

					## Pull and merge content from upstream
					#git fetch upstream
					## Deal with our master branch
					#git checkout master
					## Make the merge
					#git merge upstream/master

				else
					echo "NOTE: with $foundFile - not a git repository"
				fi

			#  "$foundFile" = "tapsterbot"
			#else
			#	if [ -d ".git" ]; then
			#		git checkout $TAPSTER_WORKING_BRANCH
			#	else
			#		echo "NOTE: with $foundFile - not a git repository"
			#	fi
			#fi
			cd ..
		fi
	done

	fSaveToLegacy "Sync with upstream"

	cd $WHERE_WERE_WE

}

# ######### #
# MAIN CODE #
# ######### #

# Check the args and display usage if needed
if [ "$#" -lt 1 -o "$#" -gt 2 ]; then
	echo "ERROR: bad command"
	fUsageAndExit
fi

# Let's work !
if [ "$1" ]; then
	# Help
	if [ "$1" = "--help" -o "$1" = "-h" ]; then
		fUsageAndExit
	# Sync with legacy
	elif [ "$1" = "--syncWithLegacy" -o "$1" = "-sync" ]; then
		fSyncWithLegacy
	# Get from legacy
	elif [ "$1" = "--getFromLegacy" -o "$1" = "-gfl" ]; then
		fGetFromLegacy
	# Get from inner
	elif [ "$1" = "--getFromInner" -o "$1" = "-gfi" ]; then
		fGetFromInner
	# Save to legacy
	elif [ "$1" = "--saveToLegacy" -o "$1" = "-stl" ]; then
		if [ "$2" ]; then
			fSaveToLegacy "$2"
		else
			echo "ERROR: bad command"
			fUsageAndExit
		fi
	# Save to inner
	elif [ "$1" = "--saveToInner" -o "$1" = "-sti" ]; then
		if [ "$2" ]; then
			fSaveToInner "$2"
		else
			echo "ERROR: bad command"
			fUsageAndExit
		fi
	# ...
	else
		echo "ERROR: bad command"
		fUsageAndExit
	fi
else
	echo "ERROR: bad command"
	fUsageAndExit
fi
