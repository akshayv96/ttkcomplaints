package com.example.ttkhealthcare_complaints;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String name,ph,email,c_from,p_from,sent,brand,addr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Spinner Spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.category,android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(staticAdapter);
        
        Spinner Spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter.createFromResource(this, R.array.category,android.R.layout.simple_spinner_item);
        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(staticAdapter2);
        

        Spinner Spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> staticAdapter3 = ArrayAdapter.createFromResource(this, R.array.choice,android.R.layout.simple_spinner_item);
        staticAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner3.setAdapter(staticAdapter3);
        

        Spinner Spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> staticAdapter4 = ArrayAdapter.createFromResource(this, R.array.product,android.R.layout.simple_spinner_item);
        staticAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner4.setAdapter(staticAdapter4);

    
    TextView nextButton = (TextView) findViewById(R.id.button1);
    nextButton.setOnClickListener(new View.OnClickListener() {

        @Override
    public void onClick(View v) {
      
        	EditText _name = (EditText) findViewById(R.id.editText1);
            name = _name.getText().toString();
            EditText _ph = (EditText) findViewById(R.id.editText2);
            ph = _ph.getText().toString();
            EditText _email = (EditText) findViewById(R.id.editText3);
            email = _email.getText().toString();
            EditText _addr = (EditText) findViewById(R.id.editText4);
            addr = _addr.getText().toString();
            Spinner _spinner1 = (Spinner)findViewById(R.id.spinner1);
            c_from = _spinner1.getSelectedItem().toString();	
            Spinner _spinner2 = (Spinner)findViewById(R.id.spinner2);
            p_from = _spinner2.getSelectedItem().toString();	
            Spinner _spinner3 = (Spinner)findViewById(R.id.spinner3);
            sent = _spinner3.getSelectedItem().toString();	
            Spinner _spinner4 = (Spinner)findViewById(R.id.spinner4);
            brand = _spinner4.getSelectedItem().toString();	
            
            
        	if(name.isEmpty()||ph.isEmpty()||email.isEmpty()|addr.isEmpty())
            {
            Toast.makeText(getApplicationContext(),"All fields are mandatory.",Toast.LENGTH_SHORT).show();
            return;
        }
		//Check if a valid email is entered
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(getApplicationContext(),"Please enter a valid email.",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!android.util.Patterns.PHONE.matcher(ph).matches())
        {
            Toast.makeText(getApplicationContext(),"Please enter a valid phone number.",Toast.LENGTH_SHORT).show();
            return;
        }
            
            
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            Bundle b = new Bundle();
            b.putString("name",name);
            b.putString("ph",ph);
            b.putString("email",email);
            b.putString("c_from",c_from);
            b.putString("p_from",p_from);
            b.putString("sent",sent);
            b.putString("brand",brand);
            b.putString("addr",addr);
            intent.putExtras(b);
    		startActivity(intent);
 		
    }

    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
