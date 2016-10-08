package com.example.ttkhealthcare_complaints;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Trackingid extends Activity {

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.tracking);
	        
	        Intent recIntent = getIntent();
	        String user = recIntent.getStringExtra("key");
	        
	        
			  TextView textViewToChange = (TextView) findViewById(R.id.textView2);
			  textViewToChange.setText("Tracking id : "+user);
			
			  
			  TextView sButton = (TextView) findViewById(R.id.button1);
		        sButton.setOnClickListener(new View.OnClickListener() {

		            @Override
		            public void onClick(View arg0) {

		                Intent intencion = new Intent(Trackingid.this,LoginActivity.class );
		                startActivity(intencion);
		            }

		        });
		    
	    }

}
