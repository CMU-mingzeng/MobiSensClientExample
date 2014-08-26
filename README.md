MobiSensClientExample
=====================

This is an example application, which demonstrates the usage of the CMU MobiSens Library. 

To quickly test the basic sensing and querying functionalities just clone this MobiSensClientExample git repository and the [MobiSensLibraryProject](https://github.com/CMU-mingzeng/MobiSensLibraryProject) git repository. Check if the [library references](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject) of the MobiSensClientExample are correct and then install the MobiSensClientExample Android application on a mobile device. To use MobiSens Library in your own project, follow the instructions below.


Use MobiSens in an existing app
------------


Let's assume that an app called **TargetApp** wants to integrate the MobiSens Library functionality. In the following we decribe integration in details.

### Setup

- clone [MobiSensLibraryProject](https://github.com/CMU-mingzeng/MobiSensLibraryProject) into your workspace
- set up [library references](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject) in the **TargetApp** by adding **MobiSensLibraryProject** into the list of refered libraries
- copy lines in `AndroidManifest.xml` of **MobiSensClient** to the `AndroidManifest.xml` of **TargetApp**, which are annotated with `<!-- MobiSensLibrary ... -->`
- change ``edu.cmu.sv.mobisens.client.example`` on the following lines of the Manifest to match your app's package name: 
	- ``<permission android:protectionLevel="signature" android:name="edu.cmu.sv.mobisens.client.example.permission.C2D_MESSAGE" />``
	- ``<uses-permission android:name="edu.cmu.sv.mobisens.client.example.permission.C2D_MESSAGE" />``
	- ``<category android:name="edu.cmu.sv.mobisens.client.example" />``

Initialize the library by adding the following into your [Application class](http://developer.android.com/reference/android/app/Application.html):

- MobiSensLibraryApplication.applicationInit(Application application);
- Configurations.setUsernameAndPassword(Context context, <username>, <password>);

**NOTE: Please request an username and password for your application.**


### Sensing

In order to start collecting data, add the following line:

    ContextManager.startSensing(context);
    
To stop the data collection:
    
    ContextManager.stopSensing(context);
    
To check whether the system is currently collecting data:
    
    Configurations.isSensing(context)



### Configuration

By default the sampling interval is set to 60 seconds. This can be updated by calling:

    Configurations.setSensingInterval(context, sensingIntervalInMillis);


To check the current sensing interval call:

    Configurations.getSensingInterval(context)



### Query data

#### Activity sessions

An **activity session** is defined as a time segment, in which the user performed the same activity (e.g. from 10:00 to 10:15 the user was running). The get a chronological list of activity sessions call:

    QueryManager.getActivitySessions(Context context, MSHandler msHandler, Date sessionsFromTime, Date sessionsToTime) 

Through the `MSHandler` you can define the callback function `onResults(List results)`, which can be used to consume the results. `sessionsFromTime` and `sessionsToTime` defines the time period of interest (e.g., search for all activity session, which happened today between 10:00 to 11:00).

The following function sets `sessionsToTime` to the current time:

    QueryManager.getActivitySessions(Context context, MSHandler msHandler, Date sessionsFromTime)

And through following function you obtain all activity sessions, which happened in the last 24 hours:

    QueryManager.getActivitySessions(Context context, MSHandler msHandler)


NOTE: All queries return a maximum of 100 entries (e.g. activity sessions) to avoid overloading the server.


#### Activity summary
Often we are interested in only a summary of user activities (e.g., how many minutes did the user walked today?). The following call returns a list of activities and the total amount of time the user performed them:
    
    QueryManager.getActivitySummary(Context context, MSHandler msHandler, Date statsFromTime, final Date statsToTime)
	

Similar to the activity sessions, we are providing the following functionalities:

    QueryManager.getActivitySummary(Context context, MSHandler msHandler, Date statsFromTime)
    QueryManager.getActivitySummary(Context context, MSHandler msHandler)




### NOTE
If you find an mistake or something missing in this documentation, feel free to update/correct it or let us know.

