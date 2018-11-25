# text-service
A simple ephemeral text message service

### Prerequisites:
    java sdk 8 (or higher)
    maven
   
   ** on a mac, this _usually_ works: ` brew install maven ` 
   
### To Compile and Run:
    $ mvn spring-boot:run
    
### To Run the tests only:
    $ mvn test    
    
### Why did I do it this way?
I work mostly in spring-boot lately.  It is definitely verbose but I'm used to 
that.  The advantage, for me, is that I know I can scale it pretty much as is.
I didn't bother doing anything fancy with the datastorage. By using an H2
in-memory database, you can test and run after one simple git pull. Also makes
swapping out to any RDS a one line config change in the `applcation.properties`
file.

### The limitations
Plenty of messages will live longer than their desired expire time, especially
if they expire times are set below 60 seconds and there is only one sweeper 
running at 15 second intervals.  

### What would be super cool
Having the scheduled sweeper run on the same REST microservice isn't really 
a good idea.  I would separate the sweeper into it's own clustered service and 
tune the thread pools so that we could sweep multiple times a second.  I'd 
also consider moving the "cold storage" messages into a separate data store
rather than a simple table.

### How would you scale?
Configure the service to run as an AWS Beanstalk service and begin tuning 
the autoscaling groups to handle some expected/desired load benchmark. I 
usually shoot for 10K-100K requests a second using multi-regional instances
of headless JMeter tests.  I observe whatever instrumentation logs and alerts
I have available after running some sucessful tests.  I pay close attention to 
CPU and memory usage.  In this particular application, I would pay attention to
relational database write/insert constraints and look at either maximizing my 
RDS capabilities or consider some NoSQL options. 
