package profile.auto.com.autoprofile_1;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcastimplimentation extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try {


			
		     Toast.makeText(context, "...Service Running.....", Toast.LENGTH_LONG).show();
		    } 
		     catch (Exception e)
		    {
		     Toast.makeText(context,"There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
		     e.printStackTrace();
		 
		    }
		
		
		
	}

}
