package edu.cmu.sv.mobisens.client.example;
import android.app.Application;

import edu.cmu.sv.mobisens.library.api.ContextManager;


import org.acra.annotation.*;
@ReportsCrashes(
    formKey="",
    formUri = "https://mobisens.cloudant.com/acra-mobisens/_design/acra-storage/_update/report",
    reportType = org.acra.sender.HttpSender.Type.JSON,
    httpMethod = org.acra.sender.HttpSender.Method.PUT
)



public class MobiSensClientExampleApplication extends Application {



	@Override
	public void onCreate() {
		super.onCreate();
		
		ContextManager.init(this, "mobisensclientexample", "just4now");
		
	}




	
}
