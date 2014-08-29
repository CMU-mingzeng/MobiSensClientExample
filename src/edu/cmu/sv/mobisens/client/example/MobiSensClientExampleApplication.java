package edu.cmu.sv.mobisens.client.example;

import android.app.Application;
import edu.cmu.sv.mobisens.library.api.ContextManager;


public class MobiSensClientExampleApplication extends Application {



	@Override
	public void onCreate() {
		super.onCreate();
		
		ContextManager.init(this, "mobisensclientexample", "just4now");
		
	}




	
}
