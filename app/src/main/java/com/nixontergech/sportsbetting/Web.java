package com.nixontergech.sportsbetting;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Web extends Activity {
	
	private WebView webBrowser;
	private WebViewClient webClient;
	private ProgressDialog pd;
	private TextView loadtext;
	private TextView errortext;
	private ImageButton btnNext;
	private ImageButton btnBack;
	//private ImageView errorimg;
	private Button errorbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		btnBack=(ImageButton) findViewById(R.id.btnBack);
		btnNext=(ImageButton) findViewById(R.id.btnNext);
		errortext=(TextView) findViewById(R.id.errortxt);
		//errorimg=(ImageView) findViewById(R.id.imgError);
		errorbtn=(Button) findViewById(R.id.errorbtn);
		errorbtn.setVisibility(View.GONE);
		//errorimg.setVisibility(View.GONE);
		errortext.setVisibility(View.GONE);
		//checking the availability of network
		ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean net=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
		boolean wifi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
	
		String webAddress=getIntent().getStringExtra("Address").toString();
		if (wifi||net) {
			
			loadurl(webAddress);
		} else {
			AlertDialog.Builder builder=new Builder(Web.this);
			builder.setMessage("Ooops!!There seems to be no Internet Connections.\nPlease check your connections and try again");
			builder.setCancelable(true);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Web.this.finish();
				  
					
				}
			});
			builder.show();

		}
	
		
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	//method that handles the webview 
	public void loadurl(String url)
	{
		//progress Dialog
		pd=new ProgressDialog(Web.this);
		pd.setMessage("Loading...Please wait....");
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setIndeterminate(true);
		pd.setCancelable(true);
		webBrowser=(WebView) findViewById(R.id.webView);
		webBrowser.getSettings().setJavaScriptEnabled(true);
		webBrowser.getSettings().setDisplayZoomControls(true);
		webClient=new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				pd.show();
				loadtext=(TextView) findViewById(R.id.txtload);
				loadtext.setTextSize(20);
				loadtext.setTextColor(Color.BLUE);
				super.onPageStarted(view, url, favicon);
			}
			@Override
			public void onPageFinished(WebView view, String url) {
				pd.dismiss();
				loadtext.setVisibility(View.GONE);
				super.onPageFinished(view, url);
			}
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				webBrowser.setVisibility(View.GONE);
				errorbtn.setVisibility(View.VISIBLE);
				//errorimg.setVisibility(View.VISIBLE);
				errortext.setTextColor(Color.RED);
				errortext.setVisibility(View.VISIBLE);
				errorbtn.setBackgroundColor(Color.GREEN);
				errorbtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
					Web.this.finish();
					}
				});
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		};
		webBrowser.setWebViewClient(webClient);
		webBrowser.saveState(null);
		webBrowser.loadUrl(url);
		//buttons kunavigate webview forward and back
      btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				webBrowser.goBack();
				
			}
		});
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			webBrowser.goForward();
				
			}
		});
	}
	
	
		@Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK) {
			Web.this.finish();
		
		}
		return super.onKeyDown(keyCode, event);
	}

}

