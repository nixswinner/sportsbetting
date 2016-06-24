package com.nixontergech.sportsbetting;



import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		setListAdapter(new MyAdapter(Main.this, android.R.layout.simple_list_item_1,
				R.id.txtload,
				getResources().getStringArray(R.array.sites)));
		
	}
	//creating an inner class
	
	class MyAdapter extends ArrayAdapter<String>
	{

		public MyAdapter(Context context, int resource,
				int textViewResourceId, String[] strings) {
			super(context, resource, textViewResourceId, strings);
			// TODO Auto-generated constructor stub
		}
		//get view override method
		//it is called for every row in the list
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row=inflater.inflate(R.layout.listview, parent, false);
			String[]items=getResources().getStringArray(R.array.sites);
			ImageView iv=(ImageView)row.findViewById(R.id.imgError);
			TextView txt=(TextView) row.findViewById(R.id.txtload);
			txt.setText(items[position]);
			
		if(items[position].equals("SportsPesa"))
			{
				iv.setImageResource(R.drawable.sportspesap);
			}
			else if(items[position].equals("EliteBet"))
			{
				iv.setImageResource(R.drawable.elitebetp);
			}
			else if(items[position].equals("Betin"))
			{
				iv.setImageResource(R.drawable.betinp);
			}
			else if(items[position].equals("Kenya RoyalBet"))
			{
				iv.setImageResource(R.drawable.kenyaroyal);
			}
			else if(items[position].equals("JustBet"))
			{
				iv.setImageResource(R.drawable.jusbetp);
			}
			else if(items[position].equals("Betway"))
			{
				iv.setImageResource(R.drawable.betway);
			}
			else
			{
				iv.setImageResource(R.drawable.betyetup);
			}
			
			return row;
		}
		
	}
@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	if (position==0) {
		Toast toast=Toast.makeText(Main.this, "You Choose Sportspesa", 10000);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		Intent intent=new Intent(Main.this, Web.class);
		intent.putExtra("Address", "https://www.sportpesa.com/mobile/#/home");
		startActivity(intent);
		
	} else if(position==1) {
		
		Toast toast=Toast.makeText(Main.this, "You Choose EliteBet", 10000);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		Intent intent=new Intent(Main.this, Web.class);
		intent.putExtra("Address", "http://www.elitebetkenya.com/");
		startActivity(intent);
		
	}
else if(position==2) {
		
		Toast toast=Toast.makeText(Main.this, "You Choose Betin", 10000);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		Intent intent=new Intent(Main.this, Web.class);
		intent.putExtra("Address", "https://mobile.betin.co.ke/Home.aspx");
		startActivity(intent);
	}
else if(position==3) {
	
	Toast toast=Toast.makeText(Main.this, "You Choose KenyaRoyal Bet", 10000);
	toast.setGravity(Gravity.CENTER, 0, 0);
	toast.show();
}
else if(position==4) {
	
	Toast toast=Toast.makeText(Main.this, "You Choose JustBet", 10000);
	toast.setGravity(Gravity.CENTER, 0, 0);
	toast.show();
	Intent intent=new Intent(Main.this, Web.class);
	intent.putExtra("Address", "http://www.justbet.co.ke/");
	startActivity(intent);
}else if(position==5){
	
	Toast toast=Toast.makeText(Main.this, "You Choose Betway", 10000);
	toast.setGravity(Gravity.CENTER, 0, 0);
	toast.show();
	Intent intent=new Intent(Main.this, Web.class);
	intent.putExtra("Address", "https://www.betway.co.ke/");
	startActivity(intent);
}
else
{
	Toast toast=Toast.makeText(Main.this, "You Choose Betyetu", 10000);
	toast.setGravity(Gravity.CENTER, 0, 0);
	toast.show();
	Intent intent=new Intent(Main.this, Web.class);
	intent.putExtra("Address", "https://m.betyetu.co.ke/");
	startActivity(intent);
}
	
	super.onListItemClick(l, v, position, id);
}

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (keyCode==KeyEvent.KEYCODE_BACK) {
		
		AlertDialog.Builder builder=new Builder(Main.this);
		builder.setMessage("Do You Really Want To Exit?");
		builder.setCancelable(false);
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Toast toast=Toast.makeText(Main.this, "Thank you for using this app.(Nixon)", 100000);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				Main.this.finish();
				
			}
		});
		builder.setNegativeButton("NO", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				arg0.cancel();
			}
		});
	builder.show();
	}
	
	return super.onKeyDown(keyCode, event);
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//on menu item Clicked
@Override
public boolean onMenuItemSelected(int featureId, MenuItem item) {
if (item.getItemId()==R.id.about) {
		
	AlertDialog.Builder builder=new Builder(Main.this);
	builder.setMessage("SportsBetting App is an Exclusive app that Enables You to easily switch with ease from one sports betting platform to another\n Increase your betting chances in varied platforms\nDeveloper:@Nixon:0702739804\nEmail:nixswinner@gmail.com");
	builder.setCancelable(true);
	builder.show();
	}
	return super.onMenuItemSelected(featureId, item);
}
}
