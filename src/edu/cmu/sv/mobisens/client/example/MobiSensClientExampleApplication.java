package edu.cmu.sv.mobisens.client.example;

import android.app.Application;
import edu.cmu.sv.mobisens.library.managers.MobiSensLibraryApplication;


public class MobiSensClientExampleApplication extends Application {



	@Override
	public void onCreate() {
		super.onCreate();
		

		MobiSensLibraryApplication.applicationInit(this);
		
	}




	
}
