package com.example.ttkhealthcare_complaints;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Intent recIntent = getIntent();
        Context c=this;
        String user = recIntent.getStringExtra("key");
        String query="https://docs.google.com/spreadsheets/d/1D_YbpVn29163CpqLJTMOnkke_jBdoPB6XB_Xu-1xYOM/gviz/tq?tqx=out:JSON&tq=select%20Q%20where%20P=\""+user+"\"";
        if (!query.isEmpty()) {
               new DownloadWebpageTask(new AsyncResult() {
                        @Override
               
                        public void onResult(JSONObject object) {
                            processJson(object);
                        }

						
                    },c).execute(query);
                }
    
    }

    private void processJson(JSONObject object) {
		// TODO Auto-generated method stub
		  try {
			  String t="";
			  
			  JSONArray rows = object.getJSONArray("rows");
			  if(rows.length()>0){
	            for (int r = 0; r < rows.length(); ++r) {
	                JSONObject row = rows.getJSONObject(r);
	                JSONArray columns = row.getJSONArray("c");

	              t = columns.getJSONObject(0).getString("v");
	            if(t.isEmpty()||t.contentEquals("null")||t.equals("null"))
	            	t="Complaint is yet to be processed. Please check after sometime"; 
	            }
			  }
			  else t="Invalid tracking id";
			  TextView textViewToChange = (TextView) findViewById(R.id.textView1);
	            textViewToChange.setText(t);

            } catch (JSONException e) {
                e.printStackTrace();
            }
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
