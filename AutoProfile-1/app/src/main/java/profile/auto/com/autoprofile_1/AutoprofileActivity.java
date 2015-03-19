package profile.auto.com.autoprofile_1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DBHandler.DBHandler;

public class AutoprofileActivity extends Activity {
    ListView lv;
    ArrayAdapter<String> a;
    DBHandler db1;
    View row=null;
    Button b1,b2;
    String verificationvalue;
    Calendar c;
    TextView tv,tv1,ct,ctt;
    List<String> mylist = new ArrayList<String>();
    SharedPreferences sp;
    int servicecode = 100;
    SharedPreferences.Editor edt;
    String s;
    ArrayList<String> asdd = new ArrayList<String>();
    ArrayList<String> asdd1 = new ArrayList<String>();
    List<String> lst;
    String pname;
    TextView clt;
    ArrayList<String> alist = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("1");
        setContentView(R.layout.main);

        System.out.println("1");
        /*title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(AutoprofileActivity.this, Info.class);

                startActivity(intent);

            }
        });*/
        b1 = (Button) findViewById(R.id.b1);
        //tv1=(TextView)findViewById(R.id.count);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AutoprofileActivity.this, AddingProfile.class);

                startActivity(intent);
                finish();
            }
        });

        b2 = (Button) findViewById(R.id.ac);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AutoprofileActivity.this, MyProfileService.class);

                startService(intent);


            }
        });


        lv = (ListView) findViewById(R.id.listView1);
        sp = this.getSharedPreferences("MySharedPref", MODE_APPEND);


        edt = sp.edit();


        db1 = new DBHandler(this);
        lst = this.db1.selectdata();
        System.out.println("3");

        for (int j = 1; j < lst.size(); j = j + 2) {
            asdd1.add(lst.get(j));
        }

        for (int j = 0; j < lst.size(); j = j + 2) {
            System.out.println("first page" + lst.get(j));
            asdd.add(lst.get(j));


            System.out.println("t1");
            CustomList cl = new CustomList();
            lv.setAdapter(cl);
            lv.setTextFilterEnabled(true);
            System.out.println("t2");
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View v, int p,
                                        long arg3) {
                    // TODO Auto-generated method stub
                    //	Toast.makeText(getApplicationContext(), "l"+asdd.get(p),40).show();
                    String s = asdd.get(p);
                    edt.putString("ref", s);
                    edt.commit();
                    Intent i = new Intent(AutoprofileActivity.this, UpdatePro.class);
                    System.out.println("t3");
                    startActivity(i);
                    finish();
                }

            });

        }
        int i = asdd.size();
        String aa = String.valueOf(i);
        System.out.println("WWWWWWWWWEEEEEEEEEEEEEEEEE" + aa);
        //	tv1.setText(aa);
        //registerForContextMenu(row);

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
            System.out.println("data object closing");
            db1.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in = getMenuInflater();
        in.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int val = item.getItemId();
        db1 = new DBHandler(this);
        switch (val) {
            case R.id.item1:
                Intent it = new Intent(AutoprofileActivity.this, Delete.class);
                startActivity(it);
                finish();
                break;
            case R.id.item2:
                this.db1.delete();
                stopService(new Intent(this, MyProfileService.class));
                servicecode = 100;
                Intent in1 = new Intent(this, AutoprofileActivity.class);
                startActivity(in1);
                finish();
                // onStop();
                break;

            default:
                break;
        }
        return true;
    }

    class CustomList extends BaseAdapter {

        @Override
        public int getCount() {
            return asdd.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li = getLayoutInflater();
            row = li.inflate(R.layout.main1, null);
            ct = (TextView) row.findViewById(R.id.ct);
            ctt = (TextView) row.findViewById(R.id.ctt);
            ct.setText(asdd.get(position));
            String yyy = asdd1.get(position);
            System.out.println("value :" + yyy);
            if (yyy.equals("notupdatedtonotupdated")) {
                yyy = "Not yet updated !!";
            }
            ctt.setText(yyy);
            return row;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}