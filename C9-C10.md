## Analyzing Data

- 2004 MapReduce: Simplified Data Processing on Large Clusters
    - Map: Apply a function  to a list, element-wise
    - Reduce: Iterate through a list
    - Google's MR is a generalization(distributed) of this
    - inptut -> Map -> Shuffle -> Reduce -> output

- Data moves slowly on Internet
    - we bring computing to data
    - Data is read accoding to an InputFormat
    - an InputForma splits the data when reading it
    - ideal use of HDFS blocks
    - a Jobtracker dispatches Mappers and Reducers (Yarn)
    - Mappers' output data are written locally
    - This step can be costly when data is volumineux
    - How to sovle it ? run combiners on Mappers' output
    - MapReduce job ends with one file per reducer
    - Mappers and Reducers can do anything
    - MapReduce natively programmed in Java
    - Hadoop Streaming allows for any language
- Pig Latin: A Not-So-Foreign Language for Data Processing
    - Pig Latin is converted to MR jobs
- Facebook Hive, offer HiveQL similart to SQL
- Not everthing can be expressed as MR, and Jobtrack tends to be a bottleneck
- YARN, yet another resource negociator
- Hoya - HBase on YARN
- Need something similar for realtime
    - S4: Distributed Stram Computing Plateform
    - [Storm](https://zh.wikipedia.org/zh-cn/Storm_(%E4%BA%8B%E4%BB%B6%E5%A4%84%E7%90%86%E5%99%A8)) developed by BackType, brought by twitter
        - Nimbus (Jobtracker)
        - Supervisors (TaskTracker)
        - Topology (MapReduce job)
        - Spouts
- Spark
- Parquet.io access to data without contaminer the cache
- Pregel: A System for Large-Scale Graph Processing
    - GraphLab
    Apache Giraph