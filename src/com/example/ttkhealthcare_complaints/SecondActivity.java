package com.example.ttkhealthcare_complaints;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity{

	String name,ph,email,c_from,p_from,sent,brand,addr;
	String prod,var,batch,desc,tpu,category;
	public static final String name_KEY="entry.1227500885";
	public static final String ph_KEY="entry.445941624";
	public static final String email_KEY="entry.965844521";
	public static final String c_from_KEY="entry.641216612";
	public static final String p_from_KEY="entry.1561860293";
	public static final String sent_KEY="entry.1283830932";
	public static final String brand_KEY="entry.1181196250";
	public static final String addr_KEY="entry.1976427961";
	public static final String prod_KEY="entry.915477921";
	public static final String var_KEY="entry.1515984526";
	public static final String batch_KEY="entry.897078929";
	public static final String desc_KEY="entry.1939759462";
	public static final String tpu_KEY="entry.122510123";
	public String track="";
	public static final String category_KEY="entry.2111526079";
	String encoded="https://docs.google.com/spreadsheets/d/1D_YbpVn29163CpqLJTMOnkke_jBdoPB6XB_Xu-1xYOM/gviz/tq?tqx=out:JSON&tq=select%20P%20order%20by%20A%20desc%20limit%201";
	Context c=this;
	 public static final MediaType FORM_DATA_TYPE= MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
	public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLSeAQZYnXybtD8EvxG9ypiearD5BvB7s9ADydQvIKQD3HeY7yg/formResponse";
ProgressBar spinner;
	@Override

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_form);
        
        
       
        Spinner Spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.fault,android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(staticAdapter);
        
        Intent i = getIntent();
        Bundle extras = i.getExtras();

        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        
        name = extras.getString("name");
        ph = extras.getString("ph");
        email = extras.getString("email");
        c_from = extras.getString("c_from");
        p_from = extras.getString("p_from");
        sent = extras.getString("sent");
        brand = extras.getString("brand");
        addr = extras.getString("addr");
        
   	 TextView nextButton = (TextView) findViewById(R.id.button1);
	    nextButton.setOnClickListener(new View.OnClickListener() {

	        @Override
	    public void onClick(View v) {
	      
	        	EditText _prod = (EditText) findViewById(R.id.editText1);
	            prod = _prod.getText().toString();
	            EditText _var = (EditText) findViewById(R.id.editText2);
	            var = _var.getText().toString();
	            EditText _batch = (EditText) findViewById(R.id.editText3);
	            batch = _batch.getText().toString();
	            EditText _desc = (EditText) findViewById(R.id.editText4);
	            desc = _desc.getText().toString();
	            EditText _tpu = (EditText) findViewById(R.id.editText5);
	            tpu = _tpu.getText().toString();
	            Spinner _spinner1 = (Spinner)findViewById(R.id.spinner1);
	            category = _spinner1.getSelectedItem().toString();
	            
	        
	        	if(prod.isEmpty()||var.isEmpty()||batch.isEmpty())
                {
                Toast.makeText(getApplicationContext(),"Product , Variant and Batch number are mandatory.",Toast.LENGTH_SHORT).show();
                return;
            }
	        	
	            spinner.setVisibility(View.VISIBLE);
	            
	            PostDataTask postDataTask = new PostDataTask();
        	   postDataTask.execute(name,ph,email,c_from,p_from,sent,brand,addr,prod,var,batch,desc,tpu,category,URL);
        	   Handler handler = new Handler(); 
        	    handler.postDelayed(new Runnable() {
        	        
        	         public void run() { 
        	        	 if (!encoded.isEmpty()) {
      	                   new DownloadWebpageTask(new AsyncResult()  {
      	                            @Override
      	                            public void onResult(JSONObject object) {
      	                                processJson(object);
      	                            }

      	     						
      	                        },c).execute(encoded);
      	                    }
        	        	
        	       
        	    }   
        	    }, 5000);       	             
        	    
			 
        	  // Intent target = new Intent(SecondActivity.this,Trackingid.class);
               //startActivity(target);   
        	    
        	 
        	    
	            
	        }
	        
	        private void processJson(JSONObject object) {
				// TODO Auto-generated method stub
				  try {
					  JSONArray rows = object.getJSONArray("rows");
					  String t="";
			            for (int r = 0; r < rows.length(); ++r) {
			                JSONObject row = rows.getJSONObject(r);
			                JSONArray columns = row.getJSONArray("c");

			              t = columns.getJSONObject(0).getString("v");
			            
			            }
		       
			
					track=t;
				/*  	  
       	         final GMailSender sender = new GMailSender("healthcarettk@gmail.com", "srittkcomp");
    			    new AsyncTask<Void, Void, Void>() {
    			       @Override
                       public Void doInBackground(Void... arg) {
    			            try {   
    			     		 //Toast.makeText(getApplicationContext(),"Track :"+track,Toast.LENGTH_LONG).show();
    			            	sender.sendMail("TTK Healthcare - Complaint Registration Successful.",   
    			                    "Dear "+name+", \nPlease note down your tracking id : "+track+". \nUse this tracking id for all further communications. \nWith regards, \nTTK Healthcare team. \n \n\nFor any grievances, \nContact:\nTTK HealthCare Limited\n6,Cathedral Road\nChennai-600028.",   
    			                    "healthcarettk@gmail.com",   
    			                    email);   
    			            } catch (Exception e) {   
    			                Log.d("SendMail", e.getMessage(), e);   
    			            }
    						return null; 
    			        }
    			    }.execute();
					*/ //Toast.makeText(c,"Track :"+track,Toast.LENGTH_LONG).show();
					  Intent target1 = new Intent(c, Trackingid.class);
					  target1.putExtra("key", t);
					  startActivity(target1);

		            } catch (JSONException e) {
		                e.printStackTrace();
		            }
			}

	    });
	}
	
	private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
        	String name=contactData[0],ph=contactData[1],email=contactData[2],c_from=contactData[3],p_from=contactData[4],sent=contactData[5],brand=contactData[6],addr=contactData[7];
        	String prod=contactData[8],var=contactData[9],batch=contactData[10],desc=contactData[11],tpu=contactData[12],category=contactData[13];
            String url=contactData[14];
        	String postBody="";
            try {
				
                postBody = name_KEY+"=" + URLEncoder.encode(name,"UTF-8") + 
							"&" +ph_KEY + "=" + URLEncoder.encode(ph,"UTF-8") + 
							"&" + email_KEY + "=" + URLEncoder.encode(email,"UTF-8")+ 
							"&" + c_from_KEY + "=" + URLEncoder.encode(c_from,"UTF-8")+ 
							"&" + p_from_KEY + "=" + URLEncoder.encode(p_from,"UTF-8")+ 
							"&" + sent_KEY + "=" + URLEncoder.encode(sent,"UTF-8")+ 
							"&" + brand_KEY + "=" + URLEncoder.encode(brand,"UTF-8")+ 
							"&" + addr_KEY + "=" + URLEncoder.encode(addr,"UTF-8")+ 
							"&" + prod_KEY + "=" + URLEncoder.encode(prod,"UTF-8")+ 
							"&" + var_KEY + "=" + URLEncoder.encode(var,"UTF-8")+ 
							"&" + batch_KEY + "=" + URLEncoder.encode(batch,"UTF-8")+ 
							"&" + desc_KEY + "=" + URLEncoder.encode(desc,"UTF-8")+ 
							"&" + tpu_KEY + "=" + URLEncoder.encode(tpu,"UTF-8")+ 
							"&" + category_KEY + "=" + URLEncoder.encode(category,"UTF-8");
         
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }
            
        
            
            try{
                OkHttpClient client = new OkHttpClient();
				RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
				//Send the request
                Response response = client.newCall(request).execute();
               

            }catch (IOException exception){
                result=false;
            }
            return result;
        }
            

		
        protected void onPostExecute(Boolean result){
			 Toast.makeText(c,result?"Complaint successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_SHORT).show();
        
            
        }
	}
	
}


