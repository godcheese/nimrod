#!/usr/bin/env bash
# Linux platform bash file
echo "author godcheese"
CURRENT_DIR=`pwd`
SCRIPTS_DIR=$(cd `dirname $0`; pwd)
cd $SCRIPTS_DIR
cd ..
echo "Chceckout new branch..."
git checkout --orphan new_branch
echo "Add file..."
git add -A
echo -n "Submit remark...Please input anything(Initial commit):"
read REMARK
if [ ! -n "$REMARK" ];then
    REMARK="Initial commit"
fi
git commit -m "$REMARK"
echo "Delete master branch..."
git branch -D master
echo "Rename new branch to master..."
git branch -m master
echo "Force submit code..."
git push -f origin master
echo "Submit complete,close..."
cd $CURRENT_DIR