package com.ug.telescopio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ShowSearchQueryActivity extends Activity {
	public static final String QUERY =  "query";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_search_query);
		
		Intent intent = getIntent();
		String queryText = intent.getStringExtra(QUERY);
		if(queryText != null){
			TextView txtMsg = (TextView)findViewById(R.id.txtMsg);
			txtMsg.setText(queryText);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_search_query, menu);
		return true;
	}

}
