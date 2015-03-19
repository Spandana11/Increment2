package profile.auto.com.autoprofile_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import DBHandler.DBHandler;

public class Delete extends Activity {
    ListView lv;
    List<String> lst;

    DBHandler db1;
    String selectVal;
    ArrayList<String> alist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.delete);
        // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
        lv = (ListView) findViewById(R.id.lst);
        db1 = new DBHandler(this);
        lst = this.db1.selectdata();
        for (int j = 0; j < lst.size(); j = j + 2) {
            System.out.println("first page" + lst.get(j));
            alist.add(lst.get(j));
        }
        //ArrayAdapter<String> mrr = new ArrayAdapter<String>(Delete.this,android.R.layout.simple_list_item_1, alist);
        Myclass mrr = new Myclass();
        lv.setAdapter(mrr);
            /*lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					 selectVal = (String) lv.getAdapter().getItem(position);
					//deleteval();
					
				}

				
			});*/
    }

    class Myclass extends BaseAdapter {

        @Override
        public int getCount() {
            return alist.size();
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
            View a = null;
            LayoutInflater li = getLayoutInflater();
            a = li.inflate(R.layout.inflate, null);
            final TextView nt = (TextView) a.findViewById(R.id.nt);
            //ImageView iv=(ImageView)findViewById(R.id.iv);
            nt.setText(alist.get(position));
            nt.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    String ss = nt.getText().toString();
                    System.out.println("ss value is: " + ss);
                    deleteval(ss);
                }
            });


            return a;
        }

    }

    private void deleteval(String ss) {
        // TODO Auto-generated method stub

        // Toast.makeText(getApplicationContext(), ""+selectVal, 40).show();
        this.db1.deleteone(ss);

        stopService(new Intent(this, MyProfileService.class));
        int servicecode = 100;
        System.out.println("alist size" + alist.size());
        if (alist.size() == 1) {
            Intent in = new Intent(this, AutoprofileActivity.class);
            startActivity(in);
            finish();
        } else {
            Intent in = new Intent(this, Delete.class);
            startActivity(in);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent in = new Intent(this, AutoprofileActivity.class);
        startActivity(in);
        finish();
    }

}
