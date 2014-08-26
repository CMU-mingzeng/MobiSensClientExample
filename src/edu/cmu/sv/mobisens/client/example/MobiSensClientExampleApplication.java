package edu.cmu.sv.mobisens.client.example;

import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.crittercism.app.Crittercism;

import edu.cmu.sv.mobisens.library.managers.MSManager;
import edu.cmu.sv.mobisens.library.managers.MobiSensLibraryApplication;
import edu.cmu.sv.mobisens.library.tools.DeviceManager;


public class MobiSensClientExampleApplication extends Application {



	@Override
	public void onCreate() {
		super.onCreate();
		

		MobiSensLibraryApplication.applicationInit(this);
		
	}




	
}
