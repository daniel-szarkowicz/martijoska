#!/usr/bin/env bash

javac Unbased.java
set -e

for i in $(seq 1 1000)
do
  echo "Deobfuscating $i"

  FLAG=`java -jar jd-cli.jar Class$i.class\
    | awk -F '"' '/flag/ {print $2}'`

  if [ ! -z "${FLAG}" ]; then
    echo $FLAG
    exit 0
  fi

  java -jar jd-cli.jar Class$i.class\
    | awk -F '"' '$1 ~ /stringBuilder\.append/ {printf $2}'\
    | java Unbased > Class$(($i + 1)).class
done
