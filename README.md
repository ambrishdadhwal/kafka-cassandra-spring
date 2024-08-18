## Run Spring boot app with Kafka & Cassandra DB.
Read data from kafka real time stream 'WikiMedia' and insert data to cassandra DB

## Run Cassandra on Windows
Download datastax from here 'https://datastax-community-edition.software.informer.com/download'
After installing search for 'Cassandra SQL Shell' in windows search bar

OR other way to install Cassandra is
1) Download cassandra
2) Download python
3) Set bin folder path in environment variable's path
4) Run cqlsh.py file (\apache-cassandra\bin)

Default port : 9042
host: localhost

##  Important Cassandra commands
1) describe keyspaces;
2) use employee;
3) desc tables;
4) select * from super_hero;

## Windows scripts

Windows scripts for consumer & producer

## Zookeeper

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

## Kafka

.\bin\windows\kafka-server-start.bat .\config\server.properties

### From Kafka 3.x onwards , we don't need a Zookeeper
Start the server with below command

    # Kraft run
    
    kafka-storage.bat random-uuid      --- (to create a clustr)
    
    kafka-storage.bat format -t GtlcdiKCQguw36UiiUkF3w -c D:\softwares\kafka\config\kraft\server.properties
    
    kafka-server-start.bat D:\softwares\kafka\config\kraft\server.properties


    
    ## Topics
    
    #1. create  --->  kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 2 --replication-factor 2
    
    #2. get all	--->  kafka-topics.sh --zookeeper 127.0.0.1:2181 --list
    
    #3.  get one  ---> kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic first_topic --describe
    
    #4. delete   ---> kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic second_topic --delete
