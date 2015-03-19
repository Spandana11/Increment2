package profile.auto.com.autoprofile_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.Calendar;


public class Msg extends BroadcastReceiver {
    Calendar c;
    String state, phno;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        c = Calendar.getInstance();
        if (extras != null) {
            state = extras.getString(TelephonyManager.EXTRA_STATE);

            System.out.println("State............." + state);

            if (state.equals("IDLE") || state == "IDLE") {
                Toast.makeText(context, "state @" + state, 40).show();

                String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                String MyPREFERENCES = "MyPrefs";
                SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                phoneNumber = sharedpreferences.getString("phno", "");
                Toast.makeText(context, phoneNumber, 50).show();
                if (sharedpreferences.getBoolean("lift", false))
                    return;
                if (!sharedpreferences.getBoolean("toggle", false))
                    return;
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("toggle", false);
                editor.commit();
                int mHours = c.get(Calendar.HOUR_OF_DAY);
                int mMins = c.get(Calendar.MINUTE);
                String n22 = String.valueOf(mHours);
                System.out.println("hrs" + n22);
                String n33 = String.valueOf(mMins);
                System.out.println("mins" + n33);
                String s = mHours + ":" + mMins;
                String newval = n22 + n33;
                System.out.println("msg" + newval);

                if (s.length() == 3) {
                    int index = s.indexOf(":");


                    newval = "0" + n22 + "0" + n33;


                }
                if (s.length() == 4) {
                    int index = s.indexOf(":");


                    if (index == 1) {
                        newval = "0" + n22 + n33;
                    } else if (index == 2) {

                        newval = n22 + "0" + n33;
                    }
                }
                System.out.println("updated" + newval);
                System.out.println("going to intent");
                Intent i = new Intent(context, MSGService.class);
                System.out.println("going to intent11111");

                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                System.out.println("after intent");
                System.out.println("@#$%^&*()))) " + newval);
                i.putExtra("num", phoneNumber);
                i.putExtra("tm", newval);

                context.startService(i);

            } else if (state.equals("RINGING")) {
                phno = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                String MyPREFERENCES = "MyPrefs";
                SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("phno", phno);
                editor.putBoolean("lift", false);
                editor.putBoolean("toggle", true);
                editor.commit();
            } else if (state.equals("OFFHOOK")) {
                String MyPREFERENCES = "MyPrefs";
                SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("lift", true);
                editor.commit();
            } else {
                System.out.println("messge not sent");
            }


        }
    }

}
