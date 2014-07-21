MobiSensClientExample
=====================

This is an example application, which demonstrates the usage of the CMU MobiSens Library. 

To quickly test the basic sensing and querying functionalities just clone this git repository and then install the Android application on a mobile device. To use MobiSens Library in your own project, follow the instructions below.


Use MobiSens in an existing app
------------

### Setup

Copy the following files and text from this MobiSensClientExample project to an existing app:

- jar files in the lib/ directory  
- lines in AndroidManifest.xml, which are annotated with <!-- MobiSensLibrary ... -->

Initialize the library by adding the following line:

    Configurations.setUsernameAndPassword(context, <username>, <password>);



### Sensing

In order to start collecting data, add the following line:

    ContextManager.startSensing(context);
    
To stop the data collection:
    
    ContextManager.stopSensing(context);
    
To check whether the system is currently collecting data:
    
    Configurations.isSensing(thisActivity)


Sensing is 


### Configuration

By default the sampling interval is set to 60 seconds. This can be updated by calling:

    Configurations.setSensingInterval(context, sensingIntervalInMillis);


To check the current sensing interval call:

    Configurations.getSensingInterval(context)





### Query data

- Activity Sessions
- Activity Stats
- 


### NOTE
If you find an mistake in this documentation, feel free to update/correct it. 

