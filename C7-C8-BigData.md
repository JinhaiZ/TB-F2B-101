# WebScale Computing and Big Data

## 3 v in Big Data
Big Data
- Volume
- Value
- Velocity
- Variety

Volume

> Mark Kryder's law:

Velocity

10ns latency between cpu and memory, velocity of light, 10ns for 30cm

- in Hard Disk
read 1 MS in 80 us
read 1GB in 10s
read 1TB in 2h46
8.66 TB a day

- in Network
for Ethernet 10^9 bits/s, but conerning Internet Protocol and TCP/UDP, avaiable bits are lesser

800Mbps bandwith limit
read 1GB in 10.5s
8.23 TB a day

- transfer by truck
100TB a day

- data processing
    - extract a substring 25ns
    - convert string to number 90ns
    - conver string to uppercase 75ns
    - allocate an array of 100 bytes 30ns

- impact of processing time
    - op that takes 10ns
    - can be peformed ? times in 1s
    - applying 1 billion in ? s

Latency Comparison Numbers
--------------------------
operation | time(ns) | time(us) 
----------|----------|---------
L1 cache reference                |           0.5 ns|
Branch mispredict                  |          5   ns|
L2 cache reference                           7   ns  |                    14x L1 cache|
Mutex lock/unlock |                          25   ns|
Main memory reference                      100   ns |                     20x L2 cache, 200x L1 cache|
Compress 1K bytes with Zippy|             3,000   ns|        3 us
Send 1K bytes over 1 Gbps network|       10,000   ns |      10 us
Read 4K randomly from SSD*             150,000   ns |     150 us          ~1GB/sec SSD|
Read 1 MB sequentially from memory|     250,000   ns |     250 us
Round trip within same datacenter  |    500,000   ns|     500 us
Read 1 MB sequentially from SSD*     1,000,000   ns|    1,000 us    1 ms  ~1GB/sec SSD, 4X memory|
Disk seek                     |      10,000,000   ns|   10,000 us   10 ms  20x datacenter roundtrip|
Read 1 MB sequentially from disk|    20,000,000   ns|   20,000 us   20 ms  80x memory, 20X SSD|
Send packet CA->Netherlands->CA|    150,000,000   ns|  150,000 us  150 ms

http://research.google.com/people/jeff/

## Internet Structure

![internet topology 2011](internet-topology-2011.png)

Autonomous Network -> Gix ->

## Query

Edit distance

spelling(dict, corpus)

indexation corpus, stemming (enlever prefix, suffix)

[Snowball](http://snowball.tartarus.org/texts/introduction.html) <- algorithme stemming

## Query processing

- [inverted index](https://www.elastic.co/guide/en/elasticsearch/guide/current/inverted-index.html) ( used in elasticsearch)
- extract terms
- 30% of news articles are generated by machine
- [google inception v3](https://codelabs.developers.google.com/codelabs/cpb102-txf-learning/index.html?index=..%2F..%2Findex#0)
- build posting list (heard of inverted index)
- generate weight
- get tf = terms frequency
- PageRank
- Boolean Query
    - retrive bit sets
- Scoring
    - [Vector Space Model](https://zh.wikipedia.org/zh-cn/%E5%90%91%E9%87%8F%E7%A9%BA%E9%96%93%E6%A8%A1%E5%9E%8B)
    - Metadata
    - Custom scorer
- Generate the SERP

# The Challenges of Big Data

## Server Traffic

- 3 millard request per day for Twitter in 2010
- 75% requests are API calls
- use caching, separate domain for date and pool connections to DBs and such
- Break large system into smaller system, [microservice](https://zh.wikipedia.org/zh-cn/%E5%BE%AE%E6%9C%8D%E5%8B%99)
- Loosely coupled services
- desgin by interface
- aim to language agnostic
- don't reinvent the wheel, example [REST Vs SOAP](https://stackoverflow.com/questions/19884295/soap-vs-rest-differences)
- json/rest difficult to evolve


Maybe the future of REST API
- Google Protocol Buffer, GRPC
- (Facebook) Apache thrift
- Apache Avros

[comparison](https://fr.slideshare.net/IgorAnishchenko/pb-vs-thrift-vs-avro)

Stateless Service
- interest point, second request can be made to different machine, transperant to users

The more machine, more failures
- less failure, neet to automate tasks
    - deployment
    - configuration
    - recovery
- coordination machines, coordination is mandatory
    - the chubby lock service for loosely-coupled distributed systems
    - hadoop, yahoo -> linkedin(25% knows hadoop) vs viadeo(2 people know hadoop)
    - zookeeper, provides a highly available distributed file sys
        - znode can access ..
        - zondes can be persistent or ephemeral
        - zondes can be sequential, a monotonically increasing counter si appended
        - zondes can have watches, watches allow clients to be notified when zonde change
        - ZooKeeper has a simple API
    - [ZooKeeper consistency](https://zookeeper.apache.org/doc/trunk/zookeeperProgrammers.html#ch_zkGuarantees)
    - ZooKeeper use cases
- Storage
    - input(protocol buffer) -> Tensorflow -> output(protocol buffer)
    - 500PB+ data ingested daily at Facebook 2010
    - Forget traditional options
    - 2003 The Google File System -> 2013 Google Colossus
    - Hadoop Distributed File System is a simialr file sys to GFS(Google File Sys)
        - Fault tolerant
        - Hierarchical namespace
        - Large file support
        - Relatational Database (small amount data ok -> if not, too many connection error)
            - option #1 Replication
            - option #2 Sharding(separte data by content, like A-H, I-Q, R-Z)
            - option $3 Sharding+Replication
        - Aomicity Consistency Isolation Duratbility
        - Max 2 of 3: Consisteny, Availablity and Partition Tolerance <- CAP theorem(brewer's theorem), in the filed of distributed file system
        - Basically Available Soft state Eventual Consistency
    - NoSQL
        - non-relational database
    - Cassandra - A Decentralized Structured Storage System Facebook
    - Dynamo: Amazon's Highly Available Key-value Store
    - 2006 Bigtable: A Distributed File Sys
    - the end of 2006, Apache HBase based on Hadoop released, brought by Microsoft, used by Facebook, Yahoo
    - [HBase tuto](http://www.cnblogs.com/raphael5200/p/5229164.html)

