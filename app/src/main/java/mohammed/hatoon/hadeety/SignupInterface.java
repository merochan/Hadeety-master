package mohammed.hatoon.hadeety;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupInterface extends Activity {

    EditText etUsername, etPassword, etEmail , etBdate;
    Button  btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_interface);

        etUsername = (EditText)findViewById(R.id.etUsernameReg);
        etPassword = (EditText)findViewById(R.id.etPasswordReg);
        etEmail = (EditText)findViewById(R.id.etEmailReg);
        etBdate = (EditText)findViewById(R.id.etBDate);
        btnSignup = (Button)findViewById(R.id.butSignupReg);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                postStringRequest("http://10.0.2.2/mygift/volleyRegister.php",name,email,password);
            }
        });


    }

  public void postStringRequest(final String url, final String name, final String email, final String password){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(),"SUCCESS "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(
                                        SignupInterface.this,
                                        MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Post params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}


