## Configure Cassandra on Windows 
#1 Download cassandra binaries from official website.
#2 Set path of {cassandra}/bin folder in Environment Variables.
#3 Open Linux terminal in windows (prefer Ubuntu, since you can not run cassandra DB on windows . You will need Docker image & container from Datastax)
#4 Run command 'cassandra' (it will start cassandra service)
#5 Run command 'cqlsh' , this will open up shell for cassandra DB.

## Windows scripts for Kafka

Windows scripts for consumer & producer

## Zookeeper

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

## Kafka

.\bin\windows\kafka-server-start.bat .\config\server.properties


## Topics

#1. create  --->  kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 2 --replication-factor 2

#2. get all	--->  kafka-topics.sh --zookeeper 127.0.0.1:2181 --list

#3.  get one  ---> kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --describe

#4. delete   ---> kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic second_topic --delete
