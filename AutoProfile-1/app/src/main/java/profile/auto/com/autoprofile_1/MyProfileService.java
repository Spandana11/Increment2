package profile.auto.com.autoprofile_1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DBHandler.DBHandler;

public class MyProfileService extends Service {
	int newintvalr;
	Calendar c;
	int mHours;
	DBHandler db1;
	int mMins;
	AudioManager maudio;
	int j;
	
	String pname,rtype;
	String newval,msg;
	int i=100;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("serviceeeeeeeeeeeeeeeeeeee");
		return null;
		
	}
	
	@Override
	public void onCreate() {  
		// TODO Auto-generated method stub
		
			
		c=Calendar.getInstance();   
	    
		
		
		 db1=new DBHandler(this);
	//	maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
		

		super.onCreate();
	}

	

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		reserviceCalling();
		
	}
	


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	
	System.out.println("Destroying service  ");
	
	stopAlarm();
	Intent in1 = new Intent(this, AutoprofileActivity.class);
	in1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	startActivity(in1);
	
	System.out.println("SERVICE Completed");
	
	
	
	
 	}
	private void stopAlarm() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MyProfileService.this, Broadcastimplimentation.class);
		  PendingIntent sender = PendingIntent.getBroadcast(this, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		//PendingIntent pendingIntent =  PendingIntent.getBroadcast(bInsulinReminder.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		  sender.cancel();
		  AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(sender);

	}

	private void reserviceCalling() {
		// TODO Auto-generated method stub
			c.add(Calendar.MINUTE,1);
			mHours = c.get(Calendar.HOUR_OF_DAY);
	    	mMins=c.get(Calendar.MINUTE);
	    	String n22=String.valueOf(mHours);
	    	String n33=String.valueOf(mMins);
	    	String s=mHours+":"+mMins;
	    	 newval=n22+n33;
	    	 if(s.length()==3)
				{
				int index=s.indexOf(":");
				
				
				newval="0"+n22+"0"+n33;
			
					
				} 
				if(s.length()==4)
				{
				int index=s.indexOf(":");
				
				
				if(index==1)
				{
					newval="0"+n22+n33;
				}
				else if(index==2){
					
					newval=n22+"0"+n33;  
				}
	    	//int newintval=Integer.parseInt(newval); 
				}    	
	    	retraiveFromTimesAndToTimes(newval);
		  
	}

	private void retraiveFromTimesAndToTimes(String newval2) {
		// TODO Auto-generated method stub
	String nw=newval2;
		System.out.println("v@@@@"+nw);
		Toast.makeText(getApplicationContext(), "@@@"+nw, 60).show();
		
		List<String>alistaa=this.db1.activatepro(nw);
		
		if(alistaa.size()==0)
		{
			System.out.println("service run until values match");
			System.out.println("Normal Profile");
			 //maudio.setRingerMode(AudioManager.MODE_CURRENT);
			
		}else
		{
				for(int i=0;i<alistaa.size();i++){
					
					 pname = alistaa.get(0);
					 rtype = alistaa.get(1);
					Toast.makeText(getApplicationContext(), "@#@#"+rtype, 60).show();
					System.out.println("@@@@@@@@@###########$$$$$$$$$$$%%%%%%%%%%%"+rtype);
					
				}
				if(rtype=="NORMAL"||rtype.equals("NORMAL")){
					// maudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					System.out.println("Normal");
				}
				else if(rtype=="SILENT"||rtype.equals("SILENT")){
					// maudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					System.out.println("silent");
				
				}else if(rtype=="VIBRATE"||rtype.equals("VIBRATE"))
				{
					
					System.out.println("vibrate");
				    // maudio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);						
				}
				
				ArrayList<String> sttom=new ArrayList<String>();
				
				sttom=db1.dategetSttime(pname);
				
				if(sttom.size()>0)
				{
					String extime=sttom.get(0);
					
					System.out.println("ex timeeeeeeeeeeeeeeeeeeee :"+extime);
					int newintval3=Integer.parseInt(newval2); 
					int veri=Integer.parseInt(extime);
					
					if(newintval3>veri)
					{
						System.out.println("Normal Profile");
						// maudio.setRingerMode(AudioManager.MODE_CURRENT);
					}   
					
					
				}


            Intent intent = new Intent(this, Broadcastimplimentation.class);

            intent.putExtra("auto_pro_running", "started");
            PendingIntent sender = PendingIntent.getBroadcast(this, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
				
					
				
				
		}
		
	
	
		
	}  


}