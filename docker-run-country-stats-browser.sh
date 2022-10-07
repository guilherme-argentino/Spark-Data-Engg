#!/bin/sh

docker  run --network linkedin-spark-exercises_default -it $(docker build -q .) \
        com.learning.sparkdataengg.chapter4.CountryStatsBrowser -H redis