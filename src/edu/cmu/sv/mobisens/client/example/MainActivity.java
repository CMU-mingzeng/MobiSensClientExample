package edu.cmu.sv.mobisens.client.example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import edu.cmu.sv.mobisens.library.api.Configurations;
import edu.cmu.sv.mobisens.library.api.ContextManager;
import edu.cmu.sv.mobisens.library.api.QueryManager;
import edu.cmu.sv.mobisens.library.query.MSHandler;
import edu.cmu.sv.mobisens.library.query.MSObject;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {


	Activity thisActivity;
	
	ToggleButton toggleAlarmOnOff;
	Button buttonAlarmStatus;
	TextView textViewInfo;
	
	
	private static String getTimeFormat(long timeInMillis){
		return "" + Math.round(timeInMillis / 1000 / 60.0 * 10.0) / 10.0;		
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		thisActivity = this;
		
		
		
		
		
		
		Configurations.setUsernameAndPassword(thisActivity, "mobisensclientexample", "just4now");
		
		
		
		
		toggleAlarmOnOff = (ToggleButton) findViewById(R.id.toggleAlarmOnOff);
		buttonAlarmStatus = (Button) findViewById(R.id.buttonAlarmStatus);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		
		toggleAlarmOnOff.setChecked(Configurations.isSensing(thisActivity));
		
		
		toggleAlarmOnOff.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked){
					ContextManager.startSensing(thisActivity); //API Call
				}
				else{
					ContextManager.stopSensing(thisActivity); //API Call 
				}
				
			}
		});
		
		
		 
		
		
		buttonAlarmStatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				StringBuffer sb = new StringBuffer();
				sb.append(new Date()).append("\n");
				sb.append("Sensing activated: ").append(Configurations.isSensing(thisActivity)).append("\n");
				sb.append("Sampling rate (ms): ").append(Configurations.getSensingInterval(thisActivity)).append("\n");
			
				
				textViewInfo.setText(sb.toString()); 
				
				
				
				
				/*
				 * Get activities in a chronological order
				 */
				Calendar cal = Calendar.getInstance();
//				cal.add(Calendar.HOUR_OF_DAY, -1);
				cal.add(Calendar.DAY_OF_YEAR, -1);
				
				Date startActivitySessions = cal.getTime();
				
				
				QueryManager.getActivitySessions(thisActivity, new MSHandler() {
					
					@Override
					public void onResults(List results) {
						final StringBuffer sb = new StringBuffer();
						sb.append("RECENT ACTIVITIES \n");
						for(Object o: results){
							
							MSObject activitySession = (MSObject) o;
							
							sb	.append(activitySession.getStartTs())
								.append(" - ")
								.append(getTimeFormat(activitySession.getEndTs().getTime() - activitySession.getStartTs().getTime()))
								.append(" min - ")
								.append(activitySession.getName())
								.append("\n");
						}
						
						textViewInfo.setText(textViewInfo.getText() + "\n\n" + sb.toString()); 
					}
				}, startActivitySessions);
				
				
				
				
				
				/*
				 * Get a summary of activities for a given duration
				 */
				cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, -1);
				
				Date startActivityStats = cal.getTime();
//				Date startActivityStats = DateTimeManager.getStartOfToday()
				
				QueryManager.getActivityStats(thisActivity, new MSHandler() {
					
					@Override
					public void onResults(List results) {
						final StringBuffer sb = new StringBuffer();
						sb.append("ACTIVITY STATS \n");
						
						for(Object o: results){
							
							MSObject activitySession = (MSObject) o;
							
							sb	.append(activitySession.getStartTs())
								.append(" - ")
								.append(getTimeFormat(activitySession.getEndTs().getTime() - activitySession.getStartTs().getTime()))
								.append(" min - ")
								.append(activitySession.getName())
								.append(": ")
								.append(getTimeFormat(activitySession.getDurationInMillis()))
								.append("\n");
						}
						
						textViewInfo.setText(textViewInfo.getText() + "\n\n" + sb.toString()); 
					}
				}, startActivityStats);
				
				 
					
				
			}
		});
		
		
		
	}
}
