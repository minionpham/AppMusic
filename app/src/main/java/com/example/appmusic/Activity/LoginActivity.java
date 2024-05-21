package com.example.appmusic.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.appmusic.IP;
import com.example.appmusic.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
String urlLogin="http://"+new IP().Ip+"/Server/login.php";
EditText edtEmail, edtPassword;
Button btnLogin;
TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(isValidEmail(email)){
                        Login(urlLogin);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Vui lòng nhập đúng địa chỉ Email!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
    private void Login(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.trim().equals("Email khong ton tai")){
                        Toast.makeText(LoginActivity.this, "Tài khoản Email không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(response.trim().equals("Sai mat khau")){
                            Toast.makeText(LoginActivity.this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                        SharedPreferences sharedPreferences= getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("IdUser",response);
                        editor.apply();
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));}
                    }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "ERROR!!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Email", edtEmail.getText().toString().trim());
                params.put("Password", edtPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void Anhxa() {
        edtEmail=findViewById(R.id.edtemail);
        edtPassword=findViewById(R.id.edtpassword);
        btnLogin=findViewById(R.id.btnlogin);
        tvRegister=findViewById(R.id.tvregister);
    }
    private boolean isValidEmail(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}