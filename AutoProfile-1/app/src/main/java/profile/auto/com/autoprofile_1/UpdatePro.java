package profile.auto.com.autoprofile_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

import DBHandler.DBHandler;

public class UpdatePro extends Activity {
    EditText ed1, ed2, ed3, ed4;
    Button b4, b5;
    Button b1, b2;
    DBHandler db1;
    String statustone;
    String t1, t2, t3;
    String s, s1, s2, sp1;
    Button b;
    int ifr, ito;
    SharedPreferences sp;
    String t;
    Calendar c;
    int d1, d2;
    String fin, sin, fin1, sin1, sm, mess;
    private int mHours;
    private int mMins;
    ToggleButton tb;
    ArrayList<String> alltimings = new ArrayList<String>();

    ArrayList<String> a22 = new ArrayList<String>();
    private static final int TIME_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID_F = 1;
    boolean CheckboxPreference;
    String ListPreference;

    String editTextPreference;
    String ringtonePreference;
    String secondEditTextPreference;
    String customPref;
    String ar1[], ar2[], totm, frmtm;
    RadioButton rb1, rb2, rb3;
    int ft, tt;
    Intent it;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatedpro);

//        this.title.setText("Update ?");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        /*title.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder aa = new AlertDialog.Builder(UpdatePro.this);
                aa.setTitle("Updation Help !!");
                aa.setMessage("In order to run your profile automaticaly set from time and to time and the ringing mode of your mobile");
                aa.setNegativeButton("Back", null);
                aa.show();


            }
        });*/
        //this.icon.setImageResource(R.drawable.menu_UpdatePro);
        getPref();

        sp = this.getSharedPreferences("MySharedPref", MODE_APPEND);

        t = sp.getString("ref", null);
        System.out.println("!!!!!!!!!!!!!!!!" + t);
        b4 = (Button) findViewById(R.id.button4);
        rb1 = (RadioButton) findViewById(R.id.radio0);
        rb2 = (RadioButton) findViewById(R.id.radio1);
        rb3 = (RadioButton) findViewById(R.id.radio2);

        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);

        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        c = Calendar.getInstance();

        mHours = c.get(Calendar.HOUR_OF_DAY);
         mMins = c.get(Calendar.MINUTE);

        db1 = new DBHandler(this);
        a22 = db1.dateget(t);
        if (a22.size() == 0) {
            updatetime();
            updatetime1();
        } else {
            ed2.setText(a22.get(0));
            ed3.setText(a22.get(1));

        }


        b4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                t1 = ed2.getText().toString();
                t2 = ed3.getText().toString();


                if (t1.equals("notupdated") && t2.equals("notupdated")) {
                    Toast.makeText(getApplicationContext(), "please updated from and to time", Toast.LENGTH_LONG).show();
                } else if (t1.equals("notupdated")) {
                    Toast.makeText(getApplicationContext(), "please updated From-Time ", Toast.LENGTH_SHORT).show();
                } else if (t2.equals("notupdated")) {
                    Toast.makeText(getApplicationContext(), "please updated To-Time", Toast.LENGTH_SHORT).show();
                } else {

                    String newval = t1.replace(":", "");
                    String newval1 = t2.replace(":", "");
                    //Toast.makeText(getApplicationContext(), "f time"+newval, 50).show();
                    //Toast.makeText(getApplicationContext(), "t time"+newval1, 50).show();
                    int i = Integer.parseInt(newval);
                    int j = Integer.parseInt(newval1);

                    sm = ed4.getText().toString();
                    t3 = ed1.getText().toString();

                    if (rb1.isChecked()) {
                        statustone = "SILENT";
                    } else if (rb2.isChecked()) {
                        statustone = "VIBRATE";
                    } else {
                        statustone = "NORMAL";

                    }


                    update();

                }

            }

        });


        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String data1 = mHours + "-" + mMins;

                showDialog(TIME_DIALOG_ID);
            }

        });
        b2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String data2 = mHours + "-" + mMins;

                showDialog(TIME_DIALOG_ID_F);
            }

        });


        retriving();


    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        getPref();
        super.onStart();
    }

    private void getPref() {
        // TODO Auto-generated method stub
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());


        editTextPreference = prefs.getString("editTextPref",
                "Nothing has been entered");
        ringtonePreference = prefs.getString("ringtonePref",
                "DEFAULT_RINGTONE_URI");

        // Get the custom preference
        SharedPreferences mySharedPreferences = getSharedPreferences(
                "myCustomSharedPrefs", Activity.MODE_PRIVATE);
        customPref = mySharedPreferences.getString("myCusomPref", "");

    }

    private void update() {
        // TODO Auto-generated method stub

        db1 = new DBHandler(this);
        ar1 = t1.split(":");
        ar2 = t2.split(":");
        frmtm = ar1[0] + ar1[1];
        totm = ar2[0] + ar2[1];
        ifr = Integer.parseInt(frmtm);
        ito = Integer.parseInt(totm);
        final String EXP = ":";


        if (t1.length() == 3) {
            int index = t1.indexOf(EXP);


            frmtm = "0" + ar1[0] + "0" + ar1[1];
            //frmtm="0"+ar1[0]+ar1[1];


        }
        if (t1.length() == 4) {
            int index = t1.indexOf(EXP);

            if (index == 1) {
                frmtm = "0" + ar1[0] + ar1[1];
            } else if (index == 2) {
                frmtm = ar1[0] + "0" + ar1[1];
            }


        }


        if (t2.length() == 3) {
            int index = t2.indexOf(EXP);


            totm = "0" + ar2[0] + "0" + ar2[1];


        }
        if (t2.length() == 4) {
            int index = t2.indexOf(EXP);

            if (index == 1) {
                totm = "0" + ar2[0] + ar2[1];
            } else if (index == 2) {
                totm = ar2[0] + "0" + ar2[1];
            }


        }


        d1 = Integer.parseInt(frmtm);
        d2 = Integer.parseInt(totm);

			
			  
			
			
			
			/*if(ifr==ito)
			{
			Toast.makeText(getBaseContext(), "From and To Time should not be same", 60).show();
			}
			else
				if(ifr>ito)
				{
				Toast.makeText(getBaseContext(), "TO Time should greaterthan from time.", 60).show();
				}else*/
        if (d1 == d2) {
            Toast.makeText(getBaseContext(), "From and To Time should not be same", Toast.LENGTH_SHORT).show();

        } else if (d1 > d2) {
            //Toast.makeText(getBaseContext(), "TO Time should greaterthan from time.", 60).show();
            AlertDialog.Builder ab = new AlertDialog.Builder(this);

            ab.setTitle("Error!!!");
            ab.setMessage("1:To-Time must be Greaterthan From-time \n2:To-Time limit up to 23:59 so please select within the limit.");
            ab.setNegativeButton("Back", null);
            ab.show();


        } else {

            upMain();

        }
    }

    private void updateOnlyTypeAndMesssage() {
        db1 = new DBHandler(this);
        this.db1.updateOnly(t3, statustone, sm);
        Toast.makeText(getApplicationContext(), "type and msg updated", Toast.LENGTH_SHORT).show();
        Intent ii = new Intent();
        setResult(RESULT_OK);
        finish();
    }

    public void upMain() {

        db1 = new DBHandler(this);

        alltimings = db1.infoTimings(t3, frmtm, totm);

        System.out.println("i dd :" + alltimings.size());

        if (alltimings.size() != 0) {

            Toast.makeText(getApplicationContext(), "Schedule already fixed", Toast.LENGTH_SHORT).show();
        } else {

            //Toast.makeText(getBaseContext(), "a"+frmtm, 60).show();
            //Toast.makeText(getBaseContext(), "b"+totm, 60).show();
            // String ft1=frmtm.replace(":", newChar)

            String ss = frmtm.substring(0, 2);
            System.out.println(ss);


            String ss1 = frmtm.substring(2, 4);
            System.out.println(ss1);


            String ne = ss + ":" + ss1;

            System.out.println(ne);

            String sss = totm.substring(0, 2);

            String sss1 = totm.substring(2, 4);

            String nee = sss + ":" + sss1;

            System.out.println(ne);


            this.db1.update(ne, nee, t3, frmtm, totm, statustone, sm);


            Toast.makeText(getApplicationContext(), "updation completed", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(this, AutoprofileActivity.class);
            startActivity(in);
            finish();
        }
    }

    private void retriving() {
        // TODO Auto-generated method stub

        ArrayList<String> p = this.db1.info(t);


        String Ttime = p.get(1);
        String Ftime = p.get(2);
        System.out.println("T time :" + Ttime);
        System.out.println("F time :" + Ftime);

        int i = Ttime.length();
        int j = Ftime.length();

        System.out.println("length :" + i);
        System.out.println("length :" + j);

        if (i == 3) {
            System.out.println("" + Ttime.substring(0));
        }
        if (j == 3) {
            System.out.println("" + Ttime.substring(0, 1));
        }
        for (int i1 = 0; i1 < p.size(); i1++) {

            if (p.get(1).length() == 3) {
                fin = p.get(1).subSequence(0, 1).toString();


                sin = p.get(1).substring(1, p.get(1).length()).toString();

            }

            if (p.get(2).length() == 3) {

                fin1 = p.get(2).substring(0, 1).toString();

                sin1 = p.get(2).substring(1, p.get(2).length()).toString();


            } else {

                fin1 = p.get(2).substring(0, 2).toString();

                sin1 = p.get(2).substring(2, p.get(2).length()).toString();

            }
            mess = p.get(3).toString();


        }


        ed1.setText(p.get(0));
        String ftt = fin + ":" + sin;
        String ttt = fin1 + ":" + sin1;
        //ed2.setText(ftt);
        //ed3.setText(ttt);
        ed4.setText(mess);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(UpdatePro.this, timeval, mHours, mMins, true);
            case TIME_DIALOG_ID_F:
                return new TimePickerDialog(UpdatePro.this, timeval1, mHours, mMins, true);
        }
        return null;

    }


    OnTimeSetListener timeval = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            mHours = hourOfDay;
            mMins = minute;
            updatetime();
        }

    };

    OnTimeSetListener timeval1 = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            mHours = hourOfDay;
            mMins = minute;
            updatetime1();
        }

    };


    private void updatetime1() {
        // TODO Auto-generated method stub
        String val1 = mHours + ":" + mMins;
        System.out.println("upval1 time" + val1);
        System.out.println("@@@@@@@@@ time ########" + val1);
        ed3.setText(val1);

    }

    private void updatetime() {
        // TODO Auto-generated method stub
        String val1 = mHours + ":" + mMins;
        System.out.println("upval1 time" + val1);
        ed2.setText(val1);

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db1 != null) {
            db1.close();
        }
        finish();

    }


}
