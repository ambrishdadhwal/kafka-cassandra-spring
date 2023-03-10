
## Windows scripts

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
