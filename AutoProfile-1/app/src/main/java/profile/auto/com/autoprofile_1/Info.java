package profile.auto.com.autoprofile_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Info extends Activity {
	
	Button addp,upro,deletepro,delall,act,dct;
	EditText et;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		/*this.title.setText("Info");
		this.icon.setImageResource(R.drawable.menu_info);*/
		setContentView(R.layout.info);
		addp=(Button)findViewById(R.id.ap);
		upro=(Button)findViewById(R.id.up);
		deletepro=(Button)findViewById(R.id.dp);
		delall=(Button)findViewById(R.id.dap);
		act=(Button)findViewById(R.id.act);
		dct=(Button)findViewById(R.id.dct);
		et=(EditText)findViewById(R.id.pro);
	addp.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			et.setText("In add profile page enter your profilename and a message. If you doesn't answer to call message will be send to the caller automatically during the profiel activated time");
		}
	});
	upro.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			et.setText("After adding your profile click on your profile in list so you will be navigated to Updation page.For more details click on help button in that screen");
		}
	});
	deletepro.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		et.setText("To dlete your profile click on menu select on delete button you will be navigated to deletion page. When you click on profile name it will be deleted");
	}
});
	delall.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			et.setText("To dleteall your profiles click on menu select on deleteall button all your profile get deleted");
		}
	});

act.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			et.setText("After updating the profile, to activate your profile click on Activate");
		}
	});
dct.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		et.setText("Incase if you don't need your profile run click on deactivate");
	}
});



	}

}
