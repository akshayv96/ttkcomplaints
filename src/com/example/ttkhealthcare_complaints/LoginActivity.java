package com.example.ttkhealthcare_complaints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

   
  TextView signUpButton = (TextView) findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intencion = new Intent(LoginActivity.this,MainActivity.class );
                startActivity(intencion);
            }

        });
    

        TextView signinButton = (TextView) findViewById(R.id.login_button);
        signinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            	 String username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
            	 if(username.isEmpty())
                 {
                 Toast.makeText(getApplicationContext(), "Field cannot be blank!", Toast.LENGTH_SHORT).show();
                 return;
             }
            	 
            	 Intent intent = new Intent(LoginActivity.this, SignIn.class); 
                  intent.putExtra("key", username);
                  startActivity(intent);
            	 }

        });  
   
        
        TextView sButton = (TextView) findViewById(R.id.button1);
        sButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
            	Intent inwtent = new Intent(LoginActivity.this, ContactUs.class); 
                  startActivity(inwtent);
            	 }

        });  
   
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_exit) {
            finish();
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }



}