#!/bin/sh
mvn clean install &&
scp ~/workspace/chords-backend/chords-api-rest/target/chords-api-rest-1.1.0.jar zapylaev@139.59.210.149:/home/zapylaev/chords/backend/
