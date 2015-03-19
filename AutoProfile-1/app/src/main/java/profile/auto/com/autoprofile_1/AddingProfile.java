package profile.auto.com.autoprofile_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import DBHandler.DBHandler;


public class AddingProfile extends Activity {
	Button b;	
	EditText ed,ed1,ed2,ed3;
	DBHandler db1; 	
	String s,s1,s2,sp,sm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		//this.title.setText("Add profile");
		//this.icon.setImageResource(R.drawable.);
		setContentView(R.layout.addprofile);
		db1=new DBHandler(this);

		ed=(EditText)findViewById(R.id.editText1); 
		ed3=(EditText)findViewById(R.id.editText4);		
		b=(Button)findViewById(R.id.save);		        
	   
	    
	  
	    b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				s=ed.getText().toString();  
				sm=ed3.getText().toString();
				if(s.equals("")||sm.equals("")){
					Toast.makeText(getApplicationContext(), "Enter profile name and Message", 80).show();
				}else{
					validate(s);
					
				}
				
		}

			
		}); 
	    
		
	}

	private void validate(String s) {
		// TODO Auto-generated method stub
		db1=new DBHandler(this);  
		ArrayList<String> i=this.db1.info(s);
		if(i.size()==0){
			insertdata();
		}else{
		
			Toast.makeText(getApplicationContext(), "Profile name already exists", 80).show();
		}
	}
	private void insertdata() {
		// TODO Auto-generated method stub
		
		System.out.println("second.....!!!!!");
		db1=new DBHandler(this);    
		String status="notupdated";
		String ttime="notupdated";
		String ftime="notupdated";
		String sftime="notupdated";
		String sttime="notupdated";
		this.db1.insertdata1(s,ttime,ftime,sftime,sttime,status,sm); 
		Toast.makeText(getApplicationContext(), "insertion completed", 40).show();		
		Intent in = new Intent(this,AutoprofileActivity.class);
		startActivity(in);
		finish();
		
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
	
	@Override 
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent in = new Intent(this,AutoprofileActivity.class);
		startActivity(in);
		finish();
		
	}

	   


}
