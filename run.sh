#!/bin/sh

java -jar ./Spark-Data-Engg-1.0-SNAPSHOT-jar-with-dependencies.jar "$@"
tail -f /dev/null