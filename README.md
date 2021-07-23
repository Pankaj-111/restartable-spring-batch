# Restart-able Spring Batch
## Features
- **Fault tolerant**
  - **Restart-able**
  - **Retryable**
  - **Skip Function**
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
