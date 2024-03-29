#!/bin/sh

SERVICE=`pwd | sed 's#.*/##'`
BRANCH=`git rev-parse --abbrev-ref HEAD`
MESSAGE=`git log --oneline --format=%B -n 1 HEAD | head -n 1`

git checkout master
git pull origin master
NEW_IMAGE_TAG=`git log --oneline --format=%H -n 1 HEAD | head -n 1`

cd ../wetkitty/pillar/wetkitty
git reset --hard
git checkout master
git pull origin master

OLD_IMAGE_TAG=`grep -A 5 "$SERVICE:" macroservice.sls | grep image-tag | grep -o '"[^"]\+"' | sed 's/"//g'`
sed -i '' -e "s/$OLD_IMAGE_TAG/$NEW_IMAGE_TAG/" macroservice.sls

git checkout -b $BRANCH
git add macroservice.sls
git status
git commit -m "$MESSAGE"
git push origin $BRANCH

echo ""
echo "===================="
echo "Service: $SERVICE"
echo "Branch: $BRANCH"
echo "Commit message: $MESSAGE"
echo "Previous image tag: $OLD_IMAGE_TAG"
echo "New image tag: $NEW_IMAGE_TAG"
echo "===================="
echo ""