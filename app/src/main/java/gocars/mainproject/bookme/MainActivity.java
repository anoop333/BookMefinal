package gocars.mainproject.bookme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name, uname, pass, confpass, address, phone;
    Button submit;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = findViewById(R.id.button);
        name = findViewById(R.id.uname);
        uname = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        confpass = findViewById(R.id.confpass);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        login = findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || uname.getText().toString().isEmpty() || pass.getText()
                        .toString().isEmpty() || confpass.getText().toString().isEmpty() || address.getText()
                        .toString().isEmpty() || phone.getText().toString().isEmpty() ) {
                    Toast.makeText(MainActivity.this, "Fill All Fields", Toast.LENGTH_LONG).show();
                }
                else if(!pass.getText().toString().equals(confpass.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Password Mismatch", Toast.LENGTH_LONG).show();
                }
                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://anoopsuvarnan1.000webhostapp.com/bookproject/reg.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server
                                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");


                                        }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            },

                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
//Adding parameters to request

                            params.put("name", name.getText().toString());
                            params.put("password", pass.getText().toString());
                            params.put("uname",uname.getText().toString());
                            params.put("address",address.getText().toString());
                            params.put("phone",phone.getText().toString());
// Toast.makeText(Pay.this,"submitted",Toast.LENGTH_LONG).show();

//returning parameter
                            return params;
                        }

                    };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);
                }


            }
        });

    }
}

