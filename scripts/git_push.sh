#!/usr/bin/env bash
echo "author godcheese"
CURRENT_DIR=$(pwd)
SCRIPTS_DIR=$(cd "$(dirname $0)" || exit; pwd)
cd "${SCRIPTS_DIR}" || exit
cd ..
echo "Add file..."
git add -A
echo -n "Submit remark...Please input anything(Initial commit):"
read REMARK
if [[ ! -n "$REMARK" ]];then
    REMARK="Initial commit"
fi
git commit -m "$REMARK"
echo "Submit code..."
git push origin master
echo "Submit complete,close..."
cd "${CURRENT_DIR}" || exit