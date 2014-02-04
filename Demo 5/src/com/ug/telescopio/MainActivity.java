package com.ug.telescopio;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnSearch;
	Button btnOpenActivity;
	ScrollView inputControls;
	public static final String TAG = MainActivity.class.toString();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
       
        
        ButtonListener listener = new ButtonListener();
        btnSearch.setOnClickListener(listener);
        btnOpenActivity.setOnClickListener(listener);
        /*
        Button btnList = new Button(this);
        btnList.setText(getString(R.string.btn_list));
        btnList.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        */
        LinearLayout mainContent = (LinearLayout)findViewById(R.id.mainContent);
        inputControls = (ScrollView)View.inflate(this, R.layout.input_controls_content, null);
        //mainContent.addView(btnList);
        setInputControls();
        mainContent.addView(inputControls);
    }

    public void setInputControls(){
    	SeekBar seekbar = (SeekBar) inputControls.findViewById(R.id.seekBar1);
    	RatingBar ratingBar =(RatingBar) inputControls.findViewById(R.id.ratingBar1);
    	Spinner spinner = (Spinner) inputControls.findViewById(R.id.spinner1);
    	CheckBox checkBox = (CheckBox) inputControls.findViewById(R.id.checkBox1);
    	RadioGroup radioGroup = (RadioGroup) inputControls.findViewById(R.id.radioGroup1);
    	
    	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String option = "";
				switch(checkedId){
					case R.id.radio0:
						option="A";
						break;
					case R.id.radio1:
						option="B";
						break;
					case R.id.radio2:
						option="C";
						break;
				}
				Log.e(TAG, "Seleccionado " + option);
			}
    		
    	};
    	
    	radioGroup.setOnCheckedChangeListener(checkedChangeListener);
    	
    	checkBox.setChecked(true);
    	
    	ArrayList<String> names = new ArrayList<String>();
    	names.add("Hugo");
    	names.add("Paco");
    	names.add("Luis");
    	ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,names);
    	
    	spinner.setAdapter(namesAdapter);
    	ratingBar.setRating((float)2.5);
    	
    	seekbar.setMax(10);
    	seekbar.setProgress(5);
    	seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Toast.makeText(getApplicationContext(), "Cambio a " + progress, Toast.LENGTH_SHORT).show();
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
    		  
    		  
    	});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			EditText searchQuery = (EditText)findViewById(R.id.editTextSearchQuery);
			String searchQueryText = searchQuery.getText().toString();
			String url = "https://www.google.com/q=" + searchQueryText + "#q=" + searchQueryText;
			Intent intent = null;
			if (v.getId() == btnOpenActivity.getId()){
				intent = new Intent(getApplicationContext(), ShowSearchQueryActivity.class);
				intent.putExtra(ShowSearchQueryActivity.QUERY, searchQueryText);
			}else if(v.getId() == btnSearch.getId()){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
			}
			
			startActivity(intent);
			Log.i(TAG, "hizo click!");
			Toast.makeText(getApplicationContext(), "hizo click!", Toast.LENGTH_SHORT).show();
		}
    	
    }
}
