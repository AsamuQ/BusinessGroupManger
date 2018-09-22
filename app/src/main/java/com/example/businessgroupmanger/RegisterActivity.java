package com.example.businessgroupmanger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.businessgroupmanger.db.AccountDAO;
import com.example.businessgroupmanger.tables.Account;

public class RegisterActivity extends Activity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnRegister;
    private Button btnGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        etUsername=(EditText)findViewById(R.id.et_username);
        etPassword=(EditText)findViewById(R.id.et_password);

        btnGoLogin = findViewById(R.id.btngologin);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });

        btnRegister = findViewById(R.id.btnregister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    /**

     * 注册

     */

    private void register() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        }

        // 先判断改用户信息是否已经被注册
        AccountDAO accountDAO = new AccountDAO(getApplicationContext());
        Account account = accountDAO.find(username);

        if (account!= null) {
            Toast.makeText(this, "该用户名已经被注册！", Toast.LENGTH_SHORT).show();
        } else if(username.length()>0&&password.length()>0) {
            account = new Account(username, password);
            accountDAO.add(account);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

            // 注册成功后，跳转登录页面让用户进行登录
            goLogin();
        }
    }

    /**

     * 去登录

     */

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}