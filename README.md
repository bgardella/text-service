# text-service
A simple ephemeral text message service

### Prerequisites:
    java sdk 8 (or higher)
    maven
   
### To Compile and Run:
    $ mvn spring-boot:run
    
### Why did I do it this way?
I work mostly in spring-boot lately.  It is definitely verbose but I'm used to 
that.  

### The limitations
Plenty of messages will live longer than their desired expire time, especially
if they expire times are set below 60 seconds and there is only one sweeper 
running at 60 second intervals.  



### What would be super cool
Having the scheduled sweeper run on the same REST microservice isn't really 
a good idea.  I would seperate the sweeper into it's own clustered service and 
configure some thread pools so that we could sweep multiple times a second.  

### How would you avoid the fail whale?
Configure the service to run as an AWS Beanstalk service and begin tuning 
the autoscaling groups to handle some expected load.
