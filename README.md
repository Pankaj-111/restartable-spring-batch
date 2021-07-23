# Restart-able Spring Batch
The batch can be used as a template which has some inherent features in it. The most important features are below.
## Features
- **Fault tolerant**: The batch is fault tolerant at a certain level the level can be cofigured. the fault tolerance can be achieved by configuring points below.
  - **Restart-able** : The batch is restartable by default, if the batch fails, the batch can be re started from the prevous state where it was failed.
  - **Retryable** : The number of retry can be configured for **each item per thread** based on a particular **Exception**.
  - **Skip Function**:The number of skips can be configured for **each thread** based on a particular **Exception**.
- **Scalable**
  - **Data Partion** : The data can be break into partitions. the partition are configurable and controlled through configuration file. One single thred will be responsible to process the data in a partion at a time. The max number of thread will be the number of partion. These threads are maintained in a thread pool.
- **API calling support**
  -  Fiegn client integrated
- **Kafka support**
  - Kafka integrated  
- **Spring Data support**
  - Spring Data JPA
  - Spring Data JDBC
- Monitoring
