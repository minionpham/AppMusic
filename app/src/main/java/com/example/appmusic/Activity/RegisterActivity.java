package com.example.appmusic.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmusic.IP;
import com.example.appmusic.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    String urlRegister="http://"+new IP().Ip +"/Server/register.php";
EditText edtName, edtEmail, edtPassword;
Button btnRegister;
private String email,name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Anhxa();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString();
                name = edtName.getText().toString();
                pass = edtPassword.getText().toString();
               if(email.equals("") || name.equals("") || pass.equals("")){
                   Toast.makeText(RegisterActivity.this, "Vui lòng nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
               }
               else{
                   if(isValidEmail(email)){
                       Register(urlRegister);
                   }
                   else {
                       Toast.makeText(RegisterActivity.this, "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
    }

    private void Register(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.trim().equals("success")){
                        Toast.makeText(RegisterActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                    else {
                        if (response.trim().equals("Email da ton tai")) {
                            Toast.makeText(RegisterActivity.this, "Tài khoản Email đã tồn tại!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Lỗi đăng kí..", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("Name", edtName.getText().toString().trim());
                params.put("Email", edtEmail.getText().toString().trim());
                params.put("Password", edtPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Anhxa() {
        edtName = findViewById(R.id.edtname);
        edtEmail= findViewById(R.id.edtemail);
        edtPassword = findViewById(R.id.edtpassword);
        btnRegister= findViewById(R.id.btnregister);
    }

    private boolean isValidEmail(String email){
                String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }
}